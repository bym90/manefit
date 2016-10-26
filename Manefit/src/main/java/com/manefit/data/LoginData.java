package com.manefit.data;

import java.sql.Date;

public class LoginData {
	private String id;
	private String pw;
	private String name;
	private String email;
	private Date birth;
	private String tel;
	private String pc;
	private String addr1;
	private String addr2;
	private Date jdate;
	private String status;
	private String grade;
	private String[] prefer;
	private String style1;
	private String style2;
	private String kind;
	private String resrank;
	private String restyle1;
	private String restyle2;
	private int	   savemoney;

	
	
	public int getSavemoney() {
		return savemoney;
	}

	public void setSavemoney(int savemoney) {
		this.savemoney = savemoney;
	}

	public String getRestyle1() {
		return restyle1;
	}

	public void setRestyle1(String restyle1) {
		this.restyle1 = restyle1;
	}

	public String getRestyle2() {
		return restyle2;
	}

	public void setRestyle2(String restyle2) {
		this.restyle2 = restyle2;
	}

	public String getResrank() {
		return resrank;
	}

	public void setResrank(String resrank) {
		this.resrank = resrank;
	}


	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String[] getPrefer() {
		return prefer;
	}

	public void setPrefer(String[] prefer) {
		this.prefer = prefer;
	}

	public String getStyle1() {
		return style1;
	}

	public void setStyle1(String style1) {
		this.style1 = style1;
	}

	public String getStyle2() {
		return style2;
	}

	public void setStyle2(String style2) {
		this.style2 = style2;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getJdate() {
		return jdate;
	}

	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public char getAgree() {
		return agree;
	}

	public void setAgree(char agree) {
		this.agree = agree;
	}

	private char agree;

}
