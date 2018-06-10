package com.jshx.zcaqgcs.service.impl;

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
import com.jshx.zcaqgcs.dao.CerSafEngDao;
import com.jshx.zcaqgcs.entity.CerSafEng;
import com.jshx.zcaqgcs.service.CerSafEngService;

@Service("cerSafEngService")
public class CerSafEngServiceImpl extends BaseServiceImpl implements CerSafEngService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("cerSafEngDao")
	private CerSafEngDao cerSafEngDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return cerSafEngDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CerSafEng getById(String id)
	{
		return cerSafEngDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CerSafEng cerSafEng)
	{
		cerSafEngDao.save(cerSafEng);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CerSafEng cerSafEng)
	{
		cerSafEngDao.update(cerSafEng);
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
		List objects=cerSafEngDao.findCerSafEng(paraMap);
		
		cerSafEngDao.removeAll(objects);
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
				    cerSafEngDao.deleteWithFlag(id);
			}
		}
	}
}
