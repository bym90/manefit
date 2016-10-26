package com.manefit.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.manefit.dao.AdminDAO;
import com.manefit.data.BoardData;
import com.manefit.dao.ShoppingDAO;
import com.manefit.data.FAQData;
import com.manefit.data.GoodsData;
import com.manefit.data.ImageData;
import com.manefit.data.MessageData;
import com.manefit.data.OrderData;
import com.manefit.data.ShoppingData;
import com.manefit.util.FileUtil;
import com.manefit.util.PageUtil;
import com.manefit.util.StringUtil;

@Service
public class AdminService {
	
	public static final int LIST = 1;
	public static final int SEARCH = 2;
	public static final int PS = 3;
	public static final int INQ = 4;
	
	
	private static final ArrayList ArrayList = null;
	@Autowired
	private AdminDAO aDAO;
	@Autowired
	private ShoppingDAO sDAO;
	
	
	/*
	 * 베너 이미지 등록
	 */
	public void insertBanner(ShoppingData data, String imagePath,String kind){
		
		String path = imagePath;
		MultipartFile[] temp = data.getUpimage();
		
		for(int i = 0; i < temp.length; i ++){
			String oriName = temp[i].getOriginalFilename();
			
			if(StringUtil.isNull(oriName)){
				continue;
				
			}
			String newName = FileUtil.renameFile(path, oriName);
			File files = new File(path,newName);
			
			try {
				temp[i].transferTo(files);
			} catch (Exception e) {
				
			}
			
			data.setSavename(newName);
			data.setPath(path);
			
				
			aDAO.insertImage(data);	
			
		}
	}
	
	/*
	 * 카테고리 등록하기
	 */
	public void insertCate(String cate,String name,String kind){
		ShoppingData data = new ShoppingData();
		data.setKind(kind);
		data.setCate(cate);
		data.setName(name);
		
		System.out.println(name);
		System.out.println(kind);
		System.out.println(cate);
		
		aDAO.insertCate(data);
	}
	
	/*
	 * 카테고리 가져오기
	 */
	public ArrayList getCate(String cate,String kind){
		ShoppingData data = new ShoppingData();
		
		data.setCate(cate);
		data.setKind(kind);
				
		return aDAO.getCate(data);
	}
	
	/*
	 * 상품 등록하기
	 */
	public void insertGoods(GoodsData data, String ImagePath){
		String path = ImagePath;
		MultipartFile temp = data.getMainimage();
		
			String oriName = temp.getOriginalFilename();
					
			String newName = FileUtil.renameFile(path, oriName);
			File files = new File(path,newName);
			
			try {
				temp.transferTo(files);
			} catch (Exception e) {
				
			}
			
			data.setSavename(newName);
			
			aDAO.insertGoods(data);
			
		
		}
	
	/*
	 * 중복된 상품 제외하기 위한 갯수
	 */
	public int notInsertGoods(String scate1) {
		return aDAO.notInsertGoods(scate1); 
	}
	
	/*
	 * 상품 이미지 등록하기
	 */
	public void insertGoodsImage(ImageData data,String ImagePath, String kind){
		String path = ImagePath;
		MultipartFile[] temp = data.getUpimage();
		
		for(int i = 0; i < temp.length; i ++){
			String oriName = temp[i].getOriginalFilename();
			
			if(StringUtil.isNull(oriName)){
				continue;
			}
			String newName = FileUtil.renameFile(path, oriName);
			File files = new File(path,newName);
			
			try {
				temp[i].transferTo(files);
			} catch (Exception e) {
				
			}
			
			data.setSavename(newName);
			
			if(kind.equals("I")){
				aDAO.insertGoodsImage(data);
			}
			else if(kind.equals("S")){
				// 서브 이미지 변경
				aDAO.changeSubImage(data);
			}
			else{
				// 메인 이미지 변경
				aDAO.changeMainImage(data);
			}
	}
}
	
	/*
	 * 메인이미지 가져오기
	 */
	public String getMainImage(String cate){
		return aDAO.getMainImage(cate);
	}
	
	
	/*
	 * 재고관리 총 페이지 수 구하기
	 */
	public PageUtil getSPageInfo(int nowPage, int kind, String index, String word, OrderData data) {
		OrderData idata = new OrderData();
		idata.setIndex(index);
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = aDAO.getSTotalList(idata);
		}
		else if(kind == SEARCH) {
			totalCount = aDAO.getSSearchTotalList(word);
			System.out.println("토탈 카운트"+totalCount);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 10, 5);
		return pInfo;
	}
	
	/*
	 * 재고 목록 가져오기
	 */
	public ArrayList getStockList(PageUtil pInfo, int kind, String index, String word, OrderData data) {
		OrderData idata = new OrderData();
		idata.setIndex(index);
		idata.setWord(word);
		ArrayList list = null;
		if (kind == LIST) {
			list = aDAO.getStockList(idata);
		}
		else if(kind == SEARCH) {
			list = aDAO.getSSearchList(word);
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
	 * 상품 서브 이미지 가져오기
	 */
	public ArrayList getGoodsImage(String cate){
		ArrayList list = aDAO.getGoodsImage(cate);
		return list;
	}
	/*
	 * 상품 사이즈, 색상, 수량 넣기
	 */
	public void insertSCQ(GoodsData data){
		
		//사이즈 ,색상 중복 체크
		ArrayList list = aDAO.checkSCQ(data);
		
			
		if(list.size()==0){
			aDAO.insertSCQ(data);	
		}
		else{
			aDAO.updateSCQ(data);
		}
	}

	/*
	 * 상품 기본정보 수정
	 */
	public void updateGoodsInfo(GoodsData data){
		aDAO.updateGoodsInfo(data);
	}
	
	/*
	 * 상품 사이즈, 색상, 수량 가져오기
	 */
	public ArrayList getSCQ(String cate){
		
		return aDAO.getSCQ(cate);
	}
	
	/*
	 * 상품 총 개수 가져오기 
	 */
	public int getTotal(String cate){
		return aDAO.getTotal(cate);
	}
	
	/*
	 * 상풍 수량 변경
	 */
	public void modifyQuantity(GoodsData data){
		aDAO.modifySCQ(data);
	}
	
	/*
	 * 색상 컬러 수량 삭제
	 */
	public void deleteSCQ(GoodsData data){
		aDAO.deleteSCQ(data);
	}

	/*
	 * 주문관리 페이지 정보 구하기
	 */
	public PageUtil getOPageInfo(int nowPage, int kind, OrderData data) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = aDAO.getOTotalList();
		}
		else if(kind == SEARCH) {
//			totalCount = aDAO.getOSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 10, 5);
		return pInfo;
	}
	
	/*
	 * 주문관리 리스트 보기
	 */
	public ArrayList getOrderList(PageUtil pInfo, int kind, OrderData data) {
		ArrayList list = new ArrayList();
		if (kind == LIST) {
		
			
			ArrayList getID = aDAO.getOrderID();
			String[] id = new String[getID.size()];
	
			for(int i = 0; i < getID.size(); i ++){
				id[i] = (String)getID.get(i);
			
			// ID에 해당하는 주문 번호를 알아온다
			ArrayList strOrderCount = sDAO.getOrderTotalList2(id[i]);
					
			int[] orderCount = new int[strOrderCount.size()];
			for(int j = 0; j < strOrderCount.size(); j++){
											
				orderCount[j] = (int)strOrderCount.get(j); 
			}
					
			for(int j = orderCount.length-1 ; j >= 0; j --){
										
				OrderData Order = new OrderData();
				Order.setId(id[i]);
				Order.setNo(orderCount[j]);
				int count = sDAO.getGroupCount(Order);	
								
				//그룹에 속한 제일 첫번째 상품 가꼬옴
				Order = sDAO.getOrderList(Order);
				
				Order.setTotalcount(count);
				Order.setId(id[i]);
				list.add(Order);
		
				}
			}
		}	
	
				
		else if(kind == SEARCH) {
//			list = aDAO.getOSearchList(data);
		}
	
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
	
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i<list.size()&& i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		
		}
		return resultList;
	}
	
	/*
	 * 운송장 번호 업데이트
	 */
	public void parcelUpdate(int ono, String oparcel){
		OrderData data = new OrderData();
		data.setOno(ono);
		data.setOparcel(oparcel);
		
		aDAO.parcelUpdate(data);
	}
	
	/*
	 * 배송상태 완료로 변경
	 */
	public void ShipUpdate(int ono) {
		aDAO.ShipUpdate(ono);
	}
	
	/*
	 * 유저관리 페이지 정보 구하기
	 */
	public PageUtil getUPageInfo(int nowPage, int kind, OrderData data) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = aDAO.getUTotalList();
		}
		else if(kind == SEARCH) {
//			totalCount = aDAO.getUSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 10, 5);
		return pInfo;
	}
	
	/*
	 * 유저관리 리스트 보기
	 */
	public ArrayList getUserList(PageUtil pInfo, int kind, OrderData data) {
		ArrayList list = null;
		if (kind == LIST) {
			list = aDAO.getUserList();
		}
		else if(kind == SEARCH) {
//			list = aDAO.getUSearchList(data);
		}
		
		System.out.println("유저 사이즈 : "+list.size());
		
		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;
	
		
		ArrayList resultList = new ArrayList();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(list.get(i));
		}
		
		
		return resultList;
	}
	
	/*
	 * 회원 탈퇴 요청
	 */
	public void userWithdraw(String id) {
		aDAO.userWithdraw(id);
	}
	
	/*
	 * 사용자 글 페이지 정보 구하기
	 */
	public PageUtil getABoardPageInfo(int nowPage, int kind, int kind2) {
		
		int totalCount = 0;
		if (kind == LIST) {
			if(kind2 == PS){
				totalCount = aDAO.getApsTotalList();
			} else if(kind2 == INQ){
				totalCount = aDAO.getAinqTotalList();
			}
			
		}
		else if(kind == SEARCH) {
//			totalCount = aDAO.getOSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 10, 5);
		return pInfo;
	}
	
	/*
	 * 사용자 글 리스트 가져오기
	 */
	public ArrayList getABoardList(PageUtil pInfo, int kind, int kind2) {
		ArrayList list = null;
		if (kind == LIST) {
			if(kind2 == PS){
				list = aDAO.getApsList();
			} else if (kind2 == INQ){
				list = aDAO.getAinqList();
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

	/*
	 * 관리자가 후기게시판 글 삭제
	 */
	public void AdminPSBoardDelete(String[] no) {
		for(int i = 0; i < no.length; i ++){
			int CartNo = Integer.parseInt(no[i]);
			
			aDAO.AdminPSBoardDelete(CartNo);;
		}
	}
	
	
	/*
	 * 품절 상품 등록한 사람 찾기
	 */
	public ArrayList findPerson(GoodsData data){
		return aDAO.findPerson(data);
	}
	/*
	 * 품절 상품 등록한 사람에게 쪽지 
	 */
	public void sendNotice(MessageData data){
		aDAO.sendNotice(data);
	}
	
	/*
	 * 해당 카테고리 상품의 총 개수 
	 */
	public int getCateGoodsQuantity(String cate){
		return aDAO.getCateGoodsQuantity(cate);
	}
	
	/*
	 * 품절 상품을 판매중으로 변경
	 */
	public void updateGoodsStatus(String cate){
		aDAO.updateGoodsStatus(cate);
	}
	
	/*
	 * 현재 적용된 배너
	 */
	public ArrayList selectBanner(){
		return aDAO.selectBanner();
	}
	
	/*
	 * 배너 삭제
	 */
	public void deleteBanner(String savename){
		aDAO.deleteBanner(savename);
	}
}
