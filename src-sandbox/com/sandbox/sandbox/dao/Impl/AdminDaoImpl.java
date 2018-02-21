package com.sandbox.sandbox.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sandbox.sandbox.dao.AdminDao;
import com.sandbox.sandbox.model.Admin;

public class AdminDaoImpl extends BaseDaoImpl<Admin, Integer> implements AdminDao {

	@Override
	public List<Admin> QueryAdminByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			Query query = session.createQuery("from Admin a where a.name = :name");
			query.setString("name", name);
			List<Admin> list = query.list();
			if(list.size()>0){
				return list;
			}else{
				return null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			//session.getTransaction().commit();
			session.close();
		}
	}

}
