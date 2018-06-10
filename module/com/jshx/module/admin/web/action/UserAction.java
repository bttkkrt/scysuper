/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserLinkedDept;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.extend.IUserExtendInfo;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserLinkedDeptService;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-17 下午02:51:57  
 * 类说明  
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -262450066779221597L;
	
	@Autowired() 
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired() 
	@Qualifier("deptService")
	private DeptService deptService;
	
	@Autowired() 
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired() 
	@Qualifier("userLinkedDeptService")
	private UserLinkedDeptService userLinkedDeptService;

	private User user;
	
    private Pagination pagination;
    
    private String newPassword;
    
    private String[] roleIds;
    
    private List<UserRole> roleList;
    
    private Boolean mobileUnique;
    
    private String errorInfo;
    
    private String userId;
    
    private String roleCodes;
    
    private String deptId;
    
    private File userFile;
    
    private String linkedDeptIds;
    
    private String selDept;
    
    private String message;
    
    private String deptCode;
    
    private String deptName;
    
    private String[] ids;
    
    private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
    
	/**
	 * 查看用户信息
	 * 
	 * @return String 
	 */
	public String viewUser(){
		try{
			if(user!=null && user.getId()!=null){
				user = userService.findUserById(user.getId());
			}else{
				BasalException ex = new BasalException(BasalException.NO, Constants.USER_NULL_EXCEPTION);
				throw ex;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return VIEW;
	}
	
	/**
	 * 添加/修改人员
	 * 
	 * @return String 
	 */
	public String editUser(){
		if(user!=null && user.getId()!=null){//修改用户
			user = userService.findUserById(user.getId());
			this.deptCode = user.getDept().getDeptCode();
			this.deptName = user.getDept().getDeptName();
		}else{//新增用户
			Department department = null;
			if(deptCode.trim().equals("")){
				if(this.getLoginUser().getIsSuperAdmin())
					department = deptService.findDeptByDeptCode(this.deptCode);
				else
					department = (Department)getLoginUser().getDept();
			}else
				department = deptService.findDeptByDeptCode(this.deptCode);
			if(null!=department){
				this.deptCode = department.getDeptCode();
				this.deptName = department.getDeptName();				
			}
		}
		
		//判断手机号是否需要唯一处理
		mobileUnique = Boolean.valueOf(SysPropertiesUtil.getProperty("MOBILE_NO_UNIQUE"));
		return EDIT;
	}
	
	/**
	 * 保存人员信息
	 * 
	 * @return String  
	 */
	public void saveUser(){
		if(user!=null ){
			if(null==user.getDeptCode() || "".equals(user.getDeptCode())){
				try {
					getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"请选择该用户所属的部门！\"}");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}else{
				Department dept = deptService.findDeptByDeptCode(user.getDeptCode());
				if(null==dept){
					try {
						getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"已选择的部门不存在，可能在另一线程中已被删除！\"}");		
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
				user.setDept(dept);
				if(checkedRight!=null && !checkedRight.equals("")){
					roleIds = checkedRight.split(",");
				}
				IUserExtendInfo userExtendInfo = (IUserExtendInfo)createExtendInfo();
				user.setUserExtendInfo(userExtendInfo);
				
				if(uploadFileName != null && uploadFileName.size() != 0)
				{
					//上传电子签名 2016-02-26 lt
					String name = uploadFileName.get(0);
					name = name.toLowerCase();
					if(name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif")  || name.endsWith(".bmp") || name.endsWith(".tiff")  || name.endsWith(".jpeg")  || name.endsWith(".tga"))
					{
						try {
							FileOutputStream fos = null;
							BufferedInputStream bis = null;
							File outfile = null;
							File outdir = null;
							byte[] bs = new byte[1024];
							String imgName="";
							StringBuffer destFName = new StringBuffer();
							String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
							root = root.substring(0,root.indexOf("webapps")+8);
							root = root.replaceAll("\\\\", "/");
							root = root.replace("webapps","virtualdir/upload");
							destFName.append(root).append("dzqm/");
							outdir = new File(destFName.toString());
							if(upload!= null && !upload.isEmpty()){//获取附件文件 进行判断是否存在
							    imgName =getDatedFName(uploadFileName.get(0));
								outfile = new File(destFName+imgName);
								InputStream stream  = new FileInputStream(upload.get(0));
								bis = new BufferedInputStream(stream);
								if (!outdir.exists())
									outdir.mkdirs();
								if (!outfile.exists())
									outfile.createNewFile();

								fos = new FileOutputStream(outfile);
								int i;
								while ((i = bis.read(bs)) != -1) {
									fos.write(bs, 0, i);
								}
							}
							String url = SysPropertiesUtil.getProperty("nwurl");
							user.setFilePath(url+"/upload/dzqm/"+imgName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						try {
							getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"文件格式错误！\"}");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				if(user.getId()==null) {
					// 判断用户名是否存在
					User user1 = userService.findUserByLoginId(user.getLoginId());
					if(user1!=null){
						try {
							getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"该用户名已经存在，请重新输入用户名！\"}");		
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}						
					userService.save(user, roleIds);
				}else
					userService.modify(user, roleIds);
				
				try {
					getResponse().getWriter().println("{\"status\":\"y\",\"info\":\""+user.getId()+"\"}");	
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}else{
			try {
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"没有找到该用户！\"}");		
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @author luting 2015-10-13
	 */
	public String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}
	
	/**
	 * 保存个人修改的信息
	 * 
	 * @return
	 */
	public String saveMyInfo(){
		if(user!=null ){
			Department dept = deptService.findDeptByDeptCode(user.getDeptCode());
			user.setDept(dept);
			if(checkedRight!=null && !checkedRight.equals("")){
				roleIds = checkedRight.split(",");
			}
			IUserExtendInfo userExtendInfo = (IUserExtendInfo)createExtendInfo();
			user.setUserExtendInfo(userExtendInfo);
			if(user.getId()==null) 
				user = userService.save(user, roleIds);
			else
				user = userService.modify(user, roleIds);
			this.setSessionAttribute(Constants.CURR_USER, user);
		}
		return RELOAD;
	}
	
	/**
	 * 激活被禁用的人员
	 * 
	 * @return String  
	 */
	public void activeUser(){
		if(user!=null && user.getId()!=null){
			user = userService.activeUser(user.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.USER_NULL_EXCEPTION);
			throw ex;
		}
	}
	
	/**
	 * 禁用人员
	 * 
	 * @return String  
	 */
	public void inactiveUser(){
		if(user!=null && user.getId()!=null){
			user = userService.inactiveUser(user.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.USER_NULL_EXCEPTION);
			throw ex;
		}
	}
	
	
	/**
	 * 获取用户角色设置
	 * 
	 * @return String
	 */
	public String editUserRight(){
		if (userId != null) {
			user = userService.findUserById(userId);
		}
		//setSessionAttribute("moduleRightList", module.getRightList());
		//setSessionAttribute("moduleId", moduleId);
		roleList = userRoleService.getAll();
		return SUCCESS;
	}
	
	
	/**
	 * 保存用户角色设置
	 * 
	 * @return String
	 */
	public String saveUserRight(){
		if(userId==null)
			userId=(String)this.getSessionAttribute("userId");
		// 删除用户原有角色
		userService.delByUser(userId);
		user = userService.findUserById(userId);
		
		// 添加角色
		if(roleCodes!=null||roleCodes.trim().equals("")){
			String[] roleIds = roleCodes.split("\\|");
			for(String roleId:roleIds){
				if(roleId==null||roleId.trim().equals(""))
					continue;
				UserRight right = new UserRight();
				UserRole role = userRoleService.findRoleByCode(roleId);
				right.setUser(user);
				right.setRole(role);
				userService.saveRight(right);
			}
		}
		return SUCCESS;

	}
	/**
	 * 返回用户管理页面
	 */
	public String list(){
		if(!this.getLoginUser().getIsSuperAdmin()){
			if(user==null)
				user = new User();
			user.setDeptCode(this.getLoginUser().getDept().getDeptCode());
			deptId=this.getLoginUser().getDept().getId();
		}
		return LIST;
	}
	
	/**
	 * 分页查询用户信息，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"delFlag":0,"dept":{"delFlag":0,"deptName":"","id":"","sortSQ":1},"displayName":"","duty":"","email":"","id":"","loginId":"","mobile":"","tel":""}]}
	 * @return String 
	 */
	public void listUser(){		
		pagination = new Pagination(super.getRequest());
		if(this.getRequestParameter("pageNumber")!=null){
			pagination.setPageNumber(Integer.parseInt(this.getRequestParameter("pageNumber")));
			//this.setRequestAttribute("page", this.getRequestParameter("pageNumber"));
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(user!=null){
			if(StringUtils.isNotEmpty(user.getDeptCode()) && !user.getDeptCode().equals("0")){
				paraMap.put("parentDeptCode", user.getDeptCode()+"%");
				this.setSessionAttribute("deptCode", user.getDeptCode().trim());
			}
			if(StringUtils.isNotEmpty(user.getLoginId()))
				paraMap.put("loginId", "%" + user.getLoginId()+"%");
			if(StringUtils.isNotEmpty(user.getDisplayName()))
				paraMap.put("displayName", "%" + user.getDisplayName()+"%");
			if(user.getDelFlag()==null || user.getDelFlag()==0){
				paraMap.put("delFlag", 0);
			}
		}else{
			
		}
		pagination = userService.findUserByPage(pagination, paraMap);
		try {
			outputJsonList(pagination.getTotalCount(), "id|displayName|loginId|duty|mobile|tel|email|sortSQ|delFlag|dept|deptName|", pagination.getListOfObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 *根据部门编码查询用户信息返回json数据<br>
	 *[{"displayName":"","id":""}]
	 */
    public void listAllUsersByDept(){
    	if(deptCode.equals("") && !getLoginUser().getIsSuperAdmin())
    		deptCode = getLoginUser().getDeptCode();
    	List<User> users=this.userService.findAllUsersByDept(this.deptCode);
    	
    	JsonConfig config = new JsonConfig();
    	final String filter="id|displayName";
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (filter.indexOf(name + "|") != -1 || filter.indexOf("|" + name) != -1)
					return false;
				else
					return true;
			}
		});
    	JSONArray json = JSONArray.fromObject(users,config);
    	try {
			this.getResponse().getWriter().println(json);
		} catch (Exception e) {
			logger.error(JSON_ERROR, e);
			e.printStackTrace();
		}
    }
    
	/**
	 * 初始化密码为99999，返回json字符串：{"result":"true|false"}
	 */
	public void initUserPassword(){
		if(user!=null && user.getId()!=null){
			userService.initPassword(user.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
		}
	}
	
	/**
	 * 变更密码
	 * 
	 * @return String  
	 */
	public String changePassword(){
		user = userService.checkPassword(user.getLoginId(), CodeUtil.encode(user.getPassword(), CodeUtil.MD5));
		if(null != user){
			userService.modifyPassword(user.getId(), newPassword);
			setSessionAttribute("noMd5Password", newPassword);
			errorInfo = "密码修改成功，下次请用新密码登录！";
		}else{
			errorInfo = "密码修改失败，原密码错误！";
		}
		return RELOAD;
	}
	
	/**
	 * 变更密码
	 * 
	 * @return String  
	 */
	public String changePasswords(){
		user = userService.checkPassword(user.getLoginId(), CodeUtil.encode(user.getPassword(), CodeUtil.MD5));
		if(null != user){
			userService.modifyPassword(user.getId(), newPassword);
			setSessionAttribute("noMd5Password", newPassword);
			errorInfo = "密码修改成功，下次请用新密码登录！";
		}else{
			errorInfo = "密码修改失败，原密码错误！";
		}
		return RELOAD;
	}
	
	/**
	 * 删除用户，返回json字符串：{"result":"true|false"}
	 */
	public void delUser(){
		try
        {
            if(ids!=null && ids.length!=0){
            	for(String item : ids){
            		userService.delByUser(item);
            		userService.delUser(item);
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
        catch (Exception e)
        {
        	try{
        		getResponse().getWriter().println("{\"result\":false}");
        	}catch(Exception e1){
        	}
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	/**
	 * 逻辑删除用户，delFlag置为2
	 */
	public void logicDelUser(){
		if(user!=null && user.getId()!=null){
			//user = userService.findUserById(user.getId());
			//删除用户角色
			userService.logicDelUser(user.getId());
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			BasalException ex = new BasalException(BasalException.NO, Constants.USER_NULL_EXCEPTION);
			throw ex;
		}
	}
	/**
	 * 导入初始化，需要传递参数
	 */
	public String initImport(){
		return SUCCESS;
	}
	/**
	 * 导入用户操作
	 */
	public String importUser(){
		StringBuffer errorInfo = new StringBuffer();
		
		if(null!=deptId && !deptId.equals("")){
			Department dept = deptService.findDeptByDeptCode(deptId);
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(userFile);
			} catch (BiffException e) {
				BasalException ex = new BasalException(BasalException.NO, Constants.EXCEL_FILE_ERROR);
				throw ex;
			} catch (IOException e) {
				BasalException ex = new BasalException(BasalException.NO, Constants.EXCEL_FILE_ERROR);
				throw ex;
			}
			Sheet sheet = workbook.getSheet(0);
			
			int column = sheet.getColumns();
			int row = sheet.getRows();
			List<User> userList = new ArrayList<User>();
			for (int i = 1; i < row; i++) {
				
				Cell[] cells = new Cell[column];
				Cell[] cellsTmp = sheet.getRow(i);
				for (int j = 0; j < cellsTmp.length; j++) {
					cells[j] = cellsTmp[j];
				}
				User user = new User();
				
				user.setDept(dept);
				user.setDeptCode(dept.getDeptCode());
				
				StringBuffer colError = null;
				if (cells[0]==null || cells[0].getContents().equals("")) {
					workbook.close();
					//BasalException ex = new BasalException(BasalException.NO, "用户显示名称输入有误，不能为空，请检查!");
					//throw ex;
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：用户显示名称输入有误，不能为空，请检查!</span><br>");
				}else
					user.setDisplayName(cells[0].getContents().trim());
				if (cells[1]==null ||cells[1].getContents().equals("")) {
					workbook.close();
					//BasalException ex = new BasalException(BasalException.NO, "用户登录名称输入有误，不能为空，请检查!");
					//throw ex;
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：用户登录名称输入有误，不能为空，请检查!</span><br>");
				}else{
					String loginId = cells[1].getContents().trim();
					User user1 = userService.findUserByLoginId(loginId);
					if(user1!=null){
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：该用户登录名已经使用过!</span><br>");
					}else
						user.setLoginId(cells[1].getContents().trim());
					
				}
				if (cells[2]==null || cells[2].getContents().equals("")) {
					workbook.close();
					//BasalException ex = new BasalException(BasalException.NO, "用户手机号输入有误，不能为空，请检查!");
					//throw ex;
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：用户手机号输入有误，不能为空，请检查!</span><br>");
				}else
					user.setMobile(cells[2].getContents().trim());

				if (cells[3] != null)
					user.setDuty(cells[3].getContents().trim());
				if (cells[4] != null)
					user.setTel(cells[4].getContents().trim());
				if (cells[5] != null)
					user.setEmail(cells[5].getContents().trim());
				try{
					if (cells[6] != null)
						user.setSortSq(Integer.parseInt(cells[6].getContents().trim()));
					else
						user.setSortSq(1);
				}catch(Exception e){
					errorInfo.append("<span style='color:yellow'>警告：用户排序有误，修改为“1”!</span><br>");
					user.setSortSq(1);
				}
				user.setDelFlag(0);
				
				if(colError==null){
					userList.add(user);
					errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
				}else{
					errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
					errorInfo.append(colError).append("<br>");
				}
			}
			message = errorInfo.toString();
			try {
				userService.save(userList);
			} catch (Exception e) {
				workbook.close();
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 返回部门设置页面
	 */
	public String deptTree(){
		user = userService.findUserById(userId);
		return SUCCESS;
	}
	/**
	 * 用户关联部门设置保存
	 */
	public void saveLinkedDept(){
		if(linkedDeptIds!=null){
			userLinkedDeptService.saveLinkedDept(userId, linkedDeptIds.split("\\|"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		outputJson(null, map);
	}
	/**
	 * 关联部门树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findLinkedDept(){
		try {
			user = userService.findUserById(userId);
			if (null == selDept) {
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("id", "o");
				root.put("text", "组织机构");
				root.put("state", "opened");
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				List<UserLinkedDept> linkedDeptList = userLinkedDeptService.getLinkedDeptByUser(userId, "%");
				// 初始化部门树
				if (getLoginUser().getIsSuperAdmin()){
					List<Department> deptList = deptService
							.findDeptByParentDeptCode("");
					
					for (Department d : deptList) {
						Map<String, Object> item = new HashMap<String, Object>();
						item.put("id", d.getDeptCode());
						item.put("text", d.getDeptName());
						List<Department> childList = deptService.findDeptByParentDeptCode(
								d.getDeptCode());
						if (childList!=null && childList.size() > 0) {
							item.put("state", "closed");
						}
						for(UserLinkedDept linkedDept : linkedDeptList){
							if(linkedDept.getLinkedDeptId().equals(d.getId())){
								item.put("checked", "true");
								break;
							}
						}
						elements.add(item);
					}
				}else{
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
			List<UserLinkedDept> linkedDeptList1 = userLinkedDeptService.getLinkedDeptByUser(getLoginUserId(), "%");
			if(linkedDeptList1!=null && linkedDeptList1.size()>0){
				for(UserLinkedDept linkedDept : linkedDeptList1){
					item = new HashMap<String, Object>();
					item.put("id", linkedDept.getLinkedDept().getDeptCode());
					item.put("text", linkedDept.getLinkedDept().getDeptName());
					if (deptService.findDeptByParentDeptCode(
							linkedDept.getLinkedDept().getDeptCode()).size() > 0|| deptService.findLinkedDpet(linkedDept.getLinkedDept()).size()>0) {
						item.put("state", "closed");
					}
					for(UserLinkedDept linkedDept1 : linkedDeptList){
						if(linkedDept.getLinkedDeptId().equals(linkedDept1.getLinkedDeptId())){
							item.put("checked", "true");
							break;
						}
					}
					elements.add(item);
				}
			}
				}
				
				root.put("children", elements);
				items.add(root);
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(json.toString());
			} else {
				List<Department> deptList = deptService
						.findDeptByParentDeptCode(selDept);
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				List<UserLinkedDept> linkedDeptList = userLinkedDeptService.getLinkedDeptByUser(userId, selDept+"%");
				for (Department d : deptList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", d.getDeptCode());
					item.put("text", d.getDeptName());
					if (deptService.findDeptByParentDeptCode(d.getDeptCode())
							.size() > 0 || deptService.findLinkedDpet(d).size()>0) {
						item.put("state", "closed");
					}
					for(UserLinkedDept linkedDept : linkedDeptList){
						if(linkedDept.getLinkedDeptId().equals(d.getId())){
							item.put("checked", "true");
							break;
						}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the roleIds
	 */
	public String[] getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the roleList
	 */
	public List<UserRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the mobileUnique
	 */
	public Boolean getMobileUnique() {
		return mobileUnique;
	}

	/**
	 * @param mobileUnique the mobileUnique to set
	 */
	public void setMobileUnique(Boolean mobileUnique) {
		this.mobileUnique = mobileUnique;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	public String checkedRight;

	public String getCheckedRight() {
		return checkedRight;
	}

	public void setCheckedRight(String checkedRight) {
		this.checkedRight = checkedRight;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getLinkedDeptIds() {
		return linkedDeptIds;
	}

	public void setLinkedDeptIds(String linkedDeptIds) {
		this.linkedDeptIds = linkedDeptIds;
	}

	public String getSelDept() {
		return selDept;
	}

	public void setSelDept(String selDept) {
		this.selDept = selDept;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
}
