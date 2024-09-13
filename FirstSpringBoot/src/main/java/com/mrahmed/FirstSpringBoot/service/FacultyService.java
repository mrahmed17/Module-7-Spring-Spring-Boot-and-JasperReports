package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Faculty;
import com.mrahmed.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;


    public  void saveFaculty(Faculty f){

         facultyRepository.save(f);
    }

    public List<Faculty>  getAllFaculty(){

        return  facultyRepository.findAll();
    }

    public  void deleteFacultyById(int id){
        facultyRepository.deleteById(id);
    }

    public  Faculty findById(int id){
        return  facultyRepository.findById(id).orElseThrow(()-> new RuntimeException("Couldn't find faculty' by this id: " + id));
    };


    public  void updateFaculty(Faculty f, Integer id){
        facultyRepository.save(f);

    }


}