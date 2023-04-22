package com.suyash2000;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteDao implements AutoCloseable {
	private Connection con;

	public QuoteDao() throws Exception {
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

	public int likeQuote(int userId, int quoteId) throws Exception {
		int cnt = 0;
		String sql = "INSERT INTO fav_quotes(user_id,quote_id) VALUES(?,?)";

		try (PreparedStatement prs = con.prepareStatement(sql)) {
			prs.setInt(1, userId);
			prs.setInt(2, quoteId);
			cnt = prs.executeUpdate();

		}
		return cnt;

	}

	public List<Quotes> allQuote() throws Exception {
		List<Quotes> list = new ArrayList<>();

		String sql = "SELECT id,author,quote,user_id ,created_at from quotes";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Quotes q = new Quotes();
				q.setId(rs.getInt("id"));
				q.setAuthor(rs.getString("author"));
				q.setQuotes(rs.getString("quote"));
				q.setUserid(rs.getInt("user_id"));
				Timestamp ts = rs.getTimestamp("created_at");
				q.setCreatedAt(new Date(ts.getTime()));
				list.add(q);

			}
		}
		return list;

	}

	public List<Quotes> myQuotes(int userId) throws Exception {

		List<Quotes> list = new ArrayList<Quotes>();

		String sql = "SELECT id,author,quote,created_at from quotes where user_id = ?";
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Quotes q = new Quotes();
				q.setId(rs.getInt("id"));
				q.setAuthor(rs.getString("author"));
				q.setQuotes(rs.getString("quote"));
				q.setUserid(userId);
				Timestamp ts = rs.getTimestamp("created_at");
				q.setCreatedAt(new Date(ts.getTime()));
				list.add(q);

			}

		}
		return list;
	}

	public List<Quotes> favQuotes(int userId) throws Exception {
		List<Quotes> list = new ArrayList<Quotes>();

		String sql = "SELECT q.id,  q.author, q.quote,q.user_id, q.created_at FROM quotes q INNER JOIN fav_quotes fq ON q.id = fq.quote_id WHERE fq.user_id=?";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, userId);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Quotes q = new Quotes();
				q.setId(rs.getInt("id"));
				q.setAuthor(rs.getString("author"));
				q.setQuotes(rs.getString("quote"));
				q.setUserid(rs.getInt("user_id"));
				Timestamp ts = rs.getTimestamp("created_at");
				q.setCreatedAt(new Date(ts.getTime()));
				list.add(q);

			}
		}
		return list;

	}

	public int addQuote(Quotes q) throws Exception {
		int cnt;
		String sql = "INSERT INTO quotes(author,quote,user_id,created_at) VALUES(?,?,?,?)";
		try (PreparedStatement prs = con.prepareStatement(sql)) {
			prs.setString(1, q.getAuthor());
			prs.setString(2, q.getQuotes());
			prs.setInt(3, q.getUserid());
			long time = q.getCreatedAt().getTime();
			Timestamp createdAt = new Timestamp(time);
			prs.setTimestamp(4, createdAt);
			cnt = prs.executeUpdate();
			
			System.out.println("Quote Created Successfully");
		}
		return cnt;
	}

	public void editQuote(int quoteId, String author, String quote, int userId) throws Exception {
		
		String sql = "UPDATE quotes set author =?,quote = ? where id =? AND user_id =?";
		try (PreparedStatement prs = con.prepareStatement(sql)) {
			prs.setString(1, author);
			prs.setString(2, quote);
			prs.setInt(3, quoteId);
			prs.setInt(4, userId);
		     prs.executeUpdate();
			System.out.println("Quote Updated Successfully");
		}
	}
	
	public void deleteQuote(int quoteId,int userId) throws Exception {
		
		String sql = "DELETE from quotes where id =? AND user_id = ?";
		try (PreparedStatement prs = con.prepareStatement(sql)) {
			prs.setInt(1, quoteId);
			prs.setInt(2, userId);
			int cnt = prs.executeUpdate();
			System.out.println("Quote Deleted Successfully");
		}
		
	}

}
