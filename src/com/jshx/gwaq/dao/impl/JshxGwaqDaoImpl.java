package com.jshx.gwaq.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gwaq.dao.JshxGwaqDao;
import com.jshx.gwaq.entity.JshxGwaq;

@Component("jshxGwaqDao")
public class JshxGwaqDaoImpl extends BaseDaoImpl implements JshxGwaqDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxGwaqByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public List findJshxGwaq(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxGwaqByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public JshxGwaq getById(String id)
	{
		return (JshxGwaq)this.getObjectById(JshxGwaq.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void save(JshxGwaq jshxGwaq)
	{
		jshxGwaq.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxGwaq);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void update(JshxGwaq jshxGwaq)
	{
		this.saveOrUpdateObject(jshxGwaq);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxGwaq.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void deleteWithFlag(String id)
	{
		JshxGwaq jshxGwaq = (JshxGwaq)this.getObjectById(JshxGwaq.class, id);
		jshxGwaq.setDelFlag(1);
		this.saveObject(jshxGwaq);
	}
}
