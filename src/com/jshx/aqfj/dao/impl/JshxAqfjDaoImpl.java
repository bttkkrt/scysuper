package com.jshx.aqfj.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.aqfj.dao.JshxAqfjDao;
import com.jshx.aqfj.entity.JshxAqfj;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;

@Component("jshxAqfjDao")
public class JshxAqfjDaoImpl extends BaseDaoImpl implements JshxAqfjDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxAqfjByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public List findJshxAqfj(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxAqfjByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxAqfj getById(String id)
	{
		return (JshxAqfj)this.getObjectById(JshxAqfj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void save(JshxAqfj jshxAqfj)
	{
		jshxAqfj.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxAqfj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void update(JshxAqfj jshxAqfj)
	{
		this.saveOrUpdateObject(jshxAqfj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxAqfj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void deleteWithFlag(String id)
	{
		JshxAqfj jshxAqfj = (JshxAqfj)this.getObjectById(JshxAqfj.class, id);
		jshxAqfj.setDelFlag(1);
		this.saveObject(jshxAqfj);
	}
}
