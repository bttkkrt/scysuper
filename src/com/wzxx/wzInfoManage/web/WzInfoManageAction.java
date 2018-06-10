package com.wzxx.wzInfoManage.web;

import java.io.FileInputStream;
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
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.aqwh.service.SafCulService;
import com.wzxx.csgl.entity.CityMan;
import com.wzxx.csgl.service.CityManService;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;
import com.wzxx.jgdl.entity.Jgdl;
import com.wzxx.jgdl.service.JgdlService;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.tzgg.service.TzggService;
import com.wzxx.wzInfoManage.entity.WzInfoManage;
import com.wzxx.wzInfoManage.service.WzInfoManageService;
import com.wzxx.zhzf.entity.Zhzf;
import com.wzxx.zhzf.service.ZhzfService;

public class WzInfoManageAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private WzInfoManage wzInfoManage = new WzInfoManage();

	private List<Tzgg> tzggList = new ArrayList<Tzgg>();
	
	private List<Gzdt> gzdtList = new ArrayList<Gzdt>();
	
	private List<Map<String, Object>> wxhxpList = new ArrayList<Map<String, Object>>();//危险化学品
	
	
	public List<Tzgg> getTzggList() {
		return tzggList;
	}
	public void setTzggList(List<Tzgg> tzggList) {
		this.tzggList = tzggList;
	}
	public List<Gzdt> getGzdtList() {
		return gzdtList;
	}
	public void setGzdtList(List<Gzdt> gzdtList) {
		this.gzdtList = gzdtList;
	}
	public List<Map<String, Object>> getWxhxpList() {
		return wxhxpList;
	}
	public void setWxhxpList(List<Map<String, Object>> wxhxpList) {
		this.wxhxpList = wxhxpList;
	}
	@Autowired
	private HttpService httpService;
	
	/**
	 * 业务类
	 */
	@Autowired
	private WzInfoManageService wzInfoManageService;
	@Autowired
	private PhotoPicService photoPicService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private int pageNo=1;
	private int totalCount=0;
	private int totalPage=0;
	private List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private int infoType;
	private int keep;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private GzdtService gzdtService;
	
	@Autowired
	private TzggService tzggService;
	
	@Autowired
	private SafCulService safCulService;
	
	@Autowired
	private ZhzfService zhzfService;
	
	@Autowired
	private CityManService cityManService;
	
	@Autowired
	private JgdlService jgdlService;
	
	private String type;
	
	public String init(){
		if("8".equals(wzInfoManage.getInfoType())){
			return "xzxkgs";
		}else{
			return SUCCESS;
		}
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != wzInfoManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != wzInfoManage.getInfotitle()) && (0 < wzInfoManage.getInfotitle().trim().length())){
				paraMap.put("infotitle", "%" + wzInfoManage.getInfotitle().trim() + "%");
			}
			
			if ((null != wzInfoManage.getInfoType()) && (0 < wzInfoManage.getInfoType().trim().length())){
				paraMap.put("infoType",  wzInfoManage.getInfoType().trim());
			}
			
			if ((null != wzInfoManage.getCreateUserID()) && (0 < wzInfoManage.getCreateUserID().trim().length())){
				paraMap.put("createUserID",  "%" + wzInfoManage.getCreateUserID().trim()+ "%");
			}
			if ((null != wzInfoManage.getCompanyName()) && (0 < wzInfoManage.getCompanyName().trim().length())){
				paraMap.put("companyName",  "%" + wzInfoManage.getCompanyName().trim()+ "%");
			}
			if ((null != wzInfoManage.getCategory()) && (0 < wzInfoManage.getCategory().trim().length())){
				paraMap.put("category",  "%" + wzInfoManage.getCategory().trim()+ "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|infotitle|infoType|clickTime|user|user.displayName|createTime|updateTime|category|companyName|";
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
		pagination = wzInfoManageService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != wzInfoManage)&&(null != wzInfoManage.getId())){
			wzInfoManage = wzInfoManageService.getById(wzInfoManage.getId());
			if(wzInfoManage.getLinkId() == null || "".equals(wzInfoManage.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				wzInfoManage.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",wzInfoManage.getLinkId());
				map.put("mkType", "wzInfo");
				map.put("picType","wzInfofj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			wzInfoManage.setLinkId(linkId);
		}
		if("8".equals(wzInfoManage.getInfoType())){
			return "xzxkgs";
		}else{
			return VIEW;
		}
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		if("8".equals(wzInfoManage.getInfoType())){
			return "xzxkgs";
		}else{
			return EDIT;
		}
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			wzInfoManage.setDeptId(this.getLoginUserDepartmentId());
			wzInfoManage.setDelFlag(0);
			wzInfoManageService.save(wzInfoManage);
		}else{
			wzInfoManageService.update(wzInfoManage);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != wzInfoManage)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到wzInfoManage中去
				
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
			wzInfoManageService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String awhgzList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPageForCount");
		paraMap.put("infoType", wzInfoManage.getInfoType());
		totalCount=Integer.parseInt(httpService.findListDataByMap(paraMap).get(0).get("COU").toString());
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", pageNo);
		paraMap.put("limit", 10);
		mapList=httpService.findListDataByMap(paraMap);
		if(totalCount%10==0){
			totalPage=totalCount/10;
		}else{
			totalPage=totalCount/10+1;
		}
		
		if("1".equals(wzInfoManage.getInfoType())){
			return "awhgz";
		}else if("2".equals(wzInfoManage.getInfoType())){
			return "zwxx";
		}else if("3".equals(wzInfoManage.getInfoType())){
			return "zfjc";
		}else if("4".equals(wzInfoManage.getInfoType())){
			return "wxhxp";
		}else if("5".equals(wzInfoManage.getInfoType())){
			return "zyws";
		}else if("6".equals(wzInfoManage.getInfoType())){
			return "zzjg";
		}else if("7".equals(wzInfoManage.getInfoType())){
			return "zxzz";
		}else if("8".equals(wzInfoManage.getInfoType())){
			return "xzxkgs";
		}else if("9".equals(wzInfoManage.getInfoType())){
			return "bslc";
		}else{
			return null;
		}
	}
	
	public String viewContent() throws Exception{
		tzggList = tzggService.findAllInfo(null, 1, 7);
		for(Tzgg tzgg:tzggList)
		{
			
			String s = tzgg.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			tzgg.setInfoTitle(s);
		}
		gzdtList = gzdtService.findAllInfo(null, 1, 7);
		for(Gzdt gzdt:gzdtList)
		{
		
			String s = gzdt.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			gzdt.setInfoTitle(s);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", 1);
		paraMap.put("limit", 7);
		paraMap.put("infoType", "4");
		wxhxpList=httpService.findListDataByMap(paraMap);
		if((null != wzInfoManage)&&(null != wzInfoManage.getId())){
			wzInfoManage = wzInfoManageService.getById(wzInfoManage.getId());
			String content = wzInfoManage.getInfoContent();
			String sb = "";

			String[] s1 = content.split("style");

			for (int i = 0; i < s1.length; i++) {

				if (i==0) {

					sb = sb + s1[0];

					continue;

				}

				int ii = s1[i].indexOf(">", 0);

				if(ii != -1)
				{
					sb = sb + s1[i].substring(ii);
				}
				else
				{
					sb = sb + s1[i];
				}
			}
			
			wzInfoManage.setInfoContent(sb.toString());
			int t=wzInfoManage.getClickTime();
			wzInfoManage.setClickTime(t+1);
			wzInfoManageService.update(wzInfoManage);
			if(wzInfoManage.getLinkId() == null || "".equals(wzInfoManage.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				wzInfoManage.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",wzInfoManage.getLinkId());
				map.put("mkType", "wzInfo");
				map.put("picType","wzInfofj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
		}
		if("1".equals(wzInfoManage.getInfoType())){
			return "awhgz";
		}else if("2".equals(wzInfoManage.getInfoType())){
			return "zwxx";
		}else if("3".equals(wzInfoManage.getInfoType())){
			return "zfjc";
		}else if("4".equals(wzInfoManage.getInfoType())){
			return "wxhxp";
		}else if("5".equals(wzInfoManage.getInfoType())){
			return "zyws";
		}else if("6".equals(wzInfoManage.getInfoType())){
			return "zzjg";
		}else if("7".equals(wzInfoManage.getInfoType())){
			return "zxzz";
		}else if("8".equals(wzInfoManage.getInfoType())){
			return "xzxkgs";
		}else if("9".equals(wzInfoManage.getInfoType())){
			return "bslc";
		}else{
			return null;
		}
	}
	
	/**
	 * 移动模块信息
	 * fq 2016-2-15
	 * type=0,主体是wzInfoManage，type=1 通知公告,type=2 工作动态,type=3 安全文化
	 */
	public String move() throws Exception{
		return "move";
	}
	
	/**
	 * 保存移动信息 
	 * fq 2016-2-15
	 * infoType 10:通知公告, 11:工作动态, 12:安全文化
	 * type=0,主体是wzInfoManage，type=1 通知公告,type=2 工作动态,type=3 安全文化
	 */
	public String saveMove() throws Exception{
		if("0".equals(type)){
			wzInfoManage=wzInfoManageService.getById(ids);
			if(infoType<10){
				if(keep==0){
					WzInfoManage w=new WzInfoManage();
					w.setInfoType(String.valueOf(infoType));
					w.setCompanyName(wzInfoManage.getCompanyName());
					w.setCreateTime(wzInfoManage.getCreateTime());
					w.setCreateUserID(wzInfoManage.getCreateUserID());
					w.setDelFlag(wzInfoManage.getDelFlag());
					w.setDeptId(wzInfoManage.getDeptId());
					w.setInfoContent(wzInfoManage.getInfoContent());
					w.setInfotitle(wzInfoManage.getInfotitle());
					w.setUser(wzInfoManage.getUser());
					w.setLinkId(wzInfoManage.getLinkId());
					w.setFirTitle(wzInfoManage.getFirTitle());
					wzInfoManageService.save(w);
				}else{
					wzInfoManage.setInfoType(String.valueOf(infoType));
					wzInfoManageService.update(wzInfoManage);
				}
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(wzInfoManage.getInfoContent());
				tzgg.setInfoTitle(wzInfoManage.getInfotitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(wzInfoManage.getLinkId());
				tzgg.setFirTitle(wzInfoManage.getFirTitle());
				tzggService.save(tzgg);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(wzInfoManage.getInfotitle());
				gzdt.setInfoContent(wzInfoManage.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(wzInfoManage.getLinkId());
				gzdt.setFirTitle(wzInfoManage.getFirTitle());
				gzdtService.save(gzdt);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
				
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(wzInfoManage.getInfoContent());
				safCul.setInfoTitle(wzInfoManage.getInfotitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(wzInfoManage.getLinkId());
				safCul.setFirTitle(wzInfoManage.getFirTitle());
				safCulService.save(safCul);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
			}
			else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(wzInfoManage.getInfoContent());
				zhzf.setInfoTitle(wzInfoManage.getInfotitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(wzInfoManage.getLinkId());
				zhzf.setFirTitle(wzInfoManage.getFirTitle());
				zhzfService.save(zhzf);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(wzInfoManage.getInfoContent());
				cityMan.setInfoTitle(wzInfoManage.getInfotitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(wzInfoManage.getLinkId());
				cityMan.setFirTitle(wzInfoManage.getFirTitle());
				cityManService.save(cityMan);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(wzInfoManage.getInfoContent());
				jgdl.setInfoTitle(wzInfoManage.getInfotitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(wzInfoManage.getLinkId());
				jgdl.setFirTitle(wzInfoManage.getFirTitle());
				jgdlService.save(jgdl);
				if(keep==1){
					wzInfoManage.setDelFlag(1);
					wzInfoManageService.update(wzInfoManage);
				}
			}
		}else if("1".equals(type)){
			Tzgg tzgg = tzggService.getById(ids);
			if(keep==1){
				tzgg.setDelFlag("1");
				tzggService.update(tzgg);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(tzgg.getCreateTime());
				w.setCreateUserID(tzgg.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(tzgg.getDeptId());
				w.setInfoContent(tzgg.getInfoContent());
				w.setInfotitle(tzgg.getInfoTitle());
				w.setUser(tzgg.getUser());
				w.setLinkId(tzgg.getLinkId());
				w.setFirTitle(tzgg.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==10){
				tzgg.setDelFlag("0");
				tzggService.update(tzgg);
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(tzgg.getInfoTitle());
				gzdt.setInfoContent(tzgg.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(tzgg.getLinkId());
				gzdt.setFirTitle(tzgg.getFirTitle());
				gzdtService.save(gzdt);
				
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(tzgg.getInfoContent());
				safCul.setInfoTitle(tzgg.getInfoTitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(tzgg.getLinkId());
				safCul.setFirTitle(tzgg.getFirTitle());
				safCulService.save(safCul);
			}else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(tzgg.getInfoContent());
				zhzf.setInfoTitle(tzgg.getInfoTitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(tzgg.getLinkId());
				zhzf.setFirTitle(tzgg.getFirTitle());
				zhzfService.save(zhzf);
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(tzgg.getInfoContent());
				cityMan.setInfoTitle(tzgg.getInfoTitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(tzgg.getLinkId());
				cityMan.setFirTitle(tzgg.getFirTitle());
				cityManService.save(cityMan);
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(tzgg.getInfoContent());
				jgdl.setInfoTitle(tzgg.getInfoTitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(tzgg.getLinkId());
				jgdl.setFirTitle(tzgg.getFirTitle());
				jgdlService.save(jgdl);
			}
		}else if("2".equals(type)){
			Gzdt gzdt = gzdtService.getById(ids);
			if(keep==1){
				gzdt.setDelFlag("1");
				gzdtService.update(gzdt);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(gzdt.getCreateTime());
				w.setCreateUserID(gzdt.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(gzdt.getDeptId());
				w.setInfoContent(gzdt.getInfoContent());
				w.setInfotitle(gzdt.getInfoTitle());
				w.setUser(gzdt.getUser());
				w.setLinkId(gzdt.getLinkId());
				w.setFirTitle(gzdt.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==11){
				gzdt.setDelFlag("0");
				gzdtService.update(gzdt);
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(gzdt.getInfoContent());
				tzgg.setInfoTitle(gzdt.getInfoTitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(gzdt.getLinkId());
				tzgg.setFirTitle(gzdt.getFirTitle());
				tzggService.save(tzgg);
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(gzdt.getInfoContent());
				safCul.setInfoTitle(gzdt.getInfoTitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(gzdt.getLinkId());
				safCul.setFirTitle(gzdt.getFirTitle());
				safCulService.save(safCul);
			}else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(gzdt.getInfoContent());
				zhzf.setInfoTitle(gzdt.getInfoTitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(gzdt.getLinkId());
				zhzf.setFirTitle(gzdt.getFirTitle());
				zhzfService.save(zhzf);
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(gzdt.getInfoContent());
				cityMan.setInfoTitle(gzdt.getInfoTitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(gzdt.getLinkId());
				cityMan.setFirTitle(gzdt.getFirTitle());
				cityManService.save(cityMan);
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(gzdt.getInfoContent());
				jgdl.setInfoTitle(gzdt.getInfoTitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(gzdt.getLinkId());
				jgdl.setFirTitle(gzdt.getFirTitle());
				jgdlService.save(jgdl);
			}
		}else if("3".equals(type)){
			SafCul safCul = safCulService.getById(ids);
			if(keep==1){
				safCul.setDelFlag(1);
				safCulService.update(safCul);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(safCul.getCreateTime());
				w.setCreateUserID(safCul.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(safCul.getDeptId());
				w.setInfoContent(safCul.getInfoContent());
				w.setInfotitle(safCul.getInfoTitle());
				w.setLinkId(safCul.getLinkId());
				w.setFirTitle(safCul.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==12){
				safCul.setDelFlag(0);
				safCulService.update(safCul);
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(safCul.getInfoContent());
				tzgg.setInfoTitle(safCul.getInfoTitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(safCul.getLinkId());
				tzgg.setFirTitle(safCul.getFirTitle());
				tzggService.save(tzgg);
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(safCul.getInfoTitle());
				gzdt.setInfoContent(safCul.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(safCul.getLinkId());
				gzdt.setFirTitle(safCul.getFirTitle());
				gzdtService.save(gzdt);
			}else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(safCul.getInfoContent());
				zhzf.setInfoTitle(safCul.getInfoTitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(safCul.getLinkId());
				zhzf.setFirTitle(safCul.getFirTitle());
				zhzfService.save(zhzf);
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(safCul.getInfoContent());
				cityMan.setInfoTitle(safCul.getInfoTitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(safCul.getLinkId());
				cityMan.setFirTitle(safCul.getFirTitle());
				cityManService.save(cityMan);
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(safCul.getInfoContent());
				jgdl.setInfoTitle(safCul.getInfoTitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(safCul.getLinkId());
				jgdl.setFirTitle(safCul.getFirTitle());
				jgdlService.save(jgdl);
			}
		}
		else if("4".equals(type)){
			Zhzf zhzf = zhzfService.getById(ids);
			if(keep==1){
				zhzf.setDelFlag(1);
				zhzfService.update(zhzf);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(zhzf.getCreateTime());
				w.setCreateUserID(zhzf.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(zhzf.getDeptId());
				w.setInfoContent(zhzf.getInfoContent());
				w.setInfotitle(zhzf.getInfoTitle());
				w.setLinkId(zhzf.getLinkId());
				w.setFirTitle(zhzf.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(zhzf.getInfoContent());
				safCul.setInfoTitle(zhzf.getInfoTitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(zhzf.getLinkId());
				safCul.setFirTitle(zhzf.getFirTitle());
				safCulService.save(safCul);
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(zhzf.getInfoContent());
				tzgg.setInfoTitle(zhzf.getInfoTitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(zhzf.getLinkId());
				tzgg.setFirTitle(zhzf.getFirTitle());
				tzggService.save(tzgg);
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(zhzf.getInfoTitle());
				gzdt.setInfoContent(zhzf.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(zhzf.getLinkId());
				gzdt.setFirTitle(zhzf.getFirTitle());
				gzdtService.save(gzdt);
			}else if(infoType==13){
				zhzf.setDelFlag(0);
				zhzfService.update(zhzf);
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(zhzf.getInfoContent());
				cityMan.setInfoTitle(zhzf.getInfoTitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(zhzf.getLinkId());
				cityMan.setFirTitle(zhzf.getFirTitle());
				cityManService.save(cityMan);
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(zhzf.getInfoContent());
				jgdl.setInfoTitle(zhzf.getInfoTitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(zhzf.getLinkId());
				jgdl.setFirTitle(zhzf.getFirTitle());
				jgdlService.save(jgdl);
			}
		}
		else if("5".equals(type)){
			CityMan cityMan = cityManService.getById(ids);
			if(keep==1){
				cityMan.setDelFlag(1);
				cityManService.update(cityMan);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(cityMan.getCreateTime());
				w.setCreateUserID(cityMan.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(cityMan.getDeptId());
				w.setInfoContent(cityMan.getInfoContent());
				w.setInfotitle(cityMan.getInfoTitle());
				w.setLinkId(cityMan.getLinkId());
				w.setFirTitle(cityMan.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(cityMan.getInfoContent());
				safCul.setInfoTitle(cityMan.getInfoTitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(cityMan.getLinkId());
				safCul.setFirTitle(cityMan.getFirTitle());
				safCulService.save(safCul);
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(cityMan.getInfoContent());
				tzgg.setInfoTitle(cityMan.getInfoTitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(cityMan.getLinkId());
				tzgg.setFirTitle(cityMan.getFirTitle());
				tzggService.save(tzgg);
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(cityMan.getInfoTitle());
				gzdt.setInfoContent(cityMan.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(cityMan.getLinkId());
				gzdt.setFirTitle(cityMan.getFirTitle());
				gzdtService.save(gzdt);
			}else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(cityMan.getInfoContent());
				zhzf.setInfoTitle(cityMan.getInfoTitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(cityMan.getLinkId());
				zhzf.setFirTitle(cityMan.getFirTitle());
				zhzfService.save(zhzf);
			}
			else if(infoType==14){
				cityMan.setDelFlag(0);
				cityManService.update(cityMan);
			}
			else if(infoType==15){
				Jgdl jgdl = new Jgdl();
				Date d = new Date();
				jgdl.setDeptId(this.getLoginUserDepartment().getId());
				jgdl.setDelFlag(0);
				jgdl.setInfoContent(cityMan.getInfoContent());
				jgdl.setInfoTitle(cityMan.getInfoTitle());
				jgdl.setPublicDate(d);
				jgdl.setCreateTime(d);
				jgdl.setCreateUserID(getLoginUser().getId());
				jgdl.setLinkId(cityMan.getLinkId());
				jgdl.setFirTitle(cityMan.getFirTitle());
				jgdlService.save(jgdl);
			}
		}
		else if("6".equals(type)){
			Jgdl jgdl = jgdlService.getById(ids);
			if(keep==1){
				jgdl.setDelFlag(1);
				jgdlService.update(jgdl);
			}
			if(infoType<10){
				WzInfoManage w=new WzInfoManage();
				w.setInfoType(String.valueOf(infoType));
				w.setCreateTime(jgdl.getCreateTime());
				w.setCreateUserID(jgdl.getCreateUserID());
				w.setDelFlag(0);
				w.setDeptId(jgdl.getDeptId());
				w.setInfoContent(jgdl.getInfoContent());
				w.setInfotitle(jgdl.getInfoTitle());
				w.setLinkId(jgdl.getLinkId());
				w.setFirTitle(jgdl.getFirTitle());
				wzInfoManageService.save(w);
			}else if(infoType==12){
				SafCul safCul = new SafCul();
				Date d = new Date();
				safCul.setDeptId(this.getLoginUserDepartment().getId());
				safCul.setDelFlag(0);
				safCul.setInfoContent(jgdl.getInfoContent());
				safCul.setInfoTitle(jgdl.getInfoTitle());
				safCul.setPublicDate(d);
				safCul.setCreateTime(d);
				safCul.setCreateUserID(getLoginUser().getId());
				safCul.setLinkId(jgdl.getLinkId());
				safCul.setFirTitle(jgdl.getFirTitle());
				safCulService.save(safCul);
			}else if(infoType==10){
				Tzgg tzgg = new Tzgg();
				Date d = new Date();
				tzgg.setUserId(this.getLoginUser().getId());
				tzgg.setDeptId(this.getLoginUserDepartment().getId());
				tzgg.setDelFlag("0");
				tzgg.setInfoContent(jgdl.getInfoContent());
				tzgg.setInfoTitle(jgdl.getInfoTitle());
				tzgg.setPublicDate(d);
				tzgg.setCreateTime(d);
				tzgg.setCreateUserID(getLoginUser().getId());
				tzgg.setLinkId(jgdl.getLinkId());
				tzgg.setFirTitle(jgdl.getFirTitle());
				tzggService.save(tzgg);
			}else if(infoType==11){
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setInfoTitle(jgdl.getInfoTitle());
				gzdt.setInfoContent(jgdl.getInfoContent());
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setLinkId(jgdl.getLinkId());
				gzdt.setFirTitle(jgdl.getFirTitle());
				gzdtService.save(gzdt);
			}else if(infoType==13){
				Zhzf zhzf = new Zhzf();
				Date d = new Date();
				zhzf.setDeptId(this.getLoginUserDepartment().getId());
				zhzf.setDelFlag(0);
				zhzf.setInfoContent(jgdl.getInfoContent());
				zhzf.setInfoTitle(jgdl.getInfoTitle());
				zhzf.setPublicDate(d);
				zhzf.setCreateTime(d);
				zhzf.setCreateUserID(getLoginUser().getId());
				zhzf.setLinkId(jgdl.getLinkId());
				zhzf.setFirTitle(jgdl.getFirTitle());
				zhzfService.save(zhzf);
			}
			else if(infoType==14){
				CityMan cityMan = new CityMan();
				Date d = new Date();
				cityMan.setDeptId(this.getLoginUserDepartment().getId());
				cityMan.setDelFlag(0);
				cityMan.setInfoContent(jgdl.getInfoContent());
				cityMan.setInfoTitle(jgdl.getInfoTitle());
				cityMan.setPublicDate(d);
				cityMan.setCreateTime(d);
				cityMan.setCreateUserID(getLoginUser().getId());
				cityMan.setLinkId(jgdl.getLinkId());
				cityMan.setFirTitle(jgdl.getFirTitle());
				cityManService.save(cityMan);
			}
			else if(infoType==15){
				jgdl.setDelFlag(0);
				jgdlService.update(jgdl);
			}
		}
		return RELOAD;
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

	public WzInfoManage getWzInfoManage(){
		return this.wzInfoManage;
	}

	public void setWzInfoManage(WzInfoManage wzInfoManage){
		this.wzInfoManage = wzInfoManage;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Map<String, Object>> getMapList() {
		return mapList;
	}
	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	public int getInfoType() {
		return infoType;
	}
	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	public int getKeep() {
		return keep;
	}
	public void setKeep(int keep) {
		this.keep = keep;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
       
    
}
