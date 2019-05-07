package com.ps.property;

import java.io.Serializable;
import java.util.List;

public class OrderVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userId;
	private int orderId;
	private String createTime;
	private String receiptTime;
	private String cancelReason;
	private String endTime;
	private String state;
	private String stateName;
	private float countMoney;
	private List<MenuVO> menus;
	private UserInfoVO userInfo;
	
	private OrderQueryVO orderQueryVO;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(float countMoney) {
		this.countMoney = countMoney;
	}
	public OrderQueryVO getOrderQueryVO() {
		return orderQueryVO;
	}
	public void setOrderQueryVO(OrderQueryVO orderQueryVO) {
		this.orderQueryVO = orderQueryVO;
	}
	public UserInfoVO getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoVO userInfo) {
		this.userInfo = userInfo;
	}
	public List<MenuVO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
