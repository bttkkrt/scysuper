/**
 * Class Name: ZhkxzxkAction
 * Class Description：烟花爆竹经营申报材料审查管理
 */
package com.jshx.zhkxzxk.web;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.whpclsc.service.WhpClscService;
import com.jshx.zhkxzxk.entity.Zhkxzxk;
import com.jshx.zhkxzxk.service.ZhkxzxkService;

public class ZhkxzxkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zhkxzxk zhkxzxk = new Zhkxzxk();

	/**
	 * 业务类
	 */
	@Autowired
	private ZhkxzxkService zhkxzxkService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private WhpClscService whpClscService;
	@Autowired
	private UserService userService;
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private String picName;
	private String type;
	private String createTimeStart;

	private String createTimeEnd;
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	private String[] shenheList ;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 当前登录人id
	 * @return
	 */
	private String createUserID;
	
	private List<User> userList = new ArrayList<User>();
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * 初始化安全附件情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public String init()
	{
		createUserID = this.getLoginUserId();
		flag = "0";
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("yhbzCode")))//烟火爆竹职责范围
		{
			List<UserRight> list = this.getLoginUser().getUserRoles();
			for(UserRight u:list)
			{
				String rolecode = u.getRole().getRoleCode();
				if(rolecode.equals("A12"))
				{
					flag = "1";
					break;
				}
			}
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
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
		    
		if(null != zhkxzxk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zhkxzxk.getSzzid()) && (0 < zhkxzxk.getSzzid().trim().length())){
				paraMap.put("szzid", zhkxzxk.getSzzid().trim() );
			}

			if ((null != zhkxzxk.getQymc()) && (0 < zhkxzxk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zhkxzxk.getQymc().trim() + "%");
			}
			if ((null != zhkxzxk.getState()) && (0 < zhkxzxk.getState().trim().length())){
				if(!"3".equals(zhkxzxk.getState()))
				{
					paraMap.put("state", zhkxzxk.getState().trim());
				}
			}
			if ((null != zhkxzxk.getSzc()) && (0 < zhkxzxk.getSzc().trim().length())){
				paraMap.put("szc", zhkxzxk.getSzc().trim() );
			}
		}
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid", deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		pagination = zhkxzxkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zhkxzxk)&&(null != zhkxzxk.getId()))
		{
			zhkxzxk = zhkxzxkService.getById(zhkxzxk.getId());
			if(zhkxzxk.getLinkid() == null || "".equals(zhkxzxk.getLinkid()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				zhkxzxk.setLinkid(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",zhkxzxk.getLinkid());
				map.put("picType","zhkxzxk1");
				picList1 = szwxPhotoService.findPicPath(map);//获取检查文书
				map.put("picType","zhkxzxk2");
				picList2 = szwxPhotoService.findPicPath(map);//获取复查文书
				map.put("picType","zhkxzxk3");
				picList3 = szwxPhotoService.findPicPath(map);//获取整改方案	
				map.put("picType","zhkxzxk4");
				picList4 = szwxPhotoService.findPicPath(map);//获取整改方案	
				if(zhkxzxk.getShenhe() != null && !"".equals(zhkxzxk.getShenhe()))
				{
					shenheList = zhkxzxk.getShenhe().split("#");
				}
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			zhkxzxk.setLinkid(linkId);
		}
		Map m = new HashMap();
		List<String> list = new ArrayList<String>();
	    list.add("A19");
	    list.add("A17");
	    list.add("A11");
	    m.put("roleCode", list);
	    userList = whpClscService.getUserListByMap(m);
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
		if(zhkxzxk != null && zhkxzxk.getQyid() != null && !"".equals(zhkxzxk.getQyid()))
		{
			Map map = new HashMap();
			map.put("companyId", zhkxzxk.getQyid());
			CompanyBackUp company = companyService.getCompanyBackupById(map);
			if(!zhkxzxk.getQymc().equals(company.getCompanyname()))
			{
				zhkxzxk.setQyid(null);
				Department dept = deptService.findDeptByDeptCode(zhkxzxk.getSzzid());
				zhkxzxk.setSzzid(dept.getDeptCode());
				zhkxzxk.setSzzname(dept.getDeptName());
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				zhkxzxk.setSzzid(dept.getDeptCode());
				zhkxzxk.setSzzname(dept.getDeptName());
				zhkxzxk.setQyid(company.getId());
				zhkxzxk.setQymc(company.getCompanyname());
				zhkxzxk.setQylx(company.getQylx());
				zhkxzxk.setHyfl(company.getHyfl());
				zhkxzxk.setQygm(company.getQygm());
				zhkxzxk.setQyzclx(company.getQyzclx());
				zhkxzxk.setSzc(company.getSzc());
				zhkxzxk.setSzcname(company.getSzcname());
			}
		}
		else if(zhkxzxk.getSzzid() != null && !"".equals(zhkxzxk.getSzzid()))
		{
			Department dept = deptService.findDeptByDeptCode(zhkxzxk.getSzzid());
			zhkxzxk.setSzzid(dept.getDeptCode());
			zhkxzxk.setSzzname(dept.getDeptName());
		}
		zhkxzxk.setState("1");
		User user = userService.findUserById(zhkxzxk.getShuserid());
		zhkxzxk.setShusername(user.getDisplayName());
		if ("add".equalsIgnoreCase(this.flag)){
			zhkxzxk.setDeptId(this.getLoginUserDepartmentId());
			zhkxzxk.setDelFlag(0);
			zhkxzxk.setCreateUserID(this.getLoginUserId());
			zhkxzxk.setCreateTime(new Date());
			zhkxzxkService.save(zhkxzxk);
		}else{
			zhkxzxkService.update(zhkxzxk);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zhkxzxkService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 初始化审核页面
	 * 陆婷
	 * 2013-12-11
	 * @return
	 * @throws Exception
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
	}
	/**
	 * 保存审核结果
	 * 陆婷
	 * 2013-12-12
	 * @return
	 */
	public String shenhesave()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String state = zhkxzxk.getState();
		String remark = zhkxzxk.getRemark();
		String a = "";
		if((null != zhkxzxk)&&(null != zhkxzxk.getId()))
		{
			Zhkxzxk g = zhkxzxkService.getById(zhkxzxk.getId());
			if("2".equals(state))
			{
				a = "审核不通过";
				g.setState(state);
				g.setRemark(remark);
			}
			else if("3".equals(state))
			{
				a = "审核通过";
				String isfinish = zhkxzxk.getIsfinish();
				if("0".equals(isfinish))
				{
					g.setState("1");
					String shuserid = zhkxzxk.getShuserid();
					User user = userService.findUserById(shuserid);
					g.setShuserid(shuserid);
					g.setShusername(user.getDisplayName());
				}
				else
				{
					g.setState(state);
					g.setRemark(remark);
				}
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
			zhkxzxkService.update(g);
		}
		return RELOAD;
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
			destFName.append(root).append("file/zhkxzxk/");
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
			taskPic.setPicName("zhkxzxk/"+imgName);
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
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zhkxzxk.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("相城区安监局综合科行政许可会审记录");
			sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 10000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 10000);
	        sheet.setColumnWidth(10, 10000);
	        sheet.setColumnWidth(11, 10000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("相城区安监局综合科行政许可会审记录");
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
	        ccl0.setCellValue("企业名称");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("所在镇");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("申请材料是否齐全");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("乡镇预审意见");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("签字领导");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("现场核查部门");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("核查结论");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("本次领证情况");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("是否涉及存储");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("材料审查情况");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("行政许可建议");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(11);
	        ccl11.setCellValue("局会审记录");
	        ccl11.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        
	        Map<String, Object> paraMap = new HashMap<String, Object>();
	        
	        if(flag == null || "".equals(flag))
    		{
	        	zhkxzxk = (Zhkxzxk) getSessionAttribute("zhkxzxk");
	        	createTimeStart = (String) getSessionAttribute("createTimeStart");
	        	createTimeEnd = (String) getSessionAttribute("createTimeEnd");
    		}
	        if(null != zhkxzxk){
	        	setSessionAttribute("zhkxzxk", zhkxzxk);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != zhkxzxk.getSzzid()) && (0 < zhkxzxk.getSzzid().trim().length())){
					paraMap.put("szzid", zhkxzxk.getSzzid().trim() );
				}

				if ((null != zhkxzxk.getQymc()) && (0 < zhkxzxk.getQymc().trim().length())){
					paraMap.put("qymc", "%" + zhkxzxk.getQymc().trim() + "%");
				}
				if ((null != zhkxzxk.getState()) && (0 < zhkxzxk.getState().trim().length())){
					if(!"3".equals(zhkxzxk.getState()))
					{
						paraMap.put("state", zhkxzxk.getState().trim());
					}
				}
				if ((null != zhkxzxk.getSzc()) && (0 < zhkxzxk.getSzc().trim().length())){
					paraMap.put("szc", zhkxzxk.getSzc().trim() );
				}
			}
			if (null != createTimeStart){
				setSessionAttribute("createTimeStart", createTimeStart);
				paraMap.put("startCreateTime", createTimeStart);
			}

			if (null != createTimeEnd){
				setSessionAttribute("createTimeEnd", createTimeEnd);
				paraMap.put("endCreateTime", createTimeEnd);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
			{
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", company.getId());
			}
			else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
			{
				if(deptCode.length() == 12)
				{
					paraMap.put("szzid", deptCode);
				}
				else
				{
					paraMap.put("szc", deptCode);
				}
			}
			List<Zhkxzxk> list = zhkxzxkService.findZhkxzxk(paraMap);
			int num = 2;
			for(Zhkxzxk zhkxzxk:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zhkxzxk.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zhkxzxk.getSzzname());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue((zhkxzxk.getIsCaiLiao() != null && zhkxzxk.getIsCaiLiao().equals("1"))?"是":"否");
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(zhkxzxk.getXzysyj());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zhkxzxk.getQzld());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(zhkxzxk.getXchcbm());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(zhkxzxk.getHcjl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(zhkxzxk.getBclzqk());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue((zhkxzxk.getIsCunChu() != null && zhkxzxk.getIsCunChu().equals("1"))?"是":"否");
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(zhkxzxk.getClscqk());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(zhkxzxk.getXzxkjy());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(zhkxzxk.getJhsjl());
		        ce11.setCellStyle(c);
		        num++;
			}
	        
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (Exception e) {
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

	public Zhkxzxk getZhkxzxk(){
		return this.zhkxzxk;
	}

	public void setZhkxzxk(Zhkxzxk zhkxzxk){
		this.zhkxzxk = zhkxzxk;
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

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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

	public String[] getShenheList() {
		return shenheList;
	}

	public void setShenheList(String[] shenheList) {
		this.shenheList = shenheList;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
       
    
}
