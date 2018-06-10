package com.jshx.tzgzs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzgzs.dao.HearingTellDao;
import com.jshx.tzgzs.entity.HearingTell;

@Component("hearingTellDao")
public class HearingTellDaoImpl extends BaseDaoImpl implements HearingTellDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHearingTellByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<HearingTell> findHearingTell(Map<String, Object> paraMap){
		return this.findListByHqlId("findHearingTellByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HearingTell getById(String id)
	{
		return (HearingTell)this.getObjectById(HearingTell.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HearingTell hearingTell)
	{
		hearingTell.setId(null);
		this.saveOrUpdateObject(hearingTell);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HearingTell hearingTell)
	{
		this.saveOrUpdateObject(hearingTell);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HearingTell.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HearingTell hearingTell = (HearingTell)this.getObjectById(HearingTell.class, id);
		hearingTell.setDelFlag(1);
		this.saveObject(hearingTell);
	}
}
