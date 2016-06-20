package com.atgem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    String name=request.getParameter("username");
	    String password=request.getParameter("password");
	   /* ShopDao dao=new ShopDaoImpl();  
	   int find= dao.findShopByShopNameAndPassword(name, password);
	   System.out.println(find);
	    request.getSession().setAttribute("username", name);
	    request.getSession().setAttribute("s_id",find);

  if(find!=0){*/
	 
	 request.getRequestDispatcher("/shopIndex.jsp").forward(request, response);
               PrintWriter writer = response.getWriter();
                writer.println("Success");
             
  
  
 /* }else{
	 request.getRequestDispatcher("/fail.html").forward(request, response);
  }*/
	
	
}
	
	
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
