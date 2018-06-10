/**
 * Class Name: JshxZcyhsbAction
 * Class Description：自查隐患上报
 */
package com.jshx.zcyhsb.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.qyzcyhglb.service.QyzcyhglbService;
import com.jshx.zcyhsb.entity.BzhBean;
import com.jshx.zcyhsb.entity.HyflDataBean;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.entity.TjYhBean;
import com.jshx.zcyhsb.entity.TypeBean;
import com.jshx.zcyhsb.entity.WhpBean;
import com.jshx.zcyhsb.entity.ZywhBean;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

public class JshxZcyhsbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;
	

	String name_xc = "";
	
	String data_xc = "";
	
	/**
	 * 新增字段 
	 */
	private String type;
	TjYhBean tjyhBean = new TjYhBean();
	
	BzhBean bzhBean = new BzhBean();
	
	ZywhBean zywhBean = new ZywhBean();
	
	WhpBean whpBean = new WhpBean();
	
	String[] a = new String[]{" ","资质<br>证照","安全<br>生产<br>管理<br>机构<br>及人<br>员","安全<br>生产<br>责任<br>制",
			"安全<br>生产<br>管理<br>制度","安全<br>操作<br>规程","教育<br>培训","安全<br>生产<br>管理<br>档案","安全<br>生产<br>投入",
			"应急<br>管理","特征<br>设备<br>基础<br>管理","职业<br>卫生<br>基础<br>管理","相关<br>方基<br>础管<br>理","其他<br>基础<br>管理"};//14
	String[] b = new String[]{" ","特种<br>设备<br>现场<br>管理","生产<br>设备<br>设施<br>及工<br>艺","场所<br>环境",
			"从业<br>人员<br>操作<br>行为","消防<br>安全","用电<br>安全","职业<br>卫生<br>现场<br>安全","有限<br>空间<br>现场<br>安全",
			"辅助<br>动力<br>系统","相关<br>方现<br>场管<br>理","其他<br>现场<br>管理"};//12
	
	private String ms =  "10,10,10,10";
	
	List<TypeBean> types = new ArrayList<TypeBean>();
	/**
	 * 行业分类隐患列表
	 */
	List<HyflDataBean> hyfls = new ArrayList<HyflDataBean>();
	
	HyflDataBean hyfl = new HyflDataBean();
	
	List<TjYhBean> tjyhList = new ArrayList<TjYhBean>();
	
	List<BzhBean> bzhList = new ArrayList<BzhBean>();
	
	List<ZywhBean> zywhList = new ArrayList<ZywhBean>();
	
	List<WhpBean> whpList = new ArrayList<WhpBean>();
	/**
	 * 实体类
	 */
	private JshxZcyhsb jshxZcyhsb = new JshxZcyhsb();
	 /**
     * 整改前图片
     */
	private List<PhotoPic> picList11 = new ArrayList<PhotoPic>();
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList12 = new ArrayList<PhotoPic>();
	
	 /**
     * 整改前图片
     */
	private List<PhotoPic> picList21 = new ArrayList<PhotoPic>();
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList22 = new ArrayList<PhotoPic>();
	
	 /**
     * 整改前图片
     */
	private List<PhotoPic> picList31 = new ArrayList<PhotoPic>();
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList32 = new ArrayList<PhotoPic>();
	
	 /**
     * 整改前图片
     */
	private List<PhotoPic> picList41 = new ArrayList<PhotoPic>();
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList42 = new ArrayList<PhotoPic>();
	
	 /**
     * 整改前图片
     */
	private List<PhotoPic> picList51 = new ArrayList<PhotoPic>();
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList52 = new ArrayList<PhotoPic>();
	/**
	 * 业务类
	 */
	@Autowired
	private JshxZcyhsbService jshxZcyhsbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private QyzcyhglbService qyzcyhglbService;
	
	
//	private List<File> Filedata;
//	private List<String> FiledataFileName;
//	private List<String> FiledataContentType;
	
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;
    

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryJcsjStart;

	private Date queryJcsjEnd;

	private Date queryJhwcsjStart;

	private Date queryJhwcsjEnd;
	
	private Qyzcyhglb qyzcyhglb1;
	
	private Qyzcyhglb qyzcyhglb2;
	
	private Qyzcyhglb qyzcyhglb3;
	
	private Qyzcyhglb qyzcyhglb4;
	
	private Qyzcyhglb qyzcyhglb5;
	
	private String isshow;
	private String updateFlag;
	
	private String cityClick;
	
	private String linkid;
	
	
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getCityClick() {
		return cityClick;
	}
	public void setCityClick(String cityClick) {
		this.cityClick = cityClick;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String init(){
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else
		{
			flag = "3";
		}
		setRequestAttribute("mqzt", getRequestParameter("mqzt"));
		return SUCCESS;
	}
	
	public String initFour(){
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(null != deptCode && deptCode.length()<=6){
			flag = "1";
		}
//		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
//		{
//			flag = "1";
//		}
//		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
//		{
//			flag = "2";
//		}
//		else
//		{
//			flag = "3";
//		}
		setRequestAttribute("mqzt", getRequestParameter("mqzt"));
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZcyhsb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryJcsjStart){
				paraMap.put("startJcsj", queryJcsjStart);
			}

			if (null != queryJcsjEnd){
				paraMap.put("endJcsj", queryJcsjEnd);
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%");
			}
			if ((null != jshxZcyhsb.getJcry()) && (0 < jshxZcyhsb.getJcry().trim().length())){
				paraMap.put("jcry", "%" + jshxZcyhsb.getJcry().trim() + "%");
			}

			if (null != queryJhwcsjStart){
				paraMap.put("startJhwcsj", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				paraMap.put("endJhwcsj", queryJhwcsjEnd);
			}
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZcyhsb.getSzzid().trim() + "%");
			}
			if ((null != jshxZcyhsb.getQyid()) && (0 < jshxZcyhsb.getQyid().trim().length())){
				paraMap.put("qyid", jshxZcyhsb.getQyid().trim());
			}
			if ((null != jshxZcyhsb.getSzc() )&& (0 < jshxZcyhsb.getSzc().trim().length())){
				paraMap.put("szc",jshxZcyhsb.getSzc().trim());
			}
			if ((null != jshxZcyhsb.getMqzt() )&& (0 < jshxZcyhsb.getMqzt().trim().length())){
				paraMap.put("mqzt","1");
			}
			if ((null != jshxZcyhsb.getIfzsqy() )&& (0 < jshxZcyhsb.getIfzsqy().trim().length())){
				paraMap.put("ifzsqy",jshxZcyhsb.getIfzsqy().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		
		pagination = jshxZcyhsbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void fourList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZcyhsb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryJcsjStart){
				paraMap.put("startJcsj", queryJcsjStart);
			}

			if (null != queryJcsjEnd){
				paraMap.put("endJcsj", queryJcsjEnd);
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%");
			}
			if ((null != jshxZcyhsb.getJcry()) && (0 < jshxZcyhsb.getJcry().trim().length())){
				paraMap.put("jcry", "%" + jshxZcyhsb.getJcry().trim() + "%");
			}

			if (null != queryJhwcsjStart){
				paraMap.put("startJhwcsj", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				paraMap.put("endJhwcsj", queryJhwcsjEnd);
			}
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZcyhsb.getSzzid().trim() + "%" );
			}
			if ((null != jshxZcyhsb.getQyid()) && (0 < jshxZcyhsb.getQyid().trim().length())){
				paraMap.put("qyid", jshxZcyhsb.getQyid().trim());
			}
			if ((null != jshxZcyhsb.getSzc() )&& (0 < jshxZcyhsb.getSzc().trim().length())){
				paraMap.put("szc",jshxZcyhsb.getSzc().trim());
			}
			if ((null != jshxZcyhsb.getMqzt() )&& (0 < jshxZcyhsb.getMqzt().trim().length())){
				paraMap.put("mqzt","1");
			}
			if ((null != jshxZcyhsb.getIfzsqy() )&& (0 < jshxZcyhsb.getIfzsqy().trim().length())){
				paraMap.put("ifzsqy",jshxZcyhsb.getIfzsqy().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		if(!"1".equals(cityClick)){
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
		}
		//hanxc 20141223 修改查询条件 end
		
		pagination = jshxZcyhsbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != jshxZcyhsb)&&(null != jshxZcyhsb.getId())){
			jshxZcyhsb = jshxZcyhsbService.getById(jshxZcyhsb.getId());
			if(jshxZcyhsb.getLinkId() == null || "".equals(jshxZcyhsb.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxZcyhsb.setLinkId(linkId);
			}
			Map map = new HashMap();
			map.put("yhid",jshxZcyhsb.getId());
		    List<Qyzcyhglb> list = qyzcyhglbService.findQyzcyhglb(map);
		    for(Qyzcyhglb q:list)
		    {
		    	if(q.getType().equals("1"))
		    	{
		    		qyzcyhglb1 = q;
		    	}
		    	else if(q.getType().equals("2"))
		    	{
		    		qyzcyhglb2 = q;
		    	}
		    	else if(q.getType().equals("3"))
		    	{
		    		qyzcyhglb3 = q;
		    	}
		    	else if(q.getType().equals("4"))
		    	{
		    		qyzcyhglb4 = q;
		    	}
		    	else if(q.getType().equals("5"))
		    	{
		    		qyzcyhglb5 = q;
		    	}
		    }
		    map.put("taskProId",jshxZcyhsb.getLinkId());
			map.put("picType","zgqtp1");
		    picList11 = szwxPhotoService.findPicPath(map);//获取整改前图片
		    map.put("picType","zgqtp2");
		    picList21 = szwxPhotoService.findPicPath(map);//获取整改前图片
		    map.put("picType","zgqtp3");
		    picList31 = szwxPhotoService.findPicPath(map);//获取整改前图片
		    map.put("picType","zgqtp4");
		    picList41 = szwxPhotoService.findPicPath(map);//获取整改前图片
		    map.put("picType","zgqtp5");
		    picList51 = szwxPhotoService.findPicPath(map);//获取整改前图片
		    map.put("picType","zghtp1");
		    picList12 = szwxPhotoService.findPicPath(map);//获取整改后图片
		    map.put("picType","zghtp2");
		    picList22 = szwxPhotoService.findPicPath(map);//获取整改后图片
		    map.put("picType","zghtp3");
		    picList32 = szwxPhotoService.findPicPath(map);//获取整改后图片
		    map.put("picType","zghtp4");
		    picList42 = szwxPhotoService.findPicPath(map);//获取整改后图片
		    map.put("picType","zghtp5");
		    picList52 = szwxPhotoService.findPicPath(map);//获取整改后图片
		}else{
			jshxZcyhsb.setQymc(this.getLoginUser().getDisplayName());
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxZcyhsb.setLinkId(linkId);
			jshxZcyhsb.setYhsl("0");
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
		try {
			
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			if("0".equals(jshxZcyhsb.getYhsl())){
				jshxZcyhsb.setMqzt("0");
			}
			if ("add".equalsIgnoreCase(this.flag)){
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					jshxZcyhsb.setSzzid(dept.getDeptCode());
					jshxZcyhsb.setSzzname(dept.getDeptName());
					jshxZcyhsb.setQyid(company.getId());
					jshxZcyhsb.setQymc(company.getCompanyname());
					jshxZcyhsb.setDeptId(this.getLoginUserDepartmentId());
					jshxZcyhsb.setDelFlag(0);
					jshxZcyhsb.setCreateUserID(this.getLoginUserId());
					jshxZcyhsb.setCreateTime(new Date());
					jshxZcyhsb.setQylx(company.getQylx());
					jshxZcyhsb.setHyfl(company.getHyflTwoLevel());
					jshxZcyhsb.setQygm(company.getQygm());
					jshxZcyhsb.setQyzclx(company.getQyzclx());
					jshxZcyhsb.setIfzsqy(company.getIfzsqy());
	
					jshxZcyhsb.setIfwhpqylx(company.getIfwhpqylx());
					jshxZcyhsb.setIfyhbzjyqy(company.getIfyhbzjyqy());
					jshxZcyhsb.setIfzywhqylx(company.getIfzywhqylx());
					jshxZcyhsb.setIffmksjyqy(company.getIffmksjyqy());//hanxc 20141210 设置非煤矿山标识 
					jshxZcyhsb.setSzc(company.getSzc());
					jshxZcyhsb.setSzcname(company.getSzcname());
					//四清单
					jshxZcyhsb.setRenwuList("1");
					if(Integer.valueOf(jshxZcyhsb.getYhsl())>0){
						jshxZcyhsb.setYinhuanList("1");
						jshxZcyhsb.setZhenggaiList("1");
						if(jshxZcyhsb.getZgtrzj().equals("0")){
							jshxZcyhsb.setFuchaList("1");
						}
					}
				
				//企业名称
				jshxZcyhsb.setDeptId(this.getLoginUserDepartmentId());
				jshxZcyhsb.setDelFlag(0);
				jshxZcyhsb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				jshxZcyhsb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				jshxZcyhsbService.save(jshxZcyhsb);
			}else{
				jshxZcyhsb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				jshxZcyhsb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				jshxZcyhsbService.update(jshxZcyhsb);
				Map<String, String> map = new HashMap<String, String>();
				map.put("yhid", jshxZcyhsb.getId());
				jshxZcyhsbService.deleteQyzcyhglbByMap(map);
			}
			saveDate(qyzcyhglb1,"1",jshxZcyhsb);
			saveDate(qyzcyhglb2,"2",jshxZcyhsb);
			saveDate(qyzcyhglb3,"3",jshxZcyhsb);
			saveDate(qyzcyhglb4,"4",jshxZcyhsb);
			saveDate(qyzcyhglb5,"5",jshxZcyhsb);
			updateFlag ="1";
		} catch (RuntimeException e) {
			updateFlag ="2";
			e.printStackTrace();
		}
		return RELOAD;
	}

	public void saveDate(Qyzcyhglb qyzcyhglb,String type,JshxZcyhsb jshxZcyhsb)
	{
		if(Integer.parseInt(type) <= Integer.parseInt(jshxZcyhsb.getYhsl()))
		{
			if(qyzcyhglb != null && qyzcyhglb.getYhdl() != null && !"0".equals(qyzcyhglb.getYhdl()))
			{
				qyzcyhglb.setType(type);
				qyzcyhglb.setCreateTime(new Date());
				qyzcyhglb.setDelFlag(0);
				qyzcyhglb.setYhid(jshxZcyhsb.getId());
				qyzcyhglbService.save(qyzcyhglb);
			}
		}
		
	}
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxZcyhsbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 跳转到图片导入界面  lj  2013-07-18
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	
	public void uploadAttach() {

		try {
			if (file != null && file.size() > 0) {
				for (int i = 0; i < file.size(); i++) {
					File tempFile = file.get(i);
					String fileName = fileFileName.get(i);
					
					UploadFile uploadFile = new UploadFile();
					uploadFile.setFileName(fileName);
					uploadFile.setFileType(getFileSuffix(fileName));
					uploadFile.setUploadFile(tempFile);
					String rename = UUID.randomUUID().toString() + "."
							+ getFileSuffix(fileName);
					String path = Struts2Util.getServletContext().getRealPath("/");
					uploadFile.setId(rename);
//					uploadFile.setFilePath(path
//							+ SysPropertiesUtil.getProperty("uploadFile")
//							+ File.separator
//							+ DateUtil.convertDateToString(
//									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
//									+ File.separator);
					String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
					root = root.substring(0,root.indexOf("webapps")+8);
					root = root.replaceAll("\\\\", "/");
					root = root.replace("webapps","virtualdir/upload");
					StringBuffer destFName = new StringBuffer();
					destFName.append(root).append("photo/");
					uploadFile.setFilePath(destFName.toString());
					System.out.println("zxyhsb pic filepath: "+uploadFile.getFilePath());
					uploadFile.uploadToServer();
//					uploadFile.setFilePath(
//							SysPropertiesUtil.getProperty("uploadFile")
//							+ File.separator
//							+ DateUtil.convertDateToString(
//									DateUtil.DATE_FORMAT_YYYYMMDD, new Date())
//									+ File.separator);
					/** **将附件存入数据库**** */
					/*ContentAttachs contentAttaFile = new ContentAttachs();
					contentAttaFile.setDeptId(this.getLoginUserDepartmentId());
					contentAttaFile.setDelFlag(0);
					contentAttaFile.setInfoId(contentInformations.getId());
					contentAttaFile.setAttachName(rename);
					contentAttaFile.setDocName(uploadFile.getFileName());
					//contentAttaFile.setDocType(this.getAttachType().get(i));
					contentAttaFile.setDocUrl(uploadFile.getFilePath());
					String url = SysPropertiesUtil.getProperty("httpurl");
					contentAttaFile.setHttpUrl(url);
					contentAttaFile.setNwUrl(SysPropertiesUtil.getProperty("nwurl"));
					Long filesize=uploadFile.getUploadFile().length();
					contentAttaFile.setFileSize(Long.toString(filesize));
					contentAttachsService.save(contentAttaFile);*/
					
					PhotoPic taskPic = new PhotoPic();
					taskPic.setCreateTime(new Date());
					taskPic.setPicName(fileName);
//					String[] ts = type.split(",");
//					if(ts.length>=2){
//					}
					taskPic.setPicType(type);
					taskPic.setTaskProId(linkid);
					taskPic.setTaskRemark("");
					taskPic.setDelFlag(0);
					taskPic.setFileName(rename);//保存原文件的名称 李军 2013-07-19
					szwxPhotoService.save(taskPic);//在此处调用图片的保存
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getFileSuffix(String fileName) {
		String filesuffix = null;
		StringTokenizer fx = new StringTokenizer(fileName, ". ");
		while (fx.hasMoreTokens()) {
			filesuffix = fx.nextToken();
		}
		return filesuffix;
	}
	/**
	 * 图片保存 lj  2013-07-18
	 * @return
	 */
	/*public void savePhoto(){
		try {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			File outfile = null;
			File outdir = null;
			byte[] bs = new byte[1024];
			String imgName="";
			StringBuffer destFName = new StringBuffer();
			String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
			root = root.substring(0,root.indexOf("webapps")+8);
			root = root.replaceAll("\\\\", "/");
			root = root.replace("webapps","virtualdir/upload");
			destFName.append(root).append("photo/");
			outdir = new File(destFName.toString());
			if(Filedata!= null && !Filedata.isEmpty()){//获取附件文件 进行判断是否存在
			    imgName =getDatedFName(FiledataFileName.get(0));
				outfile = new File(destFName+imgName);
				InputStream stream  = new FileInputStream(Filedata.get(0));
				bis = new BufferedInputStream(stream);
				if (!outdir.exists())
					outdir.mkdirs();
				if (!outfile.exists())
					outfile.createNewFile();

				fos = new FileOutputStream(outfile);
				int i;
				while ((i = bis.read(bs)) != -1) {
					fos.write(bs, 0, i);
				}
			}
			PhotoPic taskPic = new PhotoPic();
			taskPic.setCreateTime(new Date());
			taskPic.setPicName(imgName);
			String[] ts = type.split(",");
			if(ts.length>=2){
				taskPic.setPicType(ts[0]);
				taskPic.setTaskProId(ts[1]);
			}
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", imgName);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @return
	 */
	public String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}
	/**
	 * 删除信息图片
	 */
	public String deleteImage() throws Exception{
	    try{
	    	szwxPhotoService.deleteImageWithFlag(picName);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 图片 文字 另存为功能 
	 *  李军 2013-08-15
	 */
	public void saveFile()
  	{
  		try
  		{//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/photo/";
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			path = path.substring(0,path.indexOf("webapps"));
  			File fis = new File(path + filePath + photoPic.getPicName());
  			System.out.print("图片的地址是："+path + filePath + picName);
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

	        String browName = new String();
	        browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
	        String clientInfo = getRequest().getHeader("User-agent");
	        if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
	          if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
	            browName = new String((photoPic.getFileName()).getBytes("GBK"), "ISO-8859-1");
	        }

        Struts2Util.getResponse()
          .addHeader(
          "Content-Disposition", 
          "attachment;filename=" + 
          browName);
        OutputStream out = Struts2Util.getResponse().getOutputStream();
        try {
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) != -1)
          {
            out.write(buf, 0, len);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          in.close();
          out.close();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
	/**
	 * 企业自查隐患统计 李军 2013-11-1
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String tongJi(){
		try {
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			deptCode = deptCode.substring(0, deptCode.length()-3);
			String deptRole = this.getLoginUser().getDeptRole();
			Map map = new HashMap();
			if(jshxZcyhsb != null)
			{
				if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
					map.put("szzid",  jshxZcyhsb.getSzzid().trim() + "%"  );
				}
				if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
					map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
				}
				if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
					map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
				}
				if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
					map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
				}
			}
			if(queryJhwcsjStart != null)
			{
				map.put("queryJcsjStart", queryJhwcsjStart);
			}
			if(queryJhwcsjEnd != null)
			{
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//部门人员登录，添加查询条件过滤
				map.put("szzid",deptCode+"%");
				if(deptCode.length() >=6){
					map.put("ifzsqy","0");
				}
			}
			tjyhBean = jshxZcyhsbService.getTjYhDataFromQy(map);
			types = jshxZcyhsbService.getTypeDataFromQy(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "tongji";
	}
	/**
	 * 按检查类别查询列表 2013-11-12 李军
	 */
	@SuppressWarnings("unchecked")
	public String pieTongJi(){
		Map map = new HashMap();
		if(jshxZcyhsb != null)
		{
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
			}
			if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
				map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
			}
			if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
				map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
			}
		}
		if(queryJhwcsjStart != null )
		{
			map.put("queryJcsjStart", queryJhwcsjStart);
		}
		if(queryJhwcsjEnd != null )
		{
			map.put("queryJcsjEnd", queryJhwcsjEnd);
		}
		types = jshxZcyhsbService.getTypeDataFromQy(map);
		return "dataPie";
	}
	/**
	 * 检查类别的统计 李军 2013-11-5
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void tjType(){
		try {
			Map map = new HashMap();
			if(jshxZcyhsb != null)
			{
				if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
					map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
				}
				if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
					map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
				}
				if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
					map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
				}
				if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
					map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
				}
			}
			if(queryJhwcsjStart != null )
			{
				map.put("queryJcsjStart", queryJhwcsjStart);
			}
			if(queryJhwcsjEnd != null )
			{
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			JSONObject json = new JSONObject();
			String type = this.getRequest().getParameter("obj");
			if("2".equals(type)){//检查类别统计
				types = jshxZcyhsbService.getTypeDataFromQy(map);
				int data1=0;
				int data2=0;
				int data3 = 0;
				for(TypeBean b:types){
					if("0".equals(b.getJclb())){
						data1 = data1+b.getLbCount();
					}
					if("1".equals(b.getJclb())){
						data2 = data2+b.getLbCount();		
					}
					if("2".equals(b.getJclb())){
						data3 = data3+b.getLbCount();
					}
				}
				json.put("data1", data1);
				json.put("data2", data2);
				json.put("data3", data3);
			}else if("3".equals(type)){//隐患类别统计
				try {
					types = jshxZcyhsbService.getYhTypeDataFromQy(map);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				int data1=-1;
				int data2=0;
				int data3 = 0;
				int data2_2=0;
				int data2_3=0;
				int data3_2=0;
				int data3_3=0;
				for(TypeBean b:types){
					if("1".equals(b.getJclb())){
						data2 = data2+b.getYhlbTotal();//总数
						data2_2 = data2_2 +b.getWzgCount();//未整改
						data2_3 = data2_3 +b.getYzgCount();//已整改
					}
					if("2".equals(b.getJclb())){
						data3 = data3+b.getYhlbTotal();
						data3_2 = data3_2 +b.getWzgCount();//未整改
						data3_3 = data3_3 +b.getYzgCount();//已整改
					}
				}
				
				List<TypeBean> zhls=null;
				try {
					zhls = jshxZcyhsbService.getZlTypeData(map);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				
				String name_jc = "";
				String data_jc = "";
				String name_xc = "";
				String data_xc = "";
				
					for(int i=1;i<a.length;i++){
						name_jc = name_jc+","+a[i];
						int k=0;
						
						for(TypeBean bean:zhls){
							
							if("1".equals(bean.getJclb())){//基础管理
								int index = Integer.parseInt(bean.getYhzl().substring(2));
								if(index == i){
									data_jc = data_jc+","+bean.getYhlbTotal();
									k=1;
									break;
								}
							}
						}
						if(k==0){
							data_jc = data_jc+",0";
						}
					}
					for(int i=1;i<b.length;i++){
						name_xc = name_xc+","+b[i];
						int k=0;
						for(TypeBean bean:zhls){
							if("2".equals(bean.getJclb())){//现场管理
								int index = Integer.parseInt(bean.getYhzl().substring(2));
								if(index == i){
									data_xc = data_xc+","+bean.getYhlbTotal();
									k=1;
									break;
								}
							}
						}
						if(k==0){
							data_xc = data_xc+",0";
						}
					}
				
				
				
				json.put("data1", data1);
				json.put("data2", data2);
				json.put("data2_2", data2_2);
				json.put("data2_3", data2_3);
				json.put("data2_4", reat(data2_3,data2));
				json.put("data2_5", reat(data2_3,data2+data3));
				

				json.put("data3", data3);
				json.put("data3_2", data3_2);
				json.put("data3_3", data3_3);
				json.put("data3_4", reat(data3_3,data3));
				json.put("data3_5", reat(data3_3,data2+data3));
				
				json.put("name_jc", name_jc.replaceFirst(",", ""));
				json.put("data_jc", data_jc.replaceFirst(",", ""));
				json.put("name_xc", name_xc.replaceFirst(",", ""));
				json.put("data_xc", data_xc.replaceFirst(",", ""));
				
			}
			
			this.getResponse().getWriter().print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	public String chart3TongJi(){
		Map map = new HashMap();
		if(jshxZcyhsb != null)
		{
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
			}
			if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
				map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
			}
			if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
				map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
			}
		}
		if(queryJhwcsjStart != null )
		{
			map.put("queryJcsjStart", queryJhwcsjStart);
		}
		if(queryJhwcsjEnd != null )
		{
			map.put("queryJcsjEnd", queryJhwcsjEnd);
		}
		List<TypeBean> zhls=null;
		try {
			zhls = jshxZcyhsbService.getZlTypeData(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		for(int i=1;i<b.length;i++){
			name_xc = name_xc+","+b[i];
			int k=0;
			for(TypeBean bean:zhls){
				if("2".equals(bean.getJclb())){//现场管理
					int index = Integer.parseInt(bean.getYhzl().substring(2));
					if(index == i){
						data_xc = data_xc+","+bean.getYhlbTotal();
						k=1;
						break;
					}
				}
			}
			if(k==0){
				data_xc = data_xc+",0";
			}
		}
		 name_xc = name_xc.replaceFirst(",", "");
		 data_xc = data_xc.replaceFirst(",", "");
		return "dataChart3";
	}
	public String reat(int i,int j){
		if(j!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)i*100/(j));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
		
	}
	/**
	 * 按部门统计隐患信息 2013-11-11 李军
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deptTongJi(){
		Map map = new HashMap();
		if(jshxZcyhsb != null)
		{
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
			}
			if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
				map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
			}
			if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
				map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
			}
		}
		if(queryJhwcsjStart != null )
		{
			map.put("queryJcsjStart", queryJhwcsjStart);
		}
		if(queryJhwcsjEnd != null )
		{
			map.put("queryJcsjEnd", queryJhwcsjEnd);
		}
		tjyhBean = jshxZcyhsbService.getTjYhDataFromQy(map);
		tjyhList = jshxZcyhsbService.getTjYhListFromQy(map);
		return "dataDept";
	}
	/**
	 * 按行业分类统计隐患信息 2013-11-11 李军
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String hyflTongJi(){
		Map map = new HashMap();
		if(jshxZcyhsb != null)
		{
			if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
				map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
			}
			if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
				map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
			}
			if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
				map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
			}
			if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
				map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
			}
		}
		if(queryJhwcsjStart != null )
		{
			map.put("queryJcsjStart", queryJhwcsjStart);
		}
		if(queryJhwcsjEnd != null )
		{
			map.put("queryJcsjEnd", queryJhwcsjEnd);
		}
		hyfl = jshxZcyhsbService.getHyflDataBean(map);
		hyfls = jshxZcyhsbService.getHyflDataList(map);
		return "dataHyfl";
	}
	/**
	 * 安全标准化企业达标隐患自查自纠 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bzhTongji(){
		try {
			Map map = new HashMap();
			if(queryJhwcsjStart != null )
			{
				map.put("queryJcsjStart", queryJhwcsjStart);
			}
			if(queryJhwcsjEnd != null )
			{
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			bzhBean=jshxZcyhsbService.getBzhDataFromQy(map);
			bzhList=jshxZcyhsbService.getBzhListFromQy(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "dataBzh";
	}
	
	/**
	 * 职业危害企业隐患自查自纠 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String zywhTongji(){
		try {
			Map map = new HashMap();
			if(queryJhwcsjStart != null )
			{
				map.put("queryJcsjStart", queryJhwcsjStart);
			}
			if(queryJhwcsjEnd != null )
			{
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			zywhBean=jshxZcyhsbService.getZywhDataFromQy(map);
			zywhList=jshxZcyhsbService.getZywhListFromQy(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "dataZywh";
	}
	
	
	/**
	 * 危险化学品企业隐患自查自纠 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String whpTongji(){
		try {
			Map map = new HashMap();
			if(queryJhwcsjStart != null )
			{
				map.put("queryJcsjStart", queryJhwcsjStart);
			}
			if(queryJhwcsjEnd != null )
			{
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			whpBean=jshxZcyhsbService.getWhpDataFromQy(map);
			whpList=jshxZcyhsbService.getWhpListFromQy(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "dataWhp";
	}
	
	
	
	
	
	/**
	 * 按部门统计隐患信息导出
	 * 2013-12-2
	 * 陆婷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void deptTongJiExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zcyhdept.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按部门企业自查隐患统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按部门企业自查隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 7)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(0);
	        ccl0.setCellValue("部门及镇、街道");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("辖区内企业（单位）数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("上报企业（单位数）");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("未上报企业（单位数）");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("隐患排查数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("整改隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("未整改隐患数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("整改率");
	        ccl7.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        Map map = new HashMap();
	        if(flag == null || "".equals(flag))
			{
	        	jshxZcyhsb = (JshxZcyhsb) getSessionAttribute("jshxZcyhsb");
	        	queryJhwcsjStart = (Date) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (Date) getSessionAttribute("queryJhwcsjEnd");
			}
	        if(jshxZcyhsb != null)
			{
	        	setSessionAttribute("jshxZcyhsb", jshxZcyhsb);
				if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
					map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
				}
				if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
					map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
				}
				if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
					map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
				}
				if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
					map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
				}
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			tjyhBean = jshxZcyhsbService.getTjYhDataFromQy(map);
			tjyhList = jshxZcyhsbService.getTjYhListFromQy(map);
			
			int num = 2;
			for(TjYhBean tjyhbean:tjyhList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getDwdz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getSbqy());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getWsbqy());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getYhTotal());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getZgwc());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getZgwwc());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getZgl());
		        ce7.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
			ce1.setCellValue(tjyhBean.getQyTotal());
			ce1.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(2);
			ce2.setCellValue(tjyhBean.getSbqy());
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(3);
			ce3.setCellValue(tjyhBean.getWsbqy());
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(4);
			ce4.setCellValue(tjyhBean.getYhTotal());
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(5);
			ce5.setCellValue(tjyhBean.getZgwc());
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(6);
			ce6.setCellValue(tjyhBean.getZgwwc());
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(7);
			ce7.setCellValue(tjyhBean.getZgl());
			ce7.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按行业分类统计隐患信息导出
	 * 2013-12-2
	 * 陆婷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void hyflTongJiExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zcyhhylb.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按行业类别企业自查隐患统计");
		    sheet.setColumnWidth(0, 12000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 4000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 3000);
	        sheet.setColumnWidth(7, 4000);
	        sheet.setColumnWidth(8, 6000); 
	        sheet.setColumnWidth(9, 3000); 
	        sheet.setColumnWidth(10, 4000);
	        sheet.setColumnWidth(11, 6000);
	        sheet.setColumnWidth(12, 3000);
	        sheet.setColumnWidth(13, 4000);
	        sheet.setColumnWidth(14, 6000);
	        sheet.setColumnWidth(15, 3000);
	        sheet.setColumnWidth(16, 6000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按行业类别企业自查隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 16)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(0);
	        ccl0.setCellValue("行业分类");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 5, (short) 0)); 
	        
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("开展排查治理事故隐患的企业（单位）");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 1, (short) 3));
	        
	        
	        HSSFCell ccl2 = r3.createCell(4);
	        ccl2.setCellValue("一般事故隐患");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 1, (short) 6));
	        
	        HSSFCell ccl3 = r3.createCell(7);
	        ccl3.setCellValue("重大事故隐患");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 7, 1, (short) 15));
	        
	        HSSFCell ccl4 = r3.createCell(16);
	        ccl4.setCellValue("整改资金");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 16, 3, (short) 16)); 
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl5 = r4.createCell(1);
	        ccl5.setCellValue("应排查治理事故隐患的企业（单位）");
	        ccl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 1, 3, (short) 1)); 
	        
	        HSSFCell ccl6 = r4.createCell(2);
	        ccl6.setCellValue("实排查治理事故隐患的企业（单位）");
	        ccl6.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 2, 3, (short) 2)); 
	        
	        HSSFCell ccl7 = r4.createCell(3);
	        ccl7.setCellValue("覆盖率");
	        ccl7.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 3, 3, (short) 3));
	        
	        HSSFCell ccl8 = r4.createCell(4);
	        ccl8.setCellValue("排查一般事故隐患");
	        ccl8.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 4, 3, (short) 4));
	        
	        HSSFCell ccl9 = r4.createCell(5);
	        ccl9.setCellValue("其中：已整改（*）");
	        ccl9.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 5, 3, (short) 5));
	        
	        HSSFCell ccl10 = r4.createCell(6);
	        ccl10.setCellValue("整改率");
	        ccl10.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 6, 3, (short) 6));
	        
	        HSSFCell ccl11 = r4.createCell(7);
	        ccl11.setCellValue("一级隐患");
	        ccl11.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 7, 2, (short) 9));
	        
	        HSSFCell ccl12 = r4.createCell(10);
	        ccl12.setCellValue("二级隐患");
	        ccl12.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 10, 2, (short) 12));
	        
	        
	        HSSFCell ccl13 = r4.createCell(13);
	        ccl13.setCellValue("三级隐患");
	        ccl13.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 13, 2, (short) 15));
	        
	        
	        HSSFRow r5 = sheet.createRow(3);
	        r5.setHeight((short)(23*20));
	        HSSFCell ccl14 = r5.createCell(7);
	        ccl14.setCellValue("排查隐患数");
	        ccl14.setCellStyle(cs);
	        
	        HSSFCell ccl15 = r5.createCell(8);
	        ccl15.setCellValue("其中：已整改（*）");
	        ccl15.setCellStyle(cs);
	        
	        HSSFCell ccl16 = r5.createCell(9);
	        ccl16.setCellValue("整改率");
	        ccl16.setCellStyle(cs);
	        
	        HSSFCell ccl17 = r5.createCell(10);
	        ccl17.setCellValue("排查隐患数");
	        ccl17.setCellStyle(cs);
	        
	        HSSFCell ccl18 = r5.createCell(11);
	        ccl18.setCellValue("其中：已整改（*）");
	        ccl18.setCellStyle(cs);
	        
	        HSSFCell ccl19 = r5.createCell(12);
	        ccl19.setCellValue("整改率");
	        ccl19.setCellStyle(cs);
	        
	        HSSFCell ccl20 = r5.createCell(13);
	        ccl20.setCellValue("排查隐患数");
	        ccl20.setCellStyle(cs);
	        
	        HSSFCell ccl21 = r5.createCell(14);
	        ccl21.setCellValue("其中：已整改（*）");
	        ccl21.setCellStyle(cs);
	        
	        HSSFCell ccl22 = r5.createCell(15);
	        ccl22.setCellValue("整改率");
	        ccl22.setCellStyle(cs);
	        
	        
	        HSSFRow r6 = sheet.createRow(4);
	        r6.setHeight((short)(23*20));
	        HSSFCell cc1 = r6.createCell(1);
	        cc1.setCellValue("（家）");
	        cc1.setCellStyle(cs);
	        HSSFCell cc2 = r6.createCell(2);
	        cc2.setCellValue("（家）");
	        cc2.setCellStyle(cs);
	        HSSFCell cc3 = r6.createCell(3);
	        cc3.setCellValue("（%）");
	        cc3.setCellStyle(cs);
	        HSSFCell cc4 = r6.createCell(4);
	        cc4.setCellValue("（项）");
	        cc4.setCellStyle(cs);
	        HSSFCell cc5 = r6.createCell(5);
	        cc5.setCellValue("（项）");
	        cc5.setCellStyle(cs);
	        HSSFCell cc6 = r6.createCell(6);
	        cc6.setCellValue("（%）");
	        cc6.setCellStyle(cs);
	        HSSFCell cc7 = r6.createCell(7);
	        cc7.setCellValue("（项）");
	        cc7.setCellStyle(cs);
	        HSSFCell cc8 = r6.createCell(8);
	        cc8.setCellValue("（项）");
	        cc8.setCellStyle(cs);
	        HSSFCell cc9 = r6.createCell(9);
	        cc9.setCellValue("（%）");
	        cc9.setCellStyle(cs);
	        HSSFCell cc10 = r6.createCell(10);
	        cc10.setCellValue("（项）");
	        cc10.setCellStyle(cs);
	        HSSFCell cc11 = r6.createCell(11);
	        cc11.setCellValue("（项）");
	        cc11.setCellStyle(cs);
	        HSSFCell cc12 = r6.createCell(12);
	        cc12.setCellValue("（%）");
	        cc12.setCellStyle(cs);
	        HSSFCell cc13 = r6.createCell(13);
	        cc13.setCellValue("（项）");
	        cc13.setCellStyle(cs);
	        HSSFCell cc14 = r6.createCell(14);
	        cc14.setCellValue("（项）");
	        cc14.setCellStyle(cs);
	        HSSFCell cc15 = r6.createCell(15);
	        cc15.setCellValue("（%）");
	        cc15.setCellStyle(cs);
	        HSSFCell cc16 = r6.createCell(16);
	        cc16.setCellValue("（万元）");
	        cc16.setCellStyle(cs);
	        
	        
	        HSSFRow r7 = sheet.createRow(5);
	        r7.setHeight((short)(23*20));
	        HSSFCell c1 = r7.createCell(1);
	        c1.setCellValue("1");
	        c1.setCellStyle(cs);
	        HSSFCell c2 = r7.createCell(2);
	        c2.setCellValue("2");
	        c2.setCellStyle(cs);
	        HSSFCell c3 = r7.createCell(3);
	        c3.setCellValue("3");
	        c3.setCellStyle(cs);
	        HSSFCell c4 = r7.createCell(4);
	        c4.setCellValue("4");
	        c4.setCellStyle(cs);
	        HSSFCell c5 = r7.createCell(5);
	        c5.setCellValue("5");
	        c5.setCellStyle(cs);
	        HSSFCell c6 = r7.createCell(6);
	        c6.setCellValue("6");
	        c6.setCellStyle(cs);
	        HSSFCell c7 = r7.createCell(7);
	        c7.setCellValue("7");
	        c7.setCellStyle(cs);
	        HSSFCell c8 = r7.createCell(8);
	        c8.setCellValue("8");
	        c8.setCellStyle(cs);
	        HSSFCell c9 = r7.createCell(9);
	        c9.setCellValue("9");
	        c9.setCellStyle(cs);
	        HSSFCell c10 = r7.createCell(10);
	        c10.setCellValue("10");
	        c10.setCellStyle(cs);
	        HSSFCell c11 = r7.createCell(11);
	        c11.setCellValue("11");
	        c11.setCellStyle(cs);
	        HSSFCell c12 = r7.createCell(12);
	        c12.setCellValue("12");
	        c12.setCellStyle(cs);
	        HSSFCell c13 = r7.createCell(13);
	        c13.setCellValue("13");
	        c13.setCellStyle(cs);
	        HSSFCell c14 = r7.createCell(14);
	        c14.setCellValue("14");
	        c14.setCellStyle(cs);
	        HSSFCell c15 = r7.createCell(15);
	        c15.setCellValue("15");
	        c15.setCellStyle(cs);
	        HSSFCell c16 = r7.createCell(16);
	        c16.setCellValue("16");
	        c16.setCellStyle(cs);
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        Map map = new HashMap();
	        if(flag == null || "".equals(flag))
			{
	        	jshxZcyhsb = (JshxZcyhsb) getSessionAttribute("jshxZcyhsb");
	        	queryJhwcsjStart = (Date) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (Date) getSessionAttribute("queryJhwcsjEnd");
			}
	        if(jshxZcyhsb != null)
			{
	        	setSessionAttribute("jshxZcyhsb", jshxZcyhsb);
				if ((null != jshxZcyhsb.getSzzid()) && (0 < jshxZcyhsb.getSzzid().trim().length())){
					map.put("szzid",  jshxZcyhsb.getSzzid().trim() );
				}
				if ((null != jshxZcyhsb.getQymc()) && (0 < jshxZcyhsb.getQymc().trim().length())){
					map.put("qymc", "%" + jshxZcyhsb.getQymc().trim() + "%" );
				}
				if ((null != jshxZcyhsb.getWhpqylx()) && (0 < jshxZcyhsb.getWhpqylx().trim().length())){
					map.put("whpqylx", "%" + jshxZcyhsb.getWhpqylx().trim() + "%");
				}
				if ((null != jshxZcyhsb.getAqbzdbjb()) && (0 < jshxZcyhsb.getAqbzdbjb().trim().length())){
					map.put("aqbzdbjb",  jshxZcyhsb.getAqbzdbjb().trim() );
				}
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			hyfl = jshxZcyhsbService.getHyflDataBean(map);
			hyfls = jshxZcyhsbService.getHyflDataList(map);
			
			int num = 6;
			for(HyflDataBean hyfldatabean:hyfls)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(hyfldatabean.getHyfl());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(hyfldatabean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(hyfldatabean.getSbqy());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(hyfldatabean.getFgl());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(hyfldatabean.getJb01());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(hyfldatabean.getJb0101());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(hyfldatabean.getZgl01());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(hyfldatabean.getJb02());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
				ce8.setCellValue(hyfldatabean.getJb0202());
				ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(hyfldatabean.getZgl02());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(hyfldatabean.getJb03());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(hyfldatabean.getJb0303());
		        ce11.setCellStyle(c);
		        HSSFCell ce12 = ro.createCell(12);
		        ce12.setCellValue(hyfldatabean.getZgl03());
		        ce12.setCellStyle(c);
		        HSSFCell ce13 = ro.createCell(13);
		        ce13.setCellValue(hyfldatabean.getJb04());
		        ce13.setCellStyle(c);
		        HSSFCell ce14 = ro.createCell(14);
		        ce14.setCellValue(hyfldatabean.getJb0404());
		        ce14.setCellStyle(c);
		        HSSFCell ce15 = ro.createCell(15);
		        ce15.setCellValue(hyfldatabean.getZgl04());
		        ce15.setCellStyle(c);
		        HSSFCell ce16 = ro.createCell(16);
		        ce16.setCellValue(hyfldatabean.getZgzj());
		        ce16.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("小计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(hyfl.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(hyfl.getSbqy());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(hyfl.getFgl());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(hyfl.getJb01());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(hyfl.getJb0101());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(hyfl.getZgl01());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(hyfl.getJb02());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
			ce8.setCellValue(hyfl.getJb0202());
			ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(hyfl.getZgl02());
	        ce9.setCellStyle(c);
	        HSSFCell ce10 = ro.createCell(10);
	        ce10.setCellValue(hyfl.getJb03());
	        ce10.setCellStyle(c);
	        HSSFCell ce11 = ro.createCell(11);
	        ce11.setCellValue(hyfl.getJb0303());
	        ce11.setCellStyle(c);
	        HSSFCell ce12 = ro.createCell(12);
	        ce12.setCellValue(hyfl.getZgl03());
	        ce12.setCellStyle(c);
	        HSSFCell ce13 = ro.createCell(13);
	        ce13.setCellValue(hyfl.getJb04());
	        ce13.setCellStyle(c);
	        HSSFCell ce14 = ro.createCell(14);
	        ce14.setCellValue(hyfl.getJb0404());
	        ce14.setCellStyle(c);
	        HSSFCell ce15 = ro.createCell(15);
	        ce15.setCellValue(hyfl.getZgl04());
	        ce15.setCellStyle(c);
	        HSSFCell ce16 = ro.createCell(16);
	        ce16.setCellValue(hyfl.getZgzj());
	        ce16.setCellStyle(c);
	        
	        
	        HSSFRow r1 = sheet.createRow(num+1);
			HSSFCell ce17 = r1.createCell(0);
			ce17.setCellValue("合计");
			ce17.setCellStyle(c);
			HSSFCell ce18 = r1.createCell(1);
	        ce18.setCellValue("排查隐患总数：" + hyfl.getYhtotal() + "      其中已整改："  + hyfl.getYzgTotal() + "      整改率：" + hyfl.getTotalZgl());
	        ce18.setCellStyle(c);
	        sheet.addMergedRegion(new Region(num+1, (short) 1, num+1, (short) 16)); 
			
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按安全标准化达标企业信息导出
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void bzhTongJiExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zcyhbzh.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按安全标准化达标企业自查隐患统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按安全标准化达标企业自查隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 7)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(0);
	        ccl0.setCellValue("镇、街道");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("安全生产标准化达标企业数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("上报企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("未上报企业数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("隐患排查数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("整改隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("未整改隐患数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("整改率");
	        ccl7.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        Map map = new HashMap();
	        if(flag == null || "".equals(flag))
			{
	        	queryJhwcsjStart = (Date) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (Date) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			bzhBean = jshxZcyhsbService.getBzhDataFromQy(map);
			bzhList = jshxZcyhsbService.getBzhListFromQy(map);
			
			int num = 2;
			for(BzhBean bzhbean:bzhList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(bzhbean.getDwdz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(bzhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(bzhbean.getSbqy());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(bzhbean.getWsbqy());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(bzhbean.getYhTotal());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(bzhbean.getZgwc());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(bzhbean.getZgwwc());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(bzhbean.getZgl());
		        ce7.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
			ce1.setCellValue(bzhBean.getQyTotal());
			ce1.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(2);
			ce2.setCellValue(bzhBean.getSbqy());
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(3);
			ce3.setCellValue(bzhBean.getWsbqy());
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(4);
			ce4.setCellValue(bzhBean.getYhTotal());
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(5);
			ce5.setCellValue(bzhBean.getZgwc());
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(6);
			ce6.setCellValue(bzhBean.getZgwwc());
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(7);
			ce7.setCellValue(bzhBean.getZgl());
			ce7.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 按职业危害企业信息导出
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void zywhTongJiExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zcyhzywh.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按职业危害企业自查隐患统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按职业危害企业自查隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 7)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(0);
	        ccl0.setCellValue("镇、街道");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("职业危害企业");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("上报企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("未上报企业数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("隐患排查数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("整改隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("未整改隐患数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("整改率");
	        ccl7.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        Map map = new HashMap();
	        if(flag == null || "".equals(flag))
			{
	        	queryJhwcsjStart = (Date) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (Date) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			zywhBean = jshxZcyhsbService.getZywhDataFromQy(map);
			zywhList = jshxZcyhsbService.getZywhListFromQy(map);
			
			int num = 2;
			for(ZywhBean zywhbean:zywhList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zywhbean.getDwdz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zywhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(zywhbean.getSbqy());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(zywhbean.getWsbqy());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zywhbean.getYhTotal());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(zywhbean.getZgwc());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(zywhbean.getZgwwc());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(zywhbean.getZgl());
		        ce7.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
			ce1.setCellValue(zywhBean.getQyTotal());
			ce1.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(2);
			ce2.setCellValue(zywhBean.getSbqy());
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(3);
			ce3.setCellValue(zywhBean.getWsbqy());
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(4);
			ce4.setCellValue(zywhBean.getYhTotal());
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(5);
			ce5.setCellValue(zywhBean.getZgwc());
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(6);
			ce6.setCellValue(zywhBean.getZgwwc());
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(7);
			ce7.setCellValue(zywhBean.getZgl());
			ce7.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 按危险化学品企业信息导出
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void whpTongJiExport(){
		
			try {
				if(flag == null || "".equals(flag))
				{
					queryJhwcsjStart = (Date) getSessionAttribute("queryJhwcsjStart");
		        	queryJhwcsjEnd =  (Date) getSessionAttribute("queryJhwcsjEnd");
				}
				
				Map<String, Object> paraMap = new HashMap<String, Object>();
				if (null != queryJhwcsjStart && !"".equals(queryJhwcsjStart)){
					setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
					paraMap.put("queryJcsjStart", queryJhwcsjStart);
				}

				if (null != queryJhwcsjEnd && !"".equals(queryJhwcsjEnd)){
					setSessionAttribute("queryJcsjEnd", queryJhwcsjEnd);
					paraMap.put("queryJcsjEnd", queryJhwcsjEnd);
				}
				whpBean=jshxZcyhsbService.getWhpDataFromQy(paraMap);
				whpList=jshxZcyhsbService.getWhpListFromQy(paraMap);
				
				
				getResponse().setContentType("octets/stream");
				getResponse().addHeader("Content-Disposition", "attachment;filename=whp.xls");
				String root = this.getRequest().getRealPath("/"); 
				root = root.replaceAll("\\\\", "/");
				InputStream is= new FileInputStream(root + "whp.xls");
				HSSFWorkbook wb = new HSSFWorkbook(is);
				HSSFSheet sheet = wb.getSheetAt(0);
		        
		        int num = 3;
		        for(WhpBean whpbean:whpList)
		        {
		        	
		        	HSSFRow row0 = sheet.getRow(num);
					HSSFCell cel1 = row0.getCell(1);
					cel1.setCellValue(whpbean.getDwdz());
					HSSFCell cel2 = row0.getCell(2);
					cel2.setCellValue(whpbean.getQyTotal());
					HSSFCell cel3 = row0.getCell(3);
					cel3.setCellValue(whpbean.getSbqy());
					HSSFCell cel4 = row0.getCell(4);
					cel4.setCellValue(whpbean.getYhTotal());
					HSSFCell cel5 = row0.getCell(5);
					cel5.setCellValue(whpbean.getZgwc());
					HSSFCell cel6 = row0.getCell(6);
					cel6.setCellValue(whpbean.getZgl());
					HSSFCell cel7 = row0.getCell(7);
					cel7.setCellValue(whpbean.getHgscqyTotal());
					HSSFCell cel8 = row0.getCell(8);
					cel8.setCellValue(whpbean.getHgscsbqy());
					HSSFCell cel9 = row0.getCell(9);
					cel9.setCellValue(whpbean.getHgscyhTotal());
					HSSFCell cel10 = row0.getCell(10);
					cel10.setCellValue(whpbean.getHgsczgwc());
					HSSFCell cel11 = row0.getCell(11);
					cel11.setCellValue(whpbean.getHgsczgwwc());
					HSSFCell cel12 = row0.getCell(12);
					cel12.setCellValue(whpbean.getHgsczgl());
					HSSFCell cel13 = row0.getCell(13);
					cel13.setCellValue(whpbean.getWhpjyqyTotal());
					HSSFCell cel14 = row0.getCell(14);
					cel14.setCellValue(whpbean.getWhpjysbqy());
					HSSFCell cel15 = row0.getCell(15);
					cel15.setCellValue(whpbean.getWhpjyyhTotal());
					HSSFCell cel16 = row0.getCell(16);
					cel16.setCellValue(whpbean.getWhpjyzgwc());
					HSSFCell cel17 = row0.getCell(17);
					cel17.setCellValue(whpbean.getWhpjyzgwwc());
					HSSFCell cel18 = row0.getCell(18);
					cel18.setCellValue(whpbean.getWhpjyzgl());
					HSSFCell cel19 = row0.getCell(19);
					cel19.setCellValue(whpbean.getWhpsyqyTotal());
					HSSFCell cel20 = row0.getCell(20);
					cel20.setCellValue(whpbean.getWhpsysbqy());
					HSSFCell cel21 = row0.getCell(21);
					cel21.setCellValue(whpbean.getWhpsyyhTotal());
					HSSFCell cel22 = row0.getCell(22);
					cel22.setCellValue(whpbean.getWhpsyzgwc());
					HSSFCell cel23 = row0.getCell(23);
					cel23.setCellValue(whpbean.getWhpsyzgwwc());
					HSSFCell cel24 = row0.getCell(24);
					cel24.setCellValue(whpbean.getWhpsyzgl());
					
					
					num ++;
		        }
		        HSSFRow row0 = sheet.getRow(num);
				
				HSSFCell cel2 = row0.getCell(2);
				cel2.setCellValue(whpBean.getQyTotal());
				HSSFCell cel3 = row0.getCell(3);
				cel3.setCellValue(whpBean.getSbqy());
				HSSFCell cel4 = row0.getCell(4);
				cel4.setCellValue(whpBean.getYhTotal());
				HSSFCell cel5 = row0.getCell(5);
				cel5.setCellValue(whpBean.getZgwc());
				HSSFCell cel6 = row0.getCell(6);
				cel6.setCellValue(whpBean.getZgl());
				HSSFCell cel7 = row0.getCell(7);
				cel7.setCellValue(whpBean.getHgscqyTotal());
				HSSFCell cel8 = row0.getCell(8);
				cel8.setCellValue(whpBean.getHgscsbqy());
				HSSFCell cel9 = row0.getCell(9);
				cel9.setCellValue(whpBean.getHgscyhTotal());
				HSSFCell cel10 = row0.getCell(10);
				cel10.setCellValue(whpBean.getHgsczgwc());
				HSSFCell cel11 = row0.getCell(11);
				cel11.setCellValue(whpBean.getHgsczgwwc());
				HSSFCell cel12 = row0.getCell(12);
				cel12.setCellValue(whpBean.getHgsczgl());
				HSSFCell cel13 = row0.getCell(13);
				cel13.setCellValue(whpBean.getWhpjyqyTotal());
				HSSFCell cel14 = row0.getCell(14);
				cel14.setCellValue(whpBean.getWhpjysbqy());
				HSSFCell cel15 = row0.getCell(15);
				cel15.setCellValue(whpBean.getWhpjyyhTotal());
				HSSFCell cel16 = row0.getCell(16);
				cel16.setCellValue(whpBean.getWhpjyzgwc());
				HSSFCell cel17 = row0.getCell(17);
				cel17.setCellValue(whpBean.getWhpjyzgwwc());
				HSSFCell cel18 = row0.getCell(18);
				cel18.setCellValue(whpBean.getWhpjyzgl());
				HSSFCell cel19 = row0.getCell(19);
				cel19.setCellValue(whpBean.getWhpsyqyTotal());
				HSSFCell cel20 = row0.getCell(20);
				cel20.setCellValue(whpBean.getWhpsysbqy());
				HSSFCell cel21 = row0.getCell(21);
				cel21.setCellValue(whpBean.getWhpsyyhTotal());
				HSSFCell cel22 = row0.getCell(22);
				cel22.setCellValue(whpBean.getWhpsyzgwc());
				HSSFCell cel23 = row0.getCell(23);
				cel23.setCellValue(whpBean.getWhpsyzgwwc());
				HSSFCell cel24 = row0.getCell(24);
				cel24.setCellValue(whpBean.getWhpsyzgl());
				wb.write(getResponse().getOutputStream());
				System.out.println("excel导出成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

	public JshxZcyhsb getJshxZcyhsb(){
		return this.jshxZcyhsb;
	}

	public void setJshxZcyhsb(JshxZcyhsb jshxZcyhsb){
		this.jshxZcyhsb = jshxZcyhsb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryJcsjStart(){
		return this.queryJcsjStart;
	}

	public void setQueryJcsjStart(Date queryJcsjStart){
		this.queryJcsjStart = queryJcsjStart;
	}

	public Date getQueryJcsjEnd(){
		return this.queryJcsjEnd;
	}

	public void setQueryJcsjEnd(Date queryJcsjEnd){
		this.queryJcsjEnd = queryJcsjEnd;
	}

	public Date getQueryJhwcsjStart(){
		return this.queryJhwcsjStart;
	}

	public void setQueryJhwcsjStart(Date queryJhwcsjStart){
		this.queryJhwcsjStart = queryJhwcsjStart;
	}

	public Date getQueryJhwcsjEnd(){
		return this.queryJhwcsjEnd;
	}

	public void setQueryJhwcsjEnd(Date queryJhwcsjEnd){
		this.queryJhwcsjEnd = queryJhwcsjEnd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public List<File> getFiledata() {
//		return Filedata;
//	}
//	public void setFiledata(List<File> filedata) {
//		Filedata = filedata;
//	}
//	public List<String> getFiledataFileName() {
//		return FiledataFileName;
//	}
//	public void setFiledataFileName(List<String> filedataFileName) {
//		FiledataFileName = filedataFileName;
//	}
//	public List<String> getFiledataContentType() {
//		return FiledataContentType;
//	}
//	public void setFiledataContentType(List<String> filedataContentType) {
//		FiledataContentType = filedataContentType;
//	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Qyzcyhglb getQyzcyhglb1() {
		return qyzcyhglb1;
	}
	public void setQyzcyhglb1(Qyzcyhglb qyzcyhglb1) {
		this.qyzcyhglb1 = qyzcyhglb1;
	}
	public Qyzcyhglb getQyzcyhglb2() {
		return qyzcyhglb2;
	}
	public void setQyzcyhglb2(Qyzcyhglb qyzcyhglb2) {
		this.qyzcyhglb2 = qyzcyhglb2;
	}
	public Qyzcyhglb getQyzcyhglb3() {
		return qyzcyhglb3;
	}
	public void setQyzcyhglb3(Qyzcyhglb qyzcyhglb3) {
		this.qyzcyhglb3 = qyzcyhglb3;
	}
	public Qyzcyhglb getQyzcyhglb4() {
		return qyzcyhglb4;
	}
	public void setQyzcyhglb4(Qyzcyhglb qyzcyhglb4) {
		this.qyzcyhglb4 = qyzcyhglb4;
	}
	public Qyzcyhglb getQyzcyhglb5() {
		return qyzcyhglb5;
	}
	public void setQyzcyhglb5(Qyzcyhglb qyzcyhglb5) {
		this.qyzcyhglb5 = qyzcyhglb5;
	}
	public List<PhotoPic> getPicList11() {
		return picList11;
	}
	public void setPicList11(List<PhotoPic> picList11) {
		this.picList11 = picList11;
	}
	public List<PhotoPic> getPicList12() {
		return picList12;
	}
	public void setPicList12(List<PhotoPic> picList12) {
		this.picList12 = picList12;
	}
	public List<PhotoPic> getPicList21() {
		return picList21;
	}
	public void setPicList21(List<PhotoPic> picList21) {
		this.picList21 = picList21;
	}
	public List<PhotoPic> getPicList22() {
		return picList22;
	}
	public void setPicList22(List<PhotoPic> picList22) {
		this.picList22 = picList22;
	}
	public List<PhotoPic> getPicList31() {
		return picList31;
	}
	public void setPicList31(List<PhotoPic> picList31) {
		this.picList31 = picList31;
	}
	public List<PhotoPic> getPicList32() {
		return picList32;
	}
	public void setPicList32(List<PhotoPic> picList32) {
		this.picList32 = picList32;
	}
	public List<PhotoPic> getPicList41() {
		return picList41;
	}
	public void setPicList41(List<PhotoPic> picList41) {
		this.picList41 = picList41;
	}
	public List<PhotoPic> getPicList42() {
		return picList42;
	}
	public void setPicList42(List<PhotoPic> picList42) {
		this.picList42 = picList42;
	}
	public List<PhotoPic> getPicList51() {
		return picList51;
	}
	public void setPicList51(List<PhotoPic> picList51) {
		this.picList51 = picList51;
	}
	public List<PhotoPic> getPicList52() {
		return picList52;
	}
	public void setPicList52(List<PhotoPic> picList52) {
		this.picList52 = picList52;
	}
	public TjYhBean getTjyhBean() {
		return tjyhBean;
	}
	public void setTjyhBean(TjYhBean tjyhBean) {
		this.tjyhBean = tjyhBean;
	}
	public List<TypeBean> getTypes() {
		return types;
	}
	public void setTypes(List<TypeBean> types) {
		this.types = types;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public List<TjYhBean> getTjyhList() {
		return tjyhList;
	}
	public void setTjyhList(List<TjYhBean> tjyhList) {
		this.tjyhList = tjyhList;
	}
	public List<HyflDataBean> getHyfls() {
		return hyfls;
	}
	public void setHyfls(List<HyflDataBean> hyfls) {
		this.hyfls = hyfls;
	}
	public HyflDataBean getHyfl() {
		return hyfl;
	}
	public void setHyfl(HyflDataBean hyfl) {
		this.hyfl = hyfl;
	}
	public String getName_xc() {
		return name_xc;
	}
	public void setName_xc(String name_xc) {
		this.name_xc = name_xc;
	}
	public String getData_xc() {
		return data_xc;
	}
	public void setData_xc(String data_xc) {
		this.data_xc = data_xc;
	}
	public BzhBean getBzhBean() {
		return bzhBean;
	}
	public void setBzhBean(BzhBean bzhBean) {
		this.bzhBean = bzhBean;
	}
	public List<BzhBean> getBzhList() {
		return bzhList;
	}
	public void setBzhList(List<BzhBean> bzhList) {
		this.bzhList = bzhList;
	}
	public ZywhBean getZywhBean() {
		return zywhBean;
	}
	public void setZywhBean(ZywhBean zywhBean) {
		this.zywhBean = zywhBean;
	}
	public List<ZywhBean> getZywhList() {
		return zywhList;
	}
	public void setZywhList(List<ZywhBean> zywhList) {
		this.zywhList = zywhList;
	}
	public WhpBean getWhpBean() {
		return whpBean;
	}
	public void setWhpBean(WhpBean whpBean) {
		this.whpBean = whpBean;
	}
	public List<WhpBean> getWhpList() {
		return whpList;
	}
	public void setWhpList(List<WhpBean> whpList) {
		this.whpList = whpList;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}
	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	

}
