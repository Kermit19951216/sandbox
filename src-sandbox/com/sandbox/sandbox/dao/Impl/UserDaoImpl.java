package com.sandbox.sandbox.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sandbox.sandbox.dao.UserDao;
import com.sandbox.sandbox.model.User;

public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> QueryByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			String hql = "from User u where u.name = :name ";
			Query query = session.createQuery(hql);
			query.setString("name",name);
			List<User> list = query.list();
			if(list != null){
				return list;
			}else{
				return null;
			}
		}finally {
			//transaction.commit();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> QueryUnConfirm() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			String hql = "from User u where u.isConfirm = 0";
			Query query = session.createQuery(hql);
			List<User> list = query.list();
			if(list != null){
				return list;
			}else{
				return null;
			}
		}finally {
			//session.getTransaction().commit();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			List<User> list = session.createQuery("from User").list();
			if(list != null){
				return list;
			}else{
				return null;
			}
		}finally {
			//session.getTransaction().commit();
			session.close();
		}
	}

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
