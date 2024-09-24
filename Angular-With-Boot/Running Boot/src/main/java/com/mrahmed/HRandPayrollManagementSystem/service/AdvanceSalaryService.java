package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.AdvanceSalary;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.repository.AdvanceSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdvanceSalaryService {

    @Autowired
    private AdvanceSalaryRepository advanceSalaryRepository;

    /**
     * Save a new advance salary record.
     *
     * @param advanceSalary AdvanceSalary entity
     * @return Saved AdvanceSalary entity
     */
    public AdvanceSalary saveAdvanceSalary(AdvanceSalary advanceSalary) {
        return advanceSalaryRepository.save(advanceSalary);
    }

    /**
     * Update an existing advance salary record.
     *
     * @param advanceSalary AdvanceSalary entity with updated information
     * @return Updated AdvanceSalary entity
     */
    public AdvanceSalary updateAdvanceSalary(AdvanceSalary advanceSalary) {
        if (advanceSalary.getId() != 0 && advanceSalaryRepository.existsById(advanceSalary.getId())) {
            return advanceSalaryRepository.save(advanceSalary);
        }
        throw new IllegalArgumentException("AdvanceSalary with ID " + advanceSalary.getId() + " does not exist.");
    }

    /**
     * Delete an advance salary record by ID.
     *
     * @param id AdvanceSalary ID
     */
    public void deleteAdvanceSalary(Long id) {
        if (advanceSalaryRepository.existsById(id)) {
            advanceSalaryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("AdvanceSalary with ID " + id + " does not exist.");
        }
    }

    /**
     * Get advance salary record by ID.
     *
     * @param id AdvanceSalary ID
     * @return Optional containing the AdvanceSalary entity
     */
    public Optional<AdvanceSalary> getAdvanceSalaryById(Long id) {
        return advanceSalaryRepository.findById(id);
    }

    /**
     * Get advance salaries by user and year.
     *
     * @param userId User ID
     * @param year   Year
     * @return List of AdvanceSalary records for the user
     */
    public List<AdvanceSalary> getAdvanceSalariesByUserAndYear(Long userId, int year) {
        return advanceSalaryRepository.findByUserIdAndYear(userId, year);
    }

    /**
     * Get advance salaries by user, year, and month.
     *
     * @param userId User ID
     * @param year   Year
     * @param month  Month
     * @return List of AdvanceSalary records for the user in the specific month and year
     */
    public List<AdvanceSalary> getAdvanceSalariesByUserYearAndMonth(Long userId, int year, Month month) {
        return advanceSalaryRepository.findByUserIdYearAndMonth(userId, year, month);
    }

    /**
     * Get total advance salary for a user in a specific year.
     *
     * @param userId User ID
     * @param year   Year
     * @return Total advance salary amount
     */
    public double getTotalAdvanceSalaryByUserAndYear(Long userId, int year) {
        return advanceSalaryRepository.getTotalAdvanceSalaryByUserAndYear(userId, year);
    }

    /**
     * Get advance salaries within a specific date range.
     *
     * @param startDate Start date
     * @param endDate   End date
     * @return List of AdvanceSalary records within the date range
     */
    public List<AdvanceSalary> getAdvanceSalariesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return advanceSalaryRepository.findByDateRange(startDate, endDate);
    }

    /**
     * Get advance salaries for a specific month and year.
     *
     * @param month Month
     * @param year  Year
     * @return List of AdvanceSalary records for the specific month and year
     */
    public List<AdvanceSalary> getAdvanceSalariesByMonthAndYear(Month month, int year) {
        return advanceSalaryRepository.findByMonthAndYear(month, year);
    }

    /**
     * Get the latest advance salary record for a user.
     *
     * @param userId User ID
     * @return Optional containing the latest AdvanceSalary record for the user
     */
    public Optional<AdvanceSalary> getLatestAdvanceSalaryByUser(Long userId) {
        return advanceSalaryRepository.findLatestAdvanceSalaryByUser(userId);
    }

    /**
     * Calculate the total advance salary for a user within a specific date range.
     *
     * @param userId    User ID
     * @param startDate Start date
     * @param endDate   End date
     * @return Total advance salary amount within the specified date range
     */
    @Transactional
    public double calculateTotalAdvanceSalary(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<AdvanceSalary> advanceSalaries = getAdvanceSalariesByUserAndYear(userId, startDate.getYear());
        return advanceSalaries.stream()
                .filter(advanceSalary -> advanceSalary.getAdvanceDate().isAfter(startDate) && advanceSalary.getAdvanceDate().isBefore(endDate))
                .mapToDouble(AdvanceSalary::getAdvanceSalary)
                .sum();
    }



}