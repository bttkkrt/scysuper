package com.jshx.dcbg.service.impl;

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
import com.jshx.dcbg.dao.DcbgDao;
import com.jshx.dcbg.entity.Dcbg;
import com.jshx.dcbg.service.DcbgService;

@Service("dcbgService")
public class DcbgServiceImpl extends BaseServiceImpl implements DcbgService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("dcbgDao")
	private DcbgDao dcbgDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return dcbgDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dcbg getById(String id)
	{
		return dcbgDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Dcbg dcbg)
	{
		dcbgDao.save(dcbg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Dcbg dcbg)
	{
		dcbgDao.update(dcbg);
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
		List objects=dcbgDao.findDcbg(paraMap);
		
		dcbgDao.removeAll(objects);
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
				    dcbgDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Dcbg> findDcbg(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return dcbgDao.findDcbg(paraMap);
	}
}
