package com.atgem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.InfoMessage;

/**
 * Servlet implementation class InfoMessageServlet
 */
@WebServlet("/InfoMessageServlet")
public class InfoMessageServlet extends HttpServlet {
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
		InfoMessage infoMessage = dao.getInfoMessageByIm_id(1);
		Gson gson = new Gson();
		String iminfo = gson.toJson(infoMessage);
		System.out.println(iminfo+"aaaaaa");
		response.getWriter().println(iminfo);
	}

}
