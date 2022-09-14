package com.example.demo.command.repository.Dao_and_DaoImple.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.command.repository.Dao_and_DaoImple.Dao1;
import com.example.demo.command.repository.Form.AdministratorData;
import com.example.demo.command.repository.Form.EmployeeData;

@Repository("DaoImpl")
public class DaoImpl implements Dao1{

	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public List<EmployeeData>selectMany()throws DataAccessException{
		
		
		List<Map<String,Object>>queryList= jdbc.queryForList( "SELECT * FROM employee_data ");
		
		
		List<EmployeeData> employeeDataList= new ArrayList<>();

		for(Map<String,Object>map:queryList) {

			EmployeeData employeeData  = new EmployeeData();

			employeeData.setId((int)map.get("id"));
			employeeData.setImg((String)map.get("img"));
			employeeData.setName((String)map.get("name"));
			employeeData.setGender((String)map.get("gender"));
			employeeData.setDepartment((String)map.get("department"));
			employeeData.setBirthday((String)map.get("birthday"));
			employeeData.setMail((String)map.get("mail"));
			employeeData.setPhoneNumber((String)map.get("phonenumber"));
			employeeData.setAddress((String)map.get("address"));
			employeeData.setEducation((String)map.get("education"));
			employeeData.setArt((String)map.get("skill"));
			
		 employeeDataList.add(employeeData);
	    }

        return employeeDataList;
        }
		
	
	
	@Override
	public List<EmployeeData> search(String search)throws DataAccessException{
		
		List<Map<String, Object>> registrationList = jdbc.queryForList(
				    "SELECT * FROM employee_data"
				  + " WHERE employee_data.name LIKE ? OR employee_data.img LIKE ? OR employee_data.gender LIKE ? "
				  + "OR employee_data.department LIKE ? OR employee_data.birthday LIKE ? OR employee_data.mail LIKE ? OR employee_data.phonenumber LIKE ? OR "
				  + "employee_data.address LIKE ? OR employee_data.education LIKE ? OR employee_data.skill LIKE ? "
		          + "ORDER BY employee_data.name ASC","%"
				  + search + "%","%" + search + "%" ,"%" + search + "%","%"+ search + "%","%" + search + "%" , "%" + search + "%","%" + search + "%","%"+ search + "%", "%" + search+"%","%"+ search + "%" );

		List<EmployeeData> employeeDataList = new ArrayList<>();

		for(Map<String,Object>map:registrationList) {

		EmployeeData employeeData  = new EmployeeData();

		employeeData.setId((int)map.get("id"));
	    employeeData.setImg((String)map.get("img"));
		employeeData.setName((String)map.get("name"));
		employeeData.setGender((String)map.get("gender"));
		employeeData.setDepartment((String)map.get("department"));
		employeeData.setBirthday((String)map.get("birthday"));
		employeeData.setMail((String)map.get("mail"));
		employeeData.setPhoneNumber((String)map.get("phonenumber"));
		employeeData.setAddress((String)map.get("address"));
		employeeData.setEducation((String)map.get("education"));
		employeeData.setArt((String)map.get("skill"));
		
		employeeDataList.add(employeeData);

		}

		return employeeDataList;
	}


	
	

	@Override
	public List<EmployeeData> detailedSearch(String detailedSearch)throws DataAccessException{
		
		List<Map<String, Object>> registrationList = jdbc.queryForList(
				    "SELECT * FROM employee_data"
				  + " WHERE employee_data.name LIKE ? OR employee_data.img LIKE ? OR employee_data.gender LIKE ? "
				  + "OR employee_data.department LIKE ? OR employee_data.birthday LIKE ? OR employee_data.mail LIKE ? OR employee_data.phonenumber LIKE ? OR "
				  + "employee_data.address LIKE ? OR employee_data.education LIKE ? OR employee_data.skill LIKE ? "
		          + "ORDER BY employee_data.name ASC","%"
				  + detailedSearch + "%","%" + detailedSearch + "%" ,"%" + detailedSearch + "%","%"+ detailedSearch + "%","%" + detailedSearch + "%" , "%" + detailedSearch + "%","%" + detailedSearch + "%","%"+ detailedSearch + "%", "%" + detailedSearch +"%","%"+ detailedSearch + "%" );

		List<EmployeeData> employeeDataList = new ArrayList<>();

		for(Map<String,Object>map:registrationList) {

		EmployeeData employeeData  = new EmployeeData();

		employeeData.setId((int)map.get("id"));
	    employeeData.setImg((String)map.get("img"));
		employeeData.setName((String)map.get("name"));
		employeeData.setGender((String)map.get("gender"));
		employeeData.setDepartment((String)map.get("department"));
		employeeData.setBirthday((String)map.get("birthday"));
		employeeData.setMail((String)map.get("mail"));
		employeeData.setPhoneNumber((String)map.get("phonenumber"));
		employeeData.setAddress((String)map.get("address"));
		employeeData.setEducation((String)map.get("education"));
		employeeData.setArt((String)map.get("skill"));
		
		employeeDataList.add(employeeData);

		}
		return employeeDataList;
	}
	
	
	
	
	
	
	
	@Override
	public int insertOne(EmployeeData employeeData)throws DataAccessException{

		String sql = "INSERT INTO  employee.employee_data ("
				+" id,"
				+" img, "
				+" name,"
				+" gender, "
				+" department,"
				+" birthday, "
				+" mail, "
				+" phonenumber, "
				+" address, "
				+" education, "
				+" skill, "
				+" createtime) "
			    +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		System.out.println(sql);
		       int rowNumber = jdbc.update(sql,
						                    employeeData.getId(),
						                    employeeData.getImg(),
						                    employeeData.getName(),
						                    employeeData.getGender(),
						                    employeeData.getDepartment(),
						                    employeeData.getBirthday(),
						                    employeeData.getMail(),
						                    employeeData.getPhoneNumber(),
						                    employeeData.getAddress(),
						                    employeeData.getEducation(),
						                    employeeData.getArt(),
						                    employeeData.getCreatetime());

				return rowNumber;
	}
	
	
	
	
	
	
	@Override
	public EmployeeData selectOne(int employee1)throws DataAccessException{

	   Map<String, Object> map = jdbc.queryForMap("SELECT * FROM employee_data  WHERE id = ?",employee1);

	        EmployeeData employeeData = new EmployeeData();

	        employeeData.setId((int)map.get("id"));
	        employeeData.setImg((String)map.get("img"));
	        employeeData.setName((String)map.get("name"));
	        employeeData.setGender((String)map.get("gender"));
	        employeeData.setDepartment((String)map.get("department"));
	        employeeData.setBirthday((String)map.get("birthday"));
	        employeeData.setMail((String)map.get("mail"));
	        employeeData.setPhoneNumber((String)map.get("phonenumber"));
	        employeeData.setAddress((String)map.get("address"));
	        employeeData.setEducation((String)map.get("education"));
	        employeeData.setArt((String)map.get("skill"));

	   return employeeData;
	}

	
	@Override
	public AdministratorData selectOne(String username)throws DataAccessException{

	   Map<String, Object> map = jdbc.queryForMap("SELECT * FROM administrator  WHERE adminid = ?", username);

	        AdministratorData administratorData = new AdministratorData();

	        administratorData.setAdminid((int)map.get("adminid"));
	        administratorData.setUsername((String)map.get("username"));
	        administratorData.setAdminpassword((String)map.get("adminpassword"));
	        administratorData.setRole((String)map.get("role"));
	        administratorData.setEnabled((int)map.get("enabled"));
	        administratorData.setAdministratorname((String)map.get("administratorname"));

	   return administratorData;
	}
	
	
	
		
	
}
