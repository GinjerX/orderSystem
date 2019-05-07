package com.ps.property;

public class OrderDetail {
	private int orderIdOm;
	private String price;
	private String discountPrice;
	private String greens_name ;
	private String count ;
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(int orderIdOm, String price, String discountPrice, String greens_name, String count) {
		super();
		this.orderIdOm = orderIdOm;
		this.price = price;
		this.discountPrice = discountPrice;
		this.greens_name = greens_name;
		this.count = count;
	}

	public int getOrderIdOm() {
		return orderIdOm;
	}
	public void setOrderIdOm(int orderIdOm) {
		this.orderIdOm = orderIdOm;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getGreens_name() {
		return greens_name;
	}
	public void setGreens_name(String greens_name) {
		this.greens_name = greens_name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
