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

/**
 * Servlet implementation class SendActivityContentServlet
 */
@WebServlet("/SendActivityContentServlet")
public class SendActivityContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String u_id=request.getParameter("u_id");
	String content=request.getParameter("content");
	System.out.println("评论内容是:"+content);
	String activity_id=request.getParameter("activity_id");
	ShopDao a=new ShopDaoImpl();
	boolean flag=a.sendActivityComment(Integer.parseInt(u_id), content, Integer.parseInt(activity_id));
	Gson g=new Gson();
	String json=g.toJson(flag);
	response.getWriter().println(json);
	}

}
