package com.sandbox.sandbox.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name="tb_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755179707470034866L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@Column(name = "name" ,length = 20 , nullable = false)
	private String name;
	
	//@Column(name = "password" , length = 20 , nullable = false)
	private String password;
	
	//@Column(name = "isConfirm" , nullable = false , columnDefinition = "INT Default 0")
	private int isConfirm;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "createDate" , updatable = false , nullable = true)
	//@Generated(GenerationTime.INSERT)
	private Date createDate;
	
	@ManyToMany(  fetch = FetchType.EAGER )
	@JoinTable( name = "tb_user_item" , 
	  joinColumns = {@JoinColumn(name = "uid" , referencedColumnName = "id")},
	  inverseJoinColumns = {@JoinColumn(name = "iid" , referencedColumnName = "id")})
	private Set<Item> items = new HashSet<Item>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", isConfirm=" + isConfirm + "]";
	}
	
}
