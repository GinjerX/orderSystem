package com.ps.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ps.property.ResultVO;
import com.ps.property.UserInfoVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/registerServlet.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		request.getParameter("username");
		/*//获取客服端提交的数据
		InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine())!= null) {
			sb.append(line);
		}
		System.out.println(sb.toString());*/
		
		String obj = request.getParameter("userObj");
		
		//java转json
		JSON  json = JSONObject.parseObject(obj);
		UserInfoVO userInfo = JSONObject.toJavaObject(json, UserInfoVO.class);
		System.out.println(userInfo.getAddress());
		
		ResultVO<Object> resultVO = new ResultVO<Object>();
		ShoppingJDBC jdbc = new ShoppingJDBC();
		
		//效验用户名是否已经存在
		UserInfoVO user =jdbc.findUserInfoByUsername(userInfo.getUsername());
		//UserInfoVO user =new UserInfoVO();
		if(user == null) {
			jdbc.addUserInfo(userInfo);
			resultVO.setMsg("注册成功");
		}else {
			resultVO.setError(true);
			resultVO.setMsg("用户名已经存在");
		}
		String msg = JSONObject.toJSONString(resultVO);
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
