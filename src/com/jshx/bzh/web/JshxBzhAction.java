/**
 * Class Name: JshxBzhAction
 * Class Description：安全生产标准化达标
 */
package com.jshx.bzh.web;

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

import com.jshx.bzh.entity.JshxBzh;
import com.jshx.bzh.service.JshxBzhService;
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

public class JshxBzhAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxBzh jshxBzh = new JshxBzh();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxBzhService jshxBzhService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
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
	
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;
    /**
     * 类型
     */
    private String type;
    
    private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList02 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList03 = new ArrayList<PhotoPic>();
	
	//有效期开始时间
	private String queryYxqStart;
	//有效期结束时间
	private String queryYxqEnd;
	private String county;
	
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
	
	public String inits()
	{
		return SUCCESS;
	}
	
	/**
	 * 根据查询条件执行安全生产标准化达标列表的查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxBzh){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxBzh.getSzzname()) && (0 < jshxBzh.getSzzname().trim().length())){
				paraMap.put("szzname", "%" + jshxBzh.getSzzname().trim() + "%");
			}
			if ((null != jshxBzh.getDbjb()) && (0 < jshxBzh.getDbjb().trim().length())){
				paraMap.put("dbjb", jshxBzh.getDbjb().trim());
			}

			if ((null != jshxBzh.getQymc()) && (0 < jshxBzh.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxBzh.getQymc().trim() + "%");
			}
			if ((null != jshxBzh.getSzzid()) && (0 < jshxBzh.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxBzh.getSzzid().trim() );
			}else if((null != county) && (0 < county.trim().length())){
				paraMap.put("szzid", county+"%");
			}
			if ((null != jshxBzh.getWhpqylx()) && (0 < jshxBzh.getWhpqylx().trim().length())){
				paraMap.put("whpqylx", "%" + jshxBzh.getWhpqylx().trim() + "%");
			}
		}
		if (null != queryYxqStart && !"".equals(queryYxqStart)){
			paraMap.put("startYxq", queryYxqStart);
		}

		if (null != queryYxqEnd && !"".equals(queryYxqEnd)){
			paraMap.put("endYxq", queryYxqEnd);
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxBzhService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxBzh){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxBzh.getSzzname()) && (0 < jshxBzh.getSzzname().trim().length())){
				paraMap.put("szzname", "%" + jshxBzh.getSzzname().trim() + "%");
			}
			if ((null != jshxBzh.getDbjb()) && (0 < jshxBzh.getDbjb().trim().length())){
				paraMap.put("dbjb", jshxBzh.getDbjb().trim());
			}

			if ((null != jshxBzh.getQymc()) && (0 < jshxBzh.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxBzh.getQymc().trim() + "%");
			}
			if ((null != jshxBzh.getSzzid()) && (0 < jshxBzh.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxBzh.getSzzid().trim() );
			}
			if ((null != jshxBzh.getWhpqylx()) && (0 < jshxBzh.getWhpqylx().trim().length())){
				paraMap.put("whpqylx", "%" + jshxBzh.getWhpqylx().trim() + "%");
			}
		}
		if (null != queryYxqStart && !"".equals(queryYxqStart)){
			paraMap.put("startYxq", queryYxqStart);
		}

		if (null != queryYxqEnd && !"".equals(queryYxqEnd)){
			paraMap.put("endYxq", queryYxqEnd);
		}
		pagination = jshxBzhService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看某条安全生产标准化达标情况的详细信息
	 */
	public String view() throws Exception{
		if((null != jshxBzh)&&(null != jshxBzh.getId()))
		{
			jshxBzh = jshxBzhService.getById(jshxBzh.getId());
			if(jshxBzh.getLinkId() == null || "".equals(jshxBzh.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				jshxBzh.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",jshxBzh.getLinkId());
				map.put("picType","bzhzpbg");
			    picList01 = szwxPhotoService.findPicPath(map);//获取附件列表
			    map.put("picType","bzhhchp");
			    picList02 = szwxPhotoService.findPicPath(map);//获取附件列表
			    map.put("picType","bzhshbg");
			    picList03 = szwxPhotoService.findPicPath(map);//获取附件列表
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			jshxBzh.setLinkId(linkId);
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
	 * 保存安全生产标准化达标信息（包括新增和修改）
	 */
	public String save() throws Exception{
		try {
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxBzh.setSzzid(dept.getDeptCode());
			jshxBzh.setSzzname(dept.getDeptName());
			jshxBzh.setQyid(company.getId());
			jshxBzh.setQymc(company.getCompanyname());
			jshxBzh.setDeptId(this.getLoginUserDepartmentId());
			jshxBzh.setDelFlag(0);
			jshxBzh.setCreateUserID(this.getLoginUserId());
			jshxBzh.setCreateTime(new Date());
			jshxBzh.setQylx(company.getQylx());
			jshxBzh.setHyfl(company.getHyfl());
			jshxBzh.setQygm(company.getQygm());
			jshxBzh.setQyzclx(company.getQyzclx());
			jshxBzh.setIfwhpqylx(company.getIfwhpqylx());
			jshxBzh.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxBzh.setIfzywhqylx(company.getIfzywhqylx());
			jshxBzh.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxBzh.setSzc(company.getSzc());
			jshxBzh.setSzcname(company.getSzcname());
			jshxBzh.setWhpqylx(company.getWhpqylx());
			jshxBzh.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxBzh.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		if ("add".equalsIgnoreCase(this.flag)){
			jshxBzh.setDeptId(this.getLoginUserDepartmentId());
			jshxBzh.setDelFlag(0);
			jshxBzhService.save(jshxBzh);
		}else{
			jshxBzhService.update(jshxBzh);
		}
		return RELOAD;
	}

	/**
	 * 根据id删除安全生产标准化达标信息
	 */
	public String delete() throws Exception{
	    try{
			jshxBzhService.deleteWithFlag(ids);
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
			root = root.replace("webapps","virtualdir/upload/file/bzh/");
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
			String picname = "bzh/"+imgName;
			taskPic.setPicName(picname);
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
			getResponse().addHeader("Content-Disposition", "attachment;filename=bzh.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("安全生产标准化企业统计");
		    sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 8000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 6000); 
	        sheet.setColumnWidth(5, 6000); 
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 10000);
	        sheet.setColumnWidth(8, 8000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("安全生产标准化企业统计");
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
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("所在镇");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(2);
	        ccl3.setCellValue("达标级别");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(3);
	        ccl4.setCellValue("考评分数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(4);
	        ccl5.setCellValue("有效期");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(5);
	        ccl2.setCellValue("发证日期");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("证书号");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("发证机关");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("危化品企业类型");
	        ccl8.setCellStyle(cs);
	        
	        
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
	        	jshxBzh = (JshxBzh) getSessionAttribute("jshxBzh");
	        	queryYxqStart = (String) getSessionAttribute("queryYxqStart");
	        	queryYxqEnd = (String) getSessionAttribute("queryYxqEnd");
    		}
	        if(null != jshxBzh){
	        	setSessionAttribute("jshxBzh", jshxBzh);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != jshxBzh.getSzzname()) && (0 < jshxBzh.getSzzname().trim().length())){
					paraMap.put("szzname", "%" + jshxBzh.getSzzname().trim() + "%");
				}
				if ((null != jshxBzh.getDbjb()) && (0 < jshxBzh.getDbjb().trim().length())){
					paraMap.put("dbjb", jshxBzh.getDbjb().trim());
				}

				if ((null != jshxBzh.getQymc()) && (0 < jshxBzh.getQymc().trim().length())){
					paraMap.put("qymc", "%" + jshxBzh.getQymc().trim() + "%");
				}
				if ((null != jshxBzh.getSzzid()) && (0 < jshxBzh.getSzzid().trim().length())){
					paraMap.put("szzid",  jshxBzh.getSzzid().trim() );
				}
				if ((null != jshxBzh.getWhpqylx()) && (0 < jshxBzh.getWhpqylx().trim().length())){
					paraMap.put("whpqylx", "%" + jshxBzh.getWhpqylx().trim() + "%");
				}
			}
			if (null != queryYxqStart && !"".equals(queryYxqStart)){
				setSessionAttribute("queryYxqStart", queryYxqStart);
				paraMap.put("startYxq", queryYxqStart);
			}

			if (null != queryYxqEnd && !"".equals(queryYxqEnd)){
				setSessionAttribute("queryYxqEnd", queryYxqEnd);
				paraMap.put("endYxq", queryYxqEnd);
			}
	        
			List<JshxBzh> list = jshxBzhService.findJshxBzh(paraMap);
			
			int num = 2;
			for(JshxBzh jshxBzh:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(jshxBzh.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(jshxBzh.getSzzname());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(null!=jshxBzh.getDbjb()?(String)companyService.findCompanyTypeNameByKey(jshxBzh.getDbjb(),"4028804840b9689c0140c43a1d6a0333"):"");
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(jshxBzh.getScore());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(jshxBzh.getYxq());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(jshxBzh.getFzrq());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(jshxBzh.getZsh());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(jshxBzh.getFzjg());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(null!=jshxBzh.getWhpqylx()?(String)companyService.findCompanyTypeNameByKey(jshxBzh.getWhpqylx(),"4028e56c40a9a6750140a9c91e2f0007"):"");
		        ce8.setCellStyle(c);
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

	public JshxBzh getJshxBzh(){
		return this.jshxBzh;
	}

	public void setJshxBzh(JshxBzh jshxBzh){
		this.jshxBzh = jshxBzh;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public JshxBzhService getJshxBzhService() {
		return jshxBzhService;
	}

	public void setJshxBzhService(JshxBzhService jshxBzhService) {
		this.jshxBzhService = jshxBzhService;
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

	public String getQueryYxqStart() {
		return queryYxqStart;
	}

	public void setQueryYxqStart(String queryYxqStart) {
		this.queryYxqStart = queryYxqStart;
	}

	public String getQueryYxqEnd() {
		return queryYxqEnd;
	}

	public void setQueryYxqEnd(String queryYxqEnd) {
		this.queryYxqEnd = queryYxqEnd;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
    
}
