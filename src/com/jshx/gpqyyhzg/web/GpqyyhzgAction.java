/**
 * Class Name: GpqyyhzgAction
 * Class Description：挂牌督办隐患整改管理
 */
package com.jshx.gpqyyhzg.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.gpqyyhzg.entity.Gpqyyhzg;
import com.jshx.gpqyyhzg.service.GpqyyhzgService;

public class GpqyyhzgAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Gpqyyhzg gpqyyhzg = new Gpqyyhzg();

	/**
	 * 业务类
	 */
	@Autowired
	private GpqyyhzgService gpqyyhzgService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * 统计开始月份
	 */
	private String queryTjyfStart;
	/**
	 * 统计结束月份
	 */
	private String queryTjyfEnd;
	/**
	 * 创建人id
	 */
	private String createUserID;
	
	/**
	 * 初始化挂牌企业隐患整改列表
	 * author：陆婷
	 * 2013-11-6
	 */
	public String init(){
		//根据用户角色判断权限
		createUserID = this.getLoginUserId();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "0";
		}
		return SUCCESS;
	}
	/**
	 * 查询挂牌企业隐患整改列表
	 * author：陆婷
	 * 2013-11-6
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gpqyyhzg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gpqyyhzg.getSzzid()) && (0 < gpqyyhzg.getSzzid().trim().length())){
				paraMap.put("szzid", gpqyyhzg.getSzzid().trim());
			}
		}
		if (null != queryTjyfStart && (0 < queryTjyfStart.trim().length())){
			paraMap.put("startTjyf", queryTjyfStart);
		}

		if (null != queryTjyfEnd && (0 < queryTjyfEnd.trim().length())){
			paraMap.put("endTjyf", queryTjyfEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
		}
		pagination = gpqyyhzgService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看挂牌企业隐患整改详情
	 * author：陆婷
	 * 2013-11-6
	 */
	public String view() throws Exception{
		if((null != gpqyyhzg)&&(null != gpqyyhzg.getId()))
		{
			gpqyyhzg = gpqyyhzgService.getById(gpqyyhzg.getId());
		}
		else
		{
			gpqyyhzg.setTbr(this.getLoginUser().getDisplayName());
		}
		
		return VIEW;
	}

	/**
	 * 初始化挂牌企业隐患整改修改信息
	 * author：陆婷
	 * 2013-11-6
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存挂牌企业隐患整改信息
	 * author：陆婷
	 * 2013-11-6
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			gpqyyhzg.setCreateTime(new Date());
			gpqyyhzg.setDeptId(this.getLoginUserDepartmentId());
			gpqyyhzg.setDelFlag(0);
			gpqyyhzg.setCreateUserID(this.getLoginUserId());
			gpqyyhzg.setSzzid(this.getLoginUserDepartment().getDeptCode());
			gpqyyhzg.setSzzname(this.getLoginUserDepartment().getDeptName());
			gpqyyhzgService.save(gpqyyhzg);
		}else{
			gpqyyhzgService.update(gpqyyhzg);
		}
		return RELOAD;
	}

	/**
	 * 删除挂牌企业隐患整改信息
	 * author：陆婷
	 * 2013-11-6
	 */
	public String delete() throws Exception{
	    try{
			gpqyyhzgService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 根据统计月份获取挂牌督办信息
	 * author：陆婷
	 * 2013-11-7
	 */
	public void queryData() throws IOException
	{
		try{
			if(gpqyyhzg != null && gpqyyhzg.getTjyf() != null && !"".equals(gpqyyhzg.getTjyf()))
			{
				Map map = new HashMap();
				map.put("tjyf", gpqyyhzg.getTjyf());
				map.put("szzid", this.getLoginUserDepartment().getDeptCode());
				gpqyyhzg = gpqyyhzgService.getGpdbByMap(map);
				JSONObject json = new JSONObject();
				json.put("sgps", toNum(gpqyyhzg.getSgps()));
				json.put("syzg", toNum(gpqyyhzg.getSyzg()));
				json.put("szgz", toNum(gpqyyhzg.getSzgz()));
				json.put("swzg", toNum(gpqyyhzg.getSwzg()));
				json.put("qgps", toNum(gpqyyhzg.getQgps()));
				json.put("qyzg", toNum(gpqyyhzg.getQyzg()));
				json.put("yzgz", toNum(gpqyyhzg.getYzgz()));
				json.put("qwzg", toNum(gpqyyhzg.getQwzg()));
				json.put("zgps", toNum(gpqyyhzg.getZgps()));
				json.put("zyzg", toNum(gpqyyhzg.getZyzg()));
				json.put("zzgz", toNum(gpqyyhzg.getZzgz()));
				json.put("zwzg", toNum(gpqyyhzg.getZwzg()));
				json.put("jcqys", toNum(gpqyyhzg.getJcqys()));
				json.put("fxyhs", toNum(gpqyyhzg.getFxyhs()));
				json.put("zgyhs", toNum(gpqyyhzg.getZgyhs()));
				PrintWriter out = getResponse().getWriter();
				out.print(json.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 将空字符串变为“0”
	 * author：陆婷
	 * 2013-11-7
	 */
	public String toNum(String s)
	{
		if(s == null || "".equals(s))
		{
			s = "0";
		}
		return s;
	}
	
	/**
	 * 导出挂牌企业隐患整改信息
	 * author：陆婷
	 * 2013-11-7
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=gpqyyhzg.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("挂牌企业隐患整改进度表");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 3000); 
	        sheet.setColumnWidth(2, 3000);
	        sheet.setColumnWidth(3, 3000);
	        sheet.setColumnWidth(4, 3000);
	        sheet.setColumnWidth(5, 3000);
	        sheet.setColumnWidth(6, 3000);
	        sheet.setColumnWidth(7, 3000);
	        sheet.setColumnWidth(8, 3000);
	        sheet.setColumnWidth(9, 3000);
	        sheet.setColumnWidth(10, 3000);
	        sheet.setColumnWidth(11, 3000);
	        sheet.setColumnWidth(12, 3000);
	        sheet.setColumnWidth(13, 3000);
	        sheet.setColumnWidth(14, 3000);
	        sheet.setColumnWidth(15, 3000);
	        sheet.setColumnWidth(16, 3000);
	        sheet.setColumnWidth(17, 3000);
	        sheet.setColumnWidth(18, 3000);
	        sheet.setColumnWidth(19, 3000);
	        sheet.setColumnWidth(20, 3000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("挂牌企业隐患整改进度汇总");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 20)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r = sheet.createRow(1);
	        r.setHeight((short)(23*20));
	        HSSFCell cel0 = r.createCell(0);
	        cel0.setCellValue("所在镇");
	        cel0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 3, (short) 0)); 
	        HSSFCell cel1 = r.createCell(1);
	        cel1.setCellValue("责任书签订情况");
	        cel1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 1, (short) 2)); 
	        HSSFCell cel2 = r.createCell(3);
	        cel2.setCellValue("挂牌督办完成情况");
	        cel2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 1, (short) 14)); 
	        HSSFCell cel3 = r.createCell(15);
	        cel3.setCellValue("检查情况");
	        cel3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 15, 1, (short) 17)); 
	        HSSFCell cel4 = r.createCell(18);
	        cel4.setCellValue("培训情况");
	        cel4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 18, 1, (short) 20));
	        
	        HSSFRow r2 = sheet.createRow(2);
	        r2.setHeight((short)(23*20));
	        HSSFCell cl0 = r2.createCell(1);
	        cl0.setCellValue("政府与村");
	        cl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 1, 3, (short) 1)); 
	        HSSFCell cl1 = r2.createCell(2);
	        cl1.setCellValue("村与企业");
	        cl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 2, 3, (short) 2)); 
	        HSSFCell cl2 = r2.createCell(3);
	        cl2.setCellValue("市");
	        cl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 3, 2, (short) 6)); 
	        HSSFCell cl3 = r2.createCell(7);
	        cl3.setCellValue("区");
	        cl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 7, 2, (short) 10)); 
	        HSSFCell cl4 = r2.createCell(11);
	        cl4.setCellValue("镇");
	        cl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 11, 2, (short) 14)); 
	        HSSFCell cl5 = r2.createCell(15);
	        cl5.setCellValue("检查企业数");
	        cl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 15, 3, (short) 15)); 
	        HSSFCell cl6 = r2.createCell(16);
	        cl6.setCellValue("发现隐患数");
	        cl6.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 16, 3, (short) 16)); 
	        HSSFCell cl7 = r2.createCell(17);
	        cl7.setCellValue("整改隐患数");
	        cl7.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 17, 3, (short) 17)); 
	        HSSFCell cl8 = r2.createCell(18);
	        cl8.setCellValue("年度目标(人)");
	        cl8.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 18, 3, (short) 18)); 
	        HSSFCell cl9 = r2.createCell(19);
	        cl9.setCellValue("完成人数(人)");
	        cl9.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 19, 3, (short) 19)); 
	        HSSFCell cl10 = r2.createCell(20);
	        cl10.setCellValue("完成率(%)");
	        cl10.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 20, 3, (short) 20)); 
	        
	        HSSFRow r3 = sheet.createRow(3);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(3);
	        ccl0.setCellValue("挂牌数");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(4);
	        ccl1.setCellValue("已整改");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(5);
	        ccl2.setCellValue("整改中");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(6);
	        ccl3.setCellValue("未整改");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(7);
	        ccl4.setCellValue("挂牌数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(8);
	        ccl5.setCellValue("已整改");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(9);
	        ccl6.setCellValue("整改中");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(10);
	        ccl7.setCellValue("未整改");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(11);
	        ccl8.setCellValue("挂牌数");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(12);
	        ccl9.setCellValue("已整改");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(13);
	        ccl10.setCellValue("整改中");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(14);
	        ccl11.setCellValue("未整改");
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
        		String szzid = (String) getSessionAttribute("szzid");
        		paraMap.put("szzid", szzid);
        		queryTjyfStart = (String) getSessionAttribute("queryTjyfStart");
        		queryTjyfEnd = (String) getSessionAttribute("queryTjyfEnd");
    		}
	        if(null != gpqyyhzg){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != gpqyyhzg.getSzzid()) && (0 < gpqyyhzg.getSzzid().trim().length())){
					paraMap.put("szzid", gpqyyhzg.getSzzid().trim());
					setSessionAttribute("szzid",gpqyyhzg.getSzzid().trim());
				}
			}
			if (null != queryTjyfStart && (0 < queryTjyfStart.trim().length())){
				paraMap.put("startTjyf", queryTjyfStart);
				setSessionAttribute("queryTjyfStart",queryTjyfStart);
			}

			if (null != queryTjyfEnd && (0 < queryTjyfEnd.trim().length())){
				paraMap.put("endTjyf", queryTjyfEnd);
				setSessionAttribute("queryTjyfEnd",queryTjyfEnd);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
			{
				paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
			}
	        List<Gpqyyhzg> list  = gpqyyhzgService.findGpqyyhzgAllListByMap(paraMap);
	        int num = 4;
	        for(int i=0;i<list.size();i++)
			{
	        	Gpqyyhzg gpqyyhzg = list.get(i);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(gpqyyhzg.getSzzname());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(gpqyyhzg.getZfyc());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(gpqyyhzg.getCyqy());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(gpqyyhzg.getSgps());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(gpqyyhzg.getSyzg());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(gpqyyhzg.getSzgz());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(gpqyyhzg.getSwzg());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(gpqyyhzg.getQgps());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(gpqyyhzg.getQyzg());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(gpqyyhzg.getYzgz());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(gpqyyhzg.getQwzg());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(gpqyyhzg.getZgps());
		        ce11.setCellStyle(c);
		        HSSFCell ce12 = ro.createCell(12);
		        ce12.setCellValue(gpqyyhzg.getZyzg());
		        ce12.setCellStyle(c);
		        HSSFCell ce13 = ro.createCell(13);
		        ce13.setCellValue(gpqyyhzg.getZzgz());
		        ce13.setCellStyle(c);
		        HSSFCell ce14 = ro.createCell(14);
		        ce14.setCellValue(gpqyyhzg.getZwzg());
		        ce14.setCellStyle(c);
		        HSSFCell ce15 = ro.createCell(15);
		        ce15.setCellValue(gpqyyhzg.getJcqys());
		        ce15.setCellStyle(c);
		        HSSFCell ce16 = ro.createCell(16);
		        ce16.setCellValue(gpqyyhzg.getFxyhs());
		        ce16.setCellStyle(c);
		        HSSFCell ce17 = ro.createCell(17);
		        ce17.setCellValue(gpqyyhzg.getZgyhs());
		        ce17.setCellStyle(c);
		        HSSFCell ce18 = ro.createCell(18);
		        ce18.setCellValue(gpqyyhzg.getNdmb());
		        ce18.setCellStyle(c);
		        HSSFCell ce19 = ro.createCell(19);
		        ce19.setCellValue(gpqyyhzg.getWcrs());
		        ce19.setCellStyle(c);
		        HSSFCell ce20 = ro.createCell(20);
		        ce20.setCellValue(gpqyyhzg.getWcl());
		        ce20.setCellStyle(c);
				num++;
			}
	        Gpqyyhzg gpqyyhzg  = gpqyyhzgService.findGpqyyhzgAllByMap(paraMap);
	        HSSFRow rr = sheet.createRow(num);
	        HSSFCell cell0 = rr.createCell(0);
	        cell0.setCellValue("合计");
	        cell0.setCellStyle(c);
	        HSSFCell cell1 = rr.createCell(1);
	        cell1.setCellValue(gpqyyhzg.getZfyc());
	        cell1.setCellStyle(c);
	        HSSFCell cell2 = rr.createCell(2);
	        cell2.setCellValue(gpqyyhzg.getCyqy());
	        cell2.setCellStyle(c);
	        HSSFCell cell3 = rr.createCell(3);
	        cell3.setCellValue(gpqyyhzg.getSgps());
	        cell3.setCellStyle(c);
	        HSSFCell cell4 = rr.createCell(4);
	        cell4.setCellValue(gpqyyhzg.getSyzg());
	        cell4.setCellStyle(c);
	        HSSFCell cell5 = rr.createCell(5);
	        cell5.setCellValue(gpqyyhzg.getSzgz());
	        cell5.setCellStyle(c);
	        HSSFCell cell6 = rr.createCell(6);
	        cell6.setCellValue(gpqyyhzg.getSwzg());
	        cell6.setCellStyle(c);
	        HSSFCell cell7 = rr.createCell(7);
	        cell7.setCellValue(gpqyyhzg.getQgps());
	        cell7.setCellStyle(c);
	        HSSFCell cell8 = rr.createCell(8);
	        cell8.setCellValue(gpqyyhzg.getQyzg());
	        cell8.setCellStyle(c);
	        HSSFCell cell9 = rr.createCell(9);
	        cell9.setCellValue(gpqyyhzg.getYzgz());
	        cell9.setCellStyle(c);
	        HSSFCell cell10 = rr.createCell(10);
	        cell10.setCellValue(gpqyyhzg.getQwzg());
	        cell10.setCellStyle(c);
	        HSSFCell cell11 = rr.createCell(11);
	        cell11.setCellValue(gpqyyhzg.getZgps());
	        cell11.setCellStyle(c);
	        HSSFCell cell12 = rr.createCell(12);
	        cell12.setCellValue(gpqyyhzg.getZyzg());
	        cell12.setCellStyle(c);
	        HSSFCell cell13 = rr.createCell(13);
	        cell13.setCellValue(gpqyyhzg.getZzgz());
	        cell13.setCellStyle(c);
	        HSSFCell cell14 = rr.createCell(14);
	        cell14.setCellValue(gpqyyhzg.getZwzg());
	        cell14.setCellStyle(c);
	        HSSFCell cell15 = rr.createCell(15);
	        cell15.setCellValue(gpqyyhzg.getJcqys());
	        cell15.setCellStyle(c);
	        HSSFCell cell16 = rr.createCell(16);
	        cell16.setCellValue(gpqyyhzg.getFxyhs());
	        cell16.setCellStyle(c);
	        HSSFCell cell17 = rr.createCell(17);
	        cell17.setCellValue(gpqyyhzg.getZgyhs());
	        cell17.setCellStyle(c);
	        HSSFCell cell18 = rr.createCell(18);
	        cell18.setCellValue(gpqyyhzg.getNdmb());
	        cell18.setCellStyle(c);
	        HSSFCell cell19 = rr.createCell(19);
	        cell19.setCellValue(gpqyyhzg.getWcrs());
	        cell19.setCellStyle(c);
	        HSSFCell cell20 = rr.createCell(20);
	        cell20.setCellValue(gpqyyhzg.getWcl());
	        cell20.setCellStyle(c);
	        
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

	public Gpqyyhzg getGpqyyhzg(){
		return this.gpqyyhzg;
	}

	public void setGpqyyhzg(Gpqyyhzg gpqyyhzg){
		this.gpqyyhzg = gpqyyhzg;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public String getQueryTjyfStart(){
		return this.queryTjyfStart;
	}

	public void setQueryTjyfStart(String queryTjyfStart){
		this.queryTjyfStart = queryTjyfStart;
	}

	public String getQueryTjyfEnd(){
		return this.queryTjyfEnd;
	}

	public void setQueryTjyfEnd(String queryTjyfEnd){
		this.queryTjyfEnd = queryTjyfEnd;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

}
