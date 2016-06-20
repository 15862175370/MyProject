package com.atgem.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.atgem.dao.ShopDao;
import com.atgem.daoImpl.ShopDaoImpl;

/**
 * Servlet implementation class UpdateBarberServlet
 */
@WebServlet("/UpdateBarberServlet")
@MultipartConfig
public class UpdateBarberServlet extends HttpServlet {
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
		
		// response.setCharacterEncoding("utf-8");
		//  response.setContentType("text/html;charset=utf-8");
		  Part part = request.getPart("icon");
			String mime = part.getContentType();//mime类型
			String ext = "";//image/png    image/jpeg
			if("image/png".equals(mime)){
				ext = ".png";
			}else{   
				response.getWriter().println("您上传的文件类型不合法");
				return;
			}
			//写入文件
			String fileName = UUID.randomUUID().toString();
			String path = getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()){   
				dir.mkdir();
			}
			File file = new File(dir,fileName+ext);
			//写入工程:发布时删除此段代码
			FileOutputStream fos = new FileOutputStream("D:\\Users\\Administrator\\workspace\\Alisi2\\WebContent\\upload\\"+fileName+ext);
			File filePro = new File("D:\\Users\\Administrator\\workspace\\Alisi2\\WebContent\\upload\\"+fileName+ext);
			part.write(filePro.getPath());
			//结束  http://localhost:8080/Alisi2/upload/b260c4ac-a48c-40b2-8086-c95b2a63287c.jpg
			part.write(file.getPath());  
			String url1="http://localhost:8080/Alisi2/upload/";
			String url=url1+fileName+ext;
			//把文件路径保存到数据库     
		ShopDao dao=new ShopDaoImpl();
	   String b_position=request.getParameter("b_position");
	   String b_id=(String) request.getParameter("b_id");
	 
	   String [] servTypes=request.getParameterValues("types");
	   String wash= request.getParameter("wash");
	  
	   String tang=request.getParameter("tang");
	   String ran=request.getParameter("ran");
	   String zaoxing=request.getParameter("zaoxing");
	   String b_desc=request.getParameter("b_desc");
	   
	    dao.updateIcon(url, Integer.parseInt(b_id));
	   
	   if(b_position!=null){
		   dao.updatePosition(Integer.parseInt(b_id), b_position);
	   }
	   
	   if(b_desc!=null){
		   System.out.println(b_desc);
		   dao.updateDesc(Integer.parseInt(b_id), b_desc);
	   }
	   
	   
	   if(wash!=null){
		    int n=1;
		    dao.updateServType(n, Integer.parseInt(b_id), wash);
		   
		    
		    response.getWriter().println("修改成功....");
	   }
	   
	   
	
	}

}
