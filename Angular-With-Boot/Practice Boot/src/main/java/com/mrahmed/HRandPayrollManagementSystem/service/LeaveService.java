package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.Attendance;
import com.mrahmed.HRandPayrollManagementSystem.entity.Leave;
import com.mrahmed.HRandPayrollManagementSystem.entity.LeaveType;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import com.mrahmed.HRandPayrollManagementSystem.repository.AttendanceRepository;
import com.mrahmed.HRandPayrollManagementSystem.repository.LeaveRepository;
import com.mrahmed.HRandPayrollManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Calculate the number of days for a leave period
    private int calculateLeaveDays(Leave leave) {
        return (int) ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
    }

    // Check if a leave request overlaps with existing attendance
    public boolean isLeaveOverlappingWithAttendance(Leave leave, User user) {
        List<Attendance> attendances = attendanceRepository.findAllByUserId(user.getId());
        return attendances.stream().anyMatch(attendance ->
                !attendance.getDate().isBefore(leave.getStartDate()) &&
                        !attendance.getDate().isAfter(leave.getEndDate()));
    }

    // Update the remaining leave for a leave record
    private void updateRemainingLeave(Leave leave) {
        int leaveDaysTaken = calculateLeaveDays(leave);
        int totalLeave = getTotalLeaveForType(leave.getLeaveType());
        int remainingLeave = totalLeave - leaveDaysTaken;
        leave.setRemainingLeave(remainingLeave);
        leaveRepository.save(leave);
    }

    // Get the total leave days allowed based on leave type
    private int getTotalLeaveForType(LeaveType leaveType) {
        return switch (leaveType) {
            case SICK_PAID -> 15; // 15 days of paid sick leave
            case SICK_UNPAID -> 0; // 0 days of unpaid sick leave
            case RESERVE_UNPAID -> 10; // 10 days of reserve unpaid leave
            default -> 0;
        };
    }


    // Process and save a leave request for a user
    public void processLeave(long userId, Leave leave) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (isLeaveOverlappingWithAttendance(leave, user)) {
            throw new RuntimeException("Leave request overlaps with existing attendance");
        }

        if (leave.isApproved()) {
            updateRemainingLeave(leave); // Update remaining leave for the leave record
        }

        leaveRepository.save(leave);
    }

    // Get all leaves for a user
    public List<Leave> getLeavesByUserId(long userId) {
        return leaveRepository.findByUserId(userId);
    }

    // Get all approved leaves for a user
    public List<Leave> getApprovedLeavesByUserId(long userId) {
        return leaveRepository.findApprovedLeavesByUserId(userId);
    }

    // Get all leaves in a date range
    public List<Leave> getLeavesInRange(LocalDate startDate, LocalDate endDate) {
        return leaveRepository.findByStartDateBetween(startDate, endDate);
    }

    // Calculate total approved leave days for a user
    public int calculateTotalLeaveDaysForUser(long userId) {
        return getApprovedLeavesByUserId(userId).stream()
                .mapToInt(this::calculateLeaveDays)
                .sum();
    }

    // Update leave approval status
    public Leave approveLeave(long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setApproved(true);
        leaveRepository.save(leave);

        updateRemainingLeave(leave); // Update remaining leave for the leave record if it is approved

        return leave;
    }
}