package com.example.demo.command.repository.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.command.repository.ProductRepository;
import com.example.demo.command.repository.Dao_and_DaoImple.Dao1;
import com.example.demo.command.repository.Dao_and_DaoImple.EmployeeDataRepository;
import com.example.demo.command.repository.Form.AdministratorData;
import com.example.demo.command.repository.Form.EmployeeData;

@Service
public class Service1 {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	EmployeeDataRepository employeeDataRepository;
	
	@Autowired
	Dao1 dao1;
	
	public List<EmployeeData> selectMany(){

		   List<EmployeeData> employeeList = dao1.selectMany();

		 return employeeList;
	}
	
	
	
	public List<EmployeeData> search(String search) {

		return dao1.search(search);
	}

	
		
	
	
	public List<EmployeeData> detailedSearch(String name, String gender, String department, String birthday, String mail, String phoneNumber,String address,String education, String art) {
	    
		UserSpecifications<EmployeeData> spec = new UserSpecifications<>();
		return employeeDataRepository.findAll(Specification
	        .where(spec.nameContains(name))
	        .and(spec.genderContains(gender))
	        .and(spec.departmentContains(department))
	        .and(spec.birthdayContains(birthday))
	        .and(spec.mailContains(mail))
	        .and(spec.phoneNumberContains(phoneNumber))
	        .and(spec.addressContains(address))
	        .and(spec.educationContains(education))
	        .and(spec.artContains(art))
	         );
	}
	
	
		
	

	public void create(EmployeeData employeeData) {
	    
		 dao1.insertOne(employeeData);

		}
	    
	
	
	
	
	public boolean insertOne(EmployeeData employeeData) {
		  
		  int insert = dao1.insertOne(employeeData);

		  boolean result = false;

		    if(insert > 0) {

		      result= true;
		    }

		return result;
	}
		  
	  
	
		

	
	public EmployeeData findById(Integer id) {
		  
		  return productRepository.findById(id).get();
	  }
	
	
	
	
	  

	public void delete(Integer id) {
		  
		  EmployeeData employeeData = findById(id);
		  productRepository.delete(employeeData);
	  }
	  
	  
	
	
    public EmployeeData selectOne(int employee1) {

			return dao1.selectOne(employee1);
	    }
    
    public AdministratorData selectOne(String username) {
    	
    	
    	return dao1.selectOne(username);
    }

    
    
    
    
   
    
    
}