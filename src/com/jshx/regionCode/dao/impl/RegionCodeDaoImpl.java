package com.jshx.regionCode.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.regionCode.entity.RegionCode;
import com.jshx.regionCode.dao.RegionCodeDao;

@Component("regionCodeDao")
public class RegionCodeDaoImpl extends BaseDaoImpl implements RegionCodeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRegionCodeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRegionCode(Map<String, Object> paraMap){
		return this.findListByHqlId("findRegionCodeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RegionCode getById(String id)
	{
		return (RegionCode)this.getObjectById(RegionCode.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RegionCode regionCode)
	{
		regionCode.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(regionCode);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RegionCode regionCode)
	{
		this.saveOrUpdateObject(regionCode);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RegionCode.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RegionCode regionCode = (RegionCode)this.getObjectById(RegionCode.class, id);
		regionCode.setDelFlag(1);
		this.saveObject(regionCode);
	}

	@Override
	public String findCodeByDept(String deptCode)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptCode", deptCode + "%");
		List regionList =  this.findListByHqlId("findRegionCodeByMap", paraMap);
		if(regionList.size() > 0)
		{
			RegionCode regionCode = (RegionCode)regionList.get(0);
			return regionCode.getRegionCode();
		}
		else
		{
			//没有对应编号的用X代替
			return "X";
		}
	}
}
