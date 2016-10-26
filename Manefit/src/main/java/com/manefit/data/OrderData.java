package com.manefit.data;

import java.sql.Date;

public class OrderData {
	private int ono;
	private int no;
	private String cart;
	private String id;
	private String oname;
	private String oaddr;
	private String otel;
	private String oemail;
	// 주문상품 이름
	private String name;
	private String rname;
	private String raddr;
	private String rtel;
	private String[] color;
	private int[] quantity;
	private String[] price;
	private String[] size;
	private String price2;
	private String size2;
	private String color2;
	private int save2;
	private int quantity2;
	private String savename;
	private String[] name2;
	private String[] image;
	private String[] cate2;
	private int[] save3;
	private int kind;
	private int kind3;
	private String[] check;
	private int totalcount;

	private String cate;
	// 몇퍼센트 적립금액
	private int save;
	// 가지고 있는 적립금액
	private int smoney;
	// 사용한 적립금액
	private String usemoney;
	// 주문 총 금액
	private int totalprice;

	private Date odate;
	private String oparcel;
	private String oship;
	private String oship2;

	private String index;
	private int rownum;
	
	private String word;
	
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getOship2() {
		return oship2;
	}

	public void setOship2(String oship2) {
		this.oship2 = oship2;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String[] getCheck() {
		return check;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

	public int getKind3() {
		return kind3;
	}

	public void setKind3(int kind3) {
		this.kind3 = kind3;
	}

	public int[] getSave3() {
		return save3;
	}

	public void setSave3(int[] save3) {
		this.save3 = save3;
	}

	public String[] getName2() {
		return name2;
	}

	public void setName2(String[] name2) {
		this.name2 = name2;
	}

	public String[] getImage() {
		return image;
	}

	public void setImage(String[] image) {
		this.image = image;
	}

	public String[] getCate2() {
		return cate2;
	}

	public void setCate2(String[] cate2) {
		this.cate2 = cate2;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getQuantity2() {
		return quantity2;
	}

	public void setQuantity2(int quantity2) {
		this.quantity2 = quantity2;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getSize2() {
		return size2;
	}

	public void setSize2(String size2) {
		this.size2 = size2;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public int getSave2() {
		return save2;
	}

	public void setSave2(int save2) {
		this.save2 = save2;
	}

	public String getUsemoney() {
		return usemoney;
	}

	public void setUsemoney(String usemoney) {
		this.usemoney = usemoney;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOaddr() {
		return oaddr;
	}

	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}

	public String getOtel() {
		return otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public String getOemail() {
		return oemail;
	}

	public void setOemail(String oemail) {
		this.oemail = oemail;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRaddr() {
		return raddr;
	}

	public void setRaddr(String raddr) {
		this.raddr = raddr;
	}

	public String getRtel() {
		return rtel;
	}

	public void setRtel(String rtel) {
		this.rtel = rtel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSmoney() {
		return smoney;
	}

	public void setSmoney(int smoney) {
		this.smoney = smoney;
	}

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public int[] getQuantity() {
		return quantity;
	}

	public void setQuantity(int[] quantity) {
		this.quantity = quantity;
	}

	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public String[] getSize() {
		return size;
	}

	public void setSize(String[] size) {
		this.size = size;
	}

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public String getOparcel() {
		return oparcel;
	}

	public void setOparcel(String oparcel) {
		this.oparcel = oparcel;
	}

	public String getOship() {
		return oship;
	}

	public void setOship(String oship) {
		this.oship = oship;
	}
}
