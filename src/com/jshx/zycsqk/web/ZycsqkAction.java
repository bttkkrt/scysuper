/**
 * Class Name:DangerousChemicalsPlanService
 * Class Description：作业场所情况
 * Writer：陆婷
 * CreateTime：2013-11-22
 */
package com.jshx.zycsqk.web;

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
import com.jshx.zycsjcry.service.ZycsjcryService;
import com.jshx.zycsqk.entity.Zybwhda;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.entity.Zywhys;
import com.jshx.zycsqk.service.ZycsqkService;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.service.ZywsjbxxService;

public class ZycsqkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zycsqk zycsqk = new Zycsqk();
	
	private Zybwhda  zybwhda = new Zybwhda();

	/**
	 * 业务类
	 */
	@Autowired
	private ZycsqkService zycsqkService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZywsjbxxService zywsjbxxService;
	@Autowired
	private ZycsjcryService zycsjcryService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String type;
	
	 /**
     * 图片
     */
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private List<Zywhys> zywhysList = new ArrayList<Zywhys>();
	
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
	 * Function Name: init
	 * Function Description：初始化作业场所情况列表
	 * Writer：陆婷
	 * CreateTime：2013-11-22
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
	 * Function Name: list
	 * Function Description：查询化作业场所情况列表
	 * Writer：陆婷
	 * CreateTime：2013-11-22
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zycsqk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zycsqk.getSzzid()) && (0 < zycsqk.getSzzid().trim().length())){
				paraMap.put("szzid", "%" + zycsqk.getSzzid().trim() + "%");
			}

			if ((null != zycsqk.getQymc()) && (0 < zycsqk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zycsqk.getQymc().trim() + "%");
			}

			if ((null != zycsqk.getCjmc()) && (0 < zycsqk.getCjmc().trim().length())){
				paraMap.put("cjmc", "%" + zycsqk.getCjmc().trim() + "%");
			}

			if ((null != zycsqk.getWccj()) && (0 < zycsqk.getWccj().trim().length())){
				paraMap.put("wccj", zycsqk.getWccj().trim());
			}

			if ((null != zycsqk.getSbzt()) && (0 < zycsqk.getSbzt().trim().length())){
				paraMap.put("sbzt", "%" + zycsqk.getSbzt().trim() + "%");
			}

			if ((null != zycsqk.getCzfs()) && (0 < zycsqk.getCzfs().trim().length())){
				paraMap.put("czfs", "%" + zycsqk.getCzfs().trim() + "%");
			}

			if ((null != zycsqk.getFhssmc()) && (0 < zycsqk.getFhssmc().trim().length())){
				paraMap.put("fhssmc", "%" + zycsqk.getFhssmc().trim() + "%");
			}

			if ((null != zycsqk.getGrfhyp()) && (0 < zycsqk.getGrfhyp().trim().length())){
				paraMap.put("grfhyp", "%" + zycsqk.getGrfhyp().trim() + "%");
			}
			if ((null != zycsqk.getSzc()) && (0 < zycsqk.getSzc().trim().length())){
				paraMap.put("szc", zycsqk.getSzc().trim() );
			}
		}
		//判断登录人是否为企业人员，要是，则查询自己登记
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))) //判断为安监中队，则查看所在镇企业
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
		pagination = zycsqkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	 /**
	 * Function Name: view
	 * Function Description：查看化作业场所情况
	 * Writer：陆婷
	 * CreateTime：2013-11-22
	 * 修改 默认接触人数为0 by 陆婷 2013-12-4 
	 */
	public String view() throws Exception{
		if((null != zycsqk)&&(null != zycsqk.getId()))
		{
			zycsqk = zycsqkService.getById(zycsqk.getId());
			if(zycsqk.getLinkid() == null || "".equals(zycsqk.getLinkid()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				zycsqk.setLinkid(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",zycsqk.getLinkid());
				map.put("picType","zycsqk");
			    picList = szwxPhotoService.findPicPath(map);//获取岗位图片
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			zycsqk.setLinkid(linkId);
			zycsqk.setJcrs("0");
		}
		return VIEW;
	}

	/**
	 * Function Name: initEdit
	 * Function Description：初始化化作业场所情况
	 * Writer：陆婷
	 * CreateTime：2013-11-22
	 */
	public String initEdit() throws Exception{
		try {
			view();
			Map map = new HashMap();
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		    map.put("qyid", company.getId());
		    Zywhys zywhys = zycsqkService.getZywhysByMap(map);
		    if(zywhys != null && zywhys.getId() != null)
		    {
		    	String[] zywsysmcid = zywhys.getId().replaceAll(" ", "").split(",");
		 	    String[] zywsysmc = zywhys.getName().replaceAll(" ", "").split(",");
		 	    for(int i=0;i<zywsysmcid.length;i++)
		 	    {
		 	    	Zywhys zywhyss = new Zywhys();
		 	    	zywhyss.setId(zywsysmcid[i]);
		 	    	zywhyss.setName(zywsysmc[i]);
		 	    	zywhysList.add(zywhyss);
		 	    }
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	    return EDIT;
	}

	/**
	 * Function Name: save
	 * Function Description：保存化作业场所情况
	 * Writer：陆婷
	 * CreateTime：2013-11-22
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zycsqk.setSzzid(dept.getDeptCode());
			zycsqk.setSzzname(dept.getDeptName());
			zycsqk.setQyid(company.getId());
			zycsqk.setQymc(company.getCompanyname());
			zycsqk.setQylx(company.getQylx());
			zycsqk.setHyfl(company.getHyfl());
			zycsqk.setQyzclx(company.getQyzclx());
			zycsqk.setSzc(company.getSzc());
			zycsqk.setSzcname(company.getSzcname());
			zycsqk.setDeptId(this.getLoginUserDepartmentId());
			zycsqk.setDelFlag(0);
			zycsqk.setCreateTime(new Date());
			zycsqk.setCreateUserID(this.getLoginUserId());
			zycsqkService.save(zycsqk);
		}else{
			zycsqkService.update(zycsqk);
		}
		Zywsjbxx zywsjbxx = zywsjbxxService.getByCompanyId(company.getId());
		if(zywsjbxx !=null)
		{
			Map map = new HashMap();
			map.put("qyid", company.getId());
			String total = zycsqkService.getTotalCountByMap(map);
			zywsjbxx.setGws(total);
			zywsjbxxService.update(zywsjbxx);
		}
		return RELOAD;
	}

	/**
	 * Function Name: delete
	 * Function Description：删除化作业场所情况
	 * Writer：陆婷
	 * CreateTime：2013-11-22
	 */
	public String delete() throws Exception{
	    try{
			zycsqkService.deleteWithFlag(ids);
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Zywsjbxx zywsjbxx = zywsjbxxService.getByCompanyId(company.getId());
			if(zywsjbxx !=null)
			{
				Map map = new HashMap();
				map.put("qyid", company.getId());
				String total = zycsqkService.getTotalCountByMap(map);
				zywsjbxx.setGws(total);
				zywsjbxxService.update(zywsjbxx);
			}
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
			destFName.append(root).append("file/zycsqk/");
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
			taskPic.setPicName("zycsqk/"+imgName);
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
			jn.put("picName", "zycsqk/"+imgName);
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
	/**
	 * Function Name: zybwhdalist
	 * Function Description：查询职业病危害档案列表
	 * Writer：陆婷
	 * CreateTime：2013-12-3
	 */
	public void zybwhdalist() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zybwhda){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zybwhda.getQymc()) && (0 < zybwhda.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zybwhda.getQymc().trim() + "%");
			}
		}
		
		List<Zybwhda> zybwhdaList = new ArrayList<Zybwhda>();
		zybwhdaList = zycsqkService.getZybwhdaListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = zycsqkService.getZybwhdaListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(zybwhdaList);
		
		convObjectToJson(pagination,null);
	}
	
	
	/**
	 * Function Name: zybwhdaListExport
	 * Function Description：导出职业病危害档案列表
	 * Writer：陆婷
	 * CreateTime：2013-12-3
	 */
	public void zybwhdaListExport() throws Exception{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zybwhda.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("职业病危害档案");
		    sheet.setColumnWidth(0, 15000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 8000);
	        sheet.setColumnWidth(5, 8000);
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 8000);
	        sheet.setColumnWidth(11, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("职业病危害档案");
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
	        ccl0.setCellValue("单位名称");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("职业危害因素");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("存在职业病危害场所数量");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("接触职业病危害人员数量");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("职业健康监护总人数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("职业健康体检正常人数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("职业相关异常人数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("职业禁忌人数");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("疑似职业病人数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("检测点数");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("职业病危害行业类别");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(11);
	        ccl11.setCellValue("职业病危害程度");
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
	        	String qymc = (String) getSessionAttribute("qymc");
	        	paraMap.put("qymc", qymc);
			}
	        if(null != zybwhda){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != zybwhda.getQymc()) && (0 < zybwhda.getQymc().trim().length())){
					paraMap.put("qymc", "%" + zybwhda.getQymc().trim() + "%");
					setSessionAttribute("qymc", "%" + zybwhda.getQymc().trim() + "%");
				}
			}
	        List<Zybwhda> zybwhdaList = zycsqkService.getZybwhdaListExportByMap(paraMap);
			
			int num = 2;
			for(Zybwhda tjyhbean:zybwhdaList)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(tjyhbean.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(tjyhbean.getZybwhys());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(tjyhbean.getWhcs());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(tjyhbean.getJcrs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(tjyhbean.getJhrs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(tjyhbean.getZcrs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(tjyhbean.getYcrs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(tjyhbean.getJjrs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(tjyhbean.getYsrs());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(tjyhbean.getJcds());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(tjyhbean.getHylb());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(tjyhbean.getWhcd());
		        ce11.setCellStyle(c);
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

	public Zycsqk getZycsqk(){
		return this.zycsqk;
	}

	public void setZycsqk(Zycsqk zycsqk){
		this.zycsqk = zycsqk;
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
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
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
	public List<Zywhys> getZywhysList() {
		return zywhysList;
	}
	public void setZywhysList(List<Zywhys> zywhysList) {
		this.zywhysList = zywhysList;
	}
	public Zybwhda getZybwhda() {
		return zybwhda;
	}
	public void setZybwhda(Zybwhda zybwhda) {
		this.zybwhda = zybwhda;
	}
    
}
