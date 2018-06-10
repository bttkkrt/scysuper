package com.wzxx.bgt.service.impl;

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
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.bgt.dao.LighthouseDao;
import com.wzxx.bgt.entity.Lighthouse;
import com.wzxx.bgt.service.LighthouseService;
import com.wzxx.bgtxx.entity.ExpTabDet;
import com.wzxx.bgtxx.entity.ExpTabDetBean;

@Service("lighthouseService")
public class LighthouseServiceImpl extends BaseServiceImpl implements LighthouseService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("lighthouseDao")
	private LighthouseDao lighthouseDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return lighthouseDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Lighthouse getById(String id)
	{
		return lighthouseDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Lighthouse lighthouse)
	{
		lighthouseDao.save(lighthouse);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Lighthouse lighthouse)
	{
		lighthouseDao.update(lighthouse);
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
		List objects=lighthouseDao.findLighthouse(paraMap);
		
		lighthouseDao.removeAll(objects);
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
				    lighthouseDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 根据titleId查询曝光台详情
	 */
	public List<ExpTabDetBean> findBgtxx(Map map){
		return lighthouseDao.findBgtxx(map);
	}
	/**
	 * 查询图片信息
	 */
	public List<String> getPhotosByType(Map map) {
		return lighthouseDao.getPhotosByType(map);
	}
	/**
	 * 获取曝光台
	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	return lighthouseDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取曝光台列表分页
	 */
	public List<Lighthouse> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return lighthouseDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
