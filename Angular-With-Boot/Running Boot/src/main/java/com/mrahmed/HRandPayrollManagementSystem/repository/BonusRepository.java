package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Bonus;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    @Query("SELECT b.bonusAmount FROM Bonus b WHERE b.user.id = :userId AND b.year = :year")
    Optional<Double> getBonusForUserAndYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(b.bonusAmount), 0.0) FROM Bonus b WHERE b.user.id = :userId AND b.year = :year")
    double getTotalBonusForUserAndYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT b FROM Bonus b WHERE b.bonusMonth = :bonusMonth AND b.year = :year ORDER BY b.bonusDate DESC")
    List<Bonus> getBonusesByMonthAndYear(@Param("bonusMonth") Month bonusMonth, @Param("year") int year);

    @Query("SELECT b FROM Bonus b WHERE b.user.id = :userId AND b.bonusMonth = :bonusMonth AND b.year = :year")
    Optional<Bonus> getBonusForUserByMonthAndYear(@Param("userId") Long userId, @Param("bonusMonth") Month bonusMonth, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(b.bonusAmount), 0.0) FROM Bonus b WHERE b.year = :year")
    double getTotalBonusPaidInYear(@Param("year") int year);

    @Query("SELECT b FROM Bonus b WHERE b.bonusDate BETWEEN :startDate AND :endDate ORDER BY b.bonusDate ASC")
    List<Bonus> getBonusesBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT DISTINCT b.user.id FROM Bonus b WHERE b.year = :year")
    List<Long> getUsersWhoReceivedBonusInYear(@Param("year") int year);

    @Query("SELECT b FROM Bonus b WHERE b.user.id = :userId ORDER BY b.bonusDate DESC")
    List<Bonus> getLatestBonusForUser(@Param("userId") Long userId);

    @Query("SELECT COUNT(b) FROM Bonus b WHERE b.user.id = :userId AND b.year = :year")
    int countBonusesForUserInYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(b.bonusAmount), 0.0) FROM Bonus b WHERE b.bonusMonth = :bonusMonth AND b.year = :year")
    double getTotalBonusForMonthAndYear(@Param("bonusMonth") Month bonusMonth, @Param("year") int year);


}
