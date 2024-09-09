package com.mrahmed.FirstSpringBoot.restcontroller;


import com.mrahmed.FirstSpringBoot.entity.User;
import com.mrahmed.FirstSpringBoot.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserRestController {


    @Autowired
    private UserService userService;


//    @PostMapping("/save")
//    public  void saveDep(@RequestBody User u) throws MessagingException {
//        userService.saveUser(u);
//    }


    @PostMapping("/save")
    public ResponseEntity<String> addUser(
            @RequestPart("user") User user,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            userService.saveUser(user, imageFile);
            return new ResponseEntity<>("User added successfully with image.", HttpStatus.OK);
        } catch (MessagingException | IOException e) {
            return new ResponseEntity<>("Failed to add user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}