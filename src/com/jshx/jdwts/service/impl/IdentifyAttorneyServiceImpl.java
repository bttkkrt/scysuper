package com.jshx.jdwts.service.impl;

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
import com.jshx.jdwts.dao.IdentifyAttorneyDao;
import com.jshx.jdwts.entity.IdentifyAttorney;
import com.jshx.jdwts.service.IdentifyAttorneyService;

@Service("identifyAttorneyService")
public class IdentifyAttorneyServiceImpl extends BaseServiceImpl implements IdentifyAttorneyService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("identifyAttorneyDao")
	private IdentifyAttorneyDao identifyAttorneyDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return identifyAttorneyDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public IdentifyAttorney getById(String id)
	{
		return identifyAttorneyDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(IdentifyAttorney identifyAttorney)
	{
		identifyAttorneyDao.save(identifyAttorney);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(IdentifyAttorney identifyAttorney)
	{
		identifyAttorneyDao.update(identifyAttorney);
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
		List objects=identifyAttorneyDao.findIdentifyAttorney(paraMap);
		
		identifyAttorneyDao.removeAll(objects);
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
				    identifyAttorneyDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<IdentifyAttorney> findIdentifyAttorney(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return identifyAttorneyDao.findIdentifyAttorney(paraMap);
	}
}
