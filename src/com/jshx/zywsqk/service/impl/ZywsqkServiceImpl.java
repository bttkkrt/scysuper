package com.jshx.zywsqk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsqk.entity.Zybwhda;
import com.jshx.zywsqk.dao.ZywsqkDao;
import com.jshx.zywsqk.entity.Zywsqk;
import com.jshx.zywsqk.entity.ZywsqkAll;
import com.jshx.zywsqk.service.ZywsqkService;

@Service("zywsqkService")
public class ZywsqkServiceImpl extends BaseServiceImpl implements ZywsqkService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZywsqkDao zywsqkDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zywsqkDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsqk getById(String id)
	{
		return zywsqkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zywsqk zywsqk)
	{
		zywsqkDao.save(zywsqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zywsqk zywsqk)
	{
		zywsqkDao.update(zywsqk);
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
		List objects=zywsqkDao.findZywsqk(paraMap);
		
		zywsqkDao.removeAll(objects);
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
				    zywsqkDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteZywhglbByMap(Map map) {
		// TODO Auto-generated method stub
		zywsqkDao.deleteZywhglbByMap(map);
	}

	@Override
	public ZywsqkAll getZywsqkAllByMap(Map map) {
		// TODO Auto-generated method stub
		return zywsqkDao.getZywsqkAllByMap(map);
	}

	@Override
	public List<ZywsqkAll> getZywsqkAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return zywsqkDao.getZywsqkAllListByMap(map);
	}

}
