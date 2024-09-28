package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Bonus;
import com.mrahmed.HRandPayrollManagementSystem.entity.Month;
import com.mrahmed.HRandPayrollManagementSystem.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bonuses")
@CrossOrigin("*")
public class BonusRestController {

    @Autowired
    private BonusService bonusService;

    // Create a new Bonus
    @PostMapping
    public ResponseEntity<Bonus> createBonus(@RequestBody Bonus bonus) {
        Bonus createdBonus = bonusService.createBonus(bonus);
        return new ResponseEntity<>(createdBonus, HttpStatus.CREATED);
    }

    // Get a Bonus by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bonus> getBonusById(@PathVariable Long id) {
        return bonusService.getBonusById(id)
                .map(bonus -> new ResponseEntity<>(bonus, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing Bonus
    @PutMapping("/{id}")
    public ResponseEntity<Bonus> updateBonus(@PathVariable Long id, @RequestBody Bonus updatedBonus) {
        Bonus bonus = bonusService.updateBonus(id, updatedBonus);
        return bonus != null ? new ResponseEntity<>(bonus, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a Bonus by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable Long id) {
        bonusService.deleteBonus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Calculate bonus for a user
    @GetMapping("/calculate/{userId}")
    public ResponseEntity<Double> calculateBonus(@PathVariable Long userId) {
        double bonusAmount = bonusService.calculateBonus(userId);
        return new ResponseEntity<>(bonusAmount, HttpStatus.OK);
    }

    // Find bonuses by user's email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Bonus>> findBonusesByEmail(@PathVariable String email) {
        List<Bonus> bonuses = bonusService.findBonusesByEmail(email);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find bonuses by user's full name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Bonus>> findBonusesByName(@PathVariable String name) {
        List<Bonus> bonuses = bonusService.findBonusesByName(name);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find bonuses by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bonus>> findBonusesByUserId(@PathVariable Long userId) {
        List<Bonus> bonuses = bonusService.findBonusesByUserId(userId);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find bonuses by specific month
    @GetMapping("/month/{month}")
    public ResponseEntity<List<Bonus>> findBonusesByMonth(@PathVariable Month month) {
        List<Bonus> bonuses = bonusService.findBonusesByMonth(month);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find bonuses by specific year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Bonus>> findBonusesByYear(@PathVariable int year) {
        List<Bonus> bonuses = bonusService.findBonusesByYear(year);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find bonuses within a specific date range
    @GetMapping("/dateRange")
    public ResponseEntity<List<Bonus>> findBonusesByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<Bonus> bonuses = bonusService.findBonusesByDateRange(startDate, endDate);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Find the latest bonus for a user
    @GetMapping("/latest/{userId}")
    public ResponseEntity<List<Bonus>> findLatestBonusByUserId(@PathVariable Long userId) {
        List<Bonus> bonuses = bonusService.findLatestBonusByUserId(userId);
        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }

    // Get users who received bonuses in a specific year
    @GetMapping("/users/receivedBonusInYear/{year}")
    public ResponseEntity<List<Long>> getUsersWhoReceivedBonusInYear(@PathVariable int year) {
        List<Long> userIds = bonusService.getUsersWhoReceivedBonusInYear(year);
        return new ResponseEntity<>(userIds, HttpStatus.OK);
    }

    // Count bonuses for a specific user in a given year
    @GetMapping("/count/user/{userId}/year/{year}")
    public ResponseEntity<Integer> countBonusesForUserInYear(@PathVariable Long userId, @PathVariable int year) {
        int count = bonusService.countBonusesForUserInYear(userId, year);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    // Calculate total bonuses for a user by name
    @GetMapping("/byName/{userId}")
    public ResponseEntity<Double> getTotalBonusByName(@PathVariable Long userId) {
        double totalBonus = bonusService.getTotalBonusByName(userId);
        return new ResponseEntity<>(totalBonus, HttpStatus.OK);
    }

    // Calculate total bonuses for a specific user by user ID
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Double> getTotalBonusByUserId(@PathVariable Long userId) {
        double totalBonus = bonusService.getTotalBonusByUserId(userId);
        return new ResponseEntity<>(totalBonus, HttpStatus.OK);
    }

    // Calculate total bonuses for a specific month
    @GetMapping("/byMonth/{month}")
    public ResponseEntity<Double> getTotalBonusByMonth(@PathVariable Month month) {
        double totalBonus = bonusService.getTotalBonusByMonth(month);
        return new ResponseEntity<>(totalBonus, HttpStatus.OK);
    }

    // Calculate total bonuses for a specific year
    @GetMapping("/byYear/{year}")
    public ResponseEntity<Double> getTotalBonusByYear(@PathVariable int year) {
        double totalBonus = bonusService.getTotalBonusByYear(year);
        return new ResponseEntity<>(totalBonus, HttpStatus.OK);
    }

}
