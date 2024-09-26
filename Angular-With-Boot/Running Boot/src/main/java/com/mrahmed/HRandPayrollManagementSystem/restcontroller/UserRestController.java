package com.mrahmed.HRandPayrollManagementSystem.restcontroller;


import com.mrahmed.HRandPayrollManagementSystem.entity.Role;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import com.mrahmed.HRandPayrollManagementSystem.repository.UserRepository;
import com.mrahmed.HRandPayrollManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(
            @RequestPart("user") User user,
            @RequestPart(value = "profilePhoto", required = false) MultipartFile profilePhoto
    ) {
        try {
            userService.saveUser(user, profilePhoto);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload profile photo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestPart("user") User user,
            @RequestPart(value = "profilePhoto", required = false) MultipartFile profilePhoto
    ) {
        try {
            userService.updateUser(id, user, profilePhoto);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUser(
//            @PathVariable Long id,
//            @RequestPart("user") User user,
//            @RequestPart(value = "profilePhoto", required = false) MultipartFile profilePhoto
//    ) {
//        try {
//            userService.updateUser(id, user, profilePhoto);
//            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/salary/greaterThanOrEqual/{salary}")
    public ResponseEntity<List<User>> getUsersWithSalaryGreaterThanOrEqual(@PathVariable double salary) {
        List<User> users = userService.getUsersWithSalaryGreaterThanOrEqual(salary);
        return ResponseEntity.ok(users);
    }

    // Get users with salary less than or equal to a value
    @GetMapping("/salary/lessThanOrEqual/{salary}")
    public ResponseEntity<List<User>> getUsersWithSalaryLessThanOrEqual(@PathVariable double salary) {
        List<User> users = userService.getUsersWithSalaryLessThanOrEqual(salary);
        return ResponseEntity.ok(users);
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());
            List<User> users = userService.getUsersByRole(userRole);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<User>> getUsersByFullNamePart(@PathVariable String name) {
        List<User> users = userService.getUsersByFullNamePart(name);
        return ResponseEntity.ok(users);
    }

    // Get users by gender
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<User>> getUsersByGender(@PathVariable String gender) {
        List<User> users = userService.getUsersByGender(gender);
        return ResponseEntity.ok(users);
    }

    // Get users by joined date
    @GetMapping("/joinedDate/{joinedDate}")
    public ResponseEntity<List<User>> getUsersByJoinedDate(@PathVariable LocalDate joinedDate) {
        List<User> users = userService.getUsersByJoinedDate(joinedDate);
        return ResponseEntity.ok(users);
    }



}

