package com.jshx.log.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.log.entity.UserBehavior;
import com.jshx.log.service.UserBehaviorService;

public class UserBehaviorAction extends BaseAction
{

	private static final long serialVersionUID = -1784242580169971791L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private UserBehavior userBehavior = new UserBehavior();

	/**
	 * 业务类
	 */
	@Autowired
	private UserBehaviorService userBehaviorService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	/**
	 * 用户行为列表查询，返回json数据：<br>
	 * {"total":1,"rows":[{"behaviorName":"","behaviorService":"","behaviorType":"","behaviorUrl":"","defaultLog":"","id":"","isContinue":""}]}
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != userBehavior){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != userBehavior.getBehaviorType()) && (0 < userBehavior.getBehaviorType().trim().length())){
				paraMap.put("behaviorType", userBehavior.getBehaviorType().trim());
			}

			if ((null != userBehavior.getBehaviorName()) && (0 < userBehavior.getBehaviorName().trim().length())){
				paraMap.put("behaviorName", "%" + userBehavior.getBehaviorName().trim() + "%");
			}

			if ((null != userBehavior.getBehaviorUrl()) && (0 < userBehavior.getBehaviorUrl().trim().length())){
				paraMap.put("behaviorUrl", "%" + userBehavior.getBehaviorUrl().trim() + "%");
			}

			if ((null != userBehavior.getIsContinue()) && (0 < userBehavior.getIsContinue().trim().length())){
				paraMap.put("isContinue", userBehavior.getIsContinue().trim());
			}

		}
		
		pagination = userBehaviorService.findByPage(pagination, paraMap);
		final String filter = "behaviorType|isContinue|id|behaviorName|behaviorUrl|behaviorService|defaultLog|";
		JsonConfig config = new JsonConfig();
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("behaviorType", "40288b813c5cd3a6013c5cdd681f0007");
		codeMap.put("isContinue", "402809812e7f8c28012e7fa82239000c");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		config.setJsonPropertyFilter(new PropertyFilter(){
			public boolean apply(Object source, String name, Object value) { 
				if(filter.indexOf(name+"|")!=-1)
					return false;
				else
					return true;
			}
		});
		convObjectToJson(pagination,config);
	}

	/**
	 * 返回查看用户行为页面
	 */
	public String view() throws Exception{
		if((null != userBehavior)&&(null != userBehavior.getId()))
			userBehavior = userBehaviorService.getById(userBehavior.getId());
		
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
	 * 用户行为树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	@SuppressWarnings("unchecked")
	public void findAllUserBehaviors(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		pagination = userBehaviorService.findByPage(pagination, paraMap);
		List<UserBehavior> list = pagination.list;
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", "");
		root.put("text", "用户行为");
		root.put("state", "opened");
		List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
		for(UserBehavior behavior : list){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id",behavior.getId());
			item.put("text", behavior.getBehaviorName());
			elements.add(item);
		}
		root.put("children", elements);
		JSONArray json = JSONArray.fromObject(root);
		HttpServletResponse response = ServletActionContext
				.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try{
			response.getWriter().println(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			userBehavior.setDeptId(this.getLoginUserDepartmentId());
			userBehavior.setDelFlag(0);
			userBehaviorService.save(userBehavior);
		}else{
			userBehaviorService.update(userBehavior);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			userBehaviorService.deleteWithFlag(ids);
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

	public UserBehavior getUserBehavior(){
		return this.userBehavior;
	}

	public void setUserBehavior(UserBehavior userBehavior){
		this.userBehavior = userBehavior;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
