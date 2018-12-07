package com.techmgr.share.model.service;

import com.techmgr.file.model.vo.FileData;
import com.techmgr.share.model.vo.PageData;
import com.techmgr.share.model.vo.Share;
import com.techmgr.share.model.vo.ShareData;

public interface IShareService {
	public PageData selectAllShare(int currentPage);
	public int insertShare(Share share);
	public ShareData selectOneShare(int shareId);
}
