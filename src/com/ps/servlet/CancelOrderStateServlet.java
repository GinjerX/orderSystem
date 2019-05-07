package com.ps.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class CancelOrderStateServlet
 */
@WebServlet("/CancelOrderStateServlet")
public class CancelOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String orderId = request.getParameter("orderId");
		String cancelReason = request.getParameter("cancelReason");
		String state = request.getParameter("state");
		String total = request.getParameter("total");
		String menuId = request.getParameter("menuId");
		System.out.println(menuId+"-----------");
		
		System.out.println(orderId+"   orderid");
		
		ShoppingJDBC jdbc = new ShoppingJDBC();
		if(state.equals("2")) {
			jdbc.updateMenuStateByMenuId(orderId);
		}else if(state.equals("3")) {
			jdbc.updateOrderState(orderId);
		}else if(state.equals("4")) {
			System.out.println(menuId+"  fsdfdfasdfasd");
			jdbc.deletemenus(menuId);
		}else if(state.equals("5")){
			jdbc.cancelOrder(orderId,total);
			jdbc.cancelreason(cancelReason,orderId);
			
		}
		response.getWriter().print(JSONObject.toJSONString(jdbc));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
