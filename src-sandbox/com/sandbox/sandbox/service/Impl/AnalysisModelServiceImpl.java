package com.sandbox.sandbox.service.Impl;

import java.util.List;

import com.sandbox.sandbox.model.AnalysisModel;
import com.sandbox.sandbox.service.AnalysisModelService;

public class AnalysisModelServiceImpl extends BaseServiceImpl<AnalysisModel, Integer> implements AnalysisModelService {

	@Override
	public List<AnalysisModel> list() {
		// TODO Auto-generated method stub
		return sandboxAnalysisModelDao.list();
	}

}
