package com.dxc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Customer;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminService;

@WebServlet("/admin")
public class LoginAdminServlet extends HttpServlet
{
	IAdminService adminservice=new AdminServiceImpl();
    IAdminDao admindao=new AdminDaoImpl();
    Customer c=new Customer();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String message;
		String delete;
		String action="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		if(action.equals("add_customer"))
	      {
	    	
	      int accountnumber=Integer.parseInt(request.getParameter("accountnumber"));
	      String name=request.getParameter("name");
	      double balance=Double.parseDouble(request.getParameter("balance"));
	      String password=request.getParameter("password");
	      Customer c=new Customer(accountnumber,name,balance,password);
	      adminservice.addCustomer(c);
	      message="customer  Added Successfully!!!";
	       session.setAttribute("message", message);
	       response.sendRedirect("successfulladdeduser.jsp");
	      
	      }
		else if(action.equals("search_customer"))
	      {
	    	System.out.println("searching the customer details");
	      int accountnumber=Integer.parseInt(request.getParameter("accountnumber"));
	      List<Customer> list=adminservice.getCustomerDetails(accountnumber);
	      session.setAttribute("list", list);
	      response.sendRedirect("show_search.jsp");
	      }
		else if(action.equals("modify"))
		{
			int accountnumber=Integer.parseInt(request.getParameter("accountnumber"));
		      String name=request.getParameter("name");
		      double balance=Double.parseDouble(request.getParameter("balance"));
		     String status=adminservice.updateCustomerDetails(accountnumber,name,balance);
		      session.setAttribute("status", status);
		      response.sendRedirect("updated_customer_details.jsp");
			
		}
		else if(action.equals("close_customer"))
		{
			int account_number=Integer.parseInt(request.getParameter("accountnumber"));
			adminservice.deleteCustomer(account_number);
			delete="Customer deleted successfully";
    		session.setAttribute("delete", delete);
    		response.sendRedirect("delete_customer.jsp");
		}
		else if(action.equals("balance_enquiry"))
		{
			int accountnumber=Integer.parseInt(request.getParameter("accountnumber"));
			List<Double> list=adminservice.balanceEnquiry(accountnumber);
			session.setAttribute("list", list);
    		response.sendRedirect("enquiry.jsp");
			
		}
		else
		{
			 List<Customer> list=adminservice.showAllCustomers();
		       session.setAttribute("list", list);
		       response.sendRedirect("display_all_customer.jsp");
		}

	}

}
