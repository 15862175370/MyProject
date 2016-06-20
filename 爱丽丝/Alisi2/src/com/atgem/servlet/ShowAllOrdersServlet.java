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


import bean.Entity;
import bean.EntityPage;

/**
 * Servlet implementation class ShowAllOrdersServlet
 */
@WebServlet("/ShowAllOrdersServlet")
public class ShowAllOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   /* ShopDao dao=new ShopDaoImpl();
		String s_id=request.getParameter("s_id");
	     List<Entity> entityList=dao.getAllOrderInfo(Integer.parseInt(s_id));
	             request.setAttribute("entityList", entityList);
	             request.getRequestDispatcher("/showAllOrders.jsp").forward(request, response);*/
	  
	         	ShopDaoImpl dao=new ShopDaoImpl();   
	   	     String cPage=(String) request.getParameter("currentPage");
	   	     String s_id=request.getParameter("s_id");
	   	        if(cPage==null){
	   	        	 cPage="1";
	   	         }
	   	       
	   	       EntityPage userPage=dao.getEntityByPage(Integer.parseInt(cPage), 6,Integer.parseInt(s_id));
	   	       request.setAttribute("userPage", userPage);
	   	       request.getRequestDispatcher("/showAllOrders.jsp").forward(request, response);
	    
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
