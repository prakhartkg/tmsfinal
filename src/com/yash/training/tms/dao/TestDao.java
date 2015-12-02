package com.yash.training.tms.dao;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class TestDao {

	@Resource(lookup = "java:jboss/datasources/tms")
	private DataSource dSource;
	
	public void checkDS(){
		try {
			System.out.println(dSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
