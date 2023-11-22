package com.sunbeam.dmc;

import java.sql.Date;
public class User {
	private int id;
	private String name;
	private String password;
	private String mobile;
	private String email;
	private String address;
	private Date birth;
	private int enabled;
	private String role;
	public User() {
	}
	public User(int id, String name, String password, String mobile, String email, String address, Date birth,
			int enabled, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.birth = birth;
		this.enabled = enabled;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", mobile=" + mobile + ", email="
				+ email + ", address=" + address + ", birth=" + birth + ", enabled=" + enabled + ", role=" + role + "]";
	}
}
