package com.jshx.echarts.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.echarts.dao.EchartsDao;
import com.jshx.echarts.entity.Echarts;
import com.jshx.echarts.service.EchartsService;
import com.jshx.yhb.dao.TroManDao;
import com.jshx.yhb.entity.TjYhBean;

@Service("echartsService")
public class EchartsServiceImpl extends BaseServiceImpl implements EchartsService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("echartsDao")
	private EchartsDao echartsDao;
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("troManDao")
	private TroManDao troManDao;
	
	

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return echartsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Echarts getById(String id)
	{
		return echartsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Echarts echarts)
	{
		echartsDao.save(echarts);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Echarts echarts)
	{
		echartsDao.update(echarts);
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
		List objects=echartsDao.findEcharts(paraMap);
		
		echartsDao.removeAll(objects);
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
				    echartsDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<TjYhBean> getTjYhListFromQy(String sqlId, Map map) {
		return troManDao.getTjYhListFromQy(sqlId,map);
	}
}
