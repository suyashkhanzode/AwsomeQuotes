package com.suyash2000;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class QuoteService {

	Scanner sc = new Scanner(System.in);

	public void likeQuote() {
		try (QuoteDao quoteDao = new QuoteDao()) {
			System.out.println("Enter User ID");
			int userId = sc.nextInt();
			System.out.println("Enter Quote ID");
			int quoteId = sc.nextInt();

			int cnt = quoteDao.likeQuote(userId, quoteId);

			System.out.println("Quotes like " + cnt);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void allQuote() {
		try (QuoteDao quotes = new QuoteDao()) {
			List<Quotes> quotes1 = new ArrayList<Quotes>();

			quotes1 = quotes.allQuote();
			System.out.println(quotes1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void myQuote(int userId) {
		try (QuoteDao quotes = new QuoteDao()) {
			List<Quotes> quotes2 = new ArrayList<Quotes>();

			quotes2 = quotes.myQuotes(userId);
			System.out.println(quotes2);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void favQuotes(int userId) {
		try (QuoteDao quotes = new QuoteDao()) {
			List<Quotes> quotes2 = new ArrayList<Quotes>();

			quotes2 = quotes.favQuotes(userId);
			System.out.println(quotes2);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void addquote(int userId) {

		try (QuoteDao quotes = new QuoteDao()) {
			System.out.println("Enter Author");
			String author = sc.next();
			System.out.println("Enter Quote");
			sc.nextLine();
			String quote = sc.nextLine();
			long time = new Date().getTime();
			Timestamp createdAt = new Timestamp(time);

			Quotes q = new Quotes(author, quote, userId, createdAt);
			int cnt = quotes.addQuote(q);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void editQuote(int userId)
	{
		try(QuoteDao quotes = new QuoteDao()){
		System.out.println("Enter Quote ID");
		int quoteId = sc.nextInt();
		
		System.out.println("Enter Author");
		sc.nextLine();
		String author = sc.nextLine();
		System.out.println("Enter Quote");
		String quote = sc.nextLine();
		quotes.editQuote(quoteId, author, quote, userId);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void deleteQuote(int userId) {
		
		try(QuoteDao quotes = new QuoteDao()){
			System.out.println("Enter Quote ID");
			int quoteId = sc.nextInt();
			quotes.deleteQuote(quoteId, userId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
