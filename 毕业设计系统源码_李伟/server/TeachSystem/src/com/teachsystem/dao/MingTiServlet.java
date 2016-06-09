package com.teachsystem.dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class MingTiServlet
 */
@WebServlet("/MingTiServlet")
public class MingTiServlet extends HttpServlet {
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
		String cno=request.getParameter("cno");
		String question=request.getParameter("question");
		String question_a=request.getParameter("question_a");
		String question_b=request.getParameter("question_b");
		String question_ok=request.getParameter("question_zhengque");
		Dao dao=Dao.getInstance();
		boolean flag=dao.MingTi(Integer.parseInt(cno), question, question_a, question_b, question_ok);
		Gson g=new Gson();
		String json=g.toJson(flag);
		response.getWriter().println(json);
		
	}

}
