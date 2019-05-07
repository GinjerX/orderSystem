package com.ps.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.MenuVO;
import com.ps.property.OrderQueryVO;
import com.ps.property.ResultVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class QueryOrderInfosServlet
 */
@WebServlet("/QueryOrderInfosServlet")
public class QueryOrderInfosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOrderInfosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		//设置服务器的编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		
		String state = request.getParameter("state");
		String menuName = request.getParameter("menuName");
		List<OrderQueryVO> orderquery=null;
		if(state !=null) {
			orderquery = jdbc.getQuery(state);
		}else if(menuName != null){
			orderquery = jdbc.qeurymenuName(menuName);
		}else {
			orderquery = jdbc.Query();
		}
		
		String msg = JSONObject.toJSONString(orderquery);
		//返回消息
		response.getWriter().print(msg);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	ShoppingJDBC jdbc = new ShoppingJDBC();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
	public void query(HttpServletResponse response) {
		ArrayList<OrderQueryVO> OrderQueryVO = jdbc.Query();
		//返回消息
		PrintWriter pw;
		try {
			pw = response.getWriter();
			String msg = JSONObject.toJSONString(OrderQueryVO);
			pw.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/*	public void queryAll(HttpServletResponse response) {
		
		PrintWriter pw;
		try {
			pw = response.getWriter();
			String msg = JSONObject.toJSONString(OrderQueryVO);
			pw.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
