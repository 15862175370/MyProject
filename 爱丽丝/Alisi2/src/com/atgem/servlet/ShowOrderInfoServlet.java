package com.atgem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.OrderItem;

/**
 * Servlet implementation class ShowOrderInfoServlet
 */
@WebServlet("/ShowOrderInfoServlet")
public class ShowOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	          String u_id= request.getParameter("u_id");
	          System.out.println("用户的idshi"+u_id);
		    ShopDao dao=new ShopDaoImpl();
	        List<OrderItem> allOrdersById = dao.getAllOrdersById(Integer.parseInt(u_id));
	        Gson gson=new Gson();
	        String OrderInfo = gson.toJson(allOrdersById);
	        System.out.println("数据是："+OrderInfo);
	        response.getWriter().println(OrderInfo);
	
	}

}
