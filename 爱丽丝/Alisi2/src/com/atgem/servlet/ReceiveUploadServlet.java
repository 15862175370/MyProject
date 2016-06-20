package com.atgem.servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationPart;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;

/**
 * Servlet implementation class ReceiveUploadServlet
 */
@WebServlet("/ReceiveUploadServlet")
@MultipartConfig
public class ReceiveUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	       String content=request.getParameter("content");
	       String u_id=request.getParameter("u_id");
	       String m_barber_id=request.getParameter("m_barber_id");
	       
	        System.out.println("content="+content+"帖子id是："+m_barber_id);
	        ApplicationPart part=(ApplicationPart)request.getPart("file");
	        //D:\\Users\\Administrator\\workspace\\Alisi2\\WebContent\\upload\\"+fileName+ext
	        String path=request.getServletContext().getRealPath("/upload");
	      
	         File dir=new File(path);  
	         if(!dir.exists()){
	        	 dir.mkdirs();
	         }
		  String fileName=UUID.randomUUID().toString();
		  String fileNamePart=part.getSubmittedFileName();
		  File file=new File(dir,fileName+fileNamePart);
		  part.write(file.getPath());
		  ShopDao dao=new ShopDaoImpl();
		 
		  boolean flag=false;
		flag= dao.addPostComment(Integer.parseInt(u_id),Integer.parseInt(m_barber_id), content,file.getPath());
		  System.out.println(flag);
		  
		  
		  
		  
		  
		   response.getWriter().println("save file successful");
		
		
		
	}

}
