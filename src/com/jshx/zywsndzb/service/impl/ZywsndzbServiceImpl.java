package com.jshx.zywsndzb.service.impl;

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
import com.jshx.zywsndzb.dao.ZywsndzbDao;
import com.jshx.zywsndzb.entity.Zywsndzb;
import com.jshx.zywsndzb.entity.ZywsndzbBean;
import com.jshx.zywsndzb.service.ZywsndzbService;

@Service("zywsndzbService")
public class ZywsndzbServiceImpl extends BaseServiceImpl implements ZywsndzbService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("zywsndzbDao")
	private ZywsndzbDao zywsndzbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zywsndzbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsndzb getById(String id)
	{
		return zywsndzbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zywsndzb zywsndzb)
	{
		zywsndzbDao.save(zywsndzb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zywsndzb zywsndzb)
	{
		zywsndzbDao.update(zywsndzb);
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
		List objects=zywsndzbDao.findZywsndzb(paraMap);
		
		zywsndzbDao.removeAll(objects);
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
				    zywsndzbDao.deleteWithFlag(id);
			}
		}
	}
    public List<ZywsndzbBean> getZywsndzbListByMap(Map map){
    	return zywsndzbDao.getZywsndzbListByMap(map);
    }
	
	public ZywsndzbBean getTotalZywsndzbListByMap(Map map){
		return zywsndzbDao.getTotalZywsndzbListByMap(map);
	}
}
