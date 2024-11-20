package com.sufiyan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sufiyan.entities.User;
import com.sufiyan.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByRole(UserRole role);
}
