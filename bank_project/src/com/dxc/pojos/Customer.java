package com.dxc.pojos;

public class Customer 
{
    private int accountnumber;
    private String name;
    private double balance;
    private String password;
    public Customer()
    {
    	
    }
	public Customer(int accountnumber, String name, double balance,String password) {
		super();
		this.accountnumber = accountnumber;
		this.name = name;
		this.balance = balance;
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountNumber() {
		return accountnumber;
	}
	public void setAccountNumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
    
}
