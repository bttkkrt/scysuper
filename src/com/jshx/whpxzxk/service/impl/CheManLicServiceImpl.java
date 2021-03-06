package com.jshx.whpxzxk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.cheprolicense.entity.CheProLic;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpxzxk.dao.CheManLicDao;
import com.jshx.whpxzxk.entity.CheManLic;
import com.jshx.whpxzxk.service.CheManLicService;

@Service("cheManLicService")
public class CheManLicServiceImpl extends BaseServiceImpl implements CheManLicService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("cheManLicDao")
	private CheManLicDao cheManLicDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return cheManLicDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheManLic getById(String id)
	{
		return cheManLicDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CheManLic cheManLic)
	{
		cheManLicDao.save(cheManLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CheManLic cheManLic)
	{
		cheManLicDao.update(cheManLic);
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
		List objects=cheManLicDao.findCheManLic(paraMap);
		
		cheManLicDao.removeAll(objects);
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
				    cheManLicDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取危化品经营许可证
	 */
	public int findAllInfos(Map<String, Object> paraMap)
	{
		return cheManLicDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取危化品经营许可证列表分页
	 */
	public List<CheManLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return cheManLicDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
