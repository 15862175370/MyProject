package com.atgem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.User;

/**
 * Servlet implementation class AndroidLoginServlet
 */
@WebServlet("/AndroidLoginServlet")
public class AndroidLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	
		 ShopDao dao=new ShopDaoImpl();
		  String phone = request.getParameter("phone");
		 
		  
		  boolean boolean1 = dao.addUserByPhone(phone);
		  
		  if(boolean1){
			  System.out.println(boolean1);
			   User userInfoById = dao.getUserInfoById(phone);
			   Gson gson=new Gson();
			   String json = gson.toJson(userInfoById);
			   System.out.println("插入馨的用户id是："+userInfoById.getU_id());
			  PrintWriter out = response.getWriter();

				out.write(json);

		  }else{
			  response.getWriter().println("插入失败....");
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
