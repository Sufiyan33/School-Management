package com.sufiyan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sufiyan.entities.StudentLeave;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long> {

}
