package com.system.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stm.bean.ViewTeacherCourseEnty;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class ViewTeacherCourseServlet
 */
@WebServlet("/ViewTeacherCourseServlet")
public class ViewTeacherCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String tno=request.getParameter("tno");
	System.out.println(tno);
		Dao dao=Dao.getInstance();
		List<ViewTeacherCourseEnty> list=dao.getTeacherCourseByTno(Integer.parseInt(tno));
	Gson g=new Gson();
	if(list!=null){
	String json=g.toJson(list);
	response.getWriter().println(json);
	}
	}

}
