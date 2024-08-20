package com.mrahmed.FirstSpringBoot.controller;

import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/savestudentform")
    public String saveStudentFom(Model m) {
        m.addAttribute("title", "Add New Student");
        m.addAttribute("student", new Student());
        return "savestudentform";
    }

    @RequestMapping(value = "/saveStudent", method= RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student) {
       studentService.saveStudent(student);
       return "redirect:/studentlist";
    }

    @RequestMapping("/studentlist")
    public String studentlist(Model m) {
        m.addAttribute("title", "Students List");

        List<Student> studentList = studentService.getAllStudents();
        m.addAttribute("studentList", studentList);
        return "studentlist";
    }

    @RequestMapping(value = "/deleteStudent/{id}")
    public String deleteStudent(@PathVariable ("id") Integer id) {

        studentService.deleteById(id);

        return "redirect:/studentlist";
    }

    @RequestMapping("/editstudentform/{id}")
    public String  editStudent(@PathVariable ("id") Integer id, Model m) {
        m.addAttribute("title", "Edit Student");
        Student student =studentService.findById(id);
        m.addAttribute("student", student);
        return "studentform";
    }

}
