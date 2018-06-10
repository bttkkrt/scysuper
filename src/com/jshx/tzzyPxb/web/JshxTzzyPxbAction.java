/**
 * Class Name: JshxTzzyPxbAction
 * Class Description：特种作业培训
 */
package com.jshx.tzzyPxb.web;

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.tzzyPxb.service.JshxTzzyPxbService;
import com.jshx.utils.StringTools;

public class JshxTzzyPxbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxTzzyPxb jshxTzzyPxb = new JshxTzzyPxb();
	
	private List<JshxTzzyPxb> tzzyPxbs = new ArrayList<JshxTzzyPxb>();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxTzzyPxbService jshxTzzyPxbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HttpService httpService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	//培训开始时间
	private String queryPxsjStart;
	//培训结束时间
	private String queryPxsjEnd;
	//有效期开始时间
	private String queryYxqStart;
	//有效期结束时间
	private String queryYxqEnd;
	//有效期开始时间
	private String queryLzrqStart;
	//有效期结束时间
	private String queryLzrqEnd;

	/**
	 * 初始化特种作业人员培训列表
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
	 * 初始化特种作业人员培训列表（每个企业为1条记录）
	 * author：陆婷
	 * 2013-09-18
	 */
//	public String inits()
//	{
//		String deptCode = this.getLoginUserDepartment().getDeptCode();
//		if(deptCode.startsWith("002007"))
//		{
//			flag = "1";
//		}
//		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
//		{
//			flag = "2";
//		}
//		else
//		{
//			flag = "3";
//		}
//		return SUCCESS;
//	}
	/**
	 * 查询特种作业人员培训列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxTzzyPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != jshxTzzyPxb.getSzzname()) && (0 < jshxTzzyPxb.getSzzname().trim().length())){
				paraMap.put("szzname", jshxTzzyPxb.getSzzname().trim());
			}
			if ((null != jshxTzzyPxb.getQymc()) && (0 < jshxTzzyPxb.getQymc().trim().length())){
				paraMap.put("qymc", "%"+jshxTzzyPxb.getQymc().trim()+"%");
			}
			if ((null != jshxTzzyPxb.getPersonName()) && (0 < jshxTzzyPxb.getPersonName().trim().length())){
				paraMap.put("personName", "%" + jshxTzzyPxb.getPersonName().trim() + "%");
			}
			if ((null != jshxTzzyPxb.getXl()) && (0 < jshxTzzyPxb.getXl().trim().length())){
				paraMap.put("xl", jshxTzzyPxb.getXl().trim());
			}
			if ((null != jshxTzzyPxb.getTzzh()) && (0 < jshxTzzyPxb.getTzzh().trim().length())){
				paraMap.put("tzzh", "%" + jshxTzzyPxb.getTzzh().trim() + "%");
			}
			if ((null != jshxTzzyPxb.getSfz()) && (0 < jshxTzzyPxb.getSfz().trim().length())){
				paraMap.put("sfz", "%" + jshxTzzyPxb.getSfz().trim() + "%");
			}
			if ((null != jshxTzzyPxb.getGz()) && (0 < jshxTzzyPxb.getGz().trim().length()) && !"0".equals(jshxTzzyPxb.getGz().trim())){
				paraMap.put("gz", jshxTzzyPxb.getGz().trim());
			}
			if ((null != jshxTzzyPxb.getGzxl()) && (0 < jshxTzzyPxb.getGzxl().trim().length()) && !"0".equals(jshxTzzyPxb.getGzxl().trim())){
				paraMap.put("gzxl", jshxTzzyPxb.getGzxl().trim());
			}
			if ((null != jshxTzzyPxb.getQylx()) && (0 < jshxTzzyPxb.getQylx().trim().length())){
				paraMap.put("qylx", jshxTzzyPxb.getQylx().trim());
			}
		}
		if (null != queryPxsjStart && !"".equals(queryPxsjStart)){
			paraMap.put("startPxsj", queryPxsjStart);
		}

		if (null != queryPxsjEnd && !"".equals(queryPxsjEnd)){
			paraMap.put("endPxsj", queryPxsjEnd);
		}
		
		if (null != queryYxqStart && !"".equals(queryYxqStart)){
			paraMap.put("startYxq", queryYxqStart);
		}

		if (null != queryYxqEnd && !"".equals(queryYxqEnd)){
			paraMap.put("endYxq", queryYxqEnd);
		}
		
		if (null != queryLzrqStart && !"".equals(queryLzrqStart)){
			paraMap.put("startLzrq", queryLzrqStart);
		}

		if (null != queryLzrqEnd && !"".equals(queryLzrqEnd)){
			paraMap.put("endLzrq", queryLzrqEnd);
		}
		pagination = jshxTzzyPxbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	/**
	 * 查询特种作业人员培训列表（每个企业为1条记录）
	 * author：陆婷
	 * 2013-09-18
	 */
//	public void lists() throws Exception{
//		Map<String, Object> paraMap = new HashMap<String, Object>();
//
//		if(pagination==null)
//		    pagination = new Pagination(this.getRequest());
//		    
//		if(null != jshxTzzyPxb){
//		    //设置查询条件，开发人员可以在此增加过滤条件
//
//			if ((null != jshxTzzyPxb.getQymc()) && (0 < jshxTzzyPxb.getQymc().trim().length())){
//				paraMap.put("qymc", "%" + jshxTzzyPxb.getQymc().trim() + "%");
//			}
//		}
//		List<JshxTzzyPxb> jshxTzzyPxbList = new ArrayList<JshxTzzyPxb>();
//		jshxTzzyPxbList = jshxTzzyPxbService.getJshxTzzyPxbListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
//		int size = jshxTzzyPxbService.getJshxTzzyPxbListSizeByMap(paraMap);
//		pagination.setTotalCount(size);
//		pagination.setList(jshxTzzyPxbList);
//		pagination = jshxTzzyPxbService.findByPages(pagination, paraMap);
//		
//		convObjectToJson(pagination,null);
//	}

	/**
	 * 查看特种作业人员培训详情
	 * author：陆婷
	 * 2013-08-17
	 */
	public String view() throws Exception{
		if((null != jshxTzzyPxb)&&(null != jshxTzzyPxb.getId()))
			jshxTzzyPxb = jshxTzzyPxbService.getById(jshxTzzyPxb.getId());
		
		return VIEW;
	}

	/**
	 * 初始化特种作业人员培训修改信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存特种作业人员培训信息（添加）
	 * author：陆婷
	 * 2013-08-17
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			
			jshxTzzyPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxTzzyPxb.setDelFlag(0);
			jshxTzzyPxb.setCreateUserID(this.getLoginUserId());
			jshxTzzyPxb.setCreateTime(new Date());
			
			jshxTzzyPxbService.save(jshxTzzyPxb);
		}else{
			jshxTzzyPxbService.update(jshxTzzyPxb);
		}
		return RELOAD;
	}
	/**
	 * 保存特种作业人员培训信息（继续添加）
	 * author：陆婷
	 * 2013-09-18
	 */
	public String saves() throws Exception{
		try {
			jshxTzzyPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxTzzyPxb.setDelFlag(0);
			jshxTzzyPxb.setCreateUserID(this.getLoginUserId());
			jshxTzzyPxb.setCreateTime(new Date());
			
			jshxTzzyPxbService.save(jshxTzzyPxb);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除特种作业人员培训信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String delete() throws Exception{
	    try{
			jshxTzzyPxbService.deleteWithFlag(ids);
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
	public String deletes() throws Exception{
	    try{
	    	String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				for(String id : idArray)
				{
				    if(id!=null && !id.trim().equals(""))
				    {
				    	Map map = new HashMap();
				    	map.put("qyid", id);
				    	jshxTzzyPxbService.deleteByQyid(map);
				    }
				}
			}
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
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
	                for (int j = 4; j < rowNum; j++) {
	                    // 得到当前行的所有单元格  
	                    Cell[] cells = sheet.getRow(j);  
	                    if (cells != null && cells.length >=16) {
	                    	if(cells[1].getContents()!=null&&!"".equals(cells[1].getContents())){
	                    		 // 对每个单元格进行循环  
		                        JshxTzzyPxb zp = new JshxTzzyPxb();
		                        zp.setDeptId(this.getLoginUserDepartmentId());
			                    zp.setDelFlag(0);
			                    zp.setCreateUserID(this.getLoginUserId());
			                    zp.setCreateTime(new Date());
			                    
			                    zp.setPersonName(cells[1].getContents());//姓名
			                    zp.setSex(cells[2].getContents());//性别
			                    zp.setXl(cells[3].getContents());//学历
			                    zp.setQymc(cells[4].getContents());//所在单位
			                    zp.setLlcj(cells[5].getContents());//理论成绩
			                    zp.setSjcj(cells[6].getContents());//实践成绩
			                    zp.setTzzh(cells[7].getContents());//特种证号
			                    
			                    String a = "";
			                    if(cells[8].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[8]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
								     df.setTimeZone(tz) ; 
								     a = df.format(c00.getDate());
								}
								else
								{
									a = cells[8].getContents().replaceAll("/", "-");
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
			                    
			                    zp.setSj(a);//培训时间
	                       		zp.setSfz(cells[9].getContents());//身份证号
			                    zp.setGz(StringTools.getIndexFromName(cells[10].getContents()));//工种大类
			                    zp.setGzxl(StringTools.getIndexFromName(cells[11].getContents()));//工种小类
			                    zp.setQylx(cells[12].getContents());//企业类型
			                    zp.setSzzname(cells[13].getContents());//所在镇
			                    
			                    String b = "";
			                    if(cells[14].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[14]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
								     df.setTimeZone(tz) ; 
								     b = df.format(c00.getDate());
								}
								else
								{
									b = cells[14].getContents().replaceAll("/", "-");
				                    String[] sj1 = b.split("-");
				                    if(sj1.length == 3)
				                    {
				                    	String s1 = sj1[1];
				                    	if(s1.length() == 1)
				                    	{
				                    		s1 = "0" + s1;
				                    	}
					                    String s2 = sj1[2];
					                    if(s2.length() == 1)
					                    {
					                    	s2 = "0" + s2;
					                    }
					                    b = sj1[0] + "-" + s1 + "-" + s2;
				                    }
								}
			                    
			                    zp.setLzrq(b);//领证日期
			                    
			                    String c = "";
			                    if(cells[15].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[15]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
								     df.setTimeZone(tz) ; 
								     c = df.format(c00.getDate());
								}
								else
								{
									c = cells[15].getContents().replaceAll("/", "-");
				                    String[] sj2 = c.split("-");
				                    if(sj2.length == 3)
				                    {
				                    	String s1 = sj2[1];
				                    	if(s1.length() == 1)
				                    	{
				                    		s1 = "0" + s1;
				                    	}
					                    String s2 = sj2[2];
					                    if(s2.length() == 1)
					                    {
					                    	s2 = "0" + s2;
					                    }
					                    c = sj2[0] + "-" + s1 + "-" + s2;
				                    }
								}
			                    
			                    zp.setYxsj(c);//有效期
			                    if(cells.length > 16)
			                    {
			                    	zp.setBz(cells[16].getContents());//备注
			                    }
			                    tzzyPxbs.add(zp);
	                    	} 
                       	}
	                }
	                 //保存企业培训人员信息
	                jshxTzzyPxbService.saveTzzyPxbs(tzzyPxbs);
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
				jshxTzzyPxb = (JshxTzzyPxb) getSessionAttribute("jshxTzzyPxb");
				queryPxsjStart = (String)getSessionAttribute("queryPxsjStart");
				queryPxsjEnd = (String)getSessionAttribute("queryPxsjEnd");
				queryYxqStart = (String)getSessionAttribute("queryYxqStart");
				queryYxqEnd = (String)getSessionAttribute("queryYxqEnd");
				queryLzrqStart = (String)getSessionAttribute("queryLzrqStart");
				queryLzrqEnd = (String)getSessionAttribute("queryLzrqEnd");
			}
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(null != jshxTzzyPxb){
			    //设置查询条件，开发人员可以在此增加过滤条件
				setSessionAttribute("jshxTzzyPxb", jshxTzzyPxb);
				if ((null != jshxTzzyPxb.getSzzname()) && (0 < jshxTzzyPxb.getSzzname().trim().length())){
					paraMap.put("szzname", jshxTzzyPxb.getSzzname().trim());
				}
				if ((null != jshxTzzyPxb.getQymc()) && (0 < jshxTzzyPxb.getQymc().trim().length())){
					paraMap.put("qymc", "%"+jshxTzzyPxb.getQymc().trim()+"%");
				}
				if ((null != jshxTzzyPxb.getPersonName()) && (0 < jshxTzzyPxb.getPersonName().trim().length())){
					paraMap.put("personName", "%" + jshxTzzyPxb.getPersonName().trim() + "%");
				}
				if ((null != jshxTzzyPxb.getXl()) && (0 < jshxTzzyPxb.getXl().trim().length())){
					paraMap.put("xl", jshxTzzyPxb.getXl().trim());
				}
				if ((null != jshxTzzyPxb.getTzzh()) && (0 < jshxTzzyPxb.getTzzh().trim().length())){
					paraMap.put("tzzh", "%" + jshxTzzyPxb.getTzzh().trim() + "%");
				}
				if ((null != jshxTzzyPxb.getSfz()) && (0 < jshxTzzyPxb.getSfz().trim().length())){
					paraMap.put("sfz", "%" + jshxTzzyPxb.getSfz().trim() + "%");
				}
				if ((null != jshxTzzyPxb.getGz()) && (0 < jshxTzzyPxb.getGz().trim().length()) && !"0".equals(jshxTzzyPxb.getGz().trim())){
					paraMap.put("gz", jshxTzzyPxb.getGz().trim());
				}
				if ((null != jshxTzzyPxb.getGzxl()) && (0 < jshxTzzyPxb.getGzxl().trim().length()) && !"0".equals(jshxTzzyPxb.getGzxl().trim())){
					paraMap.put("gzxl", jshxTzzyPxb.getGzxl().trim());
				}
				if ((null != jshxTzzyPxb.getQylx()) && (0 < jshxTzzyPxb.getQylx().trim().length())){
					paraMap.put("qylx", jshxTzzyPxb.getQylx().trim());
				}
			}
			if (null != queryPxsjStart && !"".equals(queryPxsjStart)){
				paraMap.put("startPxsj", queryPxsjStart);
				setSessionAttribute("queryPxsjStart", queryPxsjStart);
			}

			if (null != queryPxsjEnd && !"".equals(queryPxsjEnd)){
				paraMap.put("endPxsj", queryPxsjEnd);
				setSessionAttribute("queryPxsjEnd", queryPxsjEnd);
			}
			
			if (null != queryYxqStart && !"".equals(queryYxqStart)){
				paraMap.put("startYxq", queryYxqStart);
				setSessionAttribute("queryYxqStart", queryYxqStart);
			}

			if (null != queryYxqEnd && !"".equals(queryYxqEnd)){
				paraMap.put("endYxq", queryYxqEnd);
				setSessionAttribute("queryYxqEnd", queryYxqEnd);
			}
			
			if (null != queryLzrqStart && !"".equals(queryLzrqStart)){
				paraMap.put("startLzrq", queryLzrqStart);
				setSessionAttribute("queryLzrqStart", queryLzrqStart);
			}

			if (null != queryLzrqEnd && !"".equals(queryLzrqEnd)){
				paraMap.put("endLzrq", queryLzrqEnd);
				setSessionAttribute("queryLzrqEnd", queryLzrqEnd);
			}
			List<JshxTzzyPxb> list = jshxTzzyPxbService.findJshxTzzyPxb(paraMap);
			
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=tzzypxb.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "tzzypxb.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        
			HSSFRow row = sheet.getRow(2);
			HSSFCell cell = row.getCell(5);
			HSSFCellStyle css = cell.getCellStyle();
			
	        int num = 3;
	        String[] a = new String[]{" ","电工作业","焊接与热切割作业","高处作业","制冷与空调作业","冶金（有色）生产安全作业","危险化学品安全作业","烟花爆竹安全作业"};
	        String[][] b = new String[][]{{" "},{"","高压电工作业","低压电工作业","防爆电气作业"},{"","熔化焊接与热切割作业","压力焊作业","钎焊作业"},
	        		{"","登高架设作业","高处安装、维护、拆除作业"},{"","制冷与空调设备运行操作作业","制冷与空调设备安装修理作业"},{"","煤气作业"},
	        		{"","光气及光气化工艺作业","氯碱电解工艺作业","氯化工艺作业","硝化工艺作业","合成氨工艺作业","裂解（裂化）工艺作业","氟化工艺作业","加氢工艺作业",
	        		"重氮化工艺作业","氧化工艺作业","过氧化工艺作业","胺基化工艺作业","磺化工艺作业","聚合工艺作业","烷基化工艺作业","化工自动化控制仪表作业"},
	        		{"","烟火药制造作业","黑火药制造作业","引火线制造作业","烟花爆竹产品涉药作业","烟花爆竹储存作业"}};
	        for(int i=0;i<list.size();i++)
	        {
	        	JshxTzzyPxb jshxTzzyPxb = list.get(i);
	        	HSSFRow row0 = sheet.createRow(num);
	        	row0.setHeight((short)(20*20));
				HSSFCell cel0 = row0.createCell(0);
				cel0.setCellValue(i+1);
				cel0.setCellStyle(css);
				HSSFCell cel1 = row0.createCell(1);
				cel1.setCellValue(jshxTzzyPxb.getPersonName());
				cel1.setCellStyle(css);
				HSSFCell cel2 = row0.createCell(2);
				cel2.setCellValue(jshxTzzyPxb.getSex());
				cel2.setCellStyle(css);
				HSSFCell cel3 = row0.createCell(3);
				cel3.setCellValue(jshxTzzyPxb.getXl());
				cel3.setCellStyle(css);
				HSSFCell cel4 = row0.createCell(4);
				cel4.setCellValue(jshxTzzyPxb.getQymc());
				cel4.setCellStyle(css);
				HSSFCell cel5 = row0.createCell(5);
				cel5.setCellValue(jshxTzzyPxb.getLlcj());
				cel5.setCellStyle(css);
				HSSFCell cel6 = row0.createCell(6);
				cel6.setCellValue(jshxTzzyPxb.getSjcj());
				cel6.setCellStyle(css);
				HSSFCell cel7 = row0.createCell(7);
				cel7.setCellValue(jshxTzzyPxb.getTzzh());
				cel7.setCellStyle(css);
				HSSFCell cel8 = row0.createCell(8);
				cel8.setCellValue(jshxTzzyPxb.getSj());
				cel8.setCellStyle(css);
				HSSFCell cel9 = row0.createCell(9);
				cel9.setCellValue(jshxTzzyPxb.getSfz());
				cel9.setCellStyle(css);
				HSSFCell cel10 = row0.createCell(10);
				int gz = Integer.parseInt(jshxTzzyPxb.getGz());
				cel10.setCellValue(a[gz]);
				cel10.setCellStyle(css);
				HSSFCell cel11 = row0.createCell(11);
				int gzxl = Integer.parseInt(jshxTzzyPxb.getGzxl());
				cel11.setCellValue(b[gz][gzxl]);
				cel11.setCellStyle(css);
				HSSFCell cel12 = row0.createCell(12);
				cel12.setCellValue(jshxTzzyPxb.getQylx());
				cel12.setCellStyle(css);
				HSSFCell cel13 = row0.createCell(13);
				cel13.setCellValue(jshxTzzyPxb.getSzzname());
				cel13.setCellStyle(css);
				HSSFCell cel14 = row0.createCell(14);
				cel14.setCellValue(jshxTzzyPxb.getLzrq());
				cel14.setCellStyle(css);
				HSSFCell cel15 = row0.createCell(15);
				cel15.setCellValue(jshxTzzyPxb.getYxsj());
				cel15.setCellStyle(css);
				HSSFCell cel16 = row0.createCell(16);
				cel16.setCellValue(jshxTzzyPxb.getBz());
				cel16.setCellStyle(css);
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

	public JshxTzzyPxb getJshxTzzyPxb(){
		return this.jshxTzzyPxb;
	}

	public void setJshxTzzyPxb(JshxTzzyPxb jshxTzzyPxb){
		this.jshxTzzyPxb = jshxTzzyPxb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public List<JshxTzzyPxb> getTzzyPxbs() {
		return tzzyPxbs;
	}
	public void setTzzyPxbs(List<JshxTzzyPxb> tzzyPxbs) {
		this.tzzyPxbs = tzzyPxbs;
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

	public String getQueryLzrqStart() {
		return queryLzrqStart;
	}

	public void setQueryLzrqStart(String queryLzrqStart) {
		this.queryLzrqStart = queryLzrqStart;
	}

	public String getQueryLzrqEnd() {
		return queryLzrqEnd;
	}

	public void setQueryLzrqEnd(String queryLzrqEnd) {
		this.queryLzrqEnd = queryLzrqEnd;
	}
	

}
