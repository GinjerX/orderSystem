package com.ps.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.ResultVO;
import com.ps.property.UserInfoVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/userLoginServlet")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置服务器编码
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username +"   user"+password+"----password");
		
		ShoppingJDBC jdbc = new ShoppingJDBC();
		
		UserInfoVO users = new UserInfoVO();
		users.setUsername(username);
		
		System.out.println(users.getUsername()+"   adfdfds  "+users.getPassword());
		
		UserInfoVO user = jdbc.findUserInfoByUsername(users.getUsername());
		
		ResultVO<Object> resultVO = new ResultVO<Object>();
		if(user== null){
			resultVO.setError(true);
			resultVO.setMsg("用户名不存在");
			System.out.println(resultVO);
		}else if(!(password.equals(user.getPassword()))) {
			resultVO.setError(true);
			resultVO.setMsg("密码输入错误");
			System.out.println(resultVO);
		}else {
			resultVO.setBody(user);
		}
		
		String msg = JSONObject.toJSONString(resultVO);
		//返回消息
		System.out.println(msg);
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
