/**
 * Class Name : MainPersonReceiptDaoImpl
 * Class Description：主要负责人安全生产履职情况报告回执
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.mainPersonReceipt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.mainPersonReceipt.dao.MainPersonReceiptDao;
import com.jshx.mainPersonReceipt.entity.MainPersonReceipt;

@Component("mainPersonReceiptDao")
public class MainPersonReceiptDaoImpl extends BaseDaoImpl implements MainPersonReceiptDao
{
	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMainPersonReceiptByMap", paraMap, page);
	}
	
	/**
	 * Function Name: findMainPersonReceipt
	 * Function Description：查询所有记录
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findMainPersonReceipt(Map<String, Object> paraMap){
		return this.findListByHqlId("findMainPersonReceiptByMap", paraMap);
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
		return (MainPersonReceipt)this.getObjectById(MainPersonReceipt.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(MainPersonReceipt mainPersonReceipt)
	{
		mainPersonReceipt.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(mainPersonReceipt);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(MainPersonReceipt mainPersonReceipt)
	{
		this.saveOrUpdateObject(mainPersonReceipt);
	}

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void delete(String id)
	{
		this.removeObjectById(MainPersonReceipt.class, id);
	}

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void deleteWithFlag(String id)
	{
		MainPersonReceipt mainPersonReceipt = (MainPersonReceipt)this.getObjectById(MainPersonReceipt.class, id);
		mainPersonReceipt.setDelFlag(1);
		this.saveObject(mainPersonReceipt);
	}
}
