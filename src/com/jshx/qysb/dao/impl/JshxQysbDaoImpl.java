package com.jshx.qysb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qysb.dao.JshxQysbDao;
import com.jshx.qysb.entity.JshxQysb;

@Component("jshxQysbDao")
public class JshxQysbDaoImpl extends BaseDaoImpl implements JshxQysbDao
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
		return this.findPageByHqlId("findJshxQysbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public List findJshxQysb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxQysbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxQysb getById(String id)
	{
		return (JshxQysb)this.getObjectById(JshxQysb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void save(JshxQysb jshxQysb)
	{
		jshxQysb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxQysb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void update(JshxQysb jshxQysb)
	{
		this.saveOrUpdateObject(jshxQysb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxQysb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void deleteWithFlag(String id)
	{
		JshxQysb jshxQysb = (JshxQysb)this.getObjectById(JshxQysb.class, id);
		jshxQysb.setDelFlag(1);
		this.saveObject(jshxQysb);
	}
}
