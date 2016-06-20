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

import bean.ChangeUser;

/**
 * Servlet implementation class ChangeUserIconServlet
 */
@WebServlet("/ChangeUserIconServlet")
@MultipartConfig
public class ChangeUserIconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("==================android:JsonPostsPictureServlet===============");
	
		String name = request.getParameter("name");
		System.out.println("name="+name);
		
		ApplicationPart part = (ApplicationPart) request.getPart("file");
		System.out.println("part="+part);
		
		String path = request.getServletContext().getRealPath("/upload");
		File dir = new File(path);
	
		String picture = request.getParameter("picture");
		int  u_id = Integer.parseInt(request.getParameter("u_id"));
		System.out.println(picture+"   "+u_id);
		
		if(!dir.exists()){

		dir.mkdir();
		System.out.println("创建文件夹 了");

		}
		
		System.out.println(dir.getAbsolutePath());
		String fileName = UUID.randomUUID().toString();

		String contentType = part.getContentType();

		System.out.println("contentType="+contentType);

		String fileNamePart = part.getFilename();

		String fileNameSubmit = part.getSubmittedFileName();
		System.out.println("fileNamePart="+fileNamePart);


		System.out.println("fileNameSubmit="+fileNameSubmit);


		File file = new File(dir,fileName+".jpg");

		System.out.println(file+"wangrong");
		System.out.println(file.getPath());
		String p = "http://10.202.1.52:8080/Alisi2/upload/"+fileName+".jpg";
		part.write(file.getPath());
		response.getWriter().print("save file successful");
		ChangeUser changeUser = new ChangeUser(u_id, p);
		ShopDao dao = new ShopDaoImpl();
		dao.changeUserIconById(changeUser);
		
	}

}
