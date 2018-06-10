package com.jshx.whpclsc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.whpclsc.dao.WhpClscDao;
import com.jshx.whpclsc.entity.WhpClsc;
import com.jshx.whpclsc.service.WhpClscService;

@Service("whpClscService")
public class WhpClscServiceImpl extends BaseServiceImpl implements WhpClscService
{
	/**
	 * Dao类
	 */
	@Resource
	private WhpClscDao whpClscDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whpClscDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public WhpClsc getById(String id)
	{
		return whpClscDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(WhpClsc whpClsc)
	{
		whpClscDao.save(whpClsc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(WhpClsc whpClsc)
	{
		whpClscDao.update(whpClsc);
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
		List objects=whpClscDao.findWhpClsc(paraMap);
		
		whpClscDao.removeAll(objects);
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
				    whpClscDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<User> getUserListByMap(Map map) {
		// TODO Auto-generated method stub
		return whpClscDao.getUserListByMap(map);
	}

	@Override
	public List<WhpClsc> findWhpClsc(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return whpClscDao.findWhpClsc(paraMap);
	}
}
