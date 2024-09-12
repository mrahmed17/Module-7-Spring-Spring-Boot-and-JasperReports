package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Attendance;
import com.mrahmed.HRandPayrollManagementSystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private  AttendanceService attendanceService;

    @GetMapping("/add")
    public ResponseEntity<String> addAttendance(@RequestBody Attendance attendance){
        attendanceService.saveAttendance(attendance);
        return ResponseEntity.ok("Attendance added successfully");
    }

    @GetMapping("/all")
    public ResponseEntity <List<Attendance>> getAllAttendances(){
        List<Attendance> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable("id") long id) {
        Attendance attendance = attendanceService.findAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Attendance>> getAttendancesByUserId(@PathVariable("id") long id){
        List<Attendance> attendances = attendanceService.getAttendancesByUserId(id);
        return ResponseEntity.ok(attendances);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable("id") long id){
        attendanceService.deleteAttendanceById(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }





}
