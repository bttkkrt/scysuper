/**
 * Class Name: OccupationalEvaluateReceiptServiceImpl
 * Class Description：职业病危害预评价报告表备案通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.occupationalEvaluateReceipt.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.occupationalEvaluateReceipt.dao.OccupationalEvaluateReceiptDao;
import com.jshx.occupationalEvaluateReceipt.entity.OccupationalEvaluateReceipt;
import com.jshx.occupationalEvaluateReceipt.service.OccupationalEvaluateReceiptService;

@Service("occupationalEvaluateReceiptService")
public class OccupationalEvaluateReceiptServiceImpl extends BaseServiceImpl implements OccupationalEvaluateReceiptService
{
	/**
	 * Dao类
	 */
	@Resource
	private OccupationalEvaluateReceiptDao occupationalEvaluateReceiptDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return occupationalEvaluateReceiptDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public OccupationalEvaluateReceipt getById(String id)
	{
		return occupationalEvaluateReceiptDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(OccupationalEvaluateReceipt occupationalEvaluateReceipt)
	{
		occupationalEvaluateReceiptDao.save(occupationalEvaluateReceipt);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(OccupationalEvaluateReceipt occupationalEvaluateReceipt)
	{
		occupationalEvaluateReceiptDao.update(occupationalEvaluateReceipt);
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
		List objects=occupationalEvaluateReceiptDao.findOccupationalEvaluateReceipt(paraMap);
		
		occupationalEvaluateReceiptDao.removeAll(objects);
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
				    occupationalEvaluateReceiptDao.deleteWithFlag(id);
			}
		}
	}
}
