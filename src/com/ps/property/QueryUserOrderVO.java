package com.ps.property;

public class QueryUserOrderVO {
	
	private int orderId;
	private int state;
	private String createTime;
	private int userId;
	private String menuName;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getState() {
		return state;
	}

	public QueryUserOrderVO( int orderId, int state, String createTime,int userId,String menuName) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.state = state;
		this.createTime = createTime;
		this.menuName = menuName;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
