package com.manefit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manefit.dao.MyPageDAO;
import com.manefit.data.FAQData;
import com.manefit.data.MessageData;
import com.manefit.data.OrderData;
import com.manefit.util.PageUtil;

@Service
public class MyPageService {
	
	@Autowired
	private MyPageDAO mDAO;
	
	public static final int LIST = 1;
	public static final int SEARCH = 2;
	
	public static final int PS = 3;
	public static final int INQ = 4;
	
	/* 
	 * 장바구니 담기
	 */
	public void insertCart(OrderData data) {
		mDAO.insertCart(data);
	}
	
	/*
	 * 장바구니 리스트 요청
	 */
	public ArrayList getCartList(PageUtil pInfo, int kind, FAQData data, String id) {
		ArrayList list = null;
		if (kind == LIST) {
			list = mDAO.getCartList(id);
		}
		else if(kind == SEARCH) {
//			list = mDAO.getCartSearchList(data, id);
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
	 * 장바구니 페이지 정보 구하기
	 */
	public PageUtil getCPageInfo(int nowPage, int kind, FAQData data, String id) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = mDAO.getCartTotalList(id);
		}
		else if(kind == SEARCH) {
//			totalCount = mDAO.getCartSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/* 
	 * 찜목록 담기
	 */
	public void insertFavor(OrderData data) {
		mDAO.insertFavor(data);
	}
	
	/*
	 * 찜목록 리스트 요청
	 */
	public ArrayList getFavorList(PageUtil pInfo, int kind, FAQData data, String id) {
		ArrayList list = null;
		if (kind == LIST) {
			list = mDAO.getFavorList(id);
		}
		else if(kind == SEARCH) {
//			list = mDAO.getFavorSearchList(data, id);
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
	 * 장바구니 페이지 정보 구하기
	 */
	public PageUtil getFPageInfo(int nowPage, int kind, FAQData data, String id) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = mDAO.getFavorTotalList(id);
		}
		else if(kind == SEARCH) {
//			totalCount = mDAO.getFavorSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * 장바구니 선택된 목록 삭제
	 */
	public void deleteCartList(String[] no){
		for(int i = 0; i < no.length; i ++){
			int CartNo = Integer.parseInt(no[i]);
			
			mDAO.deleteCartList(CartNo);
		}
	}
	/*
	 * 찜 목록 삭제
	 */
	public void deleteWishList(String[] no){
		for(int i = 0; i < no.length; i ++){
			int WishNo = Integer.parseInt(no[i]);
			
			mDAO.deleteWishList(WishNo);
		}
	}
	/*
	 * 쪽지함 페이지 정보 구하기
	 */
	public PageUtil getMsPageInfo(int nowPage, String id) {
		
		int totalCount = mDAO.getMsTotalList(id);
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * 쪽지함 페이지 리스트 구하기
	 */
	public ArrayList getMsList(PageUtil pInfo, String id) {
		ArrayList list = mDAO.getMsList(id);
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}
	
	/*
	 * 새글 읽은글로 업데이트
	 */
	public void readMeg(int reno) {
		mDAO.readMeg(reno);
	}
	
	/*
	 * 번호에 해당하는 쪽지정보 가져오기
	 */
	
	public MessageData megInfo(int reno) {
		MessageData data = mDAO.megInfo(reno);
		return data;
	}
	
	/*
	 * 새메세지 총 갯수
	 */
	public int newMeg(String id) {
		return mDAO.newMeg(id);
	}
	
	/*
	 * 내가쓴글 페이지 정보 구하기
	 */
	public PageUtil getMyBoardPageInfo(int nowPage, int kind, int kind2, String id) {
		
		int totalCount = 0;
		if (kind == LIST) {
			if(kind2 == PS){
				totalCount = mDAO.getMypsTotalList(id);
			} else if(kind2 == INQ){
				totalCount = mDAO.getMyinqTotalList(id);
			}
			
		}
		else if(kind == SEARCH) {
//			totalCount = aDAO.getOSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * 내가 쓴글 리스트 가져오기
	 */
	public ArrayList getMyBoardList(PageUtil pInfo, int kind, int kind2, String id) {
		ArrayList list = null;
		if (kind == LIST) {
			if(kind2 == PS){
				list = mDAO.getMypsList(id);
			} else if (kind2 == INQ){
				list = mDAO.getMyinqList(id);
			}
		}
		else if(kind == SEARCH) {
//			list = aDAO.getOSearchList(data);
		}
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		return resultList;
	}
}
