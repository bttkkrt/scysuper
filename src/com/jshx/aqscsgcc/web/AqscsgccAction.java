/**
 * Class Name: AqscsgccAction
 * Class Description：生产安全事故查处情况
 */
package com.jshx.aqscsgcc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.aqscsgcc.entity.Aqscsgcc;
import com.jshx.aqscsgcc.service.AqscsgccService;
import com.jshx.aqscsgccglb.entity.Aqscsgccglb;
import com.jshx.aqscsgccglb.service.AqscsgccglbService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.UserRight;

public class AqscsgccAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Aqscsgcc aqscsgcc = new Aqscsgcc();
	private Aqscsgccglb aqscsgccglb = new Aqscsgccglb();

	/**
	 * 业务类
	 */
	@Autowired
	private AqscsgccService aqscsgccService;
	@Autowired
	private AqscsgccglbService aqscsgccglbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private String queryYfStart;

	private String queryYfEnd;

	private String createUserID;
	
	private List<Aqscsgccglb> list;
	
	private String xmnrtype[] = {"1.查处事故","其中：一般事故,","较大事故","重大事故",
							"2.应结案","其中：一般事故,","较大事故","重大事故",
							"3.实际结案","其中：一般事故,","较大事故","重大事故",
							"4.按期结案率","其中：一般事故,","较大事故","重大事故",
							"5.建议给予处分","其中：行政部门领导干部","生产经营单位主要负责人",
							" 6.建议给予党纪处分","其中：行政部门领导干部","生产经营单位主要负责人",
							"7.移送追究刑事责任","其中：行政部门领导干部","生产经营单位主要负责人"
							};
	
	
	private String jldwtype[] = {"起","起","起","起",
			"起","起","起","起",
			"起","起","起","起",
			"%","%","%","%",
			"人","人","人",
			"人","人","人",
			"人","人","人"
			};
	
	/**
	 * 初始化挂牌督办列表
	 * author：陆婷
	 * 2013-11-11
	 */
	public String init(){
		//根据用户角色判断权限
		createUserID = this.getLoginUserId();
		List<UserRight> list = this.getLoginUser().getUserRoles();
		flag = "0";
		for(UserRight u:list){
			String rolecode = u.getRole().getRoleCode();
			if(rolecode.equals(SysPropertiesUtil.getProperty("zfzddzRoleCode")) ||
			   rolecode.equals(SysPropertiesUtil.getProperty("zfzdbsyRoleCode")) ||
			   rolecode.equals(SysPropertiesUtil.getProperty("zfzdfdzRoleCode")) ){//执法支队（监察大队）人员
				flag = "1";
				break;
			}
		}
		return SUCCESS;
	}
	/**
	 * 查询生产安全事故查处情况列表
	 * author：陆婷
	 * 2013-11-12
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != aqscsgcc){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != aqscsgcc.getTbr()) && (0 < aqscsgcc.getTbr().trim().length())){
				paraMap.put("tbr", "%" + aqscsgcc.getTbr().trim() + "%");
			}
		}
		if (null != queryYfStart && (0 < queryYfStart.trim().length())){
			paraMap.put("startYf", queryYfStart);
		}

		if (null != queryYfEnd && (0 < queryYfEnd.trim().length())){
			paraMap.put("endYf", queryYfEnd);
		}
		pagination = aqscsgccService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看生产安全事故查处情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String view() throws Exception{
		if((null != aqscsgcc)&&(null != aqscsgcc.getId()))
		{
			aqscsgcc = aqscsgccService.getById(aqscsgcc.getId());
			Map map = new HashMap();
			map.put("proid", aqscsgcc.getId());
			list = aqscsgccglbService.findAqscsgccglb(map);
		}
		else
		{
			list = new ArrayList<Aqscsgccglb>();
			aqscsgcc.setTbr(this.getLoginUser().getDisplayName());
			for(int i=1;i<xmnrtype.length+1;i++)
			{
				String linktype = "";
				if(i<10)
				{
					linktype = "0" + i;
				}
				else
				{
					linktype = i+"";
				}
				Aqscsgccglb aqscsgccglb = new Aqscsgccglb();
				aqscsgccglb.setXmrr(xmnrtype[i-1]);
				aqscsgccglb.setLinktype(linktype);
				aqscsgccglb.setJldw(jldwtype[i-1]);
				list.add(aqscsgccglb);
			}
		}
		return VIEW;
	}

	/**
	 * 初始化生产安全事故查处情况修改信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存生产安全事故查处情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			aqscsgcc.setCreateTime(new Date());
			aqscsgcc.setCreateUserID(this.getLoginUserId());
			aqscsgcc.setDeptname(this.getLoginUserDepartment().getDeptName());
			aqscsgcc.setDeptId(this.getLoginUserDepartmentId());
			aqscsgcc.setDelFlag(0);
			aqscsgccService.save(aqscsgcc);
		}else{
			aqscsgccService.update(aqscsgcc);
			Map map = new HashMap();
			map.put("proid", aqscsgcc.getId());
			aqscsgccService.deleteAqscsgccglbByMap(map);
		}
		String[] xmrrs = aqscsgccglb.getXmrr().replaceAll(" ", "").split(",");
		String[] jldws = aqscsgccglb.getJldw().replaceAll(" ", "").split(",");
		String[] hjs = aqscsgccglb.getHj().replaceAll(" ", "").split(",");
		String[] wxps = aqscsgccglb.getWxp().replaceAll(" ", "").split(",");
		String[] yhbzs = aqscsgccglb.getYhbz().replaceAll(" ", "").split(",");
		String[] yjs = aqscsgccglb.getYj().replaceAll(" ", "").split(",");
		String[] youses = aqscsgccglb.getYouse().replaceAll(" ", "").split(",");
		String[] qts = aqscsgccglb.getQt().replaceAll(" ", "").split(",");
		String[] linktypes = aqscsgccglb.getLinktype().replaceAll(" ", "").split(",");
		for(int i=0;i<xmrrs.length;i++)
		{
			Aqscsgccglb aqscsgccglb = new Aqscsgccglb();
			aqscsgccglb.setCreateTime(new Date());
			aqscsgccglb.setCreateUserID(this.getLoginUserId());
			aqscsgccglb.setDelFlag(0);
			aqscsgccglb.setDeptId(this.getLoginUserDepartmentId());
			aqscsgccglb.setXmrr(NullToString(xmrrs,i));
			aqscsgccglb.setJldw(NullToString(jldws,i));
			aqscsgccglb.setHj(NullToNum(hjs,i));
			aqscsgccglb.setWxp(NullToNum(wxps,i));
			aqscsgccglb.setYhbz(NullToNum(yhbzs,i));
			aqscsgccglb.setYj(NullToNum(yjs,i));
			aqscsgccglb.setYouse(NullToNum(youses,i));
			aqscsgccglb.setQt(NullToNum(qts,i));
			aqscsgccglb.setLinktype(NullToString(linktypes,i));
			aqscsgccglb.setProid(aqscsgcc.getId());
			aqscsgccglbService.save(aqscsgccglb);
		}
		flag = "1";
		return RELOAD;
	}

	public String NullToNum(String[] s,int i)
	{
		String ss = "";
		if(i >= s.length || s[i] == null || "".equals(s[i]))
		{
			ss = "0";
		}
		else
		{
			ss = s[i];
		}
		return ss;
	}
	
	public String NullToString(String[] s,int i)
	{
		String ss = "";
		if(i >= s.length || s[i] == null)
		{
			ss = "";
		}
		else
		{
			ss = s[i];
		}
		return ss;
	}
	/**
	 * 删除生产安全事故查处情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String delete() throws Exception{
	    try{
			aqscsgccService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 导出生产安全事故查处情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=scaqsgcc.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("生产安全事故查处情况");
		    sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("生产安全事故查处情况");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 7)); 
	        
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
	        ccl0.setCellValue("项目内容");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("计量单位");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("合计");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("危险化学品");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("烟花爆竹");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("冶金");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("有色");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("其它");
	        ccl7.setCellStyle(cs);
	        
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
        		String tbr = (String) getSessionAttribute("tbr");
        		paraMap.put("tbr", tbr);
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
    		}
	        if(null != aqscsgcc){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != aqscsgcc.getTbr()) && (0 < aqscsgcc.getTbr().trim().length())){
					paraMap.put("tbr", "%" + aqscsgcc.getTbr().trim() + "%");
					setSessionAttribute("tbr", "%" + aqscsgcc.getTbr().trim() + "%");
				}
			}
			if (null != queryYfStart && (0 < queryYfStart.trim().length())){
				paraMap.put("startYf", queryYfStart);
				setSessionAttribute("queryYfStart", queryYfStart);
			}

			if (null != queryYfEnd && (0 < queryYfEnd.trim().length())){
				paraMap.put("endYf", queryYfEnd);
				setSessionAttribute("queryYfEnd", queryYfEnd);
			}
			List<String> proid = aqscsgccService.getAqscsgccIdsByMap(paraMap);
			int num = 2;
			for(int i=0;i<xmnrtype.length;i++)
			{
				String linktype = "";
				int a = i + 1;
				if(a < 10)
				{
					linktype = "0" + a;
				}
				else
				{
					linktype = "" + a;
				}
				String xmrr = xmnrtype[i];
				Map map = new HashMap();
				map.put("proids", proid);
				map.put("linktype",linktype);
				Aqscsgccglb aqscsgccglb = aqscsgccglbService.getAqscsgccglbByMap(map);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(xmrr);
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(jldwtype[i]);
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(aqscsgccglb.getHj());
		        ce2.setCellStyle(c);
		        
		        HSSFCell ce3 = ro.createCell(3);
		        if(linktype.equals("13") || linktype.equals("16") || linktype.equals("19"))
				{
		        	ce3.setCellValue("乡科级");
				}
		        else
		        {
		        	ce3.setCellValue(aqscsgccglb.getWxp());
		        }
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        if(linktype.equals("13") || linktype.equals("16") || linktype.equals("19"))
				{
		        	ce4.setCellValue("其它");
		        	sheet.addMergedRegion(new Region(num, (short) 4, num, (short) 5)); 
				}
		        else if(linktype.equals("14") || linktype.equals("17") || linktype.equals("20"))
		        {
		        	ce4.setCellValue(aqscsgccglb.getYhbz());
		        	sheet.addMergedRegion(new Region(num, (short) 4, num, (short) 5)); 
		        }
		        else
		        {
		        	ce4.setCellValue(aqscsgccglb.getYhbz());
		        }
		        ce4.setCellStyle(c);
		        
		        HSSFCell ce5 = ro.createCell(5);
		        if(!linktype.equals("13") && !linktype.equals("16") && !linktype.equals("19") && !linktype.equals("14") && !linktype.equals("17") && !linktype.equals("20"))
		        {
		        	ce5.setCellValue(aqscsgccglb.getYj());
		        }
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        if(linktype.equals("13") || linktype.equals("16") || linktype.equals("19"))
				{
		        	ce6.setCellValue("");
		        	sheet.addMergedRegion(new Region(num, (short) 4, num, (short) 5)); 
				}
		        else if(linktype.equals("14") || linktype.equals("17") || linktype.equals("20"))
		        {
		        	ce6.setCellValue("");
		        	sheet.addMergedRegion(new Region(num, (short) 4, num, (short) 5)); 
		        }
		        else
		        {
		        	ce6.setCellValue(aqscsgccglb.getYouse());
		        }
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        if(!linktype.equals("13") && !linktype.equals("16") && !linktype.equals("19") && !linktype.equals("14") && !linktype.equals("17") && !linktype.equals("20"))
		        {
		        	ce7.setCellValue(aqscsgccglb.getQt());
		        }
		        ce7.setCellStyle(c);
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

	public Aqscsgcc getAqscsgcc(){
		return this.aqscsgcc;
	}

	public void setAqscsgcc(Aqscsgcc aqscsgcc){
		this.aqscsgcc = aqscsgcc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
	public String getQueryYfStart(){
		return this.queryYfStart;
	}

	public void setQueryYfStart(String queryYfStart){
		this.queryYfStart = queryYfStart;
	}

	public String getQueryYfEnd(){
		return this.queryYfEnd;
	}

	public void setQueryYfEnd(String queryYfEnd){
		this.queryYfEnd = queryYfEnd;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	public Aqscsgccglb getAqscsgccglb() {
		return aqscsgccglb;
	}
	public void setAqscsgccglb(Aqscsgccglb aqscsgccglb) {
		this.aqscsgccglb = aqscsgccglb;
	}
	public List<Aqscsgccglb> getList() {
		return list;
	}
	public void setList(List<Aqscsgccglb> list) {
		this.list = list;
	}

}
