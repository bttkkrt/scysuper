package com.jshx.reviewLog.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.reviewLog.dao.ReviewLogDao;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;

@Service("reviewLogService")
public class ReviewLogServiceImpl extends BaseServiceImpl implements ReviewLogService
{
	/**
	 * Dao类
	 */
	@Resource
	private ReviewLogDao reviewLogDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return reviewLogDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReviewLog getById(String id)
	{
		return reviewLogDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(ReviewLog reviewLog)
	{
		reviewLogDao.save(reviewLog);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(ReviewLog reviewLog)
	{
		reviewLogDao.update(reviewLog);
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
		List objects=reviewLogDao.findReviewLog(paraMap);
		
		reviewLogDao.removeAll(objects);
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
				    reviewLogDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 保存审核日志并生产下一级代表审核信息
	 * @param model 信息
	 */
	@Transactional
	public String saveReviewLogAndSetNextTask(ReviewLog reviewLog){
		String info="保存审核日志成功";
		try {
			//更新本级审核日志
			reviewLogDao.update(reviewLog);
			
			//生产下一级任务
			if(!"1".equals(reviewLog.getDutyFlag())){
				ReviewLog newReviewLog = new ReviewLog();
				newReviewLog.setItemId(reviewLog.getItemId());
				newReviewLog.setDelFlag(0);
				newReviewLog.setState("0");
				newReviewLog.setItemType(reviewLog.getItemType());
				newReviewLog.setDutyFlag((Integer.parseInt(reviewLog.getDutyFlag())-1)+"");
				newReviewLog.setStartTime(new Date());
				reviewLogDao.save(newReviewLog);
			}
		} catch (Exception e) {
			info="保存审核日志失败";
			e.printStackTrace();
		}
		
		return info;
	}
	
	/**
	 * 重新生成审核任务
	 * @param model 信息
	 */
	@Transactional
	public void saveNewTask(ReviewLog reviewLog)
	{
		//删除原审核任务
//		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("itemId", reviewLog.getItemId());
//		List list = reviewLogDao.findReviewLog(paraMap);
//		for (Object object : list) {
//			ReviewLog tempRl = (ReviewLog)object;
//			reviewLogDao.deleteWithFlag(tempRl.getId());
//		}
		//生成审核任务
//		reviewLog.setDelFlag(0);
//		reviewLog.setState("0");
		reviewLogDao.save(reviewLog);
	}
}
