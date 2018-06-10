package com.jshx.qyzcyhglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyzcyhglb.dao.QyzcyhglbDao;
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.qyzcyhglb.service.QyzcyhglbService;

@Service("qyzcyhglbService")
public class QyzcyhglbServiceImpl extends BaseServiceImpl implements QyzcyhglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private QyzcyhglbDao qyzcyhglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return qyzcyhglbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Qyzcyhglb getById(String id)
	{
		return qyzcyhglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Qyzcyhglb qyzcyhglb)
	{
		qyzcyhglbDao.save(qyzcyhglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Qyzcyhglb qyzcyhglb)
	{
		qyzcyhglbDao.update(qyzcyhglb);
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
		List objects=qyzcyhglbDao.findQyzcyhglb(paraMap);
		
		qyzcyhglbDao.removeAll(objects);
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
				    qyzcyhglbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Qyzcyhglb> findQyzcyhglb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return qyzcyhglbDao.findQyzcyhglb(paraMap);
	}
}
