package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.Bonus;
import com.mrahmed.HRandPayrollManagementSystem.entity.LeaveType;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.repository.BonusRepository;
import com.mrahmed.HRandPayrollManagementSystem.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BonusService {

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    public Bonus saveBonus(Bonus bonus) {
        return bonusRepository.save(bonus);
    }

    public Bonus updateBonus(Long id, Bonus updatedBonus) {
        if (bonusRepository.existsById(id)) {
            updatedBonus.setId(id); // Ensure the ID is set for the update
            return bonusRepository.save(updatedBonus);
        } else {
            throw new RuntimeException("Bonus not found with id " + id);
        }
    }

    // Calculate bonus for a user in a specific year considering unpaid leaves
    public double calculateBonus(Long userId, int year) {
        int totalUnpaidLeaveDays = leaveRepository.getTotalUnpaidLeaveDays(
                userId,
                Arrays.asList(LeaveType.SICK_UNPAID, LeaveType.RESERVE_UNPAID),
                year
        );

        Double baseBonus = bonusRepository.getBonusForUserAndYear(userId, year).orElse(0.0);

        double deduction = calculateLeaveBonusDeduction(totalUnpaidLeaveDays);

        return baseBonus - deduction;
    }

    // Deduction logic for unpaid leave days
    private double calculateLeaveBonusDeduction(int totalUnpaidLeaveDays) {
        double deductionPerDay = 1000.0; // Example: Deduct $1000 per unpaid leave day
        return deductionPerDay * totalUnpaidLeaveDays;
    }

    // Get total bonus for a user for a specific year
    public double getTotalBonusForUser(Long userId, int year) {
        Double totalBonus = bonusRepository.getTotalBonusForUserAndYear(userId, year);
        return totalBonus != null ? totalBonus : 0.0;
    }

    // Get all bonuses for a specific month and year
    public List<Bonus> getBonusesByMonthAndYear(Month month, int year) {
        return bonusRepository.getBonusesByMonthAndYear(month, year);
    }

    // Get the total bonus paid in a specific year
    public double getTotalBonusPaidInYear(int year) {
        return bonusRepository.getTotalBonusPaidInYear(year);
    }

    // Get bonuses for a user for a specific month and year (handling Optional)
    public Bonus getBonusForUserByMonthAndYear(Long userId, Month month, int year) {
        return bonusRepository.getBonusForUserByMonthAndYear(userId, month, year)
                .orElseThrow(() -> new RuntimeException("Bonus not found for user " + userId + " for the given month and year"));
    }

    // Get bonuses between a date range
    public List<Bonus> getBonusesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return bonusRepository.getBonusesBetweenDates(startDate, endDate);
    }

    // Get users who received a bonus in a specific year
    public List<Long> getUsersWhoReceivedBonusInYear(int year) {
        return bonusRepository.getUsersWhoReceivedBonusInYear(year);
    }

    // Get the latest bonus for a user (handling List)
    public Bonus getLatestBonusForUser(Long userId) {
        List<Bonus> bonuses = bonusRepository.getLatestBonusForUser(userId);
        if (bonuses.isEmpty()) {
            throw new RuntimeException("No bonuses found for user " + userId);
        }
        return bonuses.get(0); // Return the first (latest) bonus
    }

    // Count the number of bonuses for a user in a specific year
    public int countBonusesForUserInYear(Long userId, int year) {
        return bonusRepository.countBonusesForUserInYear(userId, year);
    }

    // Get total bonus for a specific month and year
    public double getTotalBonusForMonthAndYear(Month month, int year) {
        return bonusRepository.getTotalBonusForMonthAndYear(month, year);
    }
}
