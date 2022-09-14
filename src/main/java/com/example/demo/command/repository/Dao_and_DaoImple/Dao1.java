package com.example.demo.command.repository.Dao_and_DaoImple;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.command.repository.Form.AdministratorData;
import com.example.demo.command.repository.Form.EmployeeData;

public interface Dao1 {

	
	 public List<EmployeeData>selectMany()throws DataAccessException;
	
	 
	 public int insertOne(EmployeeData employeeData)throws DataAccessException;
	
	 public EmployeeData selectOne(int employee1)throws DataAccessException;	 
	 
	 
	 public AdministratorData selectOne(String username)throws DataAccessException;	 
	 
	
	 
	 //public EmployeeData selectOne(int employeesId) throws DataAccessException;
	 
	 	
	 //public int update(NewRegistrationData newRegistrationData)throws DataAccessException;
	 
	 
	// public int deleteOne(int EmployeeId) throws DataAccessException;


     public List<EmployeeData> search(String EmployeeId)throws DataAccessException;


     public List<EmployeeData> detailedSearch(String EmployeeId)throws DataAccessException;
     
     //public int finished(EmployeeData employeeData)throws DataAccessException;

     
     
     
	 
	 
	
	
}
