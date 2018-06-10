package com.jshx.reviewLog.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;

public class ReviewLogAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ReviewLog reviewLog = new ReviewLog();

	/**
	 * 业务类
	 */
	@Autowired
	private ReviewLogService reviewLogService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryStartTimeStart;

	private Date queryStartTimeEnd;

	private Date queryEndTimeStart;

	private Date queryEndTimeEnd;

	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != reviewLog){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != reviewLog.getItemId()) && (0 < reviewLog.getItemId().trim().length())){
				paraMap.put("itemId", "%" + reviewLog.getItemId().trim() + "%");
			}

			if ((null != reviewLog.getItemType()) && (0 < reviewLog.getItemType().trim().length())){
				paraMap.put("itemType", "%" + reviewLog.getItemType().trim() + "%");
			}

			if ((null != reviewLog.getState()) && (0 < reviewLog.getState().trim().length())){
				paraMap.put("state", "%" + reviewLog.getState().trim() + "%");
			}

			if ((null != reviewLog.getDutyFlag()) && (0 < reviewLog.getDutyFlag().trim().length())){
				paraMap.put("dutyFlag", "%" + reviewLog.getDutyFlag().trim() + "%");
			}

			if ((null != reviewLog.getUserId()) && (0 < reviewLog.getUserId().trim().length())){
				paraMap.put("userId", "%" + reviewLog.getUserId().trim() + "%");
			}

			if ((null != reviewLog.getUserName()) && (0 < reviewLog.getUserName().trim().length())){
				paraMap.put("userName", "%" + reviewLog.getUserName().trim() + "%");
			}

			if ((null != reviewLog.getUserDeptCode()) && (0 < reviewLog.getUserDeptCode().trim().length())){
				paraMap.put("userDeptCode", "%" + reviewLog.getUserDeptCode().trim() + "%");
			}

			if (null != queryStartTimeStart){
				paraMap.put("startStartTime", queryStartTimeStart);
			}

			if (null != queryStartTimeEnd){
				paraMap.put("endStartTime", queryStartTimeEnd);
			}
			if (null != queryEndTimeStart){
				paraMap.put("startEndTime", queryEndTimeStart);
			}

			if (null != queryEndTimeEnd){
				paraMap.put("endEndTime", queryEndTimeEnd);
			}
			if ((null != reviewLog.getRecord()) && (0 < reviewLog.getRecord().trim().length())){
				paraMap.put("record", "%" + reviewLog.getRecord().trim() + "%");
			}

		}
		
		pagination = reviewLogService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != reviewLog)&&(null != reviewLog.getId()))
			reviewLog = reviewLogService.getById(reviewLog.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			reviewLog.setDeptId(this.getLoginUserDepartmentId());
			reviewLog.setDelFlag(0);
			reviewLogService.save(reviewLog);
		}else{
			reviewLogService.update(reviewLog);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			reviewLogService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public ReviewLog getReviewLog(){
		return this.reviewLog;
	}

	public void setReviewLog(ReviewLog reviewLog){
		this.reviewLog = reviewLog;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryStartTimeStart(){
		return this.queryStartTimeStart;
	}

	public void setQueryStartTimeStart(Date queryStartTimeStart){
		this.queryStartTimeStart = queryStartTimeStart;
	}

	public Date getQueryStartTimeEnd(){
		return this.queryStartTimeEnd;
	}

	public void setQueryStartTimeEnd(Date queryStartTimeEnd){
		this.queryStartTimeEnd = queryStartTimeEnd;
	}

	public Date getQueryEndTimeStart(){
		return this.queryEndTimeStart;
	}

	public void setQueryEndTimeStart(Date queryEndTimeStart){
		this.queryEndTimeStart = queryEndTimeStart;
	}

	public Date getQueryEndTimeEnd(){
		return this.queryEndTimeEnd;
	}

	public void setQueryEndTimeEnd(Date queryEndTimeEnd){
		this.queryEndTimeEnd = queryEndTimeEnd;
	}

}
