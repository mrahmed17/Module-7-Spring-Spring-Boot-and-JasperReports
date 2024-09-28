package com.mrahmed.HRandPayrollManagementSystem.repository;

import com.mrahmed.HRandPayrollManagementSystem.entity.Department;
import com.mrahmed.HRandPayrollManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long> {

    // Find a department by its name
    @Query("SELECT d FROM Department d WHERE LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :departmentName, '%'))")
    Department findByDepartmentName(@Param("departmentName") String departmentName);

    // Find all employees by their department
    @Query("SELECT u FROM User u WHERE u.departments.id = :departmentId")
    List<User> findAllEmployeesByDepartmentId(@Param("departmentId") Long departmentId);

    // Count total employees by their department
    @Query("SELECT COUNT(u) FROM User u WHERE u.departments.id = :departmentId")
    long countTotalEmployeesByDepartmentId(@Param("departmentId") Long departmentId);

    // Find all departments by their branch
    @Query("SELECT d FROM Department d WHERE d.branch.id = :branchId")
    List<Department> findAllByBranchId(@Param("branchId") Long branchId);

    // Find all departments by their company
    @Query("SELECT d FROM Department d WHERE d.branch.company.id = :companyId")
    List<Department> findAllByCompanyId(@Param("companyId") Long companyId);


}
