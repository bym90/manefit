package com.manefit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manefit.dao.ShoppingDAO;
import com.manefit.data.BoardData;
import com.manefit.data.FAQData;
import com.manefit.data.GoodsData;
import com.manefit.data.LoginData;
import com.manefit.data.OrderData;
import com.manefit.data.ShoppingData;
import com.manefit.util.PageUtil;




@Service
public class ShoppingService {

	@Autowired
	private  ShoppingDAO sDAO; 
	
	public static final int LIST = 1;
	public static final int SEARCH = 2;
	public static final int PS = 3;
	public static final int INQ = 4;
	public static final int DETAIL = 5;
	public static final int LCATE = 6;
	public static final int MCATE = 7;
	public static final int LTEMA = 8;
	public static final int MTEMA	= 9;
	public static final int LCOLOR = 10;
	public static final int MCOLOR = 11;

	//메인  화면 취향저격 슬라이드 이미지
	public static final int PREFER = 10;
	//메인화면 신상품 슬라이드 이미지
	public static final int NEW = 11;
	// 메인화면 배스트상품 이미지
	public static final int BEST = 12;
	
	public static final String N = "NEW";
	public static final String B = "BEST";
	
	
	// 재고확인 
	public static final int CONFIRM = 20;
	// 상품 상태 변경
	public static final int  CHANGE = 21;
	
	
	/*
	 * 로그인 여부 확인
	 */
	
	
	/*
	 * 베너 이미지 가져오기
	 */
	public ArrayList getImage(){
		
		ArrayList list = new ArrayList(); 
		
		list = sDAO.getImage();
		
		return list;
		
	}

	/*
	 * 메뉴바 대 분류 구성
	 */
	public  ArrayList getLcate(){
		return sDAO.getLcate();
	}
	/*
	 * 메뉴바 중 분류 구성
	 */
	public ArrayList getMcate(String cate){
		return sDAO.getMcate(cate);
	}

	
	/*
	 * New & Best List
	 */
	public ArrayList getNewAndBest(String kind){
		return sDAO.getNewAndBest(kind);
	}
	
	
	/*
	 * 규영형
	 */
	/*
	 * 대 분류 선택시 총 개수 가져오기
	 */
	public PageUtil getLcateInfo(String cate, int nowPage){
		int totalCount = sDAO.getLcateInfo(cate);
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 20, 5);
		return pInfo;
	}
	
	/*
	 *	대 분류 선택 시 상품 목록 가져오기 
	 */
	public ArrayList getLcateList(String cate, PageUtil pInfo){
		ArrayList list = sDAO.getLcateList(cate);
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
				
	}
	
	/*
	 * 중 분류 선택시 총 개수 가져오기
	 */
	public PageUtil getMcateInfo(String cate, int nowPage){
		int totalCount = sDAO.getMcateInfo(cate);
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 20, 5);
		return pInfo;
	}
	
	/*	
	 *	중 분류 선택 시 상품 목록 가져오기 
	 */
	public ArrayList getMcateList(String cate, PageUtil pInfo){
		ArrayList list = sDAO.getMcateList(cate);
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
				
	}
	
	/*
	 *  분류 선택시 상품별 칼라 가져오기
	 */
	public ArrayList getLcateColor(String cate, PageUtil pInfo,int kind){
		
		ArrayList list = new ArrayList();
		
		if(kind == LCATE){
			list = sDAO.getLcateColor(cate);
		}
		else{
			list = sDAO.getMcateColor(cate);	
		}
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i<list.size()&&i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return list;
				
	}
	

	
	/*
	 * 	상품 색상 개수 알아내기
	 */
	public ArrayList getColorCount(String cate){
		return sDAO.getColorCount(cate);
	}
	
	
	/*
	 *  상품 상세보기 정보
	 */
	public GoodsData getGoodsInfo(String cate){
		return sDAO.getGoodsInfo(cate);
	}
	
	/*
	 * 상품 상세보기 사이즈 구성 
	 */
	public ArrayList getGoodsSize(String cate){
		return sDAO.getGoodsSize(cate);
	}
	
	/*
	 * 상품 상세보기 색상 구성
	 */
	public ArrayList getGoodsColor(String cate){
		return sDAO.getGoodsColor(cate);
	}
	
	/*
	 * 상품 상세보기 이미지 알아보기
	 */
	public ArrayList getGoodsImage(String cate){
		return sDAO.getGoodsImage(cate);
	}
	

	/*
	 * 아작스 사이즈 가져오기
	 */
	public ArrayList getSize(String cate, String color){
		GoodsData data = new GoodsData();
		data.setColor(color);
		data.setCate(cate);
		
		return sDAO.getSize(data);
	}
	
	/*
	 * 색상별 상품 개수 구하기
	 */
	public PageUtil getColorInfo(String cate, String color, String kind, int nowPage){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setColor(color);
		data.setKind(kind);
		int totalCount = sDAO.getColorInfo(data);
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 20, 5);
		return pInfo;
	}
	
	/*
	 * 색상별 상품 조회
	 */
	public ArrayList getGoodsColorList(String cate, String color, String kind, PageUtil pInfo){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setColor(color);
		data.setKind(kind);
		
		ArrayList list = sDAO.getGoodsColorList(data);
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}

	/*
	 * 테마별 상품 개수 구하기
	 */
	public PageUtil getTemaInfo(String cate, String tema, String kind, int nowPage){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setTema(tema);
		data.setKind(kind);
		int totalCount = sDAO.getTemaInfo(data);
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 20, 5);
		return pInfo;
	}
	
	/*
	 * 태마별 상품 조회 
	 */
	public ArrayList getGoodsTemaList(String cate, String tema, String kind, PageUtil pInfo){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setTema(tema);
		data.setKind(kind);
		
		ArrayList list = sDAO.getGoodsTemaList(data);
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}
	
	
	/*
	 * 인기, 가격 순 상품 개수 구하기
	 */
	public PageUtil getSortInfo(String cate, String kind, int nowPage){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setKind(kind);
		int totalCount = sDAO.getSortInfo(data);
		
		System.out.println("인기 가격 상품 개수 "+totalCount);
				
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 20, 5);
		return pInfo;
	}
	
	/*
	 * 인기, 가격 순
	 */
	public ArrayList getSortList(String cate, String sort, String kind, PageUtil pInfo){
		GoodsData data = new GoodsData();
		data.setCate(cate);
		data.setSort(sort);
		data.setKind(kind);
		
		ArrayList list = sDAO.getSortList(data);
		
				
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}
	
	/*
	 * 주문정보 리스트 요청
	 */
	public ArrayList getOrderList(PageUtil pInfo, int kind, FAQData data, String id) {
		ArrayList list = new ArrayList();
		if (kind == LIST) {
				
			
			// ID에 해당하는 주문 번호를 알아온다
			ArrayList strOrderCount = sDAO.getOrderTotalList2(id);
			
			System.out.println("어레이 싸이즈 : "+strOrderCount.size());
			
			int[] orderCount = new int[strOrderCount.size()];
			for(int i = 0; i < strOrderCount.size(); i++){
											
				orderCount[i] = (int)strOrderCount.get(i); 
			}
					
			for(int i = orderCount.length-1 ; i >= 0; i --){
										
				OrderData Order = new OrderData();
				Order.setId(id);
				Order.setNo(orderCount[i]);
				int count = sDAO.getGroupCount(Order);	
								
				//그룹에 속한 제일 첫번째 상품 가꼬옴
				Order = sDAO.getOrderList(Order);
				
				Order.setTotalcount(count);
				Order.setId(id);
				list.add(Order);
			
			}

			
		}
				
		else if(kind == SEARCH) {
//			list = sDAO.getCartSearchList(data, id);
		}
		
//		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
//		int end = start + pInfo.getPageList() - 1;
//		
//		System.out.println("스타트 페이지는 ???" + start);
//		
//		System.out.println("엔드 페이지는 ???" + end);
//		ArrayList resultList = new ArrayList();
//		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
//			resultList.add(list.get(i));
//		}
		return list;
	}

	
	/*
	 * 주문정보 페이지 정보 구하기
	 */
	public PageUtil getOPageInfo(int nowPage, int kind, FAQData data, String id) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = sDAO.getOrderTotalList(id,0);
		}
		else if(kind == SEARCH) {
//			totalCount = sDAO.getCartSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * 주문정보 게시판 리스트 요청
	 */
	public ArrayList getBoardList(PageUtil pInfo, int kind, BoardData data, String cate) {
		ArrayList list = null;
		if (kind == PS) {
			list = sDAO.getPBoardList(cate);
		}
		else if(kind == INQ) {
			list = sDAO.getIBoardList(cate);
		}
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}

	
	/*
	 * 주문정보 게시판 페이지 정보 요청
	 */
	public PageUtil getBoardPageInfo(int nowPage, int kind, BoardData data, String cate) {
		
		int totalCount = 0;
		if (kind == PS) {
			totalCount = sDAO.getPBoardTotalList(cate);
		}
		else if(kind == INQ) {
			totalCount = sDAO.getIBoardTotalList(cate);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * 장바구니 담기
	 */
	public void insertCart(OrderData data){
		sDAO.insertCart(data);
	}
	/*
	 * 장바구니 목록 가져오기 
	 */
	public ArrayList getCart(String id){
		return sDAO.getCart(id);
	}
	/*
	 * 장바구니 목록 지우기
	 */
	public void deleteCartList2(String id){
		sDAO.deleteCartList2(id);
	}
	
	/*
	 * 사용자 관심 스타일 가져오기
	 */
	public LoginData getStyle(String id){
		return sDAO.getStyle(id);
	}
	

	/*
	 * 관심 스타일 
	 */
	public ArrayList getGoodsList(int kind, LoginData data){
		
		return sDAO.getGoodsList(kind,data);
	}
	/*
	 * 상품 재고확인 and 품절 표시
	 *
	 */
	public int quantityConfirmAndUpdate(int kind, String cate){
		if(kind == CONFIRM){
		return sDAO.quantityConfirmAndUpdate(kind, cate);
		}
		else{
			sDAO.quantityConfirmAndUpdate(kind,cate);
			return 0;
		}
	}
	/*
	 * 주문 테이블 MAXNO 구하기
	 */
	public int getMaxOrderNo(String id){
		return sDAO.getMaxOrderNo(id);
	}
	
	/*
	 * 주문 상세보기
	 */
	public ArrayList getOrderList2(OrderData data){
		return sDAO.getOrderList2(data);
	}
	
	/*
	 * 주문 취소
	 * 
	 */
	public void cancelOrder(int no){
		sDAO.cancelOrder(no);
	}
	
	/*
	 * 모든 상품 컬라 가져오기
	 */
	public ArrayList getAllcateColor(){
		return sDAO.getAllcateColor();
				
	}
	
	
	
	
	
	/*
	 * 영민
	 */
	/*
	 * 적립금 가져오기
	 */
	public int getSmoney(String id) {
		int smoney = sDAO.getSmoney(id);
		return smoney;
	}
	
	/*
	 * 유저 정보 가져오기 
	 */
	public LoginData getUserInfo(String id, int totalPrice) {
		LoginData data = sDAO.getUserInfo(id);
		
		if(data.getGrade().equals("3")){
			totalPrice = (int) (totalPrice - (0.05 * totalPrice));
			data.setSavemoney(totalPrice);
			System.out.println("할인 가격 : "+totalPrice);
		}
		else if(data.getGrade().equals("4")){
			totalPrice = (int) (totalPrice - (0.07 * totalPrice));
			data.setSavemoney(totalPrice);
			System.out.println("할인 가격 : "+totalPrice);
		}
		
		
		return data;
	}
	
	/*
	 * 구입 요청
	 * 적립금 적립하기
	 * 구입한 수 만큼 수량 차감
	 */
	public void buyProc(OrderData data) {
		sDAO.buyProc(data);
	}
	/*
	 * 적립금 감소
	 */
	public void useSmoney(OrderData data){
		sDAO.useSmoney(data);
	}
	
	/*
	 * 새메세지 총 갯수
	 */
	public int newMeg(String id) {
		return sDAO.newMeg(id);
	}
	
	/*
	 * 해당하는 카테에 총 점수 뽑아오기
	 */
	public double avgScore(String cate) {
		ArrayList list = sDAO.avgScore(cate);
		int totalScore = 0;
		double avgScore = 0;
		for (int i = 0; i < list.size(); i++) {
			System.out.println("리스트에 뭐가있니?"+list.get(i));
			totalScore += (int) list.get(i);
		}
		avgScore = (double) totalScore / list.size();
		return avgScore;
	}
	

}
