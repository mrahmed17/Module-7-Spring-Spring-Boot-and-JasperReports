package com.mrahmed.FirstSpringBoot.restcontroller;


import com.mrahmed.FirstSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserRestController {

    @Autowired
    private UserService userService;

}
