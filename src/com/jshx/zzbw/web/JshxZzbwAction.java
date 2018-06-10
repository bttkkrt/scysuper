/**
 * Class Name: JshxZzbwAction
 * Class Description：安全生产关键装置和重点部位
 */
package com.jshx.zzbw.web;

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
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzbw.service.JshxZzbwService;

public class JshxZzbwAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZzbw jshxZzbw = new JshxZzbw();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxZzbwService jshxZzbwService;
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
	 * 本装置或部位安全操作规程
	 */
	private List<PhotoPic> picList;
	
	private List<PhotoPic> picList1;
	
	/**
	 * 附件
	 */
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private String fileId; //附件id
	private String isshow;
	
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	/**
	 * 初始化安全生产关键装置和重点部位列表
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
	 * 查询安全生产关键装置和重点部位列表
	 * author：陆婷
	 * 2013-08-20
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		
		if(null != jshxZzbw){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZzbw.getGjzzmc()) && (0 < jshxZzbw.getGjzzmc().trim().length())){
				paraMap.put("gjzzmc", "%" + jshxZzbw.getGjzzmc().trim() + "%");
			}

			if ((null != jshxZzbw.getQymc()) && (0 < jshxZzbw.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZzbw.getQymc().trim() + "%");
			}

			if ((null != jshxZzbw.getZrr()) && (0 < jshxZzbw.getZrr().trim().length())){
				paraMap.put("zrr", "%" + jshxZzbw.getZrr().trim() + "%");
			}
			if ((null != jshxZzbw.getSzzid()) && (0 < jshxZzbw.getSzzid().trim().length())){
				paraMap.put("deptCode",  jshxZzbw.getSzzid().trim() );
			}
			if ((null != jshxZzbw.getQyid()) && (0 < jshxZzbw.getQyid().trim().length())){
				paraMap.put("qyid",  jshxZzbw.getQyid().trim() );
			}
			if ((null != jshxZzbw.getSzc() )&& (0 < jshxZzbw.getSzc().trim().length())){
				paraMap.put("szc",jshxZzbw.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxZzbwService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看安全生产关键装置和重点部位详情
	 * author：陆婷
	 * 2013-08-20
	 */
	public String view() throws Exception{
		if((null != jshxZzbw)&&(null != jshxZzbw.getId()))
		{
			jshxZzbw = jshxZzbwService.getById(jshxZzbw.getId());
			if(jshxZzbw.getProId() == null || "".equals(jshxZzbw.getProId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxZzbw.setProId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxZzbw.getProId());
				map.put("picType","zzbw");
			    picList = szwxPhotoService.findPicPath(map);//获取安全操作规程
			    map.put("picType","zzbwtp");
			    picList1 = szwxPhotoService.findPicPath(map);//获取安全操作规程
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxZzbw.setProId(linkId);
		}
		return VIEW;
	}

	/**
	 * 初始化安全生产关键装置和重点部位修改信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存安全生产关键装置和重点部位信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxZzbw.setSzzid(dept.getDeptCode());
			jshxZzbw.setSzzname(dept.getDeptName());
			jshxZzbw.setQyid(company.getId());
			jshxZzbw.setQymc(company.getCompanyname());
			jshxZzbw.setDeptId(this.getLoginUserDepartmentId());
			jshxZzbw.setDelFlag(0);
			jshxZzbw.setCreateUserID(this.getLoginUserId());
			jshxZzbw.setCreateTime(new Date());
			jshxZzbw.setQylx(company.getQylx());
			jshxZzbw.setHyfl(company.getHyfl());
			jshxZzbw.setQygm(company.getQygm());
			jshxZzbw.setQyzclx(company.getQyzclx());
			jshxZzbw.setIfwhpqylx(company.getIfwhpqylx());
			jshxZzbw.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxZzbw.setIfzywhqylx(company.getIfzywhqylx());
			jshxZzbw.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			jshxZzbw.setSzc(company.getSzc());
			jshxZzbw.setSzcname(company.getSzcname());
			jshxZzbw.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZzbw.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZzbwService.save(jshxZzbw);
		}else{
			jshxZzbw.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZzbw.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZzbwService.update(jshxZzbw);
		}
		return RELOAD;
	}

	/**
	 * 删除安全生产关键装置和重点部位信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public String delete() throws Exception{
	    try{
			jshxZzbwService.deleteWithFlag(ids);
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
	
	public String uploads()
	{
		return SUCCESS;
	}
	/**
	 * 本装置或部位安全操作规程上传
	 * author：陆婷
	 * 2013-08-20
	 */
	public void fileUpload()
	{
		try
  		{
  			String filename = "";
  			String extName = "";
  			String[] proIds = jshxZzbw.getProId().split(",");
  			String proId = proIds[0];
  			String type = proIds[1];
  			if ((Filedata != null) && (!Filedata.isEmpty())) {
  				for (int i = 0; i < Filedata.size(); i++) {
  			  		//获取文件对象
  					File file = (File)Filedata.get(i);
  					UploadFile uploadFile = new UploadFile();
  					uploadFile.setFileName(filename);
  					
  					uploadFile.setUploadFile(file);
  					String rename = getDatedFName(FiledataFileName.get(i));
  					String path = Struts2Util.getServletContext().getRealPath(
  					"/");
  					uploadFile.setId(rename);
  					uploadFile.setFilePath(path + 
  					"../../virtualdir/upload/file/zzbw/"
  					);
  			  		//附件上传
  					uploadFile.uploadToServer();
  					
  					PhotoPic taskPic = new PhotoPic();
  					taskPic.setCreateTime(new Date());
  					taskPic.setPicName("zzbw/"+ rename);
  					if("0".equals(type))
  					{
  						filename = (String)FiledataFileName.get(i);
  						taskPic.setPicType("zzbw");
  					}
  					else
  					{
  						filename = "zzbw/" + rename;
  						taskPic.setPicType("zzbwtp");
  					}
					taskPic.setTaskProId(proId);
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

		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
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
	 * 本装置或部位安全操作规程删除
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
	 * 本装置或部位安全操作规程下载
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

	public JshxZzbw getJshxZzbw(){
		return this.jshxZzbw;
	}

	public void setJshxZzbw(JshxZzbw jshxZzbw){
		this.jshxZzbw = jshxZzbw;
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

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}
       
    
}
