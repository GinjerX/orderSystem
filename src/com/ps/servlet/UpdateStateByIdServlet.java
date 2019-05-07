package com.ps.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.MenuVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class UpdateStateByIdServlet
 */
@WebServlet("/UpdateStateByIdServlet")
public class UpdateStateByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStateByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String orderId = request.getParameter("orderId");
		String state = request.getParameter("state");
		String cancelReason = request.getParameter("cancelReason");
		System.out.println();
		ShoppingJDBC jdbc = new ShoppingJDBC();
		if("5".equals(state)) {//取消订单操作
			//修改状态
			jdbc.updateOrderStateByOrderId(orderId, state,cancelReason);
			
			//根据订单id查询出订单下所有的菜品信息
			List<MenuVO> menus = jdbc.findMenusByOrderId(Long.valueOf(orderId));
			
			float totalMoney = 0;
			//实际收款金额
			float actualAmount = jdbc.getOrderActualAmountById(Long.valueOf(orderId));
			//扣钱
			jdbc.deductionByUserId(actualAmount,9);
			//增加客户账号余额
			/*UserInfoVO userInfo = jdbc.findUserInfoByOrderId(Long.valueOf(orderId));
			jdbc.receivablesByUserId(actualAmount, userInfo.getId());*/
		}else {
			//修改订单状态
			jdbc.updateOrderStateByOrderId(orderId, state,null);
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
