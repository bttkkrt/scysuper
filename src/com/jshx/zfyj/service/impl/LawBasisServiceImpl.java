package com.jshx.zfyj.service.impl;

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
import com.jshx.zfyj.dao.LawBasisDao;
import com.jshx.zfyj.entity.LawBasis;
import com.jshx.zfyj.service.LawBasisService;

@Service("lawBasisService")
public class LawBasisServiceImpl extends BaseServiceImpl implements LawBasisService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("lawBasisDao")
	private LawBasisDao lawBasisDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return lawBasisDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LawBasis getById(String id)
	{
		return lawBasisDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(LawBasis lawBasis)
	{
		lawBasisDao.save(lawBasis);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(LawBasis lawBasis)
	{
		lawBasisDao.update(lawBasis);
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
		List objects=lawBasisDao.findLawBasis(paraMap);
		
		lawBasisDao.removeAll(objects);
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
				    lawBasisDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<LawBasis> getLawBasisListByUserAndType(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		return lawBasisDao.getLawBasisListByUserAndType(map, start, limit);
	}

	@Override
	public int getLawBasisListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return lawBasisDao.getLawBasisListSizeByUserAndType(map);
	}

	@Override
	public List<LawBasis> findLawBasis(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return lawBasisDao.findLawBasis(paraMap);
	}
}
