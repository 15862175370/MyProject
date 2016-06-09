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
 * Servlet implementation class ChangePassWordServlet
 */
@WebServlet("/ChangePassWordServlet")
public class ChangePassWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	this.doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String userName=request.getParameter("userName");
		String studentUserName=request.getParameter("studentuserName");
		String pass=request.getParameter("passWord");
		if(userName==null){
			userName="";
		}
		if(!userName.equals("")){
		
		Dao dao=Dao.getInstance();
		boolean flag=dao.changePassWord(Integer.parseInt(userName), pass);
		Gson g=new Gson();
		String json=g.toJson(flag);
		response.getWriter().println(json);
		}
		if(studentUserName==null){
			studentUserName="";
		}
		if(!studentUserName.equals("")){
			Dao dao=new Dao();
			boolean flag=dao.changePassWordBySno(Integer.parseInt(studentUserName), pass);
			Gson g=new Gson();
			String json=g.toJson(flag);
			response.getWriter().println(json);
		}
		
	}

}
