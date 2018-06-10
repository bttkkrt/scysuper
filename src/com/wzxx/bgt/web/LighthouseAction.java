package com.wzxx.bgt.web;

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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.wzxx.bgt.entity.Lighthouse;
import com.wzxx.bgt.service.LighthouseService;
import com.wzxx.bgtxx.entity.ExpTabDet;
import com.wzxx.bgtxx.entity.ExpTabDetBean;
import com.wzxx.bgtxx.service.ExpTabDetService;

public class LighthouseAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Lighthouse lighthouse = new Lighthouse();
	private ExpTabDet expTabDet = new ExpTabDet();
	/**
	 * 业务类
	 */
	@Autowired
	private LighthouseService lighthouseService;
	@Autowired
	private ExpTabDetService expTabDetService;
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
	
	
	private Date queryPublicDateStart;

	private Date queryPublicDateEnd;
    List<ExpTabDetBean> list=new ArrayList<ExpTabDetBean>();
    private int pageNum=1;
	
	private int totalCount;
	
	private int totalPage;
    private List<Lighthouse> bgtList=new ArrayList<Lighthouse>();
    
    private String aabb;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	/**
	 * 初始化
	 */
	public String init(){
		System.out.print("init");
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != lighthouse){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != lighthouse.getTitle()) && (0 < lighthouse.getTitle().trim().length())){
				paraMap.put("title", "%" + lighthouse.getTitle().trim() + "%");
			}

			if (null != queryPublicDateStart){
				paraMap.put("startPublicDate", queryPublicDateStart);
			}

			if (null != queryPublicDateEnd){
				paraMap.put("endPublicDate", queryPublicDateEnd);
			}
		}
		try {
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			
			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|title|publicDate|";
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
			pagination = lighthouseService.findByPage(pagination, paraMap);
			
			convObjectToJson(pagination, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != lighthouse)&&(null != lighthouse.getId()))
			lighthouse = lighthouseService.getById(lighthouse.getId());
		    Map map = new HashMap();
		    map.put("titleId",lighthouse.getId());
		    list=lighthouseService.findBgtxx(map);
		    if(list!=null&&!list.isEmpty()){
				for(ExpTabDetBean p:list){
					Map map2 = new HashMap();
					map2.put("linkId",p.getLinkId());
					List<String> photos = lighthouseService.getPhotosByType(map2);
					p.setPhotos(photos);
				}
			}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		if((null != lighthouse)&&(null != lighthouse.getId()))
			lighthouse = lighthouseService.getById(lighthouse.getId());
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
	     Date date=new Date();
	     lighthouse.setPublicDate(date);
		if ("add".equalsIgnoreCase(this.flag)){
			try {
				lighthouse.setDeptId(this.getLoginUserDepartmentId());
				lighthouse.setDelFlag(0);
				lighthouseService.save(lighthouse);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			lighthouseService.update(lighthouse);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != lighthouse)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到lighthouse中去
				
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
			lighthouseService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String bgtList(){
		bgtList = lighthouseService.findAllInfo(null, pageNum, 10);
		totalCount = lighthouseService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String bgtContent(){
		lighthouse=lighthouseService.getById(lighthouse.getId());
		JSONArray ja = new JSONArray();
		
		Map map = new HashMap();
	    map.put("titleId",lighthouse.getId());
	    list=lighthouseService.findBgtxx(map);
	    if(list!=null&&!list.isEmpty()){
			for(ExpTabDetBean p:list){
				Map map2 = new HashMap();
				map2.put("linkId",p.getLinkId());
				List<String> photos = lighthouseService.getPhotosByType(map2);
				p.setPhotos(photos);
				for(String mm:photos){
				JSONObject jo = new JSONObject();
				jo.put("href","#" );
				jo.put("alt","图片" );
				jo.put("src", "/upload/photo/"+mm);
				jo.put("smallSrc","/upload/photo/"+mm);
				jo.put("title",p.getDescriptor());
				ja.add(jo);
				}
			}
		}
		
		
		
		aabb=ja.toString();
		return SUCCESS;
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

	public Lighthouse getLighthouse(){
		return this.lighthouse;
	}

	public void setLighthouse(Lighthouse lighthouse){
		this.lighthouse = lighthouse;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPublicDateStart(){
		return this.queryPublicDateStart;
	}

	public void setQueryPublicDateStart(Date queryPublicDateStart){
		this.queryPublicDateStart = queryPublicDateStart;
	}

	public Date getQueryPublicDateEnd(){
		return this.queryPublicDateEnd;
	}

	public void setQueryPublicDateEnd(Date queryPublicDateEnd){
		this.queryPublicDateEnd = queryPublicDateEnd;
	}

	public List<ExpTabDetBean> getList() {
		return list;
	}

	public void setList(List<ExpTabDetBean> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Lighthouse> getBgtList() {
		return bgtList;
	}

	public void setBgtList(List<Lighthouse> bgtList) {
		this.bgtList = bgtList;
	}

	public String getAabb() {
		return aabb;
	}

	public void setAabb(String aabb) {
		this.aabb = aabb;
	}



	

}
