package com.wzxx.xyjc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.xyjc.dao.XyjcDao;
import com.wzxx.xyjc.entity.Xyjc;

@Component("xyjcDao")
public class XyjcDaoImpl extends BaseDaoImpl implements XyjcDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findXyjcByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findXyjc(Map<String, Object> paraMap){
		return this.findListByHqlId("findXyjcByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Xyjc getById(String id)
	{
		return (Xyjc)this.getObjectById(Xyjc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Xyjc xyjc)
	{
		xyjc.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(xyjc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Xyjc xyjc)
	{
		this.saveOrUpdateObject(xyjc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Xyjc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Xyjc xyjc = (Xyjc)this.getObjectById(Xyjc.class, id);
		xyjc.setDelFlag("1");
		this.saveObject(xyjc);
	}
	
}
