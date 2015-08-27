package com.swing.Board;

import java.util.List;

public interface DAO<T> {
	/*
	  public Customer findCustomer(User user);
	  public boolean updateCustomer(User user);
	  public List selectCustomersRS(...);
	  public Collection selectCustomersTO(...);*/
	
	// insert
	public boolean inesrt(T t);
	// delete
	public boolean delete(T t, String key);
	// update
	public boolean update(T t);
	// find
	public boolean find(T t);
	// select
	public List<T> select();
	
}
