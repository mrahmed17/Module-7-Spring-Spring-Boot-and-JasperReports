package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.User;
import com.mrahmed.FirstSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void saveUser(User user) throws MessagingException {

        String toMail = user.getEmail();
        String subject = "Registration Confirmation";
        String body = String.format("Thanks %, \n Stay with us and your \n Username is %s.", user.getName(),
                user.getEmail());

        emailService.sendSimpleEmail(toEmail, subject, body);

        userRepository.save(user);
    }

    puublic List

    <User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
//    @PostMapping("/user")
//    public  void saveDep(@RequestBody User user) throws MessagingException{
//        userService.saveUser(user);
//    }



