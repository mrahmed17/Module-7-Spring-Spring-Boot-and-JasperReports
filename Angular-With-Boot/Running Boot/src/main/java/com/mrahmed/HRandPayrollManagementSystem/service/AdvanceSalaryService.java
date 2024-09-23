package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.AdvanceSalary;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.repository.AdvanceSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdvanceSalaryService {


    @Autowired
    private AdvanceSalaryRepository advanceSalaryRepository;

    // Save new advance salary record
    public AdvanceSalary saveAdvanceSalary(AdvanceSalary advanceSalary) {
        return advanceSalaryRepository.save(advanceSalary);
    }

    // Update existing advance salary record
    public AdvanceSalary updateAdvanceSalary(AdvanceSalary advanceSalary) {
        if (advanceSalary.getId() != 0 && advanceSalaryRepository.existsById(advanceSalary.getId())) {
            return advanceSalaryRepository.save(advanceSalary);
        }
        throw new IllegalArgumentException("AdvanceSalary with ID " + advanceSalary.getId() + " does not exist.");
    }

    // Delete advance salary record by ID
    public void deleteAdvanceSalary(Long id) {
        if (advanceSalaryRepository.existsById(id)) {
            advanceSalaryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("AdvanceSalary with ID " + id + " does not exist.");
        }
    }

    // Find advance salary record by ID
    public Optional<AdvanceSalary> getAdvanceSalaryById(Long id) {
        return advanceSalaryRepository.findById(id);
    }

    // Find advance salaries by user and year
    public List<AdvanceSalary> getAdvanceSalariesByUserAndYear(Long userId, int year) {
        return advanceSalaryRepository.findByUserIdAndYear(userId, year);
    }

    // Find advance salaries by user, year, and month
    public List<AdvanceSalary> getAdvanceSalariesByUserYearAndMonth(Long userId, int year, Month month) {
        return advanceSalaryRepository.findByUserIdYearAndMonth(userId, year, month);
    }

    // Calculate total advance salary for a user in a specific year
    public BigDecimal getTotalAdvanceSalaryByUserAndYear(Long userId, int year) {
        return advanceSalaryRepository.getTotalAdvanceSalaryByUserAndYear(userId, year);
    }

    // Find advance salaries within a specific date range
    public List<AdvanceSalary> getAdvanceSalariesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return advanceSalaryRepository.findByDateRange(startDate, endDate);
    }

    // Find advance salaries for a specific month and year
    public List<AdvanceSalary> getAdvanceSalariesByMonthAndYear(Month month, int year) {
        return advanceSalaryRepository.findByMonthAndYear(month, year);
    }

    // Find latest advance salary record for a user
    public List<AdvanceSalary> getLatestAdvanceSalaryByUser(Long userId) {
        return advanceSalaryRepository.findLatestAdvanceSalaryByUser(userId);
    }

}
