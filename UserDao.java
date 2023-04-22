package com.suyash2000;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements AutoCloseable {
	private Connection con;

	public UserDao() throws Exception {
		con = DbUtil.getConnection();
	}

	@Override
	public void close() {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int SignUp(String firstName, String lastName, String email, String password, String mobileNo)
			throws Exception {

		int cnt;

		String sql = "INSERT INTO users(first_name, last_name, email, passwd, mobile) VALUES (?,?,?,?,?)";

		try (PreparedStatement prs = con.prepareStatement(sql)) {
			prs.setString(1, firstName);
			prs.setString(2, lastName);
			prs.setString(3, email);
			prs.setString(4, password);
			prs.setString(5, mobileNo);
			cnt = prs.executeUpdate();

		}

		return cnt;

	}

	public User signIn(String email, String password) throws Exception {
		User currentUser = null;
		String sql = "SELECT id,first_name,last_name,email,passwd,mobile FROM users WHERE  email =? AND passwd = ?";

		try (PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, email);
			pst.setString(2, password);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");

					String mobileNo = rs.getString("mobile");
					currentUser = new User(id, firstName, lastName, email, password, mobileNo);

				}
			}

		}

		return currentUser;

	}
	
	public void changePassword(String password,int userId) throws Exception
	{
		 String sql = "UPDATE users set passwd =? where id = ?";
		 
		 try(PreparedStatement pst = con.prepareStatement(sql))
			{
				  pst.setString(1, password);
				  pst.setInt(2, userId);
				  pst. executeUpdate();
				 
				  System.out.println("Password change Successfully");

			}

	}
	
	public User updateProfile(String firstName, String lastName,String mobile, int userId) throws Exception {
		String sql = "UPDATE users set first_name = ?,last_name =?,mobile = ? where id = ?";
		User user1 =null;
		try(PreparedStatement pst = con.prepareStatement(sql))
		{
			User user = new User();
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, mobile);
			pst.setInt(4,userId);
			pst.executeUpdate();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setMobileNo(mobile);
			 user1 = new User(userId, firstName, lastName, lastName, user.getEmail(), mobile);
			System.out.println("Profile Updated Successfully");
			
		
		}
		return user1;
	}
	
	
	
}
