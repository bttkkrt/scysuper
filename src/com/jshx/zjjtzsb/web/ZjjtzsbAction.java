package com.jshx.zjjtzsb.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.jshx.utils.ExcelTools;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsb.service.ZjjtzsbService;

public class ZjjtzsbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zjjtzsb zjjtzsb = new Zjjtzsb();

	/**
	 * 业务类
	 */
	@Autowired
	private ZjjtzsbService zjjtzsbService;
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
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String companyId;
	
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;
	/**
	 * 整改后图片
	 */
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private String type;
	
	/**
	 * 初始化质监局特种设备作业人员
	 * 陆婷 
	 * 2013-12-27
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith("003"))//质监局
		{
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			if(company != null && company.getId() != null)
			{
				companyId = company.getId();
			}
			flag = "3";
		}
		else
		{
			flag = "0";
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
		    
		if(null != zjjtzsb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zjjtzsb.getQymc()) && (0 < zjjtzsb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zjjtzsb.getQymc().trim() + "%");
			}
			if ((null != zjjtzsb.getState()) && (0 < zjjtzsb.getState().trim().length())){
				paraMap.put("state", zjjtzsb.getState().trim());
			}
			if ((null != zjjtzsb.getSzzid()) && (0 < zjjtzsb.getSzzid().trim().length())){
				paraMap.put("szzid", zjjtzsb.getSzzid().trim());
			}

			if ((null != zjjtzsb.getSblb()) && (0 < zjjtzsb.getSblb().trim().length())){
				paraMap.put("sblb", "%" + zjjtzsb.getSblb().trim() + "%");
			}

			if ((null != zjjtzsb.getSbdah()) && (0 < zjjtzsb.getSbdah().trim().length())){
				paraMap.put("sbdah", "%" + zjjtzsb.getSbdah().trim() + "%");
			}

			if ((null != zjjtzsb.getZcdm()) && (0 < zjjtzsb.getZcdm().trim().length())){
				paraMap.put("zcdm", "%" + zjjtzsb.getZcdm().trim() + "%");
			}

			if ((null != zjjtzsb.getJcry()) && (0 < zjjtzsb.getJcry().trim().length())){
				paraMap.put("jcry", "%" + zjjtzsb.getJcry().trim() + "%");
			}
			if ((null != zjjtzsb.getSzc()) && (0 < zjjtzsb.getSzc().trim().length())){
				paraMap.put("szc", zjjtzsb.getSzc().trim());
			}

		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid",deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		else if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid",  company.getId());
		}
		pagination = zjjtzsbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zjjtzsb)&&(null != zjjtzsb.getId()))
		{
			zjjtzsb = zjjtzsbService.getById(zjjtzsb.getId());
			Map map = new HashMap();
			map.put("taskProId",zjjtzsb.getLinkId());
			map.put("picType","zjjtzsb");
			picList = szwxPhotoService.findPicPath(map);//获取整改后图片
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
		if(zjjtzsb != null && zjjtzsb.getQyid() != null && !"".equals(zjjtzsb.getQyid()))
		{
			Map map = new HashMap();
			map.put("companyId", zjjtzsb.getQyid());
			CompanyBackUp company = companyService.getCompanyBackupById(map);
			if(!zjjtzsb.getQymc().equals(company.getCompanyname()))
			{
				zjjtzsb.setQyid(null);
				Department dept = deptService.findDeptByDeptCode(zjjtzsb.getSzzid());
				zjjtzsb.setSzzid(dept.getDeptCode());
				zjjtzsb.setSzzname(dept.getDeptName());
				zjjtzsb.setShbs("1");
				zjjtzsb.setState("1");
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				zjjtzsb.setSzzid(dept.getDeptCode());
				zjjtzsb.setSzzname(dept.getDeptName());
				zjjtzsb.setQyid(company.getId());
				zjjtzsb.setQymc(company.getCompanyname());
				zjjtzsb.setShbs("0");
				zjjtzsb.setState("0");
				zjjtzsb.setSzc(company.getSzc());
				zjjtzsb.setSzcname(company.getSzcname());
			}
		}
		else if(zjjtzsb.getSzzid() != null && !"".equals(zjjtzsb.getSzzid()))
		{
			Department dept = deptService.findDeptByDeptCode(zjjtzsb.getSzzid());
			zjjtzsb.setSzzid(dept.getDeptCode());
			zjjtzsb.setSzzname(dept.getDeptName());
		}
		if ("add".equalsIgnoreCase(this.flag)){
			zjjtzsb.setDeptId(this.getLoginUserDepartmentId());
			zjjtzsb.setDelFlag(0);
			zjjtzsb.setCreateUserID(this.getLoginUserId());
			zjjtzsb.setCreateTime(new Date());
			zjjtzsb.setIsFirst("0");
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			zjjtzsb.setLinkId(linkId);
			zjjtzsbService.save(zjjtzsb);
		}else{
			zjjtzsbService.update(zjjtzsb);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zjjtzsbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 删除信息
	 */
	public String deletes() throws Exception{
	    try{
			zjjtzsbService.deleteAll();
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 文件导入 陆婷 2013-12-27
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 文件保存 陆婷 2013-12-27
	 * @return
	 */
	public void saveFile() throws Exception{
		try {
			if(Filedata!= null && !Filedata.isEmpty()){
				List<List<String>> lists = ExcelTools.parseExcel(Filedata.get(0),"0");
				for(List<String> l:lists){
						int len = l.size();
						if(len<14){
							for(int j=0;j<14-len;j++){
								l.add("");
							}
						}
					  int i=0;
					  if(l.get(2) != null && !"".equals(l.get(2)) && l.get(0) != null && !"".equals(l.get(0)))
					  {
						  String szzid = (String) companyService.findCompanyTypeNameByValue(l.get(2),"4028e56c4014f290014014f981510003");
						  if(szzid != null && !"".equals(szzid))
						  {
							  Zjjtzsb zjjtzsb = new Zjjtzsb();
							  zjjtzsb.setQymc(l.get(i++));
							  Map map = new HashMap();
							  map.put("companyname", zjjtzsb.getQymc());
							  CompanyBackUp company = companyService.getCompanyBackupById(map);
							  if(company != null && company.getId() != null)
							  {
								  zjjtzsb.setQyid(company.getId());
								  zjjtzsb.setState("0");
								  zjjtzsb.setShbs("0");
								  zjjtzsb.setSzc(company.getSzc());
								  zjjtzsb.setSzcname(company.getSzcname());
							  }
							  else
							  {
								  zjjtzsb.setState("1");
								  zjjtzsb.setShbs("1");
							  }
							  zjjtzsb.setDwdz(l.get(i++));
							  zjjtzsb.setSzzname(l.get(i++));
							  zjjtzsb.setSzzid(szzid);
							  zjjtzsb.setSblb(l.get(i++));
							  zjjtzsb.setSbdah(l.get(i++));
							  zjjtzsb.setZcdm(l.get(i++));
							  zjjtzsb.setJyrq(l.get(i++));
							  zjjtzsb.setJyjl(l.get(i++));
							  zjjtzsb.setJcrq(l.get(i++));
							  zjjtzsb.setJcry(l.get(i++));
							  zjjtzsb.setZywt(l.get(i++));
							  zjjtzsb.setDwlxr(l.get(i++));
							  zjjtzsb.setDh(l.get(i++));
							  zjjtzsb.setBz(l.get(i++));
							  zjjtzsb.setDelFlag(0);
							  zjjtzsb.setCreateTime(new Date());
							  zjjtzsb.setDeptId(this.getLoginUserDepartmentId());
							  zjjtzsb.setCreateUserID(this.getLoginUserId());
							  zjjtzsb.setIsFirst("0");
							  String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
							  zjjtzsb.setLinkId(linkId);
							  zjjtzsbService.save(zjjtzsb);
						  }
					  }
				}
				
			}
			//在此处调用图片的保存
			this.getResponse().getWriter().write("1");
		} catch (Exception e) {
				e.printStackTrace();
				this.getResponse().getWriter().write("0");
			}
	}
	
	/**
	 * 跳转至审核页面
	 * author：陆婷
	 * 2013-12-27
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
	}
	/**
	 * 保存审核信息
	 * author：陆婷
	 * 2013-12-27
	 */
	public String shenhesave()
	{
		if((null != zjjtzsb)&&(null != zjjtzsb.getId()))
		{
			Zjjtzsb z = zjjtzsbService.getById(zjjtzsb.getId());
			String state = zjjtzsb.getState();
			String remark = zjjtzsb.getRemark();
			z.setState(state);
			z.setIsFirst("1");
			if("4".equals(state))
			{
				z.setRemark(remark);
				if(z.getQyid() != null && !"".equals(z.getQyid()))
				{
					z.setShbs("0");
				}
				else
				{
					z.setShbs("1");
				}
				z.setZgqk("");
				z.setZgwcsj("");
				Map map = new HashMap();
				map.put("taskProId",z.getLinkId());
				map.put("picType","zjjtzsb");
				picList = szwxPhotoService.findPicPath(map);//获取整改后图片
				for(PhotoPic p:picList)
				{
					szwxPhotoService.deleteImageWithFlag(p.getId());
				}
			}
			else
			{
				z.setShbs("2");
			}
			zjjtzsbService.update(z);
		}
		
		return RELOAD;
	}
	/**
	 * 企业报验
	 * 陆婷
	 * 2013-12-27
	 */
	public String zjjtzsbBy() throws Exception{
	    try{
	    	if((null != zjjtzsb)&&(null != zjjtzsb.getId()))
			{
				zjjtzsb = zjjtzsbService.getById(zjjtzsb.getId());
				zjjtzsb.setState("1");
				zjjtzsb.setShbs("1");
				zjjtzsb.setIsFirst("1");
				zjjtzsb.setRemark("");
				zjjtzsbService.update(zjjtzsb);
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 上报整改信息
	 * author：陆婷
	 * 2014-1-3
	 */
	public String zjjtzsbBj() throws Exception{
		view();
		return SUCCESS;
	}
	
	/**
	 * 保存整改信息
	 * author：陆婷
	 * 2014-1-3
	 */
	public String shangbaosave()
	{
		if((null != zjjtzsb)&&(null != zjjtzsb.getId()))
		{
			Zjjtzsb z = zjjtzsbService.getById(zjjtzsb.getId());
			String zgqk  = zjjtzsb.getZgqk();
			String zgwcsj = zjjtzsb.getZgwcsj();
			z.setIsFirst("1");
			z.setZgqk(zgqk);
			z.setZgwcsj(zgwcsj);
			z.setState("2");
			z.setShbs("2");
			zjjtzsbService.update(z);
		}
		
		return RELOAD;
	}
	
	/**
	 * 跳转到图片导入界面 
	 * 陆婷 
	 * 2014-1-3
	 */
	public String picUpload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 图片保存
	 * 陆婷 
	 * 2014-1-3
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
	}
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * 陆婷 
	 * 2014-1-3
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
	 * 陆婷 
	 * 2014-1-3
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
	 * 陆婷 
	 * 2014-1-3
	 */
	public void savePic()
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

	public Zjjtzsb getZjjtzsb(){
		return this.zjjtzsb;
	}

	public void setZjjtzsb(Zjjtzsb zjjtzsb){
		this.zjjtzsb = zjjtzsb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

}
