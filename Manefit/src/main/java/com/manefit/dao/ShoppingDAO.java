package com.manefit.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.GoodsData;
import com.manefit.data.LoginData;
import com.manefit.data.OrderData;
import com.manefit.service.ShoppingService;

public class ShoppingDAO extends SqlSessionDaoSupport{
	@Autowired
	private  SqlSessionTemplate sqlSession;
	private ShoppingService sService;
	
	/*
	 * 베너 그림 목록 가져오기
	 */
	public ArrayList getImage() {
		return (ArrayList)sqlSession.selectList("Shopping.getimage");
	}
	/*
	 * 메뉴바 대 분류 구성
	 */
	public ArrayList getLcate(){
		return(ArrayList)sqlSession.selectList("Shopping.getLcate");
	}
	/*
	 * 메뉴바 중 분류 구성
	 */
	public ArrayList getMcate(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getMcate",cate);
	}
	
	/*
	 * 규영형
	 */
	
	/*
	 * New & Best
	 */
	public ArrayList getNewAndBest(String kind){
		if(kind == sService.N){
			return (ArrayList) sqlSession.selectList("Shopping.getNew");
		}
		else{
			return (ArrayList) sqlSession.selectList("Shopping.getBest50");
		}
	}
	
	/*
	 * 대 분류 선택시 상품 총 개수
	 */
	public int getLcateInfo(String cate) {
		return (int) sqlSession.selectOne("Shopping.getLcateInfo", cate);
	}
	
	/*
	 * 대 분류 선택시 상품 목록 가져오기
	 */
	public ArrayList getLcateList(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getLcateList",cate);
	}
	
	/*
	 * 중 분류 선택시 상품 총 개수
	 */
	public int getMcateInfo(String cate){
		return (int) sqlSession.selectOne("Shopping.getMcateInfo", cate);
	}
	
	/*
	 * 중 분류 선택시 상품 목록 가져오기
	 */
	public ArrayList getMcateList(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getMcateList",cate);
	}
	
	/*
	 * 상품 색상 개수 알기
	 */
	public ArrayList getColorCount(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getColorCount",cate);
	}
	
	
	/*
	 *  상품 상세보기 정보
	 */
	public GoodsData getGoodsInfo(String cate){
		return (GoodsData)sqlSession.selectOne("Shopping.getGoodsInfo",cate);
	}
	
	/*
	 * 상품 상세보기 사이즈 구성 
	 */
	public ArrayList getGoodsSize(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getGoodsSize",cate);
	}
	
	/*
	 * 상품 상세보기 색상 구성
	 */
	public ArrayList getGoodsColor(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getGoodsColor",cate);
	}
	
	/*
	 * 상품 상세보기 이미지 알아보기
	 */
	public ArrayList getGoodsImage(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getGoodsImage",cate);
	}
	

		
	/*
	 * 아작스 사이즈 구하기
	 */
	public ArrayList getSize(GoodsData data){
		return (ArrayList)sqlSession.selectList("Shopping.getSize",data);
	}
	
	/*
	 * 색상별 상품 개수
	 */
	public int getColorInfo(GoodsData data){
		return (int) sqlSession.selectOne("Shopping.getColorInfo", data);
	}
	
	/*
	 * 색상별 상품 조회
	 */
	public ArrayList getGoodsColorList(GoodsData data){
		return (ArrayList)sqlSession.selectList("Shopping.getGoodsColorList",data);
	}
	
	/*
	 * 테마별 상품 개수
	 */
	public int getTemaInfo(GoodsData data){
		return (int) sqlSession.selectOne("Shopping.getTemaInfo", data);
	}
	
	/*
	 * 태마별 상품 조회 getGoodsTemaList
	 */
	public ArrayList getGoodsTemaList(GoodsData data){
		return (ArrayList)sqlSession.selectList("Shopping.getGoodsTemaList",data);
	}
	
	/*
	 * 장바구니 목록 지우기
	 */
	public void deleteCartList2(String id){
		sqlSession.delete("MyPage.deleteCartList2",id);
	}
	
	/*
	 * 유저 관심 스타일 가져오기
	 */
	public LoginData getStyle(String id){
		return (LoginData)sqlSession.selectOne("Login.getStyle",id);
	}
	/*
	 * 메인화면에 New 상품 뿌려주기
	 */
	public ArrayList getNewGoods(){
		return (ArrayList)sqlSession.selectList("Shopping.getNewList");
	}
	
	/*
	 * 메인화면 취향저격 뿌려주기
	 */
	
	public ArrayList getGoodsList(int kind, LoginData data){
		if(kind == sService.PREFER){
			return (ArrayList)sqlSession.selectList("Shopping.getPreferList",data);
		}
		else if(kind == sService.NEW){
			return (ArrayList)sqlSession.selectList("Shopping.getNewList");
		}
		else{
			return (ArrayList)sqlSession.selectList("Shopping.bestGoods");
		}
	}
	
	/*
	 * 재고수량 확인 및 상태변경
	 */
	public int quantityConfirmAndUpdate(int kind,String cate){
		if(kind == sService.CONFIRM){
		return (int)sqlSession.selectOne("Shopping.getSumQuantity", cate);
		}
		else{
			sqlSession.update("Shopping.updateStatus",cate);
			return 0;
		}
	}
	
	/*
	 * 주문테이블 MAXNO 구하기
	 */
	public int getMaxOrderNo(String id){
		return (int)sqlSession.selectOne("Shopping.getMaxOrderNo",id);
	}
	
	/*
	 * 그룹에 속한 상품 개수 구하기
	 */
	public int getGroupCount(OrderData data){
		return (int)sqlSession.selectOne("Shopping.getGroupCount",data);
	}
	
	/*
	 * 대 분류에서 칼라 뿌려주기
	 */
	public ArrayList getLcateColor(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getLcateColor",cate);
	}
	
	/*
	 * 대 분류에서 칼라 뿌려주기
	 */
	public ArrayList getMcateColor(String cate){
		return (ArrayList)sqlSession.selectList("Shopping.getMcateColor",cate);
	}
	
	/*
	 * 모든 상품 칼라 가져오기
	 */
	public ArrayList getAllcateColor(){
		return (ArrayList)sqlSession.selectList("Shopping.getAllcateColor");
	}
	
	
	
	
	
	
	
	
	/*
	 * 영민
	 */
	/*
	 * 적립금 가져오기
	 */
	public int getSmoney(String id) {
		return (int) sqlSession.selectOne("Shopping.getSmoney", id);
	}
	
	/*
	 * 회원정보 가져오기
	 */
	public LoginData getUserInfo(String id) {
		return (LoginData) sqlSession.selectOne("Shopping.getUserInfo", id);
	}
	
	/*
	 * 구입 요청
	 */
	public void buyProc(OrderData data) {
		sqlSession.insert("Shopping.buyProc", data);
		sqlSession.update("Shopping.save", data);
		sqlSession.update("Shopping.decQuantity", data);
	
		//옷 인기도 증가
		sqlSession.update("Shopping.upHit", data);
	}
	
	/* 
	 * 주문 목록 리스트 요청
	 */
	public OrderData getOrderList(OrderData data) {
		return (OrderData) sqlSession.selectOne("Shopping.getOrderList", data);
	}
	
	public ArrayList getOrderList2(OrderData data) {
		return (ArrayList) sqlSession.selectList("Shopping.getOrderList2", data);
	}
	
	/*
	 * 찜목록 총 데이터 개수 요청
	 */
	public int getOrderTotalList(String id, int kind) {
		if(kind == 0){
		return (int) sqlSession.selectOne("Shopping.getOrderTotalList", id);
		}
		else{
			return (int) sqlSession.selectOne("Shopping.getOrderTotalList2", id);
		}
	}
	
	/*
	 * 회원 주문 번호 알아오기
	 */
	
	public ArrayList getOrderTotalList2(String id) {
	
		return (ArrayList)sqlSession.selectList("Shopping.getOrderTotalList2", id);
		
	}
	
	/*
	 * 주문정보 상품후기 게시판 리스트 요청
	 */
	public ArrayList getPBoardList(String cate) {
		return (ArrayList) sqlSession.selectList("Shopping.getPBoardList", cate);
	}
	
	/*
	 * 주문정보 상품후기 게시판 페이지 정보 요청
	 */
	public int getPBoardTotalList(String cate) {
		return (int) sqlSession.selectOne("Shopping.getPBoardTotalList", cate);
	}
	
	/*
	 * 주문정보 상품문의 게시판 리스트 요청
	 */
	public ArrayList getIBoardList(String cate) {
		return (ArrayList) sqlSession.selectList("Shopping.getIBoardList", cate);
	}
	
	/*
	 * 주문정보 상품문의 게시판 페이지 정보 요청
	 */
	public int getIBoardTotalList(String cate) {
		return (int) sqlSession.selectOne("Shopping.getIBoardTotalList", cate);
	}
	
	/*
	 * 장바구니 담기
	 */
	public void insertCart(OrderData data){
		sqlSession.insert("Shopping.insertCart",data);
	}
	
	/*
	 * 장바구니 목록 가져오기
	 */
	public ArrayList getCart(String id){
		return (ArrayList)sqlSession.selectList("Shopping.getCart",id);
	}

	/*
	 * 적립금 감소
	 */
	public void useSmoney(OrderData data){
		sqlSession.update("Shopping.useSmoney",data);		
	}

	/*
	 * 새메세지 총 개수
	 */
	public int newMeg(String id) {
		return (int) sqlSession.selectOne("Shopping.newMeg", id);
	}
	
	/*
	 * 해당하는 카테에 모든 점수 불러오기
	 */
	public ArrayList avgScore(String cate) {
		return (ArrayList)sqlSession.selectList("Shopping.avgScore", cate);
	}
	
	/*
	 * 주문 취소
	 */
	public void cancelOrder(int no){
		sqlSession.delete("Shopping.cancelOrder",no);
	}
	/*
	 *	인기, 가격순 상품 개수 
	 */
	public int getSortInfo(GoodsData data){
		return (int) sqlSession.selectOne("Shopping.getSortInfo",data);
	}
	
	
	/*
	 * 상품 인기,가격 순 정렬
	 */
	public ArrayList getSortList(GoodsData data){
		return (ArrayList)sqlSession.selectList("Shopping.getSortList",data);
	}
}
