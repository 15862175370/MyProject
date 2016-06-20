package com.atgem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;

import bean.Barber;

/**
 * Servlet implementation class ShowAllBarberInfo
 */
@WebServlet("/ShowAllBarberInfo")
public class ShowAllBarberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShopDao dao=new ShopDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllBarberInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			ShopDao dao=new ShopDaoImpl();
			String s_id=request.getParameter("s_id");
			String b_id=request.getParameter("b_id");
			
			System.out.println(b_id);
			 if(b_id!=null)
			 {   System.out.println(b_id);
				 deleteBarberById(Integer.parseInt(b_id));
				 
			 }
			
			
			
			   List<Barber> barberList=dao.getAllBarberInfo(Integer.parseInt(s_id));
			  if(barberList!=null){
				
				request.setAttribute("barberList",barberList);
				request.getRequestDispatcher("/showAllBarberInfo.jsp").forward(request, response);
				
				
				
			}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void deleteBarberById(int id){
		dao.deleteBarberById(id);
		
	}
}
