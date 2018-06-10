package com.jshx.whysjbqk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whysjbqk.dao.WhysjbqkDao;
import com.jshx.whysjbqk.entity.Whysjbqk;
import com.jshx.whysjbqk.entity.Whysjbqkglb;
import com.jshx.whysjbqk.service.WhysjbqkService;

@Service("whysjbqkService")
public class WhysjbqkServiceImpl extends BaseServiceImpl implements WhysjbqkService
{
	/**
	 * Dao类
	 */
	@Resource
	private WhysjbqkDao whysjbqkDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whysjbqkDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whysjbqk getById(String id)
	{
		return whysjbqkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Whysjbqk whysjbqk)
	{
		whysjbqkDao.save(whysjbqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Whysjbqk whysjbqk)
	{
		whysjbqkDao.update(whysjbqk);
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
		List objects=whysjbqkDao.findWhysjbqk(paraMap);
		
		whysjbqkDao.removeAll(objects);
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
				    whysjbqkDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void saveGlb(Whysjbqkglb model) {
		whysjbqkDao.saveGlb(model);
	}

	@Override
	public List<Whysjbqkglb> getWhysjbqkglbList(Map map) {
		return whysjbqkDao.getWhysjbqkglbList(map);
	}

	@Transactional
	public void delWhysjbqkglb(String id) {
		 whysjbqkDao.delWhysjbqkglb(id);
	}

	@Override
	public String getWhysjbqkIdByQyid(String id) {
		return whysjbqkDao.getWhysjbqkIdByQyid(id);
	}
}
