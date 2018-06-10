/**
 * Class Name: UserTerminalAction
 * Class Description：终端管理
 */
package com.jshx.terminal.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.terminal.entity.UserTerminal;
import com.jshx.terminal.service.UserTerminalService;

public class UserTerminalAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private UserTerminal userTerminal = new UserTerminal();

	/**
	 * 业务类
	 */
	@Autowired
	private UserTerminalService userTerminalService;
	
	/**
	 * 部门信息列表
	 */
	private List<Department> depts;

	public List<Department> getDepts() {
		return depts;
	}

	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	/**
	 *用户信息列表
	 */
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	public String initList(){
	    return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public String list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != userTerminal){
		    //设置查询条件，开发人员可以在此增加过滤条件
			paraMap.put("telephone",userTerminal.getTelephone());
			
			String terminalName = userTerminal.getTerminalName();
			
			if ((null != terminalName) && (0 < terminalName.trim().length()))
			{

				paraMap.put("terminalName", "%" + terminalName.trim() + "%");
			}
		}
		
		pagination = userTerminalService.findByPage(pagination, paraMap);
		
		//将查询结果转为json数据给datagrid展现
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject());
		data.append(json.toString());
		data.append("  \n").append("}");
		
		getResponse().getWriter().println(data);
		return null;
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != userTerminal)&&(null != userTerminal.getId()))
			userTerminal = userTerminalService.getById(userTerminal.getId());
		//获取部门列表信息
		depts = userTerminalService.getDeptInfo(null);
		//获取用户列表信息
		users = userTerminalService.getUsersByDeptCode(userTerminal.getDeptId());
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
	 * 实现部门查询用户的级联操作
	 */
	public void queryUser() {
		try {
			String deptCode =getRequest().getParameter("deptCode");
			List<User> list = userTerminalService.getUsersByDeptCode(deptCode);
			JSONArray json = new JSONArray();
			for(User user :list){
				JSONObject jj = new JSONObject();
				jj.put("id", user.getId());
				jj.put("name", user.getDisplayName());
				json.add(jj);
			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}

		//return RESULT_JSON;
	}

	
	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		String deptId="";
		//通过请求获取部门编号  疑惑：如果不是如此页面获取的编号会累加 例 001， 002002……
		deptId= getRequest().getParameter("code");
		userTerminal.setDeptId(deptId);
		if ("add".equalsIgnoreCase(this.flag)){
			//保留的是添加终端用户的部门而不是登录人的部门
			userTerminal.setDelFlag(0);
			userTerminalService.save(userTerminal);
		}else{
			userTerminalService.update(userTerminal);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			userTerminalService.deleteWithFlag(ids);
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

	public UserTerminal getUserTerminal(){
		return this.userTerminal;
	}

	public void setUserTerminal(UserTerminal userTerminal){
		this.userTerminal = userTerminal;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
