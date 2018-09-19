package com.chen.bean;

public class Books {
	private int bid;
	private String bname;
	private String bauthor;
	private double bprice;
	private String bdate;
	private String bimage;
	private int bisonline;
	
	public Books(String bname, String bauthor, double bprice, String bdate, String bimage, int bisonline) {
		super();
		this.bname = bname;
		this.bauthor = bauthor;
		this.bprice = bprice;
		this.bdate = bdate;
		this.bimage = bimage;
		this.bisonline = bisonline;
	}
	@Override
	public String toString() {
		return "Books [bid=" + bid + ", bname=" + bname + ", bauthor=" + bauthor + ", bprice=" + bprice + ", bdate="
				+ bdate + ", bimage=" + bimage + ", bisonline=" + bisonline + "]";
	}
	public Books(int bid, String bname, String bauthor, double bprice, String bdate, String bimage, int bisonline) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.bauthor = bauthor;
		this.bprice = bprice;
		this.bdate = bdate;
		this.bimage = bimage;
		this.bisonline = bisonline;
	}
	public Books() {
		super();
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBimage() {
		return bimage;
	}
	public void setBimage(String bimage) {
		this.bimage = bimage;
	}
	public int getBisonline() {
		return bisonline;
	}
	public void setBisonline(int bisonline) {
		this.bisonline = bisonline;
	}
	
}
