/**
 * Class Name:AqscfkAction
 * Class Description：安全生产罚款
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.aqscfk.web;

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

import com.jshx.aqscfk.entity.Aqscfk;
import com.jshx.aqscfk.entity.Aqscxzcf;
import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.aqscfk.service.AqscfkService;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class AqscfkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Aqscfk aqscfk = new Aqscfk();

	/**
	 * 业务类
	 */
	@Autowired
	private AqscfkService aqscfkService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	private String[] shenheList ;
	
	private String picName;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryJasjStart;

	private Date queryJasjEnd;

	//执法文书
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	//取证材料
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	//调查报告
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	//行政处罚告知书
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	//陈述人笔录
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	//行政处罚决定书
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	//处罚决定书回证
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();
	//结案审批材料
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();
	//类型
	private String type;
	
	private String createUserID;
	
	private List<Aqscxzcf> xzcflist = new ArrayList<Aqscxzcf>();

	
	
	/**
	 * 初始化挂牌督办列表
	 * author：陆婷
	 * 2013-09-17
	 */
	public String init(){
		//根据用户角色判断权限
		List<UserRight> list = this.getLoginUser().getUserRoles();
		flag = "0";
		for(UserRight u:list){
			String rolecode = u.getRole().getRoleCode();
			if(rolecode.equals(SysPropertiesUtil.getProperty("zfzddzRoleCode")) ||
			   rolecode.equals(SysPropertiesUtil.getProperty("zfzdfdzRoleCode")) ){//执法支队（监察大队大队)队长、副队长
				flag = "1";
				break;
			}else if(rolecode.equals(SysPropertiesUtil.getProperty("zfzdbsyRoleCode"))){//执法支队（监察大队）办事员
				flag = "2";
				break;
			} 
		}
		if("0".equals(flag) && this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇人员
		{
			flag = "3";
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据--两个报表中的 安全生产行政处罚 
	 * author：杨鹏
	 * 2014-06-03
	 */
	public String aqscfkReport1() throws Exception{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
		    
			if(null != aqscfk){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if (null != queryJasjStart){
					paraMap.put("startJasj", queryJasjStart);
				}

				if (null != queryJasjEnd){
					paraMap.put("endJasj", queryJasjEnd);
				}
				if ((null != aqscfk.getSzzid()) && (0 < aqscfk.getSzzid().trim().length())){
					paraMap.put("szzid", aqscfk.getSzzid().trim());
				}
			}
			Aqscxzcfglb hj = aqscfkService.getAqscxzcfglbByMap(paraMap);
			paraMap.put("qylx", 1);
			Aqscxzcfglb whp = aqscfkService.getAqscxzcfglbByMap(paraMap);
			paraMap.put("qylx", 2);
			Aqscxzcfglb yhbz = aqscfkService.getAqscxzcfglbByMap(paraMap);
			paraMap.put("qylx", 3);
			Aqscxzcfglb zywh = aqscfkService.getAqscxzcfglbByMap(paraMap);
			paraMap.put("qylx", 4);
			Aqscxzcfglb qtgm = aqscfkService.getAqscxzcfglbByMap(paraMap);
			paraMap.put("qylx", 5);
			Aqscxzcfglb qt = aqscfkService.getAqscxzcfglbByMap(paraMap);
			
			Aqscxzcf aqscxzcf1 = new Aqscxzcf();
			aqscxzcf1.setXmnr("行政处罚次数");
			aqscxzcf1.setJldw("次");
			aqscxzcf1.setHj(hj.getData1());
			aqscxzcf1.setWxp(whp.getData1());
			aqscxzcf1.setYhbz(yhbz.getData1());
			aqscxzcf1.setZyjk(zywh.getData1());
			aqscxzcf1.setQtgm(qtgm.getData1());
			aqscxzcf1.setQt(qt.getData1());
			xzcflist.add(aqscxzcf1);
			
			Aqscxzcf aqscxzcf2 = new Aqscxzcf();
			aqscxzcf2.setXmnr("其中：1）对生产经营单位");
			aqscxzcf2.setJldw("次");
			aqscxzcf2.setHj(hj.getData2());
			aqscxzcf2.setWxp(whp.getData2());
			aqscxzcf2.setYhbz(yhbz.getData2());
			aqscxzcf2.setZyjk(zywh.getData2());
			aqscxzcf2.setQtgm(qtgm.getData2());
			aqscxzcf2.setQt(qt.getData2());
			xzcflist.add(aqscxzcf2);
			
			Aqscxzcf aqscxzcf3 = new Aqscxzcf();
			aqscxzcf3.setXmnr("2）对生产经营单位主要负责人");
			aqscxzcf3.setJldw("次");
			aqscxzcf3.setHj(hj.getData3());
			aqscxzcf3.setWxp(whp.getData3());
			aqscxzcf3.setYhbz(yhbz.getData3());
			aqscxzcf3.setZyjk(zywh.getData3());
			aqscxzcf3.setQtgm(qtgm.getData3());
			aqscxzcf3.setQt(qt.getData3());
			xzcflist.add(aqscxzcf3);
			
//			Aqscxzcf aqscxzcf4 = new Aqscxzcf();
//			aqscxzcf4.setXmnr("      3）强制措施决定书");
//			aqscxzcf4.setJldw("家");
//			aqscxzcf4.setHj(hj.getData4());
//			aqscxzcf4.setWxp(whp.getData4());
//			aqscxzcf4.setYhbz(yhbz.getData4());
//			aqscxzcf4.setZyjk(zywh.getData4());
//			aqscxzcf4.setQtgm(qtgm.getData4());
//			aqscxzcf4.setQt(qt.getData4());
//			xzcflist.add(aqscxzcf4);
			
			Aqscxzcf aqscxzcf5 = new Aqscxzcf();
			aqscxzcf5.setXmnr("罚款次数");
			aqscxzcf5.setJldw("次");
			aqscxzcf5.setHj(hj.getData5());
			aqscxzcf5.setWxp(whp.getData5());
			aqscxzcf5.setYhbz(yhbz.getData5());
			aqscxzcf5.setZyjk(zywh.getData5());
			aqscxzcf5.setQtgm(qtgm.getData5());
			aqscxzcf5.setQt(qt.getData5());
			xzcflist.add(aqscxzcf5);
			
			Aqscxzcf aqscxzcf6 = new Aqscxzcf();
			aqscxzcf6.setXmnr("其中：1）事故处罚");
			aqscxzcf6.setJldw("次");
			aqscxzcf6.setHj(hj.getData6());
			aqscxzcf6.setWxp(whp.getData6());
			aqscxzcf6.setYhbz(yhbz.getData6());
			aqscxzcf6.setZyjk(zywh.getData6());
			aqscxzcf6.setQtgm(qtgm.getData6());
			aqscxzcf6.setQt(qt.getData6());
			xzcflist.add(aqscxzcf6);
			
			Aqscxzcf aqscxzcf7 = new Aqscxzcf();
			aqscxzcf7.setXmnr("2）监督监察处罚");
			aqscxzcf7.setJldw("次");
			aqscxzcf7.setHj(hj.getData7());
			aqscxzcf7.setWxp(whp.getData7());
			aqscxzcf7.setYhbz(yhbz.getData7());
			aqscxzcf7.setZyjk(zywh.getData7());
			aqscxzcf7.setQtgm(qtgm.getData7());
			aqscxzcf7.setQt(qt.getData7());
			xzcflist.add(aqscxzcf7);
			
			Aqscxzcf aqscxzcf8 = new Aqscxzcf();
			aqscxzcf8.setXmnr("责令停产停业整顿生产经营单位");
			aqscxzcf8.setJldw("个");
			aqscxzcf8.setHj(hj.getData8());
			aqscxzcf8.setWxp(whp.getData8());
			aqscxzcf8.setYhbz(yhbz.getData8());
			aqscxzcf8.setZyjk(zywh.getData8());
			aqscxzcf8.setQtgm(qtgm.getData8());
			aqscxzcf8.setQt(qt.getData8());
			xzcflist.add(aqscxzcf8);
			
			Aqscxzcf aqscxzcf9 = new Aqscxzcf();
			aqscxzcf9.setXmnr("处罚罚款");
			aqscxzcf9.setJldw("万元");
			aqscxzcf9.setHj(hj.getData9());
			aqscxzcf9.setWxp(whp.getData9());
			aqscxzcf9.setYhbz(yhbz.getData9());
			aqscxzcf9.setZyjk(zywh.getData9());
			aqscxzcf9.setQtgm(qtgm.getData9());
			aqscxzcf9.setQt(qt.getData9());
			xzcflist.add(aqscxzcf9);
			
			Aqscxzcf aqscxzcf10 = new Aqscxzcf();
			aqscxzcf10.setXmnr("其中：1）事故处罚");
			aqscxzcf10.setJldw("万元");
			aqscxzcf10.setHj(hj.getData10());
			aqscxzcf10.setWxp(whp.getData10());
			aqscxzcf10.setYhbz(yhbz.getData10());
			aqscxzcf10.setZyjk(zywh.getData10());
			aqscxzcf10.setQtgm(qtgm.getData10());
			aqscxzcf10.setQt(qt.getData10());
			xzcflist.add(aqscxzcf10);
			
			Aqscxzcf aqscxzcf11 = new Aqscxzcf();
			aqscxzcf11.setXmnr("2）监督监察处罚");
			aqscxzcf11.setJldw("万元");
			aqscxzcf11.setHj(hj.getData11());
			aqscxzcf11.setWxp(whp.getData11());
			aqscxzcf11.setYhbz(yhbz.getData11());
			aqscxzcf11.setZyjk(zywh.getData11());
			aqscxzcf11.setQtgm(qtgm.getData11());
			aqscxzcf11.setQt(qt.getData11());
			xzcflist.add(aqscxzcf11);
			
			Aqscxzcf aqscxzcf12 = new Aqscxzcf();
			aqscxzcf12.setXmnr("实际收缴罚款");
			aqscxzcf12.setJldw("万元");
			aqscxzcf12.setHj(hj.getData12());
			aqscxzcf12.setWxp(whp.getData12());
			aqscxzcf12.setYhbz(yhbz.getData12());
			aqscxzcf12.setZyjk(zywh.getData12());
			aqscxzcf12.setQtgm(qtgm.getData12());
			aqscxzcf12.setQt(qt.getData12());
			xzcflist.add(aqscxzcf12);
			
			Aqscxzcf aqscxzcf13 = new Aqscxzcf();
			aqscxzcf13.setXmnr("其中：1）事故处罚");
			aqscxzcf13.setJldw("万元");
			aqscxzcf13.setHj(hj.getData13());
			aqscxzcf13.setWxp(whp.getData13());
			aqscxzcf13.setYhbz(yhbz.getData13());
			aqscxzcf13.setZyjk(zywh.getData13());
			aqscxzcf13.setQtgm(qtgm.getData13());
			aqscxzcf13.setQt(qt.getData13());
			xzcflist.add(aqscxzcf13);
			
			Aqscxzcf aqscxzcf14 = new Aqscxzcf();
			aqscxzcf14.setXmnr("2）监督监察处罚");
			aqscxzcf14.setJldw("万元");
			aqscxzcf14.setHj(hj.getData14());
			aqscxzcf14.setWxp(whp.getData14());
			aqscxzcf14.setYhbz(yhbz.getData14());
			aqscxzcf14.setZyjk(zywh.getData14());
			aqscxzcf14.setQtgm(qtgm.getData14());
			aqscxzcf14.setQt(qt.getData14());
			xzcflist.add(aqscxzcf14);
			
			Aqscxzcf aqscxzcf15 = new Aqscxzcf();
			aqscxzcf15.setXmnr("罚款收缴率");
			aqscxzcf15.setJldw("%");
			aqscxzcf15.setHj(hj.getData15());
			aqscxzcf15.setWxp(whp.getData15());
			aqscxzcf15.setYhbz(yhbz.getData15());
			aqscxzcf15.setZyjk(zywh.getData15());
			aqscxzcf15.setQtgm(qtgm.getData15());
			aqscxzcf15.setQt(qt.getData15());
			xzcflist.add(aqscxzcf15);
			
			Aqscxzcf aqscxzcf16 = new Aqscxzcf();
			aqscxzcf16.setXmnr("其中：1）事故处罚");
			aqscxzcf16.setJldw("%");
			aqscxzcf16.setHj(hj.getData16());
			aqscxzcf16.setWxp(whp.getData16());
			aqscxzcf16.setYhbz(yhbz.getData16());
			aqscxzcf16.setZyjk(zywh.getData16());
			aqscxzcf16.setQtgm(qtgm.getData16());
			aqscxzcf16.setQt(qt.getData16());
			xzcflist.add(aqscxzcf16);
			
			Aqscxzcf aqscxzcf17 = new Aqscxzcf();
			aqscxzcf17.setXmnr("2）监督监察处罚");
			aqscxzcf17.setJldw("%");
			aqscxzcf17.setHj(hj.getData17());
			aqscxzcf17.setWxp(whp.getData17());
			aqscxzcf17.setYhbz(yhbz.getData17());
			aqscxzcf17.setZyjk(zywh.getData17());
			aqscxzcf17.setQtgm(qtgm.getData17());
			aqscxzcf17.setQt(qt.getData17());
			xzcflist.add(aqscxzcf17);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据--两个报表中的 安全生产行政执法文书使用情况
	 * author：杨鹏
	 * 2014-06-03
	 */
	public String aqscfkReport2() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(null != aqscfk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryJasjStart){
				paraMap.put("startJasj", queryJasjStart);
			}

			if (null != queryJasjEnd){
				paraMap.put("endJasj", queryJasjEnd);
			}
			if ((null != aqscfk.getSzzid()) && (0 < aqscfk.getSzzid().trim().length())){
				paraMap.put("szzid", aqscfk.getSzzid().trim());
			}
		}
		xzcflist = aqscfkService.findListByPara("aqscfkReport2", paraMap)  ;
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != aqscfk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != aqscfk.getSzzid()) && (0 < aqscfk.getSzzid().trim().length())){
				paraMap.put("szzid", aqscfk.getSzzid().trim());
			}

			if ((null != aqscfk.getQymc()) && (0 < aqscfk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + aqscfk.getQymc().trim() + "%");
			}

			if ((null != aqscfk.getFkfl()) && (0 < aqscfk.getFkfl().trim().length())){
				paraMap.put("fkfl", "%" + aqscfk.getFkfl().trim() + "%");
			}

			if ((null != aqscfk.getSgfl()) && (0 < aqscfk.getSgfl().trim().length())){
				paraMap.put("sgfl",  aqscfk.getSgfl().trim() );
			}
			
			if ((null != aqscfk.getZfwsh()) && (0 < aqscfk.getZfwsh().trim().length())){
				paraMap.put("zfwsh", "%" + aqscfk.getZfwsh().trim() + "%");
			}
			if (null != queryJasjStart){
				paraMap.put("startJasj", queryJasjStart);
			}

			if (null != queryJasjEnd){
				paraMap.put("endJasj", queryJasjEnd);
			}
			if ((null != aqscfk.getSzc() )&& (0 < aqscfk.getSzc().trim().length())){
				paraMap.put("szc",aqscfk.getSzc().trim());
			}
			
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid", deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		pagination = aqscfkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != aqscfk)&&(null != aqscfk.getId()))
		{
			aqscfk = aqscfkService.getById(aqscfk.getId());
			if(aqscfk.getLinkId() == null || "".equals(aqscfk.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				aqscfk.setLinkId(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",aqscfk.getLinkId());
				map.put("picType","aqscfk1");
				picList1 = szwxPhotoService.findPicPath(map);//获取执法文书
				map.put("picType","aqscfk2");
				picList2 = szwxPhotoService.findPicPath(map);//获取取证材料
				map.put("picType","aqscfk3");
				picList3 = szwxPhotoService.findPicPath(map);//获取调查报告
				map.put("picType","aqscfk4");
				picList4 = szwxPhotoService.findPicPath(map);//获取行政处罚告知书
				map.put("picType","aqscfk5");
				picList5 = szwxPhotoService.findPicPath(map);//获取陈述人笔录
				map.put("picType","aqscfk6");
				picList6 = szwxPhotoService.findPicPath(map);//获取行政处罚决定书
				map.put("picType","aqscfk7");
				picList7 = szwxPhotoService.findPicPath(map);//获取处罚决定书回证
				map.put("picType","aqscfk8");
				picList8 = szwxPhotoService.findPicPath(map);//获取结案审批材料
			}
			if(aqscfk.getShenhe() != null && !"".equals(aqscfk.getShenhe()))
			{
				shenheList = aqscfk.getShenhe().split("#");
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			aqscfk.setLinkId(linkId);
		}
			
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			type = "1";
			aqscfk.setSzzid(deptCode);
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if(aqscfk.getQyid() != null && !"".equals(aqscfk.getQyid()))
		{
			Map map = new HashMap();
			map.put("companyId", aqscfk.getQyid());
			CompanyBackUp company = companyService.getCompanyBackupById(map);
			if(!aqscfk.getQymc().equals(company.getCompanyname()))
			{
				aqscfk.setQyid(null);
				Department dept = deptService.findDeptByDeptCode(aqscfk.getSzzid());
				aqscfk.setSzzid(dept.getDeptCode());
				aqscfk.setSzzname(dept.getDeptName());
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				aqscfk.setSzzid(dept.getDeptCode());
				aqscfk.setSzzname(dept.getDeptName());
				aqscfk.setQyid(company.getId());
				aqscfk.setQymc(company.getCompanyname());
				aqscfk.setQylx(company.getQylx());
				aqscfk.setHyfl(company.getHyfl());
				aqscfk.setQygm(company.getQygm());
				aqscfk.setQyzclx(company.getQyzclx());
				aqscfk.setIfwhpqylx(company.getIfwhpqylx());
				aqscfk.setIfyhbzjyqy(company.getIfyhbzjyqy());
				aqscfk.setIfzywhqylx(company.getIfzywhqylx());
				aqscfk.setSzc(company.getSzc());
				aqscfk.setSzcname(company.getSzcname());
				
			}
		}
		else if(aqscfk.getSzzid() != null && !"".equals(aqscfk.getSzzid()))
		{
			Department dept = deptService.findDeptByDeptCode(aqscfk.getSzzid());
			aqscfk.setSzzid(dept.getDeptCode());
			aqscfk.setSzzname(dept.getDeptName());
		}
		aqscfk.setState("1");
		if ("add".equalsIgnoreCase(this.flag)){
			aqscfk.setDeptId(this.getLoginUserDepartmentId());
			aqscfk.setDelFlag(0);
			aqscfk.setCreateTime(new Date());
			aqscfk.setCreateUserID(this.getLoginUserId());
			aqscfkService.save(aqscfk);
		}else{
			aqscfkService.update(aqscfk);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			aqscfkService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转至安全生产罚款附件上传页面
	 * author：陆婷
	 * 2013-11-1
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 保存安全生产罚款附件信息
	 * author：陆婷
	 * 2013-11-1
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
			destFName.append(root).append("file/aqscfk/");
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
			taskPic.setPicName("aqscfk/" + imgName);
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
			jn.put("picName", taskPic.getPicName());
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
	 * 2013-11-1
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
	 * 删除附件信息
	 * author：陆婷
	 * 2013-11-1
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
	 * 附件下载
	 * author：陆婷
	 * 2013-11-1
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
	 * 初始化审核页面
	 * 陆婷
	 * 2013-12-18
	 * @return
	 * @throws Exception
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
	}
	/**
	 * 保存审核结果
	 * 陆婷
	 * 2013-12-18
	 * @return
	 */
	public String shenhesave()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String state = aqscfk.getState();
		String remark = aqscfk.getRemark();
		String a = "";
		if((null != aqscfk)&&(null != aqscfk.getId()))
		{
			Aqscfk g = aqscfkService.getById(aqscfk.getId());
			if("2".equals(state))
			{
				a = "审核不通过";
			}
			else if("3".equals(state))
			{
				a = "审核通过";
			}
			String ss = this.getLoginUser().getDisplayName()+"于" + sdf.format(new Date()) + "进行了审核，审核结果为：["+a + "],备注为：[" + remark+"]";
			String shenhe = g.getShenhe();
			if(shenhe == null || "".equals(shenhe))
			{
				shenhe = ss;
			}
			else
			{
				shenhe = shenhe + "#" + ss ;
			}
			g.setState(state);
			g.setRemark(remark);
			g.setShenhe(shenhe);
			aqscfkService.update(g);
		}
		return RELOAD;
	}
	
	/**
	 * 导出行政处罚
	 * author：陆婷
	 * 2013-12-18
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=xzcf.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("行政处罚情况统计");
		    sheet.setColumnWidth(0, 8000); 
	        sheet.setColumnWidth(1, 10000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 6000); 
	        sheet.setColumnWidth(5, 6000); 
	        sheet.setColumnWidth(6, 15000);
	        sheet.setColumnWidth(7, 6000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("行政处罚情况统计");
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
	        ccl0.setCellValue("所在镇");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业名称");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(2);
	        ccl3.setCellValue("执法文书号");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(3);
	        ccl4.setCellValue("处罚金额（万元）");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(4);
	        ccl5.setCellValue("罚款分类");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(5);
	        ccl2.setCellValue("事故分类");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("处罚事由");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("结案时间");
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
        		String qymc = (String) getSessionAttribute("qymc");
        		String szzid = (String) getSessionAttribute("szzid");
        		String fkfl = (String) getSessionAttribute("fkfl");
        		String qylx = (String) getSessionAttribute("qylx");
        		String zfwsh = (String) getSessionAttribute("zfwsh");
        		paraMap.put("qymc", qymc);
        		paraMap.put("fkfl", fkfl);
        		paraMap.put("qylx", qylx);
        		paraMap.put("zfwsh", zfwsh);
        		paraMap.put("szzid", szzid);
        		queryJasjStart = (Date) getSessionAttribute("queryJasjStart");
        		queryJasjEnd = (Date) getSessionAttribute("queryJasjEnd");
    		}
	        if(null != aqscfk){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != aqscfk.getSzzid()) && (0 < aqscfk.getSzzid().trim().length())){
					paraMap.put("szzid", aqscfk.getSzzid().trim());
					setSessionAttribute("szzid", aqscfk.getSzzid().trim());
				}

				if ((null != aqscfk.getQymc()) && (0 < aqscfk.getQymc().trim().length())){
					paraMap.put("qymc", "%" + aqscfk.getQymc().trim() + "%");
					setSessionAttribute("qymc", "%" + aqscfk.getQymc().trim() + "%");
				}

				if ((null != aqscfk.getFkfl()) && (0 < aqscfk.getFkfl().trim().length())){
					paraMap.put("fkfl", "%" + aqscfk.getFkfl().trim() + "%");
					setSessionAttribute("fkfl","%" + aqscfk.getFkfl().trim() + "%");
				}

				if ((null != aqscfk.getSgfl()) && (0 < aqscfk.getSgfl().trim().length())){
					paraMap.put("sgfl",  aqscfk.getSgfl().trim() );
				}
				
				if ((null != aqscfk.getZfwsh()) && (0 < aqscfk.getZfwsh().trim().length())){
					paraMap.put("zfwsh", "%" + aqscfk.getZfwsh().trim() + "%");
					setSessionAttribute("zfwsh", "%" + aqscfk.getZfwsh().trim() + "%" );
				}
			}
	        if (null != queryJasjStart){
				paraMap.put("startJasj", queryJasjStart);
				setSessionAttribute("queryJasjStart", queryJasjStart );
			}

			if (null != queryJasjEnd){
				paraMap.put("endJasj", queryJasjEnd);
				setSessionAttribute("queryJasjEnd", queryJasjEnd );
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
			{
				if(deptCode.length() == 12)
				{
					paraMap.put("szzid", deptCode);
				}
				else
				{
					paraMap.put("szc", deptCode);
				}
			}
	        
			List<Aqscfk> list = aqscfkService.findAqscfk(paraMap);
			
			int num = 2;
			for(Aqscfk aqscfk:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(aqscfk.getSzzname());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(aqscfk.getQymc());
		        ce1.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(2);
		        ce3.setCellValue(aqscfk.getZfwsh());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(3);
		        ce4.setCellValue(aqscfk.getCfje());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(4);
		        String fkfl = aqscfk.getFkfl();
		        if("0".equals(fkfl))
		    	{
		        	fkfl = "事故罚款";
		    	}
		    	else
		    	{
		    		fkfl = "监督监察罚款";
		    	}
		        ce5.setCellValue(fkfl);
		        ce5.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(5);
		        String sgfl = aqscfk.getSgfl();
		        if("0".equals(sgfl))
		    	{
		        	sgfl = "一般事故";
		    	}
		    	else if("1".equals(sgfl))
		    	{
		    		sgfl = "较大事故";
		    	}
		    	else if("2".equals(sgfl))
		    	{
		    		sgfl = "重大事故";
		    	}
		    	else if("3".equals(sgfl))
		    	{
		    		sgfl = "特大事故";
		    	}
		        ce2.setCellValue(sgfl);
		        ce2.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(aqscfk.getCfsy());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String jasj = "";
		        if(aqscfk.getJasj() != null)
		        {
		        	jasj = sdf.format(aqscfk.getJasj());
		        }
		        ce7.setCellValue(jasj);
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

	public Aqscfk getAqscfk(){
		return this.aqscfk;
	}

	public void setAqscfk(Aqscfk aqscfk){
		this.aqscfk = aqscfk;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryJasjStart(){
		return this.queryJasjStart;
	}

	public void setQueryJasjStart(Date queryJasjStart){
		this.queryJasjStart = queryJasjStart;
	}

	public Date getQueryJasjEnd(){
		return this.queryJasjEnd;
	}

	public void setQueryJasjEnd(Date queryJasjEnd){
		this.queryJasjEnd = queryJasjEnd;
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

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
	}

	public List<PhotoPic> getPicList4() {
		return picList4;
	}

	public void setPicList4(List<PhotoPic> picList4) {
		this.picList4 = picList4;
	}

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
	}

	public List<PhotoPic> getPicList6() {
		return picList6;
	}

	public void setPicList6(List<PhotoPic> picList6) {
		this.picList6 = picList6;
	}

	public List<PhotoPic> getPicList7() {
		return picList7;
	}

	public void setPicList7(List<PhotoPic> picList7) {
		this.picList7 = picList7;
	}

	public List<PhotoPic> getPicList8() {
		return picList8;
	}

	public void setPicList8(List<PhotoPic> picList8) {
		this.picList8 = picList8;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String[] getShenheList() {
		return shenheList;
	}

	public void setShenheList(String[] shenheList) {
		this.shenheList = shenheList;
	}
	public List<Aqscxzcf> getXzcflist() {
		return xzcflist;
	}
	public void setXzcflist(List<Aqscxzcf> xzcflist) {
		this.xzcflist = xzcflist;
	}

	 

}
