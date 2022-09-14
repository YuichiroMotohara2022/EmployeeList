package com.example.demo.command.repository.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.command.repository.Dao_and_DaoImple.EmployeeDataRepository;
import com.example.demo.command.repository.Form.EmployeeData;


@Service
@Transactional
public class EmployeeDataService {

	@Autowired
	EmployeeDataRepository employeeDataRepository;
	
	
	
	
	
	
	
	
	
	public void update(EmployeeData employeeData) {
		
		employeeDataRepository.save(employeeData);
		
	}
	
	
	public Page<EmployeeData> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeDataRepository.findAll(pageable);
	}
	
	
	
	
}
