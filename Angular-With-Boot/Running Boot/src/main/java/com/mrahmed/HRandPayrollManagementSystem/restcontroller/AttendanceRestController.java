package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Attendance;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import com.mrahmed.HRandPayrollManagementSystem.service.AttendanceService;
import com.mrahmed.HRandPayrollManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceRestController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;


    @PostMapping("/checkin")
    public ResponseEntity<String> checkIn(@RequestBody Map<String, Object> request) {
        try {
            long userId = Long.parseLong(request.get("userId").toString());
            attendanceService.checkIn(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Check-in successful for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while checking in");
        }
    }

    @PutMapping("/checkout")
    public ResponseEntity<String> checkOut(@RequestBody Map<String, Object> request) {
        try {
            long userId = Long.parseLong(request.get("userId").toString());
            attendanceService.checkOut(userId);
            return ResponseEntity.ok("Check-out successful for user ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while checking out");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Attendance>> getAllAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        return attendances.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(attendances);
    }

    @GetMapping("/overtime/{userId}")
    public ResponseEntity<List<Attendance>> getOvertimeByUser(
            @PathVariable Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> overtimeRecords = attendanceService.getOvertimeForUser(userId, startDate, endDate);
        if (overtimeRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(overtimeRecords);
    }


    // New endpoint for fetching overtime in a date range
    @GetMapping("/overtime")
    public List<Attendance> getOvertimeInRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return attendanceService.getOvertimeInRange(start, end);
    }


    @GetMapping("/today")
    public ResponseEntity<List<Attendance>> getTodayAttendance() {
        List<Attendance> attendances = attendanceService.getTodayAttendances();
        return attendances.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(attendances);
    }


    // New endpoint to get users without attendance today
    @GetMapping("/absent-today")
    public ResponseEntity<List<User>> getUsersWithoutAttendanceToday() {
        List<User> absentUsers = attendanceService.findUsersWithoutAttendanceToday();
        return absentUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(absentUsers);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Attendance> findAttendanceById(@PathVariable("id") long id) {
        Attendance attendance = attendanceService.findAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

//    @GetMapping("/user/{id}/attendances")
//    public ResponseEntity<List<Attendance>> getAttendancesByUserId(@PathVariable Long id) {
//        List<Attendance> attendances = attendanceService.getAttendanceByUserId(id);
//        return ResponseEntity.ok(attendances);
//    }

    @GetMapping("/attendanceRange")
    public ResponseEntity<Map<User, Long>> getUsersWithAttendanceInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<User, Long> userAttendance = attendanceService.getUsersAttendanceInRange(startDate, endDate);
        return userAttendance.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(userAttendance);
    }

    @GetMapping("/isWithinMorningShift/{userId}")
    public ResponseEntity<Boolean> isWithinMorningShift(@PathVariable Long userId) {
        boolean isMorningShift = attendanceService.isWithinMorningShift(userId);
        return ResponseEntity.ok(isMorningShift);
    }

    @GetMapping("/isWithinDayShift/{userId}")
    public ResponseEntity<Boolean> isWithinDayShift(@PathVariable Long userId) {
        boolean isDayShift = attendanceService.isWithinDayShift(userId);
        return ResponseEntity.ok(isDayShift);
    }


    @GetMapping("/isLate/{userId}")
    public ResponseEntity<Boolean> isLate(@PathVariable Long userId) {
        boolean isLate = attendanceService.isLate(userId);
        return ResponseEntity.ok(isLate);
    }


    @GetMapping("/peakAttendanceDay")
    public ResponseEntity<List<Object[]>> getPeakAttendanceDay() {
        List<Object[]> peakAttendanceDay = attendanceService.getPeakAttendanceDay();
        return peakAttendanceDay.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(peakAttendanceDay);
    }

    @GetMapping("/peakAttendanceMonth")
    public ResponseEntity<List<Object[]>> getPeakAttendanceMonth() {
        List<Object[]> peakAttendanceMonth = attendanceService.getPeakAttendanceMonth();
        return peakAttendanceMonth.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(peakAttendanceMonth);
    }

    @GetMapping("/peakAttendanceYear")
    public ResponseEntity<List<Object[]>> getPeakAttendanceYear() {
        List<Object[]> peakAttendanceYear = attendanceService.getPeakAttendanceYear();
        return peakAttendanceYear.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(peakAttendanceYear);
    }

    @PostMapping("/holidayAttendance")
    public ResponseEntity<List<Object[]>> getHolidayAttendance(@RequestBody List<LocalDate> holidayDates) {
        List<Object[]> holidayAttendance = attendanceService.getHolidayAttendance(holidayDates);
        return holidayAttendance.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(holidayAttendance);
    }

    @GetMapping("/lateCheckIns")
    public ResponseEntity<List<Attendance>> getLateCheckIns(
            @RequestParam("lateTime") LocalTime lateTime,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> lateCheckIns = attendanceService.getLateCheckIns(lateTime, startDate, endDate);
        return lateCheckIns.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lateCheckIns);
    }


    @GetMapping("/shiftPlanning")
    public ResponseEntity<List<Object[]>> getRegularEmployeesForShiftPlanning(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Object[]> regularEmployees = attendanceService.getRegularEmployeesForShiftPlanning(startDate, endDate);
        return regularEmployees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(regularEmployees);
    }


    @GetMapping("/searchByName")
    public ResponseEntity<List<Attendance>> getAttendancesByUserNamePart(@RequestParam("name") String name) {
        List<Attendance> attendances = attendanceService.getAttendancesByUserNamePart(name);
        return attendances.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(attendances);
    }


    @GetMapping("/roleAttendance")
    public ResponseEntity<List<Attendance>> getAttendanceByRoleAndDateRange(
            @RequestParam("role") String role,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> attendances = attendanceService.getAttendanceByRoleAndDateRange(role, startDate, endDate);
        return attendances.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(attendances);
    }


    @GetMapping("/todayAttendance/{userId}")
    public ResponseEntity<List<Attendance>> getTodayAttendanceByUserId(@PathVariable("userId") long userId) {
        List<Attendance> todayAttendance = attendanceService.getTodayAttendanceByUserId(userId);
        return todayAttendance.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(todayAttendance);
    }




}
