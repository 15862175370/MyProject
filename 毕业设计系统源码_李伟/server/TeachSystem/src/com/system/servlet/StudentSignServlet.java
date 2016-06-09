package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.st.utils.GetIp;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class StudentSignServlet
 */
@WebServlet("/StudentSignServlet")
public class StudentSignServlet extends HttpServlet {
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
		String cno=request.getParameter("cno");
		System.out.println(cno);
		System.out.println(sno);
		String ip=GetIp.getIpAddr(request);
		Dao dao=Dao.getInstance();
		int judge=dao.judgeSign(Integer.parseInt(sno),Integer.parseInt(cno));
		System.out.println(judge);
		if(judge==1){
			Gson g=new Gson();
			String json=g.toJson(judge);
			response.getWriter().println(json);
		}else{
			boolean f=dao.judge(Integer.parseInt(sno),Integer.parseInt(cno));
			if(f){
				boolean t=dao.changeSignState(Integer.parseInt(cno), Integer.parseInt(sno));
				Gson g=new Gson();
				String json=g.toJson(t);
				response.getWriter().println(json);
			}else{
		boolean flag=dao.insertIp(Integer.parseInt(cno), Integer.parseInt(sno), ip);
		Gson g=new Gson();
		String json=g.toJson(flag);
		response.getWriter().println(json);
			}
		}
		
	}

}
