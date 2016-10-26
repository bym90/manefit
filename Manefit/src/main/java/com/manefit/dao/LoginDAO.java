package com.manefit.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.LoginData;
import com.manefit.data.MessageData;

public class LoginDAO extends SqlSessionDaoSupport{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/*
	 * 로그인시 회원 여부 확인
	 */
	public LoginData loginCheck(LoginData ldata){
		return (LoginData) sqlSession.selectOne("Login.loginCheck", ldata);
	}
	
	/*
	 * 회원 등급 가져오기
	 */
	public String getGrade(String id) {
		return (String) sqlSession.selectOne("Login.getGrade", id);
	}
	
	/*
	 * 회원 가입시 회원 정보 입력
	 */
	public void signUpProc(LoginData data) {
		sqlSession.insert("Login.signUpProc", data);
		sqlSession.insert("Login.signUpRank", data);
		sqlSession.insert("Login.signUpSmoney", data);
	}
	
	/*
	 * 아이디 중복 확인
	 */
	public int idCheck(String id) {
		return (int) sqlSession.selectOne("Login.idCheck", id);
	}

	
	/*
	 * 구입한 총 가격 알아오기
	 */
	public int getTotalPrice(String id){
		return (int)sqlSession.selectOne("Login.getTotalPrice",id);
				
	}
	
	/*
	 * 회원 등업 시키기
	 */
	public void upGrade(LoginData data){
			sqlSession.update("Login.upGrade",data);
			sqlSession.update("Login.upSaveMoney",data);
	}

	
	/*
	 * 새 메세지 총 개수
	 */
	public int newMegCount(String id) {
		return (int) sqlSession.selectOne("Login.newMegCount", id);
	}
	/*
	 * 등급업 한 사람에게 쪽지
	 */
	public void sendGradeUpNotice(MessageData data){
		sqlSession.insert("Admin.sendGradeUpNotice",data);
	}
}
