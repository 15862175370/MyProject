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
 * Servlet implementation class OkChooseCourseServlet
 */
@WebServlet("/OkChooseCourseServlet")
public class OkChooseCourseServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String sno=request.getParameter("sno");
		String cno=request.getParameter("cno");
		if(sno==null){
			sno="";
		}
		if(cno==null){
			cno="";
		}
		Dao dao=Dao.getInstance();
		String flag1=dao.checkIsChooseCourseBySnoAndCno(Integer.parseInt(sno), Integer.parseInt(cno));
		if(flag1.equals("未选课")){
		boolean flag=dao.chooseCourse(Integer.parseInt(cno), Integer.parseInt(sno));
		Gson g=new Gson();
		String json=g.toJson(flag);
		response.getWriter().println(json);
		}else{
			response.getWriter().println("该课程已选，不能重复选择");
		}
		
	}

}
