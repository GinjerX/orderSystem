package com.ps.property;

public class MenuVO {
	private int id;

	private String name;
	
	private float price;
	
	private float discountPrice;
	
	private String isTop;
	
	private String groundingTime;
	
	private String state;
	
	private String stateName;
	
	private int count;
	
	private int sumCount;
	

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getGroundingTime() {
		return groundingTime;
	}

	public void setGroundingTime(String groundingTime) {
		this.groundingTime = groundingTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	@Override
	public String toString() {
		return "MenuVO [id=" + id + ", name=" + name + ", price=" + price + ", discountPrice=" + discountPrice
				+ ", isTop=" + isTop + ", groundingTime=" + groundingTime + ", state=" + state + ", stateName="
				+ stateName + ", count=" + count + ", sumCount=" + sumCount + ", getSumCount()=" + getSumCount()
				+ ", getCount()=" + getCount() + ", getId()=" + getId() + ", getName()=" + getName() + ", getPrice()="
				+ getPrice() + ", getDiscountPrice()=" + getDiscountPrice() + ", getIsTop()=" + getIsTop()
				+ ", getGroundingTime()=" + getGroundingTime() + ", getState()=" + getState() + ", getStateName()="
				+ getStateName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
