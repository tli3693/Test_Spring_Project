package com.troy.spring.test.data.entity;

import java.util.List;

public class Admin {
	
	private List<Object> users2;

	private List<String> listUsername;
	
	private String name;
	
	private List<User> listUsers;
	
	public List<Object> getUsers2() {
		return users2;
	}

	public void setUsers2(List<Object> users2) {
		this.users2 = users2;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addUser(User user) {
		this.users2.add(user);
	}

	public List<String> getListUsername() {
		return listUsername;
	}

	public void setListUsername(List<String> listUsername) {
		this.listUsername = listUsername;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	
}
