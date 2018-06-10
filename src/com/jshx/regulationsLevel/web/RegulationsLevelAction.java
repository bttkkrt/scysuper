package com.jshx.regulationsLevel.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.regulationsLevel.entity.RegulationsLevel;
import com.jshx.regulationsLevel.service.RegulationsLevelService;

public class RegulationsLevelAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private RegulationsLevel regulationsLevel = new RegulationsLevel();

	/**
	 * 业务类
	 */
	@Autowired
	private RegulationsLevelService regulationsLevelService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private List allType;//所有类别
	
	private String filePath;
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list1() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != regulationsLevel){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != regulationsLevel.getLevelCode()) && (0 < regulationsLevel.getLevelCode().trim().length())){
				paraMap.put("levelCode", "%" + regulationsLevel.getLevelCode().trim() + "%");
			}

			if ((null != regulationsLevel.getLevelName()) && (0 < regulationsLevel.getLevelName().trim().length())){
				paraMap.put("levelName", "%" + regulationsLevel.getLevelName().trim() + "%");
			}

		}
		pagination = regulationsLevelService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public String list() throws Exception{
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(null != regulationsLevel){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != regulationsLevel.getLevelName()) && (0 < regulationsLevel.getLevelName().trim().length())){
				paraMap.put("levelName", "%" + regulationsLevel.getLevelName().trim() + "%");
			}

		}
		JSONArray json = new JSONArray();
		
		List<Object> list = regulationsLevelService.findPageTree(paraMap);
		
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(list.size()).append(",\n");
		data.append("  \"rows\":\n");
		
		for(int i=0;i<list.size();i++)
		{
			Object[] obj=(Object[])list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("id", obj[0]);
			jo.put("name", obj[1]);
			if(null!=obj[2]&&obj[2].toString().equals("-1"))
			{
				jo.put("_parentId", "");
			}else
			{	
				jo.put("_parentId", obj[2]);
			}
			jo.put("userName", obj[3]);
			jo.put("deptName", obj[4]);
			jo.put("updeTime", obj[5]);
			jo.put("createuserid", obj[6]);
			
			json.add(jo);
		}
		data.append(json.toString());
		
		data.append("  \n").append("}");
		
		//System.out.println(data);
		getResponse().getWriter().println(data);
		return null;
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != regulationsLevel)&&(null != regulationsLevel.getId()))
		{
			regulationsLevel = regulationsLevelService.getById(regulationsLevel.getId());
		
			if (null != regulationsLevel && null != regulationsLevel.getUplevelId() && !"".equals(regulationsLevel.getUplevelId()))
			{
				if ("-1".equals(regulationsLevel.getUplevelId()))
				{
					getRequest().setAttribute("superTypeName", "法规级别");
				}
				else
				{
					RegulationsLevel superType = regulationsLevelService.getById(regulationsLevel.getUplevelId());
					if (null != superType)
					{
						getRequest().setAttribute("superTypeName", superType.getLevelName());
					}
					else
					{
						getRequest().setAttribute("superTypeName", "法规级别");
					}
				}
			}
		}
		return VIEW;
	}
	
	/**
	 * 此法规级别是否有子节点
	 * 
	 * @return
	 */
	public String checkHasChild() {
		Map<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("parentId", ids);
		// paraMap.put("delFlag", "0");
		String result = this.regulationsLevelService.checkHasChild(paraMap);
		try {
			getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 查询是否存在法规级别
	 * 
	 * @return
	 */
	public String checkExist() {
		Map<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("levelName", regulationsLevel.getLevelName());
		// paraMap.put("delFlag", "0");
		String result = this.regulationsLevelService.checkExist(paraMap);
		try {
			getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		// allType = regulationsLevelService.findAllType();
		if(flag.equals("add")){
			regulationsLevel.setUplevelId("-1");
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			regulationsLevel.setDeptId(this.getLoginUserDepartmentId());
			regulationsLevel.setDelFlag(0);
			regulationsLevel.setUpdateTime(new Date());
			String fullPath = regulationsLevelService.findFullPathBySuperId(regulationsLevel.getUplevelId());
			if(fullPath != null && fullPath !=""){
				regulationsLevel.setFullpath(fullPath+regulationsLevel.getUplevelId()+"/");
			}else{
				regulationsLevel.setFullpath(regulationsLevel.getUplevelId()+"/");
			}
			regulationsLevelService.save(regulationsLevel);
		}else{
			String fullPath = regulationsLevelService.findFullPathBySuperId(regulationsLevel.getUplevelId());
			if(fullPath != null && fullPath !=""){
				regulationsLevel.setFullpath(fullPath+regulationsLevel.getUplevelId()+"/");
			}else{
				regulationsLevel.setFullpath(regulationsLevel.getUplevelId()+"/");
			}
			regulationsLevelService.update(regulationsLevel);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete11() throws Exception{
	    try{
			regulationsLevelService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
	    	int i=regulationsLevelService.checkOfficesupply(ids);
	    	if(i>0)
	    	{
	    		this.getResponse().getWriter().println("{\"result\":\"2\"}");
	    	}
	    	else
	    	{
	    		regulationsLevelService.deleteWithFlag(ids);
				this.getResponse().getWriter().println("{\"result\":\"1\"}");
	    	}
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"2\"}");
		}
		return null;
	}
	
	/**
	 * 查询名字
	 */
	public String findName() throws Exception{
	    try{
	    	String name = regulationsLevelService.findName(regulationsLevel.getUplevelId());
			this.getResponse().getWriter().println("{\"result\":\""+name+"\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 列表数
	 * @return
	 * @throws Exception
	 */
	public String getRegulationsLevelTree() throws Exception{
		List jsonList = new ArrayList();
		List jsonList1 = new ArrayList();
		List<RegulationsLevel> rootList = regulationsLevelService.findRegulationsLevel("getRegulationsLevelRoot",null);  //所有根节点
		Map map1=new HashMap();
		map1.put("id", "-1");
		map1.put("text", "法规级别");
		if(null!=rootList && rootList.size()>0)
		{
			for(int i=0;i<rootList.size();i++)
			{
				RegulationsLevel regulationsLevel=rootList.get(i);
				Map<String,Object> tempMap = new HashMap<String,Object>();
				tempMap.put("id", regulationsLevel.getId());
				tempMap.put("text", regulationsLevel.getLevelName());
				
				//判断是否有子节点
				Map map=new HashMap();
				map.put("parentId", regulationsLevel.getId());
				List<RegulationsLevel> officeList=regulationsLevelService.findRegulationsLevel("getChildRegulationsLevelNodesByParentId", map);
				if(null!=officeList && officeList.size()>0)
				{
					tempMap.put("children", getChildSuppliesTypeByParentId(regulationsLevel.getId()));
					tempMap.put("state", "closed");
				}
				jsonList.add(tempMap);
			}
			map1.put("children", jsonList);
			map1.put("state", "closed");
		}
		jsonList1.add(map1);
		JSONArray json = JSONArray.fromObject(jsonList1);
		getResponse().setContentType("application/json;charset=UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Charset", "UTF-8");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().getWriter().print(json);
		return null;
	}
	
	//根据父Id找到其子节点===获取办公用品类别子节点
	public List getChildSuppliesTypeByParentId(String parentId)
	{
		List list=new ArrayList();
		if(null!=parentId && !"".equals(parentId.trim()))
		{	
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("parentId", parentId);
			List<RegulationsLevel> deptList=regulationsLevelService.findRegulationsLevel("getChildRegulationsLevelNodesByParentId", map);
			if(null!=deptList && deptList.size()>0)
			{
				for(int j=0;j<deptList.size();j++)
				{
					RegulationsLevel mfSuppliesType=deptList.get(j);
					//存在子节点
					Map<String,Object> tempMap = new HashMap<String,Object>();
					tempMap.put("id", mfSuppliesType.getId());
					tempMap.put("text", mfSuppliesType.getLevelName());
					List childList = getChildSuppliesTypeByParentId(mfSuppliesType.getId());
					if(null!=childList&&childList.size()>0)
					{
						tempMap.put("children", getChildSuppliesTypeByParentId(mfSuppliesType.getId()));
						tempMap.put("state", "closed");
					}
					list.add(tempMap);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 父节点是否变成子节点的子节点
	 * 
	 * @return
	 */
	public String checkToBeChild() {
		Map<String, String> paraMap = new HashMap<String, String>();
		String result = "0";
		String supplytypeId = regulationsLevel.getId();
		// paraMap.put("delFlag", "0");
		List list = this.regulationsLevelService.checkToBeChild(regulationsLevel.getUplevelId());
		if(list.size() > 0) {
			regulationsLevel = (RegulationsLevel) list.get(0);
			String[] str = regulationsLevel.getFullpath().substring(0, regulationsLevel.getFullpath().length()-1).split("/");
			for(int i = 0;i < str.length;i++){
				if(supplytypeId.equals(str[i])){
					result = "1";
				}
			}
		}
		try {
			getResponse().getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public RegulationsLevel getRegulationsLevel(){
		return this.regulationsLevel;
	}

	public void setRegulationsLevel(RegulationsLevel regulationsLevel){
		this.regulationsLevel = regulationsLevel;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
