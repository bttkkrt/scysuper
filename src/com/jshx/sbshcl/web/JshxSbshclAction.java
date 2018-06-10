/**
 * Class Name: JshxSbshclAction
 * Class Description：行政许可证书文件管理
 */
package com.jshx.sbshcl.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.sbshcl.entity.JshxSbshcl;
import com.jshx.sbshcl.service.JshxSbshclService;

public class JshxSbshclAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxSbshcl jshxSbshcl = new JshxSbshcl();
	 /**
     *附件列表
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
	/**
	 * 业务类
	 */
	@Autowired
	private JshxSbshclService jshxSbshclService;

	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ReviewLogService reviewLogService;
	@Autowired
	private DeptService deptService;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private String isshow;
	
	private String deptrole;
	
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
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
	
	private Date createTimeStart;

	private Date createTimeEnd;
	
	private String passFlag;
	
	private String deptflag;
	
	private String deptcode;
	
	private String type;//type为0表示只能看，type为1表示能审核
	private String deptCodeLenth;
	private String ifzsqy;
	private String xjshState;//县级审核状态
	private String xjBack;//县级审核备注
	private String sjshState;//市级审核状态
	private String sjBack;//市级审核备注
	/**
	 * 初始化列表
	 * @return
	 */
	public String init()
	{
		deptflag="99";
		String deptCode = this.getLoginUserDepartment().getDeptCode();
/*		passFlag = "1";
		String tempDutyFlag = "";
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		if(deptCode.length() == countyDeptCodeLength){//县级部门
			tempDutyFlag = "2";
		}else if(deptCode.length() == cityDeptCodeLength){//市级部门
			tempDutyFlag = "1";
		}
		*//**
		 * 查询审核日志表
		 *//*
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		//paraMap.put("itemId", company.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewList = pagination.getList();
		if(reviewList != null && reviewList.size() > 0){
			for(int i=0;i<reviewList.size();i++){
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
				dutyFlag = reviewLog.getDutyFlag();
				if(dutyFlag.equals(tempDutyFlag)&& !reviewLog.getState().equals("1")){//登陆人未审核通过该条记录
					passFlag = "0";
				}
			}
		}*/
		
		
		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && deptRole.equals("21")){
			deptflag = "2";
		}else{//企业人员
			//根据部门角色进行查询条件过滤
			if(null != deptRole && (deptRole.contains(SysPropertiesUtil.getProperty("fmksCode"))||deptRole.contains(SysPropertiesUtil.getProperty("whpCode"))
					||deptRole.contains(SysPropertiesUtil.getProperty("fmgmCode"))||deptRole.contains(SysPropertiesUtil.getProperty("zywhCode")))){
				deptflag = "1";
			} else {
				deptflag = "0";
			}
		}
		
		deptcode = deptCode.length()+"";
		if(!"1".equals(type) || (this.getLoginUserDepartment().getDeptName().contains("安监中队") && deptCode.length() != 9))
		{
			jshxSbshcl.setState("3");
		}
		if(this.getLoginUser().getLoginId().equals("admin"))
		{
			flag = "1";
		}
		return SUCCESS;
	}
	/**
	 * 根据条件执行有关行政许可列表查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		
		if(null != jshxSbshcl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxSbshcl.getQyid()) && (0 < jshxSbshcl.getQyid().trim().length())){
				paraMap.put("qyid",  jshxSbshcl.getQyid().trim() );
			}
			if ((null != jshxSbshcl.getJshxTitle()) && (0 < jshxSbshcl.getJshxTitle().trim().length())){
				paraMap.put("jshxTitle", jshxSbshcl.getJshxTitle().trim());
			}
			if ((null != jshxSbshcl.getQymc()) && (0 < jshxSbshcl.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxSbshcl.getQymc().trim() + "%");
			}
			if ((null != jshxSbshcl.getSzzid()) && (0 < jshxSbshcl.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxSbshcl.getSzzid().trim() );
			}
			if ((null != jshxSbshcl.getSzc() )&& (0 < jshxSbshcl.getSzc().trim().length())){
				paraMap.put("szc",jshxSbshcl.getSzc().trim());
			}
		}
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxSbshclService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看某条有关行政许可的详细信息
	 */
	public String view() throws Exception{
		if((null != jshxSbshcl)&&(null != jshxSbshcl.getId())){
			jshxSbshcl = jshxSbshclService.getById(jshxSbshcl.getId());
			if(jshxSbshcl.getLinkId() == null || "".equals(jshxSbshcl.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxSbshcl.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxSbshcl.getLinkId());
				map.put("picType","sbshcl");
			    picList01 = szwxPhotoService.findPicPath(map);//获取附件列表
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxSbshcl.setLinkId(linkId);
		}
		setType(type);
		/**
		 * hyc 2014-12-11
		 * 获得登陆人的部门长度，方便jsp页面判断审核是市级还是县级还是镇级
		 * 获得登陆用户名
		 */
		deptCodeLenth = this.getLoginUserDepartment().getDeptCode().length()+"";
		/**
		 * end
		 */
		
		/**
		 * 查询审核日志表
		 */
		if(jshxSbshcl.getIfzsqy() != null && jshxSbshcl.getIfzsqy().equals("1")){
			ifzsqy = "1";
		}else{
			ifzsqy = "0";
		}
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", jshxSbshcl.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewList = pagination.getList();
		if(reviewList != null && reviewList.size() > 0){
			for(int i=0;i<reviewList.size();i++){
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
				dutyFlag = reviewLog.getDutyFlag();
				if(dutyFlag.equals("2")){//县级领导
					xjshState = reviewLog.getState();
					xjBack = reviewLog.getRemark();
				}else if(dutyFlag.equals("1")){//市级领导
					sjshState = reviewLog.getState();
					sjBack = reviewLog.getRemark();
				}
			}
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
	 * 初始化审核信息
	 */
	public String shenhe() throws Exception{
		view();
	    return SUCCESS;
	}
	
	/**
	 * 保存有关行政许可的信息（包括新增和修改）
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxSbshcl.setSzzid(dept.getDeptCode());
			jshxSbshcl.setSzzname(dept.getDeptName());
			jshxSbshcl.setQyid(company.getId());
			jshxSbshcl.setQymc(company.getCompanyname());
			jshxSbshcl.setDeptId(this.getLoginUserDepartmentId());
			jshxSbshcl.setDelFlag(0);
			jshxSbshcl.setState("0");  //初始值
			jshxSbshcl.setDutyFlag("0");  //初始值
			jshxSbshcl.setIfzsqy(company.getIfzsqy());
			jshxSbshcl.setZsqytype(company.getZsqytype());
			
			jshxSbshcl.setCreateUserID(this.getLoginUserId());
			jshxSbshcl.setCreateTime(new Date());
			jshxSbshcl.setQylx(company.getQylx());
			jshxSbshcl.setHyfl(company.getHyfl());
			jshxSbshcl.setQygm(company.getQygm());
			jshxSbshcl.setQyzclx(company.getQyzclx());
			jshxSbshcl.setIfwhpqylx(company.getIfwhpqylx());
			jshxSbshcl.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxSbshcl.setIfzywhqylx(company.getIfzywhqylx());
			jshxSbshcl.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxSbshcl.setSzc(company.getSzc());
			jshxSbshcl.setSzcname(company.getSzcname());
			jshxSbshcl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxSbshcl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxSbshclService.save(jshxSbshcl);
			
			//hanxc 20141211 生成待审核任务 start
			ReviewLog newReviewLog = new ReviewLog();
			if(null != jshxSbshcl.getIfzsqy() && jshxSbshcl.getIfzsqy().equals("1")){
				newReviewLog.setDutyFlag("1");//市级部门审核任务
			}else{
				newReviewLog.setDutyFlag("2");//县级部门审核任务
			}
			newReviewLog.setState("0");
			newReviewLog.setItemId(jshxSbshcl.getId());
			newReviewLog.setItemType("1");//企业信息类型：type=1 
			newReviewLog.setStartTime(new Date());
			reviewLogService.saveNewTask(newReviewLog);
		}else{
			jshxSbshcl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxSbshcl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxSbshclService.update(jshxSbshcl);
		}
		return RELOAD;
	}
	//状态
	public void findState(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String str= "{\"state\":\"4\"}";
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != ids ){
			if (ids != null){
				paraMap.put("itemId", ids);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String dutyFlag = "99";
			if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"))){//市级部门
				dutyFlag = "1";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"))){//县级部门
				dutyFlag = "2";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"))){//镇级部门
				dutyFlag = "3";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"))){//村级部门
				dutyFlag = "4";
			}
			String deptRole = this.getLoginUser().getDeptRole();
			if(deptRole.equals("21")){
				paraMap.put("dutyFlag", "1");
			}else{
				paraMap.put("dutyFlag", dutyFlag);
			}
			
			pagination = reviewLogService.findByPage(pagination, paraMap);
			List list = pagination.getList();
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					ReviewLog reviewLog = (ReviewLog) list.get(0);
					str = "{\"state\":\""+reviewLog.getState()+"\"}";
				}
			}
		}
		try {
			this.getResponse().getWriter().println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 保存审核信息
	 */
	public String shenhesave() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null != jshxSbshcl && null != jshxSbshcl.getId()){
			String state = jshxSbshcl.getState();
			String shbz = jshxSbshcl.getShbs();
			jshxSbshcl = jshxSbshclService.getById(jshxSbshcl.getId());
			jshxSbshcl.setState(state);
			jshxSbshcl.setShbs(shbz);
			/**
			 * 安监领导审批信息
			 */
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 start
			String dutyFlag = "0";
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"))){//市级部门
				dutyFlag = "1";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"))){//县级部门
				dutyFlag = "2";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"))){//镇级部门
				dutyFlag = "3";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"))){//村级部门
				dutyFlag = "4";
			}
			jshxSbshcl.setDutyFlag(dutyFlag);
			
			Pagination page = new Pagination(this.getRequest());
			Map<String, Object> tempParaMap = new HashMap<String, Object>();
			tempParaMap.put("itemId", jshxSbshcl.getId());
			tempParaMap.put("dutyFlag", dutyFlag);
			page = reviewLogService.findByPage(page, tempParaMap);
			List rlList = page.getList();
			if(!rlList.isEmpty()){ 
				ReviewLog reviewLog = (ReviewLog)rlList.get(0);
				reviewLog.setItemType("1");
				reviewLog.setState(jshxSbshcl.getState());
				reviewLog.setUserId(this.getLoginUserId());
				reviewLog.setUserName(this.getLoginUser().getDisplayName());
				reviewLog.setUserDeptCode(deptCode);
				reviewLog.setEndTime(new Date());
				reviewLog.setRecord("");
				reviewLog.setRemark(jshxSbshcl.getShbs());
				reviewLogService.saveReviewLogAndSetNextTask(reviewLog);
			}
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 end
			
			jshxSbshclService.update(jshxSbshcl);
		}
		
		return RELOAD;
	}
	

	/**
	 * 根据id删除有关行政许可信息
	 */
	public String delete() throws Exception{
	    try{
			jshxSbshclService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转到图片导入界面  lj  2013-08-15
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 图片保存 lj  2013-08-15
	 * @return
	 */
	public void savePhoto(){
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
			root = root.replace("webapps","virtualdir/upload/file/sbshcl/");
			destFName.append(root);// 2013-08-21 按统一格式 模块名称/公司名称/附件名称
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
			String picname = "sbshcl/"+imgName;
			taskPic.setPicName(picname); 
			taskPic.setTaskProId(jshxSbshcl.getLinkId());
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setPicType("sbshcl");//类型设置为资格证书管理
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", picname);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
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
  		{   PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/file/";
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			path = path.substring(0,path.indexOf("webapps"));
  			File fis = new File(path + filePath + photoPic.getPicName());
  			System.out.print("图片的地址是："+path + filePath + photoPic.getPicName());
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

	public JshxSbshcl getJshxSbshcl(){
		return this.jshxSbshcl;
	}

	public void setJshxSbshcl(JshxSbshcl jshxSbshcl){
		this.jshxSbshcl = jshxSbshcl;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList01() {
		return picList01;
	}

	public void setPicList01(List<PhotoPic> picList01) {
		this.picList01 = picList01;
	}

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

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
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getDeptrole() {
		return deptrole;
	}
	public void setDeptrole(String deptrole) {
		this.deptrole = deptrole;
	}
	public String getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}
	public String getDeptflag() {
		return deptflag;
	}
	public void setDeptflag(String deptflag) {
		this.deptflag = deptflag;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeptCodeLenth() {
		return deptCodeLenth;
	}
	public void setDeptCodeLenth(String deptCodeLenth) {
		this.deptCodeLenth = deptCodeLenth;
	}
	public String getIfzsqy() {
		return ifzsqy;
	}
	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
	public String getXjshState() {
		return xjshState;
	}
	public void setXjshState(String xjshState) {
		this.xjshState = xjshState;
	}
	public String getXjBack() {
		return xjBack;
	}
	public void setXjBack(String xjBack) {
		this.xjBack = xjBack;
	}
	public String getSjshState() {
		return sjshState;
	}
	public void setSjshState(String sjshState) {
		this.sjshState = sjshState;
	}
	public String getSjBack() {
		return sjBack;
	}
	public void setSjBack(String sjBack) {
		this.sjBack = sjBack;
	}
       
    
}
