package com.sufiyan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sufiyan.entities.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>{

}
