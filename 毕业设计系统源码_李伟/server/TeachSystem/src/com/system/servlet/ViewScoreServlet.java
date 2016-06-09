package com.system.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.st.utils.GetIp;
import com.stm.bean.ViewScoreEntry;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class ViewScoreServlet
 */
@WebServlet("/ViewScoreServlet")
public class ViewScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		int sno=Integer.parseInt(request.getParameter("sno"));
		System.out.println(GetIp.getIpAddr(request));
		Dao dao=Dao.getInstance();
		List<ViewScoreEntry>list=dao.viewMyScoreBySno(sno);
		Gson gson=new Gson();
		String json=gson.toJson(list);
		response.getWriter().println(json);
	}

}
