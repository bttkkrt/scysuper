package com.jshx.safeInspectDistribute.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safeInspectDistribute.dao.SafeInspectDistributeDao;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.entity.SafeInspectTjBean;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

@Service("safeInspectDistributeService")
public class SafeInspectDistributeServiceImpl extends BaseServiceImpl implements SafeInspectDistributeService
{
	/**
	 * Dao类
	 */
	@Resource
	private SafeInspectDistributeDao safeInspectDistributeDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return safeInspectDistributeDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafeInspectDistribute getById(String id)
	{
		return safeInspectDistributeDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SafeInspectDistribute safeInspectDistribute)
	{
		safeInspectDistributeDao.save(safeInspectDistribute);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SafeInspectDistribute safeInspectDistribute)
	{
		safeInspectDistributeDao.update(safeInspectDistribute);
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
		List objects=safeInspectDistributeDao.findSafeInspectDistribute(paraMap);
		
		safeInspectDistributeDao.removeAll(objects);
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
				    safeInspectDistributeDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 查询所有记录
	 * @param paraMap 查询条件信息
	 */
	public List findSafeInspectDistribute(Map<String, Object> paraMap){
		return safeInspectDistributeDao.findSafeInspectDistribute(paraMap);
	}
	/**
	 * 获取最大流水号
	 * @return
	 */
	public String findMaxSerialNum(){
		return safeInspectDistributeDao.findMaxSerialNum();
	}
	public List<SafeInspectTjBean> getTongJiSafeInspect(Map map) {
		return safeInspectDistributeDao.getTongJiSafeInspect(map);
	}
}
