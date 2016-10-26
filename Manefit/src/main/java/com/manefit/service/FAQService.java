package com.manefit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manefit.dao.FAQDAO;
import com.manefit.data.FAQData;
import com.manefit.util.PageUtil;

@Service
public class FAQService {
	@Autowired
	private FAQDAO fDAO;
	
	public static final int LIST = 1;
	public static final int SEARCH = 2;
	
	/*
	 * 리스트 불러오기
	 */
	public ArrayList getList(PageUtil pInfo, int kind, FAQData data) {
		ArrayList list = null;
		if (kind == LIST) {
			list = fDAO.getList();
		}
		else if(kind == SEARCH) {
			list = fDAO.getSearchList(data);
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
	 * 페이지 정보 구하기
	 */
	public PageUtil getPageInfo(int nowPage, int kind, FAQData data) {
		
		int totalCount = 0;
		if (kind == LIST) {
			totalCount = fDAO.getTotalList();
		}
		else if(kind == SEARCH) {
			totalCount = fDAO.getSearchTotalList(data);
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}
	
	/*
	 * FAQ 글 쓰기
	 */
	public void writeProc(FAQData fdata) {
		fDAO.writeProc(fdata);
	}
	
	/*
	 * FAQ 상세 보기
	 */
	public FAQData getView(int fno) {
		FAQData fdata = fDAO.getView(fno);
		return fdata;
	}
}
