package com.sandbox.sandbox.dao.Impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sandbox.sandbox.dao.BaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	protected SessionFactory sessionFactory;

	@Override
	public PK create(T object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			//session.persist(object);
			Serializable result = session.save(object);
			PK key = (PK)result;
			session.getTransaction().commit();
			return key;
		}catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			return null;
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T QueryById(Class <? extends T> clazz , PK primarykey) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			return (T)session.get(clazz , primarykey);
		}finally {
			session.getTransaction().commit();
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
