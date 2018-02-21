package com.sandbox.sandbox.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;

import com.mchange.v2.async.StrandedTaskReporting;


@Entity
@Table(name = "tb_item")
public class Item implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8587431169549623724L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@Column(name = "name" , length = 20 , nullable = false)
	private String name;
	
	//@Column(name = "Picname" , length = 30 , nullable = false)
	private String Picname;
	
	//@Column(name = "isPublic" , nullable = false , columnDefinition = "INT Default 0")
	private int isPublic;
	
	//@Column(name = "xloc" , nullable = true)
	private Integer xloc;
	
	//@Column(name = "yloc" , nullable = true)
	private Integer yloc;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "createDate" , nullable = true , updatable = false)
	//@org.hibernate.annotations.Generated(GenerationTime.INSERT)
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicname() {
		return Picname;
	}

	public void setPicname(String picname) {
		Picname = picname;
	}

	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getXloc() {
		return xloc;
	}

	public void setXloc(Integer xloc) {
		this.xloc = xloc;
	}

	public Integer getYloc() {
		return yloc;
	}

	public void setYloc(Integer yloc) {
		this.yloc = yloc;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", Picname=" + Picname + ", isPublic=" + isPublic + ", xloc="
				+ xloc + ", yloc=" + yloc + ", createDate=" + createDate + "]";
	}
}
