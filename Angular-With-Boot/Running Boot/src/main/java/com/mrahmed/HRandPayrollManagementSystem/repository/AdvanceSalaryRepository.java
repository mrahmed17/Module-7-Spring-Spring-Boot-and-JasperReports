package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.AdvanceSalary;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdvanceSalaryRepository extends JpaRepository<AdvanceSalary, Long> {

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.id = :userId AND a.year = :year")
    List<AdvanceSalary> findByUserIdAndYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.id = :userId AND a.year = :year AND a.advanceMonth = :month")
    List<AdvanceSalary> findByUserIdYearAndMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") Month month);

    @Query("SELECT COALESCE(SUM(a.advanceSalary), 0.0) FROM AdvanceSalary a WHERE a.user.id = :userId AND a.year = :year")
    double getTotalAdvanceSalaryByUserAndYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.advanceDate BETWEEN :startDate AND :endDate")
    List<AdvanceSalary> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.advanceMonth = :month AND a.year = :year")
    List<AdvanceSalary> findByMonthAndYear(@Param("month") Month month, @Param("year") int year);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.id = :userId ORDER BY a.advanceDate DESC")
    Optional<AdvanceSalary> findLatestAdvanceSalaryByUser(@Param("userId") Long userId);


}
