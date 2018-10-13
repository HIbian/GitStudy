package com.hr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hr.bean.Download;
import com.hr.utils.C3P0Utils;

public class DownloadDaoimpl implements DownloadDao{
	
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
	@Override
	public int uploadFile(Download dl) {
		String sql = "insert into t_download values(null,?,?,?,?,?)";
		List<Object> parms = new ArrayList<>();
		parms.add(dl.getDownloadTittle());
		parms.add(dl.getDownloadCreateTime());
		parms.add(dl.getUserId()	);
		parms.add(dl.getDownloadDes());
		parms.add(dl.getDownPath());
		try {
			return qr.update(sql, parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getTotalCount() {
		try {
			Long totalCount = qr.query("select count(*) from t_download", new ScalarHandler<Long>(1));
			return totalCount.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Download> getDownloadFiles() {
		try {
			List<Download> dls = qr.query("select * from t_download", new BeanListHandler<>(Download.class));
			return dls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Download getFileById(int downloadId) {
		try {
			return qr.query("select * from t_download where downloadId=?", new BeanHandler<>(Download.class),downloadId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
