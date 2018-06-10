package com.jshx.sgyhjb.web;

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
import com.jshx.http.bean.DataBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.sgyhjb.entity.Sgyhjb;
import com.jshx.sgyhjb.service.SgyhjbService;

public class SgyhjbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Sgyhjb sgyhjb = new Sgyhjb();

	/**
	 * 业务类
	 */
	@Autowired
	private SgyhjbService sgyhjbService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private HttpService httpService;
	

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String type;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String deptCode;
	
	private List<Department> deptlist1 = new ArrayList<Department>(); //交办部门
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String picName;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	public String init()
	{
		//根据用户部门职责判断权限
		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && deptRole.contains("h"))
		{
			flag = "1";
		}
		else if(null != deptRole && (deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("f") || deptRole.contains("g")))
		{
			flag = "2";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "3";
		}
		else
		{
			flag = "4";
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
		    
		if(null != sgyhjb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != sgyhjb.getJbr()) && (0 < sgyhjb.getJbr().trim().length())){
				paraMap.put("jbr", "%" + sgyhjb.getJbr().trim() + "%");
			}
			if ((null != sgyhjb.getJbrdh()) && (0 < sgyhjb.getJbrdh().trim().length())){
				paraMap.put("jbrdh", "%" + sgyhjb.getJbrdh().trim() + "%");
			}
			if ((null != sgyhjb.getYhjb()) && (0 < sgyhjb.getYhjb().trim().length())){
				paraMap.put("yhjb", sgyhjb.getYhjb().trim());
			}

			if ((null != sgyhjb.getJbszz()) && (0 < sgyhjb.getJbszz().trim().length())){
				paraMap.put("jbszz", sgyhjb.getJbszz().trim());
			}

			if ((null != sgyhjb.getJbqy()) && (0 < sgyhjb.getJbqy().trim().length())){
				paraMap.put("jbqy", "%" + sgyhjb.getJbqy().trim() + "%");
			}

		}
		//根据用户部门职责判断权限
		String deptRole = this.getLoginUser().getDeptRole();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			paraMap.put("deptCode", deptCode.substring(0,9));
		}
		else if(null != deptRole && (deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("f") || deptRole.contains("g")))
		{
			paraMap.put("deptCode", deptCode);
		}
		
		pagination = sgyhjbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		//根据用户部门职责判断权限
		String deptRole = this.getLoginUser().getDeptRole();
		if((null != sgyhjb)&&(null != sgyhjb.getId()))
		{
			sgyhjb = sgyhjbService.getById(sgyhjb.getId());
			if(sgyhjb.getLinkId() == null || "".equals(sgyhjb.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				sgyhjb.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",sgyhjb.getLinkId());
				map.put("picType","yhtp");
				picList1 = szwxPhotoService.findPicPath(map);//获取故障图片
				map.put("picType","zghzp");
				picList2 = szwxPhotoService.findPicPath(map);//获取整改图片
			}
		}
		else 
		{
			sgyhjb.setScjb("1");
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			sgyhjb.setLinkId(linkId);
		}
		deptlist1 = sgyhjbService.getJbbmListByMap(null);
		Department d = new Department();
		if(sgyhjb.getJbbm()!= null && sgyhjb.getJbbm().startsWith("002006"))
		{
			d.setDeptCode(sgyhjb.getJbbm());
		}
		else
		{
			d.setDeptCode("002006");
		}
		d.setDeptName("乡镇");
		deptlist1.add(d);
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(null != deptRole && deptRole.contains("h"))
		{
			type = "1";
		}
		else if(null != deptRole && (deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("f") || deptRole.contains("g")))
		{
			type = "2";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			type = "3";
		}
		else
		{
			type = "4";
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ("add".equalsIgnoreCase(this.flag)){
			if(sgyhjb.getJbqyid() != null  && !"".equals(sgyhjb.getJbqyid()))
			{
				Map mm = new HashMap();
				mm.put("companyId", sgyhjb.getJbqyid());
				CompanyBackUp company=companyService.getCompanyBackupById(mm);
				if(!sgyhjb.getJbqy().equals(company.getCompanyname()))
				{
					sgyhjb.setJbqyid(null);
				}
			}
			sgyhjb.setDeptId(this.getLoginUserDepartmentId());
			sgyhjb.setDelFlag(0);
			sgyhjb.setState("1");
			if(sgyhjb.getJbbm().equals("002006"))
			{
				sgyhjb.setJbbm(sgyhjb.getJbszz());
			}
			Department d = deptService.findDeptByDeptCode(sgyhjb.getJbbm());
			sgyhjb.setJbbmname(d.getDeptName());
			sgyhjbService.save(sgyhjb);
		}else{
			if((sgyhjb.getState().equals("0") && flag.equals("0")) || (sgyhjb.getState().equals("1") && flag.equals("1")))
			{
				if(sgyhjb.getJbqyid() != null && !"".equals(sgyhjb.getJbqyid()))
				{
					Map mm = new HashMap();
					mm.put("companyId", sgyhjb.getJbqyid());
					CompanyBackUp	company=companyService.getCompanyBackupById(mm);
					if(!sgyhjb.getJbqy().equals(company.getCompanyname()))
					{
						sgyhjb.setJbqyid(null);
					}
				}
				if(flag.equals("0"))
				{
					sgyhjb.setState("1");
				}
				if(sgyhjb.getJbbm().equals("002006"))
				{
					sgyhjb.setJbbm(sgyhjb.getJbszz());
				}
				Department d = deptService.findDeptByDeptCode(sgyhjb.getJbbm());
				sgyhjb.setJbbmname(d.getDeptName());
				sgyhjbService.update(sgyhjb);
			}
			else if((sgyhjb.getState().equals("1") && flag.equals("0")) || (sgyhjb.getState().equals("2") && flag.equals("1")))
			{
				Sgyhjb s = sgyhjbService.getById(sgyhjb.getId());
				s.setJbbmclqk(sgyhjb.getJbbmclqk());
				s.setJbbmclsj(sgyhjb.getJbbmclsj());
				s.setJbbmclfh(sgyhjb.getJbbmclfh());
				if(flag.equals("0"))
				{
					s.setState("2");
				}
				if(s.getJbbm().startsWith("002006"))
				{
					s.setClbm(s.getJbbm());
					s.setClbmname(s.getJbbmname());
					s.setClbmjssj(sdf.format(new Date()));
				}
				else
				{
					if(sgyhjb.getJbbmclfh().equals("0"))//乡镇处理
					{
						Department d = deptService.findDeptByDeptCode(s.getJbszz());
						s.setClbm(s.getJbszz());
						s.setClbmname(d.getDeptName());
						s.setClbmjssj(sgyhjb.getClbmjssj());
					}
					else//自己处理
					{
						s.setClbm(s.getJbbm());
						s.setClbmname(s.getJbbmname());
						s.setClbmjssj(sgyhjb.getClbmjssj());
					}
				}
				sgyhjbService.update(s);
			}
			else if((sgyhjb.getState().equals("2") && flag.equals("0")) || (sgyhjb.getState().equals("3") && flag.equals("1")))
			{
				Sgyhjb s = sgyhjbService.getById(sgyhjb.getId());
				if(flag.equals("0"))
				{
					s.setState("3");
				}
				s.setCljg(sgyhjb.getCljg());
				s.setClr(sgyhjb.getClr());
				s.setClsj(sgyhjb.getClsj());
				s.setZgwcsj(sgyhjb.getZgwcsj());
				s.setJlje(sgyhjb.getJlje());
				s.setLinkId(sgyhjb.getLinkId());
				sgyhjbService.update(s);
			}
			else if((sgyhjb.getState().equals("3") && flag.equals("0")) || (sgyhjb.getState().equals("4") && flag.equals("1")))
			{
				Sgyhjb s = sgyhjbService.getById(sgyhjb.getId());
				if(flag.equals("0"))
				{
					s.setState("4");
				}
				s.setJbbmfzr(sgyhjb.getJbbmfzr());
				s.setJbbmyjsj(sgyhjb.getJbbmyjsj());
				s.setJbbmyj(sgyhjb.getJbbmyj());
				sgyhjbService.update(s);
			}
			else if((sgyhjb.getState().equals("4") && flag.equals("0")) || (sgyhjb.getState().equals("5") && flag.equals("1")))
			{
				Sgyhjb s = sgyhjbService.getById(sgyhjb.getId());
				if(flag.equals("0"))
				{
					s.setState("5");
				}
				s.setScjg(sgyhjb.getScjg());
				s.setDf(sgyhjb.getDf());
				sgyhjbService.update(s);
			}
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			sgyhjbService.deleteWithFlag(ids);
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
	
	public String uploadsave()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sgyhjb.setDelFlag(0);
		sgyhjb.setState("0");
		sgyhjb.setLdlb("2");
		sgyhjb.setSxsj(sdf.format(new Date()));
		sgyhjbService.save(sgyhjb);
		return RELOAD;
	}
	
	public String picupload() throws Exception{
		return SUCCESS;
	}
	
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
	
	public String deleteImage() throws Exception{
	    try{
	    	szwxPhotoService.deleteImageWithFlag(picName);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
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
	
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=sgyhjb.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("事故隐患举报汇总表");
			sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000); 
	        sheet.setColumnWidth(9, 5000); 
	        sheet.setColumnWidth(10, 5000);
	        sheet.setColumnWidth(11, 5000);
	        sheet.setColumnWidth(12, 5000);
	        sheet.setColumnWidth(13, 5000);
	        sheet.setColumnWidth(14, 5000);
	        sheet.setColumnWidth(15, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("事故隐患举报汇总表");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 15)); 
	        
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
	        ccl0.setCellValue("序号");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("原举报编号");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("举报所在镇");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("举报企业或其它");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl17 = r3.createCell(4);
	        ccl17.setCellValue("举报人");
	        ccl17.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(5);
	        ccl4.setCellValue("举报电话");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(6);
	        ccl5.setCellValue("收信时间");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(7);
	        ccl6.setCellValue("来电类别");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(8);
	        ccl7.setCellValue("隐患等级");
	        ccl7.setCellStyle(cs);
	        
	        
	        HSSFCell ccl8 = r3.createCell(9);
	        ccl8.setCellValue("来自何部门");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(10);
	        ccl9.setCellValue("交办时间");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(11);
	        ccl10.setCellValue("要求结办时间");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(12);
	        ccl11.setCellValue("处理部门");
	        ccl11.setCellStyle(cs);
	        HSSFCell ccl12 = r3.createCell(13);
	        ccl12.setCellValue("处理乡镇");
	        ccl12.setCellStyle(cs);
	        HSSFCell ccl13 = r3.createCell(14);
	        ccl13.setCellValue("处理时间");
	        ccl13.setCellStyle(cs);
	        HSSFCell ccl14 = r3.createCell(15);
	        ccl14.setCellValue("得分");
	        ccl14.setCellStyle(cs);
	        
	        
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
	        	sgyhjb = (Sgyhjb) getSessionAttribute("sgyhjb");
    		}
	        if(null != sgyhjb){
	        	setSessionAttribute("sgyhjb", sgyhjb);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != sgyhjb.getJbr()) && (0 < sgyhjb.getJbr().trim().length())){
					paraMap.put("jbr", "%" + sgyhjb.getJbr().trim() + "%");
				}
				if ((null != sgyhjb.getJbrdh()) && (0 < sgyhjb.getJbrdh().trim().length())){
					paraMap.put("jbrdh", "%" + sgyhjb.getJbrdh().trim() + "%");
				}
				if ((null != sgyhjb.getYhjb()) && (0 < sgyhjb.getYhjb().trim().length())){
					paraMap.put("yhjb", sgyhjb.getYhjb().trim());
				}

				if ((null != sgyhjb.getJbszz()) && (0 < sgyhjb.getJbszz().trim().length())){
					paraMap.put("jbszz", sgyhjb.getJbszz().trim());
				}

				if ((null != sgyhjb.getJbqy()) && (0 < sgyhjb.getJbqy().trim().length())){
					paraMap.put("jbqy", "%" + sgyhjb.getJbqy().trim() + "%");
				}

			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			//hanxc 20141210 修改时注意sql比较复杂，应特殊处理 start FIXME
			String deptRole = this.getLoginUser().getDeptRole();
			
			if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
			{
				paraMap.put("deptCode", deptCode.substring(0,9));
			}
			else if(null != deptRole && (deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("f") || deptRole.contains("g")))
			{
				paraMap.put("deptCode", deptCode);
			}
			List<Sgyhjb> list = sgyhjbService.findSgyhjb(paraMap);
			int num = 2;
			for(int i=0;i<list.size();i++)
			{
				Sgyhjb sgyhjb = list.get(i);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(i+1);
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(StringTools.NullToStr(sgyhjb.getJbbh(),""));
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        Map mm = new HashMap();
           		mm.put("szz", sgyhjb.getJbszz());
           		List<DataBean> ll = httpService.getTownList(mm);
           		String szzname = "";
           		if(ll.size() != 0)
           		{
           			szzname = ll.get(0).getRname();
           		}
		        ce2.setCellValue(szzname);
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(sgyhjb.getJbqy());
		        ce3.setCellStyle(c);
		        HSSFCell ce17 = ro.createCell(4);
		        ce17.setCellValue(StringTools.NullToStr(sgyhjb.getJbr(),""));
		        ce17.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(5);
		        ce4.setCellValue(StringTools.NullToStr(sgyhjb.getJbrdh(),""));
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(6);
		        ce5.setCellValue(StringTools.NullToStr(sgyhjb.getSxsj(),""));
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(7);
		        ce6.setCellValue(null!=sgyhjb.getLdlb()?(String)companyService.findCompanyTypeNameByKey(sgyhjb.getLdlb(),"4028e53a4912677d01491296ab11000e"):"");
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(8);
		        ce7.setCellValue(StringTools.NullToStr(sgyhjb.getYhjb(),""));
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(9);
				ce8.setCellValue(StringTools.NullToStr(sgyhjb.getLzbm(),""));
				ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(10);
		        ce9.setCellValue(StringTools.NullToStr(sgyhjb.getJbsj(),""));
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(11);
		        ce10.setCellValue(StringTools.NullToStr(sgyhjb.getYqbjsj(),""));
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(12);
		        ce11.setCellValue(StringTools.NullToStr(sgyhjb.getJbbmname(),""));
		        ce11.setCellStyle(c);
		        HSSFCell ce12 = ro.createCell(13);
		        ce12.setCellValue(StringTools.NullToStr(sgyhjb.getClbmname(),""));
		        ce12.setCellStyle(c);
		        HSSFCell ce13 = ro.createCell(14);
		        ce13.setCellValue(StringTools.NullToStr(sgyhjb.getClsj(),""));
		        ce13.setCellStyle(c);
		        HSSFCell ce14 = ro.createCell(15);
		        ce14.setCellValue(StringTools.NullToStr(sgyhjb.getDf(),""));
		        ce14.setCellStyle(c);
		        num++;
			}
	        
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (Exception e) {
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

	public Sgyhjb getSgyhjb(){
		return this.sgyhjb;
	}

	public void setSgyhjb(Sgyhjb sgyhjb){
		this.sgyhjb = sgyhjb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public List<Department> getDeptlist1() {
		return deptlist1;
	}

	public void setDeptlist1(List<Department> deptlist1) {
		this.deptlist1 = deptlist1;
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
       
    
}
