package com.jshx.aqscfk.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.aqscfk.dao.AqscfkDao;
import com.jshx.aqscfk.entity.Aqscfk;
import com.jshx.aqscfk.entity.Aqscxzcf;
import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.aqscfk.service.AqscfkService;

@Service("aqscfkService")
public class AqscfkServiceImpl extends BaseServiceImpl implements AqscfkService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqscfkDao aqscfkDao;

//	@Autowired
//	private IctSqlMapClientDaoSupport ictSqlMapClientDaoSupport;
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 信息查询
	 * author：杨鹏
	 * 2014-06-03
	 * @param sqlId SQL名称
	 * @param paraMap 查询条件
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public List<Aqscxzcf> findListByPara(String sqlId, Map<String, Object> paraMap)
	{
		
		return this.sqlMapClientTemplate.queryForList(sqlId, paraMap);
	}
	
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscfkDao.findByPage(page, paraMap);
	}
	
	public List<Aqscfk> findAqscfk(Map<String, Object> paraMap)
	{
		return aqscfkDao.findAqscfk(paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscfk getById(String id)
	{
		return aqscfkDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscfk aqscfk)
	{
		aqscfkDao.save(aqscfk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscfk aqscfk)
	{
		aqscfkDao.update(aqscfk);
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
		List objects=aqscfkDao.findAqscfk(paraMap);
		
		aqscfkDao.removeAll(objects);
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
				    aqscfkDao.deleteWithFlag(id);
			}
		}
	}


	@Override
	public Aqscxzcfglb getAqscxzcfglbByMap(Map map) {
		// TODO Auto-generated method stub
		return aqscfkDao.getAqscxzcfglbByMap(map);
	}
}
