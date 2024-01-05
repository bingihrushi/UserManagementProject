package com.manage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.manage.user.User;

public class UserDAOImpl implements UserDAO{
	
	
	private static PreparedStatement pstmt = null;
	private static Statement stmt = null;
	  private static ResultSet res = null;
	  
	
	   private static String jdbcURL = "jdbc:mysql://localhost:3306/usermanagement";
	    private static String jdbcUsername = "root";
	    private static String jdbcPassword = "Hrushi@1234";

	    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
	        " (?, ?, ?);";

	    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	    private static final String SELECT_ALL_USERS = "select * from users";
	    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

	    
	    public UserDAOImpl() {
			
		}
	    
	    public static Connection getConnection()
	    {
	          Connection connection = null;
			try {
	        	  Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          
	          return connection;
	    }
	    
	    
	@Override
	public  void insertUser(User user) {
		
	 try {
		 Connection connection = getConnection();
		pstmt = connection.prepareStatement(INSERT_USERS_SQL);
		pstmt.setString(1,user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getCountry());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	@Override
	public User selectUser(int id) {
		
		User user=null;
		 try {
			 Connection connection = getConnection();
			pstmt = connection.prepareStatement(SELECT_USER_BY_ID);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			
			while(res.next())
			{
				String name = res.getString("name");
				String email = res.getString("email");
				String country = res.getString("country");
			    user = new User(id,name,email,country);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> selectAllUsers() {
		User user =null;
		List<User> users = new ArrayList<User>();
		 try {
			 Connection connection = getConnection();
			stmt = connection.createStatement();
			 res = stmt.executeQuery(SELECT_ALL_USERS);
			 while(res.next())
			 {
				    int id = res.getInt("id");
	                String name = res.getString("name");
	                String email = res.getString("email");
	                String country = res.getString("country");
	                user = new User(id,name,email,country);
	                users.add(user);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return users;
	}

	@Override
	public boolean updateUser(User user) {
		boolean rowUpdated=false;
		 try {
			 Connection connection = getConnection();
			pstmt = connection.prepareStatement(UPDATE_USERS_SQL);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getCountry());
			pstmt.setInt(4, user.getId());
			  rowUpdated= pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean rowDeleted=false;
          try {
        	  Connection connection = getConnection();
			pstmt = connection.prepareStatement(DELETE_USERS_SQL);
			pstmt.setInt(1, id);
			 rowDeleted = pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowDeleted;
		
	}

}
