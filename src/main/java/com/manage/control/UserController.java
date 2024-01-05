package com.manage.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.dao.UserDAOImpl;
import com.manage.user.User;


@WebServlet("/")
public class UserController extends HttpServlet {
	UserDAOImpl userDAOImpl;
	
	public void init()
	{
		 userDAOImpl = new UserDAOImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		
	   switch(action)
	   {
	       case "/new":
		       showNewForm(request,response);
		       break;
	       case "/edit":
	    	   showeditForm(request,response);
	    	   break;
	       case "/update":
	    	   updateForm(request,response);
	    	   break;
	       case "/insert":
	    	   insertData(request,response);
	    	   break;
	       case "/delete":
	    	   deleteData(request,response);
	    	   break;
	       default:
	    	   listAll(request,response);
	    	   break;
	   }
	}

	private void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<User> listUser = userDAOImpl.selectAllUsers();
		request.setAttribute("listUser", listUser);
		 RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
	        rd.forward(request, response);
	}

	private void deleteData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		userDAOImpl.deleteUser(id);
		response.sendRedirect("list");
	}

	private void insertData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User user = new User(name,email,country);
		userDAOImpl.insertUser(user);
		response.sendRedirect("list");
	}

	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User user = new User(id,name,email,country);
		userDAOImpl.updateUser(user);
		response.sendRedirect("list");
	}

	private void showeditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAOImpl.selectUser(id);
		RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		rd.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
		rd.forward(request, response);
		
	}
}
