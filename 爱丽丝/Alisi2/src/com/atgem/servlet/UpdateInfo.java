package com.atgem.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bean.ChangeUser;

/**
 * Servlet implementation class UpdateInfo
 */
@WebServlet("/UpdateInfo")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");

		ShopDao dao = new ShopDaoImpl();
		String Stringlist = request.getParameter("info");
		System.out.println(Stringlist);
		Gson gson = new Gson();
//		Gson user = gson.fromJson(Stringlist);
		ChangeUser list = gson.fromJson(Stringlist, ChangeUser.class);
	

		boolean b = dao.updateUser(list);
		if(b==true){
			response.getWriter().print(true);
		}else{
			response.getWriter().print(false);
		}
	}

}
