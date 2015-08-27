package com.swing.user;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swing.Board.Board;
import com.swing.ex.FileRelated;
import com.swing.ex.Status;

public class UserDAO {
	private Map<String, User> userMap;
	private static UserDAO dao;

	private UserDAO() {
		init();
	}

	public static UserDAO getInstance() {
		return (dao == null ? dao = new UserDAO() : dao);
	}

	public void init() {
		// userList�� user�� ������ ��� ��´�.
		// File�� ���� Board�� ������ �´�.
		userMap = new HashMap<String, User>();
		
		String[] users = FileRelated.openFile(String.valueOf(Status.get("TablePath"))+"\\users.txt");
		
		for(String user : users){
			String[] split = user.split(" ");
			//User(String id, String pwd, String name, String email)
			userMap.put(split[0],new User(split[0],split[1],split[2],split[3]));
		}
		
		
	}

	public boolean hasUser(String userID) {
		return userMap.containsKey(userID);
	}

	public User findUser(String userID) {
		User user = null;
		if (userMap.containsKey(userID))
			user = userMap.get(userID);

		return user;
	}

	public boolean insertUser(User user) {
		// User�� ������ ��, false ��ȯ
		return ( hasUser(user.getId()) ) ? false : (userMap.put(user.getId(), user) == null) ;
	}

	/*
	 * ���� ������ �ִ� user�� ����� �������� Method ���������� Password Ȯ���ϱ� ���ؼ� ���Ǵ� ���� Method
	 * 
	 * @return User�� Object Array�� ��ȯ���ش�.
	 */

	public User[] getUsers(){

		if (userMap.size() == 0) {
			return null;
		}
		User[] users = new User[userMap.size()];

		System.arraycopy(userMap, 0, users, 0, users.length);

		return users;
	}
}
