package com.jshx.factorypicture.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.factorypicture.dao.FactorypictureDao;
import com.jshx.factorypicture.entity.Factorypicture;

@Component("factorypictureDao")
public class FactorypictureDaoImpl extends BaseDaoImpl implements FactorypictureDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findFactorypictureByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Factorypicture> findFactorypicture(Map<String, Object> paraMap){
		return this.findListByHqlId("findFactorypictureByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Factorypicture getById(String id)
	{
		return (Factorypicture)this.getObjectById(Factorypicture.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Factorypicture factorypicture)
	{
		factorypicture.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(factorypicture);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Factorypicture factorypicture)
	{
		this.saveOrUpdateObject(factorypicture);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Factorypicture.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Factorypicture factorypicture = (Factorypicture)this.getObjectById(Factorypicture.class, id);
		factorypicture.setDelFlag(1);
		this.saveObject(factorypicture);
	}
}
