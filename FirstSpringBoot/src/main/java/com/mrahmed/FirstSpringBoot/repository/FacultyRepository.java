package com.mrahmed.FirstSpringBoot.repository;

import com.mrahmed.FirstSpringBoot.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
