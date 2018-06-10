package com.jshx.yhbzlsd.service.impl;

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
import com.jshx.yhbzlsd.dao.YhbzlsdDao;
import com.jshx.yhbzlsd.entity.Yhbzlsd;
import com.jshx.yhbzlsd.entity.YhbzlsdBean;
import com.jshx.yhbzlsd.service.YhbzlsdService;

@Service("yhbzlsdService")
public class YhbzlsdServiceImpl extends BaseServiceImpl implements YhbzlsdService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("yhbzlsdDao")
	private YhbzlsdDao yhbzlsdDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return yhbzlsdDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Yhbzlsd getById(String id)
	{
		return yhbzlsdDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Yhbzlsd yhbzlsd)
	{
		yhbzlsdDao.save(yhbzlsd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Yhbzlsd yhbzlsd)
	{
		yhbzlsdDao.update(yhbzlsd);
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
		List objects=yhbzlsdDao.findYhbzlsd(paraMap);
		
		yhbzlsdDao.removeAll(objects);
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
				    yhbzlsdDao.deleteWithFlag(id);
			}
		}
	}
	public List<YhbzlsdBean> getYhbzlsdListByMap(Map map){
		return yhbzlsdDao.getYhbzlsdListByMap(map);
	}
	
	public YhbzlsdBean getTotalYhbzlsdListByMap(Map map){
		return yhbzlsdDao.getTotalYhbzlsdListByMap(map);
	}
}
