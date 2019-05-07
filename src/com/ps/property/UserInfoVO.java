package com.ps.property;

import java.io.Serializable;

public class UserInfoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private String userType;
	private float money;
	/*
	@Override
	public String toString() {
		return "UserInfoVO [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", address=" + address + ", userType=" + userType + ", money=" + money + "]";
	}
	*/
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}

}
