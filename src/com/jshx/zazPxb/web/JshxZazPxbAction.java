/**
 * Class Name: JshxZazPxbAction
 * Class Description：主要负责人、安全管理员安全培训和职业卫生培训
 */
package com.jshx.zazPxb.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.zazPxb.entity.JshxPx;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zazPxb.service.JshxZazPxbService;

public class JshxZazPxbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZazPxb jshxZazPxb = new JshxZazPxb();
	
	List<JshxZazPxb> zazPxbs = new ArrayList<JshxZazPxb>();
	
	List<JshxPx> pxlist = new ArrayList<JshxPx>();
	

	public List<JshxZazPxb> getZazPxbs() {
		return zazPxbs;
	}
	public void setZazPxbs(List<JshxZazPxb> zazPxbs) {
		this.zazPxbs = zazPxbs;
	}
	/**
	 * 业务类
	 */
	@Autowired
	private JshxZazPxbService jshxZazPxbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HttpService httpService;

	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	//培训开始时间
	private String queryPxsjStart;
	//培训结束时间
	private String queryPxsjEnd;
	
	/**
	 * 初始化主要负责人、安全管理员安全培训和职业卫生培训列表
	 * author：陆婷
	 * 2013-08-17
	 * 修改 新增权限由企业变为培训中心 2013-09-18 by 陆婷
	 */
	public String init(){
		//根据用户角色判断权限
		List<UserRight> list = this.getLoginUser().getUserRoles();
		flag = "2";
		for(UserRight u:list){
			String rolecode = u.getRole().getRoleCode();
			if(rolecode.equals(SysPropertiesUtil.getProperty("pxzxRoleCode"))){//培训中心人员
				flag = "1";
				break;
			} 
		}
		return SUCCESS;
	}
	/**
	 * 初始化主要负责人、安全管理员安全培训和职业卫生培训列表（每个企业为1条记录）
	 * author：陆婷
	 * 2013-09-18
	 */
	public String inits()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (null != queryPxsjStart && !"".equals(queryPxsjStart)){
			paraMap.put("startPxsj", queryPxsjStart);
		}

		if (null != queryPxsjEnd && !"".equals(queryPxsjEnd)){
			paraMap.put("endPxsj", queryPxsjEnd);
		}
		pxlist = jshxZazPxbService.getJshxPxListByMap(paraMap);
		JshxPx jshxPx = jshxZazPxbService.getTotalJshxPxListByMap(paraMap);
		jshxPx.setSzzname("合计");
		pxlist.add(jshxPx);
		return SUCCESS;
	}
	/**
	 * 查询主要负责人、安全管理员安全培训和职业卫生培训列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZazPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZazPxb.getSzzname()) && (0 < jshxZazPxb.getSzzname().trim().length())){
				paraMap.put("szzname", jshxZazPxb.getSzzname().trim() );
			}
			if ((null != jshxZazPxb.getQymc()) && (0 < jshxZazPxb.getQymc().trim().length())){
				paraMap.put("qymc", "%"+jshxZazPxb.getQymc().trim()+"%");
			}
			if ((null != jshxZazPxb.getPersonName()) && (0 < jshxZazPxb.getPersonName().trim().length())){
				paraMap.put("personName", "%" + jshxZazPxb.getPersonName().trim() + "%");
			}
			if ((null != jshxZazPxb.getZw()) && (0 < jshxZazPxb.getZw().trim().length())){
				paraMap.put("zw", jshxZazPxb.getZw().trim());
			}
			if ((null != jshxZazPxb.getXl() )&& (0 < jshxZazPxb.getXl().trim().length())){
				paraMap.put("xl",jshxZazPxb.getXl().trim());
			}
			if ((null != jshxZazPxb.getSfz() )&& (0 < jshxZazPxb.getSfz().trim().length())){
				paraMap.put("sfz","%" + jshxZazPxb.getSfz().trim()+"%");
			}

			if ((null != jshxZazPxb.getPxzh()) && (0 < jshxZazPxb.getPxzh().trim().length())){
				paraMap.put("pxzh", "%" + jshxZazPxb.getPxzh().trim() + "%");
			}
			if ((null != jshxZazPxb.getWhpqylx() )&& (0 < jshxZazPxb.getWhpqylx().trim().length())){
				paraMap.put("whpqylx","%"+jshxZazPxb.getWhpqylx().trim()+"%");
			}
			
		}
		if (null != queryPxsjStart && !"".equals(queryPxsjStart)){
			paraMap.put("startPxsj", queryPxsjStart);
		}

		if (null != queryPxsjEnd && !"".equals(queryPxsjEnd)){
			paraMap.put("endPxsj", queryPxsjEnd);
		}
		pagination = jshxZazPxbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 查询主要负责人、安全管理员安全培训和职业卫生培训列表（每个企业为1条记录）
	 * author：陆婷
	 * 2013-09-18
	 */
//	public void lists() throws Exception{
//		Map<String, Object> paraMap = new HashMap<String, Object>();
//
//		if(pagination==null)
//		    pagination = new Pagination(this.getRequest());
//		    
//		if(null != jshxZazPxb){
//		    //设置查询条件，开发人员可以在此增加过滤条件
//			if ((null != jshxZazPxb.getQymc()) && (0 < jshxZazPxb.getQymc().trim().length())){
//				paraMap.put("qymc", "%" + jshxZazPxb.getQymc().trim() + "%");
//			}
//		}
//		List<JshxZazPxb> jshxZazPxbList = new ArrayList<JshxZazPxb>();
//		jshxZazPxbList = jshxZazPxbService.getJshxZazPxbListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
//		int size = jshxZazPxbService.getJshxZazPxbListSizeByMap(paraMap);
//		pagination.setTotalCount(size);
//		pagination.setList(jshxZazPxbList);
//		pagination = jshxZazPxbService.findByPages(pagination, paraMap);
//		
//		convObjectToJson(pagination,null);
//	}

	/**
	 * 查看主要负责人、安全管理员安全培训和职业卫生培训详情
	 * author：陆婷
	 * 2013-08-17
	 */
	public String view() throws Exception{
		if((null != jshxZazPxb)&&(null != jshxZazPxb.getId()))
			jshxZazPxb = jshxZazPxbService.getById(jshxZazPxb.getId());
		
		return VIEW;
	}

	/**
	 * 初始化主要负责人、安全管理员安全培训和职业卫生培训修改信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}
	
	/**
	 * 保存主要负责人、安全管理员安全培训和职业卫生培训信息（添加）
	 * author：陆婷
	 * 2013-08-17
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			
			jshxZazPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxZazPxb.setDelFlag(0);
			jshxZazPxb.setCreateUserID(this.getLoginUserId());
			jshxZazPxb.setCreateTime(new Date());
			jshxZazPxbService.save(jshxZazPxb);
		}else{
			jshxZazPxbService.update(jshxZazPxb);
		}
		return RELOAD;
	}
	/**
	 * 保存主要负责人、安全管理员安全培训和职业卫生培训信息（继续添加）
	 * author：陆婷
	 * 2013-09-18
	 */
	public String saves() throws Exception{
		try {
			jshxZazPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxZazPxb.setDelFlag(0);
			jshxZazPxb.setCreateUserID(this.getLoginUserId());
			jshxZazPxb.setCreateTime(new Date());
			jshxZazPxbService.save(jshxZazPxb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 删除主要负责人、安全管理员安全培训和职业卫生培训信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String delete() throws Exception{
	    try{
			jshxZazPxbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 删除信息（每个企业为1条信息）
	 * author：陆婷
	 * 2013-09-18
	 */
//	public String deletes() throws Exception{
//	    try{
//	    	String[] idArray = ids.split("\\|");
//			if(null != idArray)
//			{
//				for(String id : idArray)
//				{
//				    if(id!=null && !id.trim().equals(""))
//				    {
//				    	Map map = new HashMap();
//				    	map.put("qyid", id);
//				    	jshxZazPxbService.deleteByQyid(map);
//				    }
//				}
//			}
//			this.getResponse().getWriter().println("{\"result\":\"true\"}");
//		}catch(Exception e){
//			this.getResponse().getWriter().println("{\"result\":\"false\"}");
//		}
//		return null;
//	}
	public void saveExcel() throws Exception{
		if(!Filedata.isEmpty()){
			File file = Filedata.get(0);
	        Workbook wb = null;  
	        try {  
	            // 构造Workbook（工作薄）对象  
	            wb = Workbook.getWorkbook(file);  
	        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
	        Sheet sheet = wb.getSheet(0);  
	  
	            // 对每个工作表进行循环  
	                // 得到当前工作表的行数  
	                int rowNum = sheet.getRows();  
	                for (int j = 3; j < rowNum; j++) {
	                    // 得到当前行的所有单元格  
	                    Cell[] cells = sheet.getRow(j);  
	                    if (cells != null && cells.length >=15) { 
	                        // 对每个单元格进行循环  
	                    	//验证姓名为必添香
	                    	if(cells[1].getContents()!=null&&!"".equals(cells[1].getContents())){
	                    		 JshxZazPxb zp = new JshxZazPxb();
		                       		
	 	                        zp.setDeptId(this.getLoginUserDepartmentId());
	 		                    zp.setDelFlag(0);
	 		                    zp.setCreateUserID(this.getLoginUserId());
	 		                    zp.setCreateTime(new Date());
	 		                    
	 		                    zp.setPersonName(cells[1].getContents());//姓名
	 		                    zp.setSex(cells[2].getContents());//性别
	 		                    zp.setZw(cells[3].getContents());//职务
	 		                    zp.setXl(cells[4].getContents());//学历
	 		                    zp.setLxfs(cells[5].getContents());//联系方式
	 		                    zp.setSfz(cells[6].getContents());//身份证号
	                        		zp.setQymc(cells[7].getContents());//企业名称
	 		                    zp.setAddress(cells[8].getContents());//地址
	 		                    
	 		                    String a = "";
	 		                    if(cells[9].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[9]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
								     df.setTimeZone(tz) ; 
								     a = df.format(c00.getDate());
								}
								else
								{
									a = cells[9].getContents().replaceAll("/", "-");
		 		                    String[] sj = a.split("-");
		 		                    if(sj.length == 3)
		 		                    {
		 		                    	String s1 = sj[1];
		 		                    	if(s1.length() == 1)
		 		                    	{
		 		                    		s1 = "0" + s1;
		 		                    	}
		 			                    String s2 = sj[2];
		 			                    if(s2.length() == 1)
		 			                    {
		 			                    	s2 = "0" + s2;
		 			                    }
		 			                    a = sj[0] + "-" + s1 + "-" + s2;
		 		                    }
								}
	 		                    
	 		                    zp.setCpsj(a);//初培时间
	 		                    zp.setPxzh(cells[10].getContents());//资格证号
	 		                    zp.setKscj(cells[11].getContents());//考试成绩
	 		                    zp.setBz(cells[12].getContents());//备注
	 		                    zp.setSzzname(cells[13].getContents());//所在镇
	 		                    zp.setWhpqylx(cells[14].getContents());//危化品企业类型
	 		                    zazPxbs.add(zp);
	                    	}
                       	}
	                }
	                 //保存企业培训人员信息
	                jshxZazPxbService.saveZazPxbs(zazPxbs);
	        // 最后关闭资源，释放内存  
	        wb.close(); 
	        this.getResponse().getWriter().println("{\"result\":\"true\"}");
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	            this.getResponse().getWriter().println("{\"result\":\"false\"}");
	        }
		}
	}
	
	public void export()
	{
		try {
			if(flag == null || "".equals(flag))
			{
				queryPxsjStart = (String)getSessionAttribute("queryPxsjStart");
				queryPxsjEnd = (String)getSessionAttribute("queryPxsjEnd");
			}
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if (null != queryPxsjStart && !"".equals(queryPxsjStart)){
				setSessionAttribute("queryPxsjStart", queryPxsjStart);
				paraMap.put("startPxsj", queryPxsjStart);
			}

			if (null != queryPxsjEnd && !"".equals(queryPxsjEnd)){
				setSessionAttribute("queryPxsjEnd", queryPxsjEnd);
				paraMap.put("endPxsj", queryPxsjEnd);
			}
			pxlist = jshxZazPxbService.getJshxPxListByMap(paraMap);
			JshxPx jshxPx = jshxZazPxbService.getTotalJshxPxListByMap(paraMap);
			jshxPx.setSzzname("合计");
			pxlist.add(jshxPx);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=pxtj.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "pxtj.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        
	        int num = 3;
	        for(int i=0;i<pxlist.size();i++)
	        {
	        	JshxPx jshxpx = pxlist.get(i);
	        	HSSFRow row0 = sheet.getRow(num);
				HSSFCell cel1 = row0.getCell(1);
				cel1.setCellValue(jshxpx.getSzzname());
				HSSFCell cel2 = row0.getCell(2);
				cel2.setCellValue(jshxpx.getData1());
				HSSFCell cel3 = row0.getCell(3);
				cel3.setCellValue(jshxpx.getData2());
				HSSFCell cel4 = row0.getCell(4);
				cel4.setCellValue(jshxpx.getData3());
				HSSFCell cel5 = row0.getCell(5);
				cel5.setCellValue(jshxpx.getData4());
				HSSFCell cel6 = row0.getCell(6);
				cel6.setCellValue(jshxpx.getData5());
				HSSFCell cel7 = row0.getCell(7);
				cel7.setCellValue(jshxpx.getData6());
				HSSFCell cel8 = row0.getCell(8);
				cel8.setCellValue(jshxpx.getData7());
				HSSFCell cel9 = row0.getCell(9);
				cel9.setCellValue(jshxpx.getData8());
				HSSFCell cel10 = row0.getCell(10);
				cel10.setCellValue(jshxpx.getData9());
				HSSFCell cel11 = row0.getCell(11);
				cel11.setCellValue(jshxpx.getData10());
				HSSFCell cel12 = row0.getCell(12);
				cel12.setCellValue(jshxpx.getData11());
				HSSFCell cel13 = row0.getCell(13);
				cel13.setCellValue(jshxpx.getData12());
				HSSFCell cel14 = row0.getCell(14);
				cel14.setCellValue(jshxpx.getData13());
				HSSFCell cel15 = row0.getCell(15);
				cel15.setCellValue(jshxpx.getData14());
				HSSFCell cel16 = row0.getCell(16);
				cel16.setCellValue(jshxpx.getData15());
				HSSFCell cel17 = row0.getCell(17);
				cel17.setCellValue(jshxpx.getData16());
				HSSFCell cel18 = row0.getCell(18);
				cel18.setCellValue(jshxpx.getData17());
				HSSFCell cel19 = row0.getCell(19);
				cel19.setCellValue(jshxpx.getData18());
				HSSFCell cel20 = row0.getCell(20);
				cel20.setCellValue(jshxpx.getData19());
				HSSFCell cel21 = row0.getCell(21);
				cel21.setCellValue(jshxpx.getData20());
				HSSFCell cel22 = row0.getCell(22);
				cel22.setCellValue(jshxpx.getData21());
				HSSFCell cel23 = row0.getCell(23);
				cel23.setCellValue(jshxpx.getData22());
				HSSFCell cel24 = row0.getCell(24);
				cel24.setCellValue(jshxpx.getData23());
				HSSFCell cel25 = row0.getCell(25);
				cel25.setCellValue(jshxpx.getData24());
				HSSFCell cel26 = row0.getCell(26);
				cel26.setCellValue(jshxpx.getData25());
				HSSFCell cel27 = row0.getCell(27);
				cel27.setCellValue(jshxpx.getData26());
				HSSFCell cel28 = row0.getCell(28);
				cel28.setCellValue(jshxpx.getData27());
				HSSFCell cel29 = row0.getCell(29);
				cel29.setCellValue(jshxpx.getData28());
				HSSFCell cel30 = row0.getCell(30);
				cel30.setCellValue(jshxpx.getData29());
				HSSFCell cel31 = row0.getCell(31);
				cel31.setCellValue(jshxpx.getData30());
				HSSFCell cel32 = row0.getCell(32);
				cel32.setCellValue(jshxpx.getData31());
				HSSFCell cel33 = row0.getCell(33);
				cel33.setCellValue(jshxpx.getData32());
				HSSFCell cel34 = row0.getCell(34);
				cel34.setCellValue(jshxpx.getData33());
				HSSFCell cel35 = row0.getCell(35);
				cel35.setCellValue(jshxpx.getData34());
				num ++;
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

	public JshxZazPxb getJshxZazPxb(){
		return this.jshxZazPxb;
	}

	public void setJshxZazPxb(JshxZazPxb jshxZazPxb){
		this.jshxZazPxb = jshxZazPxb;
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
	public String getQueryPxsjStart() {
		return queryPxsjStart;
	}
	public void setQueryPxsjStart(String queryPxsjStart) {
		this.queryPxsjStart = queryPxsjStart;
	}
	public String getQueryPxsjEnd() {
		return queryPxsjEnd;
	}
	public void setQueryPxsjEnd(String queryPxsjEnd) {
		this.queryPxsjEnd = queryPxsjEnd;
	}
	public List<JshxPx> getPxlist() {
		return pxlist;
	}
	public void setPxlist(List<JshxPx> pxlist) {
		this.pxlist = pxlist;
	}
	

}
