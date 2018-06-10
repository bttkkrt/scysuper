package com.jshx.log.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.dao.BehaviorLogParamDao;
import com.jshx.log.entity.BehaviorLogParam;

@Component("behaviorLogParamDao")
public class BehaviorLogParamDaoImpl extends BaseDaoImpl implements BehaviorLogParamDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findBehaviorLogParamByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * 
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public List<BehaviorLogParam> findBehaviorLogParam(Map<String, Object> paraMap){
		return this.findListByHqlId("findBehaviorLogParamByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public BehaviorLogParam getById(String id)
	{
		return (BehaviorLogParam)this.getObjectById(BehaviorLogParam.class, id);
	}

	/**
	 * 保存日志参数
	 * 
	 * @param behaviorLogParam 信息
	 */
	public void save(BehaviorLogParam behaviorLogParam)
	{
		behaviorLogParam.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(behaviorLogParam);
	}

	/**
	 * 修改日志参数
	 * 
	 * @param behaviorLogParam 信息
	 */
	public void update(BehaviorLogParam behaviorLogParam)
	{
		this.saveOrUpdateObject(behaviorLogParam);
	}

	/**
	 * 物理删除信息
	 * @param id 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(BehaviorLogParam.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param id 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		BehaviorLogParam behaviorLogParam = (BehaviorLogParam)this.getObjectById(BehaviorLogParam.class, id);
		behaviorLogParam.setDelFlag(1);
		this.saveObject(behaviorLogParam);
	}
}
