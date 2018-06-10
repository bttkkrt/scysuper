/**
 * Class Name: MainPersonReceiptServiceImpl
 * Class Description：主要负责人安全生产履职情况报告回执
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.mainPersonReceipt.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.mainPersonReceipt.dao.MainPersonReceiptDao;
import com.jshx.mainPersonReceipt.entity.MainPersonReceipt;
import com.jshx.mainPersonReceipt.service.MainPersonReceiptService;

@Service("mainPersonReceiptService")
public class MainPersonReceiptServiceImpl extends BaseServiceImpl implements MainPersonReceiptService
{
	/**
	 * Dao类
	 */
	@Resource
	private MainPersonReceiptDao mainPersonReceiptDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return mainPersonReceiptDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public MainPersonReceipt getById(String id)
	{
		return mainPersonReceiptDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(MainPersonReceipt mainPersonReceipt)
	{
		mainPersonReceiptDao.save(mainPersonReceipt);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(MainPersonReceipt mainPersonReceipt)
	{
		mainPersonReceiptDao.update(mainPersonReceipt);
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
		List objects=mainPersonReceiptDao.findMainPersonReceipt(paraMap);
		
		mainPersonReceiptDao.removeAll(objects);
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
				    mainPersonReceiptDao.deleteWithFlag(id);
			}
		}
	}
}
