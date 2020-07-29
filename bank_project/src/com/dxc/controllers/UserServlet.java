package com.dxc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.services.IUserService;
import com.dxc.services.UserServiceImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	IUserService userservice = new UserServiceImpl();
	IUserDao userdao = new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action="";
		String message=" ";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		
		if(action.equals("user_login"))
	      {

			
	      int accountnumber=Integer.parseInt(request.getParameter("accountnumber"));
	      String password=request.getParameter("password");
	      System.out.println(accountnumber);
	      System.out.println(password);
	       session.setAttribute("accountnumber", accountnumber);
	      boolean b=userservice.authenticate(accountnumber, password);
	      System.out.println(b);
	      if(b)
	      {
	         message="Login Successful!!!";
	         session.setAttribute("message", message);
	         response.sendRedirect("userloggedin.jsp");
	    	  
	      }
	      else
	      {
	    	  message="Invalid login";
	    	  session.setAttribute("message", message);
	    	  response.sendRedirect("userfailedlogin.jsp");
	      
	      }
	      }
	}
}
