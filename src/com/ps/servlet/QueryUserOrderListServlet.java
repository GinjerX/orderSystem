package com.ps.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ps.property.OrderQueryVO;
import com.ps.storJdbc.QueryUserOrderListJDBC;

/**
 * Servlet implementation class QueryUserOrderListServlet
 */
@WebServlet("/queryOrderList")
public class QueryUserOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryUserOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		
		String state = request.getParameter("state");
		String userId = request.getParameter("userId");
		System.out.println(userId+"   useridajdflasdjfld");
		
		QueryUserOrderListJDBC jdbc = new QueryUserOrderListJDBC();
		List<OrderQueryVO> orderquery=null;
		if(state !=null) {
			orderquery = jdbc.getQuery(state,userId);
		}else {
			orderquery = jdbc.Query(userId);
		}
		
		String msg = JSONObject.toJSONString(orderquery);
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
