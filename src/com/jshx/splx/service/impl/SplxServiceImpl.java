package com.jshx.splx.service.impl;

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
import com.jshx.splx.dao.SplxDao;
import com.jshx.splx.entity.Splx;
import com.jshx.splx.service.SplxService;

@Service("splxService")
public class SplxServiceImpl extends BaseServiceImpl implements SplxService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("splxDao")
	private SplxDao splxDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return splxDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Splx getById(String id)
	{
		return splxDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Splx splx)
	{
		splxDao.save(splx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Splx splx)
	{
		splxDao.update(splx);
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
		List objects=splxDao.findSplx(paraMap);
		
		splxDao.removeAll(objects);
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
				    splxDao.deleteWithFlag(id);
			}
		}
	}
	
	public List<Splx> findSplxList(Map map){
		return splxDao.findSplxList(map);
	}

	@Transactional
	public void deleteSplxByMap(Map map) {
		// TODO Auto-generated method stub
		splxDao.deleteSplxByMap(map);
	}
}
