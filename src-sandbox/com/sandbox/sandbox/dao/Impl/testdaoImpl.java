package com.sandbox.sandbox.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sandbox.sandbox.dao.testdao;
import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;



public class testdaoImpl  implements testdao {

	public SessionFactory  sessionFactory;  
	
	
	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return null;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
