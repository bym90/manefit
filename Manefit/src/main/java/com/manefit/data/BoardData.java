package com.manefit.data;

import java.sql.Date;

public class BoardData {
	private String id;
	private int no;
	private String score;
	private String cate;
	private String body;
	private String name;
	private Date pdate;
	private Date idate;
	private String title;
	private String pw;
	private String rbody;
	private String rtitle;
	private String cname;
	private int rownum;
	private int[] no2;
	public int[] getNo2() {
		return no2;
	}

	public void setNo2(int[] no2) {
		this.no2 = no2;
	}

	private String[] pscheck;

	
	
	public String[] getPscheck() {
		return pscheck;
	}

	public void setPscheck(String[] pscheck) {
		this.pscheck = pscheck;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRbody() {
		return rbody;
	}

	public void setRbody(String rbody) {
		this.rbody = rbody;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
