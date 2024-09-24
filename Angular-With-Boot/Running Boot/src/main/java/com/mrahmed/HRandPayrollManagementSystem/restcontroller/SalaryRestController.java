package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.entity.Salary;
import com.mrahmed.HRandPayrollManagementSystem.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@CrossOrigin("*")
public class SalaryRestController {

    @Autowired
    private SalaryService salaryService;

    /**
     * Create a new salary record.
     *
     * @param salary Salary entity
     * @return Created Salary entity
     */
    @PostMapping("/create")
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        Salary createdSalary = salaryService.saveSalary(salary);
        return ResponseEntity.ok(createdSalary);
    }

    /**
     * Update an existing salary record.
     *
     * @param salaryId Salary ID
     * @param salary   Updated Salary entity
     * @return Updated Salary entity
     */
    @PutMapping("/update/{salaryId}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long salaryId,
                                               @RequestBody Salary salary) {
        Salary updatedSalary = salaryService.updateSalary(salaryId, salary);
        return ResponseEntity.ok(updatedSalary);
    }

    /**
     * Delete a salary record by ID.
     *
     * @param salaryId Salary ID
     * @return Response status
     */
    @DeleteMapping("/delete/{salaryId}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long salaryId) {
        salaryService.deleteSalary(salaryId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get a salary record by ID.
     *
     * @param salaryId Salary ID
     * @return Salary entity
     */
    @GetMapping("/find/{salaryId}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long salaryId) {
        Salary salary = salaryService.getSalaryById(salaryId);
        return ResponseEntity.ok(salary);
    }

    /**
     * Get all salaries for a user by year.
     *
     * @param userId User ID
     * @param year   Year
     * @return List of salaries
     */
    @GetMapping("/user/{userId}/year/{year}")
    public ResponseEntity<List<Salary>> getSalariesByUserAndYear(@PathVariable Long userId,
                                                                 @PathVariable int year) {
        List<Salary> salaries = salaryService.getSalariesByUserAndYear(userId, year);
        return ResponseEntity.ok(salaries);
    }

    /**
     * Get salaries for a user by year and month.
     *
     * @param userId User ID
     * @param year   Year
     * @param month  Payroll Month
     * @return List of salaries
     */
    @GetMapping("/user/{userId}/year/{year}/month/{month}")
    public ResponseEntity<List<Salary>> getSalariesByUserYearAndMonth(@PathVariable Long userId,
                                                                      @PathVariable int year,
                                                                      @PathVariable Month month) {
        List<Salary> salaries = salaryService.getSalariesByUserYearAndMonth(userId, year, month);
        return ResponseEntity.ok(salaries);
    }

    // Endpoint to get salaries by month and year
    @GetMapping("/month/{payrollMonth}/year/{year}")
    public List<Salary> getSalariesByMonthAndYear(@PathVariable Month payrollMonth, @PathVariable int year) {
        return salaryService.getSalariesByMonthAndYear(payrollMonth, year);
    }

    // Endpoint to get total overtime salary for a user in a date range
//    @GetMapping("/overtime/{userId}/start/{startDate}/end/{endDate}")
//    public double getTotalOvertimeSalary(@PathVariable Long userId,
//                                         @PathVariable LocalDateTime startDate,
//                                         @PathVariable LocalDateTime endDate) {
//        return salaryService.getTotalOvertimeSalary(userId, startDate, endDate);
//    }

    @GetMapping("/total-overtime-salary")
    public ResponseEntity<Double> getTotalOvertimeSalary(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        double totalOvertimeSalary = salaryService.getTotalOvertimeSalary(userId, startDate, endDate);
        return ResponseEntity.ok(totalOvertimeSalary);
    }

    /**
     * Get the latest salary record for a user.
     *
     * @param userId User ID
     * @return Latest salary record
     */
    @GetMapping("/user/{userId}/latest")
    public ResponseEntity<List<Salary>> getLatestSalaryByUser(@PathVariable Long userId) {
        List<Salary> latestSalaries = salaryService.getSalariesByUserAndYear(userId, LocalDateTime.now().getYear());
        return ResponseEntity.ok(latestSalaries);
    }

    @GetMapping("/total-overtime-hours")
    public ResponseEntity<Double> getTotalOvertimeHours(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        double totalOvertimeHours = salaryService.getTotalOvertimeHours(userId, startDate, endDate);
        return ResponseEntity.ok(totalOvertimeHours);
    }

    /**
     * Get salary records within a specific date range.
     *
     * @param startDate Start date of the range
     * @param endDate   End date of the range
     * @return List of salary records within the date range
     */
    @GetMapping("/range")
    public ResponseEntity<List<Salary>> getSalariesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Salary> salaries = salaryService.getSalariesByDateRange(startDate, endDate);
        return ResponseEntity.ok(salaries);
    }

    /**
     * Calculate overtime salary for a user within a date range.
     *
     * @param userId    User ID
     * @param startDate Start date of the range
     * @param endDate   End date of the range
     * @return Overtime salary for the given period
     */
    @GetMapping("/overtime-salary")
    public ResponseEntity<Double> calculateOvertimeSalary(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        double overtimeSalary = salaryService.calculateOvertimeSalary(userId, startDate, endDate);
        return ResponseEntity.ok(overtimeSalary);
    }

    /**
     * Get total salary payments for a user in a specific year.
     *
     * @param userId User ID
     * @param year   Year
     * @return Total salary for the user in that year
     */
    @GetMapping("/total-salary/user/{userId}/year/{year}")
    public ResponseEntity<Double> getTotalSalaryByUserAndYear(@PathVariable Long userId,
                                                              @PathVariable int year) {
        double totalSalary = salaryService.getTotalSalaryByUserAndYear(userId, year);
        return ResponseEntity.ok(totalSalary);
    }

    /**
     * Calculate total salary for a user within a date range.
     *
     * @param userId    User ID
     * @param startDate Start date of the range
     * @param endDate   End date of the range
     * @return Total salary for the given period
     */
    @GetMapping("/calculate-total-salary")
    public ResponseEntity<Double> calculateTotalSalary(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        double totalSalary = salaryService.calculateTotalSalary(userId, startDate, endDate);
        return ResponseEntity.ok(totalSalary);
    }


}
