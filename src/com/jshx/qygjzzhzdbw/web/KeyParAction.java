package com.jshx.qygjzzhzdbw.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import net.sf.json.JSONObject;
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
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qygjzzhzdbw.entity.KeyPar;
import com.jshx.qygjzzhzdbw.service.KeyParService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class KeyParAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private KeyPar keyPar = new KeyPar();

	/**
	 * 业务类
	 */
	@Autowired
	private KeyParService keyParService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private HttpService httpService;

	private int pageNo;
	private int pageSize;
	private String searchLike;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String roleName;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	 private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//安全操作规程
		
	 private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//现场照片
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
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
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != keyPar){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != keyPar.getAreaId()) && (0 < keyPar.getAreaId().trim().length())){
				paraMap.put("areaId", keyPar.getAreaId().trim() );
			}

			if ((null != keyPar.getCompanyName()) && (0 < keyPar.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + keyPar.getCompanyName().trim() + "%");
			}

			if ((null != keyPar.getKeyPartName()) && (0 < keyPar.getKeyPartName().trim().length())){
				paraMap.put("keyPartName", "%" + keyPar.getKeyPartName().trim() + "%");
			}

			if ((null != keyPar.getResponsiblePerson()) && (0 < keyPar.getResponsiblePerson().trim().length())){
				paraMap.put("responsiblePerson", "%" + keyPar.getResponsiblePerson().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|responsiblePerson|areaName|companyName|keyPartName|";
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
		pagination = keyParService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != keyPar)&&(null != keyPar.getId()))
		{
			keyPar = keyParService.getById(keyPar.getId());
				if(keyPar.getLinkId() == null || "".equals(keyPar.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						keyPar.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",keyPar.getLinkId());
							map.put("mkType", "qygjzzhzdbw1");
							map.put("picType","qygjzzhzdbwfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "qygjzzhzdbw2");
							map.put("picType","qygjzzhzdbwfj2");
							picList2 = photoPicService.findPicPath(map);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					keyPar.setLinkId(linkId);
				}
		
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
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		keyPar.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		keyPar.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		keyPar.setCompanyId(enBaseInfo.getId());
		keyPar.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			keyPar.setDeptId(this.getLoginUserDepartmentId());
			keyPar.setDelFlag(0);
			keyParService.save(keyPar);
		}else{
			keyParService.update(keyPar);
		}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != keyPar)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到keyPar中去
				
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
			keyParService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	@SuppressWarnings("unchecked")
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != keyPar){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != keyPar.getAreaId()) && (0 < keyPar.getAreaId().trim().length())){
				paraMap.put("areaId", keyPar.getAreaId().trim() );
			}

			if ((null != keyPar.getCompanyName()) && (0 < keyPar.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + keyPar.getCompanyName().trim() + "%");
			}

			if ((null != keyPar.getKeyPartName()) && (0 < keyPar.getKeyPartName().trim().length())){
				paraMap.put("keyPartName", "%" + keyPar.getKeyPartName().trim() + "%");
			}

			if ((null != keyPar.getResponsiblePerson()) && (0 < keyPar.getResponsiblePerson().trim().length())){
				paraMap.put("responsiblePerson", "%" + keyPar.getResponsiblePerson().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|responsiblePerson|areaName|companyName|keyPartName|";
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
		
		
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = keyParService.findByPage(pagination, paraMap);
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public KeyPar getKeyPar(){
		return this.keyPar;
	}

	public void setKeyPar(KeyPar keyPar){
		this.keyPar = keyPar;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchLike() {
		return searchLike;
	}

	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
       
    
}
