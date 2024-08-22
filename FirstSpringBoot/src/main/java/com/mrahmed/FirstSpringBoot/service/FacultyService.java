package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.entity.Faculty;
import com.mrahmed.FirstSpringBoot.repository.DepartmentRepository;
import com.mrahmed.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public void saveFaculty(Faculty faculty) {
        Department newDepartment= departmentRepository.findById(faculty.getDepartment().getId()).get();

        faculty.setDepartment(newDepartment);

        facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFacultyById(int id) {
        facultyRepository.deleteById(id);
    }

    public Faculty findById(int id) {
        return facultyRepository.findById(id).get();
    }

    public void updateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }


}
