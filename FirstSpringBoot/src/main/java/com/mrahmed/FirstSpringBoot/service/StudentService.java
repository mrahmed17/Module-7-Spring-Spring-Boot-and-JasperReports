package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Student;
import com.mrahmed.FirstSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

        public void saveStudent(Student s){
            studentRepository.save(s);
        }

        public List<Student> getAllStudents(){
            return studentRepository.findAll();
        }

        public void deleteById(int id){
            studentRepository.deleteById(id);
        }

        public Student findById(int id){
            return studentRepository.findById(id).get();
        }

}
