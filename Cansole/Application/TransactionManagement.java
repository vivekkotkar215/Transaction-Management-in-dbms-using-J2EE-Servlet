package Cansole.Application;

import java.sql.*;
import java.util.*;

public class TransactionManagement {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","vivek215");
			Scanner sc= new Scanner(System.in);
			con.setAutoCommit(false);
			
			//op1: Read Ac1
			System.out.println("Enter the 1St Account Number:");
			int acno1=sc.nextInt();
			PreparedStatement ps1= con.prepareStatement("Select * from account where ano=?");
			ps1.setInt(1,acno1);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next()){
				System.out.println("Account No:"+rs1.getInt(1));
				System.out.println("Account Name:"+rs1.getString(2));
				System.out.println("Account Balance:"+rs1.getInt(3));
			}
			else{
				System.out.println("Invalid Account Number");
				throw new SQLException("Invalid Account Number.....!");
			}
			
			//op2: withdraw amount
			System.out.println("Enter the amount to be withdraw:");
			int amt1=sc.nextInt();
			PreparedStatement ps2= con.prepareStatement("update account set abal=abal-? where ano=?");
			ps2.setInt(1,amt1);
			ps2.setInt(2,acno1);
			int i=ps2.executeUpdate();
			if(i>0)
				System.out.println("Amount withdraw sucessfully....");
			
			//op3: Read Ac2
			System.out.println("Enter the 2nd Account Number:");
			int acno2=sc.nextInt();
			PreparedStatement ps3= con.prepareStatement("Select * from account where ano=?");
			ps3.setInt(1,acno2);
			ResultSet rs2=ps3.executeQuery();
			if(rs2.next())
			{
				System.out.println("Account No:"+rs2.getInt(1));
				System.out.println("Account Name:"+rs2.getString(2));
				System.out.println("Account Balance:"+rs2.getInt(3));
			}
			else
			{
				System.out.println("Invalid Account Number");
				throw new SQLException("Invalid Account Number.....!");
			}
			
			//op4: deposit amount
			PreparedStatement ps4= con.prepareStatement("update account set abal=abal+? where ano=?");
			ps4.setInt(1,amt1);
			ps4.setInt(2,acno2);
			i=ps4.executeUpdate();
			if(i>0)
				System.out.println("Amount Transfer sucessfully....");
			
			// op5: display ac1   
			// op6: display ac2			   
			PreparedStatement ps5= con.prepareStatement("Select * from account where ano IN (?,?)");
			ps5.setInt(1,acno1);
			ps5.setInt(2,acno2);
			ResultSet rs3=ps5.executeQuery();
			while(rs3.next())
			{
				System.out.println("\n\nAccount No:"+rs3.getInt(1));
				System.out.println("Account Name:"+rs3.getString(2));
				System.out.println("Account Balance:"+rs3.getInt(3));
			}   
			con.commit();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			con.rollback();
		}
	}

}
