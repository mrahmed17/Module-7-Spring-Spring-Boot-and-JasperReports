package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.*;
import com.mrahmed.HRandPayrollManagementSystem.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private UserService userService;

    /**
     * Save a new salary record.
     */
    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    /**
     * Update an existing salary record.
     */
    public Salary updateSalary(Long salaryId, Salary salary) {
        return salaryRepository.findById(salaryId).map(existingSalary -> {
            salary.setId(salaryId); // Ensure the same ID is retained
            return salaryRepository.save(salary);
        }).orElseThrow(() -> new RuntimeException("Salary record not found."));
    }

    /**
     * Delete a salary record by ID.
     */
    public void deleteSalary(Long salaryId) {
        if (!salaryRepository.existsById(salaryId)) {
            throw new RuntimeException("Salary record not found.");
        }
        salaryRepository.deleteById(salaryId);
    }

    /**
     * Get a salary record by ID.
     */
    public Salary getSalaryById(Long salaryId) {
        return salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary record not found."));
    }

    /**
     * Calculate overtime salary for a specific user between a date range.
     */
    public double calculateOvertimeSalary(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        double totalOvertimeHours = salaryRepository.getTotalOvertimeHoursByUserAndDateRange(userId, startDate, endDate);
        User user = userService.findUserById(userId);
        double basicSalary = user.getBasicSalary();

        // Assume a 40-hour workweek by default; make this configurable as needed
        double hourlyRate = basicSalary / (4 * 5 * 8);

        // Apply any overtime rate multiplier, e.g., 1.5x
        double overtimeRate = 1.5; // Adjust this based on policy
        return totalOvertimeHours * hourlyRate * overtimeRate;
    }

    /**
     * Calculate the total salary for a user for a specific period.
     */
    @Transactional
    public double calculateTotalSalary(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Salary> latestSalaries = salaryRepository.findLatestSalaryByUser(userId);
        if (latestSalaries.isEmpty()) {
            throw new RuntimeException("No salary record found for the user.");
        }

        Salary latestSalary = latestSalaries.get(0);
        double overtimeSalary = calculateOvertimeSalary(userId, startDate, endDate);

        // Calculate total bonuses
        double totalBonuses = latestSalary.getBonuses().stream()
                .mapToDouble(Bonus::getBonusAmount)
                .sum();

        // Calculate total allowances and deductions
        double totalAllowances = calculateTotalAllowances(latestSalary);
        double totalDeductions = calculateTotalDeductions(latestSalary);

        // Final salary calculation
        return latestSalary.getNetSalary() + overtimeSalary + totalBonuses + totalAllowances - totalDeductions;
    }

    private double calculateTotalAllowances(Salary salary) {
        return Optional.ofNullable(salary.getTransportAllowance()).orElse(0.0) +
                Optional.ofNullable(salary.getTelephoneSubsidy()).orElse(0.0) +
                Optional.ofNullable(salary.getUtilityAllowance()).orElse(0.0) +
                Optional.ofNullable(salary.getDomesticAllowance()).orElse(0.0) +
                Optional.ofNullable(salary.getLunchAllowance()).orElse(0.0);
    }

    private double calculateTotalDeductions(Salary salary) {
        return Optional.ofNullable(salary.getMedicare()).orElse(0.0) +
                Optional.ofNullable(salary.getProvidentFund()).orElse(0.0) +
                Optional.ofNullable(salary.getInsurance()).orElse(0.0) +
                Optional.ofNullable(salary.getTax()).orElse(0.0);
    }

    // Other methods for fetching salaries by different criteria...

    public List<Salary> getSalariesByMonthAndYear(Month payrollMonth, int year) {
        return salaryRepository.findSalariesByMonthAndYear(payrollMonth, year);
    }

    public double getTotalOvertimeSalary(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return salaryRepository.getTotalOvertimeSalaryByUserAndDateRange(userId, startDate, endDate);
    }


    public List<Salary> getSalariesByUserAndYear(Long userId, int year) {
        return salaryRepository.findSalariesByUserAndYear(userId, year);
    }

    public double getTotalOvertimeHours(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return salaryRepository.getTotalOvertimeHoursByUserAndDateRange(userId, startDate, endDate);
    }

    public List<Salary> getSalariesByUserYearAndMonth(Long userId, int year, Month payrollMonth) {
        return salaryRepository.findSalariesByUserYearAndMonth(userId, year, payrollMonth);
    }

    public List<Salary> getSalariesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return salaryRepository.findSalariesByDateRange(startDate, endDate);
    }

    public double getTotalSalaryByUserAndYear(Long userId, int year) {
        return salaryRepository.getTotalSalaryByUserAndYear(userId, year);
    }



}
