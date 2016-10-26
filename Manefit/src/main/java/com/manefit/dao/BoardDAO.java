package com.manefit.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.manefit.data.BoardData;

public class BoardDAO  extends SqlSessionDaoSupport {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void psWrite(BoardData data){
		sqlSession.insert("Board.psWrite", data);
	}
	
//	public int maxNo(String cate) {
//		return (int) sqlSession.selectOne("Board.maxNo", cate);
//	}
	
	public void insertInqBoard(BoardData data) {
		sqlSession.insert("Board.insertInqBoard", data);
	}
	
	public int IpwCheck(BoardData data) {
		return (int) sqlSession.selectOne("Board.IpwCheck", data);
	}
	
	public BoardData getBody(BoardData data) {
		return (BoardData) sqlSession.selectOne("Board.getBody", data);
	}
	
	public void IAnswerProc(BoardData data) {
		sqlSession.insert("Board.IAnswerProc", data);
	}
	
	public BoardData replyInfo(BoardData data) {
		return (BoardData) sqlSession.selectOne("Board.replyInfo", data);
	}
	
	public void inqMegInsert(BoardData data) {
		sqlSession.insert("Board.inqMegInsert",data);
	}
}
