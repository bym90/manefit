package com.manefit.www.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manefit.data.MessageData;
import com.manefit.data.OrderData;
import com.manefit.data.ShoppingData;
import com.manefit.service.MyPageService;
import com.manefit.service.ShoppingService;
import com.manefit.util.PageUtil;
import com.manefit.util.StringUtil;

@Controller
public class MyPageController {
	
	@Autowired
	private MyPageService mService;
	@Autowired
	private ShoppingService sService;
	
	private static final Logger logger =  LoggerFactory.getLogger(MyPageController.class);

	//메인 바 대 분류 구성 
	public  ArrayList getLcate(){
		ArrayList Lcate = sService.getLcate();
		return  Lcate;
	}
	//메인바 중분류 구성
	public ArrayList getMcate(){
		ArrayList Lcate = sService.getLcate();
		ArrayList Mcate = new ArrayList();
		ShoppingData data = new ShoppingData();
		ShoppingData temp2 = new ShoppingData();
	
		for(int i = 0; i < Lcate.size(); i++){
			data = (ShoppingData) Lcate.get(i);
			String cate = data.getCate();
			ArrayList temp = sService.getMcate(cate);
			for(int j = 0; j < temp.size(); j++){
				temp2 = (ShoppingData) temp.get(j);
				Mcate.add(temp2);
			}			
		}
		return Mcate;
	}
	
	
	
	/*
	 * 장바구니 리스트 요청
	 */
	@RequestMapping("/MyPage/CartList")
	public ModelAndView cartList(HttpServletRequest req, HttpSession session, OrderData data) {
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		if(data.getCate()!=null){
			//장바구니 선택된거 삭제 하기
			if(data.getCate().equals("1")){
				String[] no = data.getCheck();
					mService.deleteCartList(no);
			}
		}
		
		String id = (String)session.getAttribute("ID");
		// 데이터 총 개수
		PageUtil pInfo = mService.getCPageInfo(nowPage, mService.LIST, null, id);
		// 리스트 정보
		ArrayList list = mService.getCartList(pInfo, mService.LIST, null, id);
		
		// 장바구니가 비었는지 확인
		boolean check = true;
		if(list.size() <= 0 ){
			check = false;
		}
		
		int totalPrice = 0; 
		for(int i = 0; i < list.size(); i ++){
			OrderData data2 = new OrderData();
			data2 = (OrderData) list.get(i);
			totalPrice = totalPrice + Integer.parseInt(data2.getPrice2());
		}
		
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("TOTALPRICE",totalPrice);
		mv.addObject("LIST", list);
		mv.addObject("PINFO", pInfo);
		mv.addObject("CHECK",check);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/MyPage/CartList");
		return mv;
	}
	
	/*
	 * 찜목록
	 */
	@RequestMapping("/MyPage/Favorite")
	public ModelAndView favoriteList(HttpServletRequest req, HttpSession session, OrderData data) {
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		if(data.getName()!= null){
			mService.insertFavor(data);
		}
		if(data.getCate()!=null){
			//찜 목록 선택된거 삭제 하기
			if(data.getCate().equals("2")){
		
				String[] no = data.getCheck();
			
					mService.deleteWishList(no); 
			}
		}
		
		String id = (String)session.getAttribute("ID");
		// 데이터 총 개수
		PageUtil pInfo = mService.getFPageInfo(nowPage, mService.LIST, null, id);
		// 리스트 정보
		ArrayList list = mService.getFavorList(pInfo, mService.LIST, null, id);
			
		// 찜목록이 비었는지 확인
		boolean check = true;
		if(list.size() <= 0 ){
			check = false;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LIST", list);
		mv.addObject("PINFO", pInfo);
		mv.addObject("CHECK", check);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/MyPage/FavoriteList");
		return mv;
	}
	
	/*
	 * 메세지 리스트 요청
	 */
	@RequestMapping("/MyPage/Message")
	public ModelAndView messageList(HttpServletRequest req, HttpSession session) {
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		String id = (String) session.getAttribute("ID");
	
		PageUtil pInfo = mService.getMsPageInfo(nowPage, id);
		ArrayList list = mService.getMsList(pInfo, id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/MyPage/Message");
		return mv;
	}
	
	/*
	 * 상품문의 상세보기
	 */
	@RequestMapping("/MyPage/InqMeg")
	public ModelAndView inqView(HttpServletRequest req, HttpSession session) {
		String strNo = req.getParameter("no");
		String strPage = req.getParameter("nowPage");
		int no = Integer.parseInt(strNo);
		int nowPage = Integer.parseInt(strPage);
		String id = (String) session.getAttribute("ID");
		
		// newletter 업데이트 0으로
		mService.readMeg(no);
		
		int newCount = mService.newMeg(id);
		if(newCount == 0){
			session.removeAttribute("MEG");
		}
		
		MessageData data = mService.megInfo(no);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("INQVIEW", data);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/MyPage/InqMeg");
		return mv;
	}
	
	/*
	 * 내가 쓴글 모아보기 리스트 요청
	 */
	@RequestMapping("/MyPage/MyBoard")
	public ModelAndView myLetter(HttpServletRequest req, HttpSession session) {
		String strPage1 = req.getParameter("nowPage1");
		int nowPage1 = 1;
		if (!StringUtil.isNull(strPage1)) {
			nowPage1 = Integer.parseInt(strPage1);
		}
		
		String strPage2 = req.getParameter("nowPage2");
		int nowPage2 = 1;
		if (!StringUtil.isNull(strPage2)) {
			nowPage2 = Integer.parseInt(strPage2);
		}
		String id = (String) session.getAttribute("ID");
		
		PageUtil pInfoPS = mService.getMyBoardPageInfo(nowPage1, mService.LIST, mService.PS, id);
		ArrayList listPS = mService.getMyBoardList(pInfoPS, mService.LIST, mService.PS, id);
		
		PageUtil pInfoInq = mService.getMyBoardPageInfo(nowPage2, mService.LIST, mService.INQ, id);
		ArrayList listInq= mService.getMyBoardList(pInfoInq, mService.LIST, mService.INQ, id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("PPINFO", pInfoPS);
		mv.addObject("PLIST", listPS);
		mv.addObject("IPINFO", pInfoInq);
		mv.addObject("ILIST", listInq);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/MyPage/MyBoard");
		return mv;
	}
	
}
