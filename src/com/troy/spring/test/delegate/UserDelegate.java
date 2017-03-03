package com.troy.spring.test.delegate;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.troy.spring.test.data.dao.HibernateUserDAOImpl;
import com.troy.spring.test.data.dao.UserDAO;
import com.troy.spring.test.data.entity.User;

@Component
public class UserDelegate {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	HibernateUserDAOImpl hibernateUserDAOImpl;
	
	@Transactional
	public boolean registerUser(User user){
		if (userExists(user) == true)
			return false;
		
		return userDAO.registerUser(user);

	}

	public User loginUser(User user) {
		User userByUsername = userDAO.getUserByUsername(user.getUsername());

		return userByUsername;
	}

	public boolean userExists(User user) {
		if (userDAO.getUserByUsername(user.getUsername()) != null)
			return true;
		return false;
	}
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	@Transactional
	public boolean deleteUsers(List<String> delUsers)  {
		if(delUsers!=null&&!delUsers.isEmpty()) {
			if(userDAO.deleteUsers(delUsers)==0) {
				return true;
			}
			
		}
		
		return false;
	}

	public List<User> getUsersByUsername(List<String> listUsername) {
		if(listUsername!=null&&!listUsername.isEmpty()) {
			
			return userDAO.getUsersByUsername(listUsername);
		}
		return null;
	}

	@Transactional
	public boolean updateUsers(List<User> users) {
		
		if(users!=null && !users.isEmpty())
			return userDAO.updateUsers(users);
		return false;
	}
	
	public List<User> filterUsers(List<User> listUsers, User filterUser) {
		
		Iterator<User> i = listUsers.iterator();
		while (i.hasNext()) {
			User user = i.next(); // must be called before you can call i.remove()
			if(filterUser!=null) {
				String username = filterUser.getUsername();
				String nameFirst = filterUser.getNameFirst();
				String nameLast = filterUser.getNameLast();
				Boolean enabled = filterUser.getEnabled();
				
				if(username!=null&&!username.isEmpty()) {
					if(user.getUsername()!=null&&!user.getUsername().isEmpty()&&user.getUsername().contains(username)) {
						if (nameFirst != null && !nameFirst.isEmpty()) {
							if(user.getNameFirst()!=null&&!user.getNameFirst().isEmpty()&&user.getNameFirst().contains(nameFirst)) {
								if (nameLast != null && !nameLast.isEmpty()) {
									if(user.getNameLast()!=null&&!user.getNameLast().isEmpty()&&user.getNameLast().contains(nameLast)) {
										if(enabled!=null) {
											if (enabled==user.getEnabled()) {
												continue;
											} else {
												i.remove();
												continue;
											}
										}
									} else {
										i.remove();
										continue;
									}
								}
							} else {
								i.remove();
								continue;
							}
						}
						if (nameLast != null && !nameLast.isEmpty()) {
							if(user.getNameLast()!=null&&!user.getNameLast().isEmpty()&&user.getNameLast().contains(nameLast)) {
								if(enabled!=null) {
									if (enabled==user.getEnabled()) {
										continue;
									} else {
										i.remove();
										continue;
									}
								}
							} else {
								i.remove();
								continue;
							}
						}
						if(enabled!=null) {
							if (enabled==user.getEnabled()) {
								continue;
							} else {
								i.remove();
								continue;
							}
						} else
							continue;
					}
					i.remove();
					continue;
				}
				if (nameFirst != null && !nameFirst.isEmpty()) {
					if(user.getNameFirst()!=null&&!user.getNameFirst().isEmpty()&&user.getNameFirst().contains(nameFirst)) {
						if (nameLast != null && !nameLast.isEmpty()) {
							if(user.getNameLast()!=null&&!user.getNameLast().isEmpty()&&user.getNameLast().contains(nameLast)) {
								if (enabled==user.getEnabled()) {
									continue;
								} else {
									i.remove();
									continue;
								}
							} else {
								i.remove();
								continue;
							}
						}
						if(enabled!=null) {
							if (enabled==user.getEnabled()) {
								continue;
							} else {
								i.remove();
								continue;
							}
						} else
							continue;
					}
					i.remove();
					continue;
				}
				if (nameLast != null && !nameLast.isEmpty()) {
					if(user.getNameLast()!=null&&!user.getNameLast().isEmpty()&&user.getNameLast().contains(nameLast)) {
						if(enabled!=null) {
							if (enabled==user.getEnabled()) {
								continue;
							} else {
								i.remove();
								continue;
							}
						} else
							continue;
					}
					i.remove();
					continue;
				}
				if(enabled!=null) {
					if (enabled==user.getEnabled()) {
						continue;
					} else {
						i.remove();
						continue;
					}
				}
			}
		}
		return listUsers;
	}

	public void testHibernate() {
		hibernateUserDAOImpl.getAllUsers();
		
	}
	


}
