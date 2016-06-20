package com.atgem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class OrderBarberServlet
 */
@WebServlet("/OrderBarberServlet")
public class OrderBarberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	                 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		
		String time=request.getParameter("time");
		String day=request.getParameter("day");
		String b_id=request.getParameter("b_id");
		System.out.println("时间段是："+time+"哪一天："+day+"理发师id是："+b_id);
		ShopDao a=new ShopDaoImpl();
		boolean flag=a.updateState(Integer.parseInt(b_id), day, time);
		System.out.println(flag);
		Gson g=new Gson();
		
		String f=g.toJson(flag);
		response.getWriter().println(f);
		
		
	}

}
