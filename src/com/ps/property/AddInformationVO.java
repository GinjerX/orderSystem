package com.ps.property;

import java.util.Arrays;

public class AddInformationVO {
	private int userId;
	private int [] orderId;
	private int [] menuId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

		public int[] getOrderId() {		return orderId;
	}
	public void setOrderId(int[] orderId) {
		this.orderId = orderId;
	}
	public int[] getMenuId() {
		return menuId;
	}
	public void setMenuId(int[] menuId) {
		this.menuId = menuId;
	}
	@Override
	public String toString() {
		return "AddInformation [userId=" + userId + ", orderId=" + Arrays.toString(orderId) + ", menuId="
				+ Arrays.toString(menuId) + "]";
	}
	
	
}
