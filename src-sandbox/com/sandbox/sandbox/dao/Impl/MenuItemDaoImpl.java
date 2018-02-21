package com.sandbox.sandbox.dao.Impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sandbox.sandbox.dao.MenuItemDao;
import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;

public class MenuItemDaoImpl extends BaseDaoImpl<MenuItem, Integer> implements MenuItemDao {

	@Override
	public List<MenuItem> list(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			Set<String> nameset = new HashSet<>();
			Iterator<Item> it = user.getItems().iterator();
			while(it.hasNext()){
				nameset.add(it.next().getName());
			}
			Query query = session.createQuery("from MenuItem mi where mi.name not in (:nameset)");
			query.setParameterList("nameset", nameset);
			List<MenuItem> list = query.list();
			return list;
		}finally {
			session.close();
		}
	}

	@Override
	public List<MenuItem> listAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
		      List<MenuItem> list = session.createQuery("from MenuItem").list();
		      return list;
		}finally {
			session.close();
		}
	}

}
