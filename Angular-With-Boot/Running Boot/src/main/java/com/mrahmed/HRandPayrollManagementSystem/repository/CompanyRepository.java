package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Branch;
import com.mrahmed.HRandPayrollManagementSystem.entity.Company;
import com.mrahmed.HRandPayrollManagementSystem.entity.Department;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Long> {

    // Find a company by its name
    @Query("SELECT c FROM Company c WHERE LOWER(c.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))")
    Company findByCompanyName (@Param("companyName")String companyName);

    // Find all branches by their company
    @Query("SELECT b FROM Branch b WHERE b.company.id = :companyId")
    List<Branch> findAllByCompanyId(Long companyId);

    // Find all departments by their branches
    @Query("SELECT d FROM Department d WHERE d.branch.id = :branchId")
    List<Department> findAllDepartmentsByBranchId(Long branchId);

    // Find all departments by their company
    @Query("SELECT d FROM Department d WHERE d.branch.company.id = :companyId")
    List<Department> findAllDepartmentsByCompanyId(Long companyId);

    // Find all employees by their department
    @Query("SELECT u FROM User u WHERE u.departments.id = :departmentId")
    List<User> findAllEmployeesByDepartmentId(Long departmentId);

    // Count total employees by their company
    @Query("SELECT COUNT(u) FROM User u WHERE u.departments.branch.company.id = :companyId")
    long countTotalEmployeesByCompanyId(Long companyId);

    // Count total employees by their branch
    @Query("SELECT COUNT(u) FROM User u WHERE u.departments.branch.id = :branchId")
    long countTotalEmployeesByBranchId(Long branchId);


}
