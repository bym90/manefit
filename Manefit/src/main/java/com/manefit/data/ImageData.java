package com.manefit.data;

import org.springframework.web.multipart.MultipartFile;

public class ImageData {

	private String scate1;
    private MultipartFile[] upimage;
    private String savename;
    private String existingimage;
    private	int no;
    
	
    
    
    
    public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getExistingimage() {
		return existingimage;
	}
	public void setExistingimage(String existingimage) {
		this.existingimage = existingimage;
	}
	public String getScate1() {
		return scate1;
	}
	public void setScate1(String scate1) {
		this.scate1 = scate1;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public MultipartFile[] getUpimage() {
		return upimage;
	}
	public void setUpimage(MultipartFile[] upimage) {
		this.upimage = upimage;
	}
	 
	 
	 
	
	
}
