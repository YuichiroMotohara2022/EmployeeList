package com.example.demo.command.repository.Dao_and_DaoImple.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.command.repository.Dao_and_DaoImple.EmployeeDataRepository;

public class AddImpl {

	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmployeeDataRepository employeeDataRepository;
	
	/*@Override
	public AdministratorData getOneByUserName(String name) throws DataAccessException{
		
		 Map<String, Object> map = jdbc.queryForMap("SELECT * FROM administrator  WHERE adminid = ?", name);

	        AdministratorData administratorData = new AdministratorData();

	        administratorData.setAdminid((int)map.get("adminid"));
	        administratorData.setUsername((String)map.get("username"));
	        administratorData.setAdminpassword((String)map.get("adminpassword"));
	        administratorData.setRole((String)map.get("role"));
	        administratorData.setEnabled((int)map.get("enabled"));
	        administratorData.setAdministratorname((String)map.get("administratorname"));

	   return administratorData;
	}*/
}
