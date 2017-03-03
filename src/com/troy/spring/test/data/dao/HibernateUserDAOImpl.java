package com.troy.spring.test.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.troy.spring.test.data.entity.hib.Users;



@Repository
public class HibernateUserDAOImpl extends BaseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Users> getAllUsers() {
		Session session = getSessionFactory().openSession();
		String hql = " from Users";
		Query query = session.createQuery(hql);
		
		List<Users> users = query.list();
		if(users!=null) {
			for(Users u : users) {
				System.out.println(u.getId());
			}
		}
		return users;
	}

}
