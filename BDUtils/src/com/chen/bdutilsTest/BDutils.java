package com.chen.bdutilsTest;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class BDutils {
	public void quert() throws SQLException{
		// 将数据源传递给QueryRunner，QueryRunner内部通过数据源获取数据库连接
		QueryRunner qr = new QueryRunner(druidtest.getdatasource());
		System.out.println(qr);
	}
}
