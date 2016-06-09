package com.system.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;

import com.google.gson.Gson;

import com.stm.bean.Student;
import com.stm.bean.Teacher;
import com.stm.bean.UserTokenInfo;
import com.teachsystem.dao.Dao;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

/**
 * Servlet implementation class GetRongToken
 */
@WebServlet("/GetRongToken")
public class GetRongToken extends HttpServlet {
	private String AppKey="8w7jv4qb7eloy";
	private String SecretKey="sSFiE0ZMxzV";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRongToken() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String studentUser=request.getParameter("studentUser");
		String teacherUser=request.getParameter("TeacherUser");
		if(studentUser==null){
			studentUser="";
		}
		if(teacherUser==null){
			teacherUser="";
		}
		
		Dao dao=Dao.getInstance();
		if(!studentUser.equals("")){
			
			String token=dao.getRongToken(Integer.parseInt(studentUser));
			if(!token.equals("")){
				response.getWriter().println(token);
				return;
		
		}else{
			
			Student student=dao.findStudentInfoBySno(Integer.parseInt(studentUser));
			SdkHttpResult result=null;
			try {
				result = ApiHttpClient.getToken(AppKey, SecretKey, String.valueOf(student.getSno()), student.getSname(),
						student.getSicon(), FormatType.json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss=result.getResult();
			Gson gson=new Gson();
			UserTokenInfo userTokenInfo=gson.fromJson(ss, UserTokenInfo.class);
			String studenttoken= userTokenInfo.getToken();
			if(studenttoken!=null&&!studenttoken.equals("")){
			boolean flag=dao.insertRongToken(Integer.parseInt(studentUser),studenttoken );
			if(flag){
				String token1=dao.getRongToken(Integer.parseInt(studentUser));
				if(!token1.equals("")){
					response.getWriter().println(token1);
					return;
				}
			}else{
				
			}
			
			}
			
		}
			
		}
		
		System.out.println(teacherUser);
		System.out.println(teacherUser+"有数");
		
		if(!teacherUser.equals("")){
			String teachertoken=dao.getTeacherRongToken(Integer.parseInt(teacherUser));
			System.out.println(teachertoken+"哈哈哈");
			if(!teachertoken.equals("")){
				response.getWriter().println(teachertoken);
				return;

			}else {
			
			
			Teacher teacher=dao.findTeacherInfoByTno(Integer.parseInt(teacherUser));
			SdkHttpResult result=null;
			try {
				result = ApiHttpClient.getToken(AppKey, SecretKey, String.valueOf(teacher.getTno()), teacher.getTname(),
					teacher.getTicon(), FormatType.json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss=result.getResult();
			Gson gson=new Gson();
			UserTokenInfo userTokenInfo=gson.fromJson(ss, UserTokenInfo.class);
			String teachertoken1= userTokenInfo.getToken();
			System.out.println(teachertoken1);
			if(teachertoken1!=null&&!teachertoken1.equals("")){
				boolean flag=dao.insertTeacherRongToken(Integer.parseInt(teacherUser),teachertoken1 );
				if(flag){
					String token1=dao.getTeacherRongToken(Integer.parseInt(teacherUser));
					if(!token1.equals("")){
						response.getWriter().println(token1);
						return;
					}
				}else{
					
				}
			
			}
		
		}
		}
		if(studentUser.equals("")&&teacherUser.equals("")){
		return;
			
		}
		}
	}


