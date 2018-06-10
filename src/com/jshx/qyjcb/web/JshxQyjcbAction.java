/**
 * Class Name: JshxQyjcbAction
 * Class Description：企业自查列表
 */
package com.jshx.qyjcb.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.qyjcb.entity.JshxQyjcb;
import com.jshx.qyjcb.service.JshxQyjcbService;
import com.jshx.qyjcbData.entity.QyjcbData;
import com.jshx.qyjcbData.service.QyjcbDataService;

public class JshxQyjcbAction extends BaseAction
{
	private String[] datas = 
		new String[]{"重要车间、部位安全运行","工艺技术管理制度执行","设施、设备管理制度执行","开车、停车规程执行","检修、受限作业制度执行","“六防”工作措施落实","职工培训、持证上岗","应急救援预案制定落实","工程项目建设管理","废弃物处置安全管理","危险化学品运输管理","合计"};

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxQyjcb jshxQyjcb = new JshxQyjcb();
	
	private QyjcbData qyjcbData = new QyjcbData();
	
	private List<QyjcbData>  qyjcbDatas= new ArrayList<QyjcbData>();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxQyjcbService jshxQyjcbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private QyjcbDataService qyjcbDataService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private String queryTbsjStart;

	private String queryTbsjEnd;
	
	private String exportYear;

	/**
	 * 初始化列表
	 * @return
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
		    
		if(null != jshxQyjcb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryTbsjStart){
				paraMap.put("startTbsj", queryTbsjStart);
			}

			if (null != queryTbsjEnd){
				paraMap.put("endTbsj", queryTbsjEnd);
			}
			if ((null != jshxQyjcb.getQymc()) && (0 < jshxQyjcb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxQyjcb.getQymc().trim() + "%");
			}
			if ((null != jshxQyjcb.getSzzid()) && (0 < jshxQyjcb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxQyjcb.getSzzid().trim() );
			}
			if ((null != jshxQyjcb.getSzc() )&& (0 < jshxQyjcb.getSzc().trim().length())){
				paraMap.put("szc",jshxQyjcb.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxQyjcbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if((null != jshxQyjcb)&&(null != jshxQyjcb.getId())){
			jshxQyjcb = jshxQyjcbService.getById(jshxQyjcb.getId());
				 paraMap.put("linkId", jshxQyjcb.getId());
			}else{
				 paraMap.put("linkId", "linkId");
			}
		qyjcbDatas = qyjcbDataService.findQyjcbData(paraMap);
		if(qyjcbDatas==null){
			qyjcbDatas = new ArrayList<QyjcbData>();
			for(int i=0;i<16;i++){
				QyjcbData sgtjData = new QyjcbData();
				qyjcbDatas.add(sgtjData);
			}
			
		}else if(qyjcbDatas.size()<16){
			for(int i=(qyjcbDatas.size());i<16;i++){
				QyjcbData sgtjData = new QyjcbData();
				qyjcbDatas.add(sgtjData);
			}
		}
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			try {
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				jshxQyjcb.setSzzid(dept.getDeptCode());
				jshxQyjcb.setSzzname(dept.getDeptName());
				jshxQyjcb.setQyid(company.getId());
				jshxQyjcb.setQymc(company.getCompanyname());
				jshxQyjcb.setDeptId(this.getLoginUserDepartmentId());
				jshxQyjcb.setDelFlag(0);
				jshxQyjcb.setCreateUserID(this.getLoginUserId());
				jshxQyjcb.setCreateTime(new Date());
				jshxQyjcb.setQylx(company.getQylx());
				jshxQyjcb.setHyfl(company.getHyfl());
				jshxQyjcb.setQygm(company.getQygm());
				jshxQyjcb.setQyzclx(company.getQyzclx());
				jshxQyjcb.setDeptId(this.getLoginUserDepartmentId());
				jshxQyjcb.setDelFlag(0);
				jshxQyjcb.setIfwhpqylx(company.getIfwhpqylx());
				jshxQyjcb.setIfyhbzjyqy(company.getIfyhbzjyqy());
				jshxQyjcb.setIfzywhqylx(company.getIfzywhqylx());
				jshxQyjcb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				jshxQyjcb.setSzc(company.getSzc());
				jshxQyjcb.setSzcname(company.getSzcname());
			} catch (RuntimeException e) {
				e.printStackTrace();
			}//企业名称
			jshxQyjcb.setTbsj(new Date());
			jshxQyjcb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxQyjcb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxQyjcbService.save(jshxQyjcb);
			for(int i=0;i<16;i++){
				qyjcbDataService.save(convertObjectData(jshxQyjcb.getId(),i,this.flag));
			}
		}else{
			jshxQyjcb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxQyjcb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxQyjcbService.update(jshxQyjcb);
			for(int i=0;i<16;i++){
				qyjcbDataService.update(convertObjectData(jshxQyjcb.getId(),i,this.flag));
			}
		}
		return RELOAD;
	}
	/**
	 * 给指定对象赋值 李军 2013-08-19
	 */
	public  QyjcbData convertObjectData(String linkId,int index,String flag){
		QyjcbData aqsc = new QyjcbData();
		aqsc.setLinkid(linkId);
		aqsc.setSort((index+1)+"");
		aqsc.setDelFlag(0);
		if(!"add".equals(flag)){
			aqsc.setId(StringTools.getStrByIndex(qyjcbData.getId(),index));
			aqsc.setCreateTime(qyjcbData.getCreateTime());
		}
		aqsc.setData_1(StringTools.getStrByIndex(qyjcbData.getData_1(),index));
		aqsc.setData_2(StringTools.getStrByIndex(qyjcbData.getData_2(),index));
		aqsc.setData_3(StringTools.getStrByIndex(qyjcbData.getData_3(),index));
		aqsc.setData_4(StringTools.getStrByIndex(qyjcbData.getData_4(),index));
		aqsc.setData_5(StringTools.getStrByIndex(qyjcbData.getData_5(),index));
		aqsc.setData_6(StringTools.getStrByIndex(qyjcbData.getData_6(),index));
		aqsc.setData_7(StringTools.getStrByIndex(qyjcbData.getData_7(),index));
		aqsc.setData_8(StringTools.getStrByIndex(qyjcbData.getData_8(),index));
		aqsc.setData_9(StringTools.getStrByIndex(qyjcbData.getData_9(),index));
		aqsc.setData_10(StringTools.getStrByIndex(qyjcbData.getData_10(),index));
		aqsc.setData_11(StringTools.getStrByIndex(qyjcbData.getData_11(),index));
		aqsc.setData_12(StringTools.getStrByIndex(qyjcbData.getData_12(),index));
		return aqsc;
	} 
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxQyjcbService.deleteWithFlag(ids);
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

	public JshxQyjcb getJshxQyjcb(){
		return this.jshxQyjcb;
	}

	public void setJshxQyjcb(JshxQyjcb jshxQyjcb){
		this.jshxQyjcb = jshxQyjcb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public String getQueryTbsjStart(){
		return this.queryTbsjStart;
	}

	public void setQueryTbsjStart(String queryTbsjStart){
		this.queryTbsjStart = queryTbsjStart;
	}

	public String getQueryTbsjEnd(){
		return this.queryTbsjEnd;
	}

	public void setQueryTbsjEnd(String queryTbsjEnd){
		this.queryTbsjEnd = queryTbsjEnd;
	}
	public String[] getDatas() {
		return datas;
	}
	public void setDatas(String[] datas) {
		this.datas = datas;
	}
	public QyjcbData getQyjcbData() {
		return qyjcbData;
	}
	public void setQyjcbData(QyjcbData qyjcbData) {
		this.qyjcbData = qyjcbData;
	}
	public List<QyjcbData> getQyjcbDatas() {
		return qyjcbDatas;
	}
	public void setQyjcbDatas(List<QyjcbData> qyjcbDatas) {
		this.qyjcbDatas = qyjcbDatas;
	}
	public String getExportYear() {
		return exportYear;
	}
	public void setExportYear(String exportYear) {
		this.exportYear = exportYear;
	}

}
