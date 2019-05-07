package com.ps.storJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ps.property.OrderQueryVO;

public class QueryUserOrderListJDBC {
	ArrayList<OrderQueryVO> orderQueryVOs = new ArrayList<OrderQueryVO>();
	private Connection conn;
	private PreparedStatement ps;
	
	
	
	/**
	 * 订单查询连接关系表
	 * @param states
	 * @return
	 */
	public List<OrderQueryVO> getQuery(String states,String userIds) {
		conn = ConnectionMy.getConnection();
		orderQueryVOs.clear();
		String sql = "SELECT o.orderId,om.count,m.id '菜单',us.id '用户',us.phone,us.address,m.price,m.discountPrice,o.state,CASE o.state WHEN 1 THEN'未接单'WHEN 2 THEN'已接单'WHEN 0 THEN'已取消'WHEN 3 THEN'已完成'END AS stateName,o.create_time,o.cancel_reason,us.username,m.greens_name "
				+ "from order_menu om " + 
				" LEFT JOIN eat_order_t o on(o.orderId=om.order_id) " + 
				" LEFT JOIN eat_menu_t m on(m.id =om.menu_id )LEFT JOIN eat_user_t us on(us.id=o.user_id)"
				+ " where o.state = ? and us.id = ? group by o.orderId ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, states);
			ps.setString(2, userIds);
			System.out.println();
			ResultSet set = ps.executeQuery();
			while (set.next()) {

				int orderId = set.getInt(1);
				int count = set.getInt(2);
				int menuId = set.getInt(3);
				int userId = set.getInt(4);
				String phone = set.getString(5);
				String address = set.getString(6);
				float price = set.getFloat(7);
				float disCountprice = set.getFloat(8);
				int state = set.getInt(9);
				String stateName = set.getString(10);
				String time = set.getString(11);
				String cancelReason = set.getString(12);
				String username = set.getString(13);
				String menuname = set.getString(14);

				OrderQueryVO orderQueryVO = new OrderQueryVO(orderId, count, menuId, userId, phone, address, price,
						disCountprice, state,stateName,time, cancelReason, username, menuname);
				orderQueryVOs.add(orderQueryVO);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderQueryVOs;
	}
	/**
	 * 模糊查询菜品名(根据订单id)
	 */
	public List<OrderQueryVO> qeurymenuName(String menuName){
		List<OrderQueryVO> list = new ArrayList<OrderQueryVO>();
		conn = ConnectionMy.getConnection();
		String sql ="SELECT o.orderId,om.count,m.id '菜单',us.id '用户',us.phone,us.address,m.price,m.discountPrice,o.state,CASE o.state WHEN 1 THEN'未接单'WHEN 2 THEN'已接单'WHEN 0 THEN'已取消'WHEN 3 THEN'已完成'END AS stateName,o.create_time,o.cancel_reason,us.username,m.greens_name from " + 
				"order_menu om " + 
				"LEFT JOIN eat_order_t o on(o.orderId=om.order_id) " + 
				"LEFT JOIN eat_menu_t m on(m.id =om.menu_id ) " + 
				"LEFT JOIN eat_user_t us on(us.id=o.user_id)  group by o.orderId where m.greens_name like '%"+menuName+"%'";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
	        	
	        	int orderId = set.getInt(1);
				int count = set.getInt(2);
				int menuId = set.getInt(3);
				int userId = set.getInt(4);
				String phone = set.getString(5);
				String address = set.getString(6);
				float price = set.getFloat(7);
				float disCountprice = set.getFloat(8);
				int state = set.getInt(9);
				String stateName = set.getString(10);
				String time = set.getString(11);
				String cancelReason = set.getString(12);
				String username = set.getString(13);
				String menuname = set.getString(14);

				OrderQueryVO orderQueryVO = new OrderQueryVO(orderId, count, menuId, userId, phone, address, price,
						disCountprice, state,stateName, time, cancelReason, username, menuname);
				orderQueryVOs.add(orderQueryVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<OrderQueryVO> Query(String userIds) {
		orderQueryVOs.clear();
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("SELECT o.orderId,om.count,m.id '菜单',us.id '用户',us.phone,us.address,m.price,m.discountPrice,o.state,CASE o.state WHEN 1 THEN'未接单'WHEN 2 THEN'已接单'WHEN 0 THEN'已取消'WHEN 3 THEN'已完成'END AS stateName,o.create_time,o.cancel_reason,us.username,m.greens_name from " + 
					" order_menu om " + 
					"LEFT JOIN eat_order_t o on(o.orderId=om.order_id) " + 
					"LEFT JOIN eat_menu_t m on(m.id =om.menu_id ) " + 
					"LEFT JOIN eat_user_t us on(us.id=o.user_id)  where us.id = ? group by o.orderId");
			ps.setString(1, userIds);
			ResultSet set = ps.executeQuery();

			while (set.next()) {

				int orderId = set.getInt(1);
				int count = set.getInt(2);
				int menuId = set.getInt(3);
				int userId = set.getInt(4);
				String phone = set.getString(5);
				String address = set.getString(6);
				float price = set.getFloat(7);
				float disCountprice = set.getFloat(8);
				int state = set.getInt(9);
				String stateName = set.getString(10);
				String time = set.getString(11);
				String cancelReason = set.getString(12);
				String username = set.getString(13);
				String menuname = set.getString(14);

				OrderQueryVO orderQueryVO = new OrderQueryVO(orderId, count, menuId, userId, phone, address, price,
						disCountprice, state,stateName, time, cancelReason, username, menuname);
				orderQueryVOs.add(orderQueryVO);
			}
			System.out.println("orderQueryVOs = "+orderQueryVOs);
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderQueryVOs;
	}

}
