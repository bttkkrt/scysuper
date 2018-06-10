package com.jshx.xkzsb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xkzsb.dao.JshxXkzsbDao;
import com.jshx.xkzsb.entity.JshxXkzsb;

@Component("jshxXkzsbDao")
public class JshxXkzsbDaoImpl extends BaseDaoImpl implements JshxXkzsbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxXkzsbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<JshxXkzsb> findJshxXkzsb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxXkzsbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxXkzsb getById(String id)
	{
		return (JshxXkzsb)this.getObjectById(JshxXkzsb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxXkzsb jshxXkzsb)
	{
		jshxXkzsb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxXkzsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxXkzsb jshxXkzsb)
	{
		this.saveOrUpdateObject(jshxXkzsb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxXkzsb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxXkzsb jshxXkzsb = (JshxXkzsb)this.getObjectById(JshxXkzsb.class, id);
		jshxXkzsb.setDelFlag(1);
		this.saveObject(jshxXkzsb);
	}
}
