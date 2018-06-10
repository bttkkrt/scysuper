package com.jshx.fcsbzzqk.service.impl;

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
import com.jshx.fcsbzzqk.dao.FcsbzzqkDao;
import com.jshx.fcsbzzqk.entity.Fcsbzzqk;
import com.jshx.fcsbzzqk.entity.FcsbzzqkBean;
import com.jshx.fcsbzzqk.service.FcsbzzqkService;

@Service("fcsbzzqkService")
public class FcsbzzqkServiceImpl extends BaseServiceImpl implements FcsbzzqkService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("fcsbzzqkDao")
	private FcsbzzqkDao fcsbzzqkDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return fcsbzzqkDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Fcsbzzqk getById(String id)
	{
		return fcsbzzqkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Fcsbzzqk fcsbzzqk)
	{
		fcsbzzqkDao.save(fcsbzzqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Fcsbzzqk fcsbzzqk)
	{
		fcsbzzqkDao.update(fcsbzzqk);
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
		List objects=fcsbzzqkDao.findFcsbzzqk(paraMap);
		
		fcsbzzqkDao.removeAll(objects);
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
				    fcsbzzqkDao.deleteWithFlag(id);
			}
		}
	}
	
   public List<FcsbzzqkBean> getFcsbzzqkListByMap(Map map){
	   return fcsbzzqkDao.getFcsbzzqkListByMap(map);
   }
	
	public FcsbzzqkBean getTotalFcsbzzqkListByMap(Map map){
		return fcsbzzqkDao.getTotalFcsbzzqkListByMap(map);
	}
}
