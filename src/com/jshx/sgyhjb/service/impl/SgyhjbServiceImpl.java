package com.jshx.sgyhjb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;
import com.jshx.sgyhjb.dao.SgyhjbDao;
import com.jshx.sgyhjb.entity.Sgyhjb;
import com.jshx.sgyhjb.service.SgyhjbService;

@Service("sgyhjbService")
public class SgyhjbServiceImpl extends BaseServiceImpl implements SgyhjbService
{
	/**
	 * Dao类
	 */
	@Resource
	private SgyhjbDao sgyhjbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return sgyhjbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sgyhjb getById(String id)
	{
		return sgyhjbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Sgyhjb sgyhjb)
	{
		sgyhjbDao.save(sgyhjb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Sgyhjb sgyhjb)
	{
		sgyhjbDao.update(sgyhjb);
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
		List objects=sgyhjbDao.findSgyhjb(paraMap);
		
		sgyhjbDao.removeAll(objects);
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
				    sgyhjbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Department> getClbmListByMap(Map map) {
		// TODO Auto-generated method stub
		return sgyhjbDao.getClbmListByMap(map);
	}

	@Override
	public List<Department> getJbbmListByMap(Map map) {
		// TODO Auto-generated method stub
		return sgyhjbDao.getJbbmListByMap(map);
	}

	@Override
	public List<Sgyhjb> findSgyhjb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return sgyhjbDao.findSgyhjb(paraMap);
	}
}
