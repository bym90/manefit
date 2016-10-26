package com.manefit.www.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.manefit.data.FAQData;
import com.manefit.data.ShoppingData;
import com.manefit.service.FAQService;
import com.manefit.service.ShoppingService;
import com.manefit.util.PageUtil;
import com.manefit.util.StringUtil;

@Controller
public class FAQController {
	@Autowired
	FAQService fService;
	@Autowired
	ShoppingService sService;
	private static final Logger logger =  LoggerFactory.getLogger(FAQController.class);
	
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
				System.out.println(temp2.getCate());
				Mcate.add(temp2);
			}			
		}
		return Mcate;
	}
	/*
	 * FAQ리스트 보기
	 */
	@RequestMapping("/FAQ/List")
	public ModelAndView list(HttpServletRequest req){
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtil.isNull(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PageUtil pInfo = fService.getPageInfo(nowPage, fService.LIST, null);
		ArrayList list = fService.getList(pInfo, fService.LIST, null);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("FAQ/List");
		return mv;
	}
	
	/*
	 * FAQ글쓰기 폼 요청
	 */
	@RequestMapping("FAQ/WriteForm")
	public ModelAndView WriteForm(HttpServletRequest req, HttpSession session) {
		String path = session.getServletContext().getRealPath("/");
		
		logger.warn("Test......");
		logger.info("경로"+ path);
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("FAQ/WriteForm");
		return mv;
	}
	
	/*
	 * FAQ 글쓰기 요청
	 */
	@RequestMapping("FAQ/WriteProc")
	public ModelAndView WriteProc(HttpServletRequest req) {
		String title = req.getParameter("title");
		String body = req.getParameter("ir1");
		
		FAQData fdata = new FAQData();
		
		fdata.setFtitle(title);
		fdata.setFbody(body);
		
		fService.writeProc(fdata);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		rv.setUrl("../FAQ/List.com");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * FAQ 글쓰기 상세 보기
	 */
	@RequestMapping("FAQ/View")
	public ModelAndView view(HttpServletRequest req){
		String strNo = req.getParameter("no");
		String strPage = req.getParameter("nowPage");
		int fno = Integer.parseInt(strNo);
		int nowPage = Integer.parseInt(strPage);
		
		FAQData data = fService.getView(fno);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("DATA", data);
		mv.setViewName("FAQ/View");
		return mv;
	}
	
	/*
	 * FAQ 검색 요청
	 * 끝나고 수정해야되는 부분
	 */
	@RequestMapping("FAQ/Search")
	public ModelAndView Search(HttpServletRequest req, HttpSession session) {
		String strPage = req.getParameter("nowPage");
	
		int nowPage =  1;
		if(!StringUtil.isNull(strPage)){
			nowPage = Integer.parseInt(strPage);
		}
	
		String kind = req.getParameter("kind");
		String word = req.getParameter("word");
		System.out.println(kind+"카인드가 뭐죠");
		System.out.println(word+"워드가 뭐죠");
		
		if(StringUtil.isNull(kind)){
			kind = (String) session.getAttribute("KIND");
			word = (String) session.getAttribute("WORD");
		}
		System.out.println("종류"+kind);
		System.out.println("단어"+word);
		session.setAttribute("KIND", kind);
		session.setAttribute("WORD", word);
		
		FAQData data = new FAQData();
		data.setKind(kind);
		data.setWord(word);
		
		PageUtil pInfo = fService.getPageInfo(nowPage, FAQService.SEARCH, data);
		ArrayList list = fService.getList(pInfo, FAQService.SEARCH, data);
		System.out.println(list.size()+"리스트사이즈");
		System.out.println(pInfo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("word", word);
		mv.addObject("kind", kind);
		mv.setViewName("FAQ/Search");
		return mv;
	}
	
}
