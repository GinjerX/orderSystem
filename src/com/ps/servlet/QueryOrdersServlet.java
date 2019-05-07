package com.ps.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.QueryUserOrderVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class QueryOrders
 */
@WebServlet("/QueryOrderServlet")
public class QueryOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		

		ShoppingJDBC jdbc = new ShoppingJDBC();
		
		String userIds = request.getParameter("userId");
		
		int userId = Integer.parseInt(userIds);
		
		ArrayList<QueryUserOrderVO> orderQueryVOs = jdbc.queryOrder(userId);
		
		
		String msg = JSONObject.toJSONString(orderQueryVOs);
		System.out.println("----orderQueryVOs----------"+msg);
		//返回消息
		PrintWriter pw;
		pw = response.getWriter(); 
		//response.getWriter().write();
		pw.print(msg);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
