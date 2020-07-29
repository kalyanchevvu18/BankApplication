package com.dxc.services;

import java.sql.Date;
import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Customer;
import com.dxc.pojos.TransactionDetails;

public class UserServiceImpl implements IUserService
{
	IUserDao userdao=new UserDaoImpl();
	public boolean authenticate(int name, String password)
	{
		return userdao.authenticate(name, password);

	}
	public String deposit(int accountnumber,double balance)
	{
		return userdao.deposit(accountnumber,balance);
		
	}
	public String withdraw(int accountnumber, double balance) 
	{
		return userdao.withdraw(accountnumber, balance);
	}
	public List<Double> checkBalance(int accountnumber)
	{
		return userdao.checkBalance(accountnumber);
	}
	public String updateNewPassword(int username,String newpass)
	{
		return userdao.updateNewPassword(username,newpass);
	}
	public String transferMoney(String accountnumber1,String accountnumber2,double balance)
	{
       return userdao.transferMoney(accountnumber1, accountnumber2,balance);   
		
	}
	public List<TransactionDetails > miniStatement(int accountnumber)
	{
		return userdao.miniStatement(accountnumber);
	    
	}
}
