package com.techmgr.notice.model.service;

import com.techmgr.notice.model.vo.PageData;

public interface INoticeListService {
	public PageData selectAllNotice(int currentPage);
}
