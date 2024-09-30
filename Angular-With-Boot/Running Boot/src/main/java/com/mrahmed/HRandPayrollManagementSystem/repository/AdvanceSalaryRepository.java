package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.AdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdvanceSalaryRepository extends JpaRepository<AdvanceSalary, Long> {

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.email = :userEmail")
    Optional<AdvanceSalary> findAdvanceSalariesByEmail(@Param("userEmail") String userEmail);

    @Query("SELECT a FROM AdvanceSalary a WHERE LOWER(a.user.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<AdvanceSalary> findAdvanceSalariesByName(@Param("name") String name);

    @Query("SELECT SUM(a.advanceSalary) FROM AdvanceSalary a WHERE a.user.id = :userId AND LOWER(a.user.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    double getTotalAdvanceSalaryByName(@Param("userId") Long userId);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.id = :userId")
    List<AdvanceSalary> findAdvanceSalariesByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(a.advanceSalary) FROM AdvanceSalary a WHERE a.user.id = :userId")
    double getTotalAdvanceSalaryByUserId(@Param("userId") Long userId);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.advanceDate BETWEEN :startDate AND :endDate")
    List<AdvanceSalary> findAdvanceSalariesByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT a FROM AdvanceSalary a WHERE a.user.id = :userId ORDER BY a.advanceDate DESC")
    List<AdvanceSalary> findLatestAdvanceSalaryByUserId(@Param("userId") Long userId);

}
