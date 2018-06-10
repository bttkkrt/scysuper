package com.jshx.yqjnfkspb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yqjnfkspb.entity.PosFinApp;
import com.jshx.yqjnfkspb.dao.PosFinAppDao;

@Component("posFinAppDao")
public class PosFinAppDaoImpl extends BaseDaoImpl implements PosFinAppDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPosFinAppByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PosFinApp> findPosFinApp(Map<String, Object> paraMap){
		return this.findListByHqlId("findPosFinAppByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PosFinApp getById(String id)
	{
		return (PosFinApp)this.getObjectById(PosFinApp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PosFinApp posFinApp)
	{
		posFinApp.setId(null);
		this.saveOrUpdateObject(posFinApp);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PosFinApp posFinApp)
	{
		this.saveOrUpdateObject(posFinApp);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PosFinApp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PosFinApp posFinApp = (PosFinApp)this.getObjectById(PosFinApp.class, id);
		posFinApp.setDelFlag(1);
		this.saveObject(posFinApp);
	}
}
