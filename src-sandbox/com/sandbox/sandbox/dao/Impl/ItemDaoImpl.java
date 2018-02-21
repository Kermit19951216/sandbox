package com.sandbox.sandbox.dao.Impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mchange.v2.c3p0.QueryConnectionTester;
import com.sandbox.sandbox.dao.ItemDao;
import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;

public class ItemDaoImpl extends BaseDaoImpl<Item, Integer> implements ItemDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> QueryByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			String hql = "from Item i where i.name = :name";
			Query query = session.createQuery(hql);
			query.setString("name", name);
			List<Item> list = query.list();
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
	public List<Item> listAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			List<Item> list = session.createQuery("from Item").list();
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

	@Override
	public List<Item> LoadUnusedItem(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Set<Item> set = user.getItems();
			if(set == null){
				return null;
			}else{
				Query query = session.createQuery("from Item i "
						+ "where i not in (:items)");
				query.setParameterList("items", set);
				List<Item> items = query.list();
				//System.out.println(items.get(0));
				return items;
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

	@Override
	public void deleteItemCheck(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery("delete from tb_user_item where iid = :id");
			query.setInteger("id", item.getId());
			query.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//session.getTransaction().rollback();
		}finally {
			//session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public void clearUserItem(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery("select iid from tb_user_item where uid = :uid");
			query.setInteger("uid", user.getId());
			List<Integer> list = query.list();
			for(Integer integer : list){
				System.out.println(integer);
			}
			if(list.size()>0){
				Query query2 = session.createSQLQuery("delete from tb_item where id in (:id)");
				query2.setParameterList("id", list).executeUpdate();
			}
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	/*@Override
	public void delete(Item item){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Integer id = item.getId();
			Query query = session.createSQLQuery("delete * "
					+ "from tb_user_item where iid = :id");
			query.setInteger("id", id);
			query.list();
			session.delete(item);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.getTransaction().commit();
			session.close();
		}
	}*/
	
}
