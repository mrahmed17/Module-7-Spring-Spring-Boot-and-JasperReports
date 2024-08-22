package com.mrahmed.FirstSpringBoot.repository;

import com.mrahmed.FirstSpringBoot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
