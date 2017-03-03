package com.troy.spring.test.data.entity;

import java.util.List;

public class Member {

	private boolean newMember;
	
	private List<String> courses2;
	
	public boolean isNewMember() {
		return newMember;
	}

	public void setNewMember(boolean newMember) {
		this.newMember = newMember;
	}

	public List<String> getCourses2() {
		return courses2;
	}

	public void setCourses2(List<String> courses2) {
		this.courses2 = courses2;
	}
	
}