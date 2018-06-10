package com.jshx.aqscldjg.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscldjg.dao.ProLegOrgDao;
import com.jshx.aqscldjg.entity.ProLegOrg;
import com.jshx.aqscldjg.service.ProLegOrgService;

@Service("proLegOrgService")
public class ProLegOrgServiceImpl extends BaseServiceImpl implements ProLegOrgService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("proLegOrgDao")
	private ProLegOrgDao proLegOrgDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return proLegOrgDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProLegOrg getById(String id)
	{
		return proLegOrgDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(ProLegOrg proLegOrg)
	{
		proLegOrgDao.save(proLegOrg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(ProLegOrg proLegOrg)
	{
		proLegOrgDao.update(proLegOrg);
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
		List objects=proLegOrgDao.findProLegOrg(paraMap);
		
		proLegOrgDao.removeAll(objects);
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
				    proLegOrgDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProLegOrg(Map<String, Object> paraMap){
		return proLegOrgDao.findProLegOrg(paraMap);
	}
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findJgxxByPage(Pagination page, Map<String, Object> paraMap)
	{
		return proLegOrgDao.findJgxxByPage(page, paraMap);
	}
}
