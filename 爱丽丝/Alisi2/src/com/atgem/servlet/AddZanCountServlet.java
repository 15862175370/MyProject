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
 * Servlet implementation class AddZanCountServlet
 */
@WebServlet("/AddZanCountServlet")
public class AddZanCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	             boolean flag;
	        String u_id=request.getParameter("u_id");
	        String m_barber_id=request.getParameter("m_barber_id");
	        ShopDao dao=new ShopDaoImpl();
	        flag=dao.addZancountById(Integer.parseInt(u_id),Integer.parseInt(m_barber_id));
	       System.out.println(flag);      
	             
	             
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
