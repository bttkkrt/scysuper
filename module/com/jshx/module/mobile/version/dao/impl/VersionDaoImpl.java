package com.jshx.module.mobile.version.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.mobile.version.entity.Version;
import com.jshx.module.mobile.version.dao.VersionDao;

@Component("versionDao")
public class VersionDaoImpl extends BaseDaoImpl implements VersionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findVersionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findVersion(Map<String, Object> paraMap){
		return this.findListByHqlId("findVersionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Version getById(String id)
	{
		return (Version)this.getObjectById(Version.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Version version)
	{
		version.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(version);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Version version)
	{
		this.saveOrUpdateObject(version);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Version.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Version version = (Version)this.getObjectById(Version.class, id);
		version.setDelFlag(1);
		this.saveObject(version);
	}
}
