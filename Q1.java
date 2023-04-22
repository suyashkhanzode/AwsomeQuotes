package com.suyash2000;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class Q1 {
	public static User userCurrent = null;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int signUpChoice;
		
		do {
			System.out.println("-----------------------------------------------");
			System.out.println("| 0] Exit         1]SignIn        2]SignUp    |");
			System.out.println("-----------------------------------------------");
			
			signUpChoice = sc.nextInt();
			switch (signUpChoice) {
			case 1:
				UserService userService = new UserService();
				userService.SignIn();
				if(userCurrent != null)
					userMenu();
				break;
             case 2:
            	 UserService userService1 = new UserService();
         		userService1.SignUp();
				break;

			default:
				break;
			}
			
			
		} while (signUpChoice != 0);
			
	}
	
	public static void userMenu() {
		Scanner sc = new Scanner(System.in);
		int userChoice;
		do {
			System.out.println("Hello, " + userCurrent.getFirstName() + " " +userCurrent.getLastName());
			System.out.println("0. Sign Out");
			System.out.println("1. All Quotes");
			System.out.println("2. My Quotes");
			System.out.println("3. My Favorite Quotes");
			System.out.println("4. Like/Unlike Quote");
			System.out.println("5. New Quote");
			System.out.println("6. Edit Quote");
			System.out.println("7. Delete Quote");
			System.out.println("8. Change Password");
			System.out.println("9. Edit Profile");
			userChoice = sc.nextInt();
			
			switch (userChoice) {
			case 1:
				QuoteService quoteService = new QuoteService();
				quoteService.allQuote();
				break;
             case 2:
            	 QuoteService quoteService2 = new QuoteService();
 				quoteService2.myQuote(userCurrent.getUserId());
				break;
             case 3:
            	 QuoteService quoteservice3 = new QuoteService();
  				quoteservice3.favQuotes(userCurrent.getUserId());
 				break;
             case 4:
 				QuoteService quoteservice1 = new QuoteService();
 				quoteservice1.likeQuote();
 				break;
             case 5:
  				QuoteService quoteservice5 = new QuoteService();
  				quoteservice5.addquote(userCurrent.getUserId());
  				break;
             case 6:
            	 QuoteService quoteservice6 = new QuoteService();
   				quoteservice6.editQuote(userCurrent.getUserId());
  				break;
             case 7:
            	 QuoteService quoteservice7 = new QuoteService();
    			quoteservice7.deleteQuote(userCurrent.getUserId());
  				break;
             case 8:
            	 UserService userService1 = new UserService();
           		userService1.changePassword(userCurrent.getUserId());
  				break;
             case 9:
            	 UserService userService2 = new UserService();
            		userService2.updateprofile(userCurrent.getUserId());
   				break;

			default:
				System.out.println("Wrong Choice");
				break;
			}
			
		} while (userChoice != 0);
	}

}
