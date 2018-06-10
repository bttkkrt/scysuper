package com.jshx.dangerStorge.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dangerStorge.entity.Dangerstorge;
import com.jshx.dangerStorge.dao.DangerstorgeDao;

@Component("dangerstorgeDao")
public class DangerstorgeDaoImpl extends BaseDaoImpl implements DangerstorgeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDangerstorgeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDangerstorge(Map<String, Object> paraMap){
		return this.findListByHqlId("findDangerstorgeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dangerstorge getById(String id)
	{
		return (Dangerstorge)this.getObjectById(Dangerstorge.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dangerstorge dangerstorge)
	{
		dangerstorge.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dangerstorge);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dangerstorge dangerstorge)
	{
		this.saveOrUpdateObject(dangerstorge);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dangerstorge.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dangerstorge dangerstorge = (Dangerstorge)this.getObjectById(Dangerstorge.class, id);
		dangerstorge.setDelFlag(1);
		this.saveObject(dangerstorge);
	}
}
