package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Attendance;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 1. Find attendance by user and date
    Optional<Attendance> findByUserIdAndDate(long userId, LocalDate date);

    // 2. Count attendance by user and date
    long countByUserIdAndDate(long userId, LocalDate date);

    // 3. Get attendances within a date range
    @Query("SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findAttendancesInRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 4. Find last attendance for a user today
    @Query("SELECT a FROM Attendance a WHERE a.user.id = :userId AND a.date = :today ORDER BY a.clockInTime DESC")
    Optional<Attendance> findLastAttendanceForUser(@Param("userId") long userId, @Param("today") LocalDate today);

    // 5. Find users who have not checked in today
    @Query("SELECT u FROM User u WHERE u.id NOT IN " +
            "(SELECT a.user.id FROM Attendance a WHERE a.date = :today)")
    List<User> findUsersWithoutAttendanceForToday(@Param("today") LocalDate today);

    // 6. Find all attendances for a user
    List<Attendance> findAllByUserId(Long id);

    // NEW FEATURES IMPLEMENTED BELOW

    // 7. Daily peak attendance (which day had the most/least attendance)
    @Query("SELECT a.date, COUNT(a) FROM Attendance a GROUP BY a.date ORDER BY COUNT(a) DESC")
    List<Object[]> findPeakAttendanceDay();

    // 8. Monthly attendance trends (which month had the most/least attendance)
    @Query("SELECT MONTH(a.date), COUNT(a) FROM Attendance a GROUP BY MONTH(a.date) ORDER BY COUNT(a) DESC")
    List<Object[]> findPeakAttendanceMonth();

    // 9. Yearly attendance trends (which year had the most/least attendance)
    @Query("SELECT YEAR(a.date), COUNT(a) FROM Attendance a GROUP BY YEAR(a.date) ORDER BY COUNT(a) DESC")
    List<Object[]> findPeakAttendanceYear();

    // 10. Department-wise attendance trends (which department had the most attendance)
//    @Query("SELECT d.name, COUNT(a) FROM Attendance a JOIN a.user u JOIN u.department d GROUP BY d.name ORDER BY COUNT(a) DESC")
//    List<Object[]> findAttendanceByDepartment();

    // 11. Overtime tracking for a user
//    @Query("SELECT a FROM Attendance a WHERE a.user.id = :userId AND a.overtime = true AND a.date BETWEEN :startDate AND :endDate")
//    List<Attendance> findOvertimeByUserIdInRange(@Param("userId") long userId,
//                                                 @Param("startDate") LocalDate startDate,
//                                                 @Param("endDate") LocalDate endDate);

    // 12. Sick leave trends (which month had the most sick leaves)
//    @Query("SELECT MONTH(a.date), COUNT(a) FROM Attendance a WHERE a.leaveType = 'SICK_PAID' GROUP BY MONTH(a.date) ORDER BY COUNT(a) DESC")
//    List<Object[]> findSickLeaveTrends();

    // 13. Employees with high leave rate (to track absenteeism)
//    @Query("SELECT u.id, COUNT(a) FROM Attendance a JOIN a.user u WHERE a.leaveType IS NOT NULL GROUP BY u.id HAVING COUNT(a) > :threshold")
//    List<User> findEmployeesWithHighLeaveRate(@Param("threshold") long threshold);

    // 14. Holiday attendance (which holidays had the least attendance)
    @Query("SELECT a.date, COUNT(a) FROM Attendance a WHERE a.date IN (:holidayDates) GROUP BY a.date ORDER BY COUNT(a) ASC")
    List<Object[]> findHolidayAttendance(@Param("holidayDates") List<LocalDate> holidayDates);

    // 15. Late check-ins (find users who are consistently late)
    @Query("SELECT a FROM Attendance a WHERE a.clockInTime > :lateTime AND a.date BETWEEN :startDate AND :endDate ORDER BY a.clockInTime DESC")
    List<Attendance> findLateCheckIns(@Param("lateTime") String lateTime,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    // 16. Shift planning based on regular attendance
    @Query("SELECT u.id, COUNT(a) FROM Attendance a JOIN a.user u WHERE a.date BETWEEN :startDate AND :endDate GROUP BY u.id ORDER BY COUNT(a) DESC")
    List<Object[]> findRegularEmployeesForShiftPlanning(@Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    // 17. Absence reasons (track reasons for employee absence)
//    @Query("SELECT a FROM Attendance a WHERE a.leaveType IS NOT NULL AND a.date BETWEEN :startDate AND :endDate")
//    List<Attendance> findAbsencesWithReasonInRange(@Param("startDate") LocalDate startDate,
//                                                   @Param("endDate") LocalDate endDate);



}
