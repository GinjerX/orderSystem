package com.ps.servlet;

import java.io.IOException;
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
 * Servlet implementation class QueryMenuById
 */
@WebServlet("/QueryMenuById")
public class QueryMenuByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryMenuByIdServlet() {
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
		
		String menuId = request.getParameter("menuId");
		ResultVO resultVO = new ResultVO();
		ShoppingJDBC jdbc = new ShoppingJDBC();
		MenuVO menuVo = jdbc.QueryMenuById(menuId);
		if (menuVo == null) {
			resultVO.setError(true);
			resultVO.setMsg("未查询到菜单信息！");
		} else {
			resultVO.setBody(menuVo);
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
