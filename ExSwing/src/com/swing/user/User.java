package com.swing.user;

public class User {
	private String email;
	private String name;
	private String id;
	private String pwd;
	
	// ID를 찾기 위해 필요한 것
	public User(String id, String pwd){
		this.id = id;
		this.pwd = pwd;
	}
	
	public User(String id, String pwd, String name, String email){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString(){
		return id+" " + pwd + " " + name + " " + email+ "\n";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
