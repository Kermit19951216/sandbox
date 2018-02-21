package com.sandbox.sandbox.dao.Impl;

import java.util.List;

import org.hibernate.Session;

import com.sandbox.sandbox.dao.AnalysisModelDao;
import com.sandbox.sandbox.model.AnalysisModel;

public class AnalysisModelDaoImpl extends BaseDaoImpl<AnalysisModel, Integer> implements AnalysisModelDao {

	@Override
	public List<AnalysisModel> list() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try{
			return session.createQuery("from AnalysisModel").list();
		}finally {
			session.close();
		}
	}

}
