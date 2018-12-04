package com.techmgr.file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.file.model.vo.FileData;

public class FileDao implements IFileDao{

	@Override
	public int uploadFile(Connection conn, FileData fd) {
		PreparedStatement pstmt = null;
		int fileResult = 0;
		
		String query = "insert into files values(fileNo.nextval, ?, ?, ?, ?, ?, (select (last_number-1) FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'BOARDNO'))";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, fd.getFileName());
			pstmt.setString(2, fd.getFilePath());
			pstmt.setLong(3, fd.getFileSize());
			pstmt.setString(4, fd.getFileUser());
			pstmt.setTimestamp(5, fd.getUploadTime());
			
			fileResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return fileResult;
	}

	public FileData selectOneFile(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FileData fd = null;
		
		String query = "select * from files where board_id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fd = new FileData();
				
				fd.setFileName(rset.getString("file_name"));
				fd.setFilePath(rset.getString("file_path"));
				fd.setFileSize(rset.getLong("file_size"));
				fd.setFileUser(rset.getString("uploader_id"));
				fd.setUploadTime(rset.getTimestamp("upload_time"));
				fd.setBoardId(rset.getInt("board_id"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return fd;
	}

	public FileData fileDownload(String fileName, String userId, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FileData fd = null;

		String query = "select * from files where file_name =? and uploader_id=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setString(2, userId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				fd = new FileData();

				fd.setFileName(rset.getString("file_name"));
				fd.setFilePath(rset.getString("file_path"));
				fd.setFileSize(rset.getLong("file_size"));
				fd.setFileUser(rset.getString("uploader_id"));
				fd.setUploadTime(rset.getTimestamp("upload_time"));
				fd.setBoardId(rset.getInt("board_id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(rset);
			JDBCTemplate.Close(pstmt);
		}

		return fd;
	}

	

}
