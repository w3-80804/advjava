package com.sunbeam.dmc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements AutoCloseable {
	
	
	private Connection con;
	
	public UserDao() throws Exception {
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
	
	
	public User FindByEmail(String email) throws Exception{
		String query = "SELECT * FROM users WHERE EMAIL = ?";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setString(1, email);
			try(ResultSet rs  = statement.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					email = rs.getString("email");
					String address = rs.getString("address");
					Date birth = rs.getDate("birth");
					int enabled = rs.getInt("enabled");
					String role = rs.getString("role");
					User user = new User(id, name, password, mobile, email, address, birth, enabled, role);
					System.out.println(user);
					return user;
				}
			}
		}
		return null;
	}
	
	
	public int save(User u) throws Exception {
		String query = "INSERT INTO users values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setInt(1, u.getId());
			statement.setString(2, u.getName());
			statement.setString(3, u.getPassword());
			statement.setString(4, u.getMobile());
			statement.setString(5, u.getEmail());
			statement.setString(6, u.getAddress());
			statement.setDate(7, u.getBirth());
			statement.setInt(8, u.getEnabled());
			statement.setString(9, u.getRole());
			int count = statement.executeUpdate();
			return count;
		}
	}
	
	public int update(User u) throws Exception {
		String query = "UPDATE users SET name=?, password=?,"
				+ " mobile=?, email=?, address=?, birth=?, enabled=?, role=? WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setString(1, u.getName());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getMobile());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getAddress());
			statement.setDate(6, u.getBirth());
			statement.setInt(7, u.getEnabled());
			statement.setString(8, u.getRole());
			statement.setInt(9, u.getId());
			int count = statement.executeUpdate();
			return count;	
		}	
	}

	public int deleteById(int id) throws Exception {
		String query = "DELETE from users WHERE id = ?";
		try(PreparedStatement statement = con.prepareStatement(query)){
			statement.setInt(1, id);
			int count = statement.executeUpdate();
			return count;
		}
	}
	
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<>();
		String query = "SELECT * FROM users";
		try(PreparedStatement statement = con.prepareStatement(query)){
			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					String email = rs.getString("email");
					String address = rs.getString("address");
					Date birth = rs.getDate("birth");
					int enabled = rs.getInt("enabled");
					String role = rs.getString("role");
					User user = new User(id, name, password, mobile, email, address, birth, enabled, role);
					list.add(user);
				}
			}
		}
		return list;
	}
		

}
