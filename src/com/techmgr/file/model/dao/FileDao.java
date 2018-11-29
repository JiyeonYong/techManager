package com.techmgr.file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.file.model.vo.FileData;

public class FileDao implements IFileDao{

	@Override
	public int uploadFile(Connection conn, FileData fd) {
		PreparedStatement pstmt = null;
		int fileResult = 0;
		
		String query = "fileNo.nextval, ?, ?, ?, ?, sysdate, boardNo.currval";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, fd.getFileName());
			pstmt.setString(2, fd.getFilePath());
			pstmt.setLong(3, fd.getFileSize());
			pstmt.setString(4, fd.getFileUser());
			
			fileResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return fileResult;
	}

	

}
