package com.jshx.zycsqk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsqk.dao.ZycsqkDao;
import com.jshx.zycsqk.entity.Zybwhda;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.entity.Zywhys;
import com.jshx.zycsqk.service.ZycsqkService;

@Service("zycsqkService")
public class ZycsqkServiceImpl extends BaseServiceImpl implements ZycsqkService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZycsqkDao zycsqkDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zycsqkDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsqk getById(String id)
	{
		return zycsqkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zycsqk zycsqk)
	{
		zycsqkDao.save(zycsqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zycsqk zycsqk)
	{
		zycsqkDao.update(zycsqk);
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
		List objects=zycsqkDao.findZycsqk(paraMap);
		
		zycsqkDao.removeAll(objects);
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
				    zycsqkDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public Zywhys getZywhysByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsqkDao.getZywhysByMap(map);
	}

	@Override
	public String getTotalCountByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsqkDao.getTotalCountByMap(map);
	}
	
	@Override
	public List<Zybwhda> getZybwhdaListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		return zycsqkDao.getZybwhdaListByMap(map, start, limit);
	}

	@Override
	public int getZybwhdaListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsqkDao.getZybwhdaListSizeByMap(map);
	}

	@Override
	public List<Zybwhda> getZybwhdaListExportByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsqkDao.getZybwhdaListExportByMap(map);
	}
}
