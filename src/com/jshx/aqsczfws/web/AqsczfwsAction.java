/**
 * Class Name: AqscxzcfAction
 * Class Description：安全生产行政执法文书使用情况
 */
package com.jshx.aqsczfws.web;

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

import com.jshx.aqsczfws.entity.Aqsczfws;
import com.jshx.aqsczfws.service.AqsczfwsService;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;
import com.jshx.aqsczfwsglb.service.AqsczfwsglbService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.UserRight;

public class AqsczfwsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Aqsczfws aqsczfws = new Aqsczfws();
	
	private Aqsczfwsglb aqsczfwsglb = new Aqsczfwsglb();

	/**
	 * 业务类
	 */
	@Autowired
	private AqsczfwsService aqsczfwsService;
	@Autowired
	private AqsczfwsglbService aqsczfwsglbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String createUserID;
	
	private Date queryTbrqStart;

	private Date queryTbrqEnd;
	
	private String[] aqsczfwstype = {"使用安全生产行政执法文书合计","立案审批表","询问通知书","询问笔录","勘验笔录",
		    "抽样取证凭证","先行登记保存证据审批表","先行登记保存证据通知书","先行登记保存证据处理审批表","先行登记保存证据处理决定书",
		    "现场处理措施决定书","鉴定委托书","行政处罚告知书","当事人陈述申辩笔录","听证告知书","听证会通知书","听证笔录",
		    "听证会报告书","案件处理呈批表","行政处罚集体讨论记录","行政（当场）处罚决定书（单位）","行政（当场）处罚决定书（个人）",
		   "行政处罚决定书（单位）","行政处罚决定书（个人）","罚款催缴通知书","延期（分期）缴纳罚款审批表","延期（分期）缴纳罚款批准书",
		   "文书送达回执","强制执行申请书","结案审批表","案件移送审批表","案件移送书"};
	private String jldwtype[] = {"份","份","份","份","份",
		    "份","份","份","份","份",
		    "份","份","份","份","份","份","份",
		    "份","份","份","份","份",
		   "份","份","份","份","份",
		   "份","份","份","份","份"
			};
	private List<Aqsczfwsglb> zfwslist = new ArrayList<Aqsczfwsglb>();
	
	private int totalSize;
	
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
	 * 查询安全生产行政执法文书使用情况列表
	 * author：陆婷
	 * 2013-11-12
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != aqsczfws){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != aqsczfws.getTbr()) && (0 < aqsczfws.getTbr().trim().length())){
				paraMap.put("tbr", "%" + aqsczfws.getTbr().trim() + "%");
			}
			
		}
		if (null != queryTbrqStart){
			paraMap.put("startTbrq", queryTbrqStart);
		}

		if (null != queryTbrqEnd){
			paraMap.put("endTbrq", queryTbrqEnd);
		}
		pagination = aqsczfwsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看安全生产行政执法文书使用情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String view() throws Exception{
		if((null != aqsczfws)&&(null != aqsczfws.getId()))
		{
			aqsczfws = aqsczfwsService.getById(aqsczfws.getId());
			Map map = new HashMap();
			map.put("proid", aqsczfws.getId());
			zfwslist = aqsczfwsglbService.findAqsczfwsglb(map);
		}
		else
		{
			aqsczfws.setTbr(this.getLoginUser().getDisplayName());
			aqsczfws.setTbrq(new Date());
			for(int i=0;i<aqsczfwstype.length;i++)
			{
				Aqsczfwsglb aqsczfwsglb = new Aqsczfwsglb();
				aqsczfwsglb.setXmrr(aqsczfwstype[i]);
				aqsczfwsglb.setJldw(jldwtype[i]);
				zfwslist.add(aqsczfwsglb);
			}
		}
		totalSize = zfwslist.size();
		return VIEW;
	}

	/**
	 * 初始化安全生产行政执法文书使用情况修改信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存安全生产行政执法文书使用情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			aqsczfws.setCreateTime(new Date());
			aqsczfws.setCreateUserID(this.getLoginUserId());
			aqsczfws.setDeptname(this.getLoginUserDepartment().getDeptName());
			aqsczfws.setDeptId(this.getLoginUserDepartmentId());
			aqsczfws.setDelFlag(0);
			aqsczfwsService.save(aqsczfws);
		}else{
			aqsczfwsService.update(aqsczfws);
			Map map = new HashMap();
			map.put("proid", aqsczfws.getId());
			aqsczfwsService.deleteAqsczfwsglbByMap(map);
		}
		String[] xmrrs = aqsczfwsglb.getXmrr().replaceAll(" ", "").split(",");
		String[] jldws = aqsczfwsglb.getJldw().replaceAll(" ", "").split(",");
		String[] hjs = aqsczfwsglb.getHj().replaceAll(" ", "").split(",");
		String[] wxps = aqsczfwsglb.getWxp().replaceAll(" ", "").split(",");
		String[] yhbzs = aqsczfwsglb.getYhbz().replaceAll(" ", "").split(",");
		String[] yjs = aqsczfwsglb.getYj().replaceAll(" ", "").split(",");
		String[] youses = aqsczfwsglb.getYouse().replaceAll(" ", "").split(",");
		String[] qts = aqsczfwsglb.getQt().replaceAll(" ", "").split(",");
		for(int i=0;i<xmrrs.length;i++)
		{
			Aqsczfwsglb aqsczfwsglb = new Aqsczfwsglb();
			aqsczfwsglb.setCreateTime(new Date());
			aqsczfwsglb.setCreateUserID(this.getLoginUserId());
			aqsczfwsglb.setDelFlag(0);
			aqsczfwsglb.setDeptId(this.getLoginUserDepartmentId());
			aqsczfwsglb.setXmrr(NullToString(xmrrs,i));
			aqsczfwsglb.setJldw(NullToString(jldws,i));
			aqsczfwsglb.setHj(NullToNum(hjs,i));
			aqsczfwsglb.setWxp(NullToNum(wxps,i));
			aqsczfwsglb.setYhbz(NullToNum(yhbzs,i));
			aqsczfwsglb.setYj(NullToNum(yjs,i));
			aqsczfwsglb.setYouse(NullToNum(youses,i));
			aqsczfwsglb.setQt(NullToNum(qts,i));
			aqsczfwsglb.setProid(aqsczfws.getId());
			aqsczfwsglbService.save(aqsczfwsglb);
		}
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
	 * 删除安全生产行政执法文书使用情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public String delete() throws Exception{
	    try{
			aqsczfwsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 导出安全生产行政执法文书使用情况信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=aqscxzzfws.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("安全生产行政执法文书使用情况");
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
	        cell.setCellValue("安全生产行政执法文书使用情况");
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
        		queryTbrqStart = (Date) getSessionAttribute("queryTbrqStart");
        		queryTbrqEnd = (Date) getSessionAttribute("queryTbrqEnd");
    		}
	        if(null != aqsczfws){
			    //设置查询条件，开发人员可以在此增加过滤条件

				if ((null != aqsczfws.getTbr()) && (0 < aqsczfws.getTbr().trim().length())){
					paraMap.put("tbr", "%" + aqsczfws.getTbr().trim() + "%");
					setSessionAttribute("tbr", "%" + aqsczfws.getTbr().trim() + "%");
				}
			}
			if (null != queryTbrqStart){
				paraMap.put("startTbrq", queryTbrqStart);
				setSessionAttribute("queryTbrqStart", queryTbrqStart);
			}

			if (null != queryTbrqEnd){
				paraMap.put("endTbrq", queryTbrqEnd);
				setSessionAttribute("queryTbrqEnd", queryTbrqEnd);
			}
			List<String> proid = aqsczfwsService.getAqsczfwsIdsByMap(paraMap);
			int num = 2;
			for(int i=0;i<aqsczfwstype.length;i++)
			{
				String xmrr = aqsczfwstype[i];
				Map map = new HashMap();
				map.put("proids", proid);
				map.put("xmrr", xmrr);
				Aqsczfwsglb aqsczfwsglb = aqsczfwsglbService.getAqsczfwsglbByMap(map);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(xmrr);
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(jldwtype[i]);
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(aqsczfwsglb.getHj());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(aqsczfwsglb.getWxp());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(aqsczfwsglb.getYhbz());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(aqsczfwsglb.getYj());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(aqsczfwsglb.getYouse());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(aqsczfwsglb.getQt());
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

	public Aqsczfws getAqsczfws(){
		return this.aqsczfws;
	}

	public void setAqsczfws(Aqsczfws aqsczfws){
		this.aqsczfws = aqsczfws;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public Date getQueryTbrqStart() {
		return queryTbrqStart;
	}

	public void setQueryTbrqStart(Date queryTbrqStart) {
		this.queryTbrqStart = queryTbrqStart;
	}

	public Date getQueryTbrqEnd() {
		return queryTbrqEnd;
	}

	public void setQueryTbrqEnd(Date queryTbrqEnd) {
		this.queryTbrqEnd = queryTbrqEnd;
	}

	public List<Aqsczfwsglb> getZfwslist() {
		return zfwslist;
	}

	public void setZfwslist(List<Aqsczfwsglb> zfwslist) {
		this.zfwslist = zfwslist;
	}

	public Aqsczfwsglb getAqsczfwsglb() {
		return aqsczfwsglb;
	}

	public void setAqsczfwsglb(Aqsczfwsglb aqsczfwsglb) {
		this.aqsczfwsglb = aqsczfwsglb;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
       
    
}
