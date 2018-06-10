package com.jshx.qyzyfzrpx.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

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
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.service.MaiChaTraService;
import com.lkdj.kzk.GetTkzxxRequest;
import com.lkdj.kzk.GetTkzxxResponse;
import com.lkdj.kzk.TkzxxPort;
import com.lkdj.kzk.TkzxxPortService;
import com.lkdj.kzk.GetTkzxxRequest.Tkzxxs;
import com.lkdj.lkLog.entity.LkLog;
import com.lkdj.lkLog.service.LkLogService;
import com.lkdj.rkk.GetLkRKRequest;
import com.lkdj.util.ceateLkRKUtil;
import com.lkdj.util.ceateTkzxxUtil;

public class MaiChaTraAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private MaiChaTra maiChaTra = new MaiChaTra();

	/**
	 * 业务类
	 */
	@Autowired
	private MaiChaTraService maiChaTraService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryTrainingTimeStart;

	private Date queryTrainingTimeEnd;

	private Date queryTrainingTimeEndStart;

	private Date queryTrainingTimeEndEnd;

	
	private List<EntChaPer> zyfzr=new ArrayList<EntChaPer>();
	@Autowired
	private HttpService httpService;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	@Autowired
	private LkLogService lkLogService;
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	
	/**
	 * 执行查询的方法，返回json数据
	 */
	
	/**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
				break;
			}
		}
		return SUCCESS;
	}
	
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != maiChaTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != maiChaTra.getAreaId()) && (0 < maiChaTra.getAreaId().trim().length())){
				paraMap.put("areaId",maiChaTra.getAreaId().trim());
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != maiChaTra.getTrainingPersonName()) && (0 < maiChaTra.getTrainingPersonName().trim().length())){
				paraMap.put("trainingPersonName", "%" + maiChaTra.getTrainingPersonName().trim() + "%");
			}
			
			if ((null != maiChaTra.getTrainingCardnum()) && (0 < maiChaTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + maiChaTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != maiChaTra.getCompanyName()) && (0 < maiChaTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + maiChaTra.getCompanyName().trim() + "%");
			}
			if ((null != maiChaTra.getCompanyId()) && (0 < maiChaTra.getCompanyId().trim().length())){
				paraMap.put("companyId",  maiChaTra.getCompanyId().trim()  );
			}
			
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingPersonName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination = maiChaTraService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != maiChaTra)&&(null != maiChaTra.getId()))
		{
			maiChaTra = maiChaTraService.getById(maiChaTra.getId());
			if(maiChaTra.getLinkId() == null || "".equals(maiChaTra.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					maiChaTra.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",maiChaTra.getLinkId());
					map.put("mkType", "qyzyfzrpx");
					map.put("picType","pxfj");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				maiChaTra.setLinkId(linkId);
			}

		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		maiChaTra.setCompanyId(enBaseInfo.getId());
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
		maiChaTra.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		maiChaTra.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		maiChaTra.setCompanyId(enBaseInfo.getId());
		maiChaTra.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			maiChaTra.setDeptId(this.getLoginUserDepartmentId());
			maiChaTra.setDelFlag(0);
			
			maiChaTraService.save(maiChaTra);
		}else{
			maiChaTraService.update(maiChaTra);
		}

		try {
			String zjhm = NullToString(maiChaTra.getTrainingCardnum()).replaceAll(" ", "");
			if(zjhm != null && !"".equals(zjhm))
			{
				LkLog log1 = new LkLog();
				log1.setNrid(maiChaTra.getId());
				log1.setLb("maiChaTra扩展信息");
				//人口扩展数据
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				JSONArray ja = new JSONArray();
				String trainingTime = maiChaTra.getTrainingTime() != null?new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingTime()):"";
				String trainingTimeEnd = maiChaTra.getTrainingTimeEnd() != null?new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingTimeEnd()):"";
				String trainingCardPickDate = maiChaTra.getTrainingCardPickDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingCardPickDate()):"";
				String trainingCardValidity = maiChaTra.getTrainingCardValidity() != null?new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingCardValidity()):"";
				ja.add((new JSONObject()).put("QYMC", NullToString(maiChaTra.getCompanyName())));//企业名称
				ja.add((new JSONObject()).put("ZYFZRXM", NullToString(maiChaTra.getTrainingPersonName()) ));//主要负责人姓名
				ja.add((new JSONObject()).put("PXKSSJ" , trainingTime));//培训开始时间
				ja.add((new JSONObject()).put("PXJSSJ" , trainingTimeEnd ));//培训结束时间
				ja.add((new JSONObject()).put("PXDW" , NullToString(maiChaTra.getTrainingAddress())));//培训地点
				ja.add((new JSONObject()).put("PXXS" , NullToString(maiChaTra.getTrainingTeacheTime())));//培训学时
				ja.add((new JSONObject()).put("SKR" , NullToString(maiChaTra.getTrainingTeacher())  ));//授课人
				ja.add((new JSONObject()).put("PXLR" , NullToString(maiChaTra.getTrainingContent())));//培训内容
				ja.add((new JSONObject()).put("ZSHM" , NullToString(maiChaTra.getTrainingCardnum())  ));//证书号码
				ja.add((new JSONObject()).put("ZSFZSJ" ,trainingCardPickDate ));//证书发证日期
				ja.add((new JSONObject()).put("YXQ" , trainingCardValidity  ));//有效期
				ja.add((new JSONObject()).put("FZDW" , NullToString(maiChaTra.getFzdw())));//发证单位
				JSONObject jo = new JSONObject();
				jo.put("rkkzxx", ja.toString());
				String kzxx = jo.toString();
				Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("qyzyfzrpxb",maiChaTra.getCreateTime(), kzxx, zjhm, "");
				kzqq.getTkzxxs().add(tkzxxs);
				
				TkzxxPortService  tkzxxPortService= new TkzxxPortService();
				tkzxxPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
				GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
				System.out.println("主要负责人培训信息"+getTkzxxResponse.getMsg());
				log1.setResult(getTkzxxResponse.getMsg());
				lkLogService.save(log1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != maiChaTra)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到maiChaTra中去
				
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
			maiChaTraService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String zwtInit(){
		
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}


	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != maiChaTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != maiChaTra.getAreaId()) && (0 < maiChaTra.getAreaId().trim().length())){
				paraMap.put("areaId",  maiChaTra.getAreaId().trim() );
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != maiChaTra.getTrainingPersonName()) && (0 < maiChaTra.getTrainingPersonName().trim().length())){
				paraMap.put("trainingPersonName", "%" + maiChaTra.getTrainingPersonName().trim() + "%");
			}
			
			if ((null != maiChaTra.getTrainingCardnum()) && (0 < maiChaTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + maiChaTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != maiChaTra.getCompanyName()) && (0 < maiChaTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + maiChaTra.getCompanyName().trim() + "%");
			}

		}

		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、姓名或证书号码".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingPersonName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = maiChaTraService.findByPage(pagination, paraMap);
		
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

	public MaiChaTra getMaiChaTra(){
		return this.maiChaTra;
	}

	public void setMaiChaTra(MaiChaTra maiChaTra){
		this.maiChaTra = maiChaTra;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTrainingTimeStart(){
		return this.queryTrainingTimeStart;
	}

	public void setQueryTrainingTimeStart(Date queryTrainingTimeStart){
		this.queryTrainingTimeStart = queryTrainingTimeStart;
	}

	public Date getQueryTrainingTimeEnd(){
		return this.queryTrainingTimeEnd;
	}

	public void setQueryTrainingTimeEnd(Date queryTrainingTimeEnd){
		this.queryTrainingTimeEnd = queryTrainingTimeEnd;
	}


	public Date getQueryTrainingTimeEndStart() {
		return queryTrainingTimeEndStart;
	}

	public void setQueryTrainingTimeEndStart(Date queryTrainingTimeEndStart) {
		this.queryTrainingTimeEndStart = queryTrainingTimeEndStart;
	}

	public Date getQueryTrainingTimeEndEnd() {
		return queryTrainingTimeEndEnd;
	}

	public void setQueryTrainingTimeEndEnd(Date queryTrainingTimeEndEnd) {
		this.queryTrainingTimeEndEnd = queryTrainingTimeEndEnd;
	}

	public List<EntChaPer> getZyfzr() {
		return zyfzr;
	}

	public void setZyfzr(List<EntChaPer> zyfzr) {
		this.zyfzr = zyfzr;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
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
	
	public void sendInfo()
	{
		try {
			//对接企业主要负责人培训表
			List<MaiChaTra> fzrpxlist = maiChaTraService.findMaiChaTra(null);
			int total8 = fzrpxlist.size();
			int num8 = total8/100;
			int ys8 = total8%100;
			if(ys8 != 0)
			{
				num8 = num8 + 1;
			}
			for(int i=0;i<num8;i++)
			{
				int start = 100*i;
				int end = 100*(i+1);
				if(end > total8)
				{
					end = total8;
				}
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				for(int j=start;j<end;j++)
				{
					MaiChaTra zyfzrpx = fzrpxlist.get(j);
					String zjhm = NullToString(zyfzrpx.getTrainingCardnum()).replaceAll(" ", "");
					if(zjhm != null && !"".equals(zjhm))
					{
						//人口扩展数据
						JSONArray ja = new JSONArray();
						String trainingTime = zyfzrpx.getTrainingTime() != null?new SimpleDateFormat("yyyy-MM-dd").format(zyfzrpx.getTrainingTime()):"";
						String trainingTimeEnd = zyfzrpx.getTrainingTimeEnd() != null?new SimpleDateFormat("yyyy-MM-dd").format(zyfzrpx.getTrainingTimeEnd()):"";
						String trainingCardPickDate = zyfzrpx.getTrainingCardPickDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(zyfzrpx.getTrainingCardPickDate()):"";
						String trainingCardValidity = zyfzrpx.getTrainingCardValidity() != null?new SimpleDateFormat("yyyy-MM-dd").format(zyfzrpx.getTrainingCardValidity()):"";
						ja.add((new JSONObject()).put("QYMC", NullToString(zyfzrpx.getCompanyName())));//企业名称
						ja.add((new JSONObject()).put("ZYFZRXM", NullToString(zyfzrpx.getTrainingPersonName()) ));//主要负责人姓名
						ja.add((new JSONObject()).put("PXKSSJ" , trainingTime));//培训开始时间
						ja.add((new JSONObject()).put("PXJSSJ" , trainingTimeEnd ));//培训结束时间
						ja.add((new JSONObject()).put("PXDW" , NullToString(zyfzrpx.getTrainingAddress())));//培训地点
						ja.add((new JSONObject()).put("PXXS" , NullToString(zyfzrpx.getTrainingTeacheTime())));//培训学时
						ja.add((new JSONObject()).put("SKR" , NullToString(zyfzrpx.getTrainingTeacher())  ));//授课人
						ja.add((new JSONObject()).put("PXLR" , NullToString(zyfzrpx.getTrainingContent())));//培训内容
						ja.add((new JSONObject()).put("ZSHM" , NullToString(zyfzrpx.getTrainingCardnum())  ));//证书号码
						ja.add((new JSONObject()).put("ZSFZSJ" ,trainingCardPickDate ));//证书发证日期
						ja.add((new JSONObject()).put("YXQ" , trainingCardValidity  ));//有效期
						ja.add((new JSONObject()).put("FZDW" , NullToString(zyfzrpx.getFzdw())));//发证单位
						JSONObject jo = new JSONObject();
						jo.put("rkkzxx", ja.toString());
						String kzxx = jo.toString();
						Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("qyzyfzrpxb",zyfzrpx.getCreateTime(), kzxx, zjhm, "");
						kzqq.getTkzxxs().add(tkzxxs);
						
					}
				}
				TkzxxPortService  tkzxxPortService= new TkzxxPortService();
				tkzxxPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
				GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
				System.out.println("主要负责人培训信息"+getTkzxxResponse.getStatus());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//学历
	public String xl(String xl)
	{
		if(xl != null)
		{
			if(xl.equals("1"))//小学
			{
				return "81";
			}
			else if(xl.equals("2"))//初中
			{
				return "71";
			}
			else if(xl.equals("3"))//中专
			{
				return "41";
			}
			else if(xl.equals("4"))//高中
			{
				return "61";
			}
			else if(xl.equals("5"))//专科
			{
				return "31";
			}
			else if(xl.equals("6"))//本科
			{
				return "21";
			}
			else if(xl.equals("7"))//硕士研究生
			{
				return "14";
			}
			else if(xl.equals("8"))//博士研究生
			{
				return "11";
			}
		}
		return "99";
	}
       
	public String NullToString(String s)
	{
		if(s == null)
		{
			return "";
		}
		else
		{
			return s;
		}
	}
}
