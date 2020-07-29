package com.dxc.services;

import java.sql.Date;
import java.util.List;

import com.dxc.pojos.Customer;
import com.dxc.pojos.TransactionDetails;

public interface IUserService 
{
	public boolean authenticate(int name, String password); 
	public String  deposit(int accountnumber,double balance);
	public String withdraw(int accountnumber,double balance);
	public List<Double> checkBalance(int accountnumber);
	public String updateNewPassword(int username,String newpass);
	public String transferMoney(String accountnumber1,String accountnumber2,double balance);
	public List<TransactionDetails> miniStatement(int accountnumber);
}
