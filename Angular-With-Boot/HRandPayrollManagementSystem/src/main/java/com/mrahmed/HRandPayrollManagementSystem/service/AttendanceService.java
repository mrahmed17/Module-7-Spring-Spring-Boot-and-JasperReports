package com.mrahmed.HRandPayrollManagementSystem.service;

import com.mrahmed.HRandPayrollManagementSystem.entity.Attendance;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import com.mrahmed.HRandPayrollManagementSystem.repository.AttendanceRepository;
import com.mrahmed.HRandPayrollManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    public  void saveAttendance(Attendance attend) {
       attendanceRepository.save(attend);
    }

    public  List<Attendance>  getAllAttendances(){
        return attendanceRepository.findAll();
    }

    public  void deleteAttendanceById(long id){
        attendanceRepository.deleteById(id);
    }

    public  Attendance findAttendanceById(long id){
        return  attendanceRepository.findById(id).orElseThrow(()-> new RuntimeException("Attendance not found with id: " + id));
    }

    public List<Attendance> getAttendancesByUserId(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return attendanceRepository.findByUser(user);
    }


//    public void updateAttendance(Attendance updatedAttendance, long id) {
//        Attendance existingAttendance = findAttendanceById(id);
//
//        existingAttendance.setDate(updatedAttendance.getDate());
//        existingAttendance.setClockInTime(updatedAttendance.getClockInTime());
//        existingAttendance.setClockOutTime(updatedAttendance.getClockOutTime());
//        existingAttendance.setUser(updatedAttendance.getUser());
//
//        attendanceRepository.save(existingAttendance);
//    }

// For Angular, you can use the following method instead of the above one
//    public  void updateAttendance(Attendance attendance, long id){
//        attendanceRepository.save(attendance);
//
//    }

}
