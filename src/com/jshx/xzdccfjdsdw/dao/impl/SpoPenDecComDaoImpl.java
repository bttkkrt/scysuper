package com.jshx.xzdccfjdsdw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xzdccfjdsdw.entity.SpoPenDecCom;
import com.jshx.xzdccfjdsdw.dao.SpoPenDecComDao;

@Component("spoPenDecComDao")
public class SpoPenDecComDaoImpl extends BaseDaoImpl implements SpoPenDecComDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSpoPenDecComByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SpoPenDecCom> findSpoPenDecCom(Map<String, Object> paraMap){
		return this.findListByHqlId("findSpoPenDecComByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SpoPenDecCom getById(String id)
	{
		return (SpoPenDecCom)this.getObjectById(SpoPenDecCom.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SpoPenDecCom spoPenDecCom)
	{
		spoPenDecCom.setId(null);
		this.saveOrUpdateObject(spoPenDecCom);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SpoPenDecCom spoPenDecCom)
	{
		this.saveOrUpdateObject(spoPenDecCom);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SpoPenDecCom.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SpoPenDecCom spoPenDecCom = (SpoPenDecCom)this.getObjectById(SpoPenDecCom.class, id);
		spoPenDecCom.setDelFlag(1);
		this.saveObject(spoPenDecCom);
	}
}
