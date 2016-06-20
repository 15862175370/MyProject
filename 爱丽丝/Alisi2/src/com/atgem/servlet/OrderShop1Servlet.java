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

import bean.OrderShop;

/**
 * Servlet implementation class OrderShop1Servlet
 */
@WebServlet("/OrderShop1Servlet")
public class OrderShop1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	ShopDao a=new ShopDaoImpl();
	List<OrderShop> list=a.getAllOrderShop();
	Gson g=new Gson();
	String json=g.toJson(list);
	response.getWriter().println(json);
	}

}
