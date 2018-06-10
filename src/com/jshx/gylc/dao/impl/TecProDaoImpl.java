package com.jshx.gylc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gylc.entity.TecPro;
import com.jshx.gylc.dao.TecProDao;

@Component("tecProDao")
public class TecProDaoImpl extends BaseDaoImpl implements TecProDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findTecProByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTecPro(Map<String, Object> paraMap){
		return this.findListByHqlId("findTecProByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TecPro getById(String id)
	{
		return (TecPro)this.getObjectById(TecPro.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TecPro tecPro)
	{
		tecPro.setId(null);
		this.saveOrUpdateObject(tecPro);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TecPro tecPro)
	{
		this.saveOrUpdateObject(tecPro);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(TecPro.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		TecPro tecPro = (TecPro)this.getObjectById(TecPro.class, id);
		tecPro.setDelFlag(1);
		this.saveObject(tecPro);
	}
}
