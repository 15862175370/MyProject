package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stm.bean.NiChengAndIcon;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class GetNiChengAndIconServlet
 */
@WebServlet("/GetNiChengAndIconServlet")
public class GetNiChengAndIconServlet extends HttpServlet {
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
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String suserName=request.getParameter("suserName");
		String tuserName=request.getParameter("tuserName");
                   System.out.println(tuserName+"哈哈哈哈");
                   System.out.println(suserName+"哈哈哈哈");
		Dao dao=Dao.getInstance();
		if(suserName!=null&&!suserName.equals("")){
			System.out.println(tuserName);
			NiChengAndIcon niChengAndIcon=dao.getNiChengAndIconBySno(Integer.parseInt(suserName));
			if(niChengAndIcon!=null){
			Gson g=new Gson();
			String json=g.toJson(niChengAndIcon);
			response.getWriter().println(json);
			}
		}
		
		if(tuserName!=null&&!tuserName.equals("")){
			NiChengAndIcon niChengAndIcon=dao.getNiChengAndIconByTno(Integer.parseInt(tuserName));
			if(niChengAndIcon!=null){
				Gson g=new Gson();
				String json=g.toJson(niChengAndIcon);
				response.getWriter().println(json);
				}
		}
	}

}
