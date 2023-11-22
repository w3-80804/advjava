package com.sunbeam.dmc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements AutoCloseable{
	
	private Connection con;
	
	public BookDao() throws Exception  {
		con = DbUtil.getConnection();
	}

	@Override
	public void close() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int deleteById(int id) throws Exception {
		String query = "DELETE from books WHERE id = ?";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setInt(1, id);
			int count = statement.executeUpdate();
			return count;
		}
	}
	
	public List<Book> findAll() throws Exception {
		List<Book> list = new ArrayList<>();
		String query = "SELECT * FROM books";
		try(PreparedStatement statement = con.prepareStatement(query)){
			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					list.add(b);
				}
			}
		}
		return list;	
	}
	
	
	public List<Book> findBySubject(String subject) throws Exception {
		List<Book> list = new ArrayList<>();
		String query = "SELECT * FROM books WHERE subject=?";
		try(PreparedStatement statement = con.prepareStatement(query)) {
			statement.setString(1, subject);
			try(ResultSet rs = statement.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					list.add(b);
				}
			} 
		} 
		return list;
	}
	
	public int save(Book b) throws Exception {
		String query = "INSERT INTO books VALUES(?, ?, ?, ?, ?)";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setInt(1, b.getId());
			statement.setString(2, b.getName());
			statement.setString(3, b.getAuthor());
			statement.setString(4, b.getSubject());
			statement.setDouble(5, b.getPrice());
			int count = statement.executeUpdate();
			return count;
		}
	}
	
	public List<Book> findByAuthor(String author) throws Exception {
		List<Book> list = new ArrayList<>();
		String query = "SELECT * FROM books WHERE Author = ?";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setString(1, author);
			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					list.add(b);
				}
			}
		}
		return list;
	}
	
	
	public List<String> getSubjects() throws Exception {
		List<String> list = new ArrayList<>();
		String query = "SELECT DISTINCT Subject FROM books";
		try(PreparedStatement statement = con.prepareStatement(query)){
			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					list.add(rs.getString("Subject"));
				}
			}
		}
		return list;
	}
	
	public Book findById(int id) throws Exception {
		String query = "SELECT * FROM books WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, id);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					return b;
				}
			} 
		} 
		return null;
	}
	
	public int update(Book b) throws Exception {
		String query = "UPDATE books SET name=?, author=?, subject=?, price=? WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(query)) {
			statement.setString(1, b.getName());
			statement.setString(2, b.getAuthor());
			statement.setString(3, b.getSubject());
			statement.setDouble(4, b.getPrice());
			statement.setInt(5, b.getId());
			int count = statement.executeUpdate();
			return count;
		} 
	}
}
