package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address,Integer>{
    
}
