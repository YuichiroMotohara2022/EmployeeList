package com.example.demo.command.repository.Dao_and_DaoImple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.command.repository.Form.EmployeeData;

@Repository
public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Integer>,JpaSpecificationExecutor<EmployeeData>{
	
	//public AdministratorData getOneByUserName(String name)throws DataAccessException;
		
	
}
