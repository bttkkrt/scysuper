/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 11, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.vo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jshx.core.utils.SysPropertiesUtil;


/**
 * 分页对象
 * 
 * @author john.zhang
 * @version 创建时间：Jan 11, 2011 9:38:41 AM 
 *  
 */
@SuppressWarnings("rawtypes")
public class Pagination {
	public static final String SORT_DESC = "descending";
	public static final String SORT_ASC = "ascending";

	private int pageNumber;
	private int pageSize = Integer.parseInt(SysPropertiesUtil
			.getProperty("paging.size") == null ? "10" : SysPropertiesUtil
			.getProperty("paging.size"));
	private int firstResult;
	public int totalCount;
	
	public List list;
	public List listOfObject;
	
	private String sortCriterion;
	private String sortType;

	protected Pagination() {

	}
	
	/**
	 * 分页对象构造器
	 * 
	 * @param pageNumb  页数
	 * @param pageSize  每页记录数
	 */
	public Pagination(int pageNumb,int pageSize ){
		setPageNumber(pageNumb);
		setPageSize(pageSize);
	}

	/**
	 * 分页构造器，从HttpServletRequest获取页数、每页记录数等信息
	 * 
	 * @param request
	 */
	public Pagination(HttpServletRequest request) {
		init(request);
	}

	private void init(HttpServletRequest request) {
		String page = request.getParameter("page");
		if (StringUtils.isEmpty(page) || !StringUtils.isNumeric(page)) {
			page = "1";
		}
		setPageNumber(Integer.parseInt(page));
		String sort = request.getParameter("order");
		setSortType(sort);
		
		String orderBy = request.getParameter("sort");
		if (StringUtils.isNotEmpty(orderBy)) {
			setSortCriterion(orderBy);
		}
		Integer pageSize1 = (Integer)request.getAttribute("rows");
		if(pageSize1!=null)
			setPageSize(pageSize1);
		else{
			String pagesizeStr = request.getParameter("rows");
			if (pagesizeStr != null && !"".equals(pagesizeStr)) {
				if (StringUtils.isNumeric(pagesizeStr)) {
					setPageSize(Integer.parseInt(pagesizeStr));
				} else {
					setPageSize(Integer.MAX_VALUE); // ALL
				}
			}
		}
		
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getFirstResult() {
		if (pageNumber > 0) {
			return (pageNumber - 1) * pageSize;
		}
		return firstResult;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public String getSortType() {
		return sortType;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getList() {
		return list;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public int getObjectsPerPage() {
		return this.pageSize;
	}

	public int getFullListSize() {
		return this.totalCount;
	}

	public String getSortCriterion() {
		return this.sortCriterion;
	}

	public List getListOfObject() {
		return list;
	}


}
