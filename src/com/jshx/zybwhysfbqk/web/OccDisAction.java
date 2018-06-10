package com.jshx.zybwhysfbqk.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
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
import com.jshx.module.admin.service.CodeService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.zybwhysfbqk.entity.OccDis;
import com.jshx.zybwhysfbqk.service.OccDisService;
import com.jshx.zywhjbysqk.entity.OccHazBas;
import com.jshx.zywhjbysqk.service.OccHazBasService;
import com.jshx.zywsgljbxx.service.OccHeaInfoService;

public class OccDisAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private OccDis occDis = new OccDis();
	
	private EntBaseInfo entBaseInfo = new EntBaseInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private OccDisService occDisService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private int pageNo;
	private int pageSize;
	private String searchLike;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * 实体类
	 */
	private OccHazBas occHazBas = new OccHazBas();

	/**
	 * 业务类
	 */
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private OccHeaInfoService occHeaInfoService;
	@Autowired
	private OccHazBasService occHazBasService;
	
	public String init(){
		
		
		//初始化equAndFac，获取equAndFac的id，使添加的occDis列表与之关联
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map<String, Object> paraMap0 = new HashMap<String, Object>();
		paraMap0.put("companyId", enBaseInfo.getId());
		occHazBas=(OccHazBas)occHeaInfoService.findObjectByMap(OccHazBas.class,paraMap0 );
		if(null==occHazBas){
			occHazBas=new OccHazBas();
			occHazBas.setDelFlag(0);
			occHazBas.setDeptId(this.getLoginUserDepartmentId());
			occHazBas.setAreaId(enBaseInfo.getEnterprisePossession());
			occHazBas.setCompanyId(enBaseInfo.getId());
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			occHazBas.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			occHazBasService.save(occHazBas);
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null){
		    pagination = new Pagination(this.getRequest());
		}
		if(null != occDis){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		paraMap.put("proId", occHazBas.getId());
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|workPlace|contactNum|proId|";
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
		pagination = occDisService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	/**
	 * 政务通 基本因素 
	 * lj
	 */
	public String jbys(){
		Map map = new HashMap();
		map.put("companyId", entBaseInfo.getId());
		occHazBas=(OccHazBas)occHeaInfoService.findObjectByMap(OccHazBas.class,map );
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	@SuppressWarnings("unchecked")
	public void zwtList() throws Exception{
		if(null!=ids&&!"".equals(ids)){
			Map map = new HashMap();
			String[] a=ids.split(",");
			if(a.length > 1)
			{
				map.put("companyId", a[1].trim());
				List l=occHazBasService.findOccHazBas(map);
				if(l.size()>0){
					
					occHazBas=(OccHazBas)l.get(0);
				}else{
					occHazBas.setId("0");
				}
			}
		}
		
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null){
		    pagination = new Pagination(this.getRequest());
		}
		if(null != occDis){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		paraMap.put("proId", occHazBas.getId());
		JsonConfig config = new JsonConfig();
	
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = occDisService.findByPage(pagination, paraMap);
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
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != occDis)&&(null != occDis.getId()))
			occDis = occDisService.getById(occDis.getId());
		
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
			occDis.setDeptId(this.getLoginUserDepartmentId());
			occDis.setDelFlag(0);
			occDis.setProId(occHazBas.getId());
			occDisService.save(occDis);
		}else{
			occDisService.update(occDis);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != occDis)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到occDis中去
				
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
			occDisService.deleteWithFlag(ids);
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

	public OccDis getOccDis(){
		return this.occDis;
	}

	public void setOccDis(OccDis occDis){
		this.occDis = occDis;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public OccHazBas getOccHazBas() {
		return occHazBas;
	}
	public void setOccHazBas(OccHazBas occHazBas) {
		this.occHazBas = occHazBas;
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
	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}
	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}

       
    
}
