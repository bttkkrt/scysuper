/**
 * Class Name: ZywsjbxxAction
 * Class Description：职业卫生基本信息列表
 */
package com.jshx.zywsjbxx.web;

import java.io.IOException;
import java.util.ArrayList;
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

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.zycsjcry.service.ZycsjcryService;
import com.jshx.zycsqk.service.ZycsqkService;
import com.jshx.zywsjbxx.entity.Zybwhqytj;
import com.jshx.zywsjbxx.entity.Zywsglry;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.service.ZywsjbxxService;

/**
 * @author 高强 createtime 13年11月20 desc 职业卫生基本信息action层
 * 
 */
public class ZywsjbxxAction extends BaseAction {

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zywsjbxx zywsjbxx = new Zywsjbxx();

	/**
	 * 部门业务类
	 */
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZycsqkService zycsqkService;
	@Autowired
	private ZycsjcryService zycsjcryService;
	/**
	 * 实体类 createtime 13年11月21 desc 企业业务类 writer 高强
	 */
	@Autowired
	private CompanyService companyService;
	/**
	 * 实体类 createtime 13年11月21 desc 职业卫生管理人员对象列表 writer 高强
	 */
	private List<Zywsglry> zywsglrys = new ArrayList<Zywsglry>();

	private Zywsglry zywsglry = new Zywsglry();
	
	private List<Zybwhqytj> zybwhqytjList = new ArrayList<Zybwhqytj>();
	
	private Zybwhqytj zybwhqytj = new Zybwhqytj();

	/**
	 * 业务类
	 */
	@Autowired
	private ZywsjbxxService zywsjbxxService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * 实体类 createtime 13年11月22 desc type 为1 表示上级部门 0 表示企业 writer 高强
	 */
	private String type;
	
	private String queryStartTime;
	
	private String queryEndTime;

	/**
	 * 实体类 createtime 13年11月22 desc flag 为1 表示安监上级部门 0 表示企业 writer 高强
	 */
	public String initlist() {
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if (this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")) && deptCode.length() > 6) {
			flag = "0";
		} else {
			flag = "1";
		}
		return SUCCESS;
	}

	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		/**
		 * createtime 13年11月22 desc 上级部门查看，安监中队的能查看自己对应中队下的企业，其他上级部门可查看所有企业
		 * writer 高强
		 */
		Department dept = this.getLoginUserDepartment();
		String code = dept.getDeptCode();
		if (this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))) {
			paraMap.put("deptcode", code + "%");
		}
		if (null != zywsjbxx) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zywsjbxx.getSzzname())
					&& (0 < zywsjbxx.getSzzname().trim().length())) {
				paraMap.put("deptcode", "%" + zywsjbxx.getSzzname().trim()
						+ "%");
			}

			if ((null != zywsjbxx.getQymc())
					&& (0 < zywsjbxx.getQymc().trim().length())) {
				paraMap.put("qymc", "%" + zywsjbxx.getQymc().trim() + "%");
			}

			if ((null != zywsjbxx.getQylx())
					&& (0 < zywsjbxx.getQylx().trim().length())) {
				paraMap.put("qylx", zywsjbxx.getQylx().trim());
			}

			if ((null != zywsjbxx.getHyfl())
					&& (0 < zywsjbxx.getHyfl().trim().length())) {
				paraMap.put("hyfl", zywsjbxx.getHyfl().trim());
			}

			if ((null != zywsjbxx.getQyzclx())
					&& (0 < zywsjbxx.getQyzclx().trim().length())) {
				paraMap.put("qyzclx", zywsjbxx.getQyzclx().trim());
			}
		}

		pagination = zywsjbxxService.findByPage(pagination, paraMap);

		convObjectToJson(pagination, null);
	}

	
	
	/**
	 * writer 高强
	 * createtime 2013-11-26
	 * 修改：实现数据查询 展示 by陆婷 2013-11-28
	 * 
	 * desc 查询查询职业病危害企业统计列表数据
	 */
	public String countlist() throws Exception {
		Map map = new HashMap();
		if (null != queryStartTime) {
			map.put("queryStartTime", queryStartTime);
		}
		if (null != queryEndTime) {
			map.put("queryEndTime", queryEndTime);
		}
		zybwhqytjList = zywsjbxxService.getZywhqytjListByMap(map);
		zybwhqytj = zywsjbxxService.getZywhqytjByMap(map);
		return SUCCESS;
	}
	
	
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception {
		// if((null != zywsjbxx)&&(null != zywsjbxx.getId()))
		// {
		// zywsjbxx = zywsjbxxService.getById(zywsjbxx.getId());
		// zywsglrys=zywsjbxxService.getZywsglrysById(zywsjbxx.getId());
		// }

		/**
		 * createtime 11yue21 writer 高强 desc type 为0 表示企业查询 1表示上级部门查看
		 */
		if (this.type != null && this.type.equals("0")) {
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			if(null != company){
				zywsjbxx = zywsjbxxService.getByCompanyId(company.getId());
			}
			if(zywsjbxx == null)
			{
				zywsjbxx = new Zywsjbxx();
				Map map = new HashMap();
				map.put("qyid", company.getId());
				String gws = zycsqkService.getTotalCountByMap(map);
				zywsjbxx.setGws(gws);
				String jcrs = zycsjcryService.getTotalCountByMap(map);
				zywsjbxx.setJcrs(jcrs);
				map.put("xb", "0");
				String jcngs = zycsjcryService.getTotalCountByMap(map);
				zywsjbxx.setJcngrs(jcngs);
				zywsjbxx.setTbbm(this.getLoginUserDepartment().getDeptName());
				zywsjbxx.setTbr(this.getLoginUser().getDisplayName());
			}
		} else // 表示上级部门查看
		{
			zywsjbxx = zywsjbxxService.getById(zywsjbxx.getId());
		}

		if (zywsjbxx != null && zywsjbxx.getId() != null)
		{
			zywsglrys = zywsjbxxService.getZywsglrysById(zywsjbxx.getId());
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception {
		view();

		return EDIT;
	}

	/**
	 * createtime 11yue21 writer 高强 desc 保存职业卫生上报的信息
	 */
	public String save() throws Exception {
		// zywsjbxx 和zywsjbxx.getId() 为空标示新增
		if (zywsjbxx != null && zywsjbxx.getId().length() == 0) {

			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			zywsjbxx.setSzzname(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			zywsjbxx.setSzzid(company.getDwdz1());
			zywsjbxx.setQyid(company.getId());
			zywsjbxx.setQymc(company.getCompanyname());
			// 企业类型 qylx 行业分类 hyfl 企业规模 qygm 企业注册类型 qyzclx
			zywsjbxx.setQylx(company.getQylx());
			zywsjbxx.setHyfl(company.getHyfl());
			zywsjbxx.setQyzclx(company.getQyzclx());

			zywsjbxx.setDeptId(this.getLoginUserDepartmentId());
			zywsjbxx.setDelFlag(0);
			/**
			 * createtime 11yue21 writer 高强 desc 保存职业卫生上报的信息
			 */
			String zywsjbxxid = zywsjbxxService.save(zywsjbxx);

			/**
			 * createtime 11yue21 writer 高强 desc 提交的管理人员信息
			 */
			Zywsglry zywqreqry = this.getZywsglry();
			if (zywqreqry != null && zywqreqry.getGlrxm() != null) {
				// 管理人员姓名
				String[] glrxms = zywqreqry.getGlrxm() .replaceAll(" ", "").split(",");
				// 职业卫生管理人员职务
				String[] glrzws = zywqreqry.getGlrzw().replaceAll(" ", "").split(",");
				// 职业卫生管理人员办公电话
				String[] glrdhs = zywqreqry.getGlrdh().replaceAll(" ", "").split(",");
				// 职业卫生管理人员手机
				String[] glrsjs = zywqreqry.getGlrsj().replaceAll(" ", "").split(",");
				// 职业卫生管理人员文化程度
				String[] slrwhcds = zywqreqry.getSlrwhcd().replaceAll(" ", "").split(",");
				// 职业卫生管理人员专业
				String[] slrzys = zywqreqry.getSlrzy().replaceAll(" ", "").split(",");
				// 把每一条信息封装到Zywsglry 职业卫生人员对象中，保存下来
				Zywsglry zywsglry = null;
				for (int i = 0; i < glrxms.length; i++) {
					zywsglry = new Zywsglry();
					zywsglry.setGlrxm(glrxms[i]);
					zywsglry.setGlrzw(glrzws[i]);
					zywsglry.setGlrdh(glrdhs[i]);
					zywsglry.setGlrsj(glrsjs[i]);
					zywsglry.setSlrwhcd(slrwhcds[i]);
					zywsglry.setSlrzy(slrzys[i]);
					zywsglry.setDelFlag(0);
					zywsglry.setZywsjbxxid(zywsjbxxid);
					// 保存管理人员信息到表中
					zywsjbxxService.saveZywsglry(zywsglry);
				}
			}

			// 修改保存职业卫生上报的信息
		} else {
			zywsjbxxService.update(zywsjbxx);

			/**
			 * createtime 11yue21 writer 高强 desc 提交的管理人员信息
			 */
			Zywsglry zywqreqry = this.getZywsglry();
			if (zywqreqry != null) {
				if (zywqreqry.getGlrxm() != null) {
					// 管理人员ids
					String[] glryids = null;
					if(null != zywqreqry.getId() && 0 < zywqreqry.getId().length()){
						glryids  = zywqreqry.getId().replaceAll(" ", "").split(",");
					}
					// 管理人员姓名
					String[] glrxms = zywqreqry.getGlrxm().replaceAll(" ", "").split(",");
					// 职业卫生管理人员职务
					String[] glrzws = zywqreqry.getGlrzw().replaceAll(" ", "").split(",");
					// 职业卫生管理人员办公电话
					String[] glrdhs = zywqreqry.getGlrdh().replaceAll(" ", "").split(",");
					// 职业卫生管理人员手机
					String[] glrsjs = zywqreqry.getGlrsj().replaceAll(" ", "").split(",");
					// 职业卫生管理人员文化程度
					String[] slrwhcds = zywqreqry.getSlrwhcd().replaceAll(" ", "").split(",");
					// 职业卫生管理人员专业
					String[] slrzys = zywqreqry.getSlrzy().replaceAll(" ", "").split(",");
					// 把每一条信息封装到Zywsglry 职业卫生人员对象中，保存下来
					Zywsglry zywsglry = null;
					for (int i = 0; i < glrxms.length; i++) {
						zywsglry = new Zywsglry();
						zywsglry.setGlrxm(glrxms[i]);
						zywsglry.setGlrzw(glrzws[i]);
						zywsglry.setGlrdh(glrdhs[i]);
						zywsglry.setGlrsj(glrsjs[i]);
						zywsglry.setSlrwhcd(slrwhcds[i].trim());
						zywsglry.setSlrzy(slrzys[i]);
						zywsglry.setDelFlag(0);
						zywsglry.setZywsjbxxid(zywsjbxx.getId());
						// 保存管理人员信息到表中,有id值得信息更新它，反之新增信息

						if (glryids != null && (null != glryids && i < glryids.length))// 更新信息
						{
							// 根据id查询管理人员的信息
							Zywsglry zywsglryOld = zywsjbxxService.getZywsglryById(glryids[i]);
							zywsglryOld.setGlrxm(glrxms[i]);
							zywsglryOld.setGlrzw(glrzws[i]);
							zywsglryOld.setGlrdh(glrdhs[i]);
							zywsglryOld.setGlrsj(glrsjs[i]);
							zywsglryOld.setSlrwhcd(slrwhcds[i]);
							zywsglryOld.setSlrzy(slrzys[i]);
							// 更新信息
							zywsjbxxService.updateZywsglry(zywsglryOld);
						} else // 新增信息
						{
							zywsjbxxService.saveZywsglry(zywsglry);
						}
					}
				}
			}

		}
		return RELOAD;
	}

	/**
	 * desc 删除管理人信息
	 * 
	 * @param id
	 *            管理人信息id writer 高强 createtime 11月21日
	 */
	public String zywsglryDel() throws Exception {
		try {
			zywsjbxxService.deleteZywsglryDel(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception {
		try {
			zywsjbxxService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	
	/**
	 * writer 陆婷
	 * createtime 2013-12-3
	 * desc 职业病危害企业统计导出
	 */
	public void countlistExport() throws Exception {
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zybwhqytj.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("职业病危害企业统计");
		    sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("职业病危害企业统计");
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
	        ccl0.setCellValue("属地");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业总数");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("职工人数");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("接触职业危害人数");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("职业病人数");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("检测点数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("监测点合格率");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("体检员工数");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("体检异常人数");
	        ccl8.setCellStyle(cs);
	        
	        
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
	        	queryStartTime = (String) getSessionAttribute("queryStartTime");
	        	queryEndTime =  (String) getSessionAttribute("queryEndTime");
			}
	        if (null != queryStartTime) {
				map.put("queryStartTime", queryStartTime);
				setSessionAttribute("queryStartTime", queryStartTime);
			}
			if (null != queryEndTime) {
				map.put("queryEndTime", queryEndTime);
				setSessionAttribute("queryEndTime", queryEndTime);
			}
			zybwhqytjList = zywsjbxxService.getZywhqytjListByMap(map);
			zybwhqytj = zywsjbxxService.getZywhqytjByMap(map);
			
			HSSFRow ro = sheet.createRow(2);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(zybwhqytj.getQyzs());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(zybwhqytj.getZgrs());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        ce3.setCellValue(zybwhqytj.getJcrs());
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(zybwhqytj.getZybrs());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(zybwhqytj.getJcds());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(zybwhqytj.getJcdhgl()+"%");
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(zybwhqytj.getTjygs());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(zybwhqytj.getTjycs());
	        ce8.setCellStyle(c);
	        
			int num = 3;
			for(Zybwhqytj zybwhqytj:zybwhqytjList)
			{
				HSSFRow rows = sheet.createRow(num);
				HSSFCell ce00 = rows.createCell(0);
				ce00.setCellValue(zybwhqytj.getSzzname());
				ce00.setCellStyle(c);
		        HSSFCell ce01 = rows.createCell(1);
		        ce01.setCellValue(zybwhqytj.getQyzs());
		        ce01.setCellStyle(c);
		        HSSFCell ce02 = rows.createCell(2);
		        ce02.setCellValue(zybwhqytj.getZgrs());
		        ce02.setCellStyle(c);
		        HSSFCell ce03 = rows.createCell(3);
		        ce03.setCellValue(zybwhqytj.getJcrs());
		        ce03.setCellStyle(c);
		        HSSFCell ce04 = rows.createCell(4);
		        ce04.setCellValue(zybwhqytj.getZybrs());
		        ce04.setCellStyle(c);
		        HSSFCell ce05 = rows.createCell(5);
		        ce05.setCellValue(zybwhqytj.getJcds());
		        ce05.setCellStyle(c);
		        HSSFCell ce06 = rows.createCell(6);
		        ce06.setCellValue(zybwhqytj.getJcdhgl()+"%");
		        ce06.setCellStyle(c);
		        HSSFCell ce07 = rows.createCell(7);
		        ce07.setCellValue(zybwhqytj.getTjygs());
		        ce07.setCellStyle(c);
		        HSSFCell ce08 = rows.createCell(8);
		        ce08.setCellValue(zybwhqytj.getTjycs());
		        ce08.setCellStyle(c);
		        num++;
			}
	        
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Zywsjbxx getZywsjbxx() {
		return this.zywsjbxx;
	}

	public void setZywsjbxx(Zywsjbxx zywsjbxx) {
		this.zywsjbxx = zywsjbxx;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Zywsglry> getZywsglrys() {
		return zywsglrys;
	}

	public void setZywsglrys(List<Zywsglry> zywsglrys) {
		this.zywsglrys = zywsglrys;
	}

	public Zywsglry getZywsglry() {
		return zywsglry;
	}

	public void setZywsglry(Zywsglry zywsglry) {
		this.zywsglry = zywsglry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public List<Zybwhqytj> getZybwhqytjList() {
		return zybwhqytjList;
	}

	public void setZybwhqytjList(List<Zybwhqytj> zybwhqytjList) {
		this.zybwhqytjList = zybwhqytjList;
	}

	public Zybwhqytj getZybwhqytj() {
		return zybwhqytj;
	}

	public void setZybwhqytj(Zybwhqytj zybwhqytj) {
		this.zybwhqytj = zybwhqytj;
	}

}
