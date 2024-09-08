package com.mrahmed.FirstSpringBoot.service;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void saveDep(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void deleteDepBy(Integer id) {
        departmentRepository.deleteById(id);
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id).get();
    }

    public void updateDep(Department department) {
        departmentRepository.save(department);
    }


}
