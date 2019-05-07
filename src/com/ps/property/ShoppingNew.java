package com.ps.property;

import java.util.Arrays;

public class ShoppingNew {
	
	private int userId;
	private int [] shoppingId;
	private int [] count;
	private int [] orderId;
	private int [] menuId;
	
	public int [] getMenuId() {
		return menuId;
	}
	public void setMenuId(int[] menuId) {
		this.menuId = menuId;
	}
	public int [] getOrderId() {
		return orderId;
	}
	public void setOrderId(int[] orderId) {
		this.orderId = orderId;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int [] getShopping() {
		return shoppingId;
	}
	public void setShoppingId(int [] shoppingId) {
		this.shoppingId = shoppingId;
	}
	public int [] getCount() {
		return count;
	}
	public void setCount(int [] count) {
		this.count = count;
	}
	/*@Override
	public String toString() {
		return "ShoppingNew [userId=" + userId + ", menuId=" + Arrays.toString(shoppingId) + ", count="
				+ Arrays.toString(count) + "]";
	}*/
	@Override
	public String toString() {
		return "ShoppingNew [userId=" + userId + ", shoppingId=" + Arrays.toString(shoppingId) + ", count="
				+ Arrays.toString(count) + ", orderId=" + Arrays.toString(orderId) + ", menuId="
				+ Arrays.toString(menuId) + "]";
	}
	
	
	

}
