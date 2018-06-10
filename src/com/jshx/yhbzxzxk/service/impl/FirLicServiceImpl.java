package com.jshx.yhbzxzxk.service.impl;

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
import com.jshx.whpxzxk.entity.CheManLic;
import com.jshx.yhbzxzxk.dao.FirLicDao;
import com.jshx.yhbzxzxk.entity.FirLic;
import com.jshx.yhbzxzxk.service.FirLicService;

@Service("firLicService")
public class FirLicServiceImpl extends BaseServiceImpl implements FirLicService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("firLicDao")
	private FirLicDao firLicDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return firLicDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FirLic getById(String id)
	{
		return firLicDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(FirLic firLic)
	{
		firLicDao.save(firLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(FirLic firLic)
	{
		firLicDao.update(firLic);
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
		List objects=firLicDao.findFirLic(paraMap);
		
		firLicDao.removeAll(objects);
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
				    firLicDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取烟花爆竹经营许可证
	 */
	public int findAllInfos(Map<String, Object> paraMap)
	{
		return firLicDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取烟花爆竹经营许可证列表分页
	 */
	public List<FirLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return firLicDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
