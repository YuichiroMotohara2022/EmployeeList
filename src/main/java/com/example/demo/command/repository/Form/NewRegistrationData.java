package com.example.demo.command.repository.Form;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Table(name="employee_data")
@Data
public class NewRegistrationData {

	@Getter
	@Setter
	@Column(name="id")
	private int id;
	
	@Getter
	@Setter
	@Column(name="img")
	private MultipartFile image;
	
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	@NotNull(message="必ず入力してください")
	@Column(name="name")
	private String name;
	
	@Getter
	@Setter
	@Column(name="gender")
	@NotBlank(message="必ず入力してください")
	private String gender;
	
	
	@NotNull
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	@Column(name="department")
	private String department;
	
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	//@Pattern(regexp="^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$", message="数値を入力してください")
	@Pattern(regexp="^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$",message="半角数字で0000/00/00の形式で入力してください")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name="birthday")
	private String birthday;
	
	@Getter
	@Setter
	@NotNull(message="空欄は不可です")
	@NotBlank(message="必ず入力してください")
	@Email(message ="メールアドレスの形式で入力してください")
	@Column(name="mail")
	private String mail;
	
	@Getter
	@Setter
	@NotNull(message="空欄は不可です")
	@NotBlank(message="必ず入力してください")
	@Size(max=11)
	@Pattern(regexp="^[0-9]*$" , message= "数字で電話番号を入力してください")
	@Column(name="phonenumber")
	private String phonenumber;
	
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	 @Column(name="address")
	private String address;
	
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	@Column(name="education")
	private String education;
	
	@Getter
	@Setter
	@NotBlank(message="必ず入力してください")
	@Column(name="skill")
	private String art;
	
	 /** 確認チェック */
    @Getter
    @Setter
    private String checked;
 
	
	
	//新規登録(registration)の男女ラジオボタンを押した後、一覧表で男か女か表示されるコード。
	public Map<String,String> getGenderItems(){
		Map<String,String>genderMap = new LinkedHashMap<String,String>();
		genderMap.put("男","男");
		genderMap.put("女","女");
		return genderMap;
	}
	
	public void clearVal() {
		this.id = 0;
		this.image = null;
		this.name = "";
		this.gender = "1";
		this.department = "";
		this.birthday = null;
		this.mail = "";
		this.phonenumber = "";
		this.address = "";
		this.education = "";
		this.art = "";
	}
	
	
	
	
	
}
