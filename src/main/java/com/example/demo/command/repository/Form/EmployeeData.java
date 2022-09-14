package com.example.demo.command.repository.Form;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {

	
	  @Id
	  @Column(name="id")
	  private Integer id;
	  
	  @Column(name="img")
	  private String img;
	
	  
	  @NotBlank
	  @Column(name="name")
	  @NotEmpty(message="空欄は不可です")
	  private String name;
	  
	  @Column(name="gender")
	  @NotBlank(message="必ず入力してください")
	  private String gender;
	  
	  @NotBlank(message="空欄は不可です")
	  @Column(name="department")
	  @NotNull
	  private String department;
	  
	  
	  @NotBlank(message = "空欄は不可です")
	  @Column(name="birthday")
	  //@Pattern(regexp = "^[0-9]*$")
	  @Pattern(regexp="^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$",message="半角数字で0000/00/00の形式で入力してください")
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private String birthday;
	  
	  
	  @NotBlank(message="空欄は不可です")
	  @NotNull(message="空欄は不可です")
	  @Column(name="mail")
	  @Email(message ="メールアドレスの形式で入力してください")
	  private String mail;
	  
	  
	  @NotBlank(message="空欄は不可です")
	  @NotNull(message="空欄は不可です")
	  @Column(name="phonenumber")
	  @Pattern(regexp="^[0-9]*$", message= "数字で電話番号を入力してください")
	  private String phoneNumber;
	  
	  
	  @NotBlank(message="空欄は不可です")
	  @Column(name="address")
	  private String address;
	  
	  
	  @NotBlank(message="空欄は不可です")
	  @Column(name="education")
	  private String education;
	  
	  
	  @NotBlank(message="空欄は不可です")
	  @Column(name="skill")
	  private String art;
	  
	  @Column(name="createtime")
	  private Timestamp createtime;
	  
	  @Transient
	  public String getPhotosImagePath() {
	        if (img == null || id == null) return null;
	         
	        return "/user-photos/"   + img;
	    }
	  
	
	

	  @Transient
	  private MultipartFile tempimg;
	
	
	
	

	public EmployeeData(Integer id, String img, String name, String gender, String department, String birthday,
			String mail, String phoneNumber, String address, String education, String art,Timestamp createtime) {
		super();
		this.id = id;
		this.img = img;
		this.name = name;
		this.gender = gender;
		this.department = department;
		this.birthday = birthday;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.education = education;
		this.art = art;
		this.createtime = createtime;
	}
	

	public Map<String,String> getGenderItems(){
		Map<String,String>genderMap = new LinkedHashMap<String,String>();
		genderMap.put("1","男");
		genderMap.put("2","女");
		return genderMap;
	}
	

	
	
	
	
}
