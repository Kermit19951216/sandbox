package com.sandbox.sandbox.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_menuitem")
public class MenuItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String Picname;
	
	private int isPublic;

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

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", Picname=" + Picname + ", isPublic=" + isPublic + "]";
	}
	
}
