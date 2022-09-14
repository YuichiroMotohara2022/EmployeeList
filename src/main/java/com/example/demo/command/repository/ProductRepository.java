package com.example.demo.command.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.command.repository.Form.EmployeeData;

public interface ProductRepository extends JpaRepository<EmployeeData, Integer>{
	
	List<EmployeeData> findByName(String name);

}
	