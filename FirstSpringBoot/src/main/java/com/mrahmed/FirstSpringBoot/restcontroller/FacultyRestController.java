package com.mrahmed.FirstSpringBoot.restcontroller;

import com.mrahmed.FirstSpringBoot.entity.Faculty;
import com.mrahmed.FirstSpringBoot.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @PostMapping("/save")
    public void saveFaculty(@RequestBody Faculty faculty) {
        facultyService.saveFaculty(faculty);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFaculty(@PathVariable("id") int id) {
        facultyService.deleteFacultyById(id);
    }

    @PutMapping("/update")
    public void updateFaculty(@RequestBody Faculty faculty) {
        facultyService.updateFaculty(faculty);
    }


}
