/**
 * Class Name: JshxQysbAction
 * Class Description：企业设备
 */
package com.jshx.qysb.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.jshx.qysb.entity.JshxQysb;
import com.jshx.qysb.service.JshxQysbService;

public class JshxQysbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxQysb jshxQysb = new JshxQysb();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxQysbService jshxQysbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	//使用日期开始
	private Date querySyrqStart;
    //使用日期结束
	private Date querySyrqEnd;
	 /**
	 * @author gq
	 * @date 8yue 20
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	 @Autowired
		private SzwxPhotoService szwxPhotoService;
	 private String type;
	 
	 private Date createTimeStart;

		private Date createTimeEnd;
	/**
	 * 初始化企业设备列表
	 * author：陆婷
	 * 2013-08-19
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
	 * 查询企业设备列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxQysb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxQysb.getSbmc()) && (0 < jshxQysb.getSbmc().trim().length())){
				paraMap.put("sbmc", "%" + jshxQysb.getSbmc().trim() + "%");
			}

			if ((null != jshxQysb.getQymc()) && (0 < jshxQysb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxQysb.getQymc().trim() + "%");
			}

			if (null != querySyrqStart){
				paraMap.put("startSyrq", querySyrqStart);
			}

			if (null != querySyrqEnd){
				paraMap.put("endSyrq", querySyrqEnd);
			}
			if ((null != jshxQysb.getIftzsb()) && (0 < jshxQysb.getIftzsb().trim().length())){
				paraMap.put("iftzsb", jshxQysb.getIftzsb().trim());
			}
			if ((null != jshxQysb.getSzzid()) && (0 < jshxQysb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxQysb.getSzzid().trim() );
			}
			if ((null != jshxQysb.getSzc() )&& (0 < jshxQysb.getSzc().trim().length())){
				paraMap.put("szc",jshxQysb.getSzc().trim());
			}
		}
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxQysbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看企业设备详情
	 * author：陆婷
	 * 2013-08-19
	 */
	public String view() throws Exception{
		if((null != jshxQysb)&&(null != jshxQysb.getId()))
			jshxQysb = jshxQysbService.getById(jshxQysb.getId());
		if(jshxQysb.getLinkid()==null)
		{
			jshxQysb.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",jshxQysb.getLinkid());
		map.put("picType","qysbylb");
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化企业设备修改信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		jshxQysb.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存企业设备信息
	 * author：陆婷
	 * 2013-08-19
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxQysb.setSzzid(dept.getDeptCode());
			jshxQysb.setSzzname(dept.getDeptName());
			jshxQysb.setQyid(company.getId());
			jshxQysb.setQymc(company.getCompanyname());
			jshxQysb.setDeptId(this.getLoginUserDepartmentId());
			jshxQysb.setDelFlag(0);
			jshxQysb.setCreateUserID(this.getLoginUserId());
			jshxQysb.setCreateTime(new Date());
			jshxQysb.setQylx(company.getQylx());
			jshxQysb.setHyfl(company.getHyfl());
			jshxQysb.setQygm(company.getQygm());
			jshxQysb.setQyzclx(company.getQyzclx());
			jshxQysb.setIfwhpqylx(company.getIfwhpqylx());
			jshxQysb.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxQysb.setIfzywhqylx(company.getIfzywhqylx());
			jshxQysb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxQysb.setSzc(company.getSzc());
			jshxQysb.setSzcname(company.getSzcname());
			jshxQysb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxQysb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxQysbService.save(jshxQysb);
		}else{
			jshxQysb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxQysb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxQysbService.update(jshxQysb);
		}
		return RELOAD;
	}

	/**
	 * 删除企业设备信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String delete() throws Exception{
	    try{
			jshxQysbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public void fileDownload()
	{
		try
  		{
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			String fileName = "";
  			if("ldfh".equals(type))
  			{
  				fileName = "劳动防护用品配备情况.xlsx";
  			}
  			else if("qysb".equals(type))
  			{
  				fileName = "企业设备一览表.xlsx";
  			}
  			else if("aqss".equals(type))
  			{
  				fileName = "安全设施登记台账.xlsx";
  			}
  			else if("aqjs".equals(type))
  			{
  				fileName = "安全警示标示设置情况.xlsx";
  			}
  			else if("hxdj".equals(type))
  			{
  				fileName = "重大危险源核销登记表.docx";
  			}
  			else if("badj".equals(type))
  			{
  				fileName = "重大危险源备案登记表.docx";
  			}
  			else if("basq".equals(type))
  			{
  				fileName = "重大危险源备案申请表.docx";
  			}
  			else if("hxsq".equals(type))
  			{
  				fileName = "重大危险源核销申请表.docx";
  			}
  			else if("jbtz".equals(type))
  			{
  				fileName = "重大危险源基本特征.docx";
  			}
  			File fis = new File(path + "../../virtualdir/upload/file/excelCase/" + fileName);
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

  				String browName = new String();
  				browName = URLEncoder.encode(fileName, "UTF-8");
  				String clientInfo = getRequest().getHeader("User-agent");
  				if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
  					if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
  						browName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
  				}

  				Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
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

	public JshxQysb getJshxQysb(){
		return this.jshxQysb;
	}

	public void setJshxQysb(JshxQysb jshxQysb){
		this.jshxQysb = jshxQysb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQuerySyrqStart(){
		return this.querySyrqStart;
	}

	public void setQuerySyrqStart(Date querySyrqStart){
		this.querySyrqStart = querySyrqStart;
	}

	public Date getQuerySyrqEnd(){
		return this.querySyrqEnd;
	}

	public void setQuerySyrqEnd(Date querySyrqEnd){
		this.querySyrqEnd = querySyrqEnd;
	}
	public List<PhotoPic> getList() {
		return list;
	}
	public void setList(List<PhotoPic> list) {
		this.list = list;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
