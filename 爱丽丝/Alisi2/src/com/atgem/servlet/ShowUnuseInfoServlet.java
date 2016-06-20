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

import bean.UnuseInfo;

/**
 * Servlet implementation class ShowUnuseInfoServlet
 */
@WebServlet("/ShowUnuseInfoServlet")
public class ShowUnuseInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	            doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String u_id=request.getParameter("u_id");
		System.out.println("用户的id是："+u_id);
		ShopDao dao=new ShopDaoImpl();
        List<UnuseInfo> allUnuseById = dao.getAllUnuseById(Integer.parseInt(u_id));
        Gson gson=new Gson();
        String UnuseInfo = gson.toJson(allUnuseById);
        response.getWriter().println(UnuseInfo);

		
		
	}

}
