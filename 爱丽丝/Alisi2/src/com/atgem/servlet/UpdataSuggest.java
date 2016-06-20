package com.atgem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

import bean.Suggest;

/**
 * Servlet implementation class UpdataSuggest
 */
@WebServlet("/UpdataSuggest")
public class UpdataSuggest extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String suggest = request.getParameter("suggest");
		System.out.println(suggest);
		Gson gson = new Gson();
		Suggest suggestInfo = gson.fromJson(suggest, Suggest.class);
		
		boolean b = dao.insertSuggest(suggestInfo);
		if(b == true){
			response.getWriter().print(true);
		}else{
			response.getWriter().print(false);
		}
	}

}
