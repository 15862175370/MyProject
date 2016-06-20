package com.atgem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;

/**
 * Servlet implementation class UpdateUserMoneyServlet
 */
@WebServlet("/UpdateUserMoneyServlet")
public class UpdateUserMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//.getWriter().append("Served at: ").append(request.getContextPath());
	    doPost(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   String u_id=request.getParameter("u_id");
	   String money=request.getParameter("money");
		 ShopDao dao=new ShopDaoImpl();
		 boolean hasUpdate;
		hasUpdate= dao.updateUserMoney(Integer.parseInt(money),Integer.parseInt(u_id));
		if(hasUpdate){
			response.getWriter().println("success");
		}else{
			response.getWriter().println("failed");
		}
		
	}

}
