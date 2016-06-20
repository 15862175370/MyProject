package com.atgem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.LastedVersionInfo;

/**
 * Servlet implementation class LastedVersionInfo
 */
@WebServlet("/LastedVersionInfoServlet")
public class LastedVersionInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LastedVersionInfo versionInfo=new LastedVersionInfo();
		versionInfo.setVersionCode(2);
		versionInfo.setVersionName("2.1");
		versionInfo.setDesc("新增预约新功能，赶快更新吧....");
		versionInfo.setDowunLoadUrl("####");
		Gson gson=new Gson();
		String json = gson.toJson(versionInfo);
		System.out.println("为什么程序突然崩溃！");
		
		
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
