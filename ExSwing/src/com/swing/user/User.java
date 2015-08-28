package com.swing.user;

public class User {
	private String email;
	private String birth;
	private String id;
	private String pwd;
	private String profilePath;
	
	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	// ID를 찾기 위해 필요한 것
	public User(String id, String pwd){
		this.id = id;
		this.pwd = pwd;
	}
	
	public User(String id, String pwd, String birth, String email){
		this.id = id;
		this.pwd = pwd;
		this.birth = birth;
		this.email = email;
	}

	@Override
	public String toString(){
		return id+" " + pwd + " " + birth + " " + email+ "\n";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
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
