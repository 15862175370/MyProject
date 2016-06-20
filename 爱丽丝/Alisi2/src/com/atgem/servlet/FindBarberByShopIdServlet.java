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
import com.google.gson.Gson;

import bean.FixBarber;

/**
 * Servlet implementation class FindBarberByShopIdServlet
 */
@WebServlet("/FindBarberByShopIdServlet")
public class FindBarberByShopIdServlet extends HttpServlet {
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
		
		String id=request.getParameter("id");	
		System.out.println(id);
		ShopDaoImpl a=new ShopDaoImpl();
		
		List<FixBarber> list=a.getAllBarberByShopId(Integer.parseInt(id));
		
			//发送到手机端
		Gson g=new Gson();
			
		String json1=g.toJson(list);
		System.out.println(json1);
	response.getWriter().println(json1);
	}

}
