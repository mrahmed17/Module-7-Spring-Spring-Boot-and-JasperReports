package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentRestController {


    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudent() {
        return studentService.getAllStu();
    }

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student s) {
        studentService.saveStu(s);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteStudent(@PathVariable int id){
        studentService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public  void updateStudentById(@RequestBody Student s, @PathVariable int id){
        studentService.updateStudent(s, id);
    }

    @PutMapping("/update")
    public  void updateStudent(@RequestBody Student s){
        studentService.saveStu(s);
    }



}