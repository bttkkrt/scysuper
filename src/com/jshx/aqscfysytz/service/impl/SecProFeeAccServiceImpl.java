package com.jshx.aqscfysytz.service.impl;

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
import com.jshx.aqscfysytz.dao.SecProFeeAccDao;
import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.aqscfysytz.service.SecProFeeAccService;

@Service("secProFeeAccService")
public class SecProFeeAccServiceImpl extends BaseServiceImpl implements SecProFeeAccService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("secProFeeAccDao")
	private SecProFeeAccDao secProFeeAccDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return secProFeeAccDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProFeeAcc getById(String id)
	{
		return secProFeeAccDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SecProFeeAcc secProFeeAcc)
	{
		secProFeeAccDao.save(secProFeeAcc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SecProFeeAcc secProFeeAcc)
	{
		secProFeeAccDao.update(secProFeeAcc);
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
		List objects=secProFeeAccDao.findSecProFeeAcc(paraMap);
		
		secProFeeAccDao.removeAll(objects);
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
				    secProFeeAccDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<SecProFeeAcc> findSecProFeeAcc(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return secProFeeAccDao.findSecProFeeAcc(paraMap);
	}
}
