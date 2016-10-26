package com.manefit.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.BoardData;
import com.manefit.data.GoodsData;
import com.manefit.data.ImageData;
import com.manefit.data.MessageData;
import com.manefit.data.OrderData;
import com.manefit.data.ShoppingData;

public class AdminDAO extends SqlSessionDaoSupport{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/*
	 * 베너 등록
	 */
	public void insertImage(ShoppingData data){
		sqlSession.insert("Admin.insertimage",data);
	}
	
	/*
	 * 카테고리 등록하기
	 */
	public void insertCate(ShoppingData data){
		sqlSession.insert("Admin.insertCate",data);
	}
	/*
	 * 카테고리 목록 가져오기
	 */
	public ArrayList getCate(ShoppingData data){
		return (ArrayList)sqlSession.selectList("Admin.getCate",data);
	}
	
	/*
	 * 상품 등록하기
	 */
	public void insertGoods(GoodsData data){
		sqlSession.insert("Admin.insertGoods",data);
	
	}
	
	/*
	 * 중복된 상품 제외하기 위한 갯수
	 */
	public int notInsertGoods(String scate1){
		return (int) sqlSession.selectOne("Admin.notInsertGoods", scate1);
	}
	
	/*
	 * 상품 이미지 등록
	 */
	public void insertGoodsImage(ImageData data){
		sqlSession.insert("Admin.insertGoodsImg",data);
	}
	
	/*
	 * 상품 서브 이미지 변경
	 */
	public void changeSubImage(ImageData data){
		sqlSession.update("Admin.changeSubImage",data);
	}
	
	/*
	 * 상품 메인 이미지 변경
	 */
	public void changeMainImage(ImageData data){
		sqlSession.update("Admin.changeMainImage",data);
	}
	
	/*
	 * 메인이미지 가져오기
	 */
	public String getMainImage(String cate){
		return (String)sqlSession.selectOne("Admin.getMainImage",cate);
	}
	
	/*
	 * 재고 총 개수 가져오기
	 */
	public int getSTotalList(OrderData idata){
		System.out.println("1인덱스가 뭐니"+idata.getIndex());
		return (Integer) sqlSession.selectOne("Admin.getSTotalList", idata);
	}
	
	/*
	 * 재고 품목 가져오기
	 */
	public ArrayList getStockList(OrderData idata){
		System.out.println("2인덱스가 뭐니"+idata.getIndex());
		return (ArrayList)sqlSession.selectList("Admin.getStockList", idata);
	}
	
	/*
	 * 상품 서브 이미지 가져오기
	 */
	public ArrayList getGoodsImage(String temp){
		return (ArrayList)sqlSession.selectList("Admin.getSubimage", temp);
	}
	/*
	 * 상품 기본정보 수정
	 */
	public void updateGoodsInfo(GoodsData data){
		sqlSession.update("Admin.updateGoodsInfo",data);
	}
	
	/*
	 * 상품 사이즈, 색상, 수량 적용
	 */
	public void insertSCQ(GoodsData data){
		sqlSession.insert("Admin.insertSCQ",data);
	}
	
	/*
	 * 상품 사이즈, 색상 ,수량 중복 체크
	 */
	public ArrayList checkSCQ(GoodsData data){
		return (ArrayList)sqlSession.selectList("Admin.checkSCQ",data);
	}

	/*
	 * 상품 사이즈, 색상, 수량 업데이트
	 */
	public void updateSCQ(GoodsData data){
		sqlSession.update("Admin.updateSCQ",data);
		
	}
	/*
	 * 수량 변경
	 */
	public void modifySCQ(GoodsData data){
		sqlSession.update("Admin.modifySCQ",data);
		
	}
	
	/*
	 * 상품사이즈, 색상, 수량 가져오기
	 */
	public ArrayList getSCQ(String cate){
		return (ArrayList)sqlSession.selectList("Admin.getSCQ",cate);
	}
	
	/*
	 * 상품 총 개수 가져오기
	 */
	public int getTotal(String cate){
		return (Integer)sqlSession.selectOne("Admin.getTotal",cate);
	}
	
	/*
	 *  사이즈 색상 컬러 삭제
	 */
	public void deleteSCQ(GoodsData data){
		sqlSession.delete("Admin.deleteSCQ",data);
	}
	
	/*
	 * 주문관리 리스트 받아오기
	 */
	public ArrayList getOrderList(){
		return (ArrayList) sqlSession.selectList("Admin.getOrderList");
	}
	
	/*
	 * 주문관리 총 데이터 개수 구하기
	 */
	public int getOTotalList() {
		return (int) sqlSession.selectOne("Admin.getOTotalList");
		
	}
	
	/*
	 * 운송장 번호 업데이트
	 */
	public void parcelUpdate(OrderData data) {
		sqlSession.update("Admin.parcelUpdate", data);
	}
	
	/*
	 * 배송상태 완료로 변경
	 */
	public void ShipUpdate(int ono) {
		sqlSession.update("Admin.ShipUpdate", ono);
	}
	
	/*
	 * 유저관리 총 데이터 개수 구하기
	 */
	public int getUTotalList() {
		return (int) sqlSession.selectOne("Admin.getUTotalList");
		
	}
	
	/*
	 * 유저관리 리스트 받아오기
	 */
	public ArrayList getUserList(){
		return (ArrayList) sqlSession.selectList("Admin.getUserList");
	}
	
	/*
	 * 회원 탈퇴 요청
	 */
	public void userWithdraw(String id) {
		sqlSession.update("Admin.userWithdraw", id);
	}
	

	/*
	 * 주문한 아이디 가져오기
	 */
	
	public ArrayList getOrderID(){
		return (ArrayList)sqlSession.selectList("Admin.getOrderID");
	}

	/*
	 * 사용자 상품후기 글 총 데이터 개수 구하기
	 */
	public int getApsTotalList() {
		return (int) sqlSession.selectOne("Admin.getApsTotalList");
		
	}
	
	/*
	 * 사용자 상품후기 글 리스트 받아오기
	 */
	public ArrayList getApsList(){
		return (ArrayList) sqlSession.selectList("Admin.getApsList");
	}
	
	/*
	 * 사용자 상품문의 글 총 데이터 개수 구하기
	 */
	public int getAinqTotalList() {
		return (int) sqlSession.selectOne("Admin.getAinqTotalList");
		
	}
	
	/*
	 * 사용자 상품문의 글 리스트 받아오기
	 */
	public ArrayList getAinqList(){
		return (ArrayList) sqlSession.selectList("Admin.getAinqList");
	}
	
	/*
	 * 관리자가 후기게시판 글 삭제
	 */
	public void AdminPSBoardDelete(int no) {
		sqlSession.update("Admin.AdminPSBoardDelete", no);
	}
	/*
	 * 품절 상품 등록한 사람찾기
	 */
	public ArrayList findPerson(GoodsData data){
		return (ArrayList)sqlSession.selectList("Admin.findPerson",data);
	}
	/*
	 * 품절 상품 등록 시 쪽지 보내기
	 */
	public void sendNotice(MessageData data){
		sqlSession.insert("Admin.sendNotice",data);
	}

	/*
	 * 해당 카테고리 상품의 총 개수 
	 */
	public int getCateGoodsQuantity(String cate){
		return (int)sqlSession.selectOne("Admin.getCateGoodsQuantity", cate);
	}
	
	/*
	 * 풀절 상품을 판매중으로 변경
	 */
	public void updateGoodsStatus(String cate){
		sqlSession.update("Admin.updateGoodsStatus",cate);
	}
	/*
	 * 현재 적용된 배너
	 */
	public ArrayList selectBanner(){
		return (ArrayList)sqlSession.selectList("Admin.selectBanner");
	}
	
	/*
	 * 배너 삭제
	 */
	public void deleteBanner(String savename){
		sqlSession.delete("Admin.deleteBanner",savename);
	}
	
	/*
	 * 재고관리 검색 총 개수
	 */
	public int getSSearchTotalList(String word){
		return (int)sqlSession.selectOne("Admin.getSSearchTotalList", word);
	}
	
	/*
	 * 재고관리 검색 리스트
	 */
	public ArrayList getSSearchList(String word){
		return (ArrayList)sqlSession.selectList("Admin.getSSearchList", word);
	}
	
	
}
