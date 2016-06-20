package com.atgem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.daoImpl.ShopDaoImpl;

/**
 * Servlet implementation class UseConfirmCode
 */
@WebServlet("/UseConfirmCode")
public class UseConfirmCode extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		String f=request.getParameter("f");
		ShopDaoImpl dao=new ShopDaoImpl();
		boolean flag=dao.useCode(f);
		if(flag){
			response.getWriter().println("验证码已使用....");
		}else{
			response.getWriter().println("重置使用状态码失败....");
		}
	}

}
