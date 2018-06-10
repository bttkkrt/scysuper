package com.jshx.patrolUser.web;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.patrolUser.entity.PatrolUser;
import com.jshx.patrolUser.service.PatrolUserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class PatrolUserAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PatrolUser patrolUser = new PatrolUser();

	/**
	 * 业务类
	 */
	@Autowired
	private PatrolUserService patrolUserService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	public String init(){
		return SUCCESS;
	}
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private HttpService httpService;
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != patrolUser){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != patrolUser.getUserName()) && (0 < patrolUser.getUserName().trim().length())){
				paraMap.put("userName", "%" + patrolUser.getUserName().trim() + "%");
			}

			if ((null != patrolUser.getMobile()) && (0 < patrolUser.getMobile().trim().length())){
				paraMap.put("mobile", "%" + patrolUser.getMobile().trim() + "%");
			}

			if ((null != patrolUser.getLoginId()) && (0 < patrolUser.getLoginId().trim().length())){
				paraMap.put("loginId", "%" + patrolUser.getLoginId().trim() + "%");
			}

		}
		if(httpService.judgeRoleCode(this.getLoginUserId(), "A23")){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|userName|loginId|job|mobile|";
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
		pagination = patrolUserService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != patrolUser)&&(null != patrolUser.getId()))
			patrolUser = patrolUserService.getById(patrolUser.getId());
		
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
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
	
		if ("add".equalsIgnoreCase(this.flag)){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			patrolUser.setDeptId(this.getLoginUserDepartmentId());
			patrolUser.setDelFlag(0);
			patrolUser.setCompanyId(entBaseInfo.getId());
			patrolUserService.save(patrolUser);
		}else{
			patrolUserService.update(patrolUser);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != patrolUser)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到patrolUser中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			patrolUserService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	
	/**
	 * 检查登陆名不能能重复
	 */
	public String checkLoginId() throws Exception{
	    try{
	    	Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("sqlId", "queryCountForLogin");
			paraMap.put("loginId", ids);
			List<Map<String,Object>> list1=httpService.findListDataByMap(paraMap);
			paraMap.put("sqlId", "queryCountForLoginFromUsers");
			paraMap.put("loginId", ids);
			List<Map<String,Object>> list2=httpService.findListDataByMap(paraMap);
			if((list1.size()+list2.size())==0){
				this.getResponse().getWriter().println("{\"result\":true}");
			}else{
				this.getResponse().getWriter().println("{\"result\":false}");
			}
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
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

	public PatrolUser getPatrolUser(){
		return this.patrolUser;
	}

	public void setPatrolUser(PatrolUser patrolUser){
		this.patrolUser = patrolUser;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
