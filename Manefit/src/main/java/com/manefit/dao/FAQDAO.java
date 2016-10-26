package com.manefit.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.FAQData;

public class FAQDAO  extends SqlSessionDaoSupport {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/*
	 * 리스트 받아오기
	 */
	public ArrayList getList(){
		return (ArrayList) sqlSession.selectList("FAQ.getList");
	}
	
	/*
	 * 총 데이터 개수 구하기
	 */
	public int getTotalList() {
		return (int) sqlSession.selectOne("FAQ.getTotalList");
		
	}
	
	/*
	 * 글 쓰기
	 */
	public void writeProc(FAQData data) {
		sqlSession.insert("FAQ.writeProc", data);
	}
	
	/*
	 * 상세보기
	 */
	public FAQData getView(int fno) {
		return (FAQData) sqlSession.selectOne("FAQ.getView", fno);
	}
	
	/*
	 * 검색 리스트 받아오기
	 */
	public ArrayList getSearchList(FAQData data) {
		return (ArrayList) this.getSqlSession().selectList("FAQ.getSearchList", data);
	}
	
	/*
	 * 검색 데이터 개수 구하기
	 */
	public int getSearchTotalList(FAQData data) {
		return (int) this.getSqlSession().selectOne("FAQ.getSearchTotalList", data);
	}

}
