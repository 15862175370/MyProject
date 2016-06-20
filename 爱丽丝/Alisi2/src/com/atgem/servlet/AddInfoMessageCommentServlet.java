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

import bean.InfoMessageComment;

/**
 * Servlet implementation class AddInfoMessageCommentServlet
 */
@WebServlet("/AddInfoMessageCommentServlet")
public class AddInfoMessageCommentServlet extends HttpServlet {
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
		response.setContentType("application/json;charset=utf-8");

		ShopDao dao = new ShopDaoImpl();
		String comment = request.getParameter("comment");
		Gson gson = new Gson();
		InfoMessageComment infoMessageComment = gson.fromJson(comment, InfoMessageComment.class);
		System.out.println("wang rong"+infoMessageComment);
		boolean b = dao.addInfoMessageComment(infoMessageComment);
		if(b == true){
			response.getWriter().print(true);
		}else{
			response.getWriter().print(false);
		}
	}
		
		
		
		
	}


