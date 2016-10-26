package com.manefit.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class IsLoginCheck extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, 
			HttpServletResponse resp, Object handle) throws Exception {
		
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("ID");
		if(!StringUtil.isNull(id)) {			
			return true;
		}
		else {
			resp.sendRedirect("../");
			return false;
		}
	}

}
