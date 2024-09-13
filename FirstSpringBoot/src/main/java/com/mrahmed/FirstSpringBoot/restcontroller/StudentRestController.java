package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentRestController {


//    @Autowired
//    private StudentService studentService;
//
//    @GetMapping("/")
//    public List<Student> getAllStudent() {
//        return studentService.getAllStu();
//    }
//
//    @PostMapping("/save")
//    public void saveStudent(@RequestBody Student s) {
//        studentService.saveStu(s);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public  void deleteStudent(@PathVariable int id){
//        studentService.deleteById(id);
//    }
//
//
//    //2 Way Updates System by updateStudent method
//
//    @PutMapping("/update/{id}")
//    public  void updateStudentById(@RequestBody Student s,@PathVariable("id") int id){
//        studentService.updateStudent(s,id);  //This method is good for angular
//    }
//
//    //2 Way Updates System by saveStudent method
//
//    @PutMapping("/update")
//    public  void updateStudent(@RequestBody Student s){    //This method is bad for angular
//        studentService.saveStu(s);
//    }


    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student>students=studentService.getAllStu();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
//
//    @PostMapping("save")
//    public void saveStudent(@RequestBody Student s) {
//        studentService.saveStu(s);
//    }

    @PostMapping("save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student s) {
        studentService.saveStu(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    //    @DeleteMapping("delete/{id}")
//    public void deleteStudent(@PathVariable int id) {
//        studentService.deleteById(id);
//    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //    @PutMapping("update/{id}")
//    public void updateStudent(@PathVariable int id, @RequestBody Student s) {
//        studentService.updateStu(s, id);
//    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") int id, @RequestBody Student s) {
        studentService.updateStudent(s, id);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }


}