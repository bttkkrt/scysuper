package com.jshx.aqscwyhtj.service.impl;

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
import com.jshx.aqscwyhtj.dao.AqscwyhtjDao;
import com.jshx.aqscwyhtj.entity.Aqscwyhtj;
import com.jshx.aqscwyhtj.entity.AqscwyhtjBean;
import com.jshx.aqscwyhtj.service.AqscwyhtjService;

@Service("aqscwyhtjService")
public class AqscwyhtjServiceImpl extends BaseServiceImpl implements AqscwyhtjService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("aqscwyhtjDao")
	private AqscwyhtjDao aqscwyhtjDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscwyhtjDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscwyhtj getById(String id)
	{
		return aqscwyhtjDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscwyhtj aqscwyhtj)
	{
		aqscwyhtjDao.save(aqscwyhtj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscwyhtj aqscwyhtj)
	{
		aqscwyhtjDao.update(aqscwyhtj);
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
		List objects=aqscwyhtjDao.findAqscwyhtj(paraMap);
		
		aqscwyhtjDao.removeAll(objects);
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
				    aqscwyhtjDao.deleteWithFlag(id);
			}
		}
	}
	   public List<AqscwyhtjBean> getAqscwyhtjListByMap(Map map){
		   return aqscwyhtjDao.getAqscwyhtjListByMap(map);
	   }
		
		public AqscwyhtjBean getTotalAqscwyhtjListByMap(Map map){
			return aqscwyhtjDao.getTotalAqscwyhtjListByMap(map);
		}
}
