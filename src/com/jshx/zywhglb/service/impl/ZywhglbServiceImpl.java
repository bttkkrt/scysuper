package com.jshx.zywhglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywhglb.dao.ZywhglbDao;
import com.jshx.zywhglb.entity.Zywhglb;
import com.jshx.zywhglb.service.ZywhglbService;

@Service("zywhglbService")
public class ZywhglbServiceImpl extends BaseServiceImpl implements ZywhglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZywhglbDao zywhglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zywhglbDao.findByPage(page, paraMap);
	}

	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zywhglb> findZywhglb(Map<String, Object> paraMap)
	{
		return zywhglbDao.findZywhglb(paraMap);
	}
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywhglb getById(String id)
	{
		return zywhglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zywhglb zywhglb)
	{
		zywhglbDao.save(zywhglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zywhglb zywhglb)
	{
		zywhglbDao.update(zywhglb);
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
		List objects=zywhglbDao.findZywhglb(paraMap);
		
		zywhglbDao.removeAll(objects);
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
				    zywhglbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Zywhglb> getFcListByMap(Map map) {
		// TODO Auto-generated method stub
		return zywhglbDao.getFcListByMap(map);
	}
}
