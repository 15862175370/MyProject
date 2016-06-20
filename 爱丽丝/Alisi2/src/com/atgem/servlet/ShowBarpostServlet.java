package com.atgem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.BarpostItem;

/**
 * Servlet implementation class ShowBarpostServlet
 */
@WebServlet("/ShowBarpostServlet")
public class ShowBarpostServlet extends HttpServlet {
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
		            PrintWriter out = response.getWriter();
		  ShopDao dao=new ShopDaoImpl();
		  List<BarpostItem> items=dao.getAllBarpost();
		     Gson gson=new Gson();
		  String allInfo= gson.toJson(items);
		  out.println(allInfo);
		
		
		
	}

}
