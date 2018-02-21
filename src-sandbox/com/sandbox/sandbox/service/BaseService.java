package com.sandbox.sandbox.service;

import java.io.Serializable;

public interface BaseService<T,PK extends Serializable> {

	public abstract PK create(T object);
	public abstract void update(T object);
	public abstract void delete(T object);
	
}
