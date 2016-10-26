package com.manefit.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.MessageData;
import com.manefit.data.OrderData;

public class MyPageDAO extends SqlSessionDaoSupport{
	@Autowired
	private SqlSessionTemplate sqlSession;

	/*
	 * 장바구니 담기
	 */
	public void insertCart(OrderData data) {
		sqlSession.insert("MyPage.insertCart", data);
	}
	
	/* 
	 * 장바구니 리스트 요청
	 */
	public ArrayList getCartList(String id) {
		return (ArrayList) sqlSession.selectList("MyPage.getCartList", id);
	}
	
	/*
	 * 장바구니 총 데이터 개수 요청
	 */
	public int getCartTotalList(String id) {
		return (int) sqlSession.selectOne("MyPage.getCartTotalList", id);
	}
	
	/*
	 * 장바구니 목록 삭제하기
	 */
	public void deleteCartList(int no){
		sqlSession.delete("MyPage.deleteCartList",no);
	}
	
	/*
	 * 찜 목록 삭제하기
	 */
	public void deleteWishList(int no){
		sqlSession.delete("MyPage.deleteWishList",no);
	}
	
	/*
	 * 찜목록 담기
	 */
	public void insertFavor(OrderData data) {
		sqlSession.insert("MyPage.insertFavor", data);
	}
	
	/* 
	 * 찜목록 리스트 요청
	 */
	public ArrayList getFavorList(String id) {
		return (ArrayList) sqlSession.selectList("MyPage.getFavorList", id);
	}
	
	/*
	 * 찜목록 총 데이터 개수 요청
	 */
	public int getFavorTotalList(String id) {
		return (int) sqlSession.selectOne("MyPage.getFavorTotalList", id);
	}
	
	/*
	 * 검색 리스트 받아오기
	 */
//	public ArrayList getCartSearchList(FAQData data) {
//		return (ArrayList) this.getSqlSession().selectList("FAQ.getSearchList", data);
//	}
	
	/*
	 * 쪽지함 총 데이터 개수 요청
	 */
	public int getMsTotalList(String id) {
		return (int) sqlSession.selectOne("MyPage.getMsTotalList", id);
	}
	
	/*
	 * 쪽지함 리스트 받아오기
	 */
	public ArrayList getMsList(String id) {
		return (ArrayList) sqlSession.selectList("MyPage.getMsList", id);
	}
	
	/*
	 * 새글 읽은글로 업데이트
	 */
	public void readMeg(int reno) {
		sqlSession.update("MyPage.readMeg", reno);
	}
	
	/*
	 * 번호에 해당하는 쪽지정보 가져오기
	 */
	public MessageData megInfo(int reno) {
		return (MessageData) sqlSession.selectOne("MyPage.megInfo", reno);
	}
	
	/*
	 * 새글이 있는지 확인
	 */
	public int newMeg(String id) {
		return (int) sqlSession.selectOne("MyPage.newMeg", id);
	}
	
	/*
	 * 내가 쓴 후기글 총 데이터 개수 요청
	 */
	public int getMypsTotalList(String id) {
		return (int) sqlSession.selectOne("MyPage.getMypsTotalList", id);
	}
	
	/*
	 * 내가 쓴 후기글 리스트 받아오기
	 */
	public ArrayList getMypsList(String id) {
		return (ArrayList) sqlSession.selectList("MyPage.getMypsList", id);
	}
	
	/*
	 * 내가 쓴 상품문의 글 총 데이터 개수 요청
	 */
	public int getMyinqTotalList(String id) {
		return (int) sqlSession.selectOne("MyPage.getMyinqTotalList", id);
	}
	
	/*
	 * 내가 쓴 상품문의 글 리스트 받아오기
	 */
	public ArrayList getMyinqList(String id) {
		return (ArrayList) sqlSession.selectList("MyPage.getMyinqList", id);
	}
	
}
