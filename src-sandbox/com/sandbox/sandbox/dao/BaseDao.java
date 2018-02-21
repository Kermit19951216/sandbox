package com.sandbox.sandbox.dao;

import java.io.Serializable;


public interface BaseDao<T , PK extends Serializable> {

	/** 
     * 新增一个实体（在数据库INSERT一条记录） 
     * @param object 实体对象 
     */  
	public abstract PK create(T object);
	
	/** 
     * 删除一个实体（在数据库DELETE一条记录） 
     * @param object 实体对象 
     */  
	public abstract void delete(T object);
	
	/** 
     * 修改一个实体（在数据库UPDATE一条记录） 
     * @param object 实体对象 
     */
	public abstract void update(T object);
	
	/** 
     * 根据主键从数据库中查找并返回一条记录
     * @param primarykey 主键
     * @return 实体对象
     */
	public abstract T QueryById(Class <? extends T> clazz , PK primarykey);
	
	
}
