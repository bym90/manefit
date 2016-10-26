package com.manefit.data;

import org.springframework.web.multipart.MultipartFile;

public class ShoppingData {
	private int 	no;
	private String savename;
	private String path;
	private MultipartFile[] upimage;
	private String cate;
	private String mcate;
	private String scate;
	private String name;
	private String kind;
	private String code;
	private int    save;
	private int    price;
	private int    result;
	private String status;
	
	
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSave() {
		return save;
	}
	public void setSave(int save) {
		this.save = save;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public MultipartFile[] getUpimage() {
		return upimage;
	}
	public void setUpimage(MultipartFile[] upimage) {
		this.upimage = upimage;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMcate() {
		return mcate;
	}
	public void setMcate(String mcate) {
		this.mcate = mcate;
	}
	public String getScate() {
		return scate;
	}
	public void setScate(String scate) {
		this.scate = scate;
	}
	
}
