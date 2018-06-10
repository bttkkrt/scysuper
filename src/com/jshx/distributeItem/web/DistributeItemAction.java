package com.jshx.distributeItem.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class DistributeItemAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DistributeItem distributeItem = new DistributeItem();
	private SafeInspectDistribute safeInspectDistribute = new SafeInspectDistribute();
	
	/**
	 * 业务类
	 */
	@Autowired
	private DistributeItemService distributeItemService;
	@Autowired
	private SafeInspectDistributeService safeInspectDistributeService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private String companyFlag;
	private String reviewUserId;

	private List<Map<String,List>> totalTaskList = new ArrayList<Map<String,List>>();
	
	private List<DistributeItem> distList = new ArrayList<DistributeItem>();
	private List<List<String>> imgList = new ArrayList<List<String>>();
	private List<List<String>> reviewImgList = new ArrayList<List<String>>();
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String imgPath;//图片路径
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != distributeItem){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != distributeItem.getItem()) && (0 < distributeItem.getItem().trim().length())){
				paraMap.put("item", "%" + distributeItem.getItem().trim() + "%");
			}

			if ((null != distributeItem.getRequirement()) && (0 < distributeItem.getRequirement().trim().length())){
				paraMap.put("requirement", "%" + distributeItem.getRequirement().trim() + "%");
			}

			if ((null != distributeItem.getIsinspect()) && (0 < distributeItem.getIsinspect().trim().length())){
				paraMap.put("isinspect", "%" + distributeItem.getIsinspect().trim() + "%");
			}

			if ((null != distributeItem.getImage()) && (0 < distributeItem.getImage().trim().length())){
				paraMap.put("image", "%" + distributeItem.getImage().trim() + "%");
			}

			if ((null != distributeItem.getCount()) && (0 < distributeItem.getCount().trim().length())){
				paraMap.put("count", "%" + distributeItem.getCount().trim() + "%");
			}

			if ((null != distributeItem.getRemark()) && (0 < distributeItem.getRemark().trim().length())){
				paraMap.put("remark", "%" + distributeItem.getRemark().trim() + "%");
			}

		}
		
		pagination = distributeItemService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void safeDistList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != distributeItem){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != distributeItem.getItem()) && (0 < distributeItem.getItem().trim().length())){
				paraMap.put("item", "%" + distributeItem.getItem().trim() + "%");
			}

			if ((null != distributeItem.getRequirement()) && (0 < distributeItem.getRequirement().trim().length())){
				paraMap.put("requirement", "%" + distributeItem.getRequirement().trim() + "%");
			}

			if ((null != distributeItem.getIsinspect()) && (0 < distributeItem.getIsinspect().trim().length())){
				paraMap.put("isinspect", "%" + distributeItem.getIsinspect().trim() + "%");
			}

			if ((null != distributeItem.getImage()) && (0 < distributeItem.getImage().trim().length())){
				paraMap.put("image", "%" + distributeItem.getImage().trim() + "%");
			}

			if ((null != distributeItem.getCount()) && (0 < distributeItem.getCount().trim().length())){
				paraMap.put("count", "%" + distributeItem.getCount().trim() + "%");
			}

			if ((null != distributeItem.getRemark()) && (0 < distributeItem.getRemark().trim().length())){
				paraMap.put("remark", "%" + distributeItem.getRemark().trim() + "%");
			}

		}
		
		pagination = distributeItemService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		User user = this.getLoginUser();
		CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
		this.companyFlag = company.getId();
		
		if((null != safeInspectDistribute)&&(null != safeInspectDistribute.getId())){
			safeInspectDistribute = safeInspectDistributeService.getById(safeInspectDistribute.getId());
			SafeInspectDistribute tempSd = safeInspectDistributeService.getById(safeInspectDistribute.getRootId());
			safeInspectDistribute.setInspectNum(tempSd.getInspectNum());
		}

		

		if(pagination==null){
			pagination = new Pagination(this.getRequest());
			pagination.setPageSize(1000);
		}
		Map<String, Object> sdParaMap = new HashMap<String, Object>();
		sdParaMap.put("rootId", safeInspectDistribute.getRootId());
		sdParaMap.put("personnel", safeInspectDistribute.getPersonnel());
		sdParaMap.put("taskTime", safeInspectDistribute.getTaskTime());
		pagination = safeInspectDistributeService.findByPage(pagination, sdParaMap);
		List sdList = pagination.getList();
		
		//获取各次安全检查项 
		for(int i=0; i<sdList.size();i++){
			SafeInspectDistribute tempsd = (SafeInspectDistribute)sdList.get(i);
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("distributeId", tempsd.getId());
			distList = distributeItemService.findDistributeItem(paraMap);
			
			for (DistributeItem tempDist : distList) {
				Map m = new HashMap();
				m.put("type", "aqjc");
				m.put("linkId", tempDist.getId());
				DataBean bean = httpService.getPhotoListByType(m);
				List<String> tempImgList = new ArrayList<String>();
				if(null != bean.getRname() && !bean.getRname().isEmpty()){
					String[] imgArray = bean.getRname().split("\\,");
					for (int j = 0; j < imgArray.length; j++) {
						String tempImg = imgArray[j].trim();
						tempImg = "/upload/photo/"+tempImg;
						tempImgList.add(tempImg);
					}
				}
				imgList.add(tempImgList);
				
				reviewUserId = tempDist.getReviewUserId();
				if(null != tempDist.getReviewUserId() && !"".equals(tempDist.getReviewUserId())){
					Map rm = new HashMap();
					rm.put("type", "aqjc");
					rm.put("linkId", tempDist.getId() + tempDist.getReviewUserId());
					DataBean rbean = httpService.getPhotoListByType(rm);
					List<String> rtempImgList = new ArrayList<String>();
					if(null != rbean.getRname() && !rbean.getRname().isEmpty()){
						String[] imgArray = rbean.getRname().split("\\,");
						for (int j = 0; j < imgArray.length; j++) {
							String tempImg = imgArray[j].trim();
							tempImg = "/upload/photo/"+tempImg;
							rtempImgList.add(tempImg);
						}
					}
					reviewImgList.add(rtempImgList);
				}
			}
			
			Map<String,List> taskMap = new HashMap<String, List>();
			taskMap.put("distList", distList);
			taskMap.put("imgList", imgList);
			taskMap.put("reviewImgList", reviewImgList);
			totalTaskList.add(taskMap);
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
		if ("add".equalsIgnoreCase(this.flag)){
			distributeItem.setDeptId(this.getLoginUserDepartmentId());
			distributeItem.setDelFlag(0);
			distributeItemService.save(distributeItem);
		}else{
			distributeItemService.update(distributeItem);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			distributeItemService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String viewImg() throws Exception{
	    return VIEW;
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

	public DistributeItem getDistributeItem(){
		return this.distributeItem;
	}

	public void setDistributeItem(DistributeItem distributeItem){
		this.distributeItem = distributeItem;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public SafeInspectDistribute getSafeInspectDistribute() {
		return safeInspectDistribute;
	}

	public void setSafeInspectDistribute(SafeInspectDistribute safeInspectDistribute) {
		this.safeInspectDistribute = safeInspectDistribute;
	}

	public String getCompanyFlag() {
		return companyFlag;
	}

	public void setCompanyFlag(String companyFlag) {
		this.companyFlag = companyFlag;
	}

	public List<DistributeItem> getDistList() {
		return distList;
	}

	public void setDistList(List<DistributeItem> distList) {
		this.distList = distList;
	}

	public List<List<String>> getImgList() {
		return imgList;
	}

	public void setImgList(List<List<String>> imgList) {
		this.imgList = imgList;
	}

	public List<Map<String, List>> getTotalTaskList() {
		return totalTaskList;
	}

	public void setTotalTaskList(List<Map<String, List>> totalTaskList) {
		this.totalTaskList = totalTaskList;
	}

	public String getImgPath()
	{
		return imgPath;
	}

	public void setImgPath(String imgPath)
	{
		this.imgPath = imgPath;
	}

	public String getReviewUserId() {
		return reviewUserId;
	}

	public void setReviewUserId(String reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

}
