package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.repository.DepartmentRepository;
import com.mrahmed.FirstSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class StudentService{

    @Autowired
    private  StudentRepository studentRepository;
  @Autowired
    private DepartmentRepository departmentRepository;

    public  void saveStu(Student s){

        Department d=departmentRepository.findById(s.getDepartment().getId())
                        .orElseThrow(
                                ()-> new RuntimeException("Student Not Saved ")
                        );
        s.setDepartment(d);
        studentRepository.save(s);
    }

    public  List<Student>  getAllStu(){

         return studentRepository.findAll();
    }

    public  void deleteById(int id){
        studentRepository.deleteById(id);
    }

    public  Student findById(int id){

        return  studentRepository.findById(id).get();
    }

    public  void updateStudent(Student s, int id){
        studentRepository.save(s);

    }

}