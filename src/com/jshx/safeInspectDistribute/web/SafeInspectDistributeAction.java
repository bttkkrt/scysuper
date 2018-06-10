package com.jshx.safeInspectDistribute.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;
import com.jshx.inspectItem.entity.InspectItem;
import com.jshx.inspectItem.service.InspectItemService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.entity.SafeInspectTjBean;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class SafeInspectDistributeAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SafeInspectDistribute safeInspectDistribute = new SafeInspectDistribute();

	/**
	 * 业务类
	 */
	@Autowired
	private SafeInspectDistributeService safeInspectDistributeService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private CompanyService companyService;
	@Autowired DistributeItemService distributeItemService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private String item ;
	private String deptCode;
	private String selDept;
	private List<Department> deptList;
	private Department dept;
	private List<String> insptItem;
	private List<String> insptRequirement;
	private String companyFlag;
	private String taskStatusNotZero;
	private String userId;
	private List<DistributeItem> distList = new ArrayList<DistributeItem>();
	private String personnels;
	private String monthValue;
	private String anyValue;
	
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	
	
	private String queryTimeStart;//查询时间起

	private String queryTimeEnd;//查询时间止
	
	@Autowired
	private InspectItemService inspectItemService;

	List<SafeInspectTjBean> datas = new ArrayList<SafeInspectTjBean>();
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		User user = this.getLoginUser();
//		CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
		CompanyBackUp company = companyService.getCompanyByLoginId( getLoginUser().getLoginId());
		if(company!=null){
			safeInspectDistribute.setCompanyFlag(company.getId());
		}
		    
		if(null != safeInspectDistribute){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safeInspectDistribute.getInspectType()) && (0 < safeInspectDistribute.getInspectType().trim().length())){
				paraMap.put("inspectType", "%" + safeInspectDistribute.getInspectType().trim() + "%");
			}

			if ((null != safeInspectDistribute.getTitle()) && (0 < safeInspectDistribute.getTitle().trim().length())){
				paraMap.put("title", "%" + safeInspectDistribute.getTitle().trim() + "%");
			}

			if ((null != safeInspectDistribute.getQuarters()) && (0 < safeInspectDistribute.getQuarters().trim().length())){
				paraMap.put("quarters", "%" + safeInspectDistribute.getQuarters().trim() + "%");
			}

			if ((null != safeInspectDistribute.getCycleFlag()) && (0 < safeInspectDistribute.getCycleFlag().trim().length())){
				paraMap.put("cycleFlag", "%" + safeInspectDistribute.getCycleFlag().trim() + "%");
			}

			if ((null != safeInspectDistribute.getCycleValue()) && (0 < safeInspectDistribute.getCycleValue().trim().length())){
				paraMap.put("cycleValue", "%" + safeInspectDistribute.getCycleValue().trim() + "%");
			}

			if ((null != safeInspectDistribute.getPersonnel()) && (0 < safeInspectDistribute.getPersonnel().trim().length())){
				paraMap.put("personnel", "%" + safeInspectDistribute.getPersonnel().trim() + "%");
			}

			if ((null != safeInspectDistribute.getCompanyFlag()) && (0 < safeInspectDistribute.getCompanyFlag().trim().length())){
				paraMap.put("companyFlag", "%" + safeInspectDistribute.getCompanyFlag().trim() + "%");
			}
			if ((null != safeInspectDistribute.getTaskStatus()) && (0 < safeInspectDistribute.getTaskStatus().trim().length())){
				paraMap.put("taskStatus", "0");
			}
			if ((null != taskStatusNotZero) && (0 < taskStatusNotZero.trim().length())){
				paraMap.put("taskStatusNotZero", "%0%");
			}
			if ((null != safeInspectDistribute.getInspectNum()) && (0 < safeInspectDistribute.getInspectNum().trim().length())){
				paraMap.put("inspectNum", safeInspectDistribute.getInspectNum().trim());
			}
			paraMap.put("personnelDeptCode", this.getLoginUser().getDeptCode()+"%");
			
			if (null != queryTimeStart){
				paraMap.put("queryTimeStart", queryTimeStart);
			}

			if (null != queryTimeEnd){
				paraMap.put("queryTimeEnd", queryTimeEnd);
			}
		}
		//如果该用户角色仅为“岗位人员”
		List<UserRight> userRightList = this.getLoginUser().getUserRoles();
		if(null != userRightList && userRightList.size() == 1){
			String rolecode = userRightList.get(0).getRole().getRoleCode();
			if(rolecode.equals("A37")){
				paraMap.put("personnel", this.getLoginUser().getId());
			}
		}
		
		
		pagination = safeInspectDistributeService.findByPage(pagination, paraMap);
		if ((null != taskStatusNotZero) && (0 < taskStatusNotZero.trim().length())){
			List tempList = pagination.getList();
			for(int i=0; i<tempList.size();i++){
				SafeInspectDistribute sd = (SafeInspectDistribute)tempList.get(i);
				SafeInspectDistribute tempSd = safeInspectDistributeService.getById(sd.getRootId());
				sd.setInspectNum(tempSd.getInspectNum());
			}
		}
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		User user = this.getLoginUser();
		CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
		this.companyFlag = company.getId();
		
		if((null != safeInspectDistribute)&&(null != safeInspectDistribute.getId()))
			safeInspectDistribute = safeInspectDistributeService.getById(safeInspectDistribute.getId());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("distributeId", safeInspectDistribute.getId());
		distList = distributeItemService.findDistributeItem(paraMap);
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
			safeInspectDistribute.setDeptId(this.getLoginUserDepartmentId());
			safeInspectDistribute.setDelFlag(0);
			
			if("any".equals(anyValue)){
				safeInspectDistribute.setCycleValue("any");
			}else if(null != safeInspectDistribute.getCycleFlag() && "2".equals(safeInspectDistribute.getCycleFlag())){
				safeInspectDistribute.setCycleValue(monthValue);
			}
			//safeInspectDistribute.setTaskStatus("0");//任务状态：0=初始化任务，1=某天某人安全检查任务任务
			safeInspectDistribute.setCount("0");
			safeInspectDistribute.setPersonnelDeptCode(this.getLoginUser().getDeptCode());
			safeInspectDistributeService.save(safeInspectDistribute);
			if(null != insptItem && !insptItem.isEmpty()){
				for(int i=0; i<insptItem.size(); i++){
					DistributeItem di = new DistributeItem();
					di.setIsinspect("0");//检查状态标识:0：初始化 1：未检查   2符合  3：不符合
					di.setDelFlag(0);
					di.setCreateTime(new Date());
					di.setItem(insptItem.get(i));
					di.setRequirement(insptRequirement.get(i));
					di.setDistributeId(safeInspectDistribute.getId());
					di.setIndexNum(i+1);
					distributeItemService.save(di);
					
				}
			}
			addNewTask(safeInspectDistribute);
		}else{
			safeInspectDistribute.setDeptId(this.getLoginUserDepartmentId());
			safeInspectDistribute.setDelFlag(0);

			if("any".equals(anyValue)){
				safeInspectDistribute.setCycleValue("any");
			}else if(null != safeInspectDistribute.getCycleFlag() && "2".equals(safeInspectDistribute.getCycleFlag())){
				safeInspectDistribute.setCycleValue(monthValue);
			}
			//safeInspectDistribute.setTaskStatus("0");//任务状态：0=初始化任务，1=某天某人安全检查任务任务
			safeInspectDistribute.setCount("0");
			safeInspectDistribute.setPersonnelDeptCode(this.getLoginUser().getDeptCode());
			safeInspectDistributeService.update(safeInspectDistribute);
			if(null != insptItem && !insptItem.isEmpty()){
				
				//先查出原有检查项数目把逻辑删除的也包含在内 GY-UPDATE 2015-01-07
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("distributeId", safeInspectDistribute.getId());
				distList = distributeItemService.findAllDistributeItem(paraMap);
				
				for(int i=0; i<insptItem.size(); i++){
					DistributeItem di = new DistributeItem();
					di.setIsinspect("0");//检查状态标识:0：初始化 1：未检查   2符合  3：不符合
					di.setDelFlag(0);
					di.setCreateTime(new Date());
					di.setItem(insptItem.get(i));
					di.setRequirement(insptRequirement.get(i));
					di.setDistributeId(safeInspectDistribute.getId());
					di.setIndexNum(distList.size()+1+i);
					distributeItemService.save(di);
				}	
			}
			//deleteLastTaskAndAdd(safeInspectDistribute);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			safeInspectDistributeService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	private void deleteLastTaskAndAdd(SafeInspectDistribute sd){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		String ds = df.format(new Date());
		
		//删除当天的该任务
		paraMap.put("rootId", sd.getId());
		paraMap.put("taskTime", ds);
		pagination = safeInspectDistributeService.findByPage(pagination, paraMap);
		List tempList = pagination.getList();
		for(int i=0; i<tempList.size();i++){
			SafeInspectDistribute tsd = (SafeInspectDistribute)tempList.get(i);
			safeInspectDistributeService.deleteWithFlag(tsd.getId());
		}
		addNewTask(safeInspectDistribute);
	}
	private void addNewTask(SafeInspectDistribute sd){
		String[] userIdArray = sd.getPersonnel().split("\\,");
		int inspectNum = Integer.parseInt(sd.getInspectNum());//每天检查次数
		
		Map<String, Object> distParaMap = new HashMap<String, Object>();
		distParaMap.put("distributeId", sd.getId());
		distParaMap.put("isinspect", "0");
		List<DistributeItem> distList = distributeItemService.findDistributeItem(distParaMap);
		
		for(int i=0; i<userIdArray.length; i++){//循环检查人员
			String personnelDeptCode="";
			personnelDeptCode = userService.findUserById(userIdArray[i].trim()).getDeptCode();
			String serialNum = getSerialNum();
			for(int k=1; k<=inspectNum; k++){//循环检查次数
				SafeInspectDistribute tempSid = new SafeInspectDistribute();
				tempSid.setTaskStatus("1");			//0=初始化任务，1=某天某人未检查任务
				tempSid.setCreateTime(new Date());
				tempSid.setDeptId(sd.getDeptId());
				tempSid.setDelFlag(0);
				tempSid.setInspectType(sd.getInspectType());
				tempSid.setTitle(sd.getTitle());
				tempSid.setQuarters(sd.getQuarters());
				tempSid.setCycleFlag(sd.getCycleFlag());
				tempSid.setCycleValue(sd.getCycleValue());
				tempSid.setPersonnel(userIdArray[i].trim());
				tempSid.setCompanyFlag(sd.getCompanyFlag());
				tempSid.setRootId(sd.getId());
				tempSid.setTaskTime(df.format(new Date()));
				tempSid.setCount("0");
				tempSid.setInspectNum(k+"");//检查次数
				tempSid.setPersonnelDeptCode(personnelDeptCode);
				tempSid.setSerialNum(serialNum);
				
				safeInspectDistributeService.save(tempSid);
			
				for (DistributeItem di : distList) {
					DistributeItem tempDi = new DistributeItem();
					tempDi.setDelFlag(0);
					tempDi.setCreateTime(new Date());
					tempDi.setDistributeId(tempSid.getId());//某人某天检查任务所对应的ID
					tempDi.setItem(di.getItem());
					tempDi.setRequirement(di.getRequirement());
					tempDi.setIsinspect("1");//检查状态标识:0：初始化 1：未检查   2符合  3：不符合
					tempDi.setImage(di.getImage());
					tempDi.setCount(di.getCount());
					tempDi.setRemark(di.getRemark());
					tempDi.setUserId(userIdArray[i].trim());
					tempDi.setTaskTime(df.format(new Date()));
					tempDi.setIndexNum(di.getIndexNum());
					
					distributeItemService.save(tempDi);
				}
			}
		}
	}
	private String getSerialNum(){
		String msn = safeInspectDistributeService.findMaxSerialNum();
		String headStr = msn.substring(0, 8);
		int tempnum = Integer.parseInt(msn.substring(9, 17));
		String numStr = ++tempnum +"";
		for(int i=0; i<9-numStr.length(); i++){
			headStr+="0";
		}
		headStr +=numStr;
		return headStr ;
	}
	/**
	 * 
	 */
	 public void findChildDeptAndUser()
	  {
	    List items = new ArrayList();

	    getChildDeptAndUser(deptCode, items);

	    List allItem = new ArrayList();
	    Map item = new HashMap();
	    item.put("id", "");
	    item.put("text", deptService.findDeptByDeptCode(deptCode).getDeptName());
	    item.put("children", items);

	    List attrList = new ArrayList();
	    Map attr = new HashMap();
	    attr.put("dept", "1");

	    attrList.add(attr);

	    item.put("attributes", attrList);

	    allItem.add(item);

	    JSONArray json = JSONArray.fromObject(allItem);
	    try {
	      HttpServletResponse response = 
	        ServletActionContext.getResponse();

	      response.setContentType("application/json;charset=UTF-8");
	      response.setCharacterEncoding("utf-8");
	      response.setHeader("Charset", "utf-8");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().println(json.toString());
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	 
	 private void getChildDeptAndUser(String deptCode, List<Map<String, Object>> items)
	  {
		// deptCode = "002009002002001";
	    if (deptCode != null) {
	      List<Department> deptList = this.deptService.findDeptByParentDeptCode(deptCode);
	      List attrList;
	      for (Department d : deptList) {
	        Map item = new HashMap();
	        item.put("id", d.getDeptCode());
	        item.put("text", d.getDeptName());
	        attrList = new ArrayList();
	        Map attr = new HashMap();
	        attr.put("dept", "1");
	        item.put("state", "closed");
	        attrList.add(attr);

	        item.put("attributes", attrList);

	        List itemList = new ArrayList();
	        getChildDeptAndUser(d.getDeptCode(), itemList);
	        item.put("children", itemList);
	        items.add(item);
	      }

	      if (deptCode != "") {
	        Map paraMap = new HashMap();
	        paraMap.put("deptCode", deptCode);
	        paraMap.put("notadmin", "1");
	        List<User> findUser = this.userService.getUsersByDept(deptCode);
	        
	        if(null!=personnels && personnels.length()>0){
	        	String[] personnelArr = personnels.split(",");
	        	for (User user : findUser) {
	  	          Map item = new HashMap();
	  	          item.put("id", user.getId());
	  	          item.put("text", user.getDisplayName());
	  	          
		  	        for(String id : personnelArr){
					    if(id!=null && !id.trim().equals("")&&id.equals(user.getId()))
					    	item.put("checked", true);
					}
	  	          
	  	          items.add(item);
	  	        }
	        }else{
	        	for (User user : findUser) {
	  	          Map item = new HashMap();
	  	          item.put("id", user.getId());
	  	          item.put("text", user.getDisplayName());
	  	          items.add(item);
	  	        }
	        }
	        
	        
	      }
	    }
	  }
	
	 public void findChildDept()
	  {
	    try
	    {
	      Map root;
	      if (this.selDept == null || this.selDept.length()==0)
	      {
	        User user = getLoginUser();

	        List items = new ArrayList();
	        root = new HashMap();
	        Map item;
	        if ((user.getIsSuperAdmin() != null) && (user.getIsSuperAdmin().booleanValue())) {
	          root.put("id", "");
	          root.put("text", "组织机构");
	          root.put("state", "opened");
	          List<Department> deptList = this.deptService.findDeptByParentDeptCode("");
	          List elements = new ArrayList();
	          for (Department d : deptList) {
	            item = new HashMap();
	            item.put("id", d.getDeptCode());
	            item.put("text", d.getDeptName());

	            if ((this.deptService.findDeptByParentDeptCode(
	              d.getDeptCode()).size() > 0) || (this.deptService.findLinkedDpet(d).size() > 0)) {
	              item.put("state", "closed");
	            }
	            elements.add(item);
	          }
	          root.put("children", elements);
	          items.add(root);
	        } else {
	          Department independenceDept = this.deptService
	            .findIndependenceDept(user.getDeptCode());

	          root.put("id", independenceDept.getDeptCode());
	          root.put("text", independenceDept.getDeptName());
	          root.put("state", "opened");
	          List<Department> deptList = this.deptService
	            .findDeptByParentDeptCode(independenceDept
	            .getDeptCode());
	          List elements = new ArrayList();
	          Map itemLoc;
	          if (deptList.size() > 0) {
	            for (Department d : deptList) {
	            	itemLoc = new HashMap();
	            	itemLoc.put("id", d.getDeptCode());
	            	itemLoc.put("text", d.getDeptName());

	              if ((this.deptService.findDeptByParentDeptCode(
	                d.getDeptCode()).size() > 0) || (this.deptService.findLinkedDpet(d).size() > 0)) {
	            	  itemLoc.put("state", "closed");
	              }
	              elements.add(itemLoc);
	            }
	          } else {
	            List<Department> linkedDeptList = this.deptService.findLinkedDpet(independenceDept);
	            if (linkedDeptList.size() > 0) {
	              for (Department d : linkedDeptList) {
	                Map itemLocale = new HashMap();
	                itemLocale.put("id", d.getDeptCode());
	                itemLocale.put("text", d.getDeptName());

	                if ((this.deptService.findDeptByParentDeptCode(
	                  d.getDeptCode()).size() > 0) || (this.deptService.findLinkedDpet(d).size() > 0)) {
	                	itemLocale.put("state", "closed");
	                }
	                elements.add(itemLocale);
	              }
	            }
	          }

	          root.put("children", elements);
	          items.add(root);
	        }
	        JSONArray json = JSONArray.fromObject(items);
	        HttpServletResponse response = 
	          ServletActionContext.getResponse();
	        response.setContentType("application/json;charset=UTF-8");
	        response.setCharacterEncoding("utf-8");
	        response.setHeader("Charset", "utf-8");
	        response.setHeader("Cache-Control", "no-cache");
	        response.getWriter().print(json.toString());
	      } else {
	        this.deptList = this.deptService.findDeptByParentDeptCode(this.selDept);
	        List items = new ArrayList();
	        for (Department d : this.deptList) {
	          Map item = new HashMap();
	          item.put("id", d.getDeptCode());
	          item.put("text", d.getDeptName());

	          if ((this.deptService.findDeptByParentDeptCode(d.getDeptCode())
	            .size() > 0) || (this.deptService.findLinkedDpet(d).size() > 0)) {
	            item.put("state", "closed");
	          }
	          items.add(item);
	        }
	        this.dept = this.deptService.findDeptByDeptCode(this.selDept);
	        this.deptList = this.deptService.findLinkedDpet(this.dept);
	        for (Department d : this.deptList) {
	          Map item = new HashMap();
	          item.put("id", d.getDeptCode());
	          StringBuffer name = new StringBuffer("<font color='red'>");
	          if (d.getParentDept() != null) {
	            name.append(d.getParentDept().getDeptName()).append("-");
	          }
	          name.append(d.getDeptName()).append("</font>");
	          item.put("text", name.toString());

	          if ((this.deptService.findDeptByParentDeptCode(d.getDeptCode())
	            .size() > 0) || (this.deptService.findLinkedDpet(d).size() > 0)) {
	            item.put("state", "closed");
	          }
	          items.add(item);
	        }
	        JSONArray json = JSONArray.fromObject(items);
	        HttpServletResponse response = 
	          ServletActionContext.getResponse();
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
	public void findDeptnameByDeptcode(){
		try {
			String deptName = deptService.findDeptByDeptCode(deptCode).getDeptName();
			this.getResponse().getWriter().println("{\"deptName\":\""+deptName+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void findUsernameById(){
		try {
			User user = (User)userService.findUserById(userId);
			this.getResponse().getWriter().println("{\"userName\":\""+user.getDisplayName()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getSuggestion() throws IOException {
		String inspectItem = getRequest().getParameter("inspectItem");// 得到输入的值
		String inspecttype = getRequest().getParameter("inspecttype");//检查类型
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("inspectType", inspecttype.trim());
		paraMap.put("item", "%" + inspectItem.trim() + "%");
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		pagination.setPageSize(1000);
		pagination = inspectItemService.findByPage(pagination, paraMap);
		List<InspectItem> itemList = pagination.getListOfObject();
		JSONArray jarray = new JSONArray();// 下面是组装成json格式
		for (int i = 0; i < itemList.size(); i++) {
			JSONObject jo = new JSONObject();
			InspectItem obj = (InspectItem) itemList.get(i);
			jo.put("id", obj.getId());
			jo.put("item", obj.getItem());
			jo.put("requirement", obj.getRequirement());
			jarray.add(jo);
		}
		getResponse().getWriter().print(jarray);// 送回客户端
		
		return NONE;
	}
	@SuppressWarnings("unchecked")
	public String safeInspectTongJi(){
		try {
				Map map = new HashMap();
				datas = safeInspectDistributeService.getTongJiSafeInspect(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "tongji";
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

	public SafeInspectDistribute getSafeInspectDistribute(){
		return this.safeInspectDistribute;
	}

	public void setSafeInspectDistribute(SafeInspectDistribute safeInspectDistribute){
		this.safeInspectDistribute = safeInspectDistribute;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
    

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getSelDept() {
	    return this.selDept;
	  }
	
	  public void setSelDept(String selDept) {
	    this.selDept = selDept;
	  }   
	public List<Department> getDeptList() {
		return this.deptList;
	}

	public void setDeptList(List<Department> deptList) {
	   this.deptList = deptList;
	}
	public Department getDept()
	{
	    return this.dept;
	}

	public void setDept(Department dept)
	{
	    this.dept = dept;
	}
	
    public List<String> getInsptItem() {
		return insptItem;
	}

	public void setInsptItem(List<String> insptItem) {
		this.insptItem = insptItem;
	}

	public List<String> getInsptRequirement() {
		return insptRequirement;
	}

	public void setInsptRequirement(List<String> insptRequirement) {
		this.insptRequirement = insptRequirement;
	}

	public String getCompanyFlag() {
		return companyFlag;
	}

	public void setCompanyFlag(String companyFlag) {
		this.companyFlag = companyFlag;
	}

	public String getTaskStatusNotZero() {
		return taskStatusNotZero;
	}

	public void setTaskStatusNotZero(String taskStatusNotZero) {
		this.taskStatusNotZero = taskStatusNotZero;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<DistributeItem> getDistList() {
		return distList;
	}

	public void setDistList(List<DistributeItem> distList) {
		this.distList = distList;
	}

	public String getPersonnels() {
		return personnels;
	}

	public void setPersonnels(String personnels) {
		this.personnels = personnels;
	}

	public String getQueryTimeStart()
	{
		return queryTimeStart;
	}

	public void setQueryTimeStart(String queryTimeStart)
	{
		this.queryTimeStart = queryTimeStart;
	}

	public String getQueryTimeEnd()
	{
		return queryTimeEnd;
	}

	public void setQueryTimeEnd(String queryTimeEnd)
	{
		this.queryTimeEnd = queryTimeEnd;
	}

	public String getMonthValue() {
		return monthValue;
	}

	public void setMonthValue(String monthValue) {
		this.monthValue = monthValue;
	}

	public String getAnyValue() {
		return anyValue;
	}

	public void setAnyValue(String anyValue) {
		this.anyValue = anyValue;
	}

	public List<SafeInspectTjBean> getDatas() {
		return datas;
	}

	public void setDatas(List<SafeInspectTjBean> datas) {
		this.datas = datas;
	}

    
}
