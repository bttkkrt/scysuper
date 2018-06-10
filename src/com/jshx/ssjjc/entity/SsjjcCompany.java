package com.jshx.ssjjc.entity;

import java.util.ArrayList;
import java.util.List;

import com.jshx.qyjbxx.entity.EntBaseInfo;


public class SsjjcCompany
{
	private List<EntBaseInfo> qylist = new ArrayList<EntBaseInfo>();
	public List<EntBaseInfo> getQylist() {
		return qylist;
	}
	public void setQylist(List<EntBaseInfo> qylist) {
		this.qylist = qylist;
	}
	
}
