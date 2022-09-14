package com.example.demo.command.repository.Form;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class EditData {

	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private int img;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String gender;
	
	@Getter
	@Setter
	private String department;
	
	@Getter
	@Setter
	private int birthday;
	
	@Getter
	@Setter
	private String mail;
	
	@Getter
	@Setter
	private String phonenumber;
	
	@Getter
	@Setter
	private String address;
	
	@Getter
	@Setter
	private String education;
	
	@Getter
	@Setter
	private String art;
	
	 /** 確認チェック */
    @Getter
    @Setter
    private String checked;
 
	
	
	
	public Map<String,String> getGenderItems(){
		Map<String,String>genderMap = new LinkedHashMap<String,String>();
		genderMap.put("1","男");
		genderMap.put("2","女");
		return genderMap;
	}
	
	
	
	
}
