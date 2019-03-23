package org.gdufe.springmvc.bean;

public class User {
	private String id;
	private String username;
	private String password;
	private String age;
	private Address address;

	public User(String username, String password, String age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}


	public User(String id, String username, String password, String age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + "]";
	}


}
