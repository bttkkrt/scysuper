/**
 * Class Name: ZdwxyjbtzAction
 * Class Description：危险化学品重大危险源基本特征
 */
package com.jshx.zdwxyjbtz.web;

import java.text.SimpleDateFormat;
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
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.whpglb.entity.Whpglb;
import com.jshx.whpglb.service.WhpglbService;
import com.jshx.zdwxyjbtz.entity.Zdwxyjbtz;
import com.jshx.zdwxyjbtz.service.ZdwxyjbtzService;

public class ZdwxyjbtzAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxyjbtz zdwxyjbtz = new Zdwxyjbtz();
	
	private Whpglb whpglb = new Whpglb();
	 @Autowired
		private SzwxPhotoService szwxPhotoService;
	 /**
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list1 = new ArrayList<PhotoPic>();
		
		private List<PhotoPic> list2 = new ArrayList<PhotoPic>();
		private List<PhotoPic> list3 = new ArrayList<PhotoPic>();
		
	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxyjbtzService zdwxyjbtzService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private WhpglbService whpglbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryZdwxytysjStart;

	private Date queryZdwxytysjEnd;
	
	private List<Whpglb> whpList = new ArrayList<Whpglb>();
	private Date createTimeStart;

	private Date createTimeEnd;
	
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * 初始化安全附件情况列表
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
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zdwxyjbtz){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxyjbtz.getQymc()) && (0 < zdwxyjbtz.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxyjbtz.getQymc().trim() + "%");
			}

			if ((null != zdwxyjbtz.getZdwxymc()) && (0 < zdwxyjbtz.getZdwxymc().trim().length())){
				paraMap.put("zdwxymc", "%" + zdwxyjbtz.getZdwxymc().trim() + "%");
			}

			if (null != queryZdwxytysjStart){
				paraMap.put("startZdwxytysj", queryZdwxytysjStart);
			}

			if (null != queryZdwxytysjEnd){
				paraMap.put("endZdwxytysj", queryZdwxytysjEnd);
			}
			if ((null != zdwxyjbtz.getSzzid()) && (0 < zdwxyjbtz.getSzzid().trim().length())){
				paraMap.put("szzid",  zdwxyjbtz.getSzzid().trim() );
			}
			if ((null != zdwxyjbtz.getSzc() )&& (0 < zdwxyjbtz.getSzc().trim().length())){
				paraMap.put("szc",zdwxyjbtz.getSzc().trim());
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

		pagination = zdwxyjbtzService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zdwxyjbtz)&&(null != zdwxyjbtz.getId()))
		{
			zdwxyjbtz = zdwxyjbtzService.getById(zdwxyjbtz.getId());
			Map map = new HashMap();
			map.put("zdwxyid", zdwxyjbtz.getId());
			whpList = whpglbService.findWhpglb(map);
		}
		if(zdwxyjbtz.getLinkid()==null)
		{
			zdwxyjbtz.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zdwxyjbtz.getLinkid());
		map.put("picType","zdwxyjbtz");
	    list1 = szwxPhotoService.findPicPath(map);//获取图片或附件列表
	    map.put("picType","zdwxybasqb");
	    list2 = szwxPhotoService.findPicPath(map);//获取图片或附件列表
	    map.put("picType","zdwxybadjb");
	    list3 = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		zdwxyjbtz.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zdwxyjbtz.setZdwxyjb(sdf.format(new Date()));
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zdwxyjbtz.setSzzid(dept.getDeptCode());
			zdwxyjbtz.setSzzname(dept.getDeptName());
			zdwxyjbtz.setQyid(company.getId());
			zdwxyjbtz.setQymc(company.getCompanyname());
			zdwxyjbtz.setDeptId(this.getLoginUserDepartmentId());
			zdwxyjbtz.setDelFlag(0);
			zdwxyjbtz.setCreateUserID(this.getLoginUserId());
			zdwxyjbtz.setCreateTime(new Date());
			zdwxyjbtz.setQylx(company.getQylx());
			zdwxyjbtz.setHyfl(company.getHyfl());
			zdwxyjbtz.setQygm(company.getQygm());
			zdwxyjbtz.setQyzclx(company.getQyzclx());
			zdwxyjbtz.setIfwhpqylx(company.getIfwhpqylx());
			zdwxyjbtz.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zdwxyjbtz.setIfzywhqylx(company.getIfzywhqylx());
			zdwxyjbtz.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			zdwxyjbtz.setSzc(company.getSzc());
			zdwxyjbtz.setSzcname(company.getSzcname());
			zdwxyjbtz.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxyjbtz.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxyjbtzService.save(zdwxyjbtz);
		}else{
			zdwxyjbtz.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxyjbtz.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxyjbtzService.update(zdwxyjbtz);
			Map map = new HashMap();
			map.put("zdwxyid", zdwxyjbtz.getId());
			zdwxyjbtzService.deleteWhpByMap(map);
		}
		/* //危险化学品名称
		String[] wxhxpmc = whpglb.getWxhxpmc().replaceAll(" ", "").split(",");
		//危险性类别
		String[] wxxlb = whpglb.getWxxlb().replaceAll(" ", "").split(",");
		//UN编号
		String[] unbh = whpglb.getUnbh().replaceAll(" ", "").split(",");
		//生产用途
		String[] scyt = whpglb.getScyt().replaceAll(" ", "").split(",");
		//生产工艺
		String[] scgy = whpglb.getScgy().replaceAll(" ", "").split(",");
		//单个最大容器物理状态
		String[] wlzt = whpglb.getWlzt().replaceAll(" ", "").split(",");
		//单个最大容器操作温度
		String[] czwd = whpglb.getCzwd().replaceAll(" ", "").replaceAll(" ", "").split(",");
		//单个最大容器操作压力
		String[] czyl = whpglb.getCzyl().replaceAll(" ", "").split(",");
		//单个最大容器存量
		String[] zdrqcl = whpglb.getZdrqcl().replaceAll(" ", "").split(",");
		//单元内危险化学品存量
		String[] wxhxpcl = whpglb.getWxhxpcl().replaceAll(" ", "").split(",");
		//临界量
		String[] ljl = whpglb.getLjl().replaceAll(" ", "").split(",");
		
		try {
			for(int i=0;i<wxhxpmc.length;i++)
			{
				if(wxhxpmc[i] != null && !"".equals(wxhxpmc[i]))
				{
					Whpglb w = new Whpglb();
					w.setCreateTime(new Date());
					w.setCreateUserID(this.getLoginUserId());
					w.setCzwd(isNull(czwd,i));
					w.setCzyl(isNull(czyl,i));
					w.setDelFlag(0);
					w.setDeptId(this.getLoginUserDepartmentId());
					w.setHyfl(zdwxyjbtz.getHyfl());
					w.setLjl(isNull(ljl,i));
					w.setQygm(zdwxyjbtz.getQygm());
					w.setQyid(zdwxyjbtz.getQyid());
					w.setQylx(zdwxyjbtz.getQylx());
					w.setQymc(zdwxyjbtz.getQymc());
					w.setQyzclx(zdwxyjbtz.getQyzclx());
					w.setScgy(isNull(scgy,i));
					w.setScyt(isNull(scyt,i));
					w.setSzzid(zdwxyjbtz.getSzzid());
					w.setSzzname(zdwxyjbtz.getSzzname());
					w.setUnbh(isNull(unbh,i));
					w.setWlzt(isNull(wlzt,i));
					w.setWxhxpcl(isNull(wxhxpcl,i));
					w.setWxhxpmc(isNull(wxhxpmc,i));
					w.setWxxlb(isNull(wxxlb,i));
					w.setZdrqcl(isNull(zdrqcl,i));
					w.setZdwxyid(zdwxyjbtz.getId());
					whpglbService.save(w);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		return RELOAD;
	}

	public String isNull(String[] object,int i)
	{
		String s;
		if(object.length < i+1)
		{
			s = "";
		}
		else
		{
			s = object[i];
		}
		return s;
	}
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zdwxyjbtzService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
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

	public Zdwxyjbtz getZdwxyjbtz(){
		return this.zdwxyjbtz;
	}

	public void setZdwxyjbtz(Zdwxyjbtz zdwxyjbtz){
		this.zdwxyjbtz = zdwxyjbtz;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryZdwxytysjStart(){
		return this.queryZdwxytysjStart;
	}

	public void setQueryZdwxytysjStart(Date queryZdwxytysjStart){
		this.queryZdwxytysjStart = queryZdwxytysjStart;
	}

	public Date getQueryZdwxytysjEnd(){
		return this.queryZdwxytysjEnd;
	}

	public void setQueryZdwxytysjEnd(Date queryZdwxytysjEnd){
		this.queryZdwxytysjEnd = queryZdwxytysjEnd;
	}

	public Whpglb getWhpglb() {
		return whpglb;
	}

	public void setWhpglb(Whpglb whpglb) {
		this.whpglb = whpglb;
	}

	public List<Whpglb> getWhpList() {
		return whpList;
	}

	public void setWhpList(List<Whpglb> whpList) {
		this.whpList = whpList;
	}

	public List<PhotoPic> getList1() {
		return list1;
	}

	public void setList1(List<PhotoPic> list1) {
		this.list1 = list1;
	}

	public List<PhotoPic> getList2() {
		return list2;
	}

	public void setList2(List<PhotoPic> list2) {
		this.list2 = list2;
	}

	public List<PhotoPic> getList3() {
		return list3;
	}

	public void setList3(List<PhotoPic> list3) {
		this.list3 = list3;
	}

}
