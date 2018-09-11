package com.chen.vo;

public class Movie {
	private int id = 0;
	private String name = null;
	private String english = null;
	private String actor = null;
	private String type = null;
	private double price = 0;
	private String starttime =null;
	public Movie(int id,String name, String english, String actor, String type, double price, String starttime) {
		super();
		this.id = id;
		this.name = name;
		this.english = english;
		this.actor = actor;
		this.type = type;
		this.price = price;
		this.starttime = starttime;
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", english=" + english + ", actor=" + actor + ", type=" + type
				+ ", price=" + price + ", starttime=" + starttime + "]";
	}

	public Movie() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
