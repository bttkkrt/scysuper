/**
 * Class Name: JshxZsglAction
 * Class Description：资格证书扫描件
 */
package com.jshx.zsgl.web;

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
import com.jshx.zsgl.entity.JshxZsgl;
import com.jshx.zsgl.service.JshxZsglService;

public class JshxZsglAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZsgl jshxZsgl = new JshxZsgl();
	
	 /**
     *附件列表
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxZsglService jshxZsglService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;

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
	/**
	 * 初始化列表
	 * @return
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
	 * 执行资格证书管理列表 查询的方法，返回json格式数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZsgl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZsgl.getLinkman()) && (0 < jshxZsgl.getLinkman().trim().length())){
				paraMap.put("linkman", "%" + jshxZsgl.getLinkman().trim() + "%");
			}

			if ((null != jshxZsgl.getJshxType()) && (0 < jshxZsgl.getJshxType().trim().length())){
				paraMap.put("jshxType", jshxZsgl.getJshxType().trim());
			}
			if ((null != jshxZsgl.getQymc()) && (0 < jshxZsgl.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZsgl.getQymc().trim() + "%");
			}
			if ((null != jshxZsgl.getSzzid()) && (0 < jshxZsgl.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZsgl.getSzzid().trim() );
			}
			if ((null != jshxZsgl.getSzc() )&& (0 < jshxZsgl.getSzc().trim().length())){
				paraMap.put("szc",jshxZsgl.getSzc().trim());
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
		 
		pagination = jshxZsglService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看资格证书管理列表单条详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != jshxZsgl)&&(null != jshxZsgl.getId())){
			jshxZsgl = jshxZsglService.getById(jshxZsgl.getId());
			if(jshxZsgl.getLinkId() == null || "".equals(jshxZsgl.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxZsgl.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxZsgl.getLinkId());
				map.put("picType","zsgl");
			    picList01 = szwxPhotoService.findPicPath(map);//获取附件列表
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxZsgl.setLinkId(linkId);
		}
		return VIEW;
	}

	/**
	 * 初始化资格证书管理修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存资格证书管理列表信息（包括新增和修改）
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxZsgl.setSzzid(dept.getDeptCode());
			jshxZsgl.setSzzname(dept.getDeptName());
			jshxZsgl.setQyid(company.getId());
			jshxZsgl.setQymc(company.getCompanyname());
			jshxZsgl.setDeptId(this.getLoginUserDepartmentId());
			jshxZsgl.setDelFlag(0);
			jshxZsgl.setCreateUserID(this.getLoginUserId());
			jshxZsgl.setCreateTime(new Date());
			jshxZsgl.setQylx(company.getQylx());
			jshxZsgl.setHyfl(company.getHyfl());
			jshxZsgl.setQygm(company.getQygm());
			jshxZsgl.setQyzclx(company.getQyzclx());

			jshxZsgl.setIfwhpqylx(company.getIfwhpqylx());
			jshxZsgl.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxZsgl.setIfzywhqylx(company.getIfzywhqylx());
			jshxZsgl.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			jshxZsgl.setSzc(company.getSzc());
			jshxZsgl.setSzcname(company.getSzcname());
			jshxZsgl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZsgl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZsglService.save(jshxZsgl);
		}else{
			jshxZsgl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZsgl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZsglService.update(jshxZsgl);
		}
		return RELOAD;
	}

	/**
	 * 删除资格证书管理列表信息 根据id
	 */
	public String delete() throws Exception{
	    try{
			jshxZsglService.deleteWithFlag(ids);
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
	 * 图片保存 lj  2013-05-15
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
			root = root.replace("webapps","virtualdir/upload/file/zsgl/");
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
			String picname = "zsgl/"+imgName;
			taskPic.setPicName(picname);
			taskPic.setTaskProId(jshxZsgl.getLinkId());
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setPicType("zsgl");//类型设置为资格证书管理
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
	 * 删除资格证书信息图片
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
	 *  李军 2013-05-15
	 */
	public void saveFile()
  	{
  		try
  		{//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/file/";
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

	public JshxZsgl getJshxZsgl(){
		return this.jshxZsgl;
	}

	public void setJshxZsgl(JshxZsgl jshxZsgl){
		this.jshxZsgl = jshxZsgl;
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

	public List<PhotoPic> getPicList01() {
		return picList01;
	}

	public void setPicList01(List<PhotoPic> picList01) {
		this.picList01 = picList01;
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
       
    
}
