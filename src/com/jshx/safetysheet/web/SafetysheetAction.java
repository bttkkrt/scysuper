/**
 * Class Name: SafetysheetAction
 * Class Description：安全生产规章制度信息管理
 */
package com.jshx.safetysheet.web;

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
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.entity.Safetysheet;
import com.jshx.safetysheet.service.SafetysheetService;
/**
 * 
 * @author 高强
 * @date   8月14
 * @function 安全生产规章制度信息管理
 *
 */
public class SafetysheetAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Safetysheet safetysheet = new Safetysheet();

	/**
	 * 业务类
	 */
	@Autowired
	private SafetysheetService safetysheetService;
	 @Autowired
		private SzwxPhotoService szwxPhotoService;
	 /**
		 * 部门业务类
		 */
	 @Autowired
		private DeptService deptService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 图片地址
	 */
    private String picName;
	/**
	 * @author gq
	 * @date 8yue 15 
	 *@function  新增字段 
	 */
	private String type;
	
	
	   /** @author gq
	 * @date 8yue 15 
     * 原文件名称
     */
    private String fileName;
    
    /** @author gq
	 * @date 8yue 15 
     *用于区分监管部门0和企业1
     */
    private String role;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	//SYSTEMNAME对应的文件列表

	private List filelistSystemname;
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 用于上传附件的属性
	 */
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
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
	 * 企业业务类
	 */
	@Autowired
	private CompanyService companyService;
	
	private CompanyBackUp company;
	
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 跳转到图片导入界面 
	 * @return 上传附件页面
	 */
	public String upload() throws Exception{
		
		return SUCCESS;
	}
	
	 /** @author gq
	 * @date 8yue 16 
     *用于区分监管部门0和更高级别1
     */
    private String visable;
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
		if(this.getLoginUser().getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//监管部门以下的角色
		    this.visable="0";
		else 
			 this.visable="1";
		String[] userroles=this.getLoginUser().getRoleIds();
		List<String> roles=new ArrayList<String>();
		for(String role:userroles)
		{
			roles.add(role);
		}
		if(roles.contains(SysPropertiesUtil.getProperty("qiyeUserRoleId"))||roles.contains(SysPropertiesUtil.getProperty("tempqiyeUserRoleId")))
			return "qiye";
			else
		return SUCCESS;
	}
	/**
	 * @author gq
	 * @date 8yue21
	 * @param first模块拼音
	 * @return 模块中文名称
	 */
//	public String getModuleName(String  first){
//		if(first.equals("cqtp"))
//			return "厂区平面图及工厂相关图片";
//		else if(first.equals("jxzy"))
//			return "检修作业规程";
//		else if(first.equals("qysclct"))
//			return "企业生产流程图";
//		else if(first.equals("yfsgsstz"))
//			return "设施台帐";
//		else if(first.equals("aqsc"))
//			return "安全生产规章制度";
//		else if(first.equals("aqjsbz"))
//			return "安全警示标示设置";
//		else if(first.equals("xfssfbt"))
//			return "消防设施分布图";
//		else if(first.equals("wxhxpjsxm"))
//			return "危险化学品建设项目行政许可";
//		else if(first.equals("zyfzrlzbg"))
//			return "主要负责人履职报告";
//		else if(first.equals("whpyjya"))
//			return "危险化学品事故应急救援预案及备案告知书";
//		else if(first.equals("zycsjcbg"))
//				return "作业场所检测报告";
//		else if(first.equals("ygtjbghzb"))
//			return "员工体检报告汇总表";
//		else if(first.equals("aqpjbg"))
//			return "安全评价报告";
//		else if(first.equals("whpaqzlzd"))
//			return "危化品企业安全管理制度";
//		else if(first.equals("zywsglzd"))
//			return "职业卫生安全管理制度";
//		else if(first.equals("ldfhyppb"))
//			return "劳动防护用品配备情况";
//		else if(first.equals("qysbylb"))
//			return "企业设备一览表";
//		else if(first.equals("aqsbdjtz"))
//			return "安全设施登记台账";
//		else if(first.equals("zdwxyjbtz"))
//			return "重大危险源基本特征";
//		else if(first.equals("zdwxybasqb"))
//			return "重大危险源备案申请表";
//		else if(first.equals("zdwxybadjb"))
//			return "重大危险源备案登记表";
//		else if(first.equals("zdwxyhxsqb"))
//			return "重大危险源核销申请表";
//		else if(first.equals("zdwxyhxdjb"))
//			return "重大危险源核销登记表";
//		else
//			return "其他";
//	}
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 保存附件
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
			destFName.append(root).append("file/");
			PhotoPic taskPic = new PhotoPic();
			String[] ts = type.split(",");
			if(ts.length>=2){
				taskPic.setPicType(ts[0]);
				taskPic.setTaskProId(ts[1]);
			}
			
			destFName.append(taskPic.getPicType()+"/");
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
			//保存图片信息到PhotoPic表中
			
			taskPic.setCreateTime(new Date());
			taskPic.setPicName(taskPic.getPicType()+"/"+imgName);
		
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 高强 8月15
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", taskPic.getPicType()+"/"+imgName);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * @author gq
	 * @date 8yue 15
	 *  @function 自定义上传附件的名称 以时间格式来处理
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
	 * @author gq
	 * @date 8yue 15
	 * @function 删除信息图片
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
	 *  @author gq
	 * @date 8yue 15
	 * 执行查询的方法，返回json数据 制度名称
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safetysheet){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if(safetysheet.getSystemname()!=null&&safetysheet.getSystemname().length()>0)
				paraMap.put("systemname", safetysheet.getSystemname());
			
			if(safetysheet.getComName()!=null&&safetysheet.getComName().length()>0)
				paraMap.put("comName", "%"+safetysheet.getComName()+"%");
			
			if(safetysheet.getDeptId()!=null&&safetysheet.getDeptId().length()>0)
				paraMap.put("deptCode", safetysheet.getDeptId());
			
			if ((null != safetysheet.getSzc() )&& (0 < safetysheet.getSzc().trim().length())){
				paraMap.put("szc",safetysheet.getSzc().trim());
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
		 
		pagination = safetysheetService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 图片 文字 另存为功能 
	 *  高强 2013-08-15
	 *  图片或附件的下载
	 */
	public void downFile()
  	{
  		try
  		{
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
	            browName = new String(photoPic.getFileName().getBytes("GBK"), "ISO-8859-1");
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
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != safetysheet)&&(null != safetysheet.getId()))
			safetysheet = safetysheetService.getById(safetysheet.getId());
		if(safetysheet.getLinkid()==null)
		{
		safetysheet.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",safetysheet.getLinkid());
		map.put("picType","aqsc");
	    list = szwxPhotoService.findPicPath(map);//获取执法文书材料
	    
		String[] userroles=this.getLoginUser().getRoleIds();
		List<String> roles=new ArrayList<String>();
		for(String role:userroles)
		{
			roles.add(role);
		}
		if(roles.contains(SysPropertiesUtil.getProperty("qiyeUserRoleId"))||roles.contains(SysPropertiesUtil.getProperty("tempqiyeUserRoleId")))
			this.role="1";
		else
			this.role="0";
			
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
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			if ("add".equalsIgnoreCase(this.flag)){
				safetysheet.setDelFlag(0);
				safetysheet.setDeptName(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
				safetysheet.setDeptId(company.getDwdz1());
				safetysheet.setComId(company.getId());
				safetysheet.setComName(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
				safetysheet.setQylx(company.getQylx());
				safetysheet.setHyfl(company.getHyfl());
				safetysheet.setQygm(company.getQygm());
				safetysheet.setQyzclx(company.getQyzclx());
				safetysheet.setIfwhpqylx(company.getIfwhpqylx());
				safetysheet.setIfyhbzjyqy(company.getIfyhbzjyqy());
				safetysheet.setIfzywhqylx(company.getIfzywhqylx());
				safetysheet.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				safetysheet.setDelFlag(0);
				safetysheet.setCreateUserID(this.getLoginUserId());
				safetysheet.setCreateTime(new Date());
				safetysheet.setSzc(company.getSzc());
				safetysheet.setSzcname(company.getSzcname());
				safetysheet.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				safetysheet.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				safetysheetService.save(safetysheet);
			}else{
				safetysheet.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				safetysheet.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				safetysheetService.update(safetysheet);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			safetysheetService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
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

	public Safetysheet getSafetysheet(){
		return this.safetysheet;
	}

	public void setSafetysheet(Safetysheet safetysheet){
		this.safetysheet = safetysheet;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public List getFilelistSystemname(){
		return this.filelistSystemname;
	}

	public void setFilelistSystemname(List filelistSystemname){
		this.filelistSystemname = filelistSystemname;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public List<PhotoPic> getList() {
		return list;
	}
	public void setList(List<PhotoPic> list) {
		this.list = list;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	public String getVisable() {
		return visable;
	}

	public void setVisable(String visable) {
		this.visable = visable;
	}


	
}
