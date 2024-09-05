package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDep() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Department department) {
        departmentService.saveDep(department);
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepBy(id);
    }

    @PutMapping("/update")
    public void updateDepartment(@RequestBody Department department) {
        departmentService.updateDep(department);
    }
}
