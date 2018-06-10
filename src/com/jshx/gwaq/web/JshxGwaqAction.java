/**
 * Class Name: JshxGwaqAction
 * Class Description：岗位安全
 */
package com.jshx.gwaq.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jshx.gwaq.entity.JshxGwaq;
import com.jshx.gwaq.service.JshxGwaqService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class JshxGwaqAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxGwaq jshxGwaq = new JshxGwaq();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxGwaqService jshxGwaqService;
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
	
	/**
	 * 本岗位安全操作规程
	 */
	private List<PhotoPic> picList;
	
	/**
	 * 附件
	 */
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private String fileId; //附件id
	
	/**
	 * 初始化岗位安全操作规程列表
	 * author：陆婷
	 * 2013-08-20
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
	 * 查询岗位安全操作规程列表
	 * author：陆婷
	 * 2013-08-20
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxGwaq){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxGwaq.getGwmc()) && (0 < jshxGwaq.getGwmc().trim().length())){
				paraMap.put("gwmc", "%" + jshxGwaq.getGwmc().trim() + "%");
			}

			if ((null != jshxGwaq.getQymc()) && (0 < jshxGwaq.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxGwaq.getQymc().trim() + "%");
			}
			if ((null != jshxGwaq.getSzzid()) && (0 < jshxGwaq.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxGwaq.getSzzid().trim() );
			}
			if ((null != jshxGwaq.getSzc() )&& (0 < jshxGwaq.getSzc().trim().length())){
				paraMap.put("szc",jshxGwaq.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxGwaqService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看岗位安全操作规程详情
	 * author：陆婷
	 * 2013-08-20
	 */
	public String view() throws Exception{
		if((null != jshxGwaq)&&(null != jshxGwaq.getId()))
		{
			jshxGwaq = jshxGwaqService.getById(jshxGwaq.getId());
			if(jshxGwaq.getProId() == null || "".equals(jshxGwaq.getProId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxGwaq.setProId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxGwaq.getProId());
				map.put("picType","gwaq");
			    picList = szwxPhotoService.findPicPath(map);//获取安全操作规程
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxGwaq.setProId(linkId);
		}
		return VIEW;
	}

	/**
	 * 初始化岗位安全操作规程修改信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存岗位安全操作规程信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxGwaq.setSzzid(dept.getDeptCode());
			jshxGwaq.setSzzname(dept.getDeptName());
			jshxGwaq.setQyid(company.getId());
			jshxGwaq.setQymc(company.getCompanyname());
			jshxGwaq.setDeptId(this.getLoginUserDepartmentId());
			jshxGwaq.setDelFlag(0);
			jshxGwaq.setCreateUserID(this.getLoginUserId());
			jshxGwaq.setCreateTime(new Date());
			jshxGwaq.setQylx(company.getQylx());
			jshxGwaq.setHyfl(company.getHyfl());
			jshxGwaq.setQygm(company.getQygm());
			jshxGwaq.setQyzclx(company.getQyzclx());
			jshxGwaq.setIfwhpqylx(company.getIfwhpqylx());
			jshxGwaq.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxGwaq.setIfzywhqylx(company.getIfzywhqylx());
			jshxGwaq.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxGwaq.setSzc(company.getSzc());
			jshxGwaq.setSzcname(company.getSzcname());
			jshxGwaq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxGwaq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxGwaqService.save(jshxGwaq);
		}else{
			jshxGwaq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxGwaq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxGwaqService.update(jshxGwaq);
		}
		return RELOAD;
	}

	/**
	 * 删除岗位安全操作规程信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String delete() throws Exception{
	    try{
			jshxGwaqService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
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
  					"../../virtualdir/upload/file/gwaq/"
  					);
  			  		//附件上传
  					uploadFile.uploadToServer();
  					
  					PhotoPic taskPic = new PhotoPic();
  					taskPic.setCreateTime(new Date());
  					taskPic.setPicName("gwaq/" + rename);
					taskPic.setPicType("gwaq");
					taskPic.setTaskProId(jshxGwaq.getProId());
  					taskPic.setDelFlag(0);
  					taskPic.setFileName(filename);//保存原文件的名称
  					szwxPhotoService.save(taskPic);//在此处调用图片的保存
  	  				extName = taskPic.getId();
  				}
  				HttpServletResponse response = ServletActionContext.getResponse();
  			    response.getWriter().write(filename + ";" + extName);
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

	public JshxGwaq getJshxGwaq(){
		return this.jshxGwaq;
	}

	public void setJshxGwaq(JshxGwaq jshxGwaq){
		this.jshxGwaq = jshxGwaq;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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
       
    
}
