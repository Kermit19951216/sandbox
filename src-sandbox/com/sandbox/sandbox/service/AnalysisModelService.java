package com.sandbox.sandbox.service;

import java.util.List;

import com.sandbox.sandbox.model.AnalysisModel;

public interface AnalysisModelService extends BaseService<AnalysisModel, Integer>{

	public abstract List<AnalysisModel> list();
	
}
