package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class RegistCourseServlet
 */
@WebServlet("/RegistCourseServlet")
public class RegistCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String cno=request.getParameter("cno");
		String cname=request.getParameter("cname");
		String ctime=request.getParameter("ctime");
		String cadress=request.getParameter("cadress");
		String content=request.getParameter("content");
		String tno=request.getParameter("tno");
		Dao dao=Dao.getInstance();
		boolean flag=dao.registCourse(Integer.parseInt(cno), cname, ctime, Integer.parseInt(tno), cadress, content);
		
		Gson g=new Gson();
		response.getWriter().println(g.toJson(flag));
	
	}

}
