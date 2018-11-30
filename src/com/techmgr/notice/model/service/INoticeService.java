package com.techmgr.notice.model.service;

import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.PageData;

public interface INoticeService {
	public PageData selectAllNotice(int currentPage);
	public int insertNotice(Notice notice);
	public Notice selectOneNotice(int noticeId);
}
