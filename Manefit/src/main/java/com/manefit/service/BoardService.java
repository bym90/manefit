package com.manefit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manefit.dao.BoardDAO;
import com.manefit.data.BoardData;

@Service
public class BoardService {
	@Autowired
	private BoardDAO bDAO;

	/*
	 * 상품후기 글쓰기
	 */
	public void psWrite(BoardData data) {
		bDAO.psWrite(data);
	}

	/*
	 * 상품문의 전체 데이터 갯수
	 */
//	public int maxNo(String cate) {
//		return bDAO.maxNo(cate);
//	}

	/*
	 * 상품문의 게시판 글쓰기
	 */
	public void insertInqBoard(BoardData data) {
		bDAO.insertInqBoard(data);
	}

	/*
	 * 상품문의 비밀번호 확인
	 */
	public boolean IpwCheck(int no, String pw, String cate) {
		BoardData data = new BoardData();
		data.setNo(no);
		data.setPw(pw);
		data.setCate(cate);
		
		boolean confirm = false;

		int count = bDAO.IpwCheck(data);

		if (count == 1) {
			confirm = true;
		} else {
			confirm = false;
		}

		return confirm;
	}

	/*
	 * 상품문의 번호 해당 내용 가져오기
	 */
	public BoardData getBody(int no, String pw, String cate) {
		BoardData data = new BoardData();
		data.setNo(no);
		data.setPw(pw);
		data.setCate(cate);
		BoardData resultData = bDAO.getBody(data);

		return resultData;
	}
	
	/*
	 * 상품문의 답변쓰기
	 */
	public void IAnswerProc(BoardData data) {
		bDAO.IAnswerProc(data);
	}
	
	/*
	 * 답변한 상품문의 정보 가져오기
	 */
	public BoardData replyInfo(BoardData data) {
		return (BoardData) bDAO.replyInfo(data);
	}
	
	/*
	 * 메세지함에 답변완료 쪽지 보내기
	 */
	public void inqMegInsert(BoardData data) {
		bDAO.inqMegInsert(data);
	}

}
