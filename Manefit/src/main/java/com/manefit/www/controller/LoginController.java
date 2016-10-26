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

import com.manefit.data.LoginData;
import com.manefit.data.MessageData;
import com.manefit.data.ShoppingData;
import com.manefit.service.LoginService;
import com.manefit.service.ShoppingService;

@Controller
public class LoginController {

	@Autowired
	private LoginService lService;
	@Autowired
	private ShoppingService sService;
	
	private static final Logger logger =  LoggerFactory.getLogger(LoginController.class);
	private static final String SILVER = "3";
	private static final String GOLD = "4";
	
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
	 * 로그인폼 요청
	 */
	@RequestMapping("/Login/LoginForm")
	public ModelAndView LoginForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/Login/LoginForm");
		return mv;
	}
	
	
	/*
	 * 로그인 요청
	 */
	@RequestMapping("/Login/LoginProc")
	public ModelAndView loginProc(HttpSession session, LoginData data) {
		
		boolean isLogin = lService.loginCheck(session, data);
		String grade = lService.getGrade(session, data.getId());
		
		String id = (String) session.getAttribute("ID");
		
		System.out.println("세션에 저장된 아이디는" + id);
		
		System.out.println("로그인 여부"+isLogin);
		ModelAndView mv = new ModelAndView();
		if(isLogin){
			
			data.setId(id);		
			
			// 자신의 등급에서 등급업 조건이 되는지 확인을 한다(등급업요건이 되는지 확인)
			// 관리자와 골드는 더 이상 등업 조건이 없기 때문에 제외 함
			
			// 자신이 구입한 총 금액 알아옴
			int totalPrice = lService.getTotalPrice(id);
			String title = "[등업 공지]축하합니다";
			String body = "";
			MessageData Mdata = new MessageData();
			System.out.println("ID : "+id+ "     총 지출비 "+totalPrice);		
			if(grade.equals("브론즈")){
				// 자신이 구입한 총 금액이 20만원 넘으면 실버로 등업
				if(totalPrice >= 500000){
					// 골드로 등업
					data.setGrade(GOLD);
					// 골드로 승급하면  실버로 승급할 때 3000원 골드로 승급할 때 5000원 총 8000원 적립금 줘야함
					data.setSavemoney(8000);
					lService.upGrade(data);
					session.setAttribute("GRADE", "골드");
					body = "등급이 브론즈 -> 골드로 상승 했습니다.<br>(혜택 : 적립급 8000 + 상시 7% 할인)";
					
					Mdata.setBody(body);
					Mdata.setTitle(title);
					Mdata.setId(id);
					lService.sendGradeUpNotice(Mdata);
					
					System.out.println("브론즈 --> 골드 업");
				}
				
				else if(totalPrice >= 200000){
					// 실버로 등업
					data.setGrade(SILVER);
					// 실버 축하기념 3000원 적립금 
					data.setSavemoney(3000);
					lService.upGrade(data);
					session.setAttribute("GRADE", "실버");
					body = "등급이 브론즈 -> 실버로 상승 했습니다.<br>(혜택 : 적립급 3000 + 상시 5% 할인)";
					
					Mdata.setBody(body);
					Mdata.setTitle(title);
					Mdata.setId(id);
					lService.sendGradeUpNotice(Mdata);
					
					System.out.println("브론즈 --> 실버 업");
				}
			}
	
			else if(grade.equals("실버")){
				if(totalPrice >= 500000){
					// 골드로 등업
					data.setGrade(GOLD);
					// 골드 축하기념 5000원 적립금
					data.setSavemoney(5000);
					lService.upGrade(data);
					session.setAttribute("GRADE", "골드");
					body = "등급이 실버 -> 골드로 상승 했습니다.<br>(혜택 : 적립급 5000 + 상시 7% 할인)";
					
					Mdata.setBody(body);
					Mdata.setTitle(title);
					Mdata.setId(id);
					lService.sendGradeUpNotice(Mdata);
					
					System.out.println("실버 --> 골드 업");
				}
			}
				
			RedirectView rv = new RedirectView();
			rv.setUrl("../");
			mv.setView(rv);
		} else {
			mv.setViewName("Login/LoginFail");
		};
		
		int newMegCount = lService.newMegCount(id);
		session.setAttribute("MEG", newMegCount);
				
		return mv;
	}
	
	/*
	 * 로그아웃 요청
	 */
	@RequestMapping("/Login/LogoutProc")
	public ModelAndView LoginProc(HttpSession session, HttpServletRequest req){
		session = req.getSession();
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		rv.setUrl("../");
		mv.setView(rv);
		
		return mv;
	}
	
	/*
	 * 회원가입폼 요청
	 */
	@RequestMapping("/Login/SignUpForm")
	public ModelAndView SignUpForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/Login/SignUpForm");
		return mv;
	}
	
	/*
	 * 회원가입 요청
	 */
	@RequestMapping("/Login/SignUpProc")
	public ModelAndView signUpProc(LoginData data, HttpServletRequest req) {
		
		String[] prefer = data.getPrefer();
				
		for(int i = 0; i < prefer.length; i++){
			if(i == 0){
				data.setStyle1(prefer[i]);
			}
			else{
				data.setStyle2(prefer[i]);
			}
		}
		
		lService.signUpProc(data);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("LCATE",getLcate());
		mv.addObject("MCATE",getMcate());
		mv.setViewName("/Login/LoginForm");
	
		return mv;
	}
	
	/*
	 * 아이디 중복 확인 요청
	 */
	@RequestMapping("/Login/IDCheck")
	public ModelAndView IDCheck(@RequestParam("id") String id) {
		
		Boolean idCheck = lService.idCheck(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("IDCHECK", idCheck);
		mv.setViewName("/Login/IDCheck");
		return mv;
	}
	
}
