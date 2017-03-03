package com.troy.spring.test.data.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.troy.spring.test.data.entity.User;

@Repository
public class UserDAO extends BaseDAO {


	public boolean registerUser(User user)  {
		String sql = "INSERT INTO troy.my_users(username,password, name_first, name_last, enabled) VALUES (?, ?, ?, ?, ?)";
		String role = "INSERT INTO user_roles (username, role) VALUES (?, 'ROLE_USER')";
		Object[] params = {user.getUsername(), user.getPassword(), user.getNameFirst(), user.getNameLast(), 1};
		Object[] roleParams = {user.getUsername()};
		int result = sqlInsertOrUpdate(sql, params, null);
		int result2 = sqlInsertOrUpdate(role, roleParams, null);
		if(result>-1&&result2>-1) {
			return true;
		}
		return false;
	}

	public User getUserByUsername(String username) {
		String sql = "select username from my_users where username=?";
		Object[] params = {username};
		try {
			SqlRowSet rs = sqlSelect(sql, params, null);
			User user = new User();
			if(rs.next()) {
				user.setUsername(rs.getString(1));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		List<User> listUsers = new LinkedList<User>();
		String sql = "select username,name_first, name_last, enabled  from my_users";
		//no params
		try {
			SqlRowSet rs = sqlSelect(sql, null, null);
			while(rs.next()) {
				User user = new User();
				user.setUsername (rs.getString(1));
				user.setNameFirst(rs.getString(2));
				user.setNameLast (rs.getString(3));
				user.setEnabled  (rs.getString(4)!=null&&rs.getString(4).equals("1")?true:false);
				listUsers.add(user);
			}
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}

	public int deleteUsers(List<String> listUsers) {
		String rolesSql = "delete from user_roles where username =?";
		String usersSql = "delete from my_users where username =?";
		
		try {
			for(String user : listUsers) {
				Object[] params = new Object[] {user};
				sqlDelete(rolesSql, params, null);
				sqlDelete(usersSql, params, null);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	public List<User> getUsersByUsername(List<String> listUsername) {
		List<User> listUsers = null;
		String sql = "select username,name_first, name_last, enabled  from my_users where username=?";
		//no params
		try {
			for(String userName : listUsername) {
				Object[] params = new Object[] {userName};
				SqlRowSet rs = sqlSelect(sql, params, null);
				
				if(rs.next()) {
					if(listUsers==null)
						listUsers = new LinkedList<User>();
					User user = new User();
					user.setUsername (rs.getString(1));
					user.setNameFirst(rs.getString(2));
					user.setNameLast (rs.getString(3));
					user.setEnabled  (rs.getString(4)!=null&&rs.getString(4).equals("1")?true:false);
					listUsers.add(user);
				}
			}
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}
	
	public boolean updateUsers(List<User> users) {
		String sql = "update my_users SET name_first=?, name_last=?, enabled=? where username =?";
		
		try {
			for(User user : users) {
				Object[] params = new Object[] {user.getNameFirst(), user.getNameLast(), user.isEnabled(), user.getUsername()};
				sqlInsertOrUpdate(sql, params, null);
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
