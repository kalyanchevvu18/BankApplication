package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Customer;

public interface IAdminService 
{
	public String authenticate(String name,String password);
	public void addCustomer(Customer c);
	public List<Customer> getCustomerDetails(int accountnumber);
	public String updateCustomerDetails(int accountnumber,String name,double  balance);
	public void deleteCustomer(int accountnumber);
	public List<Customer> showAllCustomers();
	public List<Double> balanceEnquiry(int accountnumber);

}
