package com.jshx.showGuid.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.showGuid.dao.ShowGuidDao;
import com.jshx.showGuid.entity.ShowGuid;

@Component("showGuidDao")
public class ShowGuidDaoImpl extends BaseDaoImpl implements ShowGuidDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findShowGuidByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<String> findShowGuid(Map<String, Object> paraMap){
		return sqlMapClientTemplate.queryForList("findShowGuid",paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ShowGuid getById(String id)
	{
		return (ShowGuid)this.getObjectById(ShowGuid.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ShowGuid showGuid)
	{
		showGuid.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(showGuid);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ShowGuid showGuid)
	{
		this.saveOrUpdateObject(showGuid);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ShowGuid.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ShowGuid showGuid = (ShowGuid)this.getObjectById(ShowGuid.class, id);
		showGuid.setDelFlag(1);
		this.saveObject(showGuid);
	}
}
