package com.example.demo.command.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorsCenterController implements ErrorController{

	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView myErrorHtml(ModelAndView mav, HttpServletRequest request) {
		//エラーの種類を取得
		HttpStatus status = getHttpStatus(request);
		//mvにエラーの種類を付与
		mav.setStatus(status);
		mav.setViewName("error");

		mav.addObject("status", status.value());
		//mvにエラー元のURLを付与
		mav.addObject("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));

		return mav;
	}
	/**
	 * エラーのステータスを取得
	 * @param request エラー元からのリクエスト
	 * @return ステータスを返す
	 */
	private static HttpStatus getHttpStatus(HttpServletRequest request) {
		//エラーのステータスコードを取得
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		//statusに500エラーを付与(404以外はinternal server errorとして表示)
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		//エラーが404であれば
		if(statusCode != null && statusCode.toString().equals("error")) {
			//statusをNOT FOUNDに変更
			status = HttpStatus.NOT_FOUND;
		}

		return status;
	}

	
	
	
}
