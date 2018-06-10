package com.jshx.jcjl.service.impl;

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
import com.jshx.jcjl.dao.InsRecDao;
import com.jshx.jcjl.entity.InsRec;
import com.jshx.jcjl.service.InsRecService;

@Service("insRecService")
public class InsRecServiceImpl extends BaseServiceImpl implements InsRecService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("insRecDao")
	private InsRecDao insRecDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return insRecDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InsRec getById(String id)
	{
		return insRecDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(InsRec insRec)
	{
		insRecDao.save(insRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(InsRec insRec)
	{
		insRecDao.update(insRec);
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
		List objects=insRecDao.findInsRec(paraMap);
		
		insRecDao.removeAll(objects);
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
				    insRecDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<InsRec> getInsRecListByUserAndType(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		return insRecDao.getInsRecListByUserAndType(map, start, limit);
	}

	@Override
	public int getInsRecListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return insRecDao.getInsRecListSizeByUserAndType(map);
	}
}
