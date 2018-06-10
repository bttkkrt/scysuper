package com.jshx.http.bean;

import com.duanpf.http.comm.BaseResponse;

public class SummaryBean extends BaseResponse {
	
	private String id;
	private String content;
	
	private String message;
	
	private String total;//总条数
	
	private String page;//总页数
	
	public SummaryBean() {
		super();
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
