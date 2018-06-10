package com.jshx.tzzyry.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzzyry.dao.SpeJobPerDao;
import com.jshx.tzzyry.entity.SpeJobPer;
import com.jshx.tzzyry.service.SpeJobPerService;

@Service("speJobPerService")
public class SpeJobPerServiceImpl extends BaseServiceImpl implements SpeJobPerService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("speJobPerDao")
	private SpeJobPerDao speJobPerDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return speJobPerDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SpeJobPer getById(String id)
	{
		return speJobPerDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SpeJobPer speJobPer)
	{
		speJobPerDao.save(speJobPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SpeJobPer speJobPer)
	{
		speJobPerDao.update(speJobPer);
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
		List objects=speJobPerDao.findSpeJobPer(paraMap);
		
		speJobPerDao.removeAll(objects);
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
				    speJobPerDao.deleteWithFlag(id);
			}
		}
	}
	
	@Transactional
	public List<String> saveList(List<SpeJobPer> speJobPerList) throws Exception{
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < speJobPerList.size(); i++) {
			SpeJobPer sp = speJobPerList.get(i);
			this.save(sp);
			list.add(sp.getId());
		}
		return list;
	}
	
	public Boolean isRegSpeJobPer(String specialJobCradnum)  {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("specialJobCradnum", specialJobCradnum);
		List<?> list = speJobPerDao.findListByHqlId("isRegSpeJobPer", paraMap);
		if(list==null)
			return false;
		else{
			Long cnt = (Long)list.get(0);
			if(cnt>0)
				return true;
			else
				return false;
		}
	}

	@Override
	public List<SpeJobPer> findSpeJobPer(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return speJobPerDao.findSpeJobPer(paraMap);
	}
}
