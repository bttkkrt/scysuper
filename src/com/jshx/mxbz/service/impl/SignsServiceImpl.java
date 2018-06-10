package com.jshx.mxbz.service.impl;

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
import com.jshx.mxbz.dao.SignsDao;
import com.jshx.mxbz.entity.Signs;
import com.jshx.mxbz.service.SignsService;

@Service("signsService")
public class SignsServiceImpl extends BaseServiceImpl implements SignsService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("signsDao")
	private SignsDao signsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return signsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Signs getById(String id)
	{
		return signsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Signs signs)
	{
		signsDao.save(signs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Signs signs)
	{
		signsDao.update(signs);
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
		List objects=signsDao.findSigns(paraMap);
		
		signsDao.removeAll(objects);
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
				    signsDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void saveList(List<Signs> signSList) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < signSList.size(); i++) {
			this.save(signSList.get(i));
		}
	}
}
