package com.jshx.regionCode.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.regionCode.dao.RegionCodeDao;
import com.jshx.regionCode.entity.RegionCode;
import com.jshx.regionCode.service.RegionCodeService;

@Service("regionCodeService")
public class RegionCodeServiceImpl extends BaseServiceImpl implements RegionCodeService
{
	/**
	 * Dao类
	 */
	@Resource
	private RegionCodeDao regionCodeDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return regionCodeDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RegionCode getById(String id)
	{
		return regionCodeDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(RegionCode regionCode)
	{
		regionCodeDao.save(regionCode);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(RegionCode regionCode)
	{
		regionCodeDao.update(regionCode);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=regionCodeDao.findRegionCode(paraMap);
		
		regionCodeDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    regionCodeDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public String findCodeByDept(String deptCode)
	{
		return regionCodeDao.findCodeByDept(deptCode);
	}
}
