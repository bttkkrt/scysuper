package com.jshx.aqscgljg.service.impl;

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
import com.jshx.aqscgljg.dao.ProManOrgDao;
import com.jshx.aqscgljg.entity.ProManOrg;
import com.jshx.aqscgljg.service.ProManOrgService;

@Service("proManOrgService")
public class ProManOrgServiceImpl extends BaseServiceImpl implements ProManOrgService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("proManOrgDao")
	private ProManOrgDao proManOrgDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return proManOrgDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProManOrg getById(String id)
	{
		return proManOrgDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(ProManOrg proManOrg)
	{
		proManOrgDao.save(proManOrg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(ProManOrg proManOrg)
	{
		proManOrgDao.update(proManOrg);
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
		List objects=proManOrgDao.findProManOrg(paraMap);
		
		proManOrgDao.removeAll(objects);
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
				    proManOrgDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProManOrg(Map<String, Object> paraMap){
		return proManOrgDao.findProManOrg(paraMap);
	}
}
