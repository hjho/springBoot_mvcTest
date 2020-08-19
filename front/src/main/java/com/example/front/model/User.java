package com.example.front.model;

import lombok.Data;

@Data
public class User {
	
	private String userId;
	private String userPw;
	private String name;
	private String authType;
	
	public User(String userId, String userPw, String authType) {
		this.userId = userId;
		this.userPw = userPw;
		this.authType = authType;
	}
	
	@Override
	public String toString() {
		return "User [userId="+userId+", userPw="+userPw+", name="+name+", authType="+authType+"]";
	}
	
}
