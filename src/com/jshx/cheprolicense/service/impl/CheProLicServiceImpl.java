package com.jshx.cheprolicense.service.impl;

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
import com.jshx.cheprolicense.dao.CheProLicDao;
import com.jshx.cheprolicense.entity.CheProLic;
import com.jshx.cheprolicense.service.CheProLicService;

@Service("cheProLicService")
public class CheProLicServiceImpl extends BaseServiceImpl implements CheProLicService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("cheProLicDao")
	private CheProLicDao cheProLicDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return cheProLicDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheProLic getById(String id)
	{
		return cheProLicDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CheProLic cheProLic)
	{
		cheProLicDao.save(cheProLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CheProLic cheProLic)
	{
		cheProLicDao.update(cheProLic);
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
		List objects=cheProLicDao.findCheProLic(paraMap);
		
		cheProLicDao.removeAll(objects);
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
				    cheProLicDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取危化品安全生产许可证
	 */
	public int findAllInfos(Map<String, Object> paraMap)
	{
		return cheProLicDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取危化品安全生产许可证列表分页
	 */
	public List<CheProLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return cheProLicDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
