package com.techmgr.file.model.service;

import java.sql.Connection;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.file.model.dao.FileDao;
import com.techmgr.file.model.vo.FileData;

public class FileService {
	
	public int uploadFile(FileData fd) {
		Connection conn = JDBCTemplate.getConnection();

		int fileInsertResult = new FileDao().uploadFile(conn, fd);
		
		if(fileInsertResult > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return fileInsertResult;
	}
}
