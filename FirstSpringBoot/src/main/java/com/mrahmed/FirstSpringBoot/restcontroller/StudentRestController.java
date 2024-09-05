package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student/api")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
    }

    @PutMapping("/update/")
    public void updateStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

//    @PutMapping("/update/{id}")
//    public void updateStudent(@RequestBody Student student, @PathVariable int id) {
//        studentService.updateStudent(student, id);
//    }




}
