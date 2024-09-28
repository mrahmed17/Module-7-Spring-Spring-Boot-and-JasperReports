package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.AdvanceSalary;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.service.AdvanceSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/advance-salary")
@CrossOrigin("*")
public class AdvanceSalaryRestController {


    @Autowired
    private AdvanceSalaryService advanceSalaryService;

    // Create a new advance salary
    @PostMapping
    public ResponseEntity<AdvanceSalary> createAdvanceSalary(@RequestBody AdvanceSalary advanceSalary) {
        try {
            AdvanceSalary createdAdvanceSalary = advanceSalaryService.createAdvanceSalary(advanceSalary);
            return new ResponseEntity<>(createdAdvanceSalary, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get advance salary by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdvanceSalary> getAdvanceSalaryById(@PathVariable Long id) {
        Optional<AdvanceSalary> advanceSalary = advanceSalaryService.getAdvanceSalaryById(id);
        return advanceSalary
                .map(salary -> new ResponseEntity<>(salary, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all advance salaries
    @GetMapping
    public ResponseEntity<List<AdvanceSalary>> getAllAdvanceSalaries() {
        List<AdvanceSalary> salaries = advanceSalaryService.getAllAdvanceSalaries();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Update an existing advance salary
    @PutMapping("/{id}")
    public ResponseEntity<AdvanceSalary> updateAdvanceSalary(@PathVariable Long id, @RequestBody AdvanceSalary advanceSalary) {
        try {
            advanceSalary.setId(id);
            AdvanceSalary updatedSalary = advanceSalaryService.updateAdvanceSalary(advanceSalary);
            return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an advance salary by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvanceSalary(@PathVariable Long id) {
        try {
            advanceSalaryService.deleteAdvanceSalary(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get advance salary by user email
    @GetMapping("/user-email/{email}")
    public ResponseEntity<AdvanceSalary> getAdvanceSalaryByEmail(@PathVariable String email) {
        Optional<AdvanceSalary> advanceSalary = advanceSalaryService.getAdvanceSalariesByEmail(email);
        return advanceSalary
                .map(salary -> new ResponseEntity<>(salary, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get advance salary by user name
    @GetMapping("/user-name/{name}")
    public ResponseEntity<List<AdvanceSalary>> getAdvanceSalariesByName(@PathVariable String name) {
        List<AdvanceSalary> salaries = advanceSalaryService.getAdvanceSalariesByName(name);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Calculate total advance salary by user ID and (optional) name
    @GetMapping("/user-total-by-name/{userId}")
    public ResponseEntity<Double> getTotalAdvanceSalaryByName(@PathVariable Long userId) {
        try {
            double totalSalary = advanceSalaryService.getTotalAdvanceSalaryByName(userId);
            return new ResponseEntity<>(totalSalary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Retrieve advance salaries by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AdvanceSalary>> getAdvanceSalariesByUserId(@PathVariable Long userId) {
        List<AdvanceSalary> salaries = advanceSalaryService.getAdvanceSalariesByUserId(userId);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }


    // Get advance salary total by user ID
    @GetMapping("/user-total/{userId}")
    public ResponseEntity<Double> getTotalAdvanceSalaryByUserId(@PathVariable Long userId) {
        double totalSalary = advanceSalaryService.getTotalAdvanceSalaryByUserId(userId);
        return new ResponseEntity<>(totalSalary, HttpStatus.OK);
    }

    // Get advance salary by month
    @GetMapping("/month/{month}")
    public ResponseEntity<List<AdvanceSalary>> getAdvanceSalariesByMonth(@PathVariable Month month) {
        List<AdvanceSalary> salaries = advanceSalaryService.getAdvanceSalariesByMonth(month);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Calculate total advance salary for a specific month
    @GetMapping("/total-by-month/{month}")
    public ResponseEntity<Double> getTotalAdvanceSalaryByMonth(@PathVariable Month month) {
        try {
            double totalSalary = advanceSalaryService.getTotalAdvanceSalaryByMonth(month);
            return new ResponseEntity<>(totalSalary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get advance salary by year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<AdvanceSalary>> getAdvanceSalariesByYear(@PathVariable int year) {
        List<AdvanceSalary> salaries = advanceSalaryService.getAdvanceSalariesByYear(year);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Calculate total advance salary for a specific year
    @GetMapping("/total-by-year/{year}")
    public ResponseEntity<Double> getTotalAdvanceSalaryByYear(@PathVariable int year) {
        try {
            double totalSalary = advanceSalaryService.getTotalAdvanceSalaryByYear(year);
            return new ResponseEntity<>(totalSalary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get advance salaries by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<AdvanceSalary>> getAdvanceSalariesByDateRange(@RequestParam LocalDateTime startDate,
                                                                             @RequestParam LocalDateTime endDate) {
        List<AdvanceSalary> salaries = advanceSalaryService.getAdvanceSalariesByDateRange(startDate, endDate);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    // Get the latest advance salary for a user
    @GetMapping("/latest/{userId}")
    public ResponseEntity<AdvanceSalary> getLatestAdvanceSalaryByUserId(@PathVariable Long userId) {
        Optional<AdvanceSalary> advanceSalary = advanceSalaryService.getLatestAdvanceSalaryByUserId(userId);
        return advanceSalary
                .map(salary -> new ResponseEntity<>(salary, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to get distinct users who received advance salary in a specific year
    @GetMapping("/users/year/{year}")
    public ResponseEntity<List<Long>> getUsersWhoReceivedAdvanceSalaryInYear(@PathVariable int year) {
        List<Long> userIds = advanceSalaryService.getUsersWhoReceivedAdvanceSalaryInYear(year);
        return ResponseEntity.ok(userIds);
    }

    // Endpoint to count the number of advance salaries for a specific user in a given year
    @GetMapping("/count/user/{userId}/year/{year}")
    public ResponseEntity<Integer> countAdvanceSalariesForUserInYear(@PathVariable Long userId, @PathVariable int year) {
        int count = advanceSalaryService.countAdvanceSalariesForUserInYear(userId, year);
        return ResponseEntity.ok(count);
    }




}