package com.jshx.log.web;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.log.entity.DataLog;
import com.jshx.log.service.DataLogService;

public class DataLogAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6243412872020422817L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DataLog dataLog = new DataLog();

	/**
	 * 业务类
	 */
	@Autowired
	private DataLogService dataLogService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	/**
	 * 数据操作日志列表查询，返回json数据：<br>
	 * {"total":514,"rows":[{"createTime":"","entityName":"","id":"","opDuration":,"opType":"","recordNum":1,"updateTime":""}]}
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != dataLog){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dataLog.getEntityName()) && (0 < dataLog.getEntityName().trim().length())){
				paraMap.put("entityName", "%" + dataLog.getEntityName().trim() + "%");
			}

			if ((null != dataLog.getOpType()) && (0 < dataLog.getOpType().trim().length())){
				paraMap.put("opType", dataLog.getOpType().trim());
			}

		}
		
		pagination = dataLogService.findByPage(pagination, paraMap);
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("opType", "8a8180553c478187013c479015b5000e");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "entityName|id|opType|updateTime|opDuration|recordNum|createTime|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dataLog)&&(null != dataLog.getId()))
			dataLog = dataLogService.getById(dataLog.getId());
		
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
			dataLog.setDeptId(this.getLoginUserDepartmentId());
			dataLog.setDelFlag(0);
			dataLogService.save(dataLog);
		}else{
			dataLogService.update(dataLog);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			dataLogService.deleteWithFlag(ids);
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

	public DataLog getDataLog(){
		return this.dataLog;
	}

	public void setDataLog(DataLog dataLog){
		this.dataLog = dataLog;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
