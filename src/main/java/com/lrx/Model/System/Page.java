package com.lrx.Model.System;

/***
 * 分页类
 */
public class Page
{
	private long pageIndex;
	private long pageRows;
	private long pageBegin;
	private long pageEnd;
	public Page(long Page, long Rows)
	{
		pageIndex = Page > 0 ? Page - 1 : Page;
		pageRows = Rows;
	}
	public long getPageBegin() {
		pageBegin = pageIndex * pageRows + 1;
		return pageBegin;
	}
	public long getPageEnd() {
		pageEnd = pageIndex * pageRows + pageRows;
		return pageEnd;
	}
}