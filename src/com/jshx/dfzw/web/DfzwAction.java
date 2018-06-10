/**
 * Class Name: DfzwAction
 * Class Description：打非治违
 */
package com.jshx.dfzw.web;

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

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.dfzw.entity.Dfzw;
import com.jshx.dfzw.service.DfzwService;
import com.jshx.dfzwglb.entity.Dfzwglb;
import com.jshx.dfzwglb.service.DfzwglbService;

public class DfzwAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Dfzw dfzw = new Dfzw();
	
	private Dfzwglb dfzwglb = new Dfzwglb();

	/**
	 * 业务类
	 */
	@Autowired
	private DfzwService dfzwService;
	@Autowired
	private DfzwglbService dfzwglbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private List<Dfzwglb> dfzwlist = new ArrayList<Dfzwglb>();

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private Date queryTbrqStart;

	private Date queryTbrqEnd;
	private String createUserID;
	
	
	private String[] dfzwtype = {"打击非法违法、治理纠正违规违章行为（合计）","无证、或证照不全等非法违法情况",
			"关闭取缔后又擅自生产的、应关闭或关闭不到位的","停产整顿、整改未经验收擅自生产及违反“三同时”规定的",
			"瞒报谎报事故及重大隐患不报或不按规定限期整治的","拒不执行监察指令抗拒执法的","非法用工、无证上岗的",
			"作业规程不完善、现场混乱等","安全生产工艺系统、劳动防护不符合规定要求等","隐患排查制度等五落实不到位的",
			"重大基础设施管理制度不完善不到位的","应急救援队伍等配备不足、使用培训不到位的",
			"新材料等未经安全检验核准投入使用的","其他违法安全生产非法违法生产经营建设等行为","组织检查组","组织检查人员",
			"受检企业","警告","责令改正、限期整改、停止违法行为","没收违法所得、非法生产设备","责令停产、停业、停止建设",
			"暂扣或吊销有关许可证、职业资格","关闭非法违法企业","行政拘留","移送追究刑事责任","处罚罚款"};
	/**
	 * 初始化打非治违列表
	 * author：陆婷
	 * 2013-10-31
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "0";
		}
		createUserID = this.getLoginUserId();
		return SUCCESS;
	}
	/**
	 * 查询打非治违列表
	 * author：陆婷
	 * 2013-10-31
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != dfzw){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dfzw.getSzzid()) && (0 < dfzw.getSzzid().trim().length())){
				paraMap.put("szzid", dfzw.getSzzid().trim());
			}

			if ((null != dfzw.getTbr()) && (0 < dfzw.getTbr().trim().length())){
				paraMap.put("tbr", "%" + dfzw.getTbr().trim() + "%");
			}
		}
		if (null != queryTbrqStart){
			paraMap.put("startTbrq", queryTbrqStart);
		}

		if (null != queryTbrqEnd){
			paraMap.put("endTbrq", queryTbrqEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
		}
		pagination = dfzwService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看打非治违详情
	 * author：陆婷
	 * 2013-10-31
	 */
	public String view() throws Exception{
		if((null != dfzw)&&(null != dfzw.getId()))
		{
			dfzw = dfzwService.getById(dfzw.getId());
			Map map = new HashMap();
			map.put("proid", dfzw.getId());
			dfzwlist = dfzwglbService.findDfzwglb(map);
		}
		else
		{
			dfzw.setSzzname(this.getLoginUserDepartment().getDeptName());
			dfzw.setTbr(this.getLoginUser().getDisplayName());
			dfzw.setTbsj(new Date());
			for(String s:dfzwtype)
			{
				Dfzwglb dfzwglb = new Dfzwglb();
				dfzwglb.setLinktype(s);
				dfzwlist.add(dfzwglb);
			}
		}
			
		return VIEW;
	}

	/**
	 * 初始化打非治违修改信息
	 * author：陆婷
	 * 2013-10-31
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存打非治违信息
	 * author：陆婷
	 * 2013-10-31
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			dfzw.setSzzid(this.getLoginUserDepartment().getDeptCode());
			dfzw.setDeptId(this.getLoginUserDepartmentId());
			dfzw.setDelFlag(0);
			dfzw.setCreateTime(new Date());
			dfzw.setCreateUserID(this.getLoginUserId());
			dfzwService.save(dfzw);
		}else{
			dfzwService.update(dfzw);
			Map map = new HashMap();
			map.put("proid", dfzw.getId());
			dfzwService.deleteDfzwglbByMap(map);
		}
		String[] linktype =  dfzwglb.getLinktype().replaceAll(" ", "").split(",");
		String[] num1 = dfzwglb.getNum1().replaceAll(" ", "").split(",");
		String[] num2 = dfzwglb.getNum2().replaceAll(" ", "").split(",");
		String[] num3 = dfzwglb.getNum3().replaceAll(" ", "").split(",");
		String[] num4 = dfzwglb.getNum4().replaceAll(" ", "").split(",");
		String[] num5 = dfzwglb.getNum5().replaceAll(" ", "").split(",");
		String[] num6 = dfzwglb.getNum6().replaceAll(" ", "").split(",");
		String[] num7 = dfzwglb.getNum7().replaceAll(" ", "").split(",");
		String[] num8 = dfzwglb.getNum8().replaceAll(" ", "").split(",");
		String[] num9 = dfzwglb.getNum9().replaceAll(" ", "").split(",");
		String[] num10 = dfzwglb.getNum10().replaceAll(" ", "").split(",");
		for(int i=0;i<linktype.length;i++)
		{
			Dfzwglb dfzwglb = new Dfzwglb();
			dfzwglb.setCreateTime(new Date());
			dfzwglb.setCreateUserID(this.getLoginUserId());
			dfzwglb.setDelFlag(0);
			dfzwglb.setDeptId(this.getLoginUserDepartmentId());
			dfzwglb.setLinktype(linktype[i]);
			dfzwglb.setNum1(NullToNum(num1,i));
			dfzwglb.setNum2(NullToNum(num2,i));
			dfzwglb.setNum3(NullToNum(num3,i));
			dfzwglb.setNum4(NullToNum(num4,i));
			dfzwglb.setNum5(NullToNum(num5,i));
			dfzwglb.setNum6(NullToNum(num6,i));
			dfzwglb.setNum7(NullToNum(num7,i));
			dfzwglb.setNum8(NullToNum(num8,i));
			dfzwglb.setNum9(NullToNum(num9,i));
			dfzwglb.setNum10(NullToNum(num10,i));
			dfzwglb.setProid(dfzw.getId());
			dfzwglbService.save(dfzwglb);
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

	/**
	 * 删除打非治违信息
	 * author：陆婷
	 *2013-10-31
	 */
	public String delete() throws Exception{
	    try{
			dfzwService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 导出打非治违信息
	 * author：陆婷
	 * 2013-11-8
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=dfzw.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("打非治违情况汇总");
		    sheet.setColumnWidth(0, 18000); 
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
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("打非治违情况汇总");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10)); 
	        
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
	        ccl0.setCellValue("本地区打击各类行业企业情况（单位：人、家、个、处、万元）");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("非煤矿山");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("道路交通");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("水上交通");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("建筑施工");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("消防");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("危化品");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("烟花爆竹");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("民爆物品");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("冶金");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("其他");
	        ccl10.setCellStyle(cs);
	        
	        
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
        		String tbr = (String) getSessionAttribute("tbr");
        		paraMap.put("szzid", szzid);
        		paraMap.put("tbr", tbr);
        		queryTbrqStart = (Date) getSessionAttribute("queryTbrqStart");
        		queryTbrqEnd = (Date) getSessionAttribute("queryTbrqEnd");
    		}
			if(null != dfzw){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != dfzw.getSzzid()) && (0 < dfzw.getSzzid().trim().length())){
					paraMap.put("szzid", dfzw.getSzzid().trim());
					setSessionAttribute("szzid", dfzw.getSzzid().trim());
				}

				if ((null != dfzw.getTbr()) && (0 < dfzw.getTbr().trim().length())){
					paraMap.put("tbr", "%" + dfzw.getTbr().trim() + "%");
					setSessionAttribute("tbr", "%" + dfzw.getTbr().trim() + "%");
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
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
			{
				paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
			}
			List<String> proid = dfzwService.getDfzwIdsByMap(paraMap);
			int num = 2;
			for(String linktype:dfzwtype)
			{
				Map map = new HashMap();
				map.put("proids", proid);
				map.put("linktype", linktype);
				Dfzwglb dfzwglb = dfzwglbService.getDfzwglbByMap(map);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(dfzwglb.getLinktype());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(dfzwglb.getNum1());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(dfzwglb.getNum2());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(dfzwglb.getNum3());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(dfzwglb.getNum4());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(dfzwglb.getNum5());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(dfzwglb.getNum6());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(dfzwglb.getNum7());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(dfzwglb.getNum8());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(dfzwglb.getNum9());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(dfzwglb.getNum10());
		        ce10.setCellStyle(c);
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

	public Dfzw getDfzw(){
		return this.dfzw;
	}

	public void setDfzw(Dfzw dfzw){
		this.dfzw = dfzw;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<Dfzwglb> getDfzwlist() {
		return dfzwlist;
	}

	public void setDfzwlist(List<Dfzwglb> dfzwlist) {
		this.dfzwlist = dfzwlist;
	}

	public Dfzwglb getDfzwglb() {
		return dfzwglb;
	}

	public void setDfzwglb(Dfzwglb dfzwglb) {
		this.dfzwglb = dfzwglb;
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
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
    
}
