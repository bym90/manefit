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
import org.springframework.web.servlet.view.RedirectView;

import com.manefit.data.GoodsData;
import com.manefit.data.LoginData;
import com.manefit.data.OrderData;
import com.manefit.data.ShoppingData;
import com.manefit.service.MyPageService;
import com.manefit.service.ShoppingService;
import com.manefit.util.PageUtil;
import com.manefit.util.StringUtil;


@Controller
public class ShoppingmallController {
	
	@Autowired
	private ShoppingService sService;
	@Autowired
	private MyPageService mService;
	private static final Logger logger =  LoggerFactory.getLogger(ShoppingmallController.class);
	
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
	 * 메인
	 */
	@RequestMapping("/Shopping/Shopping")
	public ModelAndView Shopping(HttpSession session){
		
		String id = (String)session.getAttribute("ID");
		String name = (String) session.getAttribute("NAME");
		String grade = (String) session.getAttribute("GRADE");
		System.out.println("등급이 뭐니" +  grade);
		ArrayList list = sService.getImage();
		
		ModelAndView mv = new ModelAndView();
		
		
		//사용자의 관심 스타일을 가져온다
		if(id != null && id != ""){
		LoginData data = sService.getStyle(id);
		ArrayList style = sService.getGoodsList(sService.PREFER,data);	
		mv.addObject("STYLE",style); 
		}

		//New 상품 목록
		ArrayList newGoods = sService.getGoodsList(sService.NEW, null);
				
		//베스트 상품 목록
		ArrayList best = sService.getGoodsList(sService.BEST, null);
		
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("NAME", name);
		mv.addObject("GRADE", grade);
		mv.addObject("BEST",best);
		mv.addObject("NEW",newGoods);
		mv.setViewName("Shopping/ShoppingMain");
		mv.addObject("LIST",list);
		return mv;
	}
	
	/*
	 * New & Best List
	 */
	@RequestMapping("/Shopping/NewAndBest")
	public ModelAndView newAndBest(HttpServletRequest req){
		
		String kind = req.getParameter("kind");
		ModelAndView mv = new ModelAndView();
				
		if(kind.equals("New")){
			ArrayList list = sService.getNewAndBest(sService.N);
			mv.addObject("LIST",list);
		
			
		}
		else{
			ArrayList list = sService.getNewAndBest(sService.B);
			mv.addObject("LIST",list);
	
		}
		
		//상품 모든 색상 가져오기
		ArrayList color = sService.getAllcateColor();
		mv.setViewName("Shopping/NewAndBest");
		mv.addObject("KIND",kind);
		mv.addObject("COLOR",color);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		return mv;
	}
	
	/*
	 * 제품 상세보기
	 */
	@RequestMapping("/Shopping/ShoppingGoodsView")
	public ModelAndView shoppingGoodsView(HttpServletRequest req, HttpSession session){
		String scroll = req.getParameter("scroll");
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
		
		String cate = req.getParameter("cate");
	
		GoodsData info = sService.getGoodsInfo(cate);
		
	
		ArrayList size = sService.getGoodsSize(cate);
		ArrayList color = sService.getGoodsColor(cate);
		ArrayList image = sService.getGoodsImage(cate);
		
		String name = (String) session.getAttribute("NAME");
		
		PageUtil pInfo1 = sService.getBoardPageInfo(nowPage1, sService.PS, null, cate);
		ArrayList list1 = sService.getBoardList(pInfo1, sService.PS, null, cate);
		System.out.println(pInfo1.getTotalCount()+"::토탈카운트");
		
		int avgScore = (int) sService.avgScore(cate);
//		System.out.println(kk);
//		double temp = Math.round(sService.avgScore(cate) * 100);
//		double avgScore = temp / 100.0;
		System.out.println("평점은?" +  avgScore);
		
		PageUtil pInfo2 = sService.getBoardPageInfo(nowPage2, sService.INQ, null, cate);
		ArrayList list2 = sService.getBoardList(pInfo2, sService.INQ, null, cate);
		System.out.println(pInfo2.getNowPage()+"::문의 나우페이지");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("scroll", scroll);
		mv.addObject("NAME", name);
		mv.addObject("AVG", avgScore);
		mv.addObject("INFO",info);
		mv.addObject("SIZE",size);
		mv.addObject("COLOR",color);
		mv.addObject("IMAGE",image);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO1", pInfo1);
		mv.addObject("LIST1", list1);
		mv.addObject("PINFO2", pInfo2);
		mv.addObject("LIST2", list2);
		mv.setViewName("Shopping/ShoppingGoodsView");
		return mv;
	}
	
	/*
	 * 장바구니 담기
	 */
	@RequestMapping("/Shopping/ShoppingInsertCart")
	public ModelAndView shoppingInsertCart(OrderData data,HttpSession session, HttpServletRequest req){
		
		String id = (String) session.getAttribute("ID");
		String[] strPrice = data.getPrice();
		String[] strColor = data.getColor();			 
		String[] strSize = data.getSize();
		int[] strQuantity = data.getQuantity();
	
				
		for (int i = 0; i < strPrice.length; i++) {
			
			OrderData data2 = new OrderData();

			data2.setColor2(strColor[i]);
			data2.setPrice2(strPrice[i]);
			data2.setSize2(strSize[i]);
			data2.setQuantity2(strQuantity[i]);
			data2.setName(data.getName());
			data2.setSavename(data.getSavename());
			data2.setId(id);
			data2.setCate(data.getCate());
		
			int kind = data.getKind();
			if(kind != 0){
				// 장바구니에 담기
				if(kind == 1){
		
					sService.insertCart(data2);
				}
				// 찜 목록에 담기
				else{

					mService.insertFavor(data2);
				}
			}
			
		}

		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		mv.addObject("cate",data.getCate());
		rv.setUrl("../Shopping/ShoppingGoodsView");
		mv.setView(rv);
	
		return mv;
	}
	
	
	
	/*
	 * 제품 리스트
	 */
//	@RequestMapping("/Shopping/ShoppingGoodsList")
//	public ModelAndView OutperList(HttpServletRequest req){
//		
//		String Lcate = req.getParameter("lcate");
//		String Mcate = req.getParameter("mcate");
//		String color = req.getParameter("getcolor");
//		String tema = req.getParameter("tema");
//
//		ArrayList colorList = sService.getGoodsColor(tema);
//		
//		ArrayList goodsList = new ArrayList();
//		boolean check = true;
//		
//		if(Mcate == null|Mcate.length()==0 && color == null|color.length()==0 && tema ==null | tema.length()==0){
//			//대분류 검색 
//			goodsList = sService.getLcateList(Lcate);
//		}
//		else if(color == null|color.length()==0 && tema ==null | tema.length()==0){
//			//중붕류 검색
//			goodsList = sService.getMcateList(Mcate);
//		}
//		else if (Mcate == null|Mcate.length()==0 && color == null|color.length()==0 && tema !=null | tema.length()!=0 ){
//			// 대분류에서 상품 태마 검색
//			String kind = "LLIST";
//			goodsList = sService.getGoodsTemaList(Lcate, tema, kind);
//			if(goodsList.size()==0){
//				check = false;
//			}
//		}
//		else if (tema !=null | tema.length()!=0 && color == null|color.length()==0){
//			// 중 분류에서 상품 태마 검색 
//			String kind = "MLIST";
//			goodsList = sService.getGoodsTemaList(Mcate, tema, kind);
//			if(goodsList.size()==0){
//				check = false;
//			}
//		}
//		else if(Mcate == null | Mcate.length()==0 && color != null|color.length()!=0 && tema ==null | tema.length()==0){
//			//대분류에서 칼라 검색
//			String kind = "LLIST";
//			goodsList = sService.getGoodsColorList(Lcate, color, kind);
//			if(goodsList.size()==0){
//				check = false;
//			}
//		}
//		else{
//			//중분류에서 칼라 검색
//			String kind = "MLIST";
//			goodsList = sService.getGoodsColorList(Mcate, color, kind);
//			if(goodsList.size()==0){
//				check = false;
//			}
//		}
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("L",Lcate);
//		mv.addObject("M",Mcate);
//		mv.addObject("LCATE",getLcate());
//		mv.addObject("MCATE",getMcate());
//		mv.addObject("GLIST",goodsList);
//		mv.addObject("CHECK",check);
//		mv.addObject("COLOR",color);
//		mv.setViewName("Shopping/ShoppingGoodsList");
//		return mv;
//	}
	
	@RequestMapping("/Shopping/ShoppingGoodsList")
	public ModelAndView shoppingGoodsList(HttpServletRequest req){

		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String Lcate = req.getParameter("lcate");
		String Mcate = req.getParameter("mcate");
		String color = req.getParameter("getcolor");
		String tema = req.getParameter("tema");
		String sort = req.getParameter("sort");

		System.out.println("소트 ::"+sort);
		System.out.println("대분류::" + Lcate);
		System.out.println("중분류::" + Mcate);
		System.out.println("칼라::" + color);
		System.out.println("테마::" + tema);
//		ArrayList colorList = sService.getGoodsColor(tema);
		
		ArrayList goodsList = new ArrayList();
		ArrayList colorList = new ArrayList();
		ArrayList sizeList = new ArrayList();
		
		PageUtil pInfo;
		
		boolean check = true;
		
		if(Mcate == null|Mcate.length()==0 && color == null|color.length()==0 && tema ==null 
				| tema.length()==0 && sort == null|sort.length()==0){
			//대분류 검색
			pInfo = sService.getLcateInfo(Lcate, nowPage);
			goodsList = sService.getLcateList(Lcate, pInfo);
			colorList = sService.getLcateColor(Lcate, pInfo, sService.LCATE);
		}
		else if(color == null|color.length()==0 && tema ==null | tema.length()==0 && sort == null|sort.length()==0){
			//중분류 검색
			pInfo = sService.getMcateInfo(Mcate, nowPage);
			goodsList = sService.getMcateList(Mcate, pInfo);
			colorList = sService.getLcateColor(Mcate, pInfo, sService.MCATE);
		}
		else if (Mcate == null|Mcate.length()==0 && color == null|color.length()==0 && tema !=null 
				| tema.length()!=0 && sort == null|sort.length()==0){
			// 대분류에서 상품 태마 검색
			String kind = "LLIST";
			pInfo = sService.getTemaInfo(Lcate, tema, kind, nowPage);
			goodsList = sService.getGoodsTemaList(Lcate, tema, kind, pInfo);
			colorList = sService.getLcateColor(Lcate, pInfo, sService.LCATE);
		}
		
		else if (tema !=null | tema.length()!=0 && color == null|color.length()==0 && sort == null|sort.length()==0){
			// 중 분류에서 상품 태마 검색 
			String kind = "MLIST";
			pInfo = sService.getTemaInfo(Mcate, tema, kind, nowPage);
			goodsList = sService.getGoodsTemaList(Mcate, tema, kind, pInfo);
			colorList = sService.getLcateColor(Mcate, pInfo, sService.MCATE);
	
		}
		
		else if(Mcate == null | Mcate.length()==0 && color != null|color.length()!=0 && tema ==null 
				| tema.length()==0 && sort == null|sort.length()==0){
			//대분류에서 칼라 검색
			String kind = "LLIST";
			pInfo = sService.getColorInfo(Lcate, color, kind, nowPage);
			goodsList = sService.getGoodsColorList(Lcate, color, kind, pInfo);
			colorList = sService.getLcateColor(Lcate, pInfo, sService.LCATE);
	
		}
		
		// 대 분류에서 인기, 가격 순
		else if(Mcate == null|Mcate.length()==0 && color == null|color.length()==0 && tema ==null | tema.length()==0 && sort != null|sort.length()!=0){
			String kind = "LLIST";
			pInfo = sService.getSortInfo(Lcate, kind, nowPage);
			goodsList = sService.getSortList(Lcate, sort, kind, pInfo);
			colorList = sService.getLcateColor(Lcate, pInfo, sService.LCATE);
	
		}
		//	중 분류에서 인기, 가격 순 
		else if( color == null|color.length()==0 && tema ==null | tema.length()==0 && sort != null|sort.length()!=0){
			String kind = "MLIST";
			pInfo = sService.getSortInfo(Lcate, kind, nowPage);
			goodsList = sService.getSortList(Mcate, sort, kind, pInfo);
			colorList = sService.getLcateColor(Mcate, pInfo, sService.MCATE);
	
		}
		else{
			//중분류에서 칼라 검색
			String kind = "MLIST";
			pInfo = sService.getColorInfo(Mcate, color, kind, nowPage);
			goodsList = sService.getGoodsColorList(Mcate, color, kind, pInfo);
			colorList = sService.getLcateColor(Mcate, pInfo, sService.MCATE);
	
		}
		
		if(goodsList.size()==0){
			check = false;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("L",Lcate);
		mv.addObject("M",Mcate);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("GLIST",goodsList);
		mv.addObject("CLIST",colorList);
		mv.addObject("CHECK",check);
		mv.addObject("COLOR",color);
		mv.addObject("TEMA", tema);
		mv.addObject("SORT",sort);
		mv.setViewName("Shopping/ShoppingGoodsList");
		return mv;
	}
	
	
	
	
	
	/*
	 * 규영형
	 */
	
	/*
	 *  색상 클릭시 사이즈 정보 보이기  
	 */
	@RequestMapping("/Shopping/getSizeList")
	public ModelAndView getSizeList(HttpServletRequest req){
		
		String cate = req.getParameter("cate");
		String color = req.getParameter("color");

		ArrayList list = sService.getSize(cate, color);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("COLOR",list);
		mv.setViewName("/Shopping/SizeList");
		return mv;
	}
	
	/*
	 * 색상 별 상품 검색
	 */
	@RequestMapping("/Shopping/getGoodsColor")
	public ModelAndView getGoodsColor(HttpServletRequest req){
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String lcate = req.getParameter("lcate");
		String mcate = req.getParameter("mcate");
		String color = req.getParameter("getcolor");
		
		
		ArrayList goodsList = new ArrayList();
		PageUtil pInfo;
		if(mcate == null | mcate.length()==0){
			String kind = "LList";
			pInfo = sService.getColorInfo(lcate, color, kind, nowPage);
			goodsList = sService.getGoodsColorList(lcate, color, kind, pInfo);
		}
		else{
			String kind = "MList";
			pInfo = sService.getColorInfo(mcate, color, kind, nowPage);
			goodsList = sService.getGoodsColorList(mcate, color, kind, pInfo);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("GLIST",goodsList);
		mv.setViewName("/Shopping/ShoppingGoodsList");
		return mv;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 영민
	 */
	/*
	 * 구입하기 폼 요청
	 */
	@RequestMapping("/Shopping/BuyForm")
	public ModelAndView buyForm(HttpServletRequest req, HttpSession session, OrderData data){
		
		String id = (String) session.getAttribute("ID");
		
		String[] strPrice = data.getPrice();
		String[] strColor = data.getColor();			 
		String[] strSize = data.getSize();
		String[] strName = data.getName2();
		String[] strImage = data.getImage();
		String[] strCate  = data.getCate2();
		int[] strQuantity = data.getQuantity();
		int[] save	= data.getSave3();
		
		//적립금 계산하기
		for(int i = 0; i < save.length; i++){
			double tempSave = save[i] * 0.01; 
			save[i] = (int)Math.round(tempSave * Integer.parseInt(strPrice[i]));
		}
		
		//마일리지 조회하기
		
		int smoney = sService.getSmoney(id);
	
		ArrayList List = new ArrayList();
	
		
		for (int i = 0; i < strPrice.length; i++) {
			
			OrderData data2 = new OrderData();
			
			
			data2.setCate(strCate[i]);
			data2.setColor2(strColor[i]);
			data2.setPrice2(strPrice[i]);
			data2.setSave2(save[i]);
			data2.setSize2(strSize[i]);
			data2.setQuantity2(strQuantity[i]);
			data2.setName(strName[i]);
			data2.setSavename(strImage[i]);
			
						
			List.add(data2);
		
		}
		
		
		LoginData ldata = sService.getUserInfo(id,data.getTotalprice());
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("SMONEY",smoney);
		mv.addObject("DATA", data);
		mv.addObject("DATA2",List);
		mv.addObject("LDATA", ldata);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Shopping/BuyForm");
		return mv;
	}
	
	
	/*
	 * 구입하기 요청
	 */
	@RequestMapping("Shopping/OrderList")
	public ModelAndView buyProc(HttpServletRequest req, HttpSession session, OrderData data) {
	
		String strPage = req.getParameter("nowPage1");
		
	
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
			
		String id = (String) session.getAttribute("ID");
		
			System.out.println("카인드는 머야 ?? "+data.getKind3());
		
		if(data.getKind3() == 1){
		
		}
		else{
		if(data.getName2()!= null){
			
			String pc1 = req.getParameter("oaddr1");
			String addr1 = req.getParameter("oaddr2");
			String oaddr = pc1 + " " +addr1;
			
			String pc2 = req.getParameter("raddr1");
			String addr2 = req.getParameter("raddr2");
			String raddr = pc2 + " " + addr2;
			
			data.setOaddr(oaddr);
			data.setRaddr(raddr);
			data.setId(id);
		}
			
		String cate[] = data.getCate2();
		String image[] = data.getImage();
		String size[] = data.getSize();
		int quantity[] = data.getQuantity();
		String name[] = data.getName2();
		String color[] = data.getColor();
		String price[] = data.getPrice();
		int save[] = data.getSave3();
		
		// 구입테이블에 MAXNO 구하기 
		int MAXNO = sService.getMaxOrderNo(id);
		
		OrderData data2 = new OrderData();
		for(int i = 0; i <data.getName2().length; i ++){
		
			data2.setCate(cate[i]);
			data2.setSavename(image[i]);
			data2.setSize2(size[i]);
			data2.setQuantity2(quantity[i]);
			data2.setName(name[i]);
			data2.setColor2(color[i]);
			data2.setPrice2(price[i]);
			data2.setOname(data.getOname());
			data2.setOaddr(data.getOaddr());
			data2.setOtel(data.getOtel());
			data2.setOemail(data.getOemail());
			data2.setRname(data.getRname());
			data2.setRaddr(data.getRaddr());
			data2.setRtel(data.getRtel());
			data2.setId(data.getId());
			data2.setSave(save[i]);
			data2.setUsemoney(data.getUsemoney());
			data2.setNo(MAXNO);
			data2.setTotalprice(data.getTotalprice());
			
			sService.buyProc(data2);
		}
		
		
		// 상품 재고 수량 확인하기
		for(int i = 0; i < cate.length; i++){
			//카테고리 별로 수량 확인		
			int totalQuantity = sService.quantityConfirmAndUpdate(sService.CONFIRM, cate[i]);

			//상품 전체 수량이 0 이면 상품 상태를 품절로 변경
			if(totalQuantity <= 0){
					
				sService.quantityConfirmAndUpdate(sService.CHANGE, cate[i]);
			}
		}

		}	
		
		// 장바구니에서 구입을 했으면 장바구니 목록을 비워야 한다.
		if(data.getCart()!=null){
			if(data.getCart().equals("1")){
				sService.deleteCartList2(id);
			}
		}
		// 적립금 감소
		if(data.getUsemoney()!=null&data.getUsemoney() != ""){
			if(Integer.parseInt(data.getUsemoney()) != 0){
	
				sService.useSmoney(data);
			}
		}
		
		
		
		// 데이터 총 개수
		PageUtil pInfo = sService.getOPageInfo(nowPage, sService.LIST, null, id);
		// 리스트 정보
		ArrayList list = sService.getOrderList(pInfo, sService.LIST, null, id);
		
		
		
		// 주문 목록이 비었는지 확인
		boolean check = true;
		if(list.size() <= 0 ){
			check = false;
		}
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("CHECK", check);
		// 가격 상품이름 칼라 사이즈 적립금
		mv.setViewName("MyPage/OrderList");
		return mv;
	}
	
	/*
	 * 주문 상품 자세히 보기
	 */
	@RequestMapping("/Shopping/OrderView")
	public ModelAndView orderView(HttpServletRequest req){
		
		String id = req.getParameter("id");
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		OrderData data = new OrderData();
		data.setId(id);
		data.setNo(no);
		
		
		
		//오더리스트 자세히보기 
		ArrayList list = sService.getOrderList2(data);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LIST",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("MyPage/OrderView");
		return mv;
	}
	
	/*
	 * 주문 취소
	 */
	@RequestMapping("/Shopping/cancelOrder") 
	public ModelAndView cancelOrder(HttpServletRequest req){
		
		String strPage = req.getParameter("nowPage1");
		
		
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String id = req.getParameter("id");
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		OrderData data = new OrderData();
		data.setId(id);
		data.setNo(no);
		
		// 주문 취소
		sService.cancelOrder(no);
				
		// 데이터 총 개수
		PageUtil pInfo = sService.getOPageInfo(nowPage, sService.LIST, null, id);
		// 리스트 정보
		ArrayList list = sService.getOrderList(pInfo, sService.LIST, null, id);
		
				
		// 주문 목록이 비었는지 확인
		boolean check = true;
		if(list.size() <= 0 ){
			check = false;
		}
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("CHECK", check);
		// 가격 상품이름 칼라 사이즈 적립금
		mv.setViewName("MyPage/OrderList");
		return mv;
	}
	
	

}
