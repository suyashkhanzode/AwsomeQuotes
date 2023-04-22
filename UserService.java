package com.suyash2000;

import java.util.Scanner;
import static com.suyash2000.Q1.userCurrent;

public class UserService {
	Scanner sc = new Scanner(System.in);
	public  void SignUp() {
		
		try(UserDao userDao = new UserDao()) {
			System.out.println("Enter First Name      -");
			String firstName = sc.next();
			System.out.println("Enter Last Name      -");
			String lastName = sc.next();
			System.out.println("Enter Email Address   -");
			String email = sc.next();
			System.out.println("Enter Password        -");
			String password = sc.next();
			System.out.println("Enter Mobile Number   -");
			String mobile = sc.next();
			
			int cnt = userDao.SignUp(firstName, lastName, email, password, mobile);
			
			System.out.println("Profile Created Successfully "+cnt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void SignIn() {
		try(UserDao userDao = new UserDao()){
	    System.out.println("Enter Email");
		String verifyEmail = sc.next();
		System.out.println("Enter Password");
		String verifyPassword = sc.next();
		userCurrent = userDao.signIn(verifyEmail, verifyPassword);
		
		System.out.println("Sign In Successfull");
		
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void changePassword(int userId) 
	{
		try(UserDao userDao = new UserDao())
		{
			System.out.println("Enter New Password :-");
	          String newpasswd = sc.next();
	          
	         userDao.changePassword(newpasswd, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateprofile(int userId)
	{
		try(UserDao userDao = new UserDao())
		{
			System.out.println("Enter First Name :-");
			String firstName = sc.next();
			System.out.println("Enter Last Name :-");
			String lastName = sc.next();
			System.out.println("Enter Mobile No :-");
			String mobile = sc.next();
			
			userCurrent= userDao.updateProfile(firstName, lastName, mobile, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
