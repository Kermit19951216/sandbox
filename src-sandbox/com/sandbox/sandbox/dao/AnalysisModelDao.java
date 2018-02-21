package com.sandbox.sandbox.dao;

import java.util.List;

import com.sandbox.sandbox.model.AnalysisModel;

public interface AnalysisModelDao extends BaseDao<AnalysisModel, Integer> {

	public abstract List<AnalysisModel> list();
	
}
