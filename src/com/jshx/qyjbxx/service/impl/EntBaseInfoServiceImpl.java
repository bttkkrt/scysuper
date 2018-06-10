package com.jshx.qyjbxx.service.impl;

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
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.dao.EntBaseInfoDao;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

@Service("entBaseInfoService")
public class EntBaseInfoServiceImpl extends BaseServiceImpl implements EntBaseInfoService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("entBaseInfoDao")
	private EntBaseInfoDao entBaseInfoDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return entBaseInfoDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EntBaseInfo getById(String id)
	{
		return entBaseInfoDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(EntBaseInfo entBaseInfo)
	{
		entBaseInfoDao.save(entBaseInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(EntBaseInfo entBaseInfo)
	{
		entBaseInfoDao.update(entBaseInfo);
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
		List objects=entBaseInfoDao.findEntBaseInfo(paraMap);
		
		entBaseInfoDao.removeAll(objects);
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
				    entBaseInfoDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public EntBaseInfo findEntBaseInfoByMap(Map map) {
		// TODO Auto-generated method stub
		return entBaseInfoDao.findEntBaseInfoByMap(map);
	}

	@Override
	public List<EntBaseInfo> getEntBaseInfoListByUserAndType(Map map,
			int start, int limit) {
		// TODO Auto-generated method stub
		return entBaseInfoDao.getEntBaseInfoListByUserAndType(map, start, limit);
	}

	@Override
	public int getEntBaseInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return entBaseInfoDao.getEntBaseInfoListSizeByUserAndType(map);
	}

	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		entBaseInfoDao.updateUser(user);
	}
	
	public List<EntBaseInfo> findEntBaseInfo(Map map) {
		// TODO Auto-generated method stub
		return entBaseInfoDao.findEntBaseInfo(map);
	}

}
