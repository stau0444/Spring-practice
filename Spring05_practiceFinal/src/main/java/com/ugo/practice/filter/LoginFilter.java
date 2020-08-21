package com.ugo.practice.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//필터 선언 할때 어노테이션 필요 필터가 적용될 경로 
@WebFilter({"/users/private/*"})
public class LoginFilter implements Filter{



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//request는 servletRequest타입이기 떄문에 httpServletRequest 타입에 담아 준다
		HttpServletRequest req=(HttpServletRequest)request;
		//HttpServletRequest에는 getSession 메소드를 사용하여 session에 담아주고 
		HttpSession session=req.getSession();
		//sesssion으로 id라는 키값으로 session영역에 담겨 있는  
		String id=(String)session.getAttribute("id");
		//session영역에 아이디가 있어 만약 id가 넘어온다면
		if(id!=null) {
			//로그인 된 상태
			chain.doFilter(request, response);
		}else {
			//로그인 안 된 상태
			//다시 시도 한 후 로그인에 성공했을때 원래 가려던 곳으로 보내야하기 때문에 url 정보를 가져가야하고 
			//만약 get방식 파라미터가 있다면 파라미터도 같이 들어가야한다 
			//url정보가져온다
			String url=req.getRequestURI();
			//query(parameter)가져온다 
			String query=req.getQueryString();
			String encodedUrl= null;
			if(query==null) {
				//파라미터가 없다면 url 만가지고간다
				encodedUrl=URLEncoder.encode(url);
			}else {
				//파라미터가 있다면 url에 파라미터를 들고 간다
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			HttpServletResponse resp=(HttpServletResponse)response;
			String cPath=req.getContextPath();
			resp.sendRedirect(cPath+"/user/loginForm.do?url="+encodedUrl);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	
}
