/**
 * Class Name: GpdbAction
 * Class Description：挂牌督办
 */
package com.jshx.gpdb.web;

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

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.areacodeRelation.entity.AreacodeRelation;
import com.jshx.areacodeRelation.service.AreacodeRelationService;
import com.jshx.commonTrouble.entity.KsjcBean;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.gpdb.service.GpdbService;
import com.jshx.heflRelation.entity.HyflRelation;
import com.jshx.heflRelation.service.HyflRelationService;
import com.jshx.httpData.service.HttpDataService;
import com.jshx.httpData.util.ServiceTest;
//import com.jshx.httpData.util.ServiceTest;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

public class GpdbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Gpdb gpdb = new Gpdb();

	/**
	 * 业务类
	 */
	@Autowired
	private HttpDataService httpDataService;
	@Autowired
	private AreacodeRelationService areacodeRelationService;
	@Autowired
	private HyflRelationService hyflRelationService;
	@Autowired
	private GpdbService gpdbService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private JshxZcyhsbService jshxZcyhsbService;
	
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String picName;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag = "";

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	//挂牌开始时间
	private Date queryGpsjStart;
	//挂牌结束时间
	private Date queryGpsjEnd;
	//整改完成开始时间
	private Date queryZgwcsjStart;
	//整改完成结束时间
	private Date queryZgwcsjEnd;
	//验收开始时间
	private Date queryYssjStart;
	//验收结束时间
	private Date queryYssjEnd;
	//检查文书
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	//整改前图片
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	//复查文书
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	//整改方案
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	//监测措施
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	//整改后图片
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	//类型
	private String type;
	
	private String types;
	
	private String createUserID;
	
	private String[] shenheList ;
	
	private String tt;
	private String tempDeptCode;
	
	/**
	 * 初始化挂牌督办列表
	 * author：陆婷
	 * 2013-09-17
	 */
	public String init(){
		//根据用户角色判断权限
		List<UserRight> list = this.getLoginUser().getUserRoles();
		flag = "0";
		for(UserRight u:list)
		{
			String rolecode = u.getRole().getRoleCode();
			if(rolecode.equals(SysPropertiesUtil.getProperty("zdbsyRoleCode"))) //安监中队队员
			{
				flag = "1";
				break;
			}
			else if(rolecode.equals(SysPropertiesUtil.getProperty("zddzRoleCode"))) //安监中队队长
			{
				flag = "2";
				break;
			}
			else if(rolecode.equals(SysPropertiesUtil.getProperty("zhcczRoleCode")) || rolecode.equals(SysPropertiesUtil.getProperty("zhcbsyRoleCode"))) //综合科
			{
				flag = "3";
				break;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询挂牌督办列表
	 * author：陆婷
	 * 2013-09-17
	 */
	public void list() throws Exception{
		//数据上报
		List<AreacodeRelation> areacodeRelations = areacodeRelationService.findAll(null);
//		List<Map> coms = httpDataService.queryCompanysByMap(new HashMap(), 0, 0);
//		List<CompanyBackUp> companys = companyService.findCompanyBackUpList(new HashMap());
//		List<HyflRelation> hyflRelations = hyflRelationService.findAll(new HashMap());
//		List<CompanyBackUp> cbs = gpdbService.tranData(coms, areacodeRelations, companys, hyflRelations);
//		for (CompanyBackUp companyBackUp : cbs) {
//			companyService.updateCompanyBackUp(companyBackUp);
//		}
//		List<Map> zcyhsb = httpDataService.queryZcyhsbByMap(null);
//		for (Map map : zcyhsb) {
//			String param = ServiceTest.parseMapToZCYHSBStr(map, areacodeRelations);
//			try {
//				String guid = ServiceTest.doPostSoap1_1(param, "");
//				System.out.println(guid+",success:"+ ServiceTest.convertObject(map.get("QYMC")));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		String comid = (String) getSessionAttribute("jshx_company_qyid_choose");
		if(comid != null && !"".equals(comid))
		{
			paraMap.put("qyid", comid);
		}
		if(null != gpdb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gpdb.getSzzid()) && (0 < gpdb.getSzzid().trim().length())){
				paraMap.put("szzid", gpdb.getSzzid().trim());
			}

			if ((null != gpdb.getQymc()) && (0 < gpdb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + gpdb.getQymc().trim() + "%");
			}

			if ((null != gpdb.getYhmc()) && (0 < gpdb.getYhmc().trim().length())){
				paraMap.put("yhmc", "%" + gpdb.getYhmc().trim() + "%");
			}

			if (null != queryGpsjStart){
				paraMap.put("startGpsj", queryGpsjStart);
			}

			if (null != queryGpsjEnd){
				paraMap.put("endGpsj", queryGpsjEnd);
			}

			if (null != queryZgwcsjStart){
				paraMap.put("startZgwcsj", queryZgwcsjStart);
			}

			if (null != queryZgwcsjEnd){
				paraMap.put("endZgwcsj", queryZgwcsjEnd);
			}
			if (null != queryYssjStart){
				paraMap.put("startYssj", queryYssjStart);
			}

			if (null != queryYssjEnd){
				paraMap.put("endYssj", queryYssjEnd);
			}
			if ((null != gpdb.getState()) && (0 < gpdb.getState().trim().length())){
				paraMap.put("state", gpdb.getState().trim());
			}
			if ((null != gpdb.getSzc() )&& (0 < gpdb.getSzc().trim().length())){
				paraMap.put("szc",gpdb.getSzc().trim());
			}
			if ((null != gpdb.getYhlb())&& (0 < gpdb.getYhlb().trim().length())){
				paraMap.put("yhlbs",gpdb.getYhlb().trim());
			}
			
		}
		List<UserRight> list = this.getLoginUser().getUserRoles();
		for(UserRight u:list)
		{
			String rolecode = u.getRole().getRoleCode();
			if(rolecode.equals(SysPropertiesUtil.getProperty("zdbsyRoleCode"))) //安监中队队员
			{
				flag = "1";
				break;
			}
			else if(rolecode.equals(SysPropertiesUtil.getProperty("zddzRoleCode"))) //安监中队队长
			{
				flag = "2";
				break;
			}
			else if(rolecode.equals(SysPropertiesUtil.getProperty("zhcczRoleCode")) || rolecode.equals(SysPropertiesUtil.getProperty("zhcbsyRoleCode"))) //综合科
			{
				flag = "3";
				break;
			}
		}
		if(flag.equals("1"))
		{
			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
		}
		else if(flag.equals("2"))
		{
			List<String> lblist = new ArrayList<String>();
			lblist.add("2");
			paraMap.put("yhlb", lblist);
			paraMap.put("szzid", this.getLoginUserDepartment().getDeptCode());
		}
		else
		{
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.startsWith("002001")) //危化品
			{
				paraMap.put("ifwhpqylx", "1");
			}
			else if(deptCode.startsWith("002002")) // 职业健康管理科
			{
				paraMap.put("ifzywhqylx", "1");
			}
			else if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))// 企业
			{
				CompanyBackUp comb = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", comb.getId());
			}
		}
		pagination = gpdbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看挂牌督办详情
	 * author：陆婷
	 * 2013-09-17
	 */
	public String view() throws Exception{
		if((null != gpdb)&&(null != gpdb.getId()))
		{
			gpdb = gpdbService.getById(gpdb.getId());
			if(gpdb.getLinkid() == null || "".equals(gpdb.getLinkid()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				gpdb.setLinkid(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",gpdb.getLinkid());
				map.put("picType","gpdb1");
				picList1 = szwxPhotoService.findPicPath(map);//获取检查文书
				map.put("picType","gpdb2");
				picList2 = szwxPhotoService.findPicPath(map);//获取整改前图片
				map.put("picType","gpdb3");
				picList3 = szwxPhotoService.findPicPath(map);//获取复查文书
				map.put("picType","gpdb4");
				picList4 = szwxPhotoService.findPicPath(map);//获取整改方案
				map.put("picType","gpdb5");
				picList5 = szwxPhotoService.findPicPath(map);//获取监测措施
				map.put("picType","gpdb6");
				picList6 = szwxPhotoService.findPicPath(map);//获取整改后图片
			}
			if(gpdb.getShenhe() != null && !"".equals(gpdb.getShenhe()))
			{
				shenheList = gpdb.getShenhe().split("#");
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			gpdb.setLinkid(linkId);
		}
		return VIEW;
	}

	/**
	 * 初始化挂牌督办修改信息
	 * author：陆婷
	 * 2013-09-17
	 */
	public String initEdit() throws Exception{
		view();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			types ="1";
			gpdb.setSzzid(deptCode.substring(0,9));
		}
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
	 * 保存挂牌督办信息
	 * author：陆婷
	 * 2013-09-17
	 */
	public String save() throws Exception{
		try {
			if(gpdb.getQyid() != null && !"".equals(gpdb.getQyid()))
			{
				Map map = new HashMap();
				map.put("companyId", gpdb.getQyid());
				CompanyBackUp company = companyService.getCompanyBackupById(map);
				if(!gpdb.getQymc().equals(company.getCompanyname()))
				{
					gpdb.setQyid(null);
					Department dept = deptService.findDeptByDeptCode(gpdb.getSzzid());
					gpdb.setSzzid(dept.getDeptCode());
					gpdb.setSzzname(dept.getDeptName());
				}
				else
				{
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					gpdb.setSzzid(dept.getDeptCode());
					gpdb.setSzzname(dept.getDeptName());
					gpdb.setQyid(company.getId());
					gpdb.setQymc(company.getCompanyname());
					gpdb.setQylx(company.getQylx());
					gpdb.setHyfl(company.getHyfl());
					gpdb.setQygm(company.getQygm());
					gpdb.setQyzclx(company.getQyzclx());
					gpdb.setIfwhpqylx(company.getIfwhpqylx());
					gpdb.setIfyhbzjyqy(company.getIfyhbzjyqy());
					gpdb.setIfzywhqylx(company.getIfzywhqylx());
					gpdb.setSzc(company.getSzc());
					gpdb.setSzcname(company.getSzcname());
				}
			}
			else if(gpdb.getSzzid() != null && !"".equals(gpdb.getSzzid()))
			{
				Department dept = deptService.findDeptByDeptCode(gpdb.getSzzid());
				gpdb.setSzzid(dept.getDeptCode());
				gpdb.setSzzname(dept.getDeptName());
			}
			if ("add".equalsIgnoreCase(this.flag)){
				gpdb.setDeptId(this.getLoginUserDepartmentId());
				gpdb.setDelFlag(0);
				if("1".equals(tt))
				{
					gpdb.setState("3");
				}
				else
				{
					gpdb.setState("0");
				}
				gpdb.setCreateTime(new Date());
				gpdb.setCreateUserID(this.getLoginUserId());
				gpdb.setUsername(this.getLoginUser().getDisplayName());
				gpdbService.save(gpdb);
			}else{
				if("1".equals(tt))
				{
					gpdb.setState("3");
				}
				else if("1".equals(type))
				{
					gpdb.setState("1");
				}
				gpdbService.update(gpdb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return RELOAD;
	}

	/**
	 * 删除挂牌督办信息
	 * author：陆婷
	 * 2013-09-17
	 */
	public String delete() throws Exception{
	    try{
			gpdbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转至审核页面
	 * author：陆婷
	 * 2013-09-17
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
	}
	/**
	 * 保存审核信息
	 * author：陆婷
	 * 2013-09-17
	 */
	public String shenhesave()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String state = gpdb.getState();
		String remark = gpdb.getRemark();
		String a = "";
		if((null != gpdb)&&(null != gpdb.getId()))
		{
			Gpdb g = gpdbService.getById(gpdb.getId());
			if("2".equals(state))
			{
				a = "审核不通过";
			}
			else if("3".equals(state))
			{
				a = "审核通过";
			}
			String ss = this.getLoginUser().getDisplayName()+"于" + sdf.format(new Date()) + "进行了审核，审核结果为：["+a + "],备注为：[" + remark+"]";
			String shenhe = g.getShenhe();
			if(shenhe == null || "".equals(shenhe))
			{
				shenhe = ss;
			}
			else
			{
				shenhe = shenhe + "#" + ss ;
			}
			g.setShenhe(shenhe);
			g.setState(state);
			g.setRemark(remark);
			gpdbService.update(g);
		}
		return RELOAD;
	}
	
	/**
	 * 跳转至挂牌督办附件上传页面
	 * author：陆婷
	 * 2013-09-17
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 保存挂牌督办附件信息
	 * author：陆婷
	 * 2013-09-17
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
	 * 删除附件信息
	 * author：陆婷
	 * 2013-09-17
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
	 * 附件下载
	 * author：陆婷
	 * 2013-09-17
	 */
	public void saveFile()
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

	public Gpdb getGpdb(){
		return this.gpdb;
	}

	public void setGpdb(Gpdb gpdb){
		this.gpdb = gpdb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryGpsjStart(){
		return this.queryGpsjStart;
	}

	public void setQueryGpsjStart(Date queryGpsjStart){
		this.queryGpsjStart = queryGpsjStart;
	}

	public Date getQueryGpsjEnd(){
		return this.queryGpsjEnd;
	}

	public void setQueryGpsjEnd(Date queryGpsjEnd){
		this.queryGpsjEnd = queryGpsjEnd;
	}

	public Date getQueryZgwcsjStart(){
		return this.queryZgwcsjStart;
	}

	public void setQueryZgwcsjStart(Date queryZgwcsjStart){
		this.queryZgwcsjStart = queryZgwcsjStart;
	}

	public Date getQueryZgwcsjEnd(){
		return this.queryZgwcsjEnd;
	}

	public void setQueryZgwcsjEnd(Date queryZgwcsjEnd){
		this.queryZgwcsjEnd = queryZgwcsjEnd;
	}

	public Date getQueryYssjStart(){
		return this.queryYssjStart;
	}

	public void setQueryYssjStart(Date queryYssjStart){
		this.queryYssjStart = queryYssjStart;
	}

	public Date getQueryYssjEnd(){
		return this.queryYssjEnd;
	}

	public void setQueryYssjEnd(Date queryYssjEnd){
		this.queryYssjEnd = queryYssjEnd;
	}

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
	}

	public List<PhotoPic> getPicList4() {
		return picList4;
	}

	public void setPicList4(List<PhotoPic> picList4) {
		this.picList4 = picList4;
	}

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
	}

	public List<PhotoPic> getPicList6() {
		return picList6;
	}

	public void setPicList6(List<PhotoPic> picList6) {
		this.picList6 = picList6;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}
}
