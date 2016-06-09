package com.teachsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import com.st.utils.ConnectionManager;
import com.stm.bean.ChooseCourseEnty;
import com.stm.bean.KaoQin;
import com.stm.bean.MyStudentBean;
import com.stm.bean.MyTeacher;
import com.stm.bean.NiChengAndIcon;
import com.stm.bean.QuestionAndAnswer;
import com.stm.bean.Student;
import com.stm.bean.StudentAskQuestion;
import com.stm.bean.StudentMessageBean;
import com.stm.bean.TeachNotify;
import com.stm.bean.Teacher;
import com.stm.bean.TestCourse;
import com.stm.bean.ViewScoreEntry;
import com.stm.bean.ViewTeacherAdviceBean;
import com.stm.bean.ViewTeacherCourseEnty;
import com.system.servlet.SubmitQuestion;



public class Dao {
	private static Dao dao;
	
	public static  Dao getInstance(){
		if(dao==null){
			dao=new Dao();
		}
		return dao;
		
	}
	/**
	 * 根据教工号获得某个教师的信息
	 * @param tno
	 * @return
	 */
	public Teacher findTeacherInfoByTno(int tno){
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try {
	 Connection con=ConnectionManager.getConnection();
		
			psmt=con.prepareStatement("select * from teacher where Tno=?");
			psmt.setInt(1, tno);
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
				int Tno=resultSet.getInt("Tno");
				String Tname=resultSet.getString("Tname");
				String Tsex=resultSet.getString("Tsex");
				String Tage=resultSet.getString("Tage");
				String Ticon=resultSet.getString("Ticon");
				String Dno=resultSet.getString("Dno");
				String Tprof=resultSet.getString("Tprof");
				String Tuser=resultSet.getString("Tuser");
				String Tpass=resultSet.getString("Tpass");
			Teacher teacher=new Teacher(Tno, Tname, Tsex, Tage, Ticon, Dno, Tprof, Tuser, Tpass);
			return teacher;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
		
	/**
	 * 根据学号获得某个学生的信息
	 * @param tno 学号
	 */
	public Student findStudentInfoBySno(int sno){
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try {
		Connection con=ConnectionManager.getConnection();
		
			psmt=con.prepareStatement("select * from student where Sno=?");
			psmt.setInt(1, sno);
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
				int Sno=resultSet.getInt("Sno");
				String Sname=resultSet.getString("Sname");
				String Ssex=resultSet.getString("Ssex");
				String Sicon=resultSet.getString("Sicon");
			int Sage=resultSet.getInt("Sage");
				int Dno=resultSet.getInt("Dno");
				String Suser=resultSet.getString("Suser");
				String SpassWord=resultSet.getString("SpassWord");
			Student student=new Student(Sno, Sname, Ssex, Sicon, Sage, Dno, Suser, SpassWord);
			return student;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得教学新闻	
	 */
	public List<TeachNotify> getTeachNotify(){
		List<TeachNotify> list=new ArrayList<TeachNotify>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from teachnotify order by notify_time desc");
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
			int notify_id=resultSet.getInt("notify_id");
			String notify_content=resultSet.getString("notify_content");
			Timestamp notify_time=resultSet.getTimestamp("notify_time");
			TeachNotify teachNotify=new TeachNotify(notify_id, notify_content, notify_time);
			list.add(teachNotify);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
		
	}
	/**
	 * 检查教师登录账号是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean isCheckAccount(String username,String password){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try {
			pst=con.prepareStatement("select * from teacher where Tuser=? and Tpass=?");
			pst.setString(1, username);
			pst.setString(2, password);
			resultSet=pst.executeQuery();
			while(resultSet.next()){
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 检查学生登录账号是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean isStudentCheckAccount(String username,String password){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try {
			pst=con.prepareStatement("select * from student where Suser=? and SpassWord=?");
			pst.setString(1, username);
			pst.setString(2, password);
			resultSet=pst.executeQuery();
			while(resultSet.next()){
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 检查管理员登录账号是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean isCheckAdministratorAccount(String username,String password){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try {
			pst=con.prepareStatement("select * from administretor where username=? and pass=?");
			pst.setString(1, username);
			pst.setString(2, password);
			resultSet=pst.executeQuery();
			while(resultSet.next()){
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 根据用户名修改密码
	 * @param username 教工用户名
	 * @param pass 密码
	 */
	public boolean changePassWord(int username,String pass){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try{
			pst=con.prepareStatement("update teacher set Tpass=? where Tno=?");
			pst.setString(1,pass);
			pst.setInt(2, username);
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public boolean changePassWordBySno(int sno,String pass){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try{
			pst=con.prepareStatement("update student set  SpassWord=? where Sno=?");
			pst.setString(1,pass);
			pst.setInt(2, sno);
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
		
	}
	/**
	 * 教师发布通知
	 * @param content
	 * @return
	 */

	public boolean sendMessage(String content){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try{
			pst=con.prepareStatement("insert into teachnotify (notify_content)values(?)");
			pst.setString(1,content);
			
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 教师注册课程
	 * @param cno 课程号
	 * @param cname 课程名
	 * @param ctime 课时
	 * @param tno 教工号
	 * @param cadress 上课地点
	 * @param content 课程内容
	 * @return
	 */
	public boolean registCourse(int cno,String cname,String ctime,int tno,String cadress,String content){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try{
			pst=con.prepareStatement("insert into course (cno,cname,classtime,Tno,cadress,content)values(?,?,?,?,?,?)");
			pst.setInt(1,cno);
			pst.setString(2, cname);
			pst.setString(3, ctime);
			pst.setInt(4,tno);
			pst.setString(5, cadress);
			pst.setString(6, content);
		
			
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 获得教师注册的课程
	 * @return
	 */
	public List<ChooseCourseEnty> getRegistCourse() {
		List<ChooseCourseEnty> list=new ArrayList<ChooseCourseEnty>();
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement("select *from choosecourse order by cno asc");
			rs=pst.executeQuery();
			while (rs.next()) {
				int cno=rs.getInt("cno");
				String cname=rs.getString("cname");
				String classtime=rs.getString("classtime");
				int tno=rs.getInt("Tno");
				String tname=rs.getString("Tname");
				String cadress=rs.getString("cadress");
				String content=rs.getString("content");
				ChooseCourseEnty chooseCourseEnty=new ChooseCourseEnty(cno, cname, classtime, cadress, content, tname, tno);
				list.add(chooseCourseEnty);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}
	/**
	 * 选课
	 * @param cno 课程号
	 * @param sno 学号
	 * @return
	 */
	public boolean chooseCourse(int cno,int sno){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try{
			pst=con.prepareStatement("insert into sc (Sno,cno)values(?,?)");
			pst.setInt(1,sno);
			pst.setInt(2,cno);
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 根据学号获得所选择的课程
	 * @param sno
	 * @return
	 */
	
	public List<ChooseCourseEnty>  getChooseCourse(int sno){
		List<ChooseCourseEnty> list=new ArrayList<ChooseCourseEnty>();
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=con.prepareStatement("select *from mycourse where sno=? order by cno asc");
			pst.setInt(1,sno);
			rs=pst.executeQuery();
			while (rs.next()) {
				int cno=rs.getInt("cno");
				String cname=rs.getString("cname");
				String classtime=rs.getString("classtime");
				int tno=rs.getInt("Tno");
				String tname=rs.getString("Tname");
				String cadress=rs.getString("cadress");
				String content=rs.getString("content");
				ChooseCourseEnty chooseCourseEnty=new ChooseCourseEnty(cno, cname, classtime, cadress, content, tname, tno);
				list.add(chooseCourseEnty);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 * 根据课程号查找相应的题目
	 * @param cno 课程号
	 */
	public List<TestCourse> getTestCourseQuestion(int cno){
		List<TestCourse>list=new ArrayList<TestCourse>();
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=null;
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("select *from qusetion where cno=?");
			pst.setInt(1, cno);
			rs=pst.executeQuery();
			while(rs.next()){
				int qusetion_id=rs.getInt("qusetion_id");
				String question_content=rs.getString("question_content");
				String answer_content_a=rs.getString("answer_content_a");
				String answer_content_b=rs.getString("answer_content_b");
				String answer_correct=rs.getString("answer_correct");
				int cno1=rs.getInt("cno");
				TestCourse testCourse=new TestCourse(qusetion_id, question_content, cno1, answer_content_a, answer_content_b, answer_correct);
			list.add(testCourse);
			
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	
	}
	/**
	 * 根据学号和课程号插入学生的成绩
	 * @param sno
	 * @param cno
	 * @param score
	 * @return
	 */
	public boolean insetScoreBySnoAndCno(int sno,int cno,int score){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try{
			pst=con.prepareStatement("update sc set score=? where Sno=? and cno=?");
			pst.setInt(1,score);
			pst.setInt(2,sno);
			pst.setInt(3,cno);
			int m=  pst.executeUpdate();
			if(m>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据客户端传过来的学号查询我的成绩
	 * @param sno
	 * @return
	 */
	
	public List<ViewScoreEntry> viewMyScoreBySno(int sno){
		
		List<ViewScoreEntry> list=new ArrayList<ViewScoreEntry>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from score where sno=?");
		psmt.setInt(1, sno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
			int sno1=resultSet.getInt("sno");
			String sname=resultSet.getString("sname");
			String  cname=resultSet.getString("cname");
			int score=resultSet.getInt("score");
		ViewScoreEntry viewScoreEntry=new ViewScoreEntry(sno1, sname, cname, score);
		list.add(viewScoreEntry);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 根据课程号获得教师号
	 * @param cno
	 * @return
	 */
	public int getTnoByCno(int cno){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		con=ConnectionManager.getConnection();
		try {
			pst=con.prepareStatement("select Tno from course where cno=?");
			pst.setInt(1, cno);
			rs=pst.executeQuery();
			while(rs.next()){
				int Tno=rs.getInt("Tno");
				return Tno;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
		
	}
	/**
	 * 提交问题
	 * @param sno 学号
	 * @param cno 课程号
	 * @param content 内容
	 */
	public boolean SubmitQuestion(int sno,int cno,String content){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		con=ConnectionManager.getConnection();
		int tno=getTnoByCno(cno);
		try {
			if(tno!=0){
			pst=con.prepareStatement("insert into submitquestion(sno,Tno,sq_content)values(?,?,?)");
			pst.setInt(1, sno);
			pst.setInt(2, tno);
			pst.setString(3, content);
			}
			int m=pst.executeUpdate();
			if(m>0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 根据教师的教工号查找给这个老师提的问题
	 * @param tno教工号
	 */
	
	public List<StudentAskQuestion> viewStudentQuestionByTno(int tno){
		List<StudentAskQuestion> list=new ArrayList<StudentAskQuestion>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from studentsubmitquestion where Tno=?");
		psmt.setInt(1, tno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
			int sq_id=resultSet.getInt("sq_id");
			int state=resultSet.getInt("state");
			int sno=resultSet.getInt("sno");
			String sname=resultSet.getString("sname");
			String  Tname=resultSet.getString("Tname");
		String content=resultSet.getString("sq_content");
		StudentAskQuestion studentAskQuestion=new StudentAskQuestion(sq_id,state,sno, sname, tno, Tname, content);
		list.add(studentAskQuestion);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/**
	 * 选课之前先判断这门课是否已经选了
	 * @param sno
	 * @param cno
	 */
	public String checkIsChooseCourseBySnoAndCno(int sno,int cno){
		
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try {
			pst=con.prepareStatement("select * from sc where sno=? and cno=?");
			pst.setInt(1, sno);
			pst.setInt(2, cno);
			resultSet=pst.executeQuery();
			while(resultSet.next()){
				return "已选课";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "未选课";
		
		
	}
	/**
	 * 根据学号和课程号查找成绩，根据成绩是否为空判断有没有考过试
	 * @param sno
	 * @param cno
	 * @return
	 */
	public boolean isTest(int sno,int cno){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		ResultSet resultSet=null;
		try {
			pst=con.prepareStatement("select score from sc where sno=? and cno=?");
			pst.setInt(1, sno);
			pst.setInt(2, cno);
			
			resultSet=pst.executeQuery();
			while(resultSet.next()){
				String score=resultSet.getString("score");
				System.out.println(score);
				if(score==null){
					return false;
				}
				return true;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}
	/**
	 * 向数据库插入教师回复学生问题内容同时将问题表的回复状态置1
	 * @param tno
	 * @param sno
	 * @param sq_id
	 * @param content
	 * @return
	 */
	public boolean TeacherReplyStudentQuestion(int tno,int sno,int sq_id,String content){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("insert into reply(Tno,Sno,sq_id,reply_content) values(?,?,?,?)");
			pst.setInt(1, tno);
			pst.setInt(2, sno);
			pst.setInt(3, sq_id);
			pst.setString(4, content);
			int m=pst.executeUpdate();
			if(m>0){
				boolean flag=changeQusetionSubmitState( sq_id, tno, sno);
				if(flag=true){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 重置问题的恢复状态
	 * @return
	 */
	public boolean changeQusetionSubmitState(int sq_id,int Tno,int Sno){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("update studentsubmitquestion set state=1 where sq_id=? and Tno=? and Sno=?");
			pst.setInt(1, sq_id);
			pst.setInt(2, Tno);
			pst.setInt(3, Sno);
			
			int m=pst.executeUpdate();
			if(m>0){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 问题回复
	 * @param sno
	 * @return
	 */
	public List<QuestionAndAnswer> getQuestionBack(int sno){
		List<QuestionAndAnswer> list=new ArrayList<QuestionAndAnswer>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from question_and_answer where Sno=?");
		psmt.setInt(1, sno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		
			String sq_content=resultSet.getString("sq_content");
			String  reply_content=resultSet.getString("reply_content");
		QuestionAndAnswer questionAndAnswer=new QuestionAndAnswer(sq_content, reply_content);
		list.add(questionAndAnswer);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * 根据学号查找我的老师
	 * @param sno 学号
	 */
	public List<MyTeacher> getMyTeacherBySno(int sno){
		List<MyTeacher> list=new ArrayList<MyTeacher>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from myteacher where Sno=?");
		psmt.setInt(1, sno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		String cname=resultSet.getString("cname");
		String tname=resultSet.getString("Tname");
		String tsex=resultSet.getString("Tsex");
		String prof=resultSet.getString("Tprof");
		String ticon=resultSet.getString("Ticon");
		String dname=resultSet.getString("Dname");
		String phone=resultSet.getString("phone");
		int cno=resultSet.getInt("cno");
		int tno=resultSet.getInt("Tno");
		int tage=resultSet.getInt("Tage");
			
	MyTeacher myTeacher=new MyTeacher(cno, cname, tno, tname, tsex, prof, ticon, phone, dname, tage);
		list.add(myTeacher);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/**
	 * 将学生给老师发送的留言插入数据库
	 */
	
	public boolean insertStudentMessage(int sno,int tno,String content){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("insert into mymessage(sno,Tno,mymessage_content)values(?,?,?)");
			pst.setInt(1, sno);
			pst.setInt(2, tno);
			pst.setString(3, content);
			
			int m=pst.executeUpdate();
			if(m>0){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
		
	
		
	}
	
	
	/**
	 * 根据学号查找我的老师
	 * @param sno 学号
	 */
	public List<StudentMessageBean> viewStudentMessageByTno(int tno){
		List<StudentMessageBean> list=new ArrayList<StudentMessageBean>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select DISTINCT  * from view_student_message where Tno=?");
		psmt.setInt(1, tno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		String sname=resultSet.getString("sname");
		String mymessage_content=resultSet.getString("mymessage_content");
		String send_time=resultSet.getString("send_time");
		
			
	StudentMessageBean studentMessageBean=new StudentMessageBean(sname, mymessage_content, send_time);
		list.add(studentMessageBean);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * 根据教工号号查找我的学生
	 * @param tno 教工号
	 */
	public List<MyStudentBean> getMyStudentByTno(int tno){
		List<MyStudentBean> list=new ArrayList<MyStudentBean>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select DISTINCT * from mystudent where Tno=?");
		psmt.setInt(1, tno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		String sname=resultSet.getString("sname");
		String Ssex=resultSet.getString("Ssex");
		String Sicon=resultSet.getString("Sicon");
		String phone=resultSet.getString("phone");
		String dname=resultSet.getString("dname");
		int sno=resultSet.getInt("sno");
		int sage=resultSet.getInt("sage");
        MyStudentBean myStudentBean=new MyStudentBean(String.valueOf(sno), sname, Ssex, Sicon, String.valueOf(sage), dname, phone);
		list.add(myStudentBean);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/**
	 * 插入教师建议
	 * @param tno 教师号
	 * @param sno 学号
	 * @param content 意见内容
	 * @return
	 */
	public boolean saveTeacherAdvice(int tno,int sno,String content){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("insert into teacheradvice(Tno,Sno,ta_content) values(?,?,?)");
			pst.setInt(1, tno);
			pst.setInt(2, sno);
			pst.setString(3, content);
			int m=pst.executeUpdate();
			if(m>0){
				
					return true;
				
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
	/**
	 * 根据学号查找老师给学生提出的课堂建议
	 * @param tno 教工号
	 */
	public List<ViewTeacherAdviceBean> viewTeacherAdviceBySno(int sno){
		List<ViewTeacherAdviceBean> list=new ArrayList<ViewTeacherAdviceBean>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select  * from viewteacheradvice where sno=?");
		psmt.setInt(1, sno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		String tname=resultSet.getString("tname");
		String content=resultSet.getString("ta_content");
		Timestamp time=resultSet.getTimestamp("ta_time");
       ViewTeacherAdviceBean viewTeacherAdviceBean=new ViewTeacherAdviceBean(tname, content, time);
		list.add(viewTeacherAdviceBean);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	/**
	 * 将token插入数据库按学号
	 * @param sno 学号
	 * @param token 融云聊天所需要的token
	 * @return
	 */
	public boolean insertRongToken(int sno,String token){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("update student set token=? where sno=?");
			
			
			pst.setString(1,token);
			pst.setInt(2, sno);
			int m=pst.executeUpdate();
			if(m>0){
				
					return true;
				
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	/**
	 * 从数据库查找token 按学号
	 * @param sno 学号
	 * @return
	 */
	public String getRongToken(int sno){
		Connection con=ConnectionManager.getConnection();
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement("select token from student where sno=?");
			pst.setInt(1, sno);
			rs=pst.executeQuery();
			while(rs.next()){
				String token=rs.getString("token");
				if(token==null){
					return "";
				}else{
				return token;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	/**
	 * 将token插入数据库按教工号
	 * @param tno 教工号
	 * @param token 聊天token
	 * @return
	 */
	public boolean insertTeacherRongToken(int tno,String token){
		Connection con=ConnectionManager.getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement("update teacher set token=? where tno=?");
			
			
			pst.setString(1,token);
			pst.setInt(2, tno);
			int m=pst.executeUpdate();
			if(m>0){
				
					return true;
				
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	/**
	 * 从教师表中查找token
	 * @param tno
	 * @return
	 */
	public String getTeacherRongToken(int tno){
		Connection con=ConnectionManager.getConnection();
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement("select token from teacher where tno=?");
			pst.setInt(1, tno);
			rs=pst.executeQuery();
			while(rs.next()){
				String token=rs.getString("token");
				if(token==null){
					return "";
				}else{
					return token;
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	/**
	 * 通过教工号获得教师的姓名和头像显示在个人中心
	 * @param tno 教工号
	 * @return
	 */
	public NiChengAndIcon getNiChengAndIconByTno(int tno){
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		
		try {
			pst=connection.prepareStatement("select Tname,Ticon from teacher where Tno=?");
			pst.setInt(1, tno);
			rs=pst.executeQuery();
			if(rs.next()){
				String tuserName=rs.getString("Tname");
				String icon=rs.getString("Ticon");
				NiChengAndIcon niChengAndIcon=new NiChengAndIcon(tuserName, icon);
				return niChengAndIcon;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 通过学号获得学生的姓名和头像
	 * @param sno 学号
	 * @return
	 */
	
	public NiChengAndIcon getNiChengAndIconBySno(int sno){
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		
		try {
			pst=connection.prepareStatement("select Sname,Sicon from student where Sno=?");
			pst.setInt(1, sno);
			rs=pst.executeQuery();
			if(rs.next()){
				String suserName=rs.getString("Sname");
				String icon=rs.getString("Sicon");
				NiChengAndIcon niChengAndIcon=new NiChengAndIcon(suserName, icon);
				return niChengAndIcon;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	//插入ip地址
	public  boolean insertIp(int cno,int sno,String ip){
		Connection con=null;
		PreparedStatement pst=null;
		con=ConnectionManager.getConnection();
		try {
			pst=con.prepareStatement("insert into sign(sno,cno,ip,state) values(?,?,?,?)");
			pst.setInt(1, sno);
			pst.setInt(2, cno);
			pst.setString(3, ip);
			pst.setString(4, "1");
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	//根据学号和课程号判断学生有没有签到
public int judgeSign(int sno,int cno){
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	con=ConnectionManager.getConnection();
	try {
		pst=con.prepareStatement("select state from sign where sno=? and cno=?");
		pst.setInt(1, sno);
		pst.setInt(2, cno);
		rs=pst.executeQuery();
		while(rs.next()){
			String state=rs.getString("state");
			if(state.equals("1")){
				return 1;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
}

//查询任课教师所教授的课程
public List<ViewTeacherCourseEnty> getTeacherCourseByTno(int tno){
	Connection connection=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	List<ViewTeacherCourseEnty> list=new ArrayList<ViewTeacherCourseEnty>();
	connection=ConnectionManager.getConnection();
	try {
		pst=connection.prepareStatement("select cno,cname,classtime,cadress,content from course where tno=?");
		pst.setInt(1, tno);
		rs=pst.executeQuery();
		while(rs.next()){
			int cno=rs.getInt("cno");
			String cname=rs.getString("cname");
			String classtime=rs.getString("classtime");
			String cadress=rs.getString("cadress");
			String content=rs.getString("content");
			ViewTeacherCourseEnty viewTeacherCourseEnty=new ViewTeacherCourseEnty(cno, cname, classtime, cadress, content);
			list.add(viewTeacherCourseEnty);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
}

//判断是否插入了签到记录
public boolean judge(int sno,int cno){
	Connection connection=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	connection=ConnectionManager.getConnection();
	try {
		pst=connection.prepareStatement("select *from sign where sno=? and cno=?");
	pst.setInt(1, sno);
	pst.setInt(2, cno);
		rs=pst.executeQuery();
		while(rs.next()){
		return true;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}

//将签到状态值1
//插入ip地址
	public  boolean changeSignState(int cno,int sno){
		Connection con=null;
		PreparedStatement pst=null;
		con=ConnectionManager.getConnection();
		try {
			pst=con.prepareStatement("update sign set state='1' where cno=? and sno=?");
			pst.setInt(1, cno);
			pst.setInt(2, sno);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	//命题
	public boolean MingTi(int cno,String question,String question_a,String question_b,String question_ok){
		Connection con=null;
		PreparedStatement pst=null;
		con=ConnectionManager.getConnection();
		try {
		pst=con.prepareStatement("insert into qusetion(question_content,cno,answer_content_a,answer_content_b,answer_correct)values(?,?,?,?,?)");
		pst.setString(1, question);
		pst.setInt(2, cno);
		pst.setString(3, question_a);
		pst.setString(4, question_b);
		pst.setString(5, question_ok);
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	//清除签到
	public boolean cancelSignByCno(int cno){
		Connection connection=null;
		PreparedStatement pst=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("delete from sign where cno=?");
			pst.setInt(1, cno);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	//获得考勤信息
	public List<KaoQin> getKaoQinInfo(int cno){
		List<KaoQin>list=new ArrayList<KaoQin>();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("select sno,ip,state from sign where cno=?");
			pst.setInt(1, cno);
			rs=pst.executeQuery();
			while(rs.next()){
				String ip=rs.getString("ip");
				int sno=rs.getInt("sno");
				
				String sname=getSnameBySno(sno);
				
				String state=rs.getString("state");
				KaoQin kaoQin=new KaoQin(sname, ip, state);
				list.add(kaoQin);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	//根据学号获得学生姓名
	public String getSnameBySno(int sno){
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("select sname from student where sno=?");
			pst.setInt(1, sno);
			rs=pst.executeQuery();
			while(rs.next()){
				String sname=rs.getString("sname");
				return sname;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	/**
	 * 添加老师
	 * @param tno
	 * @param tname
	 * @param tsex
	 * @param tage
	 * @param prof
	 * @param phone
	 * @param dept
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean addTeacher(int tno,String tname,String tsex,int tage,String prof,String phone,int dept,String user,String pass){
		Connection connection=null;
		PreparedStatement pst=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("insert into teacher(tno,tname,tsex,tage,dno,tprof,tuser,tpass,phone)values(?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, tno);
			pst.setString(2, tname);
			pst.setString(3, tsex);
			
			pst.setInt(4, tage);
			
			pst.setInt(5, dept);
			
			pst.setString(6, prof);
			
			pst.setString(7, user);
			pst.setString(8, pass);
			pst.setString(9, phone);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 根据教工号查找老师
	 * @param sno 学号
	 */
	public List<MyTeacher> getTeacherByno(int tno){
		List<MyTeacher> list=new ArrayList<MyTeacher>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try{
			
		Connection con=ConnectionManager.getConnection();
		psmt=con.prepareStatement("select * from teacher where tno=?");
		psmt.setInt(1, tno);
		resultSet=psmt.executeQuery();
		while(resultSet.next()){
		
		String tname=resultSet.getString("Tname");
		String tsex=resultSet.getString("Tsex");
		String prof=resultSet.getString("Tprof");
		String ticon=resultSet.getString("Ticon");
		int dno=resultSet.getInt("dno");
		
		 String dname=findDnameByTno(dno);
		
		String phone=resultSet.getString("phone");
		
		int tage=resultSet.getInt("Tage");
			
	MyTeacher myTeacher=new MyTeacher( tno, tname, tsex, prof, ticon, phone, dname, tage);
		list.add(myTeacher);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * 根据系部号查系部名称
	 * @param dno
	 * @return
	 */
	public String findDnameByTno(int dno){
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("select dname from dept where dno=?");
			pst.setInt(1, dno);
			rs=pst.executeQuery();
			while(rs.next()){
				String dname=rs.getString("dname");
				return dname;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 根据系部名称查系部号
	 * @param dno
	 * @return
	 */
	public int findDnoByDname(String dname){
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("select dno from dept where dname=?");
			pst.setString(1, dname);
			rs=pst.executeQuery();
			while(rs.next()){
			int dno=rs.getInt("dno");
				return dno;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 删除老师
	 * @return
	 */
	public boolean deleteTeacherByTno(int tno){
		Connection con=null;
		PreparedStatement pst=null;
		con=ConnectionManager.getConnection();
		try {
			pst=con.prepareStatement("delete from teacher where tno=?");
			pst.setInt(1, tno);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
			return false;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//编辑老师
	
	public boolean changeTeacherInfoByTno(int tno,String tname,String tsex,int tage,String prof,String phone,String dept){
		Connection con=null;
		PreparedStatement pst=null;
		int dno=findDnoByDname(dept);
		con=ConnectionManager.getConnection();
		try {
			pst=con.prepareStatement("update teacher set tname=? , tsex=? ,tage=? , dno=? ,tprof=? ,phone=? where tno=?");
			pst.setString(1, tname);
			pst.setString(2, tsex);
			pst.setInt(3, tage);
			if(dno!=0){
			pst.setInt(4, dno);
			}
			pst.setString(5, prof);
			pst.setString(6, phone);
			
			pst.setInt(7, tno);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
			return false;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 添加学生
	 * @param sno
	 * @param sname
	 * @param ssex
	 * @param sage
	 * @param phone
	 * @param dno
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean addStudent(int sno,String sname,String ssex,int sage,String phone,int dno,String user,String pass){
		Connection connection=null;
		PreparedStatement pst=null;
		connection=ConnectionManager.getConnection();
		try {
			pst=connection.prepareStatement("insert into student(sno,sname,ssex,sage,dno,suser,spassword,phone)values(?,?,?,?,?,?,?,?)");
			pst.setInt(1, sno);
			pst.setString(2, sname);
			pst.setString(3, ssex);
			pst.setInt(4, sage);
			
			pst.setInt(5, dno);
			
			
			pst.setString(6, user);
			pst.setString(7, pass);
			pst.setString(8, phone);
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 根据学号获得学生的信息
	 * @param tno 学号
	 */
	public List<MyStudentBean> findStudentBySno(int sno){
		List<MyStudentBean>list=new ArrayList<MyStudentBean>();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try {
		Connection con=ConnectionManager.getConnection();
		
			psmt=con.prepareStatement("select * from student where Sno=?");
			psmt.setInt(1, sno);
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
				String Sname=resultSet.getString("Sname");
				String Ssex=resultSet.getString("Ssex");
				String Sicon=resultSet.getString("Sicon");
				String phone=resultSet.getString("phone");
			int Sage=resultSet.getInt("Sage");
				int Dno=resultSet.getInt("Dno");
				String dname=findDnameByTno(Dno);
		MyStudentBean student=new MyStudentBean(String.valueOf(sno), Sname, Ssex, Sicon, String.valueOf(Sage), dname, phone);
			list.add(student);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//删除学生信息
		public boolean deleteStudentBySno(int sno){
			Connection connection=null;
			PreparedStatement pst=null;
			connection=ConnectionManager.getConnection();
			try {
				pst=connection.prepareStatement("delete from student where sno=?");
				pst.setInt(1, sno);
				int count=pst.executeUpdate();
				if(count>0){
					return true;
				}else{
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
		
		//修改学生信息
		public boolean changeStudentInfoBySno(int sno,String sname,String ssex,int sage,String phone,String dept){
			Connection con=null;
			PreparedStatement pst=null;
			int dno=findDnoByDname(dept);
			con=ConnectionManager.getConnection();
			try {
				pst=con.prepareStatement("update student set sname=? , ssex=? ,sage=? , dno=?  ,phone=? where sno=?");
				pst.setString(1, sname);
				pst.setString(2, ssex);
				pst.setInt(3, sage);
				if(dno!=0){
				pst.setInt(4, dno);
				}
				
				pst.setString(5, phone);
				
				pst.setInt(6, sno);
				int count=pst.executeUpdate();
				if(count>0){
					return true;
				}else{
				return false;	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
}
