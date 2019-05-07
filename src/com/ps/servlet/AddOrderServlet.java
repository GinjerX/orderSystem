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
import com.ps.property.AddInformationVO;
import com.ps.property.ShoppingNew;
import com.ps.storJdbc.ShoppingJDBC;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//获取客服端提交的数据
		InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine())!= null) {
			sb.append(line);
		}
		System.out.println(sb.toString()+"--------");
		
		JSON  json = JSONObject.parseObject(sb.toString());
		
		AddInformationVO addInformation = JSONObject.toJavaObject(json, AddInformationVO.class);
		
		
		ShoppingJDBC jdbc = new ShoppingJDBC();
		int orderId = jdbc.addOrder(addInformation.getUserId());//增加订单（购物车下单）
		
		/*String total = request.getParameter("total");//订单下单扣用户的钱
		jdbc.totalmoney(addInformation.getUserId(),total);*/
	
		ShoppingNew n =JSONObject.toJavaObject(json, ShoppingNew.class);	
		
		int [] orderIds = n.getShopping();
		for ( int i : orderIds) {
			System.out.println("i = "+i);
			int [] addred = jdbc.queryMenuAndCount(i);
			jdbc.add(orderId, addred[0], addred[1]);//添加关系表
			jdbc.deleteOrder(i);//购物车删除
			System.out.print(addred[0]+"  "+addred[1]);
		}
		String msg = JSONObject.toJSONString(n);
		//返回消息
		response.getWriter().print(msg);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
