package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.entity.Faculty;
import com.mrahmed.FirstSpringBoot.repository.DepartmentRepository;
import com.mrahmed.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Autowired
//    private FacultyRepository facultyRepository;
//
//    public  void saveDep(Department d){
//            departmentRepository.save(d);
//    }
//
//    public List<Department>  getAllDep(){
//        return departmentRepository.findAll();
//    }
//
//    public  void deleteDepById(Integer id){
//        departmentRepository.deleteById(id);
//    }
//
//    public Department findById(Integer id){
//        return departmentRepository.findById(id).get();
//    }
//
//    public  void updateDep(Department d,Integer id){
//        departmentRepository.save(d);
//    }

    private final DepartmentRepository departmentRepository;

    private final FacultyRepository facultyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }


    public Department saveDepartment(Department d) {
        Faculty faculty=facultyRepository.findById(d.getFaculty().getId()).orElseThrow(
                () -> new RuntimeException("Faculty not found")
        );
        d.setFaculty(faculty);
        departmentRepository.save(d);
        return d;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void deleteDepartmentById(int id) {
        departmentRepository.deleteById(id);
    }
    public Department findById(int id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No department found with id: " + id)
        );
    }
    public void updateDepartment( Department d, int id) {
        departmentRepository.save(d);
    }


}