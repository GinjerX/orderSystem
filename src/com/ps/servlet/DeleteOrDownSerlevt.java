package com.ps.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.ResultVO;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class deleteOrDownSerlevt
 */
@WebServlet("/DeleteOrDownSerlevt")
public class DeleteOrDownSerlevt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrDownSerlevt() {
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
		String m = request.getParameter("m");
		
		ResultVO resultVO = new ResultVO();
		ShoppingJDBC jdbc = new ShoppingJDBC();
		if("delete".equals(m)){
			//删除指定的菜单
			jdbc.updateMenuStateByMenuId(menuId,"0");
		}else if("down".equals(m)) {
			//下架指定的菜单
			jdbc.updateMenuStateByMenuId(menuId, "2");
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
