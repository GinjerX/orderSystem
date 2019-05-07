package com.ps.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.ResultVO;
import com.ps.property.ShoppingCarVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class ShoppingCarServlet
 */
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//允许跨域的主机地址
		response.setHeader("Access-Control-Allow-Origin", "*");
		//允许跨域的请求方法GET,POST,HEAD 等
		response.setHeader("Access-Control-Allow-Methods", "*");
		//重新预检跨域的缓存时间（s）
		response.setHeader("Access-Control-Max-Age", "3600");
		//允许跨域的请求头
		response.setHeader("Access-Control-Allow-Headers", "*");
		//是否携带cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		String userId = request.getParameter("userId");
		
		ShoppingJDBC jdbc = new ShoppingJDBC();
		
		List<ShoppingCarVO> ms = jdbc.queryShopping(userId);
		
		String msg = JSONObject.toJSONString(ms);
		//返回消息
		response.getWriter().print(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
