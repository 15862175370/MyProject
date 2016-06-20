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
 * Servlet implementation class DeleteCollection
 */
@WebServlet("/DeleteCollection")
public class DeleteCollection extends HttpServlet {
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
		//doGet(request, response);
		
		String u_id=request.getParameter("u_id");
		String b_id=request.getParameter("b_id");
		ShopDao a=new ShopDaoImpl();
	//boolean flag=a.deleteCollection(1,4);
	boolean flag=a.deleteCollection(Integer.parseInt(u_id),Integer.parseInt(b_id));
	Gson g=new Gson();
	String json=g.toJson(flag);
	response.getWriter().println(json);
		
		
	}

}
