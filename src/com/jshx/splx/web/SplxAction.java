package com.jshx.splx.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.jshx.splx.entity.Splx;
import com.jshx.splx.service.SplxService;

public class SplxAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Splx splx = new Splx();

	/**
	 * 业务类
	 */
	@Autowired
	private SplxService splxService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryEndTimeStart;

	private Date queryEndTimeEnd;

	private Date queryBeginTimeStart;

	private Date queryBeginTimeEnd;
	
	private String guid;
	
	private String s;
	
	List<Splx> splxlist=new ArrayList<Splx>();
	
	private String startTime;
	
	private String endTime;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init()
	{
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != splx){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != splx.getFileName()) && (0 < splx.getFileName().trim().length())){
				paraMap.put("fileName", "%" + splx.getFileName().trim() + "%");
			}
			
			if ((null != splx.getGuid()) && (0 < splx.getGuid().trim().length())){
				paraMap.put("guid", splx.getGuid().trim() );
			}

			if (null != queryBeginTimeStart){
				paraMap.put("startBeginTime", queryBeginTimeStart);
			}

			if (null != queryBeginTimeEnd){
				paraMap.put("endBeginTime", queryBeginTimeEnd);
			}
			if (null != queryEndTimeStart){
				paraMap.put("startEndTime", queryEndTimeStart);
			}

			if (null != queryEndTimeEnd){
				paraMap.put("endEndTime", queryEndTimeEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|fileName|endTime|beginTime|guid|taskId|";
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
		pagination = splxService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != splx)&&(null != splx.getId()))
			splx = splxService.getById(splx.getId());
		
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
			splx.setDeptId(this.getLoginUserDepartmentId());
			splx.setDelFlag(0);
			splxService.save(splx);
		}else{
			splxService.update(splx);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != splx)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到splx中去
				
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
			splxService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 保存录像
	 */
	public String saveSplxList() throws IOException{
		try {
			Map map = new HashMap();
			map.put("guid", guid);
			splxService.deleteSplxByMap(map);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(s != null && !s.equals("")){
				String a[]=s.split(";");
				for(String b:a){
					String c[] = b.split(",");
					splx = new Splx();
					splx.setDeptId(this.getLoginUserDepartmentId());
					splx.setDelFlag(0);
					splx.setTaskId(c[0]);
					splx.setFileName(c[1]);
					splx.setGuid(guid);
					splx.setBeginTime(sdf.parse(c[2]));
					splx.setEndTime(sdf.parse(c[3]));
					splxService.save(splx);
				}
			}
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转播放录像
	 */
	
	public String splxVedio(){
		if(splx.getId() != null && !"".equals(splx.getId()))
		{
			splx = splxService.getById(splx.getId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
			startTime = sdf.format(splx.getBeginTime());

			endTime = sdf.format(splx.getEndTime());;
		}
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

	public Splx getSplx(){
		return this.splx;
	}

	public void setSplx(Splx splx){
		this.splx = splx;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryEndTimeStart(){
		return this.queryEndTimeStart;
	}

	public void setQueryEndTimeStart(Date queryEndTimeStart){
		this.queryEndTimeStart = queryEndTimeStart;
	}

	public Date getQueryEndTimeEnd(){
		return this.queryEndTimeEnd;
	}

	public void setQueryEndTimeEnd(Date queryEndTimeEnd){
		this.queryEndTimeEnd = queryEndTimeEnd;
	}

	public Date getQueryBeginTimeStart(){
		return this.queryBeginTimeStart;
	}

	public void setQueryBeginTimeStart(Date queryBeginTimeStart){
		this.queryBeginTimeStart = queryBeginTimeStart;
	}

	public Date getQueryBeginTimeEnd(){
		return this.queryBeginTimeEnd;
	}

	public void setQueryBeginTimeEnd(Date queryBeginTimeEnd){
		this.queryBeginTimeEnd = queryBeginTimeEnd;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<Splx> getSplxlist() {
		return splxlist;
	}

	public void setSplxlist(List<Splx> splxlist) {
		this.splxlist = splxlist;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
