package com.wzxx.jgdl.service.impl;

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
import com.wzxx.jgdl.dao.JgdlDao;
import com.wzxx.jgdl.entity.Jgdl;
import com.wzxx.jgdl.service.JgdlService;

@Service("jgdlService")
public class JgdlServiceImpl extends BaseServiceImpl implements JgdlService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("jgdlDao")
	private JgdlDao jgdlDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jgdlDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Jgdl getById(String id)
	{
		return jgdlDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Jgdl jgdl)
	{
		jgdlDao.save(jgdl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Jgdl jgdl)
	{
		jgdlDao.update(jgdl);
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
		List objects=jgdlDao.findJgdl(paraMap);
		
		jgdlDao.removeAll(objects);
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
				    jgdlDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取机关党建
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		return jgdlDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取机关党建列表分页
	 */
	public List<Jgdl> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return jgdlDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
