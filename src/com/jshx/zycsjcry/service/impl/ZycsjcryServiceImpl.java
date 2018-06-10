package com.jshx.zycsjcry.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsjcry.dao.ZycsjcryDao;
import com.jshx.zycsjcry.entity.Hyfl;
import com.jshx.zycsjcry.entity.Qylb;
import com.jshx.zycsjcry.entity.Whys;
import com.jshx.zycsjcry.entity.Xzqy;
import com.jshx.zycsjcry.entity.Zclx;
import com.jshx.zycsjcry.entity.Zycsjcry;
import com.jshx.zycsjcry.service.ZycsjcryService;

@Service("zycsjcryService")
public class ZycsjcryServiceImpl extends BaseServiceImpl implements ZycsjcryService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZycsjcryDao zycsjcryDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zycsjcryDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsjcry getById(String id)
	{
		return zycsjcryDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zycsjcry zycsjcry)
	{
		zycsjcryDao.save(zycsjcry);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zycsjcry zycsjcry)
	{
		zycsjcryDao.update(zycsjcry);
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
		List objects=zycsjcryDao.findZycsjcry(paraMap);
		
		zycsjcryDao.removeAll(objects);
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
				    zycsjcryDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlags(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    zycsjcryDao.deleteWithFlags(id);
			}
		}
	}

	@Override
	public String getTotalCountByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getTotalCountByMap(map);
	}

	@Override
	public List<Zycsjcry> getZycsjcryListByMap(Map map, int start,int limit) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getZycsjcryListByMap(map, start,limit);
	}

	@Override
	public int getZycsjcryListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getZycsjcryListSizeByMap(map);
	}

	@Override
	public List<Hyfl> getHyflListByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getHyflListByMap(map);
	}

	@Override
	public List<Qylb> getQylbListByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getQylbListByMap(map);
	}


	@Override
	public List<Whys> getWhysListByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getWhysListByMap(map);
	}

	@Override
	public List<Xzqy> getXzqyListByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getXzqyListByMap(map);
	}

	@Override
	public List<Zclx> getZclxListByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getZclxListByMap(map);
	}

	@Override
	public Whys getWhysByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getWhysByMap(map);
	}

	@Override
	public Xzqy getXzqyByMap(Map map) {
		// TODO Auto-generated method stub
		return zycsjcryDao.getXzqyByMap(map);
	}
}
