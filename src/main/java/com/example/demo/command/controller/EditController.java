package com.example.demo.command.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.command.repository.Form.EditData;
import com.example.demo.command.repository.Form.NewRegistrationData;


@Controller
@SessionAttributes(types = EditData.class)
public class EditController {

			
	    @ModelAttribute("EditData")
	    @GetMapping("/edit")
	    public EditData createEditData(){
	    	EditData editData = new EditData();
	        //名前・性別の初期値を設定する
	    	editData.setName("");
	    	editData.setGender("1");
	        return editData;//editへデータを返す。
	    }
	 
	    
	    
	    //@GetMapping("/")
	    //public String index(NewRegistrationData newRegistrationData){
	    //    return "input";
	    //}
	 
	    /**
	     * 確認画面に遷移する
	     * @param demoForm Formオブジェクト
	     * @return 確認画面へのパス
	     */
	    @PostMapping("/confirm2")
	    public String confirm(NewRegistrationData newRegistrationData){
	        return "confirm2";
	    }
	 
	    /**
	     * 完了画面へのリダイレクトパスに遷移する
	     * @return 完了画面へのリダイレクトパス
	     */
	    @PostMapping("/send2")
	    public String send(){
	        return "redirect:/complete2";
	    }
	 
	    /**
	     * 完了画面に遷移する
	     * @param sessionStatus セッションステータス
	     * @return 完了画面
	     */
	    @GetMapping("/complete2")
	    public String complete(SessionStatus sessionStatus){
	        //セッションオブジェクトを破棄
	        sessionStatus.setComplete();
	        return "complete2";
	    }
		
		
		
		
	}

	

