/**
 * Class Name:ZycsjcryAction
 * Class Description：作业场所接触人员
 * Writer：陆婷
 * CreateTime：2013-11-26
 */
package com.jshx.zycsjcry.web;

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
import java.util.Calendar;
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

import com.jshx.company.entity.Company;
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
import com.jshx.zycsjcry.entity.Hyfl;
import com.jshx.zycsjcry.entity.Qylb;
import com.jshx.zycsjcry.entity.Whys;
import com.jshx.zycsjcry.entity.Xzqy;
import com.jshx.zycsjcry.entity.Zclx;
import com.jshx.zycsjcry.entity.Zycsjcry;
import com.jshx.zycsjcry.service.ZycsjcryService;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.service.ZycsqkService;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.service.ZywsjbxxService;

public class ZycsjcryAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zycsjcry zycsjcry = new Zycsjcry();
	
	private Zycsqk zycsqk = new Zycsqk();
	
	private Whys whys = new Whys();
	
	private Qylb qylb = new Qylb();
	
	private Xzqy xzqy = new Xzqy();

	/**
	 * 业务类
	 */
	@Autowired
	private ZycsjcryService zycsjcryService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZywsjbxxService zywsjbxxService;
	@Autowired
	private ZycsqkService zycsqkService;
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
	
	private String querySgsjStart;
	private String querySgsjEnd;
	
	private String queryTjrqStart;
	private String queryTjrqEnd;
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String picName;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private List<Hyfl> hyflList = new ArrayList<Hyfl>();
	private List<Zclx> zclxList = new ArrayList<Zclx>();
	private List<Xzqy> xzqyList = new ArrayList<Xzqy>();
	private List<Whys> whysList = new ArrayList<Whys>();
	private List<Qylb> qylbList = new ArrayList<Qylb>();
	/**
	 * 初始化作业场所接触人员列表
	 * author：陆婷
	 * 2013-11-26
	 */
	public String init()
	{
	
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化企业职业健康监护情况列表
	 * author：陆婷
	 * 2013-11-21
	 */
	public String inits()
	{
		String qyid = "";
		if(zycsjcry != null && zycsjcry.getQyid() != null)
		{
			qyid = zycsjcry.getQyid();
		}
		else
		{
			Company company = companyService.getById(this.getLoginUser().getLoginId());
			if(null != company){
				qyid = company.getId();
			}
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
		}
		Map map = new HashMap();
		map.put("taskProId",qyid);
		map.put("picType","zyjkjh");
	    picList = szwxPhotoService.findPicPath(map);//获取岗位图片
		return SUCCESS;
	}
	
	/**
	 * 初始化企业职业健康监护情况列表
	 * author：陆婷
	 * 2013-11-21
	 */
	public String initss()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 * 企业
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zycsjcry){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != zycsjcry.getXm()) && (0 < zycsjcry.getXm().trim().length())){
				paraMap.put("xm", "%" + zycsjcry.getXm().trim() + "%");
			}
			if ((null != zycsjcry.getSfz()) && (0 < zycsjcry.getSfz().trim().length())){
				paraMap.put("sfz", "%" + zycsjcry.getSfz().trim() + "%");
			}
			if ((null != zycsjcry.getXb()) && (0 < zycsjcry.getXb().trim().length())){
				paraMap.put("xb", zycsjcry.getXb().trim());
			}
			if ((null != zycsjcry.getZycsid()) && (0 < zycsjcry.getZycsid().trim().length())){
				paraMap.put("zycsid", zycsjcry.getZycsid().trim());
			}
			
			if ((null != zycsjcry.getTjlx()) && (0 < zycsjcry.getTjlx().trim().length())){
				paraMap.put("tjlx", zycsjcry.getTjlx().trim());
			}
			if ((null != zycsjcry.getTjjguo()) && (0 < zycsjcry.getTjjguo().trim().length())){
				paraMap.put("tjjguo", zycsjcry.getTjjguo().trim());
			}
			if ((null != zycsjcry.getQyid()) && (0 < zycsjcry.getQyid().trim().length())){
				paraMap.put("qyid", zycsjcry.getQyid().trim());
			}
			if (null != zycsjcry.getDelFlag()){
				paraMap.put("delFlag", zycsjcry.getDelFlag());
			}
			if (null != zycsjcry.getDelFlags()){
				paraMap.put("delFlags", zycsjcry.getDelFlags());
			}
			if ((null != zycsjcry.getSzc()) && (0 < zycsjcry.getSzc().trim().length())){
				paraMap.put("szc",  zycsjcry.getSzc().trim() );
			}
		}
		if(querySgsjStart != null)
		{
			paraMap.put("querySgsjStart",querySgsjStart);
		}
		if(querySgsjEnd != null)
		{
			paraMap.put("querySgsjEnd",querySgsjEnd);
		}
		if(queryTjrqStart != null)
		{
			paraMap.put("queryTjrqStart",queryTjrqStart);
		}
		if(queryTjrqEnd != null)
		{
			paraMap.put("queryTjrqEnd",queryTjrqEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		pagination = zycsjcryService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	
	/**
	 * 执行查询的方法，返回json数据
	 * 安监部门
	 */
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zycsjcry){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zycsjcry.getQymc()) && (0 < zycsjcry.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zycsjcry.getQymc().trim() + "%");
			}

			if ((null != zycsjcry.getSzzid()) && (0 < zycsjcry.getSzzid().trim().length())){
				paraMap.put("szzid",  zycsjcry.getSzzid().trim() );
			}
			if ((null != zycsjcry.getSzc()) && (0 < zycsjcry.getSzc().trim().length())){
				paraMap.put("szc",  zycsjcry.getSzc().trim() );
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
		List<Zycsjcry> zycsjcryList = new ArrayList<Zycsjcry>();
		zycsjcryList = zycsjcryService.getZycsjcryListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = zycsjcryService.getZycsjcryListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(zycsjcryList);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zycsjcry)&&(null != zycsjcry.getId()))
			zycsjcry = zycsjcryService.getById(zycsjcry.getId());
		
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
	 * 初始化修改信息
	 */
	public String initEdits() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zycsjcry.setSzzid(dept.getDeptCode());
			zycsjcry.setSzzname(dept.getDeptName());
			zycsjcry.setQyid(company.getId());
			zycsjcry.setQymc(company.getCompanyname());
			zycsjcry.setCreateUserID(this.getLoginUserId());
			zycsjcry.setCreateTime(new Date());
			zycsjcry.setQylx(company.getQylx());
			zycsjcry.setHyfl(company.getHyfl());
			zycsjcry.setQyzclx(company.getQyzclx());
			zycsjcry.setSzc(company.getSzc());
			zycsjcry.setSzcname(company.getSzcname());
			zycsjcry.setDeptId(this.getLoginUserDepartmentId());
			zycsjcry.setDelFlag(0);
			zycsjcry.setDelFlags(0);
			zycsjcryService.save(zycsjcry);
		}else{
			zycsjcryService.update(zycsjcry);
		}
		Zywsjbxx zywsjbxx = zywsjbxxService.getByCompanyId(company.getId());
		if(zywsjbxx !=null)
		{
			Map map = new HashMap();
			map.put("qyid", company.getId());
			String total = zycsjcryService.getTotalCountByMap(map);
			zywsjbxx.setJcrs(total);
			map.put("xb", "0");
			String ngtotal = zycsjcryService.getTotalCountByMap(map);
			zywsjbxx.setJcngrs(ngtotal);
			zywsjbxxService.update(zywsjbxx);
		}
		Zycsqk zycsqk = zycsqkService.getById(zycsjcry.getZycsid());
		if(zycsqk != null)
		{
			Map map = new HashMap();
			map.put("qyid", company.getId());
			map.put("zycsid", zycsqk.getId());
			String total = zycsjcryService.getTotalCountByMap(map);
			zycsqk.setJcrs(total);
			zycsqkService.update(zycsqk);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zycsjcryService.deleteWithFlag(ids);
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Zywsjbxx zywsjbxx = zywsjbxxService.getByCompanyId(company.getId());
			if(zywsjbxx !=null)
			{
				Map map = new HashMap();
				map.put("qyid", company.getId());
				String total = zycsjcryService.getTotalCountByMap(map);
				zywsjbxx.setJcrs(total);
				map.put("xb", "0");
				String ngtotal = zycsjcryService.getTotalCountByMap(map);
				zywsjbxx.setJcngrs(ngtotal);
				zywsjbxxService.update(zywsjbxx);
			}
			Zycsqk zycsqk = zycsqkService.getById(zycsjcry.getZycsid());
			if(zycsqk != null)
			{
				Map map = new HashMap();
				map.put("qyid", company.getId());
				map.put("zycsid", zycsqk.getId());
				String total = zycsjcryService.getTotalCountByMap(map);
				zycsqk.setJcrs(total);
				zycsqkService.update(zycsqk);
			}
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
			zycsjcryService.deleteWithFlags(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 跳转至作业场所接触人员附件上传页面
	 * author：陆婷
	 * 2013-11-26
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 保存作业场所接触人员附件信息
	 * author：陆婷
	 * 2013-11-26
	 */
	public void savePhoto(){
		try {
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
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
			destFName.append(root).append("file/zyjkjh/");
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
			taskPic.setPicName("zyjkjh/" + imgName);
			taskPic.setPicType("zyjkjh");
			taskPic.setTaskProId(company.getId());
			taskPic.setDelFlag(0);
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", "zyjkjh/" + imgName);
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
	/**
	 * 按行业分类
	 * 2013-11-29
	 * 陆婷
	 */
	public String hyfllist() throws Exception{
		hyflList = zycsjcryService.getHyflListByMap(null);
		return SUCCESS;
	}
	
	/**
	 * 按注册类型
	 * 2013-11-29
	 * 陆婷
	 */
	public String zclxlist() throws Exception{
		zclxList = zycsjcryService.getZclxListByMap(null);
		return SUCCESS;
	}
	
	/**
	 * 按行政区域
	 * 2013-11-29
	 * 陆婷
	 */
	public String xzqylist() throws Exception{
		xzqyList = zycsjcryService.getXzqyListByMap(null);
		xzqy = zycsjcryService.getXzqyByMap(null);
		return SUCCESS;
	}
	
	/**
	 * 按危害因素
	 * 2013-11-29
	 * 陆婷
	 */
	public String whyslist() throws Exception{
		whysList = zycsjcryService.getWhysListByMap(null);
		whys = zycsjcryService.getWhysByMap(null);
		return SUCCESS;
	}
	
	/**
	 * 按企业列表
	 * 2013-11-29
	 * 陆婷
	 */
	public String qylblist() throws Exception{
		Map map = new HashMap();
		if(null != qylb){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != qylb.getQymc()) && (0 < qylb.getQymc().trim().length())){
				map.put("qymc", "%" + qylb.getQymc().trim() + "%");
			}
		}
		qylbList = zycsjcryService.getQylbListByMap(map);
		return SUCCESS;
	}
	
	
	/**
	 * 按行业分类导出
	 * 2013-12-3
	 * 陆婷
	 */
	public void hyfllistExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=hyfl.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按行业分类统计");
		    sheet.setColumnWidth(0, 8000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 4000);
	        sheet.setColumnWidth(7, 4000);
	        sheet.setColumnWidth(8, 4000);
	        sheet.setColumnWidth(9, 4000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按行业分类统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9)); 
	        
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
	        ccl0.setCellValue("行业分类");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业总数");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1)); 
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("劳动者总人数");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2)); 
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("职业病累计人数");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 2, (short) 3)); 
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("接触职业病危害人数");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 1, (short) 9)); 
	        
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl5 = r4.createCell(4);
	        ccl5.setCellValue("总人数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r4.createCell(5);
	        ccl6.setCellValue("粉尘类");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r4.createCell(6);
	        ccl7.setCellValue("化学性");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r4.createCell(7);
	        ccl8.setCellValue("物理性");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r4.createCell(8);
	        ccl9.setCellValue("放射性");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(9);
	        ccl10.setCellValue("其他类");
	        ccl10.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        hyflList = zycsjcryService.getHyflListByMap(null);
			
			int num = 3;
			for(Hyfl tjyhbean:hyflList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getHylx());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getQyzs());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getLdzzs());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getZybrs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getJcrs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getFcrs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getHxrs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getWlrs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(tjyhbean.getFsrs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(tjyhbean.getQtrs());
		        ce9.setCellStyle(c);
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
	 * 按注册类型导出
	 * 2013-12-3
	 * 陆婷
	 */
	public void zclxlistExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zclx.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按注册类型统计");
		    sheet.setColumnWidth(0, 8000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 4000);
	        sheet.setColumnWidth(7, 4000);
	        sheet.setColumnWidth(8, 4000);
	        sheet.setColumnWidth(9, 4000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按注册类型统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9)); 
	        
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
	        ccl0.setCellValue("注册类型");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业总数");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1)); 
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("劳动者总人数");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2)); 
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("职业病累计人数");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 2, (short) 3)); 
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("接触职业病危害人数");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 1, (short) 9)); 
	        
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl5 = r4.createCell(4);
	        ccl5.setCellValue("总人数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r4.createCell(5);
	        ccl6.setCellValue("粉尘类");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r4.createCell(6);
	        ccl7.setCellValue("化学性");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r4.createCell(7);
	        ccl8.setCellValue("物理性");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r4.createCell(8);
	        ccl9.setCellValue("放射性");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(9);
	        ccl10.setCellValue("其他类");
	        ccl10.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        zclxList = zycsjcryService.getZclxListByMap(null);
			
			int num = 3;
			for(Zclx tjyhbean:zclxList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getZclx());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getQyzs());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getLdzzs());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getZybrs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getJcrs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getFcrs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getHxrs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getWlrs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(tjyhbean.getFsrs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(tjyhbean.getQtrs());
		        ce9.setCellStyle(c);
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
	 * 按行政区域导出
	 * 2013-12-3
	 * 陆婷
	 */
	public void xzqylistExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=xzqy.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按行政区域统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 4000);
	        sheet.setColumnWidth(7, 4000);
	        sheet.setColumnWidth(8, 4000);
	        sheet.setColumnWidth(9, 4000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按行政区域统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9)); 
	        
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
	        ccl0.setCellValue("区域");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业总数");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1)); 
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("劳动者总人数");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2)); 
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("职业病累计人数");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 2, (short) 3)); 
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("接触职业病危害人数");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 1, (short) 9)); 
	        
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl5 = r4.createCell(4);
	        ccl5.setCellValue("总人数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r4.createCell(5);
	        ccl6.setCellValue("粉尘类");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r4.createCell(6);
	        ccl7.setCellValue("化学性");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r4.createCell(7);
	        ccl8.setCellValue("物理性");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r4.createCell(8);
	        ccl9.setCellValue("放射性");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(9);
	        ccl10.setCellValue("其他类");
	        ccl10.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        xzqyList = zycsjcryService.getXzqyListByMap(null);
			xzqy = zycsjcryService.getXzqyByMap(null);
			
			HSSFRow ro = sheet.createRow(3);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(xzqy.getQyzs());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(xzqy.getLdzzs());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(xzqy.getZybrs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(xzqy.getJcrs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(xzqy.getFcrs());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(xzqy.getHxrs());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(xzqy.getWlrs());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(xzqy.getFsrs());
	        ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(xzqy.getQtrs());
	        ce9.setCellStyle(c);
	        
			int num = 4;
			for(Xzqy tjyhbean:xzqyList)
			{
				HSSFRow rows = sheet.createRow(num);
				HSSFCell ce00 = rows.createCell(0);
				ce00.setCellValue(tjyhbean.getQy());
				ce00.setCellStyle(c);
		        HSSFCell ce01 = rows.createCell(1);
		        ce01.setCellValue(tjyhbean.getQyzs());
		        ce01.setCellStyle(c);
		        HSSFCell ce02 = rows.createCell(2);
		        ce02.setCellValue(tjyhbean.getLdzzs());
		        ce02.setCellStyle(c);
		        HSSFCell ce03 = rows.createCell(3);
		        ce03.setCellValue(tjyhbean.getZybrs());
		        ce03.setCellStyle(c);
		        HSSFCell ce04 = rows.createCell(4);
		        ce04.setCellValue(tjyhbean.getJcrs());
		        ce04.setCellStyle(c);
		        HSSFCell ce05 = rows.createCell(5);
		        ce05.setCellValue(tjyhbean.getFcrs());
		        ce05.setCellStyle(c);
		        HSSFCell ce06 = rows.createCell(6);
		        ce06.setCellValue(tjyhbean.getHxrs());
		        ce06.setCellStyle(c);
		        HSSFCell ce07 = rows.createCell(7);
		        ce07.setCellValue(tjyhbean.getWlrs());
		        ce07.setCellStyle(c);
		        HSSFCell ce08 = rows.createCell(8);
		        ce08.setCellValue(tjyhbean.getFsrs());
		        ce08.setCellStyle(c);
		        HSSFCell ce09 = rows.createCell(9);
		        ce09.setCellValue(tjyhbean.getQtrs());
		        ce09.setCellStyle(c);
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
	 * 按危害因素导出
	 * 2013-12-3
	 * 陆婷
	 */
	public void whyslistExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=whys.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按危害因素统计");
		    sheet.setColumnWidth(0, 25000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 8000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按危害因素统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 2)); 
	        
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
	        ccl0.setCellValue("危害因素名称");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("涉及的企业数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("接触危害的人数");
	        ccl2.setCellStyle(cs);

	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        whysList = zycsjcryService.getWhysListByMap(null);
			whys = zycsjcryService.getWhysByMap(null);
			
			HSSFRow ro = sheet.createRow(2);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(whys.getQyzs());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(whys.getJcrs());
	        ce2.setCellStyle(c);
	        
			int num = 3;
			for(Whys tjyhbean:whysList)
			{
				HSSFRow rows = sheet.createRow(num);
				HSSFCell ce00 = rows.createCell(0);
				ce00.setCellValue(tjyhbean.getWhysmc());
				ce00.setCellStyle(c);
		        HSSFCell ce01 = rows.createCell(1);
		        ce01.setCellValue(tjyhbean.getQyzs());
		        ce01.setCellStyle(c);
		        HSSFCell ce02 = rows.createCell(2);
		        ce02.setCellValue(tjyhbean.getJcrs());
		        ce02.setCellStyle(c);
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
	 * 按企业列表导出
	 * 2013-12-3
	 * 陆婷
	 */
	public void qylblistExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=qylb.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("按企业列表统计");
		    sheet.setColumnWidth(0, 12000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 4000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 4000);
	        sheet.setColumnWidth(7, 4000);
	        sheet.setColumnWidth(8, 4000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("按企业列表统计");
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
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("劳动者总人数");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1)); 
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("职业病累计人数");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2)); 
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("接触职业病危害人数");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 1, (short) 8)); 
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl5 = r4.createCell(3);
	        ccl5.setCellValue("总人数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r4.createCell(4);
	        ccl6.setCellValue("粉尘类");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r4.createCell(5);
	        ccl7.setCellValue("化学性");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r4.createCell(6);
	        ccl8.setCellValue("物理性");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r4.createCell(7);
	        ccl9.setCellValue("放射性");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(8);
	        ccl10.setCellValue("其他类");
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
	        	String qymc = (String) getSessionAttribute("qymc");
	        	map.put("qymc", qymc);
			}
			if(null != qylb){
			    //设置查询条件，开发人员可以在此增加过滤条件

				if ((null != qylb.getQymc()) && (0 < qylb.getQymc().trim().length())){
					map.put("qymc", "%" + qylb.getQymc().trim() + "%");
					setSessionAttribute("qymc", "%" + qylb.getQymc().trim() + "%");
				}
			}
			qylbList = zycsjcryService.getQylbListByMap(map);
			
			int num = 3;
			for(Qylb tjyhbean:qylbList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getLdzzs());
		        ce1.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(2);
		        ce3.setCellValue(tjyhbean.getZybrs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(3);
		        ce4.setCellValue(tjyhbean.getJcrs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(4);
		        ce5.setCellValue(tjyhbean.getFcrs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(5);
		        ce6.setCellValue(tjyhbean.getHxrs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(6);
		        ce7.setCellValue(tjyhbean.getWlrs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(7);
		        ce8.setCellValue(tjyhbean.getFsrs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(8);
		        ce9.setCellValue(tjyhbean.getQtrs());
		        ce9.setCellStyle(c);
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

	public Zycsjcry getZycsjcry(){
		return this.zycsjcry;
	}

	public void setZycsjcry(Zycsjcry zycsjcry){
		this.zycsjcry = zycsjcry;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public Zycsqk getZycsqk() {
		return zycsqk;
	}
	public void setZycsqk(Zycsqk zycsqk) {
		this.zycsqk = zycsqk;
	}
	public String getQuerySgsjStart() {
		return querySgsjStart;
	}
	public void setQuerySgsjStart(String querySgsjStart) {
		this.querySgsjStart = querySgsjStart;
	}
	public String getQuerySgsjEnd() {
		return querySgsjEnd;
	}
	public void setQuerySgsjEnd(String querySgsjEnd) {
		this.querySgsjEnd = querySgsjEnd;
	}
	public String getQueryTjrqStart() {
		return queryTjrqStart;
	}
	public void setQueryTjrqStart(String queryTjrqStart) {
		this.queryTjrqStart = queryTjrqStart;
	}
	public String getQueryTjrqEnd() {
		return queryTjrqEnd;
	}
	public void setQueryTjrqEnd(String queryTjrqEnd) {
		this.queryTjrqEnd = queryTjrqEnd;
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

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public List<Hyfl> getHyflList() {
		return hyflList;
	}

	public void setHyflList(List<Hyfl> hyflList) {
		this.hyflList = hyflList;
	}

	public List<Zclx> getZclxList() {
		return zclxList;
	}

	public void setZclxList(List<Zclx> zclxList) {
		this.zclxList = zclxList;
	}

	public List<Xzqy> getXzqyList() {
		return xzqyList;
	}

	public void setXzqyList(List<Xzqy> xzqyList) {
		this.xzqyList = xzqyList;
	}

	public List<Whys> getWhysList() {
		return whysList;
	}

	public void setWhysList(List<Whys> whysList) {
		this.whysList = whysList;
	}

	public Whys getWhys() {
		return whys;
	}

	public void setWhys(Whys whys) {
		this.whys = whys;
	}

	public Qylb getQylb() {
		return qylb;
	}

	public void setQylb(Qylb qylb) {
		this.qylb = qylb;
	}

	public Xzqy getXzqy() {
		return xzqy;
	}

	public void setXzqy(Xzqy xzqy) {
		this.xzqy = xzqy;
	}

	public List<Qylb> getQylbList() {
		return qylbList;
	}

	public void setQylbList(List<Qylb> qylbList) {
		this.qylbList = qylbList;
	}
}
