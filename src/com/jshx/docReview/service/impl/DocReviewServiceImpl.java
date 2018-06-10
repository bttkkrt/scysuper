package com.jshx.docReview.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.activiti.service.ActivityManageService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.docReview.dao.DocReviewDao;
import com.jshx.docReview.entity.DocReview;
import com.jshx.docReview.service.DocReviewService;
import com.jshx.module.infomation.dao.ContentInformationsDao;

@Service("docReviewService")
public class DocReviewServiceImpl extends BaseServiceImpl implements DocReviewService
{
	/**
	 * Dao类
	 */
	@Resource
	private DocReviewDao docReviewDao;
	
	
	@Autowired
	private ContentInformationsDao contentInformationsDao;
 
	@Autowired
	private ActivityManageService activityManageService;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return docReviewDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DocReview getById(String id)
	{
		return docReviewDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(DocReview docReview)
	{
		docReviewDao.save(docReview);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional(readOnly=false)
	public void update(DocReview docReview)
	{
		docReviewDao.update(docReview);
	}
	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional(readOnly=false)
	public void merge(DocReview docReview)
	{
		docReviewDao.merge(docReview);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=docReviewDao.findDocReview(paraMap);
		
		docReviewDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    docReviewDao.deleteWithFlag(id);
			}
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<String> findAllCandidates(  String workFlowModelId,
			String deptCode) {
		Map <String,Object>paraMap = new HashMap<String,Object>();
		List<String> users  = new ArrayList<String>();
		DocReview docReview = getById(workFlowModelId);
		while (users.size()<=0 &&deptCode.length()>=3) {
			paraMap.put("deptCode", deptCode);
			paraMap.put("ids", docReview.getUserIds().split(","));
			  users =  contentInformationsDao.findAllCandidates(paraMap);
			  deptCode = deptCode.substring(0,deptCode.length()-3);
		}
		//没有上级，关闭流程
		if(users.size()==0){
			docReview.setEnd(1);
			update(docReview);
			activityManageService.endProcess(docReview.getDefId());
		}
		return users;
	}
	
	@Transactional(readOnly=false)
	@Override
	public void endProcessHandler(String id,String loginUserId) {
	  DocReview	docReview =  getById(id);
		docReview.setEnd(1);
		docReview.setUpdateTime(new Date());
		docReview.setUpdateUserID(loginUserId);
		docReviewDao.update(docReview);
	}
}
