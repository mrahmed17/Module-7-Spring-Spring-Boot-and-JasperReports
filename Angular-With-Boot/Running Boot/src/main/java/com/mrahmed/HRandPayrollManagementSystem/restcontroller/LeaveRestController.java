package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Leave;
import com.mrahmed.HRandPayrollManagementSystem.entity.LeaveType;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin("*")
public class LeaveRestController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/save")
    public ResponseEntity<Leave> saveLeaveRequest(@RequestBody Leave leave) {
        Leave savedLeave = leaveService.saveLeaveRequest(leave);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLeave);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Leave> updateLeaveRequest(@PathVariable("id") Long id, @RequestBody Leave leave) {
        Leave updatedLeave = leaveService.updateLeaveRequest(id, leave);
        return ResponseEntity.ok(updatedLeave);
    }

    @DeleteMapping("/delete/{leaveId}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long leaveId) {
        leaveService.deleteLeave(leaveId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{leaveId}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable Long leaveId) {
        Optional<Leave> leave = leaveService.getLeaveById(leaveId);
        return leave.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/approve/{leaveId}")
    public ResponseEntity<Leave> approveLeaveRequest(@PathVariable Long leaveId) {
        Leave approvedLeave = leaveService.approveLeaveRequest(leaveId);
        return ResponseEntity.ok(approvedLeave);
    }

    @PostMapping("/reject/{leaveId}")
    public ResponseEntity<Leave> rejectLeaveRequest(@PathVariable Long leaveId) {
        Leave rejectedLeave = leaveService.rejectLeaveRequest(leaveId);
        return ResponseEntity.ok(rejectedLeave);
    }

    @GetMapping("/user/{userId}/year/{year}")
    public ResponseEntity<List<Leave>> getLeavesByUserAndYear(@PathVariable Long userId, @PathVariable int year) {
        List<Leave> leaves = leaveService.getLeavesByUserAndYear(userId, year);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaveRequests() {
        List<Leave> pendingLeaves = leaveService.getPendingLeaveRequests();
        return ResponseEntity.ok(pendingLeaves);
    }

    @GetMapping("/month/{month}/year/{year}")
    public ResponseEntity<List<Leave>> getLeavesByMonthAndYear(@PathVariable String month, @PathVariable int year) {
        Month monthEnum = Month.valueOf(month.toUpperCase());
        List<Leave> leaves = leaveService.getLeavesByMonthAndYear(monthEnum, year);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/type/{leaveType}")
    public ResponseEntity<List<Leave>> getLeavesByType(@PathVariable String leaveType) {
        LeaveType leaveTypeEnum = LeaveType.valueOf(leaveType.toUpperCase());
        List<Leave> leaves = leaveService.getLeavesByType(leaveTypeEnum);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/user/{userId}/approved")
    public ResponseEntity<List<Leave>> getApprovedLeavesByUser(@PathVariable Long userId) {
        List<Leave> approvedLeaves = leaveService.getApprovedLeavesByUser(userId);
        return ResponseEntity.ok(approvedLeaves);
    }

    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<Leave>> getLeavesByUserAndDateRange(
            @PathVariable Long userId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        List<Leave> leaves = leaveService.getLeavesByUserAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/user/{userId}/remaining/year/{year}")
    public ResponseEntity<Integer> calculateRemainingLeavesByUserAndYear(
            @PathVariable Long userId, @PathVariable int year) {
        Integer remainingLeaves = leaveService.calculateRemainingLeavesByUserAndYear(userId, year);
        return ResponseEntity.ok(remainingLeaves);
    }

    @GetMapping("/user/{userId}/unpaid/year/{year}")
    public ResponseEntity<Integer> getTotalUnpaidLeaveDays(
            @PathVariable Long userId,
            @RequestParam("leaveTypes") List<String> leaveTypes,
            @PathVariable int year) {
        List<LeaveType> leaveTypeList = leaveTypes.stream()
                .map(String::toUpperCase)
                .map(LeaveType::valueOf)
                .collect(Collectors.toList());
        int totalUnpaidDays = leaveService.getTotalUnpaidLeaveDays(userId, leaveTypeList, year);
        return ResponseEntity.ok(totalUnpaidDays);
    }

    @GetMapping("/user/{userId}/current")
    public ResponseEntity<List<Leave>> getCurrentYearLeaves(@PathVariable Long userId) {
        List<Leave> leaves = leaveService.getCurrentYearLeaves(userId);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/reason")
    public ResponseEntity<List<Leave>> getLeavesByReason(@RequestParam("reason") String reason) {
        List<Leave> leaves = leaveService.getLeavesByReason(reason);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Leave>> getLeavesByDateRange(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        List<Leave> leaves = leaveService.getLeavesByDateRange(startDate, endDate);
        return ResponseEntity.ok(leaves);
    }

}

