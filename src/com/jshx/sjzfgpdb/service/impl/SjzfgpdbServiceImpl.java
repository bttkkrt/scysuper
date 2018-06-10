package com.jshx.sjzfgpdb.service.impl;

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
import com.jshx.sjzfgpdb.dao.SjzfgpdbDao;
import com.jshx.sjzfgpdb.entity.Sjzfgpdb;
import com.jshx.sjzfgpdb.entity.SjzfgpdbBean;
import com.jshx.sjzfgpdb.service.SjzfgpdbService;

@Service("sjzfgpdbService")
public class SjzfgpdbServiceImpl extends BaseServiceImpl implements SjzfgpdbService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("sjzfgpdbDao")
	private SjzfgpdbDao sjzfgpdbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return sjzfgpdbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sjzfgpdb getById(String id)
	{
		return sjzfgpdbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Sjzfgpdb sjzfgpdb)
	{
		sjzfgpdbDao.save(sjzfgpdb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Sjzfgpdb sjzfgpdb)
	{
		sjzfgpdbDao.update(sjzfgpdb);
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
		List objects=sjzfgpdbDao.findSjzfgpdb(paraMap);
		
		sjzfgpdbDao.removeAll(objects);
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
				    sjzfgpdbDao.deleteWithFlag(id);
			}
		}
	}
   public List<SjzfgpdbBean> getSjzfgpdListByMap(Map map){
	   return  sjzfgpdbDao.getSjzfgpdListByMap(map);
   }
	
	public SjzfgpdbBean getTotalSjzfgpdListByMap(Map map){
		return sjzfgpdbDao.getTotalSjzfgpdListByMap(map);
	}
}
