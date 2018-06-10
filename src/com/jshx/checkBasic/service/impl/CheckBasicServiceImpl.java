package com.jshx.checkBasic.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.checkBasic.dao.CheckBasicDao;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;

@Service("checkBasicService")
public class CheckBasicServiceImpl extends BaseServiceImpl implements CheckBasicService
{
	/**
	 * Dao类
	 */
	@Resource
	private CheckBasicDao checkBasicDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return checkBasicDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckBasic getById(String id)
	{
		return checkBasicDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CheckBasic checkBasic)
	{
		checkBasicDao.save(checkBasic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CheckBasic checkBasic)
	{
		checkBasicDao.merge(checkBasic);
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
		List objects=checkBasicDao.findCheckBasic(paraMap);
		
		checkBasicDao.removeAll(objects);
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
				    checkBasicDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckBasic(Map<String, Object> paraMap){
		return checkBasicDao.findCheckBasic(paraMap);
	}

	@Override
	public Pagination findRectifyCheckByPage(Pagination pagination, Map<String, Object> paraMap)
	{
		return checkBasicDao.findRectifyCheckByPage(pagination, paraMap);
	}

	@Override
	public String getMaxNumByYear(String nowYear)
	{
		String maxNum = "0000";
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("rectifyYear", nowYear);
		List numList = checkBasicDao.getMaxRectifyNum(paraMap);
		if (numList.size() > 0)
		{
			maxNum = (String) numList.get(0);
		}
		return maxNum;
	}

}
