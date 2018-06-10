package com.jshx.dfzw.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dfzw.dao.DfzwDao;
import com.jshx.dfzw.entity.Dfzw;
import com.jshx.dfzw.service.DfzwService;

@Service("dfzwService")
public class DfzwServiceImpl extends BaseServiceImpl implements DfzwService
{
	/**
	 * Dao类
	 */
	@Resource
	private DfzwDao dfzwDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return dfzwDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dfzw getById(String id)
	{
		return dfzwDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Dfzw dfzw)
	{
		dfzwDao.save(dfzw);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Dfzw dfzw)
	{
		dfzwDao.update(dfzw);
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
		List objects=dfzwDao.findDfzw(paraMap);
		
		dfzwDao.removeAll(objects);
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
				    dfzwDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteDfzwglbByMap(Map map) {
		// TODO Auto-generated method stub
		dfzwDao.deleteDfzwglbByMap(map);
	}

	@Override
	public List<String> getDfzwIdsByMap(Map map) {
		// TODO Auto-generated method stub
		return dfzwDao.getDfzwIdsByMap(map);
	}
}
