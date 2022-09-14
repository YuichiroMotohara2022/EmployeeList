package com.example.demo.command.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.codejava.FileUploadUtil;
import com.example.demo.command.repository.Form.AdministratorData;
import com.example.demo.command.repository.Form.EmployeeData;
import com.example.demo.command.repository.Service.EmployeeDataService;
import com.example.demo.command.repository.Service.Service1;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Autowired
	Service1 service1;

	@Autowired
	EmployeeDataService employeeDataService;
	
	@Autowired
	HttpSession session;

	@GetMapping("/empList")
	//@RequestMapping({ "/", "/employeeList" })
	public String getEmployeeList(Model model) {

		model.addAttribute("contents", "/employeeList :: employeeList_contents");

		//List<EmployeeData> employeeData = service1.selectMany();

		//model.addAttribute("employeeData", employeeData);

		//model = loginName(model);
		
		model = findPaginatedByMain(1, "createtime", "desc", model);
		model.addAttribute("administratorname", this.getLoggingInAdmin());
		session.setAttribute("admininfo", this.getLoggingInAdmin());
		/*for(EmployeeData e : employeeData) {
		
		
		    	  log.info(e.toString());
		
		      }*/

		return "/employeeList";//"/employeeList";
	}

	
	
	
	
	public Model loginName(Model model) {
		String admin = SecurityContextHolder.getContext().getAuthentication().getName();

		AdministratorData all = service1.selectOne(admin);

		String administratorname = all.getAdministratorname();

		model.addAttribute("administratorname", administratorname);

		return model;
	}

	@PostMapping("/logout")
	public String postLogout() {

		return "redirect:/login";
	}

	@GetMapping("/search")
	public String search(@RequestParam("search") String search, Model model) {

		List<EmployeeData> employeeData = service1.search(search);

		model = findPaginatedByMain(1, "createtime", "desc", model);
		model.addAttribute("contents", "/employeeList :: employeeList_contents");
		model.addAttribute("listEmployees", employeeData);
		model.addAttribute("administratorname", this.getLoggingInAdmin());

		for (EmployeeData e : employeeData) {

			log.info(e.toString());

		}

		return "/employeeList";
	}

	@GetMapping("/detailedSearchList")
	public String detailedSearchList() {

		return "detailedSearchList";
	}

	@GetMapping("/detailedSearch")
	public String detailedSearch(HttpServletRequest request, Model model) {

		List<EmployeeData> employeeData = service1.detailedSearch(request.getParameter("name"),
				request.getParameter("gender"), request.getParameter("department"), request.getParameter("birthday"),
				request.getParameter("mail"), request.getParameter("phoneNumber"), request.getParameter("address"),
				request.getParameter("education"), request.getParameter("art"));

		model.addAttribute("contents", "/employeeList::employeeList_contents");
		model.addAttribute("listEmployees", employeeData);
		model.addAttribute("administratorname", this.getLoggingInAdmin());

		for (EmployeeData e : employeeData) {

			log.info(e.toString());

		}
		return "/employeeList";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {

		service1.delete(id);

		return "redirect:/empList";

	}

	@PostMapping("/update/{id:.+}")
	public String getUpdate(@ModelAttribute EmployeeData employeeData, Model model,
			@PathVariable("id") int employee_id) {

		model.addAttribute("contents", "/update::update_contents");

		if (employee_id != 0) {

			EmployeeData employeeData2 = service1.selectOne(employee_id);

			model.addAttribute("employeeData", employeeData2);
		}

		return "update";
	}

	@PostMapping("/update")
	public String update( @ModelAttribute("employeeData") @Valid EmployeeData employeeData,
			 BindingResult bindingResult, Model model)
			throws IOException {

		if (bindingResult.hasErrors()) {

			List<String> errorList=new ArrayList<String>();	
			
			for(ObjectError error : bindingResult.getAllErrors()) {
				  errorList.add(error.getDefaultMessage());
				  
			}
			model.addAttribute("validationError", errorList);
			
			model.addAttribute("EmployeeData", employeeData);

			return "update";

		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if (employeeData.getTempimg().isEmpty()) {
            
			employeeData.setCreatetime(timestamp);

			employeeDataService.update(employeeData);

			
			employeeDataService.update(employeeData);

			System.out.println("写真変えてません");

		} else {

			System.out.println("写真変更");

			String uploadDir = "user-photos/";

			String fileName = StringUtils.cleanPath(employeeData.getTempimg().getOriginalFilename());

			FileUploadUtil.saveFile(uploadDir, fileName, employeeData.getTempimg());

			employeeData.setImg(fileName);
			
			
			
			employeeData.setCreatetime(timestamp);

			employeeDataService.update(employeeData);

		}

		return "redirect:/empList";
	}

	
	
	
	
	@PostMapping(value="/update", params="cancel")
	public String update() {

		return "redirect:/employeeList";
	}

	
	
	
	public Model findPaginatedByMain(int pageNo,
			String sortField,
			String sortDir,
			Model model) {
		int pageSize = 5;

		Page<EmployeeData> page = employeeDataService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<EmployeeData> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);
		return model;
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;

		Page<EmployeeData> page = employeeDataService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<EmployeeData> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("administratorname", this.getLoggingInAdmin());
		session.setAttribute("admininfo", this.getLoggingInAdmin());
		return "employeeList";
	}

	//employeeListへ返す。
	//@Autowired
	//EmployeeDataRepository employeeDataRepository;
	@GetMapping("/")
	public String viewHomePage(ModelMap modelMap, HttpServletRequest httpServletRequest, Model model) {
		//model = loginName(model);
		//String name = httpServletRequest.getRemoteUser();
		//AdministratorData administratorname  = employeeDataRepository.getOneByUserName(name);
		//modelMap.addAttribute("administaratorname", administratorname);

		//model.addAttribute("administratorname", name);
		return findPaginated(1, "createtime", "desc", model);
	}
	
	private String getLoggingInAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			String adminName = "";
			UserDetails admin = (UserDetails)auth.getPrincipal();
			adminName = admin.getUsername();
			if(adminName.contains("@")) {
				adminName = adminName.split("@")[0];
			}
			return adminName;
		}
		
		return null;
	}

	
	
//	private Map<String, String> radioGender;
//	
//	private Map<String, String> initRadioGender(){
//	
//	Map<String, String>radio = new LinkedHashMap<>(); 
//	
//     radio.put("男","男");
//     radio.put("女","女");
//     
//	return radio;
//	}
//	
//	@GetMapping("/send")
//    String create(NewRegistrationData newRegistarationData, Model model) {
//        //ポイント3
//        radioGender = initRadioGender();
//        model.addAttribute("radioGender", radioGender);
//
//        //ポイント4
//        newRegistarationData.setGender("0");
//        model.addAttribute("customerForm", newRegistarationData);
//        return "/registration";
//    }
//	
	
}
