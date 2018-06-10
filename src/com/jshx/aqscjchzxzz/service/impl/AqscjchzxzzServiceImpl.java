package com.jshx.aqscjchzxzz.service.impl;

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
import com.jshx.aqscjchzxzz.dao.AqscjchzxzzDao;
import com.jshx.aqscjchzxzz.entity.Aqscjchzxzz;
import com.jshx.aqscjchzxzz.entity.AqscjchzxzzBean;
import com.jshx.aqscjchzxzz.service.AqscjchzxzzService;

@Service("aqscjchzxzzService")
public class AqscjchzxzzServiceImpl extends BaseServiceImpl implements AqscjchzxzzService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("aqscjchzxzzDao")
	private AqscjchzxzzDao aqscjchzxzzDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscjchzxzzDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscjchzxzz getById(String id)
	{
		return aqscjchzxzzDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscjchzxzz aqscjchzxzz)
	{
		aqscjchzxzzDao.save(aqscjchzxzz);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscjchzxzz aqscjchzxzz)
	{
		aqscjchzxzzDao.update(aqscjchzxzz);
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
		List objects=aqscjchzxzzDao.findAqscjchzxzz(paraMap);
		
		aqscjchzxzzDao.removeAll(objects);
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
				    aqscjchzxzzDao.deleteWithFlag(id);
			}
		}
	}
	public List<AqscjchzxzzBean>  getAqscjchzxzzListByMap(Map map){
		return  aqscjchzxzzDao.getAqscjchzxzzListByMap(map);
	}
	
	public AqscjchzxzzBean getTotalAqscjchzxzzListByMap(Map map){
		return aqscjchzxzzDao.getTotalAqscjchzxzzListByMap(map);
	}
}
