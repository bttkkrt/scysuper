package com.jshx.ajsp.service.impl;

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
import com.jshx.ajsp.dao.CloseApprovalDao;
import com.jshx.ajsp.entity.CloseApproval;
import com.jshx.ajsp.service.CloseApprovalService;

@Service("closeApprovalService")
public class CloseApprovalServiceImpl extends BaseServiceImpl implements CloseApprovalService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("closeApprovalDao")
	private CloseApprovalDao closeApprovalDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return closeApprovalDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CloseApproval getById(String id)
	{
		return closeApprovalDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CloseApproval closeApproval)
	{
		closeApprovalDao.save(closeApproval);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CloseApproval closeApproval)
	{
		closeApprovalDao.update(closeApproval);
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
		List objects=closeApprovalDao.findCloseApproval(paraMap);
		
		closeApprovalDao.removeAll(objects);
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
				    closeApprovalDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<CloseApproval> findCloseApproval(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return closeApprovalDao.findCloseApproval(paraMap);
	}
}
