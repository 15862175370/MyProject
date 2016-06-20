package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
			String name = request.getParameter("uname");
			System.out.println("name="+name);
			Part part = request.getPart("myfile");
			String mime = part.getContentType();//mime    
			String ext = "";
			if("image/jpeg".equals(mime)){
			ext = ".jpg";
			}else{   
			response.getWriter().println("   ϴ    ļ    Ͳ  Ϸ ");
			return;
			}
			//д   ļ 
			String fileName = UUID.randomUUID().toString();
			String path = getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()){   
			dir.mkdir();
			}
			File file = new File(dir,fileName+ext);
			//д 빤  :    ʱɾ  ˶δ   
			FileOutputStream fos = new FileOutputStream("F:\\teaching\\servlet\\servlet_eclipse_workspace\\WebProject\\WebContent\\upload\\"+fileName+ext);
			File filePro = new File("F:\\teaching\\servlet\\servlet_eclipse_workspace\\WebProject\\WebContent\\upload\\"+fileName+ext);
			part.write(filePro.getPath());
			//    
			part.write(file.getPath());   
			//   ļ ·     浽  ݿ      
			}
		
		
		
		
		
	}


