/**
 * Class Name : SafetyEvaluationReportServiceImpl
 * Class Description：安全现状评价报告备案事项通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.safetyEvaluationReport.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safetyEvaluationReport.dao.SafetyEvaluationReportDao;
import com.jshx.safetyEvaluationReport.entity.SafetyEvaluationReport;
import com.jshx.safetyEvaluationReport.service.SafetyEvaluationReportService;

@Service("safetyEvaluationReportService")
public class SafetyEvaluationReportServiceImpl extends BaseServiceImpl implements SafetyEvaluationReportService
{
	/**
	 * Dao类
	 */
	@Resource
	private SafetyEvaluationReportDao safetyEvaluationReportDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return safetyEvaluationReportDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public SafetyEvaluationReport getById(String id)
	{
		return safetyEvaluationReportDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(SafetyEvaluationReport safetyEvaluationReport)
	{
		safetyEvaluationReportDao.save(safetyEvaluationReport);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(SafetyEvaluationReport safetyEvaluationReport)
	{
		safetyEvaluationReportDao.update(safetyEvaluationReport);
	}

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=safetyEvaluationReportDao.findSafetyEvaluationReport(paraMap);
		
		safetyEvaluationReportDao.removeAll(objects);
	}

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
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
				    safetyEvaluationReportDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<SafetyEvaluationReport> findSafetyEvaluationReport(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return safetyEvaluationReportDao.findSafetyEvaluationReport(paraMap);
	}
}
