package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {


    // Find leaves for a specific user
    @Query("SELECT l FROM Leave l WHERE l.user.id = :userId")
    List<Leave> findByUserId(@Param("userId") Long userId);

    // Find approved leaves for a specific user
    @Query("SELECT l FROM Leave l WHERE l.user.id = :userId AND l.isApproved = true")
    List<Leave> findApprovedLeavesByUserId(@Param("userId") Long userId);

    // Find leaves within a specific date range
    @Query("SELECT l FROM Leave l WHERE l.startDate BETWEEN :startDate AND :endDate OR l.endDate BETWEEN :startDate AND :endDate OR (l.startDate <= :startDate AND l.endDate >= :endDate)")
    List<Leave> findLeavesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find overlapping leaves with a specific date range
    @Query("SELECT l FROM Leave l WHERE (l.startDate <= :endDate AND l.endDate >= :startDate)")
    List<Leave> findOverlappingLeaves(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find leaves within a specific date range
    @Query("SELECT l FROM Leave l WHERE l.startDate BETWEEN :startDate AND :endDate OR l.endDate BETWEEN :startDate AND :endDate")
    List<Leave> findByStartDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

