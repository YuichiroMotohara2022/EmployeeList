package com.example.demo.command.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.codejava.FileUploadUtil;
import com.example.demo.command.repository.Form.EmployeeData;
import com.example.demo.command.repository.Form.NewRegistrationData;
import com.example.demo.command.repository.Service.Service1;




@Controller
@SessionAttributes(types = NewRegistrationData.class)
public class RegistrationController {

	@Autowired
	private Service1 service1;

	@ModelAttribute("NewRegistrationData")
	@GetMapping("/registration")
	public NewRegistrationData createNewRegistrationData(NewRegistrationData newRegistrationData){
		//NewRegistrationData newRegistrationData = new NewRegistrationData();
		//名前・性別の初期値を設定する
		newRegistrationData.clearVal();
		//newRegistrationData.setName("");
		//newRegistrationData.setGender("1");
		return newRegistrationData;//registrationへデータを返す。
	}


	/**
	 * 確認画面に遷移する
	 * @param demoForm Formオブジェクト
	 * @return 確認画面へのパス
	 */
	@PostMapping("/confirmation")
	public String confirmation(NewRegistrationData newRegistrationData){
		return "confirmation";
	}


	/**
	 * 完了画面へのリダイレクトパスに遷移する
	 * @return 完了画面へのリダイレクトパス
	 */
	@PostMapping("/send")
	public String send(HttpServletRequest request,SessionStatus sessionStatus,@Validated NewRegistrationData newRegistrationData,BindingResult bindingResult,Model model ) 
			throws IOException{

		if(bindingResult.hasErrors()) {

			List<String> errorList=new ArrayList<String>();	

			for(ObjectError error : bindingResult.getAllErrors()) {
				errorList.add(error.getDefaultMessage());

			}
			model.addAttribute("validationError", errorList);


			Map<String, FieldError> errorsList = new HashMap<String, FieldError>();

			errorsList.put("name", bindingResult.getFieldError("name"));
			errorsList.put("gender", bindingResult.getFieldError("gender"));
			errorsList.put("department", bindingResult.getFieldError("department"));
			errorsList.put("birthday", bindingResult.getFieldError("birthday"));
			errorsList.put("mail", bindingResult.getFieldError("mail"));
			errorsList.put("phonenumber", bindingResult.getFieldError("phonenumber"));
			errorsList.put("address", bindingResult.getFieldError("address"));
			errorsList.put("education", bindingResult.getFieldError("education"));
			errorsList.put("art", bindingResult.getFieldError("art"));





			//model.addAttribute("validationError", errorsList);

			//model.addAttribute("errMessages", errorsList);
			return  "/registration";
		}



		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		if(newRegistrationData.getImage().isEmpty()) {

			EmployeeData employeeData = new EmployeeData(null, request.getParameter("img"),request.getParameter("name"),request.getParameter("gender"),request.getParameter("department"),
					request.getParameter("birthday"),request.getParameter("mail"),request.getParameter("phonenumber"),request.getParameter("address"),
					request.getParameter("education"),request.getParameter("art"),timestamp);

			employeeData.setImg("85612911.jpeg");

			service1.create(employeeData);

			return "redirect:/empList";

		}

		EmployeeData employeeData = new EmployeeData(null, request.getParameter("img"),request.getParameter("name"),request.getParameter("gender"),request.getParameter("department"),
				request.getParameter("birthday"),request.getParameter("mail"),request.getParameter("phonenumber"),request.getParameter("address"),
				request.getParameter("education"),request.getParameter("art"),timestamp);

		System.out.println(employeeData);

		// ファイルの名前取得
		String fileName = StringUtils.cleanPath(newRegistrationData.getImage().getOriginalFilename());


		//ディレクトリ
		String uploadDir = "user-photos/";

		//ファイルアップロード（保存先ディレクトリ、ファイルの名前、ファイル）
		FileUploadUtil.saveFile(uploadDir, fileName, newRegistrationData.getImage());


		//ユーザー情報のイメージをセット
		employeeData.setImg(fileName);

		//ログ
		System.out.println(employeeData);

		//ユーザー保存
		service1.create(employeeData);

		//保存先ログ
		System.out.println("!!!"+System.getProperty("user.dir").toString());


		sessionStatus.setComplete();


		return "redirect:/empList";
	}





	/**
	 * 完了画面に遷移する
	 * @param sessionStatus セッションステータス
	 * @return 完了画面
	 */
	@GetMapping("/complete")
	public String complete(SessionStatus sessionStatus){
		//セッションオブジェクトを破棄
		sessionStatus.setComplete();
		return "complete";
	}





	@PostMapping("/users/save")
	public RedirectView saveUser(EmployeeData employeeData,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		employeeData.setImg(fileName);

		boolean  savedUser = service1.insertOne(employeeData);

		String uploadDir = "user-photos/" + employeeData.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return new RedirectView("/users", true);
	}


}





