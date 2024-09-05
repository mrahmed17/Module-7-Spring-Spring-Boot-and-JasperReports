package com.mrahmed.FirstSpringBoot.repository;

import com.mrahmed.FirstSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
