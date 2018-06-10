/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-14        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserLinkedDept;
import com.jshx.module.admin.extend.IDeptExtendInfo;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserLinkedDeptService;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-14 上午11:31:38 部门管理控制器
 */
public class DeptAction extends BaseAction {

	private static final long serialVersionUID = 680980886020753009L;

	@Autowired() 
	@Qualifier("deptService")
	private DeptService deptService;
	
	@Autowired() 
	@Qualifier("userLinkedDeptService")
	private UserLinkedDeptService userLinkedDeptService;

	private Department dept;

	private Pagination pagination;

	private String deptCode;

	private Float expiration;

	private Boolean needApproval;

	private String selDept;
	
	private String[] ids;
	
	private String deptId;
	
	private List<Department> deptList;
	
	private Map<String, Department> deptMap;
	
	private String func;
	
	private String parentDeptCode;
	
	private String deptName;
	
	private String newDeptCode;
	
	/**
	 * 默认获得所有省份
	 */
	public void getProvinces(){
		List<Department> depts = deptService.getProvinces();
		JSONArray json = JSONArray.fromObject(depts);
		try {
			getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据parentid获得对应的下级地市，返回json数据
	 */
	public void findChildrenByParentId() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(null != dept){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != dept.getId()){
				paraMap.put("parentDeptCode", dept.getId());
			}

		}
		List<Department> depts = deptService.findChildrenByParentId(paraMap);
		JSONArray json = JSONArray.fromObject(depts);
		try {
			getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看部门信息
	 * 
	 * @return String
	 */
	public String viewDept() {
		if (dept != null && dept.getId() != null) {
			dept = deptService.findDeptById(dept.getId());
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.DEPT_NULL_EXCEPTION);
			throw ex;
		}
		return VIEW;
	}

	/**
	 * 创建/修改部门
	 * 
	 * @return String
	 */
	public String editDept() {
		if (dept != null && dept.getId() != null) {//修改部门
			dept = deptService.findDeptById(dept.getId());
			if(null!=dept.getParentDept()){
				this.deptCode = dept.getParentDept().getDeptCode();
				this.deptName = dept.getParentDept().getDeptName();
			}
		}else{//新增部门
			Department department = null;
			if(deptCode.trim().equals("")){
				if(this.getLoginUser().getIsSuperAdmin())
					department = deptService.findDeptByDeptCode(this.deptCode);
				else
					department = (Department)getLoginUser().getDept();
			}else
				department = deptService.findDeptByDeptCode(this.deptCode);
			
			if(null!=department){
				this.deptName = department.getDeptName();
			}
			this.newDeptCode = deptService.createDeptCode(this.deptCode);
		}
		
		return EDIT;
	}

	/**
	 * 保存部门信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	public void saveDept() throws IOException {
		if(dept!=null){
			try {
				//做上级部门判断，不能设置为自己和自己的下级部门，会引起嵌套
				/*Department currentDept = deptService.findDeptById(this.dept.getId());
				if(null!=currentDept){
					String selfDeptCode = currentDept.getDeptCode();
					String toSetParentDeptCode = this.deptCode;
					if(toSetParentDeptCode.indexOf(selfDeptCode)==0){
						getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"上级部门设置不正确，不能设置为自己和自己的下级部门，会引起嵌套！\"}");	
						return;
					}
				}*/
				
				Department parentDept = deptService.findDeptByDeptCode(deptCode);
				dept.setParentDept(parentDept);
				dept.setParentDeptCode(parentDept.getId());
				IDeptExtendInfo deptExtendInfo = (IDeptExtendInfo)createExtendInfo();
				dept.setDeptExtendInfo(deptExtendInfo);
				if (dept.getId() == null)
					deptService.save(dept);
				else
					deptService.modify(dept);	
				
				getResponse().getWriter().println("{\"status\":\"y\",\"info\":\""+dept.getId()+"\"}");	
			} catch (Exception e) {
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"部门保存失败！\"}");	
				e.printStackTrace();
			}	
		} else {
			try {
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"没有指定需要保存的部门！\"}");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 检测上层部门下有没有名称相同的部门<br>
	 * 返回json字符串：{"status":"","info":""}
	 * 
	 */
	
	public void checkDept(){
		Integer retFlag = 2;
		String deptName = this.getRequestParameter("param");
		if(null!=dept && null!=dept.getId() && !"".equals(dept.getId())){
			Department department = deptService.findDeptById(dept.getId());
			if(null==department || !department.getDeptCode().equals(deptCode) || !department.getDeptName().equals(deptName)){
				retFlag = deptService.checkDept(parentDeptCode, deptName,deptCode);
				//修改当前部门名，只要保证不重名即可
				if(0!=retFlag)
					retFlag = 2;
			}
		}else{
			retFlag = deptService.checkDept(parentDeptCode, deptName,deptCode);
		}
		
		try{
			if(0==retFlag){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"部门名重复！\"}");
			}else if(1==retFlag){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"部门编号重复！\"}");
			}else if(2==retFlag){
				getResponse().getWriter().println("{\"status\":\"y\"}");
			}
		}catch(Exception e){
			e.printStackTrace();
		}				
	}
	/**
	 * 激活禁用部门
	 * 
	 * @return String
	 */
	public void activeDept() {
		if (dept != null && dept.getId() != null) {
			dept = deptService.activeDept(dept.getId());
			try {
				getResponse().getWriter().print("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.DEPT_NULL_EXCEPTION);
			try {
				getResponse().getWriter().print("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
			throw ex;
		}
	}

	/**
	 * 禁用部门
	 * 
	 * @return String
	 */
	public void inactiveDept() {
		if (dept != null && dept.getId() != null) {
			dept = deptService.inactiveDept(dept.getId());
			try {
				getResponse().getWriter().print("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.DEPT_NULL_EXCEPTION);
			try {
				getResponse().getWriter().print("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
			throw ex;
		}
	}
	
	/**
	 * 物理删除部门，返回json字符串：{"result":"true|false"}
	 */
	public void delDept() {
		if(ids!=null && ids.length!=0){
			for(String item : ids){
				deptService.delDept(item);
			}
			try{
				getResponse().getWriter().println("{\"result\":true}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":false}");
			}catch(Exception e){
			}
		}
	}
	
	/**
	 * 逻辑删除部门，返回json字符串：{"result":"true|false"}
	 */
	public void logicDelDept(){
		if (dept != null && dept.getId() != null) {
			dept = deptService.findDeptById(dept.getId());
			deptService.logicDelDept(dept.getId());
			try {
				getResponse().getWriter().print("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.DEPT_NULL_EXCEPTION);
			try {
				getResponse().getWriter().print("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
			throw ex;
		}
	}
	
	/**
	 * 执行部门查询，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"delFlag":0,"deptCode":"","deptName":"","deptTypeCode":"","id":"","linkedDeptTypeCode":"","parentDept":{"delFlag":0,"deptCode":"","deptName":"","deptTypeCode":"","id":"","linkedDeptTypeCode":"","parentDept":{"delFlag":0,"deptCode":"","deptName":"","deptTypeCode":"","id":"","linkedDeptTypeCode":"","parentDept":null,"sortSQ":2},"sortSQ":1},"sortSQ":2}]}
	 */
	public void listDept() {
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
				
		if (dept.getDeptCode() != null && !dept.getDeptCode().equals("")) {
			paraMap.put("parentDeptCode", dept.getDeptCode().trim() + "%");
			paraMap.put("length", dept.getDeptCode().trim().length() + 3);
		}else{
			User user = (User) getLoginUser();
			if(!user.getIsSuperAdmin()){
				paraMap.put("parentDeptCode", user.getDeptCode().trim() + "%");
				paraMap.put("length", user.getDeptCode().trim().length() + 3);
			}
		}
		if (dept.getDelFlag() == null || dept.getDelFlag() == 0) {
			paraMap.put("delFlag", 0);
		}
		if (dept.getDeptName() != null) {
			paraMap.put("deptName", "%" + dept.getDeptName().trim() + "%");
		}
		
		pagination = deptService.findDeptByPage(pagination, paraMap);
		
		JsonConfig config = new JsonConfig();
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("deptTypeCode","34ed7d8e2dbac147012dbac4f9060001");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|deptName|deptCode|sortSQ|delFlag|deptTypeCode|linkedDeptTypeCode|parentDept|";
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
	 *返回部门列表页面
	 */
	public String list() {
		return LIST;
	}
	
	/**
	 * 部门树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 * 
	 * @throws Exception
	 */
	public void findChildDeptByCurrUser() throws Exception{
		User user = (User) getLoginUser();
		if (null == selDept) {
			// 初始化部门树
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			
			if (user.getIsSuperAdmin()) {
				root.put("id", "");
				root.put("text", "组织机构");
				root.put("state", "opened");
				List<Department> deptList = deptService.findDeptByParentDeptCode("");
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				for (Department d : deptList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", d.getDeptCode());
					item.put("text", d.getDeptName());
					if (deptService.findDeptByParentDeptCode(
							d.getDeptCode()).size() > 0|| deptService.findLinkedDpet(d).size()>0) {
						item.put("state", "closed");
					}
					elements.add(item);
				}
				root.put("children", elements);
				items.add(root);
			} else {
				root.put("id", "");
				root.put("text", "组织机构");
				root.put("state", "opened");
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				
				Department independenceDept = deptService
						.findDeptByDeptCode(user.getDeptCode());
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", independenceDept.getDeptCode());
				item.put("text", independenceDept.getDeptName());
				if (deptService.findDeptByParentDeptCode(
						independenceDept.getDeptCode()).size() > 0|| deptService.findLinkedDpet(independenceDept).size()>0) {
					item.put("state", "closed");
				}
				elements.add(item);
				List<UserLinkedDept> linkedDeptList = userLinkedDeptService.getLinkedDeptByUser(user.getId(), "%");
				if(linkedDeptList!=null && linkedDeptList.size()>0){
					for(UserLinkedDept linkedDept : linkedDeptList){
						item = new HashMap<String, Object>();
						item.put("id", linkedDept.getLinkedDept().getDeptCode());
						item.put("text", linkedDept.getLinkedDept().getDeptName());
						if (deptService.findDeptByParentDeptCode(
								linkedDept.getLinkedDept().getDeptCode()).size() > 0|| deptService.findLinkedDpet(linkedDept.getLinkedDept()).size()>0) {
							item.put("state", "closed");
						}
						elements.add(item);
					}
				}
				/**
				List<Department> deptList = deptService
						.findDeptByParentDeptCode(independenceDept
								.getDeptCode());
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				if(deptList.size()>0){
					for (Department d : deptList) {
						Map<String, Object> item = new HashMap<String, Object>();
						item.put("id", d.getDeptCode());
						item.put("text", d.getDeptName());
						if (deptService.findDeptByParentDeptCode(
								d.getDeptCode()).size() > 0|| deptService.findLinkedDpet(d).size()>0) {
							item.put("state", "closed");
						}
						elements.add(item);
					}
				}else{
					List<Department>linkedDeptList = deptService.findLinkedDpet(independenceDept);
					if(linkedDeptList.size()>0){
						for (Department d : linkedDeptList) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("id", d.getDeptCode());
							item.put("text", d.getDeptName());
							if (deptService.findDeptByParentDeptCode(
									d.getDeptCode()).size() > 0|| deptService.findLinkedDpet(d).size()>0) {
								item.put("state", "closed");
							}
							elements.add(item);
						}
					}
				}
				*/
				root.put("children", elements);
				items.add(root);
			}
			JSONArray json = JSONArray.fromObject(items);
			HttpServletResponse response = ServletActionContext
					.getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Charset", "utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(json.toString());
		} else {
			deptList = deptService
					.findDeptByParentDeptCode(selDept);
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			for (Department d : deptList) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", d.getDeptCode());
				item.put("text", d.getDeptName());
				if (deptService.findDeptByParentDeptCode(d.getDeptCode())
						.size() > 0 || deptService.findLinkedDpet(d).size()>0) {
					item.put("state", "closed");
				}
				items.add(item);
			}
			dept = deptService.findDeptByDeptCode(selDept);
			deptList = deptService.findLinkedDpet(dept);
			for (Department d : deptList) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", d.getDeptCode());
				StringBuffer name = new StringBuffer("<font color='red'>");
				if(d.getParentDept()!=null){
					name.append(d.getParentDept().getDeptName()).append("-");
				}
				name.append(d.getDeptName()).append("</font>");
				item.put("text", name.toString());
				if (deptService.findDeptByParentDeptCode(d.getDeptCode())
						.size() > 0 || deptService.findLinkedDpet(d).size()>0) {
					item.put("state", "closed");
				}
				items.add(item);
			}
			JSONArray json = JSONArray.fromObject(items);
			HttpServletResponse response = ServletActionContext
					.getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Charset", "utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(json.toString());
		}
	}

	/**
	 * 所有的组织机构的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 * 
	 */
	public void findChildDept() {
		try {
			if (null == selDept) {
				// 初始化部门树
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				Map<String, Object> root = new HashMap<String, Object>();
				
				root.put("id", "");
				root.put("text", "组织机构");
				root.put("state", "opened");
				List<Department> deptList = deptService.findDeptByParentDeptCode("");
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				for (Department d : deptList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", d.getDeptCode());
					item.put("text", d.getDeptName());
					List<Department> childList = deptService.findDeptByParentDeptCode(d.getDeptCode());
					if (childList!=null && childList.size() > 0|| deptService.findLinkedDpet(d).size()>0) {
						item.put("state", "closed");
					}
					elements.add(item);
				}
				root.put("children", elements);
				items.add(root);
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(json.toString());
			} else {
				deptList = deptService.findDeptByParentDeptCode(selDept);
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				for (Department d : deptList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", d.getDeptCode());
					item.put("text", d.getDeptName());
					if (deptService.findDeptByParentDeptCode(d.getDeptCode()).size() > 0 || deptService.findLinkedDpet(d).size()>0) {
						item.put("state", "closed");
					}
					items.add(item);
				}
				dept = deptService.findDeptByDeptCode(selDept);
				deptList = deptService.findLinkedDpet(dept);
				for (Department d : deptList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", d.getDeptCode());
					StringBuffer name = new StringBuffer("<font color='red'>");
					if(d.getParentDept()!=null){
						name.append(d.getParentDept().getDeptName()).append("-");
					}
					name.append(d.getDeptName()).append("</font>");
					item.put("text", name.toString());
					if (deptService.findDeptByParentDeptCode(d.getDeptCode()).size() > 0 || deptService.findLinkedDpet(d).size()>0) {
						item.put("state", "closed");
					}
					items.add(item);
				}
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回部门首页
	 */
	public String index() {
		return SUCCESS;
	}
	/**
	 * 返回部门设置页面
	 */
	public String deptTree() {
		if(func!=null && func.equals("setParentDept"))
			return "setParent";
		else
			return SUCCESS;
	}
	
	
	/**
	 * 返回设置组织机构的关联组织机构页面
	 * 
	 * @return String
	 */
	public String setLinkedDept(){
		deptList = deptService.findLinkedDpet(dept);		
		if(deptList!=null && deptList.size()>0){
			deptMap = new HashMap<String, Department>();
			for(Department dept : deptList){
				deptMap.put(dept.getId(), dept);
			}
		}
		deptList = deptService.findDeptByTpye(dept.getLinkedDeptTypeCode());
		
		return SUCCESS;
	}
	
	/**
	 * 保存组织机构的关联组织机构
	 * 
	 * @return String
	 */
	public String saveLinkedDept(){
		deptService.saveLinkedDept(dept.getId(), ids);
		return SUCCESS;
	}
	

	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the expiration
	 */
	public Float getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration
	 *            the expiration to set
	 */
	public void setExpiration(Float expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the needApproval
	 */
	public Boolean getNeedApproval() {
		return needApproval;
	}

	/**
	 * @param needApproval
	 *            the needApproval to set
	 */
	public void setNeedApproval(Boolean needApproval) {
		this.needApproval = needApproval;
	}

	public String getSelDept() {
		return selDept;
	}

	public void setSelDept(String selDept) {
		this.selDept = selDept;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public Map<String, Department> getDeptMap() {
		return deptMap;
	}

	public void setDeptMap(Map<String, Department> deptMap) {
		this.deptMap = deptMap;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getNewDeptCode() {
		return newDeptCode;
	}

	public void setNewDeptCode(String newDeptCode) {
		this.newDeptCode = newDeptCode;
	}
}
