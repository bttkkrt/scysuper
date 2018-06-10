/**
 * Class Name:DangerousChemicalsPlanService
 * Class Description：应急救援预案备案事项告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.emergencyRescuePlan.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.emergencyRescuePlan.entity.EmergencyRescuePlan;
import com.jshx.emergencyRescuePlan.service.EmergencyRescuePlanService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class EmergencyRescuePlanAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private EmergencyRescuePlan emergencyRescuePlan = new EmergencyRescuePlan();
	
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
	private String tempDeptCode;
	
	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}
	
	  /**
     * 安全生产机构管理表
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
	
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

	@Autowired
	private SzwxPhotoService szwxPhotoService;

	/**
	 * 业务类
	 */
	@Autowired
	private EmergencyRescuePlanService emergencyRescuePlanService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	/**
	 * 操作者id
	 */
	private String createUserID;
	
	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
    
	/**
	 * 备案开始时间
	 */
	private String queryPlanStart;
	/**
	 * 备案结束时间
	 */
	private String queryPlanEnd;
	
	public String getQueryPlanStart() {
		return queryPlanStart;
	}

	public void setQueryPlanStart(String queryPlanStart) {
		this.queryPlanStart = queryPlanStart;
	}

	public String getQueryPlanEnd() {
		return queryPlanEnd;
	}

	public void setQueryPlanEnd(String queryPlanEnd) {
		this.queryPlanEnd = queryPlanEnd;
	}

	/**
	 * 回执开始时间
	 */
	private String queryTbrqStart;
	/**
	 * 回执结束时间
	 */
	private String queryTbrqEnd;
	
	public String getQueryTbrqStart() {
		return queryTbrqStart;
	}

	public void setQueryTbrqStart(String queryTbrqStart) {
		this.queryTbrqStart = queryTbrqStart;
	}

	public String getQueryTbrqEnd() {
		return queryTbrqEnd;
	}
	
	public void setQueryTbrqEnd(String queryTbrqEnd) {
		this.queryTbrqEnd = queryTbrqEnd;
	}
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * Function Name: init
	 * Function Description：初始化应急救援预案备案事项告知书列表
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String init(){
		//根据用户部门职责判断权限
		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && (deptRole.contains(SysPropertiesUtil.getProperty("whpCode")) || deptRole.contains(SysPropertiesUtil.getProperty("yhbzCode"))))
		{
			flag = "1";
		}
		return SUCCESS;
	}
	
	/**
	 * Function Name: list
	 * Function Description：查询应急救援预案备案事项告知书列表
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != emergencyRescuePlan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != emergencyRescuePlan.getCompanyName()) && (0 < emergencyRescuePlan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + emergencyRescuePlan.getCompanyName().trim() + "%");
			}

			if ((null != emergencyRescuePlan.getPlanId()) && (0 < emergencyRescuePlan.getPlanId().trim().length())){
				paraMap.put("planId", "%" + emergencyRescuePlan.getPlanId().trim() + "%");
			}
			if ((null != emergencyRescuePlan.getSzzid()) && (0 < emergencyRescuePlan.getSzzid().trim().length())){
				paraMap.put("szzid", emergencyRescuePlan.getSzzid().trim());
			}
			if ((null != emergencyRescuePlan.getPlanJg()) && (0 < emergencyRescuePlan.getPlanJg().trim().length())){
				paraMap.put("planJg", "%" + emergencyRescuePlan.getPlanJg().trim() + "%");
			}
		}
		if (null != queryPlanStart){
			paraMap.put("startPlan", queryPlanStart);
		}

		if (null != queryPlanEnd){
			paraMap.put("endPlan", queryPlanEnd);
		}
		if (null != queryTbrqStart){
			paraMap.put("startTbrq", queryTbrqStart);
		}

		if (null != queryTbrqEnd){
			paraMap.put("endTbrq", queryTbrqEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid",company.getId());
		}
		pagination = emergencyRescuePlanService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * Function Name: view
	 * Function Description：查看应急救援预案备案事项告知书信息
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String view() throws Exception{
		if((null != emergencyRescuePlan)&&(null != emergencyRescuePlan.getId()))
		{
			emergencyRescuePlan = emergencyRescuePlanService.getById(emergencyRescuePlan.getId());
			String username = emergencyRescuePlan.getUserName();
			if(username != null && !"".equals(username))
			{
				String[] s = username.split("\r\n");
				username = "";
				for(String a:s)
				{
					username += "<p style='text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt;text-indent:2em'>" + a + "</p>";
				}
			}
			emergencyRescuePlan.setUserName(username);
			if(emergencyRescuePlan.getLinkId() != null && !"".equals(emergencyRescuePlan.getLinkId()))
			{
				Map map = new HashMap();
				map.put("taskProId",emergencyRescuePlan.getLinkId());
				map.put("picType","emergencyRescuePlan");
			    picList01 = szwxPhotoService.findPicPath(map);//获取执法文书材料	
			}
		}
		return VIEW;
	}

	/**
	 * Function Name: initEdit
	 * Function Description：初始化应急救援预案备案事项告知书修改信息
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String initEdit() throws Exception{
		if((null != emergencyRescuePlan)&&(null != emergencyRescuePlan.getId()))
		{
			emergencyRescuePlan = emergencyRescuePlanService.getById(emergencyRescuePlan.getId());
			if(emergencyRescuePlan.getLinkId() == null || "".equals(emergencyRescuePlan.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				emergencyRescuePlan.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",emergencyRescuePlan.getLinkId());
				map.put("picType","emergencyRescuePlan");
			    picList01 = szwxPhotoService.findPicPath(map);//获取执法文书材料	
			}
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			emergencyRescuePlan.setSendTime(sdf.format(new Date()));
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			String s = "XAJ-WH-YJYABA-" + sdf1.format(new Date());
			Map mm = new HashMap();
			mm.put("planId", s + "%");
			List<EmergencyRescuePlan> list = emergencyRescuePlanService.findEmergencyRescuePlan(mm);
			String num = "";
			if(list.size() != 0)
			{
				String planId = list.get(0).getPlanId();
				planId = planId.replace(s+"-", "");
				if(planId.startsWith("00"))
				{
					planId = planId.substring(2,3);
					int size = Integer.parseInt(planId) + 1;
					if(size < 10)
					{
						num = "00" + size;
					}
					else
					{
						num = "010";
					}
				}
				else if(planId.startsWith("0"))
				{
					planId = planId.substring(1,3);
					int size = Integer.parseInt(planId) + 1;
					if(size < 100)
					{
						num = "0" + size;
					}
					else
					{
						num = "100";
					}
				}
				else
				{
					int size = Integer.parseInt(planId) + 1;
					num = size + "";
				}
			}
			else
			{
				num  = "001";
			}
			String planId = s+"-" + num ;
			emergencyRescuePlan.setPlanId(planId);
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			emergencyRescuePlan.setLinkId(linkId);
			emergencyRescuePlan.setUserName("       你单位的危险化学品事故应急救援预案已在我局备案。希望你单位严格遵守安全生产有关法律、法规、规章和标准的规定，加强日常安全生产管理，并做到：按照你单位制定的危险化学品事故应急救援预案，配备应急救援人员和必要的应急救援器材、设备，不断完善应急救援预案，每年演练不少于两次。");
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
	    if(deptCode.length() == countyDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
	    }else if(deptCode.length() == cityDeptCodeLength){
	    	tempDeptCode = "1";
	    }else if(deptCode.length() == townDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, townDeptCodeLength-6);
	    }
	    return EDIT;
	}

	/**
	 * Function Name: save
	 * Function Description：保存信息（包括新增和修改）
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String save() throws Exception{
		try {
			if(emergencyRescuePlan.getQyid() != null && !"".equals(emergencyRescuePlan.getQyid()))
			{
				Map map = new HashMap();
				map.put("companyId", emergencyRescuePlan.getQyid());
				CompanyBackUp company = companyService.getCompanyBackupById(map);
				if(!emergencyRescuePlan.getCompanyName().equals(company.getCompanyname()))
				{
					emergencyRescuePlan.setQyid(null);
					Department dept = deptService.findDeptByDeptCode(emergencyRescuePlan.getSzzid());
					emergencyRescuePlan.setSzzid(dept.getDeptCode());
					emergencyRescuePlan.setSzzname(dept.getDeptName());
				}
				else
				{
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					emergencyRescuePlan.setSzzid(dept.getDeptCode());
					emergencyRescuePlan.setSzzname(dept.getDeptName());
				}
			}
			else if(emergencyRescuePlan.getSzzid() != null && !"".equals(emergencyRescuePlan.getSzzid()))
			{
				Department dept = deptService.findDeptByDeptCode(emergencyRescuePlan.getSzzid());
				emergencyRescuePlan.setSzzid(dept.getDeptCode());
				emergencyRescuePlan.setSzzname(dept.getDeptName());
			}
			if ("add".equalsIgnoreCase(this.flag)){
				emergencyRescuePlan.setDeptId(this.getLoginUserDepartmentId());
				emergencyRescuePlan.setDelFlag(0);
				emergencyRescuePlan.setCreateTime(new Date());
				emergencyRescuePlan.setCreateUserID(this.getLoginUserId());
				emergencyRescuePlanService.save(emergencyRescuePlan);
			}else{
				emergencyRescuePlanService.update(emergencyRescuePlan);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * Function Name: delete
	 * Function Description：删除信息
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String delete() throws Exception{
	    try{
			emergencyRescuePlanService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * Function Name: print
	 * Function Description：打印
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public String print() throws Exception{
		if((null != emergencyRescuePlan)&&(null != emergencyRescuePlan.getId()))
		{
			emergencyRescuePlan = emergencyRescuePlanService.getById(emergencyRescuePlan.getId());
			
			//导出到word模板，新增by陆婷 2014-07-17
			Map<String, String> map=new HashMap<String, String>();
	        
			map.put("planId", isNull(emergencyRescuePlan.getPlanId()));
	        map.put("companyName", isNull(emergencyRescuePlan.getCompanyName()));
	        String username = isNull(emergencyRescuePlan.getUserName());
	        int length = 1;
			if(username != null && !"".equals(username))
			{
				String[] s = username.split("\r\n");
				length = s.length;
				map.put("userName", s[0]);
				for(int i=1;i<s.length;i++)
				{
					map.put("user" + i + "Name",s[i]);
				}
			}
			else
			{
				map.put("userName", "");
			}
	        String sendTime = isNull(emergencyRescuePlan.getSendTime());
	        if(sendTime != null && !"".equals(sendTime))
	        {
	        	String[] s = sendTime.split("-");
	        	if(s.length == 3)
	        	{
	        		String year = s[0];
	        		String month = s[1];
	        		String day = s[2];
	        		if(month.startsWith("0"))
	        		{
	        			month = month.substring(1);
	        		}
	        		if(day.startsWith("0"))
	        		{
	        			day = day.substring(1);
	        		}
	        		sendTime = year + "年" + month + "月" + day + "日";
	        	}
	        }
	        map.put("sendTime", sendTime );
	        
	        
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			root = root + "emergencyRescuePlan.doc";
			
			HWPFDocument document= replaceDoc(root, map,length);
	        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		        try {
		              document.write(ostream);
		              Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=emergencyRescuePlan.doc");
		              OutputStream out = Struts2Util.getResponse().getOutputStream();
		              out.write(ostream.toByteArray());
		              out.close();  
	  		}
	  		catch (Exception e) {
	  			e.printStackTrace();
	  		}
		}
		return null;
	}

	public String isNull(String s)
	{
		if(s == null)
		{
			return "";
		}
		else
		{
			s = s.replaceAll(" ", "");
			return s;
		}
	}
	
	 /**
     * 读取word模板并替换变量
     * @param srcPath
     * @param map
     * @return
     */
	public HWPFDocument replaceDoc(String srcPath, Map<String, String> map,int length) {
        try {
            // 读取word模板
        	File file = new File(srcPath);
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
           // 读取word文本内容
           Range bodyRange = doc.getRange();
           Paragraph para = bodyRange.getParagraph(7);
           for(int i=1;i<length;i++)
           {
        	   para.insertAfter("    ${user" + i + "Name}\r");
           }
           // 替换文本内容
           for (Map.Entry<String, String> entry : map.entrySet()) {
               bodyRange.replaceText("${" + entry.getKey() + "}", entry
                       .getValue());
           }
           return doc;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
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
			root = root.replace("webapps","virtualdir/upload/file/emergencyRescuePlan/");
			destFName.append(root);
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
			String picname = "emergencyRescuePlan/"+imgName;
			taskPic.setPicName(picname);
			taskPic.setTaskProId(emergencyRescuePlan.getLinkId());
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setPicType("emergencyRescuePlan");//设置图片类型 安全管理表
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

	public EmergencyRescuePlan getEmergencyRescuePlan(){
		return this.emergencyRescuePlan;
	}

	public void setEmergencyRescuePlan(EmergencyRescuePlan emergencyRescuePlan){
		this.emergencyRescuePlan = emergencyRescuePlan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
