package com.example.demo.command.repository.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class UserSpecifications<EmployeeData> {

	
	public Specification<EmployeeData>nameContains(String name){
	
		return StringUtils.isEmpty(name) ? null : new Specification<EmployeeData>() {
            @Override
            public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
	}
	
		 };
    }
    
    

public Specification<EmployeeData>genderContains(String gender){
	return StringUtils.isEmpty(gender) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("gender"), "%" + gender + "%");
}

	 };
}



public Specification<EmployeeData>departmentContains(String department){
	return StringUtils.isEmpty(department) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("department"), "%" + department + "%");
}

	 };
}



public Specification<EmployeeData>birthdayContains(String birthday){
	return StringUtils.isEmpty(birthday) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("birthday"), "%" + birthday + "%");
}

	 };
}

public Specification<EmployeeData>mailContains(String mail){
	return StringUtils.isEmpty(mail) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("mail"), "%" + mail + "%");
}

	 };
}
public Specification<EmployeeData>phoneNumberContains(String phoneNumber){
	return StringUtils.isEmpty(phoneNumber) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("phoneNumber"), "%" + phoneNumber + "%");
}

	 };
}
public Specification<EmployeeData>addressContains(String address){
	return StringUtils.isEmpty(address) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("address"), "%" + address + "%");
}

	 };
}


public Specification<EmployeeData>educationContains(String education){
	return StringUtils.isEmpty(education) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("education"), "%" + education + "%");
}

	 };
}
public Specification<EmployeeData>artContains(String art){
	return StringUtils.isEmpty(art) ? null : new Specification<EmployeeData>() {
        @Override
        public Predicate toPredicate(Root<EmployeeData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.like(root.get("art"), "%" + art + "%");
}

	 };
}


}
