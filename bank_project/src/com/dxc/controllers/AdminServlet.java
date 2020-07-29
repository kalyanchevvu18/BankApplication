package com.dxc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet 
{
       IAdminService adminservice=new AdminServiceImpl();
       IAdminDao admindao=new AdminDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{

		String action="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		if(action.equals("admin_login"))
	      {
	    	
	      String name=request.getParameter("name");
	      String password=request.getParameter("password");
	      String status=adminservice.authenticate(name, password);
	      if(name.equals(password))
	      {
	    	  session.setAttribute("status", status);
	    	  response.sendRedirect("adminloggedin.jsp");
	    	  
	      }
	      else
	      {
	    	  session.setAttribute("status", status);
	    	  response.sendRedirect("adminfailedlogin.jsp");
	      
	      }
	      }
	}
}
