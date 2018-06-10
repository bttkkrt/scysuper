/**
 * Class Name: JshxZjjcbAction
 * Class Description：专家检查列表
 */
package com.jshx.zjjcb.web;

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
import com.jshx.zjjcb.entity.JshxZjjcb;
import com.jshx.zjjcb.service.JshxZjjcbService;

public class JshxZjjcbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZjjcb jshxZjjcb = new JshxZjjcb();
	 /**
     *附件列表
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxZjjcbService jshxZjjcbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;

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
	
	
	private Date queryJcrqStart;

	private Date queryJcrqEnd;
	
	private String exportYear;
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
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZjjcb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZjjcb.getZjName()) && (0 < jshxZjjcb.getZjName().trim().length())){
				paraMap.put("zjName", "%" + jshxZjjcb.getZjName().trim() + "%");
			}

			if (null != queryJcrqStart){
				paraMap.put("startJcrq", queryJcrqStart);
			}

			if (null != queryJcrqEnd){
				paraMap.put("endJcrq", queryJcrqEnd);
			}
			if ((null != jshxZjjcb.getQymc()) && (0 < jshxZjjcb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZjjcb.getQymc().trim() + "%");
			}
			if ((null != jshxZjjcb.getSzzid()) && (0 < jshxZjjcb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZjjcb.getSzzid().trim() );
			}
			if ((null != jshxZjjcb.getSzc() )&& (0 < jshxZjjcb.getSzc().trim().length())){
				paraMap.put("szc",jshxZjjcb.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
 
		pagination = jshxZjjcbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != jshxZjjcb)&&(null != jshxZjjcb.getId())){
			jshxZjjcb = jshxZjjcbService.getById(jshxZjjcb.getId());
			if(jshxZjjcb.getLinkId() == null || "".equals(jshxZjjcb.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxZjjcb.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxZjjcb.getLinkId());
				map.put("picType","zjjcb");
			    picList01 = szwxPhotoService.findPicPath(map);//获取附件列表
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxZjjcb.setLinkId(linkId);
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
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			try {
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				jshxZjjcb.setSzzid(dept.getDeptCode());
				jshxZjjcb.setSzzname(dept.getDeptName());
				jshxZjjcb.setQyid(company.getId());
				jshxZjjcb.setQymc(company.getCompanyname());
				jshxZjjcb.setDeptId(this.getLoginUserDepartmentId());
				jshxZjjcb.setDelFlag(0);
				jshxZjjcb.setCreateUserID(this.getLoginUserId());
				jshxZjjcb.setCreateTime(new Date());
				jshxZjjcb.setQylx(company.getQylx());
				jshxZjjcb.setHyfl(company.getHyfl());
				jshxZjjcb.setQygm(company.getQygm());
				jshxZjjcb.setQyzclx(company.getQyzclx());
				jshxZjjcb.setDeptId(this.getLoginUserDepartmentId());
				jshxZjjcb.setDelFlag(0);
				jshxZjjcb.setIfwhpqylx(company.getIfwhpqylx());
				jshxZjjcb.setIfyhbzjyqy(company.getIfyhbzjyqy());
				jshxZjjcb.setIfzywhqylx(company.getIfzywhqylx());
				jshxZjjcb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
				jshxZjjcb.setSzc(company.getSzc());
				jshxZjjcb.setSzcname(company.getSzcname());
			} catch (RuntimeException e) {
				e.printStackTrace();
			}//企业名称
			jshxZjjcb.setDeptId(this.getLoginUserDepartmentId());
			jshxZjjcb.setDelFlag(0);
			jshxZjjcb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZjjcb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZjjcbService.save(jshxZjjcb);
		}else{
			jshxZjjcb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZjjcb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZjjcbService.update(jshxZjjcb);
		}
		flag = "1";
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxZjjcbService.deleteWithFlag(ids);
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
			root = root.replace("webapps","virtualdir/upload/file/zjjcb/");
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
			String picname = "zjjcb/"+imgName;
			taskPic.setPicName(picname);
			taskPic.setTaskProId(jshxZjjcb.getLinkId());
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setPicType("zjjcb");//类型设置为资格证书管理
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", picname);
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
	
	/**
	 * 导出专家检查汇总
	 * author：陆婷
	 * 2013-12-5
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zjjc.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("相城区化工生产企业邀请专家排查隐患汇总");
			sheet.createFreezePane(2,4,3,4);
		    sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 10000); 
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
	        sheet.setColumnWidth(12, 5000);
	        sheet.setColumnWidth(13, 5000);
	        sheet.setColumnWidth(14, 5000);
	        sheet.setColumnWidth(15, 5000);
	        sheet.setColumnWidth(16, 5000);
	        sheet.setColumnWidth(17, 5000);
	        sheet.setColumnWidth(18, 5000);
	        sheet.setColumnWidth(19, 5000);
	        sheet.setColumnWidth(20, 5000);
	        sheet.setColumnWidth(21, 5000);
	        sheet.setColumnWidth(22, 5000);
	        sheet.setColumnWidth(23, 5000);
	        sheet.setColumnWidth(24, 5000);
	        sheet.setColumnWidth(25, 5000);
	        sheet.setColumnWidth(26, 5000);
	        sheet.setColumnWidth(27, 5000);
	        sheet.setColumnWidth(28, 5000);
	        sheet.setColumnWidth(29, 5000);
	        sheet.setColumnWidth(30, 5000);
	        sheet.setColumnWidth(31, 5000);
	        sheet.setColumnWidth(32, 5000);
	        sheet.setColumnWidth(33, 5000);
	        sheet.setColumnWidth(34, 5000);
	        sheet.setColumnWidth(35, 5000);
	        sheet.setColumnWidth(36, 5000);
	        sheet.setColumnWidth(37, 5000);
	        sheet.setColumnWidth(38, 5000);
	        sheet.setColumnWidth(39, 5000);
	        sheet.setColumnWidth(40, 5000);
	        sheet.setColumnWidth(41, 5000);
	        sheet.setColumnWidth(42, 5000);
	        sheet.setColumnWidth(43, 5000);
	        sheet.setColumnWidth(44, 5000);
	        sheet.setColumnWidth(45, 5000);
	        sheet.setColumnWidth(46, 5000);
	        sheet.setColumnWidth(47, 5000);
	        sheet.setColumnWidth(48, 5000);
	        sheet.setColumnWidth(49, 5000);
	        sheet.setColumnWidth(50, 5000);
	        sheet.setColumnWidth(51, 5000);
	        sheet.setColumnWidth(52, 5000);
	        sheet.setColumnWidth(53, 5000);
	        sheet.setColumnWidth(54, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("相城区化工生产企业邀请专家排查隐患汇总");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 54)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(25*20));
	        HSSFCell cc1111 = r3.createCell(0);
	        cc1111.setCellValue("所在镇");
	        cc1111.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 3, (short) 0)); 
	        HSSFCell ccl0 = r3.createCell(1);
	        ccl0.setCellValue("企业名称");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 3, (short) 1)); 
	        HSSFCell ccl1 = r3.createCell(2);
	        ccl1.setCellValue("专家排查治理隐患情况合计(隐患为项，资金为万元)");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 1, (short) 54)); 
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(25*20));
	        HSSFCell cc20 = r4.createCell(2);
	        cc20.setCellValue("1月");
	        cc20.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 2, 2, (short) 5)); 
	        HSSFCell cc21 = r4.createCell(6);
	        cc21.setCellValue("2月");
	        cc21.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 6, 2, (short) 9)); 
	        HSSFCell cc22 = r4.createCell(10);
	        cc22.setCellValue("3月");
	        cc22.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 10, 2, (short) 13)); 
	        HSSFCell cc23 = r4.createCell(14);
	        cc23.setCellValue("4月");
	        cc23.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 14, 2, (short) 17)); 
	        HSSFCell cc24 = r4.createCell(18);
	        cc24.setCellValue("5月");
	        cc24.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 18, 2, (short) 21)); 
	        HSSFCell cc25 = r4.createCell(22);
	        cc25.setCellValue("6月");
	        cc25.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 22, 2, (short) 25)); 
	        HSSFCell cc26 = r4.createCell(26);
	        cc26.setCellValue("7月");
	        cc26.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 26, 2, (short) 29)); 
	        HSSFCell cc27 = r4.createCell(30);
	        cc27.setCellValue("8月");
	        cc27.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 30, 2, (short) 33)); 
	        
	        HSSFCell cc28 = r4.createCell(34);
	        cc28.setCellValue("9月");
	        cc28.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 34, 2, (short) 37)); 
	        HSSFCell cc29 = r4.createCell(38);
	        cc29.setCellValue("10月");
	        cc29.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 38, 2, (short) 41)); 
	        HSSFCell cc230 = r4.createCell(42);
	        cc230.setCellValue("11月");
	        cc230.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 42, 2, (short) 45)); 
	        HSSFCell cc231 = r4.createCell(46);
	        cc231.setCellValue("12月");
	        cc231.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 46, 2, (short) 49)); 
	        HSSFCell cc232 = r4.createCell(50);
	        cc232.setCellValue("本年累计排查隐患");
	        cc232.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 50, 3, (short) 50)); 
	        HSSFCell cc233 = r4.createCell(52);
	        cc233.setCellValue("本年累计整改隐患");
	        cc233.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 52, 3, (short) 52)); 
	        
	        
	        
	        HSSFCell cc234 = r4.createCell(54);
	        cc234.setCellValue("本年累计已投入整改资金（万元）");
	        cc234.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 54, 3, (short) 54)); 

	        
	        
	        HSSFRow r5 = sheet.createRow(3);
	        r5.setHeight((short)(25*20));
	        HSSFCell cc30 = r5.createCell(2);
	        cc30.setCellValue("检查日期");
	        cc30.setCellStyle(cs);
	        HSSFCell cc31 = r5.createCell(3);
	        cc31.setCellValue("排查");
	        cc31.setCellStyle(cs);
	        HSSFCell cc32 = r5.createCell(4);
	        cc32.setCellValue("整改");
	        cc32.setCellStyle(cs);
	        HSSFCell cc33 = r5.createCell(5);
	        cc33.setCellValue("整改资金(万元)");
	        cc33.setCellStyle(cs);
	        HSSFCell cc34 = r5.createCell(6);
	        cc34.setCellValue("检查日期");
	        cc34.setCellStyle(cs);
	        HSSFCell cc35 = r5.createCell(7);
	        cc35.setCellValue("排查");
	        cc35.setCellStyle(cs);
	        HSSFCell cc36 = r5.createCell(8);
	        cc36.setCellValue("整改");
	        cc36.setCellStyle(cs);
	        HSSFCell cc37 = r5.createCell(9);
	        cc37.setCellValue("整改资金(万元)");
	        cc37.setCellStyle(cs);
	        
	        HSSFCell cc38 = r5.createCell(10);
	        cc38.setCellValue("检查日期");
	        cc38.setCellStyle(cs);
	        HSSFCell cc39 = r5.createCell(11);
	        cc39.setCellValue("排查");
	        cc39.setCellStyle(cs);
	        HSSFCell cc310 = r5.createCell(12);
	        cc310.setCellValue("整改");
	        cc310.setCellStyle(cs);
	        HSSFCell cc311 = r5.createCell(13);
	        cc311.setCellValue("整改资金(万元)");
	        cc311.setCellStyle(cs);
	        HSSFCell cc312 = r5.createCell(14);
	        cc312.setCellValue("检查日期");
	        cc312.setCellStyle(cs);
	        HSSFCell cc313 = r5.createCell(15);
	        cc313.setCellValue("排查");
	        cc313.setCellStyle(cs);
	        HSSFCell cc314 = r5.createCell(16);
	        cc314.setCellValue("整改");
	        cc314.setCellStyle(cs);
	        HSSFCell cc315 = r5.createCell(17);
	        cc315.setCellValue("整改资金(万元)");
	        cc315.setCellStyle(cs);
	        HSSFCell cc316 = r5.createCell(18);
	        cc316.setCellValue("检查日期");
	        cc316.setCellStyle(cs);
	        HSSFCell cc317 = r5.createCell(19);
	        cc317.setCellValue("排查");
	        cc317.setCellStyle(cs);
	        HSSFCell cc318 = r5.createCell(20);
	        cc318.setCellValue("整改");
	        cc318.setCellStyle(cs);
	        HSSFCell cc319 = r5.createCell(21);
	        cc319.setCellValue("整改资金(万元)");
	        cc319.setCellStyle(cs);
	        
	        
	        HSSFCell cc320 = r5.createCell(22);
	        cc320.setCellValue("检查日期");
	        cc320.setCellStyle(cs);
	        HSSFCell cc321 = r5.createCell(23);
	        cc321.setCellValue("排查");
	        cc321.setCellStyle(cs);
	        HSSFCell cc322 = r5.createCell(24);
	        cc322.setCellValue("整改");
	        cc322.setCellStyle(cs);
	        HSSFCell cc323 = r5.createCell(25);
	        cc323.setCellValue("整改资金(万元)");
	        cc323.setCellStyle(cs);
	        
	        
	        HSSFCell cc324 = r5.createCell(26);
	        cc324.setCellValue("检查日期");
	        cc324.setCellStyle(cs);
	        HSSFCell cc325 = r5.createCell(27);
	        cc325.setCellValue("排查");
	        cc325.setCellStyle(cs);
	        HSSFCell cc326 = r5.createCell(28);
	        cc326.setCellValue("整改");
	        cc326.setCellStyle(cs);
	        HSSFCell cc327 = r5.createCell(29);
	        cc327.setCellValue("整改资金(万元)");
	        cc327.setCellStyle(cs);
	        
	        
	        HSSFCell cc328 = r5.createCell(30);
	        cc328.setCellValue("检查日期");
	        cc328.setCellStyle(cs);
	        HSSFCell cc329 = r5.createCell(31);
	        cc329.setCellValue("排查");
	        cc329.setCellStyle(cs);
	        HSSFCell cc330 = r5.createCell(32);
	        cc330.setCellValue("整改");
	        cc330.setCellStyle(cs);
	        HSSFCell cc331 = r5.createCell(33);
	        cc331.setCellValue("整改资金(万元)");
	        cc331.setCellStyle(cs);
	        
	        
	        HSSFCell cc332 = r5.createCell(34);
	        cc332.setCellValue("检查日期");
	        cc332.setCellStyle(cs);
	        HSSFCell cc333 = r5.createCell(35);
	        cc333.setCellValue("排查");
	        cc333.setCellStyle(cs);
	        HSSFCell cc334 = r5.createCell(36);
	        cc334.setCellValue("整改");
	        cc334.setCellStyle(cs);
	        HSSFCell cc335 = r5.createCell(37);
	        cc335.setCellValue("整改资金(万元)");
	        cc335.setCellStyle(cs);
	        
	        HSSFCell cc336 = r5.createCell(38);
	        cc336.setCellValue("检查日期");
	        cc336.setCellStyle(cs);
	        HSSFCell cc337 = r5.createCell(39);
	        cc337.setCellValue("排查");
	        cc337.setCellStyle(cs);
	        HSSFCell cc338 = r5.createCell(40);
	        cc338.setCellValue("整改");
	        cc338.setCellStyle(cs);
	        HSSFCell cc339 = r5.createCell(41);
	        cc339.setCellValue("整改资金(万元)");
	        cc339.setCellStyle(cs);
	        
	        
	        HSSFCell cc340 = r5.createCell(42);
	        cc340.setCellValue("检查日期");
	        cc340.setCellStyle(cs);
	        HSSFCell cc341 = r5.createCell(43);
	        cc341.setCellValue("排查");
	        cc341.setCellStyle(cs);
	        HSSFCell cc342 = r5.createCell(44);
	        cc342.setCellValue("整改");
	        cc342.setCellStyle(cs);
	        HSSFCell cc343 = r5.createCell(45);
	        cc343.setCellValue("整改资金(万元)");
	        cc343.setCellStyle(cs);
	        
	        
	        HSSFCell cc344 = r5.createCell(46);
	        cc344.setCellValue("检查日期");
	        cc344.setCellStyle(cs);
	        HSSFCell cc345 = r5.createCell(47);
	        cc345.setCellValue("排查");
	        cc345.setCellStyle(cs);
	        HSSFCell cc346 = r5.createCell(48);
	        cc346.setCellValue("整改");
	        cc346.setCellStyle(cs);
	        HSSFCell cc347 = r5.createCell(49);
	        cc347.setCellValue("整改资金(万元)");
	        cc347.setCellStyle(cs);
	        
	        
	        HSSFCell cc348 = r5.createCell(51);
	        cc348.setCellValue("重大隐患");
	        cc348.setCellStyle(cs);
	        HSSFCell cc349 = r5.createCell(53);
	        cc349.setCellValue("重大隐患");
	        cc349.setCellStyle(cs);
	        
	        
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
        		String szzid = (String) getSessionAttribute("szzid");
        		exportYear = (String) getSessionAttribute("exportYear");
        		paraMap.put("qymc", qymc);
        		paraMap.put("szzid", szzid);
    		}
	        if(null != jshxZjjcb){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != jshxZjjcb.getQymc()) && (0 < jshxZjjcb.getQymc().trim().length())){
					paraMap.put("qymc", "%" + jshxZjjcb.getQymc().trim() + "%");
					setSessionAttribute("qymc",  "%" + jshxZjjcb.getQymc().trim() + "%");
				}
				if ((null != jshxZjjcb.getSzzid()) && (0 < jshxZjjcb.getSzzid().trim().length())){
					paraMap.put("szzid",  jshxZjjcb.getSzzid().trim() );
					setSessionAttribute("szzid", jshxZjjcb.getSzzid().trim());
				}
			}
	        if(exportYear != null && !"".equals(exportYear))
	        {
	        	setSessionAttribute("exportYear", exportYear);
	        }
	        else
	        {
	        	SimpleDateFormat s = new SimpleDateFormat("yyyy");
	        	exportYear = s.format(new Date());
	        	setSessionAttribute("exportYear",exportYear );
	        }
			//hanxc 20141223 修改查询条件 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			//hanxc 20141223 修改查询条件 end
			 
			int num = 4;
			List<JshxZjjcb> list = jshxZjjcbService.getCompanyListByMap(paraMap);
			
			double data1 = 0;
			double data2 = 0;
			double data3 = 0;
			double data4 = 0;
			double data5 = 0;
			double data6 = 0;
			double data7 = 0;
			double data8 = 0;
			double data9 = 0;
			double data10 = 0;
			double data11 = 0;
			double data12 = 0;
			double data13 = 0;
			double data14 = 0;
			double data15 = 0;
			double data16 = 0;
			double data17 = 0;
			double data18 = 0;
			double data19 = 0;
			double data20 = 0;
			double data21 = 0;
			double data22 = 0;
			double data23 = 0;
			double data24 = 0;
			double data25 = 0;
			double data26 = 0;
			double data27 = 0;
			double data28 = 0;
			double data29 = 0;
			double data30 = 0;
			double data31 = 0;
			double data32 = 0;
			double data33 = 0;
			double data34 = 0;
			double data35 = 0;
			double data36 = 0;
			double data37 = 0;
			double data38 = 0;
			double data39 = 0;
			double data40 = 0;
			double data41 = 0;
			for(JshxZjjcb j:list)
			{
				double yhtotal=0;
				double zdtotal=0;
				double yhzgtotal=0;
				double zdzgtotal=0;
				double zgzj=0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(j.getSzzname());
				ce0.setCellStyle(c);
				HSSFCell ce1 = ro.createCell(1);
				ce1.setCellValue(j.getQymc());
				ce1.setCellStyle(c);
				
				for(int i=1;i<=12;i++)
				{
					Map map = new HashMap();
					map.put("qyid", j.getQyid());
					if(i < 10)
					{
						map.put("month", exportYear + "-0" + i);
					}
					else
					{
						map.put("month", exportYear + "-" +  i);
					}
					jshxZjjcb = jshxZjjcbService.getJshxZjjcbByMap(map);
					if(jshxZjjcb != null && jshxZjjcb.getId() != null)
					{
						yhtotal += Double.valueOf(jshxZjjcb.getYhs());
						zdtotal += Double.valueOf(jshxZjjcb.getZdyhs());
						yhzgtotal += Double.valueOf(jshxZjjcb.getYhzgs());
						zdzgtotal += Double.valueOf(jshxZjjcb.getZdyhzgs());
						zgzj += Double.valueOf(jshxZjjcb.getZgfy());
						switch(i)
						{
							case 1:
							    data1 += Double.valueOf(jshxZjjcb.getYhs());
								data2 += Double.valueOf(jshxZjjcb.getYhzgs());
								data3 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 2:
							   	data4 += Double.valueOf(jshxZjjcb.getYhs());
								data5 += Double.valueOf(jshxZjjcb.getYhzgs());
								data6 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 3:
							    data7 += Double.valueOf(jshxZjjcb.getYhs());
								data8 += Double.valueOf(jshxZjjcb.getYhzgs());
								data9 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 4:
							    data10 += Double.valueOf(jshxZjjcb.getYhs());
								data11 += Double.valueOf(jshxZjjcb.getYhzgs());
								data12 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 5:
							    data13 += Double.valueOf(jshxZjjcb.getYhs());
								data14 += Double.valueOf(jshxZjjcb.getYhzgs());
								data15 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 6:
								data16 += Double.valueOf(jshxZjjcb.getYhs());
								data17 += Double.valueOf(jshxZjjcb.getYhzgs());
								data18 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 7:
								data19 += Double.valueOf(jshxZjjcb.getYhs());
								data20 += Double.valueOf(jshxZjjcb.getYhzgs());
								data21 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 8:
								data22 += Double.valueOf(jshxZjjcb.getYhs());
								data23 += Double.valueOf(jshxZjjcb.getYhzgs());
								data24 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							case 9:
								data25 += Double.valueOf(jshxZjjcb.getYhs());
								data26 += Double.valueOf(jshxZjjcb.getYhzgs());
								data27 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
							
							case 10:
								data28 += Double.valueOf(jshxZjjcb.getYhs());
								data29 += Double.valueOf(jshxZjjcb.getYhzgs());
								data30 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
								
							case 11:
								data31 += Double.valueOf(jshxZjjcb.getYhs());
								data32 += Double.valueOf(jshxZjjcb.getYhzgs());
								data33 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
								
							case 12:
								data34 += Double.valueOf(jshxZjjcb.getYhs());
								data35 += Double.valueOf(jshxZjjcb.getYhzgs());
								data36 += Double.valueOf(jshxZjjcb.getZgfy());
								break;
						}
					}
					HSSFCell ce2 = ro.createCell(2+(i-1)*4);
					ce2.setCellValue((jshxZjjcb != null && jshxZjjcb.getJcrq() != null)?sdf.format(jshxZjjcb.getJcrq()):"");
					ce2.setCellStyle(c);
					HSSFCell ce3 = ro.createCell(3+(i-1)*4);
					ce3.setCellValue((jshxZjjcb != null && jshxZjjcb.getYhs() != null)?jshxZjjcb.getYhs():"0");
					ce3.setCellStyle(c);
					HSSFCell ce4 = ro.createCell(4+(i-1)*4);
					ce4.setCellValue((jshxZjjcb != null && jshxZjjcb.getYhzgs() != null)?jshxZjjcb.getYhzgs():"0");
					ce4.setCellStyle(c);
					HSSFCell ce5 = ro.createCell(5+(i-1)*4);
					ce5.setCellValue((jshxZjjcb != null && jshxZjjcb.getZgfy() != null)?jshxZjjcb.getZgfy():"0");
					ce5.setCellStyle(c);
				}
				HSSFCell ce2 = ro.createCell(50);
				ce2.setCellValue(yhtotal);
				ce2.setCellStyle(c);
				HSSFCell ce3 = ro.createCell(51);
				ce3.setCellValue(zdtotal);
				ce3.setCellStyle(c);
				HSSFCell ce4 = ro.createCell(52);
				ce4.setCellValue(yhzgtotal);
				ce4.setCellStyle(c);
				HSSFCell ce5 = ro.createCell(53);
				ce5.setCellValue(zdzgtotal);
				ce5.setCellStyle(c);
				HSSFCell ce6 = ro.createCell(54);
				ce6.setCellValue(zgzj);
				ce6.setCellStyle(c);
				data37 += yhtotal;
				data38 += zdtotal;
				data39 += yhzgtotal;
				data40 += zdzgtotal;
				data41 += zgzj;
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(1);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(3);
			ce2.setCellValue(data1);
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(4);
			ce3.setCellValue(data2);
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(5);
			ce4.setCellValue(data3);
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(7);
			ce5.setCellValue(data4);
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(8);
			ce6.setCellValue(data5);
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(9);
			ce7.setCellValue(data6);
			ce7.setCellStyle(c);
			HSSFCell ce8 = ro.createCell(11);
			ce8.setCellValue(data7);
			ce8.setCellStyle(c);
			HSSFCell ce9 = ro.createCell(12);
			ce9.setCellValue(data8);
			ce9.setCellStyle(c);
			HSSFCell ce10 = ro.createCell(13);
			ce10.setCellValue(data9);
			ce10.setCellStyle(c);
			HSSFCell ce11 = ro.createCell(15);
			ce11.setCellValue(data10);
			ce11.setCellStyle(c);
			HSSFCell ce12 = ro.createCell(16);
			ce12.setCellValue(data11);
			ce12.setCellStyle(c);
			HSSFCell ce13 = ro.createCell(17);
			ce13.setCellValue(data12);
			ce13.setCellStyle(c);
			HSSFCell ce14 = ro.createCell(19);
			ce14.setCellValue(data13);
			ce14.setCellStyle(c);
			HSSFCell ce15 = ro.createCell(20);
			ce15.setCellValue(data14);
			ce15.setCellStyle(c);
			HSSFCell ce16 = ro.createCell(21);
			ce16.setCellValue(data15);
			ce16.setCellStyle(c);
			HSSFCell ce17 = ro.createCell(23);
			ce17.setCellValue(data16);
			ce17.setCellStyle(c);
			HSSFCell ce18 = ro.createCell(24);
			ce18.setCellValue(data17);
			ce18.setCellStyle(c);
			HSSFCell ce19 = ro.createCell(25);
			ce19.setCellValue(data18);
			ce19.setCellStyle(c);
			HSSFCell ce20 = ro.createCell(27);
			ce20.setCellValue(data19);
			ce20.setCellStyle(c);
			HSSFCell ce21 = ro.createCell(28);
			ce21.setCellValue(data20);
			ce21.setCellStyle(c);
			HSSFCell ce22 = ro.createCell(29);
			ce22.setCellValue(data21);
			ce22.setCellStyle(c);
			HSSFCell ce23 = ro.createCell(31);
			ce23.setCellValue(data22);
			ce23.setCellStyle(c);
			HSSFCell ce24 = ro.createCell(32);
			ce24.setCellValue(data23);
			ce24.setCellStyle(c);
			HSSFCell ce25 = ro.createCell(33);
			ce25.setCellValue(data24);
			ce25.setCellStyle(c);
			HSSFCell ce26 = ro.createCell(35);
			ce26.setCellValue(data25);
			ce26.setCellStyle(c);
			HSSFCell ce27 = ro.createCell(36);
			ce27.setCellValue(data26);
			ce27.setCellStyle(c);
			HSSFCell ce28 = ro.createCell(37);
			ce28.setCellValue(data27);
			ce28.setCellStyle(c);
			HSSFCell ce29 = ro.createCell(39);
			ce29.setCellValue(data28);
			ce29.setCellStyle(c);
			HSSFCell ce30 = ro.createCell(40);
			ce30.setCellValue(data29);
			ce30.setCellStyle(c);
			HSSFCell ce31 = ro.createCell(41);
			ce31.setCellValue(data30);
			ce31.setCellStyle(c);
			HSSFCell ce32 = ro.createCell(43);
			ce32.setCellValue(data31);
			ce32.setCellStyle(c);
			HSSFCell ce33 = ro.createCell(44);
			ce33.setCellValue(data32);
			ce33.setCellStyle(c);
			HSSFCell ce34 = ro.createCell(45);
			ce34.setCellValue(data33);
			ce34.setCellStyle(c);
			HSSFCell ce35 = ro.createCell(47);
			ce35.setCellValue(data34);
			ce35.setCellStyle(c);
			HSSFCell ce36 = ro.createCell(48);
			ce36.setCellValue(data35);
			ce36.setCellStyle(c);
			HSSFCell ce37 = ro.createCell(49);
			ce37.setCellValue(data36);
			ce37.setCellStyle(c);
			HSSFCell ce38 = ro.createCell(50);
			ce38.setCellValue(data37);
			ce38.setCellStyle(c);
			HSSFCell ce39 = ro.createCell(51);
			ce39.setCellValue(data38);
			ce39.setCellStyle(c);
			HSSFCell ce40 = ro.createCell(52);
			ce40.setCellValue(data39);
			ce40.setCellStyle(c);
			HSSFCell ce41= ro.createCell(53);
			ce41.setCellValue(data40);
			ce41.setCellStyle(c);
			HSSFCell ce42 = ro.createCell(54);
			ce42.setCellValue(data41);
			ce42.setCellStyle(c);
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

	public JshxZjjcb getJshxZjjcb(){
		return this.jshxZjjcb;
	}

	public void setJshxZjjcb(JshxZjjcb jshxZjjcb){
		this.jshxZjjcb = jshxZjjcb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryJcrqStart(){
		return this.queryJcrqStart;
	}

	public void setQueryJcrqStart(Date queryJcrqStart){
		this.queryJcrqStart = queryJcrqStart;
	}

	public Date getQueryJcrqEnd(){
		return this.queryJcrqEnd;
	}

	public void setQueryJcrqEnd(Date queryJcrqEnd){
		this.queryJcrqEnd = queryJcrqEnd;
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

	public String getExportYear() {
		return exportYear;
	}

	public void setExportYear(String exportYear) {
		this.exportYear = exportYear;
	}

}
