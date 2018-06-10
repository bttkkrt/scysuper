package com.jshx.zcaqgcs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.zcaqgcs.entity.CerSafEng;
import com.jshx.zcaqgcs.service.CerSafEngService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class CerSafEngAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CerSafEng cerSafEng = new CerSafEng();

	/**
	 * 业务类
	 */
	@Autowired
	private CerSafEngService cerSafEngService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	private Date queryFirstLicenseStart;

	private Date queryFirstLicenseEnd;

	private Date queryCertificaateValidStart;

	private Date queryCertificaateValidEnd;

	@Autowired
	private CodeService codeService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	 /**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A15")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}

	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(this.getLoginUser().getDeptCode().equals("009"))
		{
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}   
		if(null != cerSafEng){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cerSafEng.getAreaId()) && (0 < cerSafEng.getAreaId().trim().length())){
				paraMap.put("areaId", cerSafEng.getAreaId().trim() );
			}

			if ((null != cerSafEng.getAreaName()) && (0 < cerSafEng.getAreaName().trim().length())){
				paraMap.put("areaName", cerSafEng.getAreaName().trim());
			}

			if ((null != cerSafEng.getCompanyName()) && (0 < cerSafEng.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cerSafEng.getCompanyName().trim() + "%");
			}

			if ((null != cerSafEng.getSaftyName()) && (0 < cerSafEng.getSaftyName().trim().length())){
				paraMap.put("saftyName", "%" + cerSafEng.getSaftyName().trim() + "%");
			}

			if ((null != cerSafEng.getIdCard()) && (0 < cerSafEng.getIdCard().trim().length())){
				paraMap.put("idCard", "%" + cerSafEng.getIdCard().trim() + "%");
			}

			if ((null != cerSafEng.getCertificateNo()) && (0 < cerSafEng.getCertificateNo().trim().length())){
				paraMap.put("certificateNo", "%" + cerSafEng.getCertificateNo().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|saftyName|idCard|certificateNo|createUserID|";
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
		pagination = cerSafEngService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cerSafEng)&&(null != cerSafEng.getId()))
			cerSafEng = cerSafEngService.getById(cerSafEng.getId());
		
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
		try {
			FileInputStream in = null;
			try {
				//设置Blob字段
				setBlobField(in);
			} finally {
				if (null != in) {
					try {
						in.close();
					} catch (Exception ex) {
					}
				}
			}
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", cerSafEng.getAreaId());
			cerSafEng.setAreaName(codeService.findCodeValueByMap(m)
					.getItemText());
			if ("add".equalsIgnoreCase(this.flag)) {
				cerSafEng.setDeptId(this.getLoginUserDepartmentId());
				cerSafEng.setDelFlag(0);
				cerSafEngService.save(cerSafEng);
			} else {
				cerSafEngService.update(cerSafEng);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cerSafEng)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cerSafEng中去
				
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
			cerSafEngService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
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

	public CerSafEng getCerSafEng(){
		return this.cerSafEng;
	}

	public void setCerSafEng(CerSafEng cerSafEng){
		this.cerSafEng = cerSafEng;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryFirstLicenseStart(){
		return this.queryFirstLicenseStart;
	}

	public void setQueryFirstLicenseStart(Date queryFirstLicenseStart){
		this.queryFirstLicenseStart = queryFirstLicenseStart;
	}

	public Date getQueryFirstLicenseEnd(){
		return this.queryFirstLicenseEnd;
	}

	public void setQueryFirstLicenseEnd(Date queryFirstLicenseEnd){
		this.queryFirstLicenseEnd = queryFirstLicenseEnd;
	}

	public Date getQueryCertificaateValidStart(){
		return this.queryCertificaateValidStart;
	}

	public void setQueryCertificaateValidStart(Date queryCertificaateValidStart){
		this.queryCertificaateValidStart = queryCertificaateValidStart;
	}

	public Date getQueryCertificaateValidEnd(){
		return this.queryCertificaateValidEnd;
	}

	public void setQueryCertificaateValidEnd(Date queryCertificaateValidEnd){
		this.queryCertificaateValidEnd = queryCertificaateValidEnd;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

}
