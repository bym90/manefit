package com.manefit.www.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.manefit.data.BoardData;
import com.manefit.data.GoodsData;
import com.manefit.data.ImageData;
import com.manefit.data.MessageData;
import com.manefit.data.ShoppingData;
import com.manefit.service.AdminService;
import com.manefit.service.ShoppingService;
import com.manefit.util.PageUtil;
import com.manefit.util.StringUtil;



@Controller
public class AdminController {
	@Autowired
	private AdminService aService;
	@Autowired
	private ShoppingService sService;
	private static final Logger logger =  LoggerFactory.getLogger(AdminController.class);
	
	
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
	 * 상품 등록 화면
	 */
	@RequestMapping("/Admin/AdminGoodsAdd")
	public ModelAndView AdminGoodsAdd(HttpSession session){
		
		//세션 검사하기
		String id = (String)session.getAttribute("ID");
		String kind = "LC";
		
		ArrayList list = aService.getCate(null,kind);
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("LLIST",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/AdminGoodsAdd");

		return mv;
	}
	
	
	/*
	 * 대 카테고리 등록
	 */
	@RequestMapping("/Admin/AdminLcateAdd")
	public ModelAndView AdminCateAdd(@RequestParam("lcate") String name){
			
		String kind = "LC";
		aService.insertCate(null,name,kind);
		ModelAndView mv = new ModelAndView();
		RedirectView rv  = new RedirectView();
		rv.setUrl("../Admin/AdminGoodsAdd.com");
		mv.setView(rv);		
		return mv;
	}
	
	/*
	 * 중 카테고리 등록
	 */
	@RequestMapping("/Admin/AdminMcateAdd")
		public ModelAndView AdminMcateAdd(HttpServletRequest req){
		
		String cate = req.getParameter("lcate1");
		String name = req.getParameter("mcate");
		String kind = "MC";
		aService.insertCate(cate,name,kind);
		ModelAndView mv = new ModelAndView();
		RedirectView rv  = new RedirectView();
		rv.setUrl("../Admin/AdminGoodsAdd.com");
		mv.setView(rv);		
		return mv;
		
	}
	
	/*
	 * 소 카테고리 등록
	 */
	@RequestMapping("/Admin/AdminScateAdd")
	public ModelAndView AdminScateAdd(HttpServletRequest req){
		
		String cate = req.getParameter("mcate2");
		String name = req.getParameter("scate");
		String kind = "SC";
		
		aService.insertCate(cate,name,kind);
		ModelAndView mv = new ModelAndView();
		RedirectView rv  = new RedirectView();
		rv.setUrl("../Admin/AdminGoodsAdd.com");
		mv.setView(rv);		
		return mv;
	}
	
	/*
	 * 중카테 검색
	 */

	@RequestMapping("/Admin/AdminGetMcate")
	public ModelAndView AdminGetMcate(HttpServletRequest req){
		
		String cate = req.getParameter("lcate2");
		if(cate == null){
			cate = req.getParameter("lcate3");
		}
		
		String kind = "MSC";
		ArrayList list = aService.getCate(cate, kind);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("MLIST", list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/McateList");	
		return mv;
	}
	
	/*
	 * 소 카테 검색
	 */
	@RequestMapping("/Admin/AdminGetScate")
	public ModelAndView AdminGetScate(@RequestParam("mcate1") String cate){
		
		String kind = "MSC";
		ArrayList list =  aService.getCate(cate, kind);
						
		ModelAndView mv = new ModelAndView();
		mv.addObject("SLIST",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/ScateList");	
		return mv;
	}
	
	/*
	 * 상품 등록하기
	 */
	@RequestMapping("/Admin/AdminAddGoods")
	public ModelAndView AdminAddGoods(GoodsData data1, ImageData data2, HttpSession session){
		
		String scate1 = data1.getScate1();
		int goodsCount = aService.notInsertGoods(scate1);
		
		if(goodsCount == 0){
		String	path = session.getServletContext().getRealPath("/resources/img/Goods");
		System.out.println(path);
		aService.insertGoods(data1,path);
		aService.insertGoodsImage(data2,path,"I");
		}
				
		ModelAndView mv = new ModelAndView();
		RedirectView rv  = new RedirectView();
		mv.addObject("GOODSCOUNT", goodsCount);
		rv.setUrl("../Admin/AdminGoodsAdd.com");
		mv.setView(rv);	
		return mv;
	}
	
	/*
	 * 재고관리
	 */
	@RequestMapping("/Admin/AdminStock")
	public ModelAndView AdminStock(HttpServletRequest req){
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String index = req.getParameter("index");
		System.out.println("상태는?"+index);
		if(index == ""){
			index = "0";
		}
		
		System.out.println("후상태는?"+index);
		
		PageUtil pInfo = aService.getSPageInfo(nowPage, aService.LIST, index, null, null);
		ArrayList slist = aService.getStockList(pInfo, aService.LIST, index, null, null);
		
		String kind = "LC";
		ArrayList list = aService.getCate(null, kind);
//		ArrayList list2 = aService.getStockList();		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LLIST",list);
		mv.addObject("PINFO", pInfo);
		mv.addObject("SLIST", slist);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("index", index);
		mv.setViewName("Admin/AdminStock");	
		return mv;
	}
	
	
	/*
	 * 재고품 수정
	 */
	@RequestMapping("/Admin/AdminStockModify")
	public ModelAndView AdminStockModify(HttpServletRequest req, GoodsData data){
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
						
		ModelAndView mv = new ModelAndView();
		String cate = data.getTemp();
		
		// 서브 이미지 가져오기
		ArrayList list = aService.getGoodsImage(cate);
		
		// 상품 사이즈, 색상, 수량  가져오기
		ArrayList list2 = aService.getSCQ(cate);
		
		//	상품 총 개수 가져오기
		int total = aService.getTotal(cate);
						
		mv.addObject("TOTAL",total);
		mv.addObject("LIST",data);
		mv.addObject("LIST2",list2);
		mv.addObject("IMAGE",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("nowPage", nowPage);
		mv.setViewName("Admin/AdminStockModify");	
		return mv;
	}
	
	/*
	 * 상품 사이즈, 색상, 수량 넣기
	 */
	@RequestMapping("/Admin/AdminStockSCQ")
	public ModelAndView AdminStockSCQ(GoodsData data,HttpServletRequest req){
		
		String strkind = req.getParameter("kind");
		int kind = Integer.parseInt(strkind);
		
		String cate = data.getTemp();
		ArrayList list = aService.getGoodsImage(cate);
		
		System.out.println("카인드는 ??"+kind);
				
		ModelAndView mv = new ModelAndView();
		
		if(kind == 0){
			System.out.println("-----------"+data.getPrice());
			System.out.println("-----------"+data.getTemp());
			System.out.println("-----------"+data.getStatus());
			System.out.println("-----------"+data.getSave());
			System.out.println("-----------"+data.getDiscount());
			System.out.println("-----------"+data.getName());
			aService.updateGoodsInfo(data);
		}
		else if(kind == 1){
			GoodsData data2 = new GoodsData();
			// 카테 받기(temp)
			data2.setCate(data.getTemp());
			data2.setColor2(req.getParameter("modifyC")); 
			data2.setSize2(req.getParameter("modifyS")); 
			String strQ = req.getParameter("modifyQ");
			int Quantity = Integer.parseInt(strQ); 
			data2.setQuantity2(Quantity); 
		
			String strQ2 = req.getParameter("modifyQ2");
			int Quantity2 = Integer.parseInt(strQ2); 
			
			System.out.println("수량은 ??" +Quantity2);
			
			// 해당 카테고리 상품의 상태가 풀절일 경우 판매중으로 바꿔주기 
			// 1. 해당 카테고리 상품의 총 개수를 불러 온다. 
			int goodsTotalQ = aService.getCateGoodsQuantity(cate);
					
			// 2. 해당 카태고리 상품의 총 개수가 0이면 상태를 바꾸어 준다.
			if(goodsTotalQ >= 0 && Quantity > 0){
				aService.updateGoodsStatus(cate);
			}
			
			
			if(Quantity2 <= 0){
				// 해당 상품의 수량 0을 찜목록 한 사람들 찾기
				ArrayList personList = aService.findPerson(data2);
				
				System.out.println("카테 : "+data2.getCate()+"   칼라 : "+data2.getColor2()+"    사이즈 : "+data2.getSize2());
				
				System.out.println("수량 0개 추가 한사람 ?? "+personList.size());	
				MessageData Mdata = new MessageData();
				MessageData Mdata2 = new MessageData();
						
							
				String body = "찜 목록에 등록 해 놓으신 "+data.getName()+"상품의 "+data2.getSize2()+"사이즈, "+data2.getColor2()+"색상이 추가 되었습니다.";
				
				Mdata.setBody(body);
				Mdata.setTitle("[ 알림 ]재고 추가");
				
				// 사람들에게 쪽지 보내기 
				for(int i = 0; i < personList.size(); i++){
					Mdata2 = ((MessageData)personList.get(i));
					Mdata.setId(Mdata2.getId());
					Mdata.setCate(cate);
					
						
					System.out.println(Mdata.getId()+"한테 쪽지 보낸다 !! "+i);
					
					aService.sendNotice(Mdata);
				}
				
			}
			aService.modifyQuantity(data2);
		}
		else if(kind == 2){
			GoodsData data2 = new GoodsData();
			data2.setCate(req.getParameter("temp"));
			data2.setColor(req.getParameter("modifyC")); 
			data2.setSize(req.getParameter("modifyS")); 
			aService.deleteSCQ(data2);
		}
		else{
		
		//사이즈 , 색상, 수량 넣기
		aService.insertSCQ(data);
		}
		
		//	상품 총 개수 가져오기
		int total = aService.getTotal(cate);
		
		// 상품 사이즈, 색상, 수량  가져오기
		ArrayList list2 = aService.getSCQ(cate);
		
		mv.addObject("TOTAL",total);
		mv.addObject("LIST",data);
		mv.addObject("LIST2",list2);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("IMAGE",list);
		mv.setViewName("Admin/AdminStockModify");
			
		
		return mv;
	}

	
	/*
	 * 상품 삭제하기
	 */
	@RequestMapping("/Admin/deleteGoods")
	public ModelAndView deleteGoods(HttpServletRequest req){
		
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminStock.com");
		mv.setView(rv);
		return mv;
	}
	
	
	/*
	 * 베너 등록
	 */
	@RequestMapping("/Admin/AdminBannerAdd")
	public ModelAndView ShoppingAdmin(){
		
		ArrayList list = aService.selectBanner();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LIST",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/AdminBannerAdd");
		return mv;
	}
	
	/*
	 * 베너 등록 처리
	 */
	@RequestMapping("/Admin/AdminProc")
	public ModelAndView AdminProc(ShoppingData data,HttpSession session){
		String	path = session.getServletContext().getRealPath("/resources/img");				
		aService.insertBanner(data,path,"B");
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminBannerAdd.com");
		mv.setView(rv);
		return mv;
	}
	// 규영
	
	/*
	 * 재고품 이미지 변경
	 */
	@RequestMapping("/Admin/AdminImageChange")
	public ModelAndView AdminImageChange(GoodsData data1,ImageData data2, HttpSession session, HttpServletRequest req){
						
		ModelAndView mv = new ModelAndView();
		String cate = req.getParameter("temp");
		data2.setScate1(cate);
		
		String	path = session.getServletContext().getRealPath("/resources/img/Goods");
		System.out.println(path);
		//	이미지 변경
		aService.insertGoodsImage(data2,path,data1.getKind());
		
		// 메인 이미지 가져오기
		data1.setSavename(aService.getMainImage(cate));
		
		// 서브 이미지 가져오기
		ArrayList list = aService.getGoodsImage(cate);
		
		// 상품 사이즈, 색상, 수량  가져오기
		ArrayList list2 = aService.getSCQ(cate);
		
		//	상품 총 개수 가져오기
		int total = aService.getTotal(cate);
						
		mv.addObject("TOTAL",total);
		mv.addObject("LIST",data1);
		mv.addObject("LIST2",list2);
		mv.addObject("IMAGE",list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/AdminStockModify");	
		return mv;
	}
	

	/*
	 * 배너 삭제
	 */
	
	@RequestMapping("/Admin/deleteBanner")
	public ModelAndView deleteBanner(ImageData data){
					
		String savename = data.getSavename();
		
		System.out.println("배너 이름 뭐야 "+ savename);
		
		aService.deleteBanner(savename);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminBannerAdd.com");
		mv.setView(rv);
		return mv;
	}
	

	// 영민
	/*
	 * 회원 주문관리 리스트 요청
	 */
	@RequestMapping("/Admin/AdminOrder")
	public ModelAndView AdminOrder(HttpServletRequest req) {
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PageUtil pInfo = aService.getOPageInfo(nowPage, aService.LIST, null);
		ArrayList list = aService.getOrderList(pInfo, aService.LIST, null);
		System.out.println("상품갯수"+ pInfo.getTotalCount());
		ModelAndView mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("LIST", list);
		mv.setViewName("Admin/AdminOrder");
		return mv;
	}
	
	/*
	 * 운송장 부여 창 요청
	 */
	@RequestMapping("/Admin/ParcelForm")
	public ModelAndView ParcelForm(HttpServletRequest req) {
		String strOno = req.getParameter("ono");
		int ono = Integer.parseInt(strOno);
		String nowPage = req.getParameter("nowPage");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("nowPage", nowPage);
		mv.addObject("ono", ono);
		return mv;
	}

	/*
	 * 운송장 번호 부여
	 */
	@RequestMapping("/Admin/ParcelUpdate")
	public ModelAndView ParcelUpdate(HttpServletRequest req) {
		String strOno = req.getParameter("ono");
		int ono = Integer.parseInt(strOno);
		String oparcel = req.getParameter("oparcel");
		String nowPage= req.getParameter("nowPage");
		
		aService.parcelUpdate(ono, oparcel);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminOrder.com");
		mv.addObject("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 배송상태 완료로 바꾸기
	 */
	@RequestMapping("/Admin/ShipUpdate")
	public ModelAndView ShipUpdate(HttpServletRequest req) {
		String strOno = req.getParameter("ono");
		int ono = Integer.parseInt(strOno);
		
		aService.ShipUpdate(ono);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminOrder.com");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 고객 리스트 요청
	 */
	@RequestMapping("/Admin/AdminUser")
	public ModelAndView AdminUser(HttpServletRequest req) {
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PageUtil pInfo = aService.getUPageInfo(nowPage, aService.LIST, null);
		ArrayList list = aService.getUserList(pInfo, aService.LIST, null);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("Admin/AdminUser");
		return mv;
	}
	
	/*
	 * 회원 탈퇴 요청
	 */
	@RequestMapping("/Admin/UserWithdraw")
	public ModelAndView UserWithdraw(HttpServletRequest req) {
		String id = req.getParameter("id");
		String nowPage = req.getParameter("nowPage");
		aService.userWithdraw(id);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminUser.com");
		mv.addObject("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 사용자 글 모아보기
	 */
	@RequestMapping("/Admin/AdminBoard")
	public ModelAndView AdminBoard(HttpServletRequest req) {
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
		
		PageUtil pInfoPS = aService.getABoardPageInfo(nowPage1, aService.LIST, aService.PS);
		ArrayList listPS = aService.getABoardList(pInfoPS, aService.LIST, aService.PS);
		
		PageUtil pInfoInq = aService.getABoardPageInfo(nowPage2, aService.LIST, aService.INQ);
		ArrayList listInq= aService.getABoardList(pInfoInq, aService.LIST, aService.INQ);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("PPINFO", pInfoPS);
		mv.addObject("PLIST", listPS);
		mv.addObject("IPINFO", pInfoInq);
		mv.addObject("ILIST", listInq);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/Admin/AdminBoard");
		return mv;
	}
	
	/*
	 * 관리자 후기게시판 삭제
	 */
	@RequestMapping("/Admin/AdminPSBoardDelete")
	public ModelAndView AdminPSBoardDelete(HttpServletRequest req, BoardData data) {
//		String[] body1 = data.getBody1();
//		String[] id1 = data.getId1();
		
		String[] no = data.getPscheck();
		aService.AdminPSBoardDelete(no);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../Admin/AdminBoard.com");
		mv.setView(rv);
		return mv;
	}

	
	@RequestMapping("/Admin/AdminStockSearch")
	public ModelAndView adminStockSearch(HttpServletRequest req){
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		String word = req.getParameter("word");
		System.out.println("워드가 뭐니" + word);
		
		PageUtil pInfo = aService.getSPageInfo(nowPage, aService.SEARCH, null, word, null);
		ArrayList slist = aService.getStockList(pInfo, aService.SEARCH, null, word, null);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("SLIST", slist);
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());

		mv.setViewName("Admin/AdminStockSearch");	
		return mv;
	}
	
}
