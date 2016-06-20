package com.atgem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.ServcePrice;

/**
 * Servlet implementation class FindServicePriceServlet
 */
@WebServlet("/FindServicePriceServlet")
public class FindServicePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String bar_id=request.getParameter("bar_id");
		System.out.println(bar_id);
		ShopDaoImpl a=new ShopDaoImpl();
		List<ServcePrice> sp=a.getAllServcePriceByBar_id(Integer.parseInt(bar_id));
		
	
		Gson g=new Gson();
		String sp1=g.toJson(sp);
		System.out.println(sp1);
		response.getWriter().println(sp1);
	}

}
