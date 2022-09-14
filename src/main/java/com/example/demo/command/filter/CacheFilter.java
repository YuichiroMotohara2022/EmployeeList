package com.example.demo.command.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CacheFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	/**
	 * フィルターを設計
	 * @param request Viewからのリクエスト
	 * @param response　Viewへ返すレスポンス
	 * @param chain　フィルターの実行順序を規定
	 * @throw IOException, ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//ViewからのリクエストをHttpServletRequest型へキャスト
		HttpServletRequest Httprequest =(HttpServletRequest)request;
		//ViewへのレスポンスをHttpServletResponse型へキャスト
		HttpServletResponse Httpresponse =(HttpServletResponse)response;
		//リクエスト元のURLを取得
		String request_uri = Httprequest.getRequestURI();
		//ブラウザのキャシューを無効化
		Httpresponse.setDateHeader("Expires", 0);
		Httpresponse.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		Httpresponse.setHeader("Pragme", "no-cache");
		//login,register, css,js,imageファイルが例外としてフィルターしない
		if(Httprequest.getRequestURI().equals("/login")||request_uri.toString().contains(".css") || request_uri.toString().contains(".js")||request_uri.toString().contains(".png")||request_uri.toString().contains(".jpg")) {
			chain.doFilter(Httprequest, Httpresponse);
			//ログインしていない場合、（Sessionに入っていない場合）、アクセスを拒否する、強制的にloginページ遷移する
		}else {
			chain.doFilter(request, response);
		}



	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
