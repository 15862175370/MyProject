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

import bean.AllInfoMessage;

/**
 * Servlet implementation class AllInfoMessageServlet
 */
@WebServlet("/AllInfoMessageServlet")
public class AllInfoMessageServlet extends HttpServlet {
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
		ShopDaoImpl dao = new ShopDaoImpl();
		List<AllInfoMessage> allInfoMesslist = dao.getAllInfoMessage();
		Gson gson = new Gson();
		String allinfomesslist = gson.toJson(allInfoMesslist);
		response.getWriter().println(allinfomesslist);
		
	}

}
