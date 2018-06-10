/**
 * Class Name: ZdwxyhxsqAction
 * Class Description：危险化学品重大危险源核销申请
 */
package com.jshx.zdwxyhxsq.web;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zdwxyhxsq.entity.Zdwxyhxsq;
import com.jshx.zdwxyhxsq.service.ZdwxyhxsqService;

public class ZdwxyhxsqAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxyhxsq zdwxyhxsq = new Zdwxyhxsq();

	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxyhxsqService zdwxyhxsqService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryDjrqStart;

	private Date queryDjrqEnd;
	
	private Date createTimeStart;

	private Date createTimeEnd;
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
	/**
	 * zdwxyhx
	 */
	private List<PhotoPic> picList;
	
	/**
	 * 附件
	 */
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private String fileId; //附件id
	 /**
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list1 = new ArrayList<PhotoPic>();
		
		private List<PhotoPic> list2 = new ArrayList<PhotoPic>();
	/**
	 * 初始化安全附件情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public String init()
	{
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
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zdwxyhxsq){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxyhxsq.getQymc()) && (0 < zdwxyhxsq.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxyhxsq.getQymc().trim() + "%");
			}
			if ((null != zdwxyhxsq.getZdwxymc()) && (0 < zdwxyhxsq.getZdwxymc().trim().length())){
				paraMap.put("zdwxymc", "%" + zdwxyhxsq.getZdwxymc().trim() + "%");
			}

			if ((null != zdwxyhxsq.getZdwxybabh()) && (0 < zdwxyhxsq.getZdwxybabh().trim().length())){
				paraMap.put("zdwxybabh", "%" + zdwxyhxsq.getZdwxybabh().trim() + "%");
			}

			if (null != queryDjrqStart){
				paraMap.put("startDjrq", queryDjrqStart);
			}

			if (null != queryDjrqEnd){
				paraMap.put("endDjrq", queryDjrqEnd);
			}
			if ((null != zdwxyhxsq.getSzzid()) && (0 < zdwxyhxsq.getSzzid().trim().length())){
				paraMap.put("szzid",  zdwxyhxsq.getSzzid().trim() );
			}
			if ((null != zdwxyhxsq.getSzc() )&& (0 < zdwxyhxsq.getSzc().trim().length())){
				paraMap.put("szc",zdwxyhxsq.getSzc().trim());
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
		 
		pagination = zdwxyhxsqService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zdwxyhxsq)&&(null != zdwxyhxsq.getId()))
//		{
			zdwxyhxsq = zdwxyhxsqService.getById(zdwxyhxsq.getId());
//		}
//		else
//		{
//			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
//			zdwxyhxsq.setProId(linkId);
//		}
//		Map map = new HashMap();
//		map.put("taskProId",zdwxyhxsq.getProId());
//		map.put("picType","zdwxyhx");
//	    picList = szwxPhotoService.findPicPath(map);//获取安全操作规程
		
		if(zdwxyhxsq.getLinkid()==null)
		{
			zdwxyhxsq.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zdwxyhxsq.getLinkid());
		map.put("picType","zdwxyhxsqb");
	    list1 = szwxPhotoService.findPicPath(map);//获取图片或附件列表
	    map.put("picType","zdwxyhxdjb");
	    list2 = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		zdwxyhxsq.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zdwxyhxsq.setZdwxymc(sdf.format(new Date()));
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zdwxyhxsq.setSzzid(dept.getDeptCode());
			zdwxyhxsq.setSzzname(dept.getDeptName());
			zdwxyhxsq.setQyid(company.getId());
			zdwxyhxsq.setQymc(company.getCompanyname());
			zdwxyhxsq.setDeptId(this.getLoginUserDepartmentId());
			zdwxyhxsq.setDelFlag(0);
			zdwxyhxsq.setCreateUserID(this.getLoginUserId());
			zdwxyhxsq.setCreateTime(new Date());
			zdwxyhxsq.setQylx(company.getQylx());
			zdwxyhxsq.setHyfl(company.getHyfl());
			zdwxyhxsq.setQygm(company.getQygm());
			zdwxyhxsq.setQyzclx(company.getQyzclx());
			zdwxyhxsq.setTbdwdz(company.getDwdz2());
			zdwxyhxsq.setYzbm(company.getYzbm());
			zdwxyhxsq.setIfwhpqylx(company.getIfwhpqylx());
			zdwxyhxsq.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zdwxyhxsq.setIfzywhqylx(company.getIfzywhqylx());
			zdwxyhxsq.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			zdwxyhxsq.setSzc(company.getSzc());
			zdwxyhxsq.setSzcname(company.getSzcname());
			zdwxyhxsq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxyhxsq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxyhxsqService.save(zdwxyhxsq);
		}else{
			zdwxyhxsq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxyhxsq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxyhxsqService.update(zdwxyhxsq);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zdwxyhxsqService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String upload()
	{
		return SUCCESS;
	}
	
	/**
	 * 本岗位安全操作规程上传
	 * author：陆婷
	 * 2013-08-20
	 */
	public void fileUpload()
	{
		try
  		{
  			String filename = "";
  			String extName = "";
  			String[] a = zdwxyhxsq.getProId().split(",");
  			String proId = a[0];
  			String picType = a[1];
  			if ((Filedata != null) && (!Filedata.isEmpty())) {
  				for (int i = 0; i < Filedata.size(); i++) {
  			  		//获取文件对象
  					File file = (File)Filedata.get(i);
  					UploadFile uploadFile = new UploadFile();
  					filename = (String)FiledataFileName.get(i);
  					uploadFile.setFileName(filename);
  					
  					uploadFile.setUploadFile(file);
  					String rename = getDatedFName(FiledataFileName.get(i));
  					String path = Struts2Util.getServletContext().getRealPath(
  					"/");
  					uploadFile.setId(rename);
  					uploadFile.setFilePath(path + 
  					"../../virtualdir/upload/file/zdwxyhx/"
  					);
  			  		//附件上传
  					uploadFile.uploadToServer();
  					
  					PhotoPic taskPic = new PhotoPic();
  					taskPic.setCreateTime(new Date());
  					taskPic.setPicName("zdwxyhx/" + rename);
					taskPic.setPicType("zdwxyhx");
					taskPic.setTaskProId(proId);
  					taskPic.setDelFlag(0);
  					taskPic.setFileName(filename);//保存原文件的名称
  					picType = companyService.findCompanyTypeNameByKey(picType,"402880484076bce30140a56dfbb70b83").toString();
  					taskPic.setType(picType);
  					szwxPhotoService.save(taskPic);//在此处调用图片的保存
  	  				extName = taskPic.getId();
  				}
  				HttpServletResponse response = ServletActionContext.getResponse();
  			    response.getWriter().write(filename + ";" + extName + ";" + picType);
  			}
  		}
  		catch (Exception e) {
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
	 * 本岗位安全操作规程删除
	 * author：陆婷
	 * 2013-08-20
	 */
	public void deleteFile() throws IOException
	{
		try{
	    	szwxPhotoService.deleteImageWithFlag(fileId);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
	}
	/**
	 * 本岗位安全操作规程下载
	 * author：陆婷
	 * 2013-08-20
	 */
	public void download()
	{
		try
  		{
  		  	//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(fileId);
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			File fis = new File(path + "../../virtualdir/upload/file/" + photoPic.getPicName());
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

  				String browName = new String();
  				browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
  				String clientInfo = getRequest().getHeader("User-agent");
  				if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
  					if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
  						browName = new String(photoPic.getFileName().getBytes("GBK"), "ISO-8859-1");
  				}

  				Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
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

	public Zdwxyhxsq getZdwxyhxsq(){
		return this.zdwxyhxsq;
	}

	public void setZdwxyhxsq(Zdwxyhxsq zdwxyhxsq){
		this.zdwxyhxsq = zdwxyhxsq;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryDjrqStart(){
		return this.queryDjrqStart;
	}

	public void setQueryDjrqStart(Date queryDjrqStart){
		this.queryDjrqStart = queryDjrqStart;
	}

	public Date getQueryDjrqEnd(){
		return this.queryDjrqEnd;
	}

	public void setQueryDjrqEnd(Date queryDjrqEnd){
		this.queryDjrqEnd = queryDjrqEnd;
	}
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public List<PhotoPic> getList1() {
		return list1;
	}

	public void setList1(List<PhotoPic> list1) {
		this.list1 = list1;
	}

	public List<PhotoPic> getList2() {
		return list2;
	}

	public void setList2(List<PhotoPic> list2) {
		this.list2 = list2;
	}
}
