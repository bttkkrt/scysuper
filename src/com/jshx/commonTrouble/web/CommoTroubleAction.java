/**
 * Class Name: CommoTroubleAction
 * Class Description：一般安全隐患
 */
package com.jshx.commonTrouble.web;

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
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.entity.DeptDataBean;
import com.jshx.commonTrouble.entity.KsjcBean;
import com.jshx.commonTrouble.entity.QyDataBean;
import com.jshx.commonTrouble.entity.XzzywhBean;
import com.jshx.commonTrouble.entity.ZfwsData;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.base.vo.UploadFile;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zazPxb.entity.JshxPx;

public class CommoTroubleAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Company company = new Company();
	
	private ZfwsData zfwsData = new ZfwsData();

	private List<DeptDataBean> qylxs = new ArrayList<DeptDataBean>();
	 
	private List<XzzywhBean> xzzywhList = new ArrayList<XzzywhBean>();
	
	private List<KsjcBean> ksjcList = new ArrayList<KsjcBean>();
	 
	private DeptDataBean qylx = new DeptDataBean();
	 
	private XzzywhBean xzzywhBean = new XzzywhBean();
	
	private KsjcBean ksjcBean = new KsjcBean();
	/**
	 * 企业业务类
	 */
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	/**
	 * 实体类
	 */
	private CommoTrouble commoTrouble = new CommoTrouble();
	
	private ContentInformations contentInformations = new ContentInformations(); //公告类
	
    /**
     * 执法文书列表字段
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
	/**
	 * 整改情况列表字段
	 */
	private List<PhotoPic> picList02 = new ArrayList<PhotoPic>();

	/**
	 * 业务类
	 */
	@Autowired
	private CommoTroubleService commoTroubleService;
	 @Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private ContentAttachsService contentAttachsService;
	@Autowired
	private DeptService deptService;

	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String queryJhwcsjStart;

	private String queryJhwcsjEnd;

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
	
	/**
	 * 新增字段 1 表示信息填报  2 表示信息审核
	 */
	private String type;
	
	/**
	 * 新增关联图片 附件材料的id
	 */
	private  String linkId;
	
	/**
	 * 添加检查时间 范围字段 2013-07-25
	 * @return
	 */
	private String jcBeginDate;
	private String jcEndDate;
	/**
	 * 添加完成整改时间 范围字段 2013-07-25
	 * @return
	 */
	private String wcBeginDate;
	private String wcEndDate;
	
	private String createUserID;
	private String deptId;
	
	private String[] shenheList ;
	private String tempDeptCode;
	
	private List<Department> deptlist = new ArrayList<Department>();
	
	private List<Department> deptList = new ArrayList<Department>();
	
	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public List<Department> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}
	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String init(){
		createUserID = this.getLoginUserId();
		deptId = this.getLoginUserDepartmentId();
		String dpcode = this.getLoginUserDepartment().getDeptCode();
		if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			type="3";
		}
		deptList = commoTroubleService.getAllDeptListByMap(null);
		setRequestAttribute("shzt", getRequestParameter("shzt"));
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		if(null != commoTrouble){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != commoTrouble.getSzz()) && (0 < commoTrouble.getSzz().trim().length())){
				paraMap.put("szz", commoTrouble.getSzz().trim());
			}

			if ((null != commoTrouble.getQymc()) && (0 < commoTrouble.getQymc().trim().length())){
				paraMap.put("qymc", "%" + commoTrouble.getQymc().trim() + "%");
			}

			if ((null != commoTrouble.getJcsj()) && (0 < commoTrouble.getJcsj().trim().length())){
				paraMap.put("jcsj", "%" + commoTrouble.getJcsj().trim() + "%");
			}

			if ((null != commoTrouble.getJcry()) && (0 < commoTrouble.getJcry().trim().length())){
				paraMap.put("jcry", "%" + commoTrouble.getJcry().trim() + "%");
			}

			if ((null != commoTrouble.getZfws()) && (0 < commoTrouble.getZfws().trim().length())){
				paraMap.put("zfws", commoTrouble.getZfws().trim());
			}
			
			if ((null != commoTrouble.getShzt()) && (0 < commoTrouble.getShzt().trim().length())){
				paraMap.put("shzt", commoTrouble.getShzt().trim());
			}
			
			if ((null != jcBeginDate) && (0 < jcBeginDate.trim().length())){
				paraMap.put("jcBeginDate", jcBeginDate.trim());
			}
			
			if ((null != jcEndDate) && (0 < jcEndDate.trim().length())){
				paraMap.put("jcEndDate", jcEndDate.trim());
			}
			if ((null != wcBeginDate) && (0 < wcBeginDate.trim().length())){
				paraMap.put("wcBeginDate", wcBeginDate.trim());
			}
			
			if ((null != wcEndDate) && (0 < wcEndDate.trim().length())){
				paraMap.put("wcEndDate", wcEndDate.trim());
			}
			if ((null != commoTrouble.getSzc() )&& (0 < commoTrouble.getSzc().trim().length())){
				paraMap.put("szc",commoTrouble.getSzc().trim());
			}
			if ((null != commoTrouble.getDeptId()) && (0 < commoTrouble.getDeptId().trim().length())){
				paraMap.put("deptId", commoTrouble.getDeptId().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		/*
		String dpcode = this.getLoginUserDepartment().getDeptCode();//判断是否是企业部门
		if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			type="3";
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qymcId", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))){
			//乡镇安监中队能查看本部门登记的安全隐患和重大安全隐患，也能查看安监局各部门登记的安全隐患和重大安全隐患 
			if(dpcode.length() == 9)
			{
				paraMap.put("szz", dpcode);
			}
			else
			{
				paraMap.put("szc", dpcode);
			}
			paraMap.put("deptCode", this.getLoginUserDepartment().getChildDeptIds());
		}
		else if(dpcode.startsWith("002001")) //危化品管理科
		{
			paraMap.put("ifwhpqylx", "1");
			paraMap.put("deptCode", this.getLoginUserDepartment().getChildDeptIds());
		}
		else if(dpcode.startsWith("002002")) //职业健康管理科
		{
			paraMap.put("ifzywhqylx", "1");
			paraMap.put("deptCode", this.getLoginUserDepartment().getChildDeptIds());
		}
		else if(dpcode.startsWith("002004")) //综合科
		{
			paraMap.put("ifyhbzjyqy", "1");
			paraMap.put("deptCode", this.getLoginUserDepartment().getChildDeptIds());
		}
//		else if(dpcode.startsWith("002003")) //监察大队
//		{
//			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
//		}
//		else if(dpcode.startsWith("002005")) //办公室
//		{
//			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
//		}
		*/
		try {
			pagination = commoTroubleService.findByPage(pagination, paraMap);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息 lj 2013-07-18
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != commoTrouble)&&(null != commoTrouble.getId())){
			commoTrouble = commoTroubleService.getById(commoTrouble.getId());
			if(commoTrouble.getShenhe() != null && !"".equals(commoTrouble.getShenhe()))
			{
				shenheList = commoTrouble.getShenhe().split("#");
			}
			if(commoTrouble.getLinkId() == null || "".equals(commoTrouble.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				commoTrouble.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",commoTrouble.getLinkId());
				map.put("picType","zftp");
			    picList01 = szwxPhotoService.findPicPath(map);//获取执法文书材料
			    map.put("picType","zgqk");
			    picList02 = szwxPhotoService.findPicPath(map);//获取整改情况材料
			}
		}else{
			linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			commoTrouble.setLinkId(linkId);
			commoTrouble.setIsqysb("0");
		}

		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
	    String deptCode = this.getLoginUserDepartment().getDeptCode();
	    if(deptCode.length() == countyDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
	    }else if(deptCode.length() == cityDeptCodeLength || "001".equals(deptCode)){//市局或管理员用户
	    	tempDeptCode = "1";
	    }else if(deptCode.length() == townDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, townDeptCodeLength-6);
	    }
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			type="3";
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if(commoTrouble.getCdcs() == null || "".equals(commoTrouble.getCdcs()))
		{
			commoTrouble.setCdcs("0");
		}
		if(commoTrouble.getYhs() == null || "".equals(commoTrouble.getYhs()))
		{
			commoTrouble.setYhs("0");
		}
		if(commoTrouble.getZdyhs() == null || "".equals(commoTrouble.getZdyhs()))
		{
			commoTrouble.setZdyhs("0");
		}
		if(commoTrouble.getYhzgs() == null || "".equals(commoTrouble.getYhzgs()))
		{
			commoTrouble.setYhzgs("0");
		}
		if(commoTrouble.getZdyhzgs() == null || "".equals(commoTrouble.getZdyhzgs()))
		{
			commoTrouble.setZdyhzgs("0");
		}
		if(commoTrouble.getZgzj() == null || "".equals(commoTrouble.getZgzj()))
		{
			commoTrouble.setZgzj("0");
		}
		if(commoTrouble.getQymcId() != null)
		{
//			Map mm = new HashMap();
//			mm.put("companyId", commoTrouble.getQymcId());
			
			Company	company=companyService.getById(commoTrouble.getQymcId());
			if(null!= company&&!commoTrouble.getQymc().equals(company.getCompanyname()))
			{
				commoTrouble.setQymcId(null);
				Department dept = deptService.findDeptByDeptCode(commoTrouble.getSzz());
				commoTrouble.setSzzname(dept.getDeptName());
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				if(null != dept){
					commoTrouble.setSzzname(dept.getDeptName());
				}
				commoTrouble.setQylx(company.getQylx());
				commoTrouble.setHyfl(company.getHyfl());
				commoTrouble.setQygm(company.getQygm());
				commoTrouble.setQyzclx(company.getQyzclx());
				commoTrouble.setIfwhpqylx(company.getIfwhpqylx());
				commoTrouble.setIfyhbzjyqy(company.getIfyhbzjyqy());
				commoTrouble.setIfzywhqylx(company.getIfzywhqylx());
				commoTrouble.setSzc(company.getSzc());
				commoTrouble.setSzcname(company.getSzcname());
				commoTrouble.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				commoTrouble.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			}
		}
		if ("add".equalsIgnoreCase(this.flag)){
			commoTrouble.setDeptId(this.getLoginUserDepartmentId());
			commoTrouble.setDelFlag(0);
			commoTrouble.setShzt("0");//初始化 0 待整改 1 审核通过、完成 2 审核未通过 3 已整改待审核  lj 2013-07-18   
			commoTrouble.setCreateUserID(this.getLoginUserId());
			commoTrouble.setCreateTime(new Date());
			
			commoTroubleService.save(commoTrouble);
		}else{
			if("upload".equals(flag)){//表示上传整改信息 设置状态为已整改待审核 lj 2013-09-17
				String dpcode = this.getLoginUserDepartment().getDeptCode();//判断是否是企业部门
				if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
					type="3";
					commoTrouble.setShzt("3");
				}else{
					commoTrouble.setShzt("1");
				}
			}
			commoTroubleService.update(commoTrouble);
		}
		flag = "1";
		return RELOAD;
	}

	/**
	 * 更新隐患的状态 李军 2013-07-18
	 */
	public String updateStatus() throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if((null != commoTrouble)&&(null != commoTrouble.getId())){
					String status = commoTrouble.getShzt();
					String remark = commoTrouble.getRemark();
					String a = "";
					commoTrouble = commoTroubleService.getById(commoTrouble.getId());
					if("2".equals(status))
					{
						a = "审核不通过";
					}
					else if("1".equals(status))
					{
						a = "审核通过";
					}
					String ss = this.getLoginUser().getDisplayName()+"于" + sdf.format(new Date()) + "进行了审核，审核结果为：["+a + "],备注为：[" + remark+"]";
					String shenhe = commoTrouble.getShenhe();
					if(shenhe == null || "".equals(shenhe))
					{
						shenhe = ss;
					}
					else
					{
						shenhe = shenhe + "#" + ss ;
					}
					commoTrouble.setShenhe(shenhe);
					commoTrouble.setShzt(status);
					commoTrouble.setRemark(remark);
					commoTroubleService.update(commoTrouble);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RELOAD;
	}
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			commoTroubleService.deleteWithFlag(ids);
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
	/**
	 * 图片保存 lj  2013-07-18
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
	 *  李军 2013-07-19 
	 */
	public void saveFile()
  	{
  		try
  		{
  			PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/photo/";
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
  	 * 附件上传
  	 * author：陆婷
  	 * 2013-07-19
  	 */
  	public void infoFileUpload()
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
  					String rename = UUID.randomUUID().toString() + "." + 
  					getFileSuffix((String)FiledataFileName.get(i));
  					String path = Struts2Util.getServletContext().getRealPath(
  					"/");
  					uploadFile.setId(rename);
  					uploadFile.setFilePath(path + 
  					SysPropertiesUtil.getProperty("uploadFile") + 
  					"\\");
  			  		//附件上传
  					uploadFile.uploadToServer();
  					
  					//保存附件信息
  					ContentAttachs contentAttaFile = new ContentAttachs();
  	  				contentAttaFile.setDelFlag(Integer.valueOf(0));
  	  				contentAttaFile.setInfoId(contentInformations.getProid());
  	  				contentAttaFile.setAttachName(rename);
  	  				contentAttaFile.setDocName(filename);
  	  				contentAttaFile.setDocUrl(
  	  					SysPropertiesUtil.getProperty("uploadFile") + 
  	                      "\\");
  	  				contentAttachsService.save(contentAttaFile);
  	  				extName = contentAttaFile.getId();
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
  	 * 重命名
  	 * @author 陆婷
  	 * 2013-07-18
  	 *
  	 */
  	public String getFileSuffix(String fileName) {
  		String filesuffix = null;
  		StringTokenizer fx = new StringTokenizer(fileName, ". ");
  		while (fx.hasMoreTokens()) {
  			filesuffix = fx.nextToken();
  		}
  		return filesuffix;
  	}
  	/**
  	 * 选择企业
  	 * @return
  	 */
  	public String choseCompany(){
  		return SUCCESS;
  	}
  	/**
	 * 执行企业查询的方法，返回json数据
	 */
	public void companyList() throws Exception{
		try {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != company){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != company.getCompanyname()) && (0 < company.getCompanyname().trim().length())){
				paraMap.put("companyname", "%" + company.getCompanyname().trim() + "%");
			}

			if ((null != company.getFddbr()) && (0 < company.getFddbr().trim().length())){
				paraMap.put("fddbr", "%" + company.getFddbr().trim() + "%");
			}

			if ((null != company.getZzjgdm()) && (0 < company.getZzjgdm().trim().length())){
				paraMap.put("zzjgdm", "%" + company.getZzjgdm().trim() + "%");
			}

			if ((null != company.getGszch()) && (0 < company.getGszch().trim().length())){
				paraMap.put("gszch", "%" + company.getGszch().trim() + "%");
			}

			if ((null != company.getQylx()) && (0 < company.getQylx().trim().length())){
				paraMap.put("qylx", company.getQylx().trim());
			}

			if ((null != company.getHyfl()) && (0 < company.getHyfl().trim().length())){
				paraMap.put("hyfl", company.getHyfl().trim());
			}

			if ((null != company.getQyzclx()) && (0 < company.getQyzclx().trim().length())){
				paraMap.put("qyzclx", company.getQyzclx().trim());
			}

			if ((null != company.getWhpqylx()) && (0 < company.getWhpqylx().trim().length())){
				paraMap.put("whpqylx", company.getWhpqylx().trim());
			}

			if ((null != company.getZywhqylx()) && (0 < company.getZywhqylx().trim().length())){
				paraMap.put("zywhqylx", company.getZywhqylx().trim());
			}
				paraMap.put("basePass", "1");
		}
		//paraMap.put("deptCodes", this.getLoginUserDepartment().getDeptCode()+"%");
		
			pagination = companyService.findByPage(pagination, paraMap);
			convObjectToJson(pagination,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	public String tongJi(){
		try {
			Map map = new HashMap();
			map.put("queryJcsjStart", queryJhwcsjStart);
			map.put("queryJcsjEnd", queryJhwcsjEnd);
			zfwsData = commoTroubleService.getZfwsData(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "tongji";
	}
	public String deptData(){
		try {
			if("5".equals(data)){//按上报部门统计
				Map map = new HashMap();
				map.put("queryJcsjStart", queryJhwcsjStart);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				map.put("data", 5);
				qylxs = commoTroubleService.getXzDataListByKeshi(map);
				qylx = commoTroubleService.getXzDataBean(map);
			}else{//按所属部门统计
				Map map = new HashMap();
				map.put("queryJcsjStart", queryJhwcsjStart);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				qylxs = commoTroubleService.getXzDataList(map);
				qylx = commoTroubleService.getXzDataBean(map);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "xz";
	}
	@SuppressWarnings("unchecked")
	public String qylxData(){
		try {
			Map map = new HashMap();
			map.put("queryJcsjStart", queryJhwcsjStart);
			map.put("queryJcsjEnd", queryJhwcsjEnd);
			qylxs = commoTroubleService.getDeptDataList(map);
			qylx = commoTroubleService.getDeptDataBean(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "qylx";
	}
	
	
	/**
	 * 乡镇检查职业危害企业统计 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String xzzywhTongji(){
		try {
			Map map = new HashMap();
			map.put("queryJcsjStart", queryJhwcsjStart);
			map.put("queryJcsjEnd", queryJhwcsjEnd);
			xzzywhBean=commoTroubleService.getXzzywhDataBean(map);
			xzzywhList=commoTroubleService.getXzzywhDataList(map);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "xzzywh";
	}
	
	/**
	 * 按部门一般隐患统计 导出
	 * 2013-12-2
	 * 陆婷
	 */
	public void deptDataExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=ybyhdept.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按部门一般安全隐患统计");
		    sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 5000);
	        sheet.setColumnWidth(11, 5000);
	        sheet.setColumnWidth(12, 6000);
	        sheet.setColumnWidth(13, 5000);
	        sheet.setColumnWidth(14, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按部门一般安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 14)); 
	        
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
	        if("5".equals(data)){//按上报部门统计
	        	ccl0.setCellValue("上报部门");
			}else{//按所属部门统计
				ccl0.setCellValue("所属乡镇");
			}
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("总企业数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("检查企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("出动次数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("查出隐患数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("完成隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("隐患整改率");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("重大隐患数");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("完成重大隐患数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("重大隐患整改率");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("整改资金");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(11);
	        ccl11.setCellValue("现场检查记录");
	        ccl11.setCellStyle(cs);
	        HSSFCell ccl12 = r3.createCell(12);
	        ccl12.setCellValue("责令限期整改指令书");
	        ccl12.setCellStyle(cs);
	        HSSFCell ccl13 = r3.createCell(13);
	        ccl13.setCellValue("强制措施决定书");
	        ccl13.setCellStyle(cs);
	        HSSFCell ccl14 = r3.createCell(14);
	        ccl14.setCellValue("整改复查意见书");
	        ccl14.setCellStyle(cs);
	        
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
	        	queryJhwcsjStart = (String) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (String) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			
			if("5".equals(data)){//按上报部门统计
				qylxs = commoTroubleService.getXzDataListByKeshi(map);
				qylx = commoTroubleService.getXzDataBean(map);
			}else{//按所属部门统计
				qylxs = commoTroubleService.getXzDataList(map);
				qylx = commoTroubleService.getXzDataBean(map);
			}
			
			int num = 2;
			for(DeptDataBean tjyhbean:qylxs)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getSzz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getJcqys());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getCdcs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getCcyhs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getWcyhs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getYhzgl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getZdyhs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(tjyhbean.getWczdyhs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(tjyhbean.getZdyhzgl());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(tjyhbean.getZgzj());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(tjyhbean.getXcjcjl());
		        ce11.setCellStyle(c);
		        HSSFCell ce12 = ro.createCell(12);
		        ce12.setCellValue(tjyhbean.getZgzls());
		        ce12.setCellStyle(c);
		        HSSFCell ce13 = ro.createCell(13);
		        ce13.setCellValue(tjyhbean.getQzcs());
		        ce13.setCellStyle(c);
		        HSSFCell ce14 = ro.createCell(14);
		        ce14.setCellValue(tjyhbean.getFcyjs());
		        ce14.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(qylx.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(qylx.getJcqys());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(qylx.getCdcs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(qylx.getCcyhs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(qylx.getWcyhs());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(qylx.getYhzgl());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(qylx.getZdyhs());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(qylx.getWczdyhs());
	        ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(qylx.getZdyhzgl());
	        ce9.setCellStyle(c);
	        HSSFCell ce10 = ro.createCell(10);
	        ce10.setCellValue(qylx.getZgzj());
	        ce10.setCellStyle(c);
	        HSSFCell ce11 = ro.createCell(11);
	        ce11.setCellValue(qylx.getXcjcjl());
	        ce11.setCellStyle(c);
	        HSSFCell ce12 = ro.createCell(12);
	        ce12.setCellValue(qylx.getZgzls());
	        ce12.setCellStyle(c);
	        HSSFCell ce13 = ro.createCell(13);
	        ce13.setCellValue(qylx.getQzcs());
	        ce13.setCellStyle(c);
	        HSSFCell ce14 = ro.createCell(14);
	        ce14.setCellValue(qylx.getFcyjs());
	        ce14.setCellStyle(c);
	        
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按企业类型一般隐患统计 导出
	 * 2013-12-2
	 * 陆婷
	 */
	@SuppressWarnings("unchecked")
	public void qylxDataExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=ybyhqylx.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按企业类型一般安全隐患统计");
			sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按企业类型一般安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10)); 
	        
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
	        ccl0.setCellValue("企业类型");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("总企业数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("检查企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("出动次数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("查出隐患数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("完成隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("隐患整改率");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("重大隐患数");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("完成重大隐患数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("重大隐患整改率");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("整改资金");
	        ccl10.setCellStyle(cs);
	        
	        
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
	        	queryJhwcsjStart = (String) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (String) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			qylxs = commoTroubleService.getDeptDataList(map);
			qylx = commoTroubleService.getDeptDataBean(map);
			
			int num = 2;
			for(DeptDataBean tjyhbean:qylxs)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getQylx());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getJcqys());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getCdcs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getCcyhs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getWcyhs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getYhzgl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getZdyhs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(tjyhbean.getWczdyhs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(tjyhbean.getZdyhzgl());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(tjyhbean.getZgzj());
		        ce10.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(qylx.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(qylx.getJcqys());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(qylx.getCdcs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(qylx.getCcyhs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(qylx.getWcyhs());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(qylx.getYhzgl());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(qylx.getZdyhs());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(qylx.getWczdyhs());
	        ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(qylx.getZdyhzgl());
	        ce9.setCellStyle(c);
	        HSSFCell ce10 = ro.createCell(10);
	        ce10.setCellValue(qylx.getZgzj());
	        ce10.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 一般安全隐患按企业统计查询
	 * 2013-12-13
	 * 陆婷
	 */
	public void commoTroubleDataQyQuery()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if (null != queryJhwcsjStart){
			paraMap.put("queryJcsjStart", queryJhwcsjStart);
		}

		if (null != queryJhwcsjEnd){
			paraMap.put("queryJcsjEnd", queryJhwcsjEnd);
		}
		
		List<QyDataBean> qylist = new ArrayList<QyDataBean>();
		qylist = commoTroubleService.getCommonTroubleQyListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = commoTroubleService.getCommonTroubleQyListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(qylist);
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 初始化一般安全隐患按企业统计页面
	 * 2013-12-13
	 * 陆婷
	 */
	public String commoTroubleDataQyList()
	{
		return SUCCESS;
	}

	/**
	 * 一般安全隐患按企业统计导出
	 * 陆婷
	 * 2013-12-13
	 */
	public void commoTroubleDataQyExport()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=ybyhqy.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按企业一般安全隐患统计");
			sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 6000);
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 6000);
	        sheet.setColumnWidth(8, 6000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按企业一般安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 8)); 
	        
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
	        ccl0.setCellValue("企业名称");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(1);
	        ccl3.setCellValue("出动次数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(2);
	        ccl4.setCellValue("查出隐患数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(3);
	        ccl5.setCellValue("完成隐患数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(4);
	        ccl6.setCellValue("隐患整改率");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(5);
	        ccl7.setCellValue("重大隐患数");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(6);
	        ccl8.setCellValue("完成重大隐患数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(7);
	        ccl9.setCellValue("重大隐患整改率");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(8);
	        ccl10.setCellValue("整改资金");
	        ccl10.setCellStyle(cs);
	        
	        
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
	        	queryJhwcsjStart = (String) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (String) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			List<QyDataBean> qylist = commoTroubleService.getQyDataList(map);
			
			int num = 2;
			for(QyDataBean tjyhbean:qylist)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(1);
		        ce3.setCellValue(tjyhbean.getCdcs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(2);
		        ce4.setCellValue(tjyhbean.getCcyhs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(3);
		        ce5.setCellValue(tjyhbean.getWcyhs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(4);
		        ce6.setCellValue(tjyhbean.getYhzgl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(5);
		        ce7.setCellValue(tjyhbean.getZdyhs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(6);
		        ce8.setCellValue(tjyhbean.getWczdyhs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(7);
		        ce9.setCellValue(tjyhbean.getZdyhzgl());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(8);
		        ce10.setCellValue(tjyhbean.getZgzj());
		        ce10.setCellStyle(c);
		        num++;
			}
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 乡镇检查职业危害企业统计导出
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void xzzywhTongJiExport(){
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=xzzywh.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按乡镇检查职业危害企业统计");
		    sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 10000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 8000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 5000);
	        sheet.setColumnWidth(11, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按乡镇检查职业危害企业统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 11)); 
	        
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
	        ccl1.setCellValue("职业危害企业数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("镇级检查职业危害企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("村级检查职业危害企业数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("现场检查记录");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("责令限期整改指令书");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("整改复查意见书");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("强制措施决定书");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("检查发现隐患数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("整改隐患数");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("未整改隐患数");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(11);
	        ccl11.setCellValue("整改率");
	        ccl11.setCellStyle(cs);
	        
	        
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
	        	queryJhwcsjStart = (String) getSessionAttribute("queryJhwcsjStart");
	        	queryJhwcsjEnd =  (String) getSessionAttribute("queryJhwcsjEnd");
			}
	        if (null != queryJhwcsjStart){
	        	map.put("queryJcsjStart", queryJhwcsjStart);
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd){
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
			}
			xzzywhBean=commoTroubleService.getXzzywhDataBean(map);
			xzzywhList=commoTroubleService.getXzzywhDataList(map);
			
			int num = 2;
			for(XzzywhBean xzzywhbean:xzzywhList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(xzzywhbean.getSzz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(xzzywhbean.getQyTotal());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(xzzywhbean.getZjwhs());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(xzzywhbean.getCjwhs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(xzzywhbean.getXcjcjl());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(xzzywhbean.getZgzls());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(xzzywhbean.getQzcs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(xzzywhbean.getFcyjs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(xzzywhbean.getYhTotal());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(xzzywhbean.getZgyhs());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(xzzywhbean.getWzgyhs());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(xzzywhbean.getZgl());
		        ce11.setCellStyle(c);
		        
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(xzzywhBean.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(xzzywhBean.getZjwhs());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(xzzywhBean.getCjwhs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(xzzywhBean.getXcjcjl());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(xzzywhBean.getZgzls());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(xzzywhBean.getQzcs());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(xzzywhBean.getFcyjs());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(xzzywhBean.getYhTotal());
	        ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(xzzywhBean.getZgyhs());
	        ce9.setCellStyle(c);
	        HSSFCell ce10 = ro.createCell(10);
	        ce10.setCellValue(xzzywhBean.getWzgyhs());
	        ce10.setCellStyle(c);
	        HSSFCell ce11 = ro.createCell(11);
	        ce11.setCellValue(xzzywhBean.getZgl());
	        ce11.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ksjcTongji()
	{
		try {
			Map map = new HashMap();
			map.put("queryJcsjStart", queryJhwcsjStart);
			map.put("queryJcsjEnd", queryJhwcsjEnd);
			ksjcBean=commoTroubleService.getKsjcBean(map);
			ksjcList=commoTroubleService.getKsjcListList(map);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void ksjcTongJiExport()
	{
		try {
			if(flag == null || "".equals(flag))
			{
				queryJhwcsjStart = (String)getSessionAttribute("queryJhwcsjStart");
				queryJhwcsjEnd = (String)getSessionAttribute("queryJhwcsjEnd");
			}
			
			Map map = new HashMap();
			if (null != queryJhwcsjStart && !"".equals(queryJhwcsjStart)){
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
				map.put("queryJcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd && !"".equals(queryJhwcsjEnd)){
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			ksjcBean=commoTroubleService.getKsjcBean(map);
			ksjcList=commoTroubleService.getKsjcListList(map);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=ksjc.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "ksjc.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        
	        int num = 4;
	        for(int i=0;i<ksjcList.size();i++)
	        {
	        	KsjcBean ksjcBean = ksjcList.get(i);
	        	HSSFRow row0 = sheet.getRow(num);
	        	HSSFCell cel0 = row0.getCell(0);
				cel0.setCellValue(i+1);
				HSSFCell cel1 = row0.getCell(1);
				cel1.setCellValue(ksjcBean.getSzzname());
				HSSFCell cel2 = row0.getCell(2);
				cel2.setCellValue(ksjcBean.getData1());
				HSSFCell cel3 = row0.getCell(3);
				cel3.setCellValue(ksjcBean.getData2());
				HSSFCell cel4 = row0.getCell(4);
				cel4.setCellValue(ksjcBean.getData3());
				HSSFCell cel5 = row0.getCell(5);
				cel5.setCellValue(ksjcBean.getData4());
				HSSFCell cel6 = row0.getCell(6);
				cel6.setCellValue(ksjcBean.getData5());
				HSSFCell cel7 = row0.getCell(7);
				cel7.setCellValue(ksjcBean.getData6());
				HSSFCell cel8 = row0.getCell(8);
				cel8.setCellValue(ksjcBean.getData7());
				HSSFCell cel9 = row0.getCell(9);
				cel9.setCellValue(ksjcBean.getData8());
				HSSFCell cel10 = row0.getCell(10);
				cel10.setCellValue(ksjcBean.getData9());
				HSSFCell cel11 = row0.getCell(11);
				cel11.setCellValue(ksjcBean.getData10());
				HSSFCell cel12 = row0.getCell(12);
				cel12.setCellValue(ksjcBean.getData11());
				HSSFCell cel13 = row0.getCell(13);
				cel13.setCellValue(ksjcBean.getData12());
				HSSFCell cel14 = row0.getCell(14);
				cel14.setCellValue(ksjcBean.getData13());
				HSSFCell cel15 = row0.getCell(15);
				cel15.setCellValue(ksjcBean.getData14());
				HSSFCell cel16 = row0.getCell(16);
				cel16.setCellValue(ksjcBean.getData15());
				HSSFCell cel17 = row0.getCell(17);
				cel17.setCellValue(ksjcBean.getData16());
				HSSFCell cel18 = row0.getCell(18);
				cel18.setCellValue(ksjcBean.getData17());
				HSSFCell cel19 = row0.getCell(19);
				cel19.setCellValue(ksjcBean.getData18());
				HSSFCell cel20 = row0.getCell(20);
				cel20.setCellValue(ksjcBean.getData19());
				HSSFCell cel21 = row0.getCell(21);
				cel21.setCellValue(ksjcBean.getData20());
				HSSFCell cel22 = row0.getCell(22);
				cel22.setCellValue(ksjcBean.getData21());
				HSSFCell cel23 = row0.getCell(23);
				cel23.setCellValue(ksjcBean.getData22());
				HSSFCell cel24 = row0.getCell(24);
				cel24.setCellValue(ksjcBean.getData23());
				HSSFCell cel25 = row0.getCell(25);
				cel25.setCellValue(ksjcBean.getData24());
				HSSFCell cel26 = row0.getCell(26);
				cel26.setCellValue(ksjcBean.getData25());
				HSSFCell cel27 = row0.getCell(27);
				cel27.setCellValue(ksjcBean.getData26());
				HSSFCell cel28 = row0.getCell(28);
				cel28.setCellValue(ksjcBean.getData27());
				HSSFCell cel29 = row0.getCell(29);
				cel29.setCellValue(ksjcBean.getData28());
				HSSFCell cel30 = row0.getCell(30);
				cel30.setCellValue(ksjcBean.getData29());
				HSSFCell cel31 = row0.getCell(31);
				cel31.setCellValue(ksjcBean.getData30());
				HSSFCell cel32 = row0.getCell(32);
				cel32.setCellValue(ksjcBean.getData31());
				HSSFCell cel33 = row0.getCell(33);
				cel33.setCellValue(ksjcBean.getData32());
				HSSFCell cel34 = row0.getCell(34);
				cel34.setCellValue(ksjcBean.getData33());
				HSSFCell cel35 = row0.getCell(35);
				cel35.setCellValue(ksjcBean.getData34());
				
				HSSFCell cel36 = row0.getCell(36);
				cel36.setCellValue(ksjcBean.getData35());
				HSSFCell cel37 = row0.getCell(37);
				cel37.setCellValue(ksjcBean.getData36());
				HSSFCell cel38 = row0.getCell(38);
				cel38.setCellValue(ksjcBean.getData37());
				HSSFCell cel39 = row0.getCell(39);
				cel39.setCellValue(ksjcBean.getData38());
				
				HSSFCell cel40 = row0.getCell(40);
				cel40.setCellValue(ksjcBean.getData39());
				HSSFCell cel41 = row0.getCell(41);
				cel41.setCellValue(ksjcBean.getData40());
				HSSFCell cel42 = row0.getCell(42);
				cel42.setCellValue(ksjcBean.getData41());
				HSSFCell cel43 = row0.getCell(43);
				cel43.setCellValue(ksjcBean.getData42());
				HSSFCell cel44 = row0.getCell(44);
				cel44.setCellValue(ksjcBean.getData43());
				HSSFCell cel45 = row0.getCell(45);
				cel45.setCellValue(ksjcBean.getData44());
				HSSFCell cel46 = row0.getCell(46);
				cel46.setCellValue(ksjcBean.getData45());
				HSSFCell cel47 = row0.getCell(47);
				cel47.setCellValue(ksjcBean.getData46());
				HSSFCell cel48 = row0.getCell(48);
				cel48.setCellValue(ksjcBean.getData47());
				HSSFCell cel49 = row0.getCell(49);
				cel49.setCellValue(ksjcBean.getData48());
				
				
				
				HSSFCell cel50 = row0.getCell(50);
				cel50.setCellValue(ksjcBean.getData49());
				HSSFCell cel51 = row0.getCell(51);
				cel51.setCellValue(ksjcBean.getData50());
				HSSFCell cel52 = row0.getCell(52);
				cel52.setCellValue(ksjcBean.getData51());
				HSSFCell cel53 = row0.getCell(53);
				cel53.setCellValue(ksjcBean.getData52());
				HSSFCell cel54 = row0.getCell(54);
				cel54.setCellValue(ksjcBean.getData53());
				HSSFCell cel55 = row0.getCell(55);
				cel55.setCellValue(ksjcBean.getData54());
				HSSFCell cel56 = row0.getCell(56);
				cel56.setCellValue(ksjcBean.getData55());
				HSSFCell cel57 = row0.getCell(57);
				cel57.setCellValue(ksjcBean.getData56());
				HSSFCell cel58 = row0.getCell(58);
				cel58.setCellValue(ksjcBean.getData57());
				HSSFCell cel59 = row0.getCell(59);
				cel59.setCellValue(ksjcBean.getData58());
				
				HSSFCell cel60 = row0.getCell(60);
				cel60.setCellValue(ksjcBean.getData59());
				HSSFCell cel61 = row0.getCell(61);
				cel61.setCellValue(ksjcBean.getData60());
				HSSFCell cel62 = row0.getCell(62);
				cel62.setCellValue(ksjcBean.getData61());
				HSSFCell cel63 = row0.getCell(63);
				cel63.setCellValue(ksjcBean.getData62());
				HSSFCell cel64 = row0.getCell(64);
				cel64.setCellValue(ksjcBean.getData63());
				HSSFCell cel65 = row0.getCell(65);
				cel65.setCellValue(ksjcBean.getData64());
				HSSFCell cel66 = row0.getCell(66);
				cel66.setCellValue(ksjcBean.getData65());
				HSSFCell cel67 = row0.getCell(67);
				cel67.setCellValue(ksjcBean.getData66());
				HSSFCell cel68 = row0.getCell(68);
				cel68.setCellValue(ksjcBean.getData67());
				HSSFCell cel69 = row0.getCell(69);
				cel69.setCellValue(ksjcBean.getData68());
				
				HSSFCell cel70 = row0.getCell(70);
				cel70.setCellValue(ksjcBean.getData69());
				HSSFCell cel71 = row0.getCell(71);
				cel71.setCellValue(ksjcBean.getData70());
				HSSFCell cel72 = row0.getCell(72);
				cel72.setCellValue(ksjcBean.getData71());
				HSSFCell cel73 = row0.getCell(73);
				cel73.setCellValue(ksjcBean.getData72());
				HSSFCell cel74 = row0.getCell(74);
				cel74.setCellValue(ksjcBean.getData73());
				HSSFCell cel75 = row0.getCell(75);
				cel75.setCellValue(ksjcBean.getData74());
				HSSFCell cel76 = row0.getCell(76);
				cel76.setCellValue(ksjcBean.getData75());
				HSSFCell cel77 = row0.getCell(77);
				cel77.setCellValue(ksjcBean.getData76());
				HSSFCell cel78 = row0.getCell(78);
				cel78.setCellValue(ksjcBean.getData77());
				HSSFCell cel79 = row0.getCell(79);
				cel79.setCellValue(ksjcBean.getData78());
				
				HSSFCell cel80 = row0.getCell(80);
				cel80.setCellValue(ksjcBean.getData79());
				HSSFCell cel81 = row0.getCell(81);
				cel81.setCellValue(ksjcBean.getData80());
				HSSFCell cel82 = row0.getCell(82);
				cel82.setCellValue(ksjcBean.getData81());
				HSSFCell cel83 = row0.getCell(83);
				cel83.setCellValue(ksjcBean.getData82());
				HSSFCell cel84 = row0.getCell(84);
				cel84.setCellValue(ksjcBean.getData83());
				HSSFCell cel85 = row0.getCell(85);
				cel85.setCellValue(ksjcBean.getData84());
				HSSFCell cel86 = row0.getCell(86);
				cel86.setCellValue(ksjcBean.getData85());
				HSSFCell cel87 = row0.getCell(87);
				cel87.setCellValue(ksjcBean.getData86());
				HSSFCell cel88 = row0.getCell(88);
				cel88.setCellValue(ksjcBean.getData87());
				HSSFCell cel89 = row0.getCell(89);
				cel89.setCellValue(ksjcBean.getData88());
				
				HSSFCell cel90 = row0.getCell(90);
				cel90.setCellValue(ksjcBean.getData89());
				HSSFCell cel91 = row0.getCell(91);
				cel91.setCellValue(ksjcBean.getData90());
				HSSFCell cel92 = row0.getCell(92);
				cel92.setCellValue(ksjcBean.getData91());
				HSSFCell cel93 = row0.getCell(93);
				cel93.setCellValue(ksjcBean.getData92());
				HSSFCell cel94 = row0.getCell(94);
				cel94.setCellValue(ksjcBean.getData93());
				HSSFCell cel95 = row0.getCell(95);
				cel95.setCellValue(ksjcBean.getData94());
				HSSFCell cel96 = row0.getCell(96);
				cel96.setCellValue(ksjcBean.getData95());
				HSSFCell cel97 = row0.getCell(97);
				cel97.setCellValue(ksjcBean.getData96());
				HSSFCell cel98 = row0.getCell(98);
				cel98.setCellValue(ksjcBean.getData97());
				num ++;
	        }
	        HSSFRow row0 = sheet.getRow(num);
			HSSFCell cel1 = row0.getCell(1);
			cel1.setCellValue("合计");
			HSSFCell cel2 = row0.getCell(2);
			cel2.setCellValue(ksjcBean.getData1());
			HSSFCell cel3 = row0.getCell(3);
			cel3.setCellValue(ksjcBean.getData2());
			HSSFCell cel4 = row0.getCell(4);
			cel4.setCellValue(ksjcBean.getData3());
			HSSFCell cel5 = row0.getCell(5);
			cel5.setCellValue(ksjcBean.getData4());
			HSSFCell cel6 = row0.getCell(6);
			cel6.setCellValue(ksjcBean.getData5());
			HSSFCell cel7 = row0.getCell(7);
			cel7.setCellValue(ksjcBean.getData6());
			HSSFCell cel8 = row0.getCell(8);
			cel8.setCellValue(ksjcBean.getData7());
			HSSFCell cel9 = row0.getCell(9);
			cel9.setCellValue(ksjcBean.getData8());
			HSSFCell cel10 = row0.getCell(10);
			cel10.setCellValue(ksjcBean.getData9());
			HSSFCell cel11 = row0.getCell(11);
			cel11.setCellValue(ksjcBean.getData10());
			HSSFCell cel12 = row0.getCell(12);
			cel12.setCellValue(ksjcBean.getData11());
			HSSFCell cel13 = row0.getCell(13);
			cel13.setCellValue(ksjcBean.getData12());
			HSSFCell cel14 = row0.getCell(14);
			cel14.setCellValue(ksjcBean.getData13());
			HSSFCell cel15 = row0.getCell(15);
			cel15.setCellValue(ksjcBean.getData14());
			HSSFCell cel16 = row0.getCell(16);
			cel16.setCellValue(ksjcBean.getData15());
			HSSFCell cel17 = row0.getCell(17);
			cel17.setCellValue(ksjcBean.getData16());
			HSSFCell cel18 = row0.getCell(18);
			cel18.setCellValue(ksjcBean.getData17());
			HSSFCell cel19 = row0.getCell(19);
			cel19.setCellValue(ksjcBean.getData18());
			HSSFCell cel20 = row0.getCell(20);
			cel20.setCellValue(ksjcBean.getData19());
			HSSFCell cel21 = row0.getCell(21);
			cel21.setCellValue(ksjcBean.getData20());
			HSSFCell cel22 = row0.getCell(22);
			cel22.setCellValue(ksjcBean.getData21());
			HSSFCell cel23 = row0.getCell(23);
			cel23.setCellValue(ksjcBean.getData22());
			HSSFCell cel24 = row0.getCell(24);
			cel24.setCellValue(ksjcBean.getData23());
			HSSFCell cel25 = row0.getCell(25);
			cel25.setCellValue(ksjcBean.getData24());
			HSSFCell cel26 = row0.getCell(26);
			cel26.setCellValue(ksjcBean.getData25());
			HSSFCell cel27 = row0.getCell(27);
			cel27.setCellValue(ksjcBean.getData26());
			HSSFCell cel28 = row0.getCell(28);
			cel28.setCellValue(ksjcBean.getData27());
			HSSFCell cel29 = row0.getCell(29);
			cel29.setCellValue(ksjcBean.getData28());
			HSSFCell cel30 = row0.getCell(30);
			cel30.setCellValue(ksjcBean.getData29());
			HSSFCell cel31 = row0.getCell(31);
			cel31.setCellValue(ksjcBean.getData30());
			HSSFCell cel32 = row0.getCell(32);
			cel32.setCellValue(ksjcBean.getData31());
			HSSFCell cel33 = row0.getCell(33);
			cel33.setCellValue(ksjcBean.getData32());
			HSSFCell cel34 = row0.getCell(34);
			cel34.setCellValue(ksjcBean.getData33());
			HSSFCell cel35 = row0.getCell(35);
			cel35.setCellValue(ksjcBean.getData34());
			
			HSSFCell cel36 = row0.getCell(36);
			cel36.setCellValue(ksjcBean.getData35());
			HSSFCell cel37 = row0.getCell(37);
			cel37.setCellValue(ksjcBean.getData36());
			HSSFCell cel38 = row0.getCell(38);
			cel38.setCellValue(ksjcBean.getData37());
			HSSFCell cel39 = row0.getCell(39);
			cel39.setCellValue(ksjcBean.getData38());
			
			HSSFCell cel40 = row0.getCell(40);
			cel40.setCellValue(ksjcBean.getData39());
			HSSFCell cel41 = row0.getCell(41);
			cel41.setCellValue(ksjcBean.getData40());
			HSSFCell cel42 = row0.getCell(42);
			cel42.setCellValue(ksjcBean.getData41());
			HSSFCell cel43 = row0.getCell(43);
			cel43.setCellValue(ksjcBean.getData42());
			HSSFCell cel44 = row0.getCell(44);
			cel44.setCellValue(ksjcBean.getData43());
			HSSFCell cel45 = row0.getCell(45);
			cel45.setCellValue(ksjcBean.getData44());
			HSSFCell cel46 = row0.getCell(46);
			cel46.setCellValue(ksjcBean.getData45());
			HSSFCell cel47 = row0.getCell(47);
			cel47.setCellValue(ksjcBean.getData46());
			HSSFCell cel48 = row0.getCell(48);
			cel48.setCellValue(ksjcBean.getData47());
			HSSFCell cel49 = row0.getCell(49);
			cel49.setCellValue(ksjcBean.getData48());
			
			
			
			HSSFCell cel50 = row0.getCell(50);
			cel50.setCellValue(ksjcBean.getData49());
			HSSFCell cel51 = row0.getCell(51);
			cel51.setCellValue(ksjcBean.getData50());
			HSSFCell cel52 = row0.getCell(52);
			cel52.setCellValue(ksjcBean.getData51());
			HSSFCell cel53 = row0.getCell(53);
			cel53.setCellValue(ksjcBean.getData52());
			HSSFCell cel54 = row0.getCell(54);
			cel54.setCellValue(ksjcBean.getData53());
			HSSFCell cel55 = row0.getCell(55);
			cel55.setCellValue(ksjcBean.getData54());
			HSSFCell cel56 = row0.getCell(56);
			cel56.setCellValue(ksjcBean.getData55());
			HSSFCell cel57 = row0.getCell(57);
			cel57.setCellValue(ksjcBean.getData56());
			HSSFCell cel58 = row0.getCell(58);
			cel58.setCellValue(ksjcBean.getData57());
			HSSFCell cel59 = row0.getCell(59);
			cel59.setCellValue(ksjcBean.getData58());
			
			HSSFCell cel60 = row0.getCell(60);
			cel60.setCellValue(ksjcBean.getData59());
			HSSFCell cel61 = row0.getCell(61);
			cel61.setCellValue(ksjcBean.getData60());
			HSSFCell cel62 = row0.getCell(62);
			cel62.setCellValue(ksjcBean.getData61());
			HSSFCell cel63 = row0.getCell(63);
			cel63.setCellValue(ksjcBean.getData62());
			HSSFCell cel64 = row0.getCell(64);
			cel64.setCellValue(ksjcBean.getData63());
			HSSFCell cel65 = row0.getCell(65);
			cel65.setCellValue(ksjcBean.getData64());
			HSSFCell cel66 = row0.getCell(66);
			cel66.setCellValue(ksjcBean.getData65());
			HSSFCell cel67 = row0.getCell(67);
			cel67.setCellValue(ksjcBean.getData66());
			HSSFCell cel68 = row0.getCell(68);
			cel68.setCellValue(ksjcBean.getData67());
			HSSFCell cel69 = row0.getCell(69);
			cel69.setCellValue(ksjcBean.getData68());
			
			HSSFCell cel70 = row0.getCell(70);
			cel70.setCellValue(ksjcBean.getData69());
			HSSFCell cel71 = row0.getCell(71);
			cel71.setCellValue(ksjcBean.getData70());
			HSSFCell cel72 = row0.getCell(72);
			cel72.setCellValue(ksjcBean.getData71());
			HSSFCell cel73 = row0.getCell(73);
			cel73.setCellValue(ksjcBean.getData72());
			HSSFCell cel74 = row0.getCell(74);
			cel74.setCellValue(ksjcBean.getData73());
			HSSFCell cel75 = row0.getCell(75);
			cel75.setCellValue(ksjcBean.getData74());
			HSSFCell cel76 = row0.getCell(76);
			cel76.setCellValue(ksjcBean.getData75());
			HSSFCell cel77 = row0.getCell(77);
			cel77.setCellValue(ksjcBean.getData76());
			HSSFCell cel78 = row0.getCell(78);
			cel78.setCellValue(ksjcBean.getData77());
			HSSFCell cel79 = row0.getCell(79);
			cel79.setCellValue(ksjcBean.getData78());
			
			HSSFCell cel80 = row0.getCell(80);
			cel80.setCellValue(ksjcBean.getData79());
			HSSFCell cel81 = row0.getCell(81);
			cel81.setCellValue(ksjcBean.getData80());
			HSSFCell cel82 = row0.getCell(82);
			cel82.setCellValue(ksjcBean.getData81());
			HSSFCell cel83 = row0.getCell(83);
			cel83.setCellValue(ksjcBean.getData82());
			HSSFCell cel84 = row0.getCell(84);
			cel84.setCellValue(ksjcBean.getData83());
			HSSFCell cel85 = row0.getCell(85);
			cel85.setCellValue(ksjcBean.getData84());
			HSSFCell cel86 = row0.getCell(86);
			cel86.setCellValue(ksjcBean.getData85());
			HSSFCell cel87 = row0.getCell(87);
			cel87.setCellValue(ksjcBean.getData86());
			HSSFCell cel88 = row0.getCell(88);
			cel88.setCellValue(ksjcBean.getData87());
			HSSFCell cel89 = row0.getCell(89);
			cel89.setCellValue(ksjcBean.getData88());
			
			HSSFCell cel90 = row0.getCell(90);
			cel90.setCellValue(ksjcBean.getData89());
			HSSFCell cel91 = row0.getCell(91);
			cel91.setCellValue(ksjcBean.getData90());
			HSSFCell cel92 = row0.getCell(92);
			cel92.setCellValue(ksjcBean.getData91());
			HSSFCell cel93 = row0.getCell(93);
			cel93.setCellValue(ksjcBean.getData92());
			HSSFCell cel94 = row0.getCell(94);
			cel94.setCellValue(ksjcBean.getData93());
			HSSFCell cel95 = row0.getCell(95);
			cel95.setCellValue(ksjcBean.getData94());
			HSSFCell cel96 = row0.getCell(96);
			cel96.setCellValue(ksjcBean.getData95());
			HSSFCell cel97 = row0.getCell(97);
			cel97.setCellValue(ksjcBean.getData96());
			HSSFCell cel98 = row0.getCell(98);
			cel98.setCellValue(ksjcBean.getData97());
			
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String xzjcTongji()
	{
		try {
			Map map = new HashMap();
			map.put("queryJcsjStart", queryJhwcsjStart);
			map.put("queryJcsjEnd", queryJhwcsjEnd);
			ksjcBean=commoTroubleService.getXzjcBean(map);
			ksjcList=commoTroubleService.getXzjcListList(map);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void xzjcTongJiExport()
	{
		try {
			if(flag == null || "".equals(flag))
			{
				queryJhwcsjStart = (String)getSessionAttribute("queryJhwcsjStart");
				queryJhwcsjEnd = (String)getSessionAttribute("queryJhwcsjEnd");
			}
			
			Map map = new HashMap();
			if (null != queryJhwcsjStart && !"".equals(queryJhwcsjStart)){
				setSessionAttribute("queryJhwcsjStart", queryJhwcsjStart);
				map.put("queryJcsjStart", queryJhwcsjStart);
			}

			if (null != queryJhwcsjEnd && !"".equals(queryJhwcsjEnd)){
				setSessionAttribute("queryJhwcsjEnd", queryJhwcsjEnd);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
			}
			ksjcBean=commoTroubleService.getXzjcBean(map);
			ksjcList=commoTroubleService.getXzjcListList(map);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=xzjc.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "xzjc.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        
	        int num = 4;
	        for(int i=0;i<ksjcList.size();i++)
	        {
	        	KsjcBean ksjcBean = ksjcList.get(i);
	        	HSSFRow row0 = sheet.getRow(num);
	        	HSSFCell cel0 = row0.getCell(0);
				cel0.setCellValue(i+1);
				HSSFCell cel1 = row0.getCell(1);
				cel1.setCellValue(ksjcBean.getSzzname());
				HSSFCell cel2 = row0.getCell(2);
				cel2.setCellValue(ksjcBean.getData1());
				HSSFCell cel3 = row0.getCell(3);
				cel3.setCellValue(ksjcBean.getData2());
				HSSFCell cel4 = row0.getCell(4);
				cel4.setCellValue(ksjcBean.getData3());
				HSSFCell cel5 = row0.getCell(5);
				cel5.setCellValue(ksjcBean.getData4());
				HSSFCell cel6 = row0.getCell(6);
				cel6.setCellValue(ksjcBean.getData5());
				HSSFCell cel7 = row0.getCell(7);
				cel7.setCellValue(ksjcBean.getData6());
				HSSFCell cel8 = row0.getCell(8);
				cel8.setCellValue(ksjcBean.getData7());
				HSSFCell cel9 = row0.getCell(9);
				cel9.setCellValue(ksjcBean.getData8());
				HSSFCell cel10 = row0.getCell(10);
				cel10.setCellValue(ksjcBean.getData9());
				HSSFCell cel11 = row0.getCell(11);
				cel11.setCellValue(ksjcBean.getData10());
				HSSFCell cel12 = row0.getCell(12);
				cel12.setCellValue(ksjcBean.getData11());
				HSSFCell cel13 = row0.getCell(13);
				cel13.setCellValue(ksjcBean.getData12());
				HSSFCell cel14 = row0.getCell(14);
				cel14.setCellValue(ksjcBean.getData13());
				HSSFCell cel15 = row0.getCell(15);
				cel15.setCellValue(ksjcBean.getData14());
				HSSFCell cel16 = row0.getCell(16);
				cel16.setCellValue(ksjcBean.getData15());
				HSSFCell cel17 = row0.getCell(17);
				cel17.setCellValue(ksjcBean.getData16());
				HSSFCell cel18 = row0.getCell(18);
				cel18.setCellValue(ksjcBean.getData17());
				HSSFCell cel19 = row0.getCell(19);
				cel19.setCellValue(ksjcBean.getData18());
				HSSFCell cel20 = row0.getCell(20);
				cel20.setCellValue(ksjcBean.getData19());
				HSSFCell cel21 = row0.getCell(21);
				cel21.setCellValue(ksjcBean.getData20());
				HSSFCell cel22 = row0.getCell(22);
				cel22.setCellValue(ksjcBean.getData21());
				HSSFCell cel23 = row0.getCell(23);
				cel23.setCellValue(ksjcBean.getData22());
				HSSFCell cel24 = row0.getCell(24);
				cel24.setCellValue(ksjcBean.getData23());
				HSSFCell cel25 = row0.getCell(25);
				cel25.setCellValue(ksjcBean.getData24());
				HSSFCell cel26 = row0.getCell(26);
				cel26.setCellValue(ksjcBean.getData25());
				HSSFCell cel27 = row0.getCell(27);
				cel27.setCellValue(ksjcBean.getData26());
				HSSFCell cel28 = row0.getCell(28);
				cel28.setCellValue(ksjcBean.getData27());
				HSSFCell cel29 = row0.getCell(29);
				cel29.setCellValue(ksjcBean.getData28());
				HSSFCell cel30 = row0.getCell(30);
				cel30.setCellValue(ksjcBean.getData29());
				HSSFCell cel31 = row0.getCell(31);
				cel31.setCellValue(ksjcBean.getData30());
				HSSFCell cel32 = row0.getCell(32);
				cel32.setCellValue(ksjcBean.getData31());
				HSSFCell cel33 = row0.getCell(33);
				cel33.setCellValue(ksjcBean.getData32());
				HSSFCell cel34 = row0.getCell(34);
				cel34.setCellValue(ksjcBean.getData33());
				HSSFCell cel35 = row0.getCell(35);
				cel35.setCellValue(ksjcBean.getData34());
				
				HSSFCell cel36 = row0.getCell(36);
				cel36.setCellValue(ksjcBean.getData35());
				HSSFCell cel37 = row0.getCell(37);
				cel37.setCellValue(ksjcBean.getData36());
				HSSFCell cel38 = row0.getCell(38);
				cel38.setCellValue(ksjcBean.getData37());
				HSSFCell cel39 = row0.getCell(39);
				cel39.setCellValue(ksjcBean.getData38());
				
				HSSFCell cel40 = row0.getCell(40);
				cel40.setCellValue(ksjcBean.getData39());
				HSSFCell cel41 = row0.getCell(41);
				cel41.setCellValue(ksjcBean.getData40());
				HSSFCell cel42 = row0.getCell(42);
				cel42.setCellValue(ksjcBean.getData41());
				HSSFCell cel43 = row0.getCell(43);
				cel43.setCellValue(ksjcBean.getData42());
				HSSFCell cel44 = row0.getCell(44);
				cel44.setCellValue(ksjcBean.getData43());
				HSSFCell cel45 = row0.getCell(45);
				cel45.setCellValue(ksjcBean.getData44());
				HSSFCell cel46 = row0.getCell(46);
				cel46.setCellValue(ksjcBean.getData45());
				HSSFCell cel47 = row0.getCell(47);
				cel47.setCellValue(ksjcBean.getData46());
				HSSFCell cel48 = row0.getCell(48);
				cel48.setCellValue(ksjcBean.getData47());
				HSSFCell cel49 = row0.getCell(49);
				cel49.setCellValue(ksjcBean.getData48());
				
				
				
				HSSFCell cel50 = row0.getCell(50);
				cel50.setCellValue(ksjcBean.getData49());
				HSSFCell cel51 = row0.getCell(51);
				cel51.setCellValue(ksjcBean.getData50());
				HSSFCell cel52 = row0.getCell(52);
				cel52.setCellValue(ksjcBean.getData51());
				HSSFCell cel53 = row0.getCell(53);
				cel53.setCellValue(ksjcBean.getData52());
				HSSFCell cel54 = row0.getCell(54);
				cel54.setCellValue(ksjcBean.getData53());
				HSSFCell cel55 = row0.getCell(55);
				cel55.setCellValue(ksjcBean.getData54());
				HSSFCell cel56 = row0.getCell(56);
				cel56.setCellValue(ksjcBean.getData55());
				HSSFCell cel57 = row0.getCell(57);
				cel57.setCellValue(ksjcBean.getData56());
				HSSFCell cel58 = row0.getCell(58);
				cel58.setCellValue(ksjcBean.getData57());
				HSSFCell cel59 = row0.getCell(59);
				cel59.setCellValue(ksjcBean.getData58());
				
				HSSFCell cel60 = row0.getCell(60);
				cel60.setCellValue(ksjcBean.getData59());
				HSSFCell cel61 = row0.getCell(61);
				cel61.setCellValue(ksjcBean.getData60());
				HSSFCell cel62 = row0.getCell(62);
				cel62.setCellValue(ksjcBean.getData61());
				HSSFCell cel63 = row0.getCell(63);
				cel63.setCellValue(ksjcBean.getData62());
				HSSFCell cel64 = row0.getCell(64);
				cel64.setCellValue(ksjcBean.getData63());
				HSSFCell cel65 = row0.getCell(65);
				cel65.setCellValue(ksjcBean.getData64());
				HSSFCell cel66 = row0.getCell(66);
				cel66.setCellValue(ksjcBean.getData65());
				HSSFCell cel67 = row0.getCell(67);
				cel67.setCellValue(ksjcBean.getData66());
				HSSFCell cel68 = row0.getCell(68);
				cel68.setCellValue(ksjcBean.getData67());
				HSSFCell cel69 = row0.getCell(69);
				cel69.setCellValue(ksjcBean.getData68());
				
				HSSFCell cel70 = row0.getCell(70);
				cel70.setCellValue(ksjcBean.getData69());
				HSSFCell cel71 = row0.getCell(71);
				cel71.setCellValue(ksjcBean.getData70());
				HSSFCell cel72 = row0.getCell(72);
				cel72.setCellValue(ksjcBean.getData71());
				HSSFCell cel73 = row0.getCell(73);
				cel73.setCellValue(ksjcBean.getData72());
				HSSFCell cel74 = row0.getCell(74);
				cel74.setCellValue(ksjcBean.getData73());
				HSSFCell cel75 = row0.getCell(75);
				cel75.setCellValue(ksjcBean.getData74());
				HSSFCell cel76 = row0.getCell(76);
				cel76.setCellValue(ksjcBean.getData75());
				HSSFCell cel77 = row0.getCell(77);
				cel77.setCellValue(ksjcBean.getData76());
				HSSFCell cel78 = row0.getCell(78);
				cel78.setCellValue(ksjcBean.getData77());
				HSSFCell cel79 = row0.getCell(79);
				cel79.setCellValue(ksjcBean.getData78());
				
				HSSFCell cel80 = row0.getCell(80);
				cel80.setCellValue(ksjcBean.getData79());
				HSSFCell cel81 = row0.getCell(81);
				cel81.setCellValue(ksjcBean.getData80());
				HSSFCell cel82 = row0.getCell(82);
				cel82.setCellValue(ksjcBean.getData81());
				HSSFCell cel83 = row0.getCell(83);
				cel83.setCellValue(ksjcBean.getData82());
				HSSFCell cel84 = row0.getCell(84);
				cel84.setCellValue(ksjcBean.getData83());
				HSSFCell cel85 = row0.getCell(85);
				cel85.setCellValue(ksjcBean.getData84());
				HSSFCell cel86 = row0.getCell(86);
				cel86.setCellValue(ksjcBean.getData85());
				HSSFCell cel87 = row0.getCell(87);
				cel87.setCellValue(ksjcBean.getData86());
				HSSFCell cel88 = row0.getCell(88);
				cel88.setCellValue(ksjcBean.getData87());
				HSSFCell cel89 = row0.getCell(89);
				cel89.setCellValue(ksjcBean.getData88());
				
				HSSFCell cel90 = row0.getCell(90);
				cel90.setCellValue(ksjcBean.getData89());
				HSSFCell cel91 = row0.getCell(91);
				cel91.setCellValue(ksjcBean.getData90());
				HSSFCell cel92 = row0.getCell(92);
				cel92.setCellValue(ksjcBean.getData91());
				HSSFCell cel93 = row0.getCell(93);
				cel93.setCellValue(ksjcBean.getData92());
				HSSFCell cel94 = row0.getCell(94);
				cel94.setCellValue(ksjcBean.getData93());
				HSSFCell cel95 = row0.getCell(95);
				cel95.setCellValue(ksjcBean.getData94());
				HSSFCell cel96 = row0.getCell(96);
				cel96.setCellValue(ksjcBean.getData95());
				HSSFCell cel97 = row0.getCell(97);
				cel97.setCellValue(ksjcBean.getData96());
				HSSFCell cel98 = row0.getCell(98);
				cel98.setCellValue(ksjcBean.getData97());
				num ++;
	        }
	        HSSFRow row0 = sheet.getRow(num);
			HSSFCell cel1 = row0.getCell(1);
			cel1.setCellValue("合计");
			HSSFCell cel2 = row0.getCell(2);
			cel2.setCellValue(ksjcBean.getData1());
			HSSFCell cel3 = row0.getCell(3);
			cel3.setCellValue(ksjcBean.getData2());
			HSSFCell cel4 = row0.getCell(4);
			cel4.setCellValue(ksjcBean.getData3());
			HSSFCell cel5 = row0.getCell(5);
			cel5.setCellValue(ksjcBean.getData4());
			HSSFCell cel6 = row0.getCell(6);
			cel6.setCellValue(ksjcBean.getData5());
			HSSFCell cel7 = row0.getCell(7);
			cel7.setCellValue(ksjcBean.getData6());
			HSSFCell cel8 = row0.getCell(8);
			cel8.setCellValue(ksjcBean.getData7());
			HSSFCell cel9 = row0.getCell(9);
			cel9.setCellValue(ksjcBean.getData8());
			HSSFCell cel10 = row0.getCell(10);
			cel10.setCellValue(ksjcBean.getData9());
			HSSFCell cel11 = row0.getCell(11);
			cel11.setCellValue(ksjcBean.getData10());
			HSSFCell cel12 = row0.getCell(12);
			cel12.setCellValue(ksjcBean.getData11());
			HSSFCell cel13 = row0.getCell(13);
			cel13.setCellValue(ksjcBean.getData12());
			HSSFCell cel14 = row0.getCell(14);
			cel14.setCellValue(ksjcBean.getData13());
			HSSFCell cel15 = row0.getCell(15);
			cel15.setCellValue(ksjcBean.getData14());
			HSSFCell cel16 = row0.getCell(16);
			cel16.setCellValue(ksjcBean.getData15());
			HSSFCell cel17 = row0.getCell(17);
			cel17.setCellValue(ksjcBean.getData16());
			HSSFCell cel18 = row0.getCell(18);
			cel18.setCellValue(ksjcBean.getData17());
			HSSFCell cel19 = row0.getCell(19);
			cel19.setCellValue(ksjcBean.getData18());
			HSSFCell cel20 = row0.getCell(20);
			cel20.setCellValue(ksjcBean.getData19());
			HSSFCell cel21 = row0.getCell(21);
			cel21.setCellValue(ksjcBean.getData20());
			HSSFCell cel22 = row0.getCell(22);
			cel22.setCellValue(ksjcBean.getData21());
			HSSFCell cel23 = row0.getCell(23);
			cel23.setCellValue(ksjcBean.getData22());
			HSSFCell cel24 = row0.getCell(24);
			cel24.setCellValue(ksjcBean.getData23());
			HSSFCell cel25 = row0.getCell(25);
			cel25.setCellValue(ksjcBean.getData24());
			HSSFCell cel26 = row0.getCell(26);
			cel26.setCellValue(ksjcBean.getData25());
			HSSFCell cel27 = row0.getCell(27);
			cel27.setCellValue(ksjcBean.getData26());
			HSSFCell cel28 = row0.getCell(28);
			cel28.setCellValue(ksjcBean.getData27());
			HSSFCell cel29 = row0.getCell(29);
			cel29.setCellValue(ksjcBean.getData28());
			HSSFCell cel30 = row0.getCell(30);
			cel30.setCellValue(ksjcBean.getData29());
			HSSFCell cel31 = row0.getCell(31);
			cel31.setCellValue(ksjcBean.getData30());
			HSSFCell cel32 = row0.getCell(32);
			cel32.setCellValue(ksjcBean.getData31());
			HSSFCell cel33 = row0.getCell(33);
			cel33.setCellValue(ksjcBean.getData32());
			HSSFCell cel34 = row0.getCell(34);
			cel34.setCellValue(ksjcBean.getData33());
			HSSFCell cel35 = row0.getCell(35);
			cel35.setCellValue(ksjcBean.getData34());
			
			HSSFCell cel36 = row0.getCell(36);
			cel36.setCellValue(ksjcBean.getData35());
			HSSFCell cel37 = row0.getCell(37);
			cel37.setCellValue(ksjcBean.getData36());
			HSSFCell cel38 = row0.getCell(38);
			cel38.setCellValue(ksjcBean.getData37());
			HSSFCell cel39 = row0.getCell(39);
			cel39.setCellValue(ksjcBean.getData38());
			
			HSSFCell cel40 = row0.getCell(40);
			cel40.setCellValue(ksjcBean.getData39());
			HSSFCell cel41 = row0.getCell(41);
			cel41.setCellValue(ksjcBean.getData40());
			HSSFCell cel42 = row0.getCell(42);
			cel42.setCellValue(ksjcBean.getData41());
			HSSFCell cel43 = row0.getCell(43);
			cel43.setCellValue(ksjcBean.getData42());
			HSSFCell cel44 = row0.getCell(44);
			cel44.setCellValue(ksjcBean.getData43());
			HSSFCell cel45 = row0.getCell(45);
			cel45.setCellValue(ksjcBean.getData44());
			HSSFCell cel46 = row0.getCell(46);
			cel46.setCellValue(ksjcBean.getData45());
			HSSFCell cel47 = row0.getCell(47);
			cel47.setCellValue(ksjcBean.getData46());
			HSSFCell cel48 = row0.getCell(48);
			cel48.setCellValue(ksjcBean.getData47());
			HSSFCell cel49 = row0.getCell(49);
			cel49.setCellValue(ksjcBean.getData48());
			
			
			
			HSSFCell cel50 = row0.getCell(50);
			cel50.setCellValue(ksjcBean.getData49());
			HSSFCell cel51 = row0.getCell(51);
			cel51.setCellValue(ksjcBean.getData50());
			HSSFCell cel52 = row0.getCell(52);
			cel52.setCellValue(ksjcBean.getData51());
			HSSFCell cel53 = row0.getCell(53);
			cel53.setCellValue(ksjcBean.getData52());
			HSSFCell cel54 = row0.getCell(54);
			cel54.setCellValue(ksjcBean.getData53());
			HSSFCell cel55 = row0.getCell(55);
			cel55.setCellValue(ksjcBean.getData54());
			HSSFCell cel56 = row0.getCell(56);
			cel56.setCellValue(ksjcBean.getData55());
			HSSFCell cel57 = row0.getCell(57);
			cel57.setCellValue(ksjcBean.getData56());
			HSSFCell cel58 = row0.getCell(58);
			cel58.setCellValue(ksjcBean.getData57());
			HSSFCell cel59 = row0.getCell(59);
			cel59.setCellValue(ksjcBean.getData58());
			
			HSSFCell cel60 = row0.getCell(60);
			cel60.setCellValue(ksjcBean.getData59());
			HSSFCell cel61 = row0.getCell(61);
			cel61.setCellValue(ksjcBean.getData60());
			HSSFCell cel62 = row0.getCell(62);
			cel62.setCellValue(ksjcBean.getData61());
			HSSFCell cel63 = row0.getCell(63);
			cel63.setCellValue(ksjcBean.getData62());
			HSSFCell cel64 = row0.getCell(64);
			cel64.setCellValue(ksjcBean.getData63());
			HSSFCell cel65 = row0.getCell(65);
			cel65.setCellValue(ksjcBean.getData64());
			HSSFCell cel66 = row0.getCell(66);
			cel66.setCellValue(ksjcBean.getData65());
			HSSFCell cel67 = row0.getCell(67);
			cel67.setCellValue(ksjcBean.getData66());
			HSSFCell cel68 = row0.getCell(68);
			cel68.setCellValue(ksjcBean.getData67());
			HSSFCell cel69 = row0.getCell(69);
			cel69.setCellValue(ksjcBean.getData68());
			
			HSSFCell cel70 = row0.getCell(70);
			cel70.setCellValue(ksjcBean.getData69());
			HSSFCell cel71 = row0.getCell(71);
			cel71.setCellValue(ksjcBean.getData70());
			HSSFCell cel72 = row0.getCell(72);
			cel72.setCellValue(ksjcBean.getData71());
			HSSFCell cel73 = row0.getCell(73);
			cel73.setCellValue(ksjcBean.getData72());
			HSSFCell cel74 = row0.getCell(74);
			cel74.setCellValue(ksjcBean.getData73());
			HSSFCell cel75 = row0.getCell(75);
			cel75.setCellValue(ksjcBean.getData74());
			HSSFCell cel76 = row0.getCell(76);
			cel76.setCellValue(ksjcBean.getData75());
			HSSFCell cel77 = row0.getCell(77);
			cel77.setCellValue(ksjcBean.getData76());
			HSSFCell cel78 = row0.getCell(78);
			cel78.setCellValue(ksjcBean.getData77());
			HSSFCell cel79 = row0.getCell(79);
			cel79.setCellValue(ksjcBean.getData78());
			
			HSSFCell cel80 = row0.getCell(80);
			cel80.setCellValue(ksjcBean.getData79());
			HSSFCell cel81 = row0.getCell(81);
			cel81.setCellValue(ksjcBean.getData80());
			HSSFCell cel82 = row0.getCell(82);
			cel82.setCellValue(ksjcBean.getData81());
			HSSFCell cel83 = row0.getCell(83);
			cel83.setCellValue(ksjcBean.getData82());
			HSSFCell cel84 = row0.getCell(84);
			cel84.setCellValue(ksjcBean.getData83());
			HSSFCell cel85 = row0.getCell(85);
			cel85.setCellValue(ksjcBean.getData84());
			HSSFCell cel86 = row0.getCell(86);
			cel86.setCellValue(ksjcBean.getData85());
			HSSFCell cel87 = row0.getCell(87);
			cel87.setCellValue(ksjcBean.getData86());
			HSSFCell cel88 = row0.getCell(88);
			cel88.setCellValue(ksjcBean.getData87());
			HSSFCell cel89 = row0.getCell(89);
			cel89.setCellValue(ksjcBean.getData88());
			
			HSSFCell cel90 = row0.getCell(90);
			cel90.setCellValue(ksjcBean.getData89());
			HSSFCell cel91 = row0.getCell(91);
			cel91.setCellValue(ksjcBean.getData90());
			HSSFCell cel92 = row0.getCell(92);
			cel92.setCellValue(ksjcBean.getData91());
			HSSFCell cel93 = row0.getCell(93);
			cel93.setCellValue(ksjcBean.getData92());
			HSSFCell cel94 = row0.getCell(94);
			cel94.setCellValue(ksjcBean.getData93());
			HSSFCell cel95 = row0.getCell(95);
			cel95.setCellValue(ksjcBean.getData94());
			HSSFCell cel96 = row0.getCell(96);
			cel96.setCellValue(ksjcBean.getData95());
			HSSFCell cel97 = row0.getCell(97);
			cel97.setCellValue(ksjcBean.getData96());
			HSSFCell cel98 = row0.getCell(98);
			cel98.setCellValue(ksjcBean.getData97());
			
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

	public CommoTrouble getCommoTrouble(){
		return this.commoTrouble;
	}

	public void setCommoTrouble(CommoTrouble commoTrouble){
		this.commoTrouble = commoTrouble;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PhotoPic> getPicList01() {
		return picList01;
	}

	public void setPicList01(List<PhotoPic> picList01) {
		this.picList01 = picList01;
	}

	public List<PhotoPic> getPicList02() {
		return picList02;
	}

	public void setPicList02(List<PhotoPic> picList02) {
		this.picList02 = picList02;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ContentInformations getContentInformations() {
		return contentInformations;
	}

	public void setContentInformations(ContentInformations contentInformations) {
		this.contentInformations = contentInformations;
	}
	

	public String getJcBeginDate() {
		return jcBeginDate;
	}
	public void setJcBeginDate(String jcBeginDate) {
		this.jcBeginDate = jcBeginDate;
	}
	public String getJcEndDate() {
		return jcEndDate;
	}
	public void setJcEndDate(String jcEndDate) {
		this.jcEndDate = jcEndDate;
	}
	public String getWcBeginDate() {
		return wcBeginDate;
	}
	public void setWcBeginDate(String wcBeginDate) {
		this.wcBeginDate = wcBeginDate;
	}
	public String getWcEndDate() {
		return wcEndDate;
	}
	public void setWcEndDate(String wcEndDate) {
		this.wcEndDate = wcEndDate;
	}
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String[] getShenheList() {
		return shenheList;
	}

	public void setShenheList(String[] shenheList) {
		this.shenheList = shenheList;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public List<DeptDataBean> getQylxs() {
		return qylxs;
	}
	public void setQylxs(List<DeptDataBean> qylxs) {
		this.qylxs = qylxs;
	}
	public DeptDataBean getQylx() {
		return qylx;
	}
	public void setQylx(DeptDataBean qylx) {
		this.qylx = qylx;
	}
	public ZfwsData getZfwsData() {
		return zfwsData;
	}
	public void setZfwsData(ZfwsData zfwsData) {
		this.zfwsData = zfwsData;
	}
	public String getQueryJhwcsjStart() {
		return queryJhwcsjStart;
	}
	public void setQueryJhwcsjStart(String queryJhwcsjStart) {
		this.queryJhwcsjStart = queryJhwcsjStart;
	}
	public String getQueryJhwcsjEnd() {
		return queryJhwcsjEnd;
	}
	public void setQueryJhwcsjEnd(String queryJhwcsjEnd) {
		this.queryJhwcsjEnd = queryJhwcsjEnd;
	}

	public List<XzzywhBean> getXzzywhList() {
		return xzzywhList;
	}

	public void setXzzywhList(List<XzzywhBean> xzzywhList) {
		this.xzzywhList = xzzywhList;
	}

	public XzzywhBean getXzzywhBean() {
		return xzzywhBean;
	}

	public void setXzzywhBean(XzzywhBean xzzywhBean) {
		this.xzzywhBean = xzzywhBean;
	}

	public List<KsjcBean> getKsjcList() {
		return ksjcList;
	}

	public void setKsjcList(List<KsjcBean> ksjcList) {
		this.ksjcList = ksjcList;
	}

	public KsjcBean getKsjcBean() {
		return ksjcBean;
	}

	public void setKsjcBean(KsjcBean ksjcBean) {
		this.ksjcBean = ksjcBean;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}
}
