package com.jshx.qywghjdgl.web;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.service.ComGriManService;

public class ComGriManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ComGriMan comGriMan = new ComGriMan();

	/**
	 * 业务类
	 */
	@Autowired
	private ComGriManService comGriManService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpService httpService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	private String deptCode;
	
	public String init(){
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != comGriMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != comGriMan.getGridName()) && (0 < comGriMan.getGridName().trim().length())){
				paraMap.put("gridName", "%" + comGriMan.getGridName().trim() + "%");
			}

			if ((null != comGriMan.getGridManageDeptName()) && (0 < comGriMan.getGridManageDeptName().trim().length())){
				paraMap.put("gridManageDeptName", "%" +comGriMan.getGridManageDeptName().trim()+ "%");
			}

			if ((null != comGriMan.getGridManagePersonName()) && (0 < comGriMan.getGridManagePersonName().trim().length())){
				paraMap.put("gridManagePersonName", "%" + comGriMan.getGridManagePersonName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		//codeMap.put("gridManageDept","select ");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|gridManageDeptName|gridManagePersonName|gridName|createUserID|";
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
		pagination = comGriManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != comGriMan)&&(null != comGriMan.getId()))
			comGriMan = comGriManService.getById(comGriMan.getId());
		
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
		Department dept = deptService.findDeptByDeptCode(comGriMan.getGridManageDept());
		comGriMan.setGridManageDeptName(dept.getDeptName());
		if ("add".equalsIgnoreCase(this.flag)){
			comGriMan.setDeptId(this.getLoginUserDepartmentId());
			comGriMan.setDelFlag(0);
			comGriManService.save(comGriMan);
		}else{
			comGriManService.update(comGriMan);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != comGriMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到comGriMan中去
				
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
			comGriManService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 根据部门选择网格人员
	 * 费谦
	 * 2015-10-28
	 */
	public String deptChange() throws Exception{
		
		try{
			Department d=deptService.findDeptByDeptCode(deptCode);
			List<User> users=d.getUsers();
			String select="<select id='gridManagePerson' name='comGriMan.gridManagePerson' style='width:50%;' datatype='*1-127' errormsg='网格管理人员必须是1到127位字符！' nullmsg='网格管理人员不能为空！' sucmsg='网格管理人员选择正确！' onchange='personChange();' ><option value='' >请选择</option>";
			for(User u:users){
				if(!httpService.judgeRoleCode(u.getId(), "A11")){
					select+="<option value='"+u.getId()+"' >"+u.getDisplayName()+"</option>";
				} 
			}
			select+="</select>";
			JSONObject json=new JSONObject();
			json.put("result", true);
			json.put("s", select);
			//this.getResponse().getWriter().println("{\"result\":true,\"s\":"+select+"}");
			this.getResponse().getWriter().println(json.toString());
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

	public ComGriMan getComGriMan(){
		return this.comGriMan;
	}

	public void setComGriMan(ComGriMan comGriMan){
		this.comGriMan = comGriMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
       
    
}
