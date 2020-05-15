package Attend;

import java.sql.*;
import java.io.*;

public class MyConnection {
	
		private static final MyConnection MyConnection = null;
		static Connection con;
		static Connection getConnection()
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facial_recognition","root","");
				System.out.println("Database Connected");
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(ClassNotFoundException cn)
			{
				System.out.println(cn);
			}
			return con;
		}
	}





