package com.jshx.ygtjbghzb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ygtjbghzb.entity.Ygtjbghzb;
import com.jshx.ygtjbghzb.dao.YgtjbghzbDao;

@Component("ygtjbghzbDao")
public class YgtjbghzbDaoImpl extends BaseDaoImpl implements YgtjbghzbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findYgtjbghzbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Ygtjbghzb> findYgtjbghzb(Map<String, Object> paraMap){
		return this.findListByHqlId("findYgtjbghzbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Ygtjbghzb getById(String id)
	{
		return (Ygtjbghzb)this.getObjectById(Ygtjbghzb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Ygtjbghzb ygtjbghzb)
	{
		ygtjbghzb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(ygtjbghzb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Ygtjbghzb ygtjbghzb)
	{
		this.saveOrUpdateObject(ygtjbghzb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Ygtjbghzb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Ygtjbghzb ygtjbghzb = (Ygtjbghzb)this.getObjectById(Ygtjbghzb.class, id);
		ygtjbghzb.setDelFlag(1);
		this.saveObject(ygtjbghzb);
	}
}
