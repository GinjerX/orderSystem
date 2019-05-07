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
import com.ps.property.ResultVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuAllServlet")
public class QueryAllMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String menuName = request.getParameter("menusname");
		System.out.println(menuName+"   menuname   dsdfsf");
		ShoppingJDBC jdbc = new ShoppingJDBC();
		
		
		//查询所有的菜品
		List<MenuVO> ms=null;
		if(menuName != null) {
			ms = jdbc.menuVOQuery(menuName);
		}else {
			ms = jdbc.findAllMenuInfoList();
		}
		ResultVO resultVO = new ResultVO<>();
		resultVO.setBody(ms);
		
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
