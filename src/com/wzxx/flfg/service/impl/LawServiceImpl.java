package com.wzxx.flfg.service.impl;

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
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.flfg.dao.LawDao;
import com.wzxx.flfg.entity.Law;
import com.wzxx.flfg.service.LawService;

@Service("lawService")
public class LawServiceImpl extends BaseServiceImpl implements LawService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("lawDao")
	private LawDao lawDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return lawDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Law getById(String id)
	{
		return lawDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Law law)
	{
		lawDao.save(law);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Law law)
	{
		lawDao.update(law);
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
		List objects=lawDao.findLaw(paraMap);
		
		lawDao.removeAll(objects);
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
				    lawDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取法律法规
	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	return lawDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取安全文化列表分页
	 */
	public List<Law> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return lawDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
