package com.jshx.checkBasic.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.checkBasic.dao.CheckBasicDao;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;

@Component("checkBasicDao")
public class CheckBasicDaoImpl extends BaseDaoImpl implements CheckBasicDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckBasicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckBasic(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckBasicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckBasic getById(String id)
	{
		return (CheckBasic)this.getObjectById(CheckBasic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckBasic checkBasic)
	{
		checkBasic.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(checkBasic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckBasic checkBasic)
	{
		this.saveOrUpdateObject(checkBasic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckBasic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckBasic checkBasic = (CheckBasic)this.getObjectById(CheckBasic.class, id);
		checkBasic.setDelFlag(1);
		this.saveObject(checkBasic);
	}

	@Override
	public Pagination findRectifyCheckByPage(Pagination pagination, Map<String, Object> paraMap)
	{
		return this.findPageBySqlId("findRectifyCheckBySQL", paraMap, pagination);
	}

	@Override
	public List getMaxRectifyNum(Map<String, Object> paraMap)
	{
		return this.findListBySqlId("getMaxRectifyNum", paraMap);
	}

}
