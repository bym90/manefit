package com.manefit.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.manefit.data.BoardData;
import com.manefit.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService;
	/*
	 * 상품후기 글쓰기 요청
	 */
	@RequestMapping("/Board/PWriteProc")
	public ModelAndView epilWriteProc(HttpServletRequest req, HttpSession session, BoardData data){
		String scroll = req.getParameter("scroll");
		String body = req.getParameter("ps");
		String name = (String) session.getAttribute("NAME");
		String id = (String) session.getAttribute("ID");
		String cate = req.getParameter("cate");
		System.out.println("스크롤 위치"+ scroll);
		System.out.println("스코어가 뭐니" + data.getScore());
//		System.out.println("평점이나 좀 보자" + strScore);
		
//		data.setScore(score);
		data.setName(name);
		data.setBody(body);
		data.setId(id);
		bService.psWrite(data);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("cate", cate);
		mv.addObject("scroll", scroll);
		RedirectView rv = new RedirectView();
		rv.setUrl("../Shopping/ShoppingGoodsView.com");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 상품문의 게시판 글쓰기
	 */
	@RequestMapping("/Board/IWriteProc")
	public ModelAndView inqWriteProc(HttpServletRequest req, HttpSession session, BoardData data){
		String scroll = req.getParameter("scroll");
		String inq = req.getParameter("inq");
		String cate = req.getParameter("cate");
		String id = (String) session.getAttribute("ID");
		System.out.println("스크롤이 뭐니" + scroll);
//		int maxno = bService.maxNo(cate);
		
		data.setId(id);
		data.setBody(inq);
//		data.setNo(maxno);
		bService.insertInqBoard(data);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		mv.addObject("cate", cate);
		mv.addObject("scroll", scroll);
		rv.setUrl("../Shopping/ShoppingGoodsView.com");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 상품문의 비밀번호 확인
	 */
	@RequestMapping("/Board/IpwCheck")
	public ModelAndView ipwCheck(HttpServletRequest req){
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		String pw = req.getParameter("pw");
		
		String cate = req.getParameter("cate");
		boolean confirm = bService.IpwCheck(no, pw, cate);
		BoardData resultData = bService.getBody(no, pw, cate);

		ModelAndView mv = new ModelAndView();

		mv.addObject("CONFIRM", confirm);
		mv.addObject("BODY", resultData);
		mv.addObject("cate", cate);

		mv.setViewName("/Shopping/IpwCheck");

		return mv;
		
	}
	
	/*
	 * 상품문의 답변쓰기
	 */
	@RequestMapping("Board/IAnswerProc.com")
	public ModelAndView IAnswerProc(HttpSession session, HttpServletRequest req, BoardData data) {
		String cate = req.getParameter("cate");
		String id = (String) session.getAttribute("ID");
		bService.IAnswerProc(data);
		
		BoardData infoData = bService.replyInfo(data);
		String rbody = infoData.getRbody();
		String body = infoData.getBody();
		String resultBody = body + "<br>" + "=================답변=================" + "<br>" + rbody;
		
		infoData.setBody(resultBody);
		bService.inqMegInsert(infoData);
		
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		mv.addObject("cate", cate);
		rv.setUrl("../Admin/AdminBoard.com");
		mv.setView(rv);
		return mv;
	}
}
