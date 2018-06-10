package com.jshx.zhkxzxk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zhkxzxk.dao.ZhkxzxkDao;
import com.jshx.zhkxzxk.entity.Zhkxzxk;
import com.jshx.zhkxzxk.service.ZhkxzxkService;

@Service("zhkxzxkService")
public class ZhkxzxkServiceImpl extends BaseServiceImpl implements ZhkxzxkService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZhkxzxkDao zhkxzxkDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zhkxzxkDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zhkxzxk getById(String id)
	{
		return zhkxzxkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zhkxzxk zhkxzxk)
	{
		zhkxzxkDao.save(zhkxzxk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zhkxzxk zhkxzxk)
	{
		zhkxzxkDao.update(zhkxzxk);
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
		List objects=zhkxzxkDao.findZhkxzxk(paraMap);
		
		zhkxzxkDao.removeAll(objects);
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
				    zhkxzxkDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Zhkxzxk> findZhkxzxk(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return zhkxzxkDao.findZhkxzxk(paraMap);
	}
}
