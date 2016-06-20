package com.atgem.utils;


import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class DBCPUtils {
        public  Connection getConnection() throws Exception{
        	 Context initContext = new InitialContext();
             Context envContext  = (Context)initContext.lookup("java:/comp/env");
             DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
             Connection conn = ds.getConnection();
             return conn;
        }
        public static void main(String[] args) {
			try {
				System.out.println(new DBCPUtils().getConnection().getClass().getName().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
