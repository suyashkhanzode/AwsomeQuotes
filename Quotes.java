package com.suyash2000;



import java.util.Date;

public class Quotes  {
	
    private int id;
	private String author;
    private	String quotes;
    private int userid;

	
	private Date createdAt; 
	
	
    
	public Quotes(int id,String author,String text,int userId, Date createdAt ) {
		
	    this.id = id;
		this.author = author;
		this.quotes=text;
		this.userid = userId; 
		this.createdAt = createdAt;
		}


	public Quotes(String author, String quotes, int userid, Date createdAt) {
		super();
		this.author = author;
		this.quotes = quotes;
		this.userid = userid;
		this.createdAt = createdAt;
	}


	public Date getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}




	public Quotes() {
		
	}







	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getQuotes() {
		return quotes;
	}



	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}



	public int getUserid() {
		return userid;
	}



	public void setUserid(int userid) {
		this.userid = userid;
	}





	




	@Override
	public String toString() {
		return "Quotes [id=" + id + ", author=" + author + ", quotes=" + quotes + ", userid=" + userid + ", createdAt="
				+ createdAt + "]\n";
	}





	



	


	
	
}
