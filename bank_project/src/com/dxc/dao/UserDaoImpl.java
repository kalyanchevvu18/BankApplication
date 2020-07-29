package com.dxc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Customer;
import com.dxc.pojos.TransactionDetails;

public class UserDaoImpl implements IUserDao
{
	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded....");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kalyanmm?autoReconnect=true&useSSL=false",
					"root", "Pandu@20");
			System.out.println("connected to database.......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean authenticate(int accountnumber, String password) 
	{
		int flag=0;
		
		try {
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer ");
			while (rs.next()) {
				if ( accountnumber==(rs.getInt(1)) && password.equals(rs.getString(4)))
				{ 
					flag=1;
					return true;
				}
				
			}
			if(flag==0)
			{
				return false;
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;

	}

	public String deposit(int accountnumber, double balance) {

		double amount = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from customer where accountnumber=?");
			pstmt.setInt(1, accountnumber);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				amount = rs.getDouble(3);
				amount = amount + balance;

			}
			pstmt.close();
			PreparedStatement pstmt1 = con.prepareStatement("update customer set Balance=? where accountnumber=?");
			pstmt1.setDouble(1, amount);
			pstmt1.setInt(2, accountnumber);
			pstmt1.executeUpdate();
      
			PreparedStatement pstmt3=con.prepareStatement("insert into transaction values(?,?,?,?)");
			pstmt3.setString(1,(Integer.toString(accountnumber)));
			pstmt3.setString(2,"deposit");
			pstmt3.setDouble(3,balance);
			pstmt3.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt3.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "amount deposited successfully!!!";

	}

	public String withdraw(int accountnumber, double requestedAmount) {
		double availableAmount = 0;
		String withdrawMessage="InSufficient Balance";
		try {
			System.out.println("withdraw amonut");
			PreparedStatement pstmt = con.prepareStatement("select * from customer where accountnumber=?");
			pstmt.setInt(1, accountnumber);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				availableAmount = rs.getDouble(3);
				if (requestedAmount >= availableAmount) {
					return withdrawMessage;
				}
				availableAmount = availableAmount - requestedAmount;
			}

			PreparedStatement pstmt1 = con.prepareStatement("update customer set balance=? where accountnumber=?");
			pstmt1.setDouble(1, availableAmount);
			pstmt1.setInt(2, accountnumber);
			pstmt1.executeUpdate();
			PreparedStatement pstmt3=con.prepareStatement("insert into transaction values(?,?,?,?)");
			pstmt3.setString(1,(Integer.toString(accountnumber)));
			pstmt3.setString(2,"Withdraw");
			pstmt3.setDouble(3,requestedAmount);
			pstmt3.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt3.execute();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "amount is successfully withdraw!!!";
		

	}
	public List<Double> checkBalance(int accountnumber)
	{
		List<Double> list=new ArrayList<>();
		double balance=0;
		try {
			PreparedStatement pstmt=con.prepareStatement("select  balance from customer where accountnumber=?");
			pstmt.setInt(1, accountnumber);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				 balance=rs.getDouble(1);
				 list.add(balance);
				System.out.println("The requried balance for particular customer is obtained");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String updateNewPassword(int username,String newpass)
	{
		try {
			PreparedStatement pstmt=con.prepareStatement("update customer set password=?  where Accountnumber=?");
			pstmt.setString(1, newpass);
			pstmt.setInt(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newpass;
		
	}
	public String transferMoney(String accountnumber1,String accountnumber2,double balance)
	{
		double amount=0,amount1=0;
		try {
			PreparedStatement pstmt=con.prepareStatement("select * from customer where Accountnumber=?");
			pstmt.setString(1, accountnumber1);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				amount=rs.getDouble(3);
				if(amount>balance)
				{
					amount=amount-balance;
					System.out.println(amount);
				}
				else {
					return "insufficiennt balance";
				}
			}
			PreparedStatement pstmt1=con.prepareStatement("update customer set balance=? where Accountnumber=?");
			pstmt1.setDouble(1,amount);
			pstmt1.setString(2, accountnumber1);
			pstmt1.executeUpdate();
			System.out.println("deducting the from user1");
			
			PreparedStatement pstmt2=con.prepareStatement("select * from customer where Accountnumber=?");
			pstmt2.setString(1, accountnumber2);
			ResultSet rs1=pstmt2.executeQuery();
			while(rs1.next())
			{
				amount1=rs1.getDouble(3);
				amount1=amount1+balance;
				System.out.println(amount1);
			}
			PreparedStatement pstmt3=con.prepareStatement("update customer set balance=? where Accountnumber=?");
			pstmt3.setDouble(1,amount1);
			pstmt3.setString(2, accountnumber2);
			pstmt3.executeUpdate();
			System.out.println("updating the user2 balance");
			PreparedStatement pstmt4=con.prepareStatement("insert into transaction values(?,?,?,?)");
			pstmt4.setString(1,accountnumber1);
			pstmt4.setString(2,"transfer");
			pstmt4.setDouble(3,balance);
			pstmt4.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt4.execute();
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return "Amount is transfered successfully!!!";
	}
		
		public List<TransactionDetails> miniStatement(int accountnumber)
		{
			List<TransactionDetails> list=new ArrayList<TransactionDetails>();
			try {
				PreparedStatement pstmt=con.prepareStatement("select * from transaction  where Accountnumber=? order by Time desc limit 5");
				pstmt.setInt(1, accountnumber);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					
				  
					list.add(new TransactionDetails(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getDate(4)));
				}
				
			
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}