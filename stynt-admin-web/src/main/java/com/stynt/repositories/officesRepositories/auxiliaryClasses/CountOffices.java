package com.stynt.repositories.officesRepositories.auxiliaryClasses;

import java.util.List;

public class CountOffices {

	public String sql;
	public List<Object> obj;
	
	public CountOffices(String sql, List<Object> obj) {
		this.sql = sql;
		this.obj = obj;
	}
}
