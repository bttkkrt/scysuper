package com.jshx.dcbg.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dcbg.dao.DcbgDao;
import com.jshx.dcbg.entity.Dcbg;

@Component("dcbgDao")
public class DcbgDaoImpl extends BaseDaoImpl implements DcbgDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDcbgByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Dcbg> findDcbg(Map<String, Object> paraMap){
		return this.findListByHqlId("findDcbgByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dcbg getById(String id)
	{
		return (Dcbg)this.getObjectById(Dcbg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dcbg dcbg)
	{
		dcbg.setId(null);
		this.saveOrUpdateObject(dcbg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dcbg dcbg)
	{
		this.saveOrUpdateObject(dcbg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dcbg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dcbg dcbg = (Dcbg)this.getObjectById(Dcbg.class, id);
		dcbg.setDelFlag(1);
		this.saveObject(dcbg);
	}
}
