package com.jshx.gridManager.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gridManager.entity.BaseBean;
import com.jshx.gridManager.entity.CompanyMapBean;
import com.jshx.gridManager.entity.GridManager;
import com.jshx.gridManager.entity.GridmanagerBean;
import com.jshx.gridManager.service.GridManagerService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;

public class GridManagerAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private GridManager gridManager = new GridManager();
	
	private User user = new User();
	private  List<BaseBean> users = new ArrayList<BaseBean>();
	
	private List<GridmanagerBean> gridlist = new ArrayList<GridmanagerBean>();
	
	private List<Department> deptlist = new ArrayList<Department>();
	/**
	 * 针对有证地图对象数据列表
	 */
	private String mapBeans = "";
	/**
	 * 针对无证照企业地图对象数据
	 */
	private String mapNoBeans ="";
	

	/**
	 * 业务类
	 */
	@Autowired
	private GridManagerService gridManagerService;
	
	@Autowired
	 private UserService userService;
	 
	@Autowired
	 private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 绑定状态  1 当前绑定人  2 所有
	 */
	private String bind = "1";

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 部门code
	 */
	public String deptCode;
	/**
	 * 绑定的当前的用户id
	 */
	private String userId;
	
	private String szc;
	
	private String companyName;
	
	/**
	 * 初始化
	 */
	@SuppressWarnings("unchecked")
	public String initList(){
		String jump = "success";
		//根据登录人的不同角色跳转到不同的网格列表页面
		List<UserRight> list = this.getLoginUser().getUserRoles();
		userId = this.getLoginUserId();
		deptCode = this.getLoginUserDepartment().getDeptCode();
		Map map = new HashMap();
		map.put("deptCode",deptCode);
		String code = "";
		for(UserRight u:list){
			code += u.getRole().getRoleCode()+",";
		}
		if(code.contains("A31"))//分管领导,查看安监中队科长、副科长
		{
			map.put("flag", "7");
			jump = "town";
		}
		else if(code.contains("A21")|| code.contains("A22")){//安监中队科长,安监中队副科长 看到乡镇下的镇级安全责任人信息
			map.put("flag","1");//查询该乡镇下所有的中队长信息
			jump = "townDZ";
		}
		else if(code.contains("A10")|| code.contains("A09")){//安监中队办事员 中队长  有 镇级负责人 和 村级负责人 
			if(deptCode!=null&&deptCode.length()==12){//村级安全负责人
				map.put("flag","5");//查询自己的信息
				map.put("userId", this.getLoginUserId());
				bind="-1";//表示是村安全责任人登录
				jump  = "townFZR";
			}else{//镇级安全负责人
				if(code.contains("A09"))//安监中队中队长
				{
					map.put("flag","6");//查询自己所有的安监中队办事员信息
					jump = "townZR";
				}
				else
				{
					map.put("flag","2");//查询自己所有的村安全负责人信息
					jump = "townFZR";
					deptlist = deptService.findDeptByParentDeptCode(deptCode);
					Department d = deptService.findDeptByDeptCode(deptCode);
					d.setDeptName("镇区");
					deptlist.add(d);
				}
			}
		}
		users = gridManagerService.queryUsersByCode(map);
		return jump;
	}
	
	
	public String initListss(){
		return SUCCESS;
	}
	public String initLists(){
		Map map = new HashMap();
		map.put("deptCode", deptCode);
		List<BaseBean> fgldlist = gridManagerService.getFgldListByMap(map);
		for(BaseBean fgld:fgldlist)
		{
			GridmanagerBean fgldbean = new GridmanagerBean();
			fgldbean.setName(fgld.getName());
			map.put("fgldid", fgld.getId());
			List<BaseBean> zzrlist = gridManagerService.getZzrListByMap(map);
			List<GridmanagerBean> zzrgrid = new ArrayList<GridmanagerBean>();
			for(BaseBean zzr:zzrlist)
			{
				GridmanagerBean zzrbean = new GridmanagerBean();
				zzrbean.setName(zzr.getName());
				map.put("zrrid", zzr.getId());
				List<BaseBean> zddzlist = gridManagerService.getZddzListByMap(map);
				List<GridmanagerBean> zddzgrid = new ArrayList<GridmanagerBean>();
				for(BaseBean zddz:zddzlist)
				{
					GridmanagerBean zddzbean = new GridmanagerBean();
					zddzbean.setName(zddz.getName());
					map.put("zddzid", zddz.getId());
					List<BaseBean> zaqzrrlist = gridManagerService.getZaqzzrListByMap(map);
					List<GridmanagerBean> zaqzzrgrid = new ArrayList<GridmanagerBean>();
					for(BaseBean zaqzrr:zaqzrrlist)
					{
						GridmanagerBean zaqzrrbean = new GridmanagerBean();
						zaqzrrbean.setName(zaqzrr.getName());
						map.put("zaqzrrid", zaqzrr.getId());
						List<GridmanagerBean> caqzrrgrid = gridManagerService.getCaqzzrListByMap(map);
						zaqzrrbean.setList(caqzrrgrid);
						zaqzzrgrid.add(zaqzrrbean);
					}
					zddzbean.setList(zaqzzrgrid);
					zddzgrid.add(zddzbean);
				}
				zzrbean.setList(zddzgrid);
				zzrgrid.add(zzrbean);
			}
			fgldbean.setList(zzrgrid);
			gridlist.add(fgldbean);
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		paraMap.put("type", "total");//标示安监局看到的所有镇

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getUpUserid()) && (0 < gridManager.getUpUserid().trim().length())){
				paraMap.put("deptCode",gridManager.getUpUserid().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != gridManager)&&(null != gridManager.getId()))
			gridManager = gridManagerService.getById(gridManager.getId());
		
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
			gridManager.setDeptId(this.getLoginUserDepartmentId());
			gridManager.setDelFlag(0);
			gridManagerService.save(gridManager);
		}else{
			gridManagerService.update(gridManager);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String bindUser() throws Exception{
	    try{
			gridManagerService.bindUser(ids,userId,this.getLoginUserId(),flag);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 删除信息
	 */
	public String jcbindUser() throws Exception{
	    try{
			gridManagerService.jcbindUser(ids,userId,this.getLoginUserId(),flag);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 跳转到某个指定的乡镇列表界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bindFgld(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		map.put("deptCode", deptCode);
		map.put("flag", "7");
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 根据乡镇的code查询该乡镇下所有镇主任管辖的企业列表
	 * @return
	 */
	public void bindFgldQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		
		paraMap.put("type", "fgld");//标示看到的某个镇的企业信息
		paraMap.put("deptCode",deptCode);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 跳转到某个指定的乡镇列表界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String townView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		map.put("deptCode", deptCode);
		map.put("flag", "0");
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 根据乡镇的code查询该乡镇下所有镇主任管辖的企业列表
	 * @return
	 */
	public void townQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		if(userId==null||"".equals(userId)){//针对不同角色查询
			userId = this.getLoginUserId();
		}
		paraMap.put("type", "town");//标示看到的某个镇的企业信息
		paraMap.put("deptCode",deptCode);
		paraMap.put("bind", bind);
		//
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 跳转到绑定镇级安全负责人的列表界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bindTownDz(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		map.put("deptCode",deptCode);
		map.put("flag","1");
		
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 查询该镇下所有的镇级安全负责人
	 * @return
	 */
	public void bindTownDzQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		if(userId==null||"".equals(userId)){//针对不同角色查询
			userId = this.getLoginUserId();
		}
		paraMap.put("type", "townDz");//标示看到的某个镇的企业信息
		paraMap.put("deptCode",deptCode);
		paraMap.put("bind", bind);
		//
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 跳转到绑定镇级安全负责人的列表界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bindTownUser(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		map.put("deptCode",deptCode);
		map.put("flag","6");
		
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 查询该镇下所有的镇级安全负责人
	 * @return
	 */
	public void bindTownUserQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		if(userId==null||"".equals(userId)){//针对不同角色查询
			userId = this.getLoginUserId();
		}
		paraMap.put("type", "townUser");//标示看到的某个镇的企业信息
		paraMap.put("deptCode",deptCode);
		paraMap.put("bind", bind);
		//
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 跳转到村级安全责任人界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bindCountryUser(){
		Map map = new HashMap();
		map.put("deptCode",deptCode);
		map.put("flag","2");
		
		users = gridManagerService.queryUsersByCode(map);
		deptlist = deptService.findDeptByParentDeptCode(deptCode);
		Department d = deptService.findDeptByDeptCode(deptCode);
		d.setDeptName("镇区");
		deptlist.add(d);
		return SUCCESS;
	}
	/**
	 * 查询村级安全责任人列表信息
	 * @return
	 */
	public void bindCountryUserQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserDepartment().getDeptCode();
		}
		if(userId==null||"".equals(userId)){//针对不同角色查询
			userId = this.getLoginUserId();
		}
		paraMap.put("type", "countryUser");//标示看到的某个镇的企业信息
		paraMap.put("deptCode",deptCode);
		if("-1".equals(bind)){
			paraMap.put("userId",userId);
		}
		paraMap.put("bind", bind);
		//
		paraMap.put("bindId", userId);
		paraMap.put("szc", szc);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
		
	}
	/**
	 * 跳转到有证企业界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String companyUser(){
		Map map = new HashMap();
		User u = userService.findUserById(userId);
		if(u!=null){
			map.put("deptCode",u.getDeptCode());
		}
		map.put("flag","3");
		
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 查询有证企业的查询界面
	 * @return
	 */
	public void bindCompanyUserQuery(){
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("type", "company");//标示看到的某个镇的企业信息
		//paraMap.put("deptCode",deptCode);

		paraMap.put("bind", bind);
		User u = userService.findUserById(userId);
		if(u!=null){
			paraMap.put("deptCode",u.getDeptCode());
		}
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 绑定企业和村责任人
	 * @return
	 * @throws IOException 
	 */
	public String bindCompanyUser() throws IOException {
		  try{
				gridManagerService.bindCompanyUser(ids,userId,this.getLoginUserId());
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				this.getResponse().getWriter().println("{\"result\":\"false\"}");
			}
			return null;
	}
	
	public String jcbindCompanyUser() throws IOException {
		  try{
				gridManagerService.jcbindCompanyUser(ids,userId,this.getLoginUserId());
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				this.getResponse().getWriter().println("{\"result\":\"false\"}");
			}
			return null;
	}
	/**
	 * 跳转到无证企业界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String noCompanyUser(){
		Map map = new HashMap();
		User u = userService.findUserById(userId);
		if(u!=null){
			map.put("deptCode",u.getDeptCode());
		}
		map.put("flag","4");
		
		users = gridManagerService.queryUsersByCode(map);
		return SUCCESS;
	}
	/**
	 * 查询无证企业列表
	 * @return
	 */
	public void  bindNoCompanyUserQuery(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("type", "noCompany");//标示看到的某个镇的企业信息
		//paraMap.put("deptCode",deptCode);

		paraMap.put("bind", bind);
		User u = userService.findUserById(userId);
		if(u!=null){
			paraMap.put("deptCode",u.getDeptCode());
		}
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gridManager){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gridManager.getId()) && (0 < gridManager.getId().trim().length())){
				paraMap.put("userId",gridManager.getId().trim());
			}

		}
		//查询乡镇列表和企业总数
		pagination = gridManagerService.findByPage(pagination, paraMap);
		
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 绑定无证照企业和村责任人
	 * @return
	 * @throws IOException 
	 */
	public String bindNoCompanyUser() throws IOException {
		  try{
				gridManagerService.bindNoCompanyUser(ids,userId,this.getLoginUserId());
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				this.getResponse().getWriter().println("{\"result\":\"false\"}");
			}
			return null;
	}
	
	public String jcbindNoCompanyUser() throws IOException {
		  try{
				gridManagerService.jcbindNoCompanyUser(ids,userId,this.getLoginUserId());
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				this.getResponse().getWriter().println("{\"result\":\"false\"}");
			}
			return null;
	}
	/**
	 * 查询所有镇级 下所有的企业 并标示到地图上
	 * @return
	 */
	public String mapView(){
		Map map = new HashMap();
		map.put("deptCode", deptCode);
		map.put("type", "all");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	/**
	 * 查询某个镇级 某个分管领导 下所有的企业 并标示到地图上
	 * @return
	 */
	public String mapFgldView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "fgld");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	/**
	 * 查询某个镇级 某个主任 下所有的企业 并标示到地图上
	 * @return
	 */
	public String mapZRView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "zr");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	
	/**
	 * 查询某个镇级 某个主任 下 某个安全员 所有的企业 并标示到地图上
	 * @return
	 */
	public String mapDzView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "towndz");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	/**
	 * 查询某个镇级 某个主任 下 某个安全员 所有的企业 并标示到地图上
	 * @return
	 */
	public String mapAQYView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "townaqy");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	/**
	 * 查询某个镇级 某个主任 下 某个安全员 下的村安全员  所有的企业 并标示到地图上
	 * @return
	 */
	public String mapCountryAQYView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "countryaqy");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	/**
	 * 查询某个镇级 某个主任 下 某个安全员 下的村安全员下 某个企业 并标示到地图上
	 * @return
	 */
	public String mapCompanyView(){
		Map map = new HashMap();
		if(deptCode==null||"".equals(deptCode)){//针对不同角色查询
			deptCode = this.getLoginUserId();
		}
		map.put("userId", deptCode);
		map.put("type", "company");
		List<CompanyMapBean>  beans = this.gridManagerService.getCompamyMapDotsByMap(map);
		List<CompanyMapBean>  noBeans = this.gridManagerService.getNoCompamyMapDotsByMap(map);
		if(beans!=null&&!beans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(beans);
			mapBeans = ja.toString();
		}else{
			flag = "1";//表示没有有证照企业标记点
		}
		if(noBeans!=null&&!noBeans.isEmpty()){
			JSONArray ja = JSONArray.fromObject(noBeans);
			mapNoBeans = ja.toString();
		}else{
			flag = "2";//表示没有无证照企业标记点
		}
		if((beans==null||beans.isEmpty())&&(noBeans==null||noBeans.isEmpty())){
			flag = "3";//表示没有标记点
		}
		return SUCCESS;
	}
	
	public String yzcompany()
	{
		return SUCCESS;
	}
	
	public void yzcompanyQuery()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("bindId", userId);
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		//查询乡镇列表和企业总数
		
		    //设置查询条件，开发人员可以在此增加过滤条件
		if ((null != companyName) && (0 < companyName.trim().length())){
			paraMap.put("yzcompanyname","%" + companyName.trim() + "%");
		}

		pagination = gridManagerService.findYzCompanyByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	public String wzcompany()
	{
		return SUCCESS;
	}
	
	public void wzcompanyQuery()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("bindId", userId);
		
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		if ((null != companyName) && (0 < companyName.trim().length())){
			paraMap.put("wzcompanyname","%" + companyName.trim() + "%");
		}

		//查询乡镇列表和企业总数
		pagination = gridManagerService.findWzCompanyByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	
	public String gxfw()
	{
		if(user != null && user.getId() != null)
		{
			user = userService.findUserById(user.getId());
		}
		return SUCCESS;
	}
	
	public String gxfwSave()
	{
		User u = userService.findUserById(user.getId());
		u.setGxfw(user.getGxfw());
		userService.modify(u, null);
		return RELOAD;
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

	public GridManager getGridManager(){
		return this.gridManager;
	}

	public void setGridManager(GridManager gridManager){
		this.gridManager = gridManager;
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

	public List<BaseBean> getUsers() {
		return users;
	}

	public void setUsers(List<BaseBean> users) {
		this.users = users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getMapBeans() {
		return mapBeans;
	}

	public void setMapBeans(String mapBeans) {
		this.mapBeans = mapBeans;
	}

	public String getMapNoBeans() {
		return mapNoBeans;
	}

	public void setMapNoBeans(String mapNoBeans) {
		this.mapNoBeans = mapNoBeans;
	}

	public List<GridmanagerBean> getGridlist() {
		return gridlist;
	}

	public void setGridlist(List<GridmanagerBean> gridlist) {
		this.gridlist = gridlist;
	}


	public String getSzc() {
		return szc;
	}


	public void setSzc(String szc) {
		this.szc = szc;
	}


	public List<Department> getDeptlist() {
		return deptlist;
	}


	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
