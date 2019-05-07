package com.ps.property;
public class OrderQueryVO {
	private int orderId;
	private int count;
	private int menuId;
	private int userId;
	private String phone;
	private String address;
	private float price;
	private float disCountprice;
	private int state;
	private String stateName;
	


	private String createTime;
	private String cancelReason;
	private String username;
	private String menuname;
	
	
	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getMenuname() {
		return menuname;
	}


	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	

	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public OrderQueryVO(int orderId, int userId, int state, String createTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.state = state;
		this.createTime = createTime;
	}
	public OrderQueryVO(int orderId, int count, int menuId, int userId, String phone, String address, float price,
			float disCountprice, int state, String stateName, String createTime,String cancelReason,String username,String menuname) {
		super();
		this.orderId = orderId;
		this.count = count;
		this.menuId = menuId;
		this.userId = userId;
		this.phone = phone;
		this.address = address;
		this.price = price;
		this.disCountprice = disCountprice;
		this.state = state;
		this.stateName = stateName;
		this.createTime = createTime;
		this.cancelReason = cancelReason;
		this.username = username;
		this.menuname = menuname;
	}

	public OrderQueryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDisCountprice() {
		return disCountprice;
	}

	public void setDisCountprice(float disCountprice) {
		this.disCountprice = disCountprice;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "OrderQueryVO [orderId=" + orderId + ", count=" + count + ", menuId=" + menuId + ", userId=" + userId
				+ ", phone=" + phone + ", address=" + address + ", price=" + price + ", disCountprice=" + disCountprice
				+ ", state=" + state + ", createTime=" + createTime + ", cancelReason=" + cancelReason + ", username="
				+ username + ", menuname=" + menuname + "]";
	}

	
}
