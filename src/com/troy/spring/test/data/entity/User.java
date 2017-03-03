package com.troy.spring.test.data.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "my_users")
public class User {

	@Id
	@SequenceGenerator(name = "test_user_seq", sequenceName = "test_user_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_user_seq")
	private long id;
	@Column
	@Size(min = 6, max = 25, message = "Username needs to be 6-25 characters long")
	@NotNull(message = "Username cannot be empty")
	private String username;

	@Column
	@Size(min = 6, max = 25, message = "Password needs to be 6-25 characters long")
	@NotNull(message = "Password cannot be empty")
	private String password;
	private Boolean enabled;
	@Column
	@Size(min = 1, max = 25, message = "Must enter a first name")
	@NotNull(message = "First name cannot be empty")
	private String nameFirst;
	@Column
	@Size(min = 1, max = 25, message = "Must enter a last name")
	@NotNull(message = "Last name cannot be empty")
	private String nameLast;
	
	@Override
	public boolean equals(Object obj) {
		User newUser = (User) obj;
		
		if(this.username!=null&&newUser.username!=null&&this.username.equals(newUser.username))
			return true;

		return false;
	}

	@Override
	public int hashCode() {
		int prime = 37;
		return prime*Objects.hash(username,nameFirst,nameLast);
	}

	public User() {

	}
	
	@Override
	public String toString() {
		
		return this.username;
	}

	public Boolean isEnabled() {
		return enabled;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;

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

}
