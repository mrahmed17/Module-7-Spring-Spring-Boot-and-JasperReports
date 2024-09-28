package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Bonus;
import com.mrahmed.HRandPayrollManagementSystem.entity.LeaveType;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {


    // Find bonuses by user's email
    @Query("SELECT b FROM Bonus b WHERE b.user.email = :email")
    List<Bonus> findBonusesByEmail(@Param("email") String email);

    // Find bonuses by user's full name (partial match, case-insensitive)
    @Query("SELECT b FROM Bonus b WHERE LOWER(b.user.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Bonus> findBonusesByName(@Param("name") String name);

    // Calculate total bonuses for a user by name
    @Query("SELECT SUM(b.bonusAmount) FROM Bonus b WHERE b.user.id = :userId AND LOWER(b.user.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    double getTotalBonusByName(@Param("userId") Long userId);

    // Find bonuses by user ID
    @Query("SELECT b FROM Bonus b WHERE b.user.id = :userId")
    List<Bonus> findBonusesByUserId(@Param("userId") Long userId);

    // Calculate total bonuses for a specific user by user ID
    @Query("SELECT SUM(b.bonusAmount) FROM Bonus b WHERE b.user.id = :userId")
    double getTotalBonusByUserId(@Param("userId") Long userId);

    // Find bonuses by specific month
    @Query("SELECT b FROM Bonus b WHERE b.bonusMonth = :month")
    List<Bonus> findBonusesByMonth(@Param("month") Month month);

    // Calculate total bonuses for a specific month
    @Query("SELECT SUM(b.bonusAmount) FROM Bonus b WHERE b.bonusMonth = :month")
    double getTotalBonusByMonth(@Param("month") Month month);

    // Find bonuses by specific year
    @Query("SELECT b FROM Bonus b WHERE b.year = :year")
    List<Bonus> findBonusesByYear(@Param("year") int year);

    // Calculate total bonuses for a specific year
    @Query("SELECT SUM(b.bonusAmount) FROM Bonus b WHERE b.year = :year")
    double getTotalBonusByYear(@Param("year") int year);

    // Find bonuses within a specific date range
    @Query("SELECT b FROM Bonus b WHERE b.bonusDate BETWEEN :startDate AND :endDate")
    List<Bonus> findBonusesByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Find the latest bonus for a user
    @Query("SELECT b FROM Bonus b WHERE b.user.id = :userId ORDER BY b.bonusDate DESC")
    List<Bonus> findLatestBonusByUserId(@Param("userId") Long userId);

    // Find distinct users who received bonuses in a specific year
    @Query("SELECT DISTINCT b.user.id FROM Bonus b WHERE b.year = :year")
    List<Long> getUsersWhoReceivedBonusInYear(@Param("year") int year);

    // Count the number of bonuses for a specific user in a given year
    @Query("SELECT COUNT(b) FROM Bonus b WHERE b.user.id = :userId AND b.year = :year")
    int countBonusesForUserInYear(@Param("userId") Long userId, @Param("year") int year);


}
