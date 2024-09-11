package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Department;
import com.mrahmed.FirstSpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
@CrossOrigin("*")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDep(){
        return  departmentService.getAllDep();
    }

    @PostMapping("/save")
    public  void saveDep(@RequestBody Department d){
        departmentService.saveDep(d);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteDep(@PathVariable("id") int id){
        departmentService.deleteDepById(id);

    }

    @PutMapping("/update/{id}")
    public  void updateDep(@RequestBody Department d, @PathVariable("id") int id){
        departmentService.updateDep(d);
    }



}