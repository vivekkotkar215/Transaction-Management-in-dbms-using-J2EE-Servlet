package Cansole.Application;

import java.sql.*;
import java.util.*;

public class EmployeeManagement{
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		System.out.println("Yes");			  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","vivek215");
		System.out.println("connected");
		Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs=stmt.executeQuery("select eid,ename,esal from emp");  
	    int ch;
	    Scanner sc=new Scanner(System.in);
	    do
		{
	    	System.out.println("1)Move First");
	    	System.out.println("2)Move Last");
	    	System.out.println("3)Move Next");
	    	System.out.println("4)Move Previous");
	    	System.out.println("5)Move nth Record");
	      	System.out.println("6)Move -nth Record");
	      	System.out.println("7)Insert Record");
	      	System.out.println("8)Update Record");
	      	System.out.println("9)Delete Record");
	      	System.out.println("Enter your choice:");	
	      	ch=sc.nextInt();	
	      	if(ch==1)
	      	{
	      		rs.first();
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
	      	if(ch==2)
	      	{
	      		rs.last();
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
	      	if(ch==3)
	      	{
	      		rs.next();
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
	      	if(ch==4)
	      	{
	      		rs.previous();
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
		  
	      	if(ch==5)
	      	{
	      		System.out.println("Enter your abs record number:");	
	      		int a=sc.nextInt();
	      		rs.absolute(a);
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
	      	if(ch==6)
	      	{
	      		System.out.println("Enter your releative record number:");	
	      		int a=sc.nextInt();
	      		rs.relative(a);
	      		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
	      	}
	      	if(ch==7)
	      	{
	      		rs.last();
	      		rs.moveToInsertRow();
	      		rs.updateInt(1,105);
	      		rs.updateString(2,"RAHUL");
	      		rs.updateInt(3,15750);
	      		rs.insertRow();
	      	}
	      	if(ch==8)
	      	{
	      		rs.last();
	      		rs.updateString(2,"SACHIN");
	      		rs.updateInt(3,15750);
	      		rs.updateRow();
	      	}
	      	if(ch==9)
	      	{
	      		rs.last();
	      		rs.deleteRow();
	      	}
		}while(ch!=0);
		  
		con.close();
		
	}
}
