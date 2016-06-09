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
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
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
		String sno=request.getParameter("sno");
		String sname=request.getParameter("sname");
		String sage=request.getParameter("sage");
		String phone=request.getParameter("phone");
		String dno=request.getParameter("dno");
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		String ssex=request.getParameter("ssex");
		Dao dao=Dao.getInstance();
		boolean flag=dao.addStudent(Integer.parseInt(sno), sname, ssex, Integer.parseInt(sage), phone, Integer.parseInt(dno), user, pass);
	Gson g=new Gson();
	String json=g.toJson(flag);
	response.getWriter().println(json);
	}

}
