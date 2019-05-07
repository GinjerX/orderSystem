package com.ps.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.UserProper;

/**
 * Servlet implementation class axiosDemoServlet
 */
@WebServlet("/axiosDemoServlet")
public class AxiosDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AxiosDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置服务端的编码
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
		
		//获取前台数据
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("name:"+name+"age:"+age);
		
		
		UserProper user = new UserProper();
		user.setName("秋香");
		user.setAge(20);
		user.setId(11);
		
		String msg = JSONObject.toJSONString(user);
		//返回消息
		response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
