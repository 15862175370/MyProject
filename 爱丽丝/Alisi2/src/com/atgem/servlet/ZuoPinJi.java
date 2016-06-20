package com.atgem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.daoImpl.ShopDaoImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ZuoPinJi
 */
@WebServlet("/ZuoPinJi")
public class ZuoPinJi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	            doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String b_id=request.getParameter("b_id");
		
		String currentPage=request.getParameter("currentPage");
		ShopDaoImpl a=new ShopDaoImpl();
		if(currentPage==null){
			currentPage="1";
			List<String>list=a.getIconByPage(3,Integer.parseInt(b_id),Integer.parseInt(currentPage) );
			Gson g=new Gson();
			String json=g.toJson(list);
			response.getWriter().println(json);
		}else {
			int total=a.getBarPostPage(3, Integer.parseInt(b_id));

			int p=(int)(Integer.parseInt(currentPage));
			if (total<p) {
				p=total;
				List<String>list=a.getIconByPage(3,Integer.parseInt(b_id),p );
				Gson g=new Gson();
				String json=g.toJson(list);
				response.getWriter().println(json);
			}else{
		
			List<String>list=a.getIconByPage(3,Integer.parseInt(b_id),p );
			Gson g=new Gson();
			String json=g.toJson(list);
			response.getWriter().println(json);
			}
	}

}
}
