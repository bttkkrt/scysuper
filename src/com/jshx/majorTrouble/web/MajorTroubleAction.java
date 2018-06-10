/**
 * Class Name: MajorTroubleAction
 * Class Description：重大安全隐患
 */
package com.jshx.majorTrouble.web;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.majorTrouble.entity.MajorTrouble;
import com.jshx.majorTrouble.entity.QyData;
import com.jshx.majorTrouble.entity.TongJiData;
import com.jshx.majorTrouble.service.MajorTroubleService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class MajorTroubleAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private MajorTrouble majorTrouble = new MajorTrouble();
	
	List<TongJiData> datas = new ArrayList<TongJiData>();
	TongJiData data = new TongJiData();
	private Date queryJhwcsjStart;

	private Date queryJhwcsjEnd;
	/**
	 * lj 2013-07-19 整改方案列表
	 */
    private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
    /**
	 * lj 2013-07-19 监控措施列表
	 */
	private List<PhotoPic> picList02 = new ArrayList<PhotoPic>();
	 /**
	 * lj 2013-07-19 整改前图片列表
	 */
	private List<PhotoPic> picList03 = new ArrayList<PhotoPic>();
	 /**
	 * lj 2013-07-19 整改后图片列表
	 */
	private List<PhotoPic> picList04 = new ArrayList<PhotoPic>(); 
	/**
	 * lj 2013-07-22 执法文书1列表
	 */
	private List<PhotoPic> zfwsList01 = new ArrayList<PhotoPic>();
	/**
	 * lj 2013-07-22 执法文书2列表
	 */
	private List<PhotoPic> zfwsList02 = new ArrayList<PhotoPic>();

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

	/**
	 * 业务类
	 */
	@Autowired
	private MajorTroubleService majorTroubleService;
	 @Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CommoTroubleService commoTroubleService;

	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 新增关联图片 附件材料的id
	 */
	private  String linkId;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * 新增字段 1 表示信息填报  2 表示信息审核
	 */
	private String type;
	
	/**
	 * 添加挂牌时间 范围字段 2013-07-25
	 * @return
	 */
	private String gpBeginDate;
	private String gpEndDate;
	/**
	 * 添加整改完成时间 范围字段 2013-07-25
	 * @return
	 */
	private String wcBeginDate;
	private String wcEndDate;
	/**
	 * 添加验收时间 范围字段 2013-07-25
	 * @return
	 */
	private String ysBeginDate;
	private String ysEndDate;

	private String createUserID;
	
	private String[] shenheList ;
	private String deptId;
	private String tempDeptCode;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	private String da;
	
	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
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
		if(null != majorTrouble){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != majorTrouble.getSzz()) && (0 < majorTrouble.getSzz().trim().length())){
				paraMap.put("szz", majorTrouble.getSzz().trim());
			}

			if ((null != majorTrouble.getQymc()) && (0 < majorTrouble.getQymc().trim().length())){
				paraMap.put("qymc", "%" + majorTrouble.getQymc().trim() + "%");
			}
			if ((null != majorTrouble.getShzt()) && (0 < majorTrouble.getShzt().trim().length())){
				paraMap.put("shzt", majorTrouble.getShzt().trim());
			}
			if ((null != gpBeginDate) && (0 < gpBeginDate.trim().length())){
				paraMap.put("gpBeginDate", gpBeginDate.trim());
			}
			
			if ((null != gpEndDate) && (0 < gpEndDate.trim().length())){
				paraMap.put("gpEndDate", gpEndDate.trim());
			}
			if ((null != wcBeginDate) && (0 < wcBeginDate.trim().length())){
				paraMap.put("wcBeginDate", wcBeginDate.trim());
			}
			
			if ((null != wcEndDate) && (0 < wcEndDate.trim().length())){
				paraMap.put("wcEndDate", wcEndDate.trim());
			}
			if ((null != ysBeginDate) && (0 < ysBeginDate.trim().length())){
				paraMap.put("ysBeginDate", ysBeginDate.trim());
			}
			
			if ((null != ysEndDate) && (0 < ysEndDate.trim().length())){
				paraMap.put("ysEndDate", ysEndDate.trim());
			}
			if ((null != majorTrouble.getSzc() )&& (0 < majorTrouble.getSzc().trim().length())){
				paraMap.put("szc",majorTrouble.getSzc().trim());
			}
			if ((null != majorTrouble.getDeptId()) && (0 < majorTrouble.getDeptId().trim().length())){
				paraMap.put("deptId", majorTrouble.getDeptId().trim());
			}
			if ((null != majorTrouble.getZgwcrq()) && (0 < majorTrouble.getZgwcrq().trim().length())){
				paraMap.put("zgwcrq", "shijian");
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		
		String dpcode = this.getLoginUserDepartment().getDeptCode();//判断是否是企业部门
		if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			type="3";
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qymcId", company.getId());
		}
/*		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))){
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
		}*/
//		else if(dpcode.startsWith("002003")) //监察大队
//		{
//			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
//		}
//		else if(dpcode.startsWith("002005")) //办公室
//		{
//			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
//		} 
		pagination = majorTroubleService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
			if((null != majorTrouble)&&(null != majorTrouble.getId())){
				majorTrouble = majorTroubleService.getById(majorTrouble.getId());
				if(majorTrouble.getShenhe() != null && !"".equals(majorTrouble.getShenhe()))
				{
					shenheList = majorTrouble.getShenhe().split("#");
				}
				if(majorTrouble.getLinkId() == null || "".equals(majorTrouble.getLinkId()))
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					majorTrouble.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("taskProId",majorTrouble.getLinkId());
					map.put("picType","zgfa");
				    picList01 = szwxPhotoService.findPicPath(map);//获取整改法案材料
				    map.put("picType","jkcs");
				    picList02 = szwxPhotoService.findPicPath(map);//获取监控措施材料
				    map.put("picType","zgqtp");
				    picList03 = szwxPhotoService.findPicPath(map);//获取整改前图片
				    map.put("picType","zghtp");
				    picList04 = szwxPhotoService.findPicPath(map);//获取整改后图片
				    map.put("picType","zfws01");
				    zfwsList01 = szwxPhotoService.findPicPath(map);//获取执法文书01图片
				    map.put("picType","zfws02");
				    zfwsList02 = szwxPhotoService.findPicPath(map);//获取执法文书02图片
				}
			}
			if((null != majorTrouble)&&(null == majorTrouble.getLinkId())){
				linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				majorTrouble.setLinkId(linkId);
			}


			int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
			int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
			int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
		    String deptCode = this.getLoginUserDepartment().getDeptCode();
		    if(deptCode.length() == countyDeptCodeLength){
		    	tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
		    }else if(deptCode.length() == cityDeptCodeLength){
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
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if(majorTrouble.getYhs() == null || "".equals(majorTrouble.getYhs()))
		{
			majorTrouble.setYhs("0");
		}
		if(majorTrouble.getZgs() == null || "".equals(majorTrouble.getZgs()))
		{
			majorTrouble.setZgs("0");
		}
		if(majorTrouble.getZgzj() == null || "".equals(majorTrouble.getZgzj()))
		{
			majorTrouble.setZgzj("0");
		}
		if(majorTrouble.getQymcId() != null)
		{
//			Map mm = new HashMap();
//			mm.put("companyId", majorTrouble.getQymcId());
			Company	company=companyService.getById(majorTrouble.getQymcId());
			if(!majorTrouble.getQymc().equals(company.getCompanyname()))
			{
				majorTrouble.setQymcId(null);
				Department dept = deptService.findDeptByDeptCode(majorTrouble.getSzz());
				majorTrouble.setSzzname(dept.getDeptName());
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				if(null != dept){
					majorTrouble.setSzzname(dept.getDeptName());
				}
				majorTrouble.setQylx(company.getQylx());
				majorTrouble.setHyfl(company.getHyfl());
				majorTrouble.setQygm(company.getQygm());
				majorTrouble.setQyzclx(company.getQyzclx());
				majorTrouble.setIfwhpqylx(company.getIfwhpqylx());
				majorTrouble.setIfyhbzjyqy(company.getIfyhbzjyqy());
				majorTrouble.setIfzywhqylx(company.getIfzywhqylx());
				majorTrouble.setSzc(company.getSzc());
				majorTrouble.setSzcname(company.getSzcname());
				majorTrouble.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				majorTrouble.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			}
		}
		if ("add".equalsIgnoreCase(this.flag)){
			majorTrouble.setShzt("0");
			majorTrouble.setDeptId(this.getLoginUserDepartmentId());
			majorTrouble.setDelFlag(0);
			majorTrouble.setCreateTime(new Date());
			majorTrouble.setCreateUserID(this.getLoginUserId());
			
			majorTroubleService.save(majorTrouble);
		}else{
			if("upload".equals(flag)){
				
				majorTrouble.setShzt("3");//上上报整改信息 设置状态为已整改待审核
			}
			majorTroubleService.update(majorTrouble);
		}
		flag = "1";
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			majorTroubleService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转到图片导入界面
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 图片保存
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
			String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\njyzj\
			root = root.substring(0,root.indexOf("webapps")+8);
			root = root.replaceAll("\\\\", "/");
			root = root.replace("webapps","virtualdir/upload");
			destFName.append(root).append("photo/");
			outdir = new File(destFName.toString());
			if(Filedata!= null && !Filedata.isEmpty()){
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
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-18
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", imgName);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
				e.printStackTrace();
			}
	}
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
	 *  李军 2013-07-18 
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
	 * 更新隐患的状态 李军 2013-07-19
	 */
	public String updateStatus() throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if((null != majorTrouble)&&(null != majorTrouble.getId())){
					String status = majorTrouble.getShzt();
					String remark = majorTrouble.getRemark();
					String a = "";
					majorTrouble = majorTroubleService.getById(majorTrouble.getId());
					if("2".equals(status))
					{
						a = "审核不通过";
					}
					else if("1".equals(status))
					{
						a = "审核通过";
					}
					String ss = this.getLoginUser().getDisplayName()+"于" + sdf.format(new Date()) + "进行了审核，审核结果为：["+a + "],备注为：[" + remark+"]";
					String shenhe = majorTrouble.getShenhe();
					if(shenhe == null || "".equals(shenhe))
					{
						shenhe = ss;
					}
					else
					{
						shenhe = shenhe + "#" + ss ;
					}
					majorTrouble.setShenhe(shenhe);
					majorTrouble.setShzt(status);
					majorTrouble.setRemark(remark);
					majorTroubleService.update(majorTrouble);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RELOAD;
	}
	public String tongJi(){
		try {
			Map map = new HashMap();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "tongji";
	}
	@SuppressWarnings("unchecked")
	public String deptData(){
		try {
			if("5".equals(da)){//按上报部门统计

				Map map = new HashMap();
				map.put("queryJcsjStart", queryJhwcsjStart);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				map.put("data", 5);
				//查询科室上报的数据
				datas = majorTroubleService.getTongJiDataListByKeShi(map);
				data = majorTroubleService.getTongJiDataBean(map);
			}else{//按所属部门统计

				Map map = new HashMap();
				map.put("queryJcsjStart", queryJhwcsjStart);
				map.put("queryJcsjEnd", queryJhwcsjEnd);
				datas = majorTroubleService.getTongJiDataList(map);
				data = majorTroubleService.getTongJiDataBean(map);
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
			datas = majorTroubleService.getTongJiDataListByQylx(map);
			data = majorTroubleService.getTongJiDataBeanByQylx(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "qylx";
	}
	
	/**
	 * 按部门 重大隐患统计 导出
	 * 2013-12-2
	 * 陆婷
	 */
	public void deptDataExport(){
		try {
			getResponse().addHeader("Content-Disposition", "attachment;filename=zdyhdept.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按部门 重大安全隐患统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按部门 重大安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 6)); 
	        
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
	        if("5".equals(da)){//按上报部门统计
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
	        ccl3.setCellValue("隐患数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("整改数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("整改率");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("整改资金");
	        ccl6.setCellStyle(cs);
	        
	        
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
			if("5".equals(da)){//按上报部门统计
				datas = majorTroubleService.getTongJiDataListByKeShi(map);
				data = majorTroubleService.getTongJiDataBean(map);
			}else{//按所属部门统计
				datas = majorTroubleService.getTongJiDataList(map);
				data = majorTroubleService.getTongJiDataBean(map);
			}
			
			int num = 2;
			for(TongJiData tjyhbean:datas)
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
		        ce3.setCellValue(tjyhbean.getYhs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getZgs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getZgl());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getZgzj());
		        ce6.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(data.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(data.getJcqys());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(data.getYhs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(data.getZgs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(data.getZgl());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(data.getZgzj());
	        ce6.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按企业类型重大隐患统计 导出
	 * 2013-12-2
	 * 陆婷
	 */
	@SuppressWarnings("unchecked")
	public void qylxDataExport(){
		try {
			getResponse().addHeader("Content-Disposition", "attachment;filename=zdyhqylx.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按企业类型重大安全隐患统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按企业类型重大安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 6)); 
	        
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
	        ccl1.setCellValue("总企业人数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("检查企业数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("隐患数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("整改数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("整改率");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("整改资金");
	        ccl6.setCellStyle(cs);
	        
	        
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
			datas = majorTroubleService.getTongJiDataListByQylx(map);
			data = majorTroubleService.getTongJiDataBeanByQylx(map);
			
			int num = 2;
			for(TongJiData tjyhbean:datas)
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
		        ce3.setCellValue(tjyhbean.getYhs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getZgs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getZgl());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getZgzj());
		        ce6.setCellStyle(c);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(data.getQyTotal());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(data.getJcqys());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(data.getYhs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(data.getZgs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(data.getZgl());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(data.getZgzj());
	        ce6.setCellStyle(c);
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 重大安全隐患按企业统计查询
	 * 2013-12-13
	 * 陆婷
	 */
	public void majorTroubleDataQyQuery()
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
		
		List<QyData> qylist = new ArrayList<QyData>();
		qylist = majorTroubleService.getMajorTroubleQyListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = majorTroubleService.getMajorTroubleQyListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(qylist);
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 初始化重大安全隐患按企业统计页面
	 * 2013-12-13
	 * 陆婷
	 */
	public String majorTroubleDataQyList()
	{
		return SUCCESS;
	}

	/**
	 * 重大安全隐患按企业统计导出
	 * 陆婷
	 * 2013-12-13
	 */
	public void majorTroubleDataQyExport()
	{
		try {
			getResponse().addHeader("Content-Disposition", "attachment;filename=zdyhqy.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按企业重大安全隐患统计");
		    sheet.setColumnWidth(0, 15000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 8000);
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按企业重大安全隐患统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 4)); 
	        
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
	        ccl3.setCellValue("隐患数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(2);
	        ccl4.setCellValue("整改数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(3);
	        ccl5.setCellValue("整改率");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(4);
	        ccl6.setCellValue("整改资金");
	        ccl6.setCellStyle(cs);
	        
	        
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
			List<QyData> qylist = majorTroubleService.getTongJiDataListByQy(map);
			
			int num = 2;
			for(QyData tjyhbean:qylist)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(1);
		        ce3.setCellValue(tjyhbean.getYhs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(2);
		        ce4.setCellValue(tjyhbean.getZgs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(3);
		        ce5.setCellValue(tjyhbean.getZgl());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(4);
		        ce6.setCellValue(tjyhbean.getZgzj());
		        ce6.setCellStyle(c);
		        num++;
			}
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

	public MajorTrouble getMajorTrouble(){
		return this.majorTrouble;
	}

	public void setMajorTrouble(MajorTrouble majorTrouble){
		this.majorTrouble = majorTrouble;
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

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	public List<PhotoPic> getPicList03() {
		return picList03;
	}

	public void setPicList03(List<PhotoPic> picList03) {
		this.picList03 = picList03;
	}

	public List<PhotoPic> getPicList04() {
		return picList04;
	}

	public void setPicList04(List<PhotoPic> picList04) {
		this.picList04 = picList04;
	}

	public List<PhotoPic> getZfwsList01() {
		return zfwsList01;
	}

	public void setZfwsList01(List<PhotoPic> zfwsList01) {
		this.zfwsList01 = zfwsList01;
	}

	public List<PhotoPic> getZfwsList02() {
		return zfwsList02;
	}

	public void setZfwsList02(List<PhotoPic> zfwsList02) {
		this.zfwsList02 = zfwsList02;
	}

	public String getGpBeginDate() {
		return gpBeginDate;
	}

	public void setGpBeginDate(String gpBeginDate) {
		this.gpBeginDate = gpBeginDate;
	}

	public String getGpEndDate() {
		return gpEndDate;
	}

	public void setGpEndDate(String gpEndDate) {
		this.gpEndDate = gpEndDate;
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

	public String getYsBeginDate() {
		return ysBeginDate;
	}

	public void setYsBeginDate(String ysBeginDate) {
		this.ysBeginDate = ysBeginDate;
	}

	public String getYsEndDate() {
		return ysEndDate;
	}

	public void setYsEndDate(String ysEndDate) {
		this.ysEndDate = ysEndDate;
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

	public List<TongJiData> getDatas() {
		return datas;
	}

	public void setDatas(List<TongJiData> datas) {
		this.datas = datas;
	}

	public TongJiData getData() {
		return data;
	}

	public void setData(TongJiData data) {
		this.data = data;
	}

	public Date getQueryJhwcsjStart() {
		return queryJhwcsjStart;
	}

	public void setQueryJhwcsjStart(Date queryJhwcsjStart) {
		this.queryJhwcsjStart = queryJhwcsjStart;
	}

	public Date getQueryJhwcsjEnd() {
		return queryJhwcsjEnd;
	}

	public void setQueryJhwcsjEnd(Date queryJhwcsjEnd) {
		this.queryJhwcsjEnd = queryJhwcsjEnd;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}
}
