package com.mrahmed.HRandPayrollManagementSystem.restcontroller;

import com.mrahmed.HRandPayrollManagementSystem.entity.Branch;
import com.mrahmed.HRandPayrollManagementSystem.entity.Company;
import com.mrahmed.HRandPayrollManagementSystem.entity.Department;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import com.mrahmed.HRandPayrollManagementSystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin("*")
public class BranchRestController {

    @Autowired
    private BranchService branchService;

// create branch with branch photo upload
    @PostMapping("/create")
    public ResponseEntity<String> createBranch(
            @RequestPart("branch") Branch branch,
            @RequestPart(value = "branchPhoto", required = false) MultipartFile branchPhoto) {
        {
            try {
                branchService.createBranch(branch, branchPhoto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>("Branch created successfully with Photo. " , HttpStatus.CREATED);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Branch> updateBranch(
            @PathVariable Long id,
            @RequestPart("branch") Branch branch,
            @RequestPart(value = "branchPhoto", required = false) MultipartFile branchPhoto) {
        try {
            return new ResponseEntity<>(branchService.updateBranch(id, branch, branchPhoto), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }

    @GetMapping("/find/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    @GetMapping("/all")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/search")
    public Branch findByBranchName(@RequestParam String branchName) {
        return branchService.findByBranchName(branchName);
    }

    @GetMapping("/{branchId}/departments")
    public List<Department> getDepartmentsByBranchId(@PathVariable Long branchId) {
        return branchService.getDepartmentsByBranchId(branchId);
    }

    @GetMapping("/{branchId}/employees")
    public List<User> getEmployeesByBranchId(@PathVariable Long branchId) {
        return branchService.getEmployeesByBranchId(branchId);
    }

    @GetMapping("/{branchId}/department-count")
    public long countDepartmentsByBranchId(@PathVariable Long branchId) {
        return branchService.countDepartmentsByBranchId(branchId);
    }

    @GetMapping("/{branchId}/employee-count")
    public long countEmployeesByBranchId(@PathVariable Long branchId) {
        return branchService.countEmployeesByBranchId(branchId);
    }

    @GetMapping("/company/{companyId}")
    public List<Branch> getBranchesByCompanyId(@PathVariable Long companyId) {
        return branchService.getBranchesByCompanyId(companyId);
    }

}
