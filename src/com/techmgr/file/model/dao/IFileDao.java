package com.techmgr.file.model.dao;

import java.sql.Connection;

import com.techmgr.file.model.vo.FileData;

public interface IFileDao {
	public int uploadFile(Connection conn, FileData fd);
}
 