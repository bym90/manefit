package com.manefit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manefit.dao.LoginDAO;
import com.manefit.data.LoginData;
import com.manefit.data.MessageData;


@Service
public class LoginService {
	
	@Autowired
	private LoginDAO lDAO;
	
	
	/*
	 * 로그인시 회원 여부 확인
	 */
	public boolean loginCheck(HttpSession session, LoginData data) {
		LoginData ldata = lDAO.loginCheck(data);
		boolean isLogin = false;
		if(ldata != null) {
			isLogin = true;
			String id = (String) ldata.getId();
			String name = (String) ldata.getName();
			
			session.setAttribute("ID", id);
			session.setAttribute("NAME", name);
		}
		
		return isLogin;
	}
	
	/*
	 * 로그인시 회원 등급 확인
	 */
	public String getGrade(HttpSession session, String id) {
		String grade = lDAO.getGrade(id);
		session.setAttribute("GRADE", grade);
		return grade;	
	}
	
	/*
	 * 회원가입시 회원정보 입력
	 */
	public void signUpProc(LoginData data) {
		lDAO.signUpProc(data);
	}
	
	/*
	 * 아이디 중복 확인
	 */
	public boolean idCheck(String id) {
		boolean conf = false;
		int idcheck = lDAO.idCheck(id);
		if(idcheck == 1) {
			conf = true;
		} else {
			conf = false;
		}
		return conf;
	}

	
	/*
	 * 총 구입한 금액 알아오기
	 */
	public int getTotalPrice(String id){
		return lDAO.getTotalPrice(id);
	}

	/*
	 * 등급업
	 */
	public void upGrade(LoginData data){
		lDAO.upGrade(data);
	}

	
	/*
	 * 새 메세지 총 개수
	 */
	public int newMegCount(String id) {
		int count = lDAO.newMegCount(id);
		return count;
	}
	
	/*
	 * 등급업 한사람에게 쪽지
	 */
	public void sendGradeUpNotice(MessageData data){
		lDAO.sendGradeUpNotice(data);
	}

}
