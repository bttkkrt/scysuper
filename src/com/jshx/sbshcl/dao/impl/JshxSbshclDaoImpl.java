package com.jshx.sbshcl.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sbshcl.dao.JshxSbshclDao;
import com.jshx.sbshcl.entity.JshxSbshcl;

@Component("jshxSbshclDao")
public class JshxSbshclDaoImpl extends BaseDaoImpl implements JshxSbshclDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxSbshclByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<JshxSbshcl> findJshxSbshcl(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxSbshclByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxSbshcl getById(String id)
	{
		return (JshxSbshcl)this.getObjectById(JshxSbshcl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxSbshcl jshxSbshcl)
	{
		jshxSbshcl.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxSbshcl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxSbshcl jshxSbshcl)
	{
		this.saveOrUpdateObject(jshxSbshcl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxSbshcl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxSbshcl jshxSbshcl = (JshxSbshcl)this.getObjectById(JshxSbshcl.class, id);
		jshxSbshcl.setDelFlag(1);
		this.saveObject(jshxSbshcl);
	}
}
