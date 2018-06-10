package com.jshx.lawLib.web;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jshx.lawLib.entity.LawLib;
import com.jshx.lawLib.service.LawLibService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class LawLibAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private LawLib lawLib = new LawLib();

	/**
	 * 业务类
	 */
	@Autowired
	private LawLibService lawLibService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	@Autowired
	private PhotoPicService photoPicService;

	@Autowired
	private HttpService httpService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private String roleName;
		
	public String init(){
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
			roleName="0";
		}else{
			roleName="1";
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
		    
		if(null != lawLib){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != lawLib.getLawName()) && (0 < lawLib.getLawName().trim().length())){
				paraMap.put("lawName", "%" + lawLib.getLawName().trim() + "%");
			}

			if ((null != lawLib.getState()) && (0 < lawLib.getState().trim().length())){
				paraMap.put("state", "%" + lawLib.getState().trim() + "%");
			}
			
			if ((null != lawLib.getType()) && (0 < lawLib.getType().trim().length())){
				paraMap.put("type", lawLib.getType().trim() );
			}

		}
		
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}else{
			paraMap.put("companyId", "aj");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|lawName|state|companyId|type|";
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
		pagination = lawLibService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != lawLib)&&(null != lawLib.getId())){
			lawLib = lawLibService.getById(lawLib.getId());
			if(lawLib.getLinkId() == null || "".equals(lawLib.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				lawLib.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",lawLib.getLinkId());
				map.put("mkType", "lawLib");
				map.put("picType","lawLibfj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
				if(picList.size() != 0)
				{
					PhotoPic photo = picList.get(0);
					lawLib.setUrl(photo.getHttpUrl()+ "/upload/photo/" + photo.getPicName());
				}
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			lawLib.setLinkId(linkId);
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
		if ("add".equalsIgnoreCase(this.flag)){
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
				lawLib.setState("0");
				Map map = new HashMap();
				map.put("loginId", this.getLoginUser().getLoginId());
				EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				lawLib.setCompanyId(entBaseInfo.getId());
			}else{
				lawLib.setState("1");
				lawLib.setCompanyId("aj");
			}
			lawLib.setDeptId(this.getLoginUserDepartmentId());
			lawLib.setDelFlag(0);
			lawLibService.save(lawLib);
		}else{
			lawLibService.update(lawLib);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != lawLib)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到lawLib中去
				
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
			lawLibService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 转入标准库
	 * fq 2016-4-14
	 */
	public String lawLibTurn() throws Exception{
	    try{
	    	lawLib=lawLibService.getById(ids);
	    	lawLib.setState("1");
			lawLibService.update(lawLib);
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

	public LawLib getLawLib(){
		return this.lawLib;
	}

	public void setLawLib(LawLib lawLib){
		this.lawLib = lawLib;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
       
    
}
