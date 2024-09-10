package com.mrahmed.HrAndPayrollManagement.service;

import com.mrahmed.HrAndPayrollManagement.entity.Branch;
import com.mrahmed.HrAndPayrollManagement.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch>getAllBranch() {
        return branchRepository.findAll();
    }

    public void saveBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(()-> new RuntimeException("Branch not found by this id: " + id));
    }


}
