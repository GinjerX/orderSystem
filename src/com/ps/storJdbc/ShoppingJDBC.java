package com.ps.storJdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ps.property.MenuVO;
import com.ps.property.OrderDetail;
import com.ps.property.OrderQueryVO;
import com.ps.property.OrderVO;
import com.ps.property.QueryUserOrderVO;
import com.ps.property.ShoppingCarVO;
import com.ps.property.UserInfoVO;
import com.ps.property.Utils;

public class ShoppingJDBC {
	ArrayList<OrderQueryVO> orderQueryVOs = new ArrayList<OrderQueryVO>();
	
	private Connection conn;
	private PreparedStatement ps;
	/**
	 * 模糊查询
	 */
	public List<MenuVO> menuVOQuery(String menuname){
		List<MenuVO> list = new ArrayList<MenuVO>();
		conn = ConnectionMy.getConnection();
		String sql = "SELECT t.id,t.greens_name,t.price,t.discountPrice,t.is_top,t.grounding_time,t.state," + 
				"CASE t.state WHEN 1 THEN'上架'WHEN 2 THEN'下架'WHEN 0 THEN'删除'END AS stateName FROM eat_menu_t t where t.greens_name like '%"+menuname+"%'";
	
		ResultSet set;
		try {
			ps = conn.prepareStatement(sql);
			set = ps.executeQuery();
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				float price = set.getFloat(3);
				float discountPrice = set.getFloat(4);
				String isTop = set.getString(5);
				String groundingTime = set.getString(6);
				String state = set.getString(7);
				String stateName = set.getString(8);

				MenuVO menuVO = new MenuVO();
				menuVO.setDiscountPrice(discountPrice);
				menuVO.setGroundingTime(groundingTime);
				menuVO.setId(id);
				menuVO.setIsTop(isTop);
				menuVO.setName(name);
				menuVO.setPrice(price);
				menuVO.setState(state);
				menuVO.setStateName(stateName);

				list.add(menuVO);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
		
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
	/**
	 * 订单详情
	 * @return 
	 */
	public ArrayList<Object> orderXin(String orderId) {
		conn = ConnectionMy.getConnection();
		ArrayList<Object> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select om.order_id,m.price,m.discountPrice,m.greens_name,om.count from order_menu om LEFT JOIN eat_order_t o on (om.order_id =o.orderId) LEFT JOIN eat_menu_t m on(m.id = om.menu_id) where om.order_id = ?");
			ps.setString(1, orderId);
			ResultSet set = ps.executeQuery();
			while(set.next()){
				OrderDetail od = null;
				int orderIdOm = set.getInt(1);
				String price = set.getString(2);
				String discountPrice = set.getString(3);
				String greens_name = set.getString(4);
				String count = set.getString(5);
				od = new OrderDetail(orderIdOm,price,discountPrice,greens_name,count);
				list.add(od);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 */
	public void deletemenus(String menuId) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_menu_t t set t.state = 0 where t.id = ?");
			ps.setString(1, menuId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据菜单ID修改状态
	 * 
	 * @param menuId
	 * @param state
	 * @throws Exception
	 */
	public void updateMenuStateByMenuId(String orderId) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_order_t t set t.state = 2 where t.orderId = ?");
			ps.setString(1, orderId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 修改订单状态为收货
	 */
	public void updateOrderState(String orderId) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_order_t t set t.state = 3 where t.orderId = ?");
			ps.setString(1, orderId);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 取消订单的原因
	 */
	public void cancelreason(String cancelreason,String orderId) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_order_t set cancel_reason=? where orderId = ? ");
			ps.setString(1, cancelreason);
			ps.setString(2, orderId);
			System.out.println(cancelreason+"--cancelreason---------"+orderId+" ===");

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 管理员取消订单并退钱
	 * 取消订单
	 * @param orderId
	 */
	public void cancelOrder(String orderId,String total) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_order_t t set t.state = 0 where t.orderId = ?");
			ps.setString(1, orderId);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update eat_user_t t set t.money=t.money-"+total+" where t.id = 11");

			ps.executeUpdate();
			
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 删除购物车
	 * @param shoppingId
	 */
	public void deleteShopingCar(String shoppingId) {
		conn = ConnectionMy.getConnection();
		String sql="delete from eat_menu_car_t where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoppingId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 扣钱
	 */
	public void totalmoney(int userId,String total) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_user_t t set t.money=t.money-"+total+" where t.id = ?");
			ps.setInt(1, userId);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update eat_user_t t set t.money=t.money+"+total+" where t.id =11");
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * easyUI模糊查询
	 */
	public List<MenuVO> easyQuery(String query){
		conn = ConnectionMy.getConnection();
		List<MenuVO> list = new ArrayList<MenuVO>();
		String sql="select * from eat_menu_t where greens_name like '%"+query+"%'";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
	            MenuVO mv=new MenuVO();
	            mv.setName(set.getString(2));
	            mv.setPrice(set.getFloat(3));
	            mv.setCount(set.getInt(4));
	            mv.setState(set.getString(5));
	            list.add(mv);
	        }
			ps.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return list;
		
		
		
	}
	/**
	 * 查询用户订单
	 */
	public ArrayList<QueryUserOrderVO> queryOrder(int userId) {
		ArrayList<QueryUserOrderVO> queryUserOrder = new ArrayList<QueryUserOrderVO>();
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("select t.id,m.greens_name,t.create_time,t.state from eat_order_t t left join order_menu om on (t.id = om.order_id) LEFT JOIN eat_menu_t m on (m.id=om.menu_id) where t.user_id = ?");
			ps.setInt(1, userId);
			ResultSet set = ps.executeQuery();
			
			while (set.next()) {
				int orderId = set.getInt("id");
				//int userIds = set.getInt("user_id");
				String menuName = set.getString("greens_name");
				String createTime = set.getString("create_time");
				int state = set.getInt("state");

				QueryUserOrderVO orderQueryVO = new QueryUserOrderVO(orderId,state,createTime,userId,menuName);

				queryUserOrder.add(orderQueryVO);
			}
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryUserOrder;
	}
	/**
	 * 查菜单id和数量
	 */
	public int [] queryMenuAndCount(int userId) {
		conn = ConnectionMy.getConnection();
		int menuIds = 0;
		int counts = 0;
		try {
			ps = conn.prepareStatement("select menu_id,count from eat_menu_car_t where id = ?");
			ps.setInt(1, userId);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				menuIds = resultSet.getInt(1);
				counts = resultSet.getInt(2);
			}
			System.out.println("sql  menuid = "+menuIds+"  counts = "+counts);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new int []{menuIds,counts};
	}
	/**
	 * 添加关系表
	 */
	public void add(int orderId,int menuId,int count) {
		
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("insert into order_menu (order_id,menu_id,count)VALUES(?,?,?)");
			ps.setInt(1, orderId);
			ps.setInt(2, menuId);
			ps.setInt(3, count);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 删除掉已经下单的购物商品
	 */
	public void deleteOrder(int carId){
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("delete from eat_menu_car_t where id = ?;");
			ps.setInt(1, carId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	/**
	 * 增加到订单
	 * 根据userId 增加orderId
	 * @param userId
	 */
	public int addOrder(int userId) {
		conn = ConnectionMy.getConnection();
		int id = -1;
		try {
			ps = conn.prepareStatement("INSERT INTO eat_order_t (state,create_time,user_id)VALUES(1,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);//增加订单默认为2 未接单
			ps.setString(1,Utils.getDate());
			ps.setInt(2,userId);
			ps.executeUpdate();
			
			ResultSet set = ps.getGeneratedKeys();
			while(set.next()) {
				id=set.getInt(1);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 查询购物
	 * @return
	 */
	public List<ShoppingCarVO> queryShopping( String userIds) {
		List<ShoppingCarVO> car = new ArrayList<ShoppingCarVO>();
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("select t.id,t.user_id,t.menu_id,m.greens_name,t.create_time,t.count from eat_menu_car_t t LEFT JOIN eat_menu_t m on(t.menu_id = m.id) where t.user_id = ?;");
			ps.setString(1, userIds);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int userId = resultSet.getInt("user_id");
				int menuId = resultSet.getInt("menu_id");
				String menuName = resultSet.getString("greens_name");
				String createTime = resultSet.getString("create_time");
				int count = resultSet.getInt("count");
				
				
				ShoppingCarVO carVO = new ShoppingCarVO();
				carVO.setId(id);
				carVO.setUserId(userId);
				carVO.setMenuId(menuId);
				carVO.setMenuName(menuName);
				carVO.setCreateTime(createTime);
				carVO.setCount(count);
				
				car.add(carVO);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
		
	}
	/**
	 * 增加到购物车
	 * @param menu
	 */
	public void addShoppingCar(int userId,int menuId,int count) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("INSERT INTO eat_menu_car_t (user_id,menu_id,count,create_time) VALUES(?,?,?,?)");
			ps.setInt(1,userId);
			ps.setInt(2,menuId);
			ps.setInt(3,count);
			ps.setString(4,Utils.getDate());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void queryShoppingCar(String userId) {
		conn = ConnectionMy.getConnection();
		
		try {
			ps = conn.prepareStatement(
					"select t.id,t.create_time,t.receipt_time,t.end_time,t.cancel_reason,t.state,t.countMoney from eat_order_t t where user_id = ?;");
			ps.setString(1, userId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String createTime = resultSet.getString("create_time");
				String receiptTime = resultSet.getString("receipt_time");
				String endTime = resultSet.getString("end_time");
				String cancelReason = resultSet.getString("cancel_reason");
				String state = resultSet.getString("state");
				// String stateName = resultSet.getString("stateName");
				Float countMoney = resultSet.getFloat("countMoney");

				OrderVO menu = new OrderVO();
				menu.setId(id);
				menu.setCreateTime(createTime);
				menu.setReceiptTime(receiptTime);
				menu.setEndTime(endTime);
				menu.setCancelReason(cancelReason);
				menu.setState(state);
				menu.setCountMoney(countMoney);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改取消原因状态
	 * 
	 * @param orderId
	 * @param cancleReason
	 * @throws Exception
	 */
	public void updateOrderCancelByOrderId(String orderId, String cancleReason) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_order_t t set t.cancel_reason = ? where t.id = ?");

			ps.setString(1, cancleReason);
			ps.setString(2, orderId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单id修改
	 * 
	 * @param menuId
	 * @param state
	 * @throws Exception
	 */
	public void updateOrderStateByOrderId(String orderId, String state, String cancelReason) {
		conn = ConnectionMy.getConnection();
		String sql = null;
		try {
			if (cancelReason == null) {// 仅修改状态
				ps = conn.prepareStatement("update eat_order_t t set t.state = ? where t.id = ?");
				ps.setString(1, state);
				ps.setString(2, orderId);

			} else {// 修改状态、取消订单的原因
				ps = conn.prepareStatement("update eat_order_t t set t.state = ?,t.cancel_reason=? where t.id = ?");
				ps.setString(1, state);
				ps.setString(2, cancelReason);
				ps.setString(3, orderId);

			}

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据用户id扣除用户指定金额
	 * 
	 * @param actualAmount
	 * @param userId
	 * @throws Exception
	 */
	public void deductionByUserId(float actualAmount, long userId) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_user_t t set t.money =(t.money - ?) where t.id = ?");
			ps.setFloat(1, actualAmount);
			ps.setLong(2, userId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户id增加用户指定金额
	 * 
	 * @param actualAmount
	 * @param userId
	 * @throws Exception
	 */
	public void receivablesByUserId(float actualAmount, long userId) {
		conn = ConnectionMy.getConnection();

		try {
			ps = conn.prepareStatement("update eat_user_t t set t.money =(t.money + ?) where t.id = ?");

			ps.setFloat(1, actualAmount);
			ps.setLong(2, userId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据订单id查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserInfoVO findUserInfoByOrderId(long orderId) {
		UserInfoVO userInfo = null;
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement(
					"select t.id,t.username,t.phone,t.address,t.money,t.user_type from eat_user_t t where t.id ="
							+ "(select t.user_id from eat_order_t t where t.id =?)");
			ps.setLong(1, orderId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String phone = resultSet.getString("phone");
				String userType = resultSet.getString("user_type");
				String address = resultSet.getString("address");
				Float money = resultSet.getFloat("money");

				userInfo = new UserInfoVO();
				userInfo.setAddress(address);
				userInfo.setId(id);
				userInfo.setPhone(phone);
				userInfo.setMoney(money);
				userInfo.setUsername(username);
				userInfo.setUserType(userType);

			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据订单id查询菜品信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<MenuVO> findMenusByOrderId(long orderId) {
		conn = ConnectionMy.getConnection();
		List<MenuVO> menus = new ArrayList<MenuVO>();
		try {
			ps = conn.prepareStatement(
					"SELECT t.id,t.greens_name,t.price,t.discountPrice,t.is_top,t.grounding_time,t.state,"
							+ "CASE t.state WHEN 1 THEN'上架'WHEN 2 THEN'下架'WHEN 0 THEN'删除'END AS stateName,t.count FROM eat_menu_t t left join order_menu om"
							+ " on(t.id = om.menu_id) where om.order_id = ?");
			ps.setLong(1, orderId);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				float price = set.getFloat(3);
				float discountPrice = set.getFloat(4);
				String isTop = set.getString(5);
				String groundingTime = set.getString(6);
				String state = set.getString(7);
				String stateName = set.getString(8);
				// int count =set.getInt("");

				MenuVO menuVO = new MenuVO();
				menuVO.setDiscountPrice(discountPrice);
				menuVO.setGroundingTime(groundingTime);
				menuVO.setId(id);
				menuVO.setIsTop(isTop);
				menuVO.setName(name);
				menuVO.setPrice(price);
				menuVO.setState(state);
				menuVO.setStateName(stateName);
				// menuVO.setCount(count);

				menus.add(menuVO);
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menus;
	}

	/**
	 * 查询订单信息
	 * 
	 * @param state2
	 * @throws Exception
	 */
	public List<OrderVO> findOrderList(String state2) {
		conn = ConnectionMy.getConnection();
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();

		try {
			if (state2 == null) {// 查所有
				ps = conn.prepareStatement(
						"SELECT t.id,t.user_id,t.create_time,t.receipt_time,t.cancel_reason,t.end_time,t.state,"
								+ "CASE t.state WHEN 1 THEN '未接单' when 2 THEN '已接单'  WHEN 3 THEN '已生产' WHEN 4 THEN '完成' WHEN 5 THEN '取消' "
								+ "END AS stateName From eat_order_t t");
			} else {// 根据状态查询订单
				ps = conn.prepareStatement(
						"SELECT t.id,t.user_id,t.create_time,t.receipt_time,t.cancel_reason,t.end_time,t.state,"
								+ "CASE t.state WHEN 1 THEN '未接单' when 2 THEN '已接单'  WHEN 3 THEN '已生产' WHEN 4 THEN '完成' WHEN 5 THEN '取消' "
								+ "END AS stateName From eat_order_t t where t.state =?");
				ps.setString(1, state2);
			}

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				int id = set.getInt(1);
				int userId = set.getInt(2);
				String createTime = set.getString(3);
				String receiptTime = set.getString(4);
				String cancelReason = set.getString(5);
				String endTime = set.getString(6);
				String state = set.getString(7);
				String stateName = set.getString(8);

				OrderVO orderVO = new OrderVO();

				orderVO.setCancelReason(cancelReason);
				orderVO.setCreateTime(createTime);
				orderVO.setEndTime(endTime);
				orderVO.setId(id);
				orderVO.setReceiptTime(receiptTime);
				orderVO.setState(state);
				orderVO.setStateName(stateName);
				orderVO.setUserId(userId);

				orderVOs.add(orderVO);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderVOs;
	}

	/**
	 * 根据菜单ID修改状态
	 * 
	 * @param menuId
	 * @param state
	 * @throws Exception
	 */
	public void updateMenuStateByMenuId(String menuId, String state) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("update eat_menu_t t set t.state = ? where t.id = ?");
			ps.setString(1, state);
			ps.setString(2, menuId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据id修改菜品信息
	 * 
	 * @param menuVO
	 */
	public void updateMenuById(MenuVO menuVO) {
		PreparedStatement ps;
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement(
					"update eat_menu_t t set t.greens_name =?,t.price=?,t.discountPrice=?,t.is_top=?,t.state =? where t.id = ?");
			ps.setString(1, menuVO.getName());
			ps.setFloat(2, menuVO.getPrice());
			ps.setFloat(3, menuVO.getDiscountPrice());
			ps.setString(4, menuVO.getIsTop());
			ps.setString(5, menuVO.getState());
			ps.setLong(6, menuVO.getId());

			ps.execute();

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 菜单增加
	 * 
	 * @param menu
	 * @throws Exception
	 */
	public void menuAdd(MenuVO menu) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement(
					"insert into eat_menu_t(greens_name,price,discountPrice,is_top,state)values(?,?,?,?,1)");
			ps.setString(1, menu.getName());
			ps.setFloat(2, menu.getPrice());
			ps.setFloat(3, menu.getDiscountPrice());
			ps.setString(4, menu.getIsTop());
			//ps.setString(5, menu.getState());状态默认给1上架   2下架
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查询所有菜单信息
	 * 
	 * @return
	 */
	public List<MenuVO> findAllMenuInfoList() {
		conn = ConnectionMy.getConnection();
		List<MenuVO> menus = new ArrayList<MenuVO>();
		try {
			ps = conn.prepareStatement(
					"SELECT t.id,t.greens_name,t.price,t.discountPrice,t.is_top,t.grounding_time,t.state,"
							+ "CASE t.state WHEN 1 THEN'上架'WHEN 2 THEN'下架'WHEN 0 THEN'删除'END AS stateName FROM eat_menu_t t");

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				float price = set.getFloat(3);
				float discountPrice = set.getFloat(4);
				String isTop = set.getString(5);
				String groundingTime = set.getString(6);
				String state = set.getString(7);
				String stateName = set.getString(8);

				MenuVO menuVO = new MenuVO();
				menuVO.setDiscountPrice(discountPrice);
				menuVO.setGroundingTime(groundingTime);
				menuVO.setId(id);
				menuVO.setIsTop(isTop);
				menuVO.setName(name);
				menuVO.setPrice(price);
				menuVO.setState(state);
				menuVO.setStateName(stateName);

				menus.add(menuVO);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}

	/**
	 * 用户注册
	 * 
	 * @param userInfoVO
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addUserInfo(UserInfoVO userInfoVO) {
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement(
					"insert into eat_user_t(username,password,phone,address,money)values(?,?,?,?,500)");
			ps.setString(1, userInfoVO.getUsername());
			ps.setString(2, userInfoVO.getPassword());
			ps.setString(3, userInfoVO.getPhone());
			ps.setString(4, userInfoVO.getAddress());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户名称查找用户信息
	 * 
	 * @param username
	 * @return
	 */
	public UserInfoVO findUserInfoByUsername(String username) {
		UserInfoVO userInfo = null;
		try { 
			// 获取连接
			conn = ConnectionMy.getConnection();
			ps = conn.prepareStatement(
					"select t.id,t.username,t.password,t.user_type from eat_user_t t where t.username= ?");
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String usernames = resultSet.getString("username");
				String passwords = resultSet.getString("password");
				String type = resultSet.getString("user_type");
				userInfo = new UserInfoVO();
				userInfo.setUsername(usernames);
				userInfo.setId(id);
				userInfo.setPassword(passwords);
				userInfo.setUserType(type);

			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	/**
	 * 查询菜单id信息 根据id查询菜单信息
	 * 
	 * @param menuId
	 * @return
	 */
	public MenuVO QueryMenuById(String menuId) {
		MenuVO menuVO = null;
		try {
			conn = ConnectionMy.getConnection();
			ps = conn.prepareStatement(
					"SELECT t.id,t.greens_name,t.price,t.discountPrice,t.is_top,t.grounding_time,t.state,"
							+ "CASE t.state WHEN 1 THEN'上架'WHEN 2 THEN'下架'WHEN 0 THEN'删除'END AS stateName FROM eat_menu_t t where t.id=?");
			ps.setString(1, menuId);
			ResultSet set = ps.executeQuery();

			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				float price = set.getFloat(3);
				float discountPrice = set.getFloat(4);
				String isTop = set.getString(5);
				String groundingTime = set.getString(6);
				String state = set.getString(7);
				String stateName = set.getString(8);

				menuVO = new MenuVO();
				menuVO.setDiscountPrice(discountPrice);
				menuVO.setGroundingTime(groundingTime);
				menuVO.setId(id);
				menuVO.setIsTop(isTop);
				menuVO.setName(name);
				menuVO.setPrice(price);
				menuVO.setState(state);
				menuVO.setStateName(stateName);

			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuVO;
	}

	/**
	 * 根据订单id修改
	 * 
	 * @param orderId
	 * @return
	 */
	public float getOrderActualAmountById(Long orderId) {
		conn = ConnectionMy.getConnection();
		float actualAmount = 0;
		try {
			ps = conn.prepareStatement(
					"select SUM((m.price - IFNULL(m.discountPrice,0))*orm.count) AS count from order_menu orm LEFT JOIN "
							+ "eat_menu_t m on orm.menu_id = m.id WHERE orm.order_id =?");

			ps.setLong(1, orderId);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				actualAmount = set.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	/**
	 * 订单查询连接关系表
	 * @param states
	 * @return
	 */
	public List<OrderQueryVO> getQuery(String states) {
		conn = ConnectionMy.getConnection();
		orderQueryVOs.clear();
		try {
			ps = conn.prepareStatement("SELECT o.orderId,om.count,m.id '菜单',us.id '用户',us.phone,us.address,m.price,m.discountPrice,o.state,CASE o.state WHEN 1 THEN'未接单'WHEN 2 THEN'已接单'WHEN 0 THEN'已取消'WHEN 3 THEN'已完成'END AS stateName,o.create_time,o.cancel_reason,us.username,m.greens_name from order_menu om " + 
					"LEFT JOIN eat_order_t o on(o.orderId=om.order_id) " + 
					" LEFT JOIN eat_menu_t m on(m.id =om.menu_id )LEFT JOIN eat_user_t us on(us.id=o.user_id) where o.state = ? group by o.orderId ");
			ps.setString(1, states);
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
	public ArrayList<OrderQueryVO> Query() {
		orderQueryVOs.clear();
		conn = ConnectionMy.getConnection();
		try {
			ps = conn.prepareStatement("SELECT o.orderId,om.count,m.id '菜单',us.id '用户',us.phone,us.address,m.price,m.discountPrice,o.state,CASE o.state WHEN 1 THEN'未接单'WHEN 2 THEN'已接单'WHEN 0 THEN'已取消'WHEN 3 THEN'已完成'END AS stateName,o.create_time,o.cancel_reason,us.username,m.greens_name from " + 
					" order_menu om " + 
					"LEFT JOIN eat_order_t o on(o.orderId=om.order_id) " + 
					"LEFT JOIN eat_menu_t m on(m.id =om.menu_id ) " + 
					"LEFT JOIN eat_user_t us on(us.id=o.user_id)  group by o.orderId");
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
