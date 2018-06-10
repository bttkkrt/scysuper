package com.jshx.rectifyOpinion.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.rectifyOpinion.entity.RectifyOpinion;
import com.jshx.rectifyOpinion.dao.RectifyOpinionDao;

@Component("rectifyOpinionDao")
public class RectifyOpinionDaoImpl extends BaseDaoImpl implements RectifyOpinionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRectifyOpinionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRectifyOpinion(Map<String, Object> paraMap){
		return this.findListByHqlId("findRectifyOpinionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RectifyOpinion getById(String id)
	{
		return (RectifyOpinion)this.getObjectById(RectifyOpinion.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RectifyOpinion rectifyOpinion)
	{
		rectifyOpinion.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(rectifyOpinion);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RectifyOpinion rectifyOpinion)
	{
		this.saveOrUpdateObject(rectifyOpinion);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RectifyOpinion.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RectifyOpinion rectifyOpinion = (RectifyOpinion)this.getObjectById(RectifyOpinion.class, id);
		rectifyOpinion.setDelFlag(1);
		this.saveObject(rectifyOpinion);
	}
}
