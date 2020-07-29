package com.dxc.pojos;

import java.sql.Date;

public class TransactionDetails 
{
     private String accountnumber;
     private String account_type;
     private double amount;
     private Date time;
     
     public  TransactionDetails ()
     {
    	 
     }

	public TransactionDetails(String accountnumber, String account_type, double amount, Date time) {
		super();
		this.accountnumber = accountnumber;
		this.account_type = account_type;
		this.amount = amount;
		this.time = time;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccount_nunmber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TransactionDetails [accountnunmber=" + accountnumber + ", account_type=" + account_type + ", amount="
				+ amount + ", time=" + time + "]";
	}
     
}
