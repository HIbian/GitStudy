package com.hr.servce;

import java.util.List;

import com.hr.bean.Download;

public interface DownloadServce {
	int uploadFile(Download dl);
	int getTotalCount();
	List<Download> getDownloadFiles();
	Download getFileById(int downloadId);
}
