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
 * Servlet implementation class TeacherReplyStudentQuestionServlet
 */
@WebServlet("/TeacherReplyStudentQuestionServlet")
public class TeacherReplyStudentQuestionServlet extends HttpServlet {
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
		int tno=Integer.parseInt(request.getParameter("tno"));
	int sno=Integer.parseInt(request.getParameter("sno"));
		int sq_id=Integer.parseInt(request.getParameter("sq_id"));
		String  content=request.getParameter("content");
		Dao dao=Dao.getInstance();
		boolean flag=dao.TeacherReplyStudentQuestion(tno, sno, sq_id, content);
		Gson g=new Gson();
		String json=g.toJson(flag);
		response.getWriter().println(json);
	}

}
