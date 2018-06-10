package com.wzxx.xyjc.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.wzxx.xyjc.entity.Xyjc;
import com.wzxx.xyjc.service.XyjcService;

public class XyjcAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Xyjc xyjc = new Xyjc();
	
	/**
	 * 业务类
	 */
	@Autowired
	private XyjcService xyjcService;
	
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private Date queryPublicDateStart;

	private Date queryPublicDateEnd;
	
	/**
	 * 执行查询的方法，返回json数据<br>
	 * json包含的属性："infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|"
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != xyjc){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != xyjc.getInfoTitle()) && (0 < xyjc.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + xyjc.getInfoTitle().trim() + "%");
			}
			
			if ((null != xyjc.getMail()) && (0 < xyjc.getMail().trim().length())){
				paraMap.put("mail", "%" + xyjc.getMail().trim() + "%");
			}
			if ((null != xyjc.getName()) && (0 < xyjc.getName().trim().length())){
				paraMap.put("name", "%" + xyjc.getName().trim() + "%");
			}
			if (null != queryPublicDateStart){
				paraMap.put("startPublicDate", queryPublicDateStart);
			}

			if (null != queryPublicDateEnd){
				paraMap.put("endPublicDate", queryPublicDateEnd);
			}
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "infoTitle|id|mail|name|createTime|";
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
		
		pagination = xyjcService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
		
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != xyjc)&&(null != xyjc.getId()))
		{
			xyjc = xyjcService.getById(xyjc.getId());
		}
		return VIEW;
	}


	/**
	 * 删除信息,返回json{result:true/false}
	 */
	public String delete() throws Exception{
	    try{
	    	xyjcService.deleteWithFlag(ids);
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

	public Xyjc getXyjc(){
		return this.xyjc;
	}

	public void setXyjc(Xyjc xyjc){
		this.xyjc = xyjc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public Date getQueryPublicDateStart() {
		return queryPublicDateStart;
	}

	public void setQueryPublicDateStart(Date queryPublicDateStart) {
		this.queryPublicDateStart = queryPublicDateStart;
	}

	public Date getQueryPublicDateEnd() {
		return queryPublicDateEnd;
	}

	public void setQueryPublicDateEnd(Date queryPublicDateEnd) {
		this.queryPublicDateEnd = queryPublicDateEnd;
	}
}
