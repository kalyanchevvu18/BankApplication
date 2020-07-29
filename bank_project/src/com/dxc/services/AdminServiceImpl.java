package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Customer;

public class AdminServiceImpl implements IAdminService 
{
	IAdminDao admindao=new AdminDaoImpl();
   
	@Override
	public String authenticate(String name, String password) 
	{
		return admindao.authenticate(name, password);
	}

	@Override
	public void addCustomer(Customer c) 
	{
		admindao.addCustomer(c);
		
	}
	public List<Customer> getCustomerDetails(int accountnumber)
	{
		return admindao.getCustomerDetails(accountnumber);
	}
	public String updateCustomerDetails(int accountnumber,String name,double  balance)
	{
		return admindao.updateCustomerDetails(accountnumber, name, balance);
	}
	public void deleteCustomer(int accountnumber)
	{
		admindao.deleteCustomer(accountnumber);
	}
	public List<Customer> showAllCustomers()
	{
		return admindao.showAllCustomers();
	}
	public List<Double> balanceEnquiry(int accountnumber)
	{
		 return admindao.balanceEnquiry(accountnumber);
	}

}
