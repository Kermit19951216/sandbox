package com.sandbox.sandbox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_analysis")
public class AnalysisModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String itemname;
	
	private String params;
	
	private int judge;
	
	private String standard;
	
	private String result;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getJudge() {
		return judge;
	}

	public void setJudge(int judge) {
		this.judge = judge;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AnalysisModel [id=" + id + ", itemname=" + itemname + ", params=" + params + ", judge=" + judge
				+ ", standard=" + standard + ", result=" + result + "]";
	}
}
