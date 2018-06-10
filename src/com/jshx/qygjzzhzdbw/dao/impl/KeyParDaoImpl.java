package com.jshx.qygjzzhzdbw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qygjzzhzdbw.entity.KeyPar;
import com.jshx.qygjzzhzdbw.dao.KeyParDao;

@Component("keyParDao")
public class KeyParDaoImpl extends BaseDaoImpl implements KeyParDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findKeyParByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findKeyPar(Map<String, Object> paraMap){
		return this.findListByHqlId("findKeyParByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public KeyPar getById(String id)
	{
		return (KeyPar)this.getObjectById(KeyPar.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(KeyPar keyPar)
	{
		keyPar.setId(null);
		this.saveOrUpdateObject(keyPar);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(KeyPar keyPar)
	{
		this.saveOrUpdateObject(keyPar);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(KeyPar.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		KeyPar keyPar = (KeyPar)this.getObjectById(KeyPar.class, id);
		keyPar.setDelFlag(1);
		this.saveObject(keyPar);
	}
}
