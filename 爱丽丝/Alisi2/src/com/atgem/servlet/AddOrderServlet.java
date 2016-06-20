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
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
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
		ShopDao dao=new ShopDaoImpl();
		String u_id=request.getParameter("u_id");
		String b_id=request.getParameter("b_id");
		String time1=request.getParameter("time1");
		String servid=request.getParameter("servid");
		String state=request.getParameter("state");
		String day_id=request.getParameter("day_id");
		
		
		boolean hasOrder;
		hasOrder=dao.addOrder(Integer.parseInt(u_id),Integer.parseInt(b_id),Integer.parseInt(time1),Integer.parseInt(servid),Integer.parseInt(state),Integer.parseInt(day_id));
	   if(hasOrder){
		  int maxId= dao.getMaxId();
		  System.out.println(maxId);
		   response.getWriter().println(maxId);
	   }else{
		   System.out.println("获取失败....");
	   }
		
	
	
	
	}

}
