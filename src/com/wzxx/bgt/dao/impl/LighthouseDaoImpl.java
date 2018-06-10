package com.wzxx.bgt.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.bgt.entity.Lighthouse;
import com.wzxx.bgt.dao.LighthouseDao;
import com.wzxx.bgtxx.entity.ExpTabDet;
import com.wzxx.bgtxx.entity.ExpTabDetBean;

@Component("lighthouseDao")
public class LighthouseDaoImpl extends BaseDaoImpl implements LighthouseDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLighthouseByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLighthouse(Map<String, Object> paraMap){
		return this.findListByHqlId("findLighthouseByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Lighthouse getById(String id)
	{
		return (Lighthouse)this.getObjectById(Lighthouse.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Lighthouse lighthouse)
	{
		lighthouse.setId(null);
		this.saveOrUpdateObject(lighthouse);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Lighthouse lighthouse)
	{
		this.saveOrUpdateObject(lighthouse);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Lighthouse.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Lighthouse lighthouse = (Lighthouse)this.getObjectById(Lighthouse.class, id);
		lighthouse.setDelFlag(1);
		this.saveObject(lighthouse);
	}
	/**
	 * 根据titleId查询曝光台详情
	 */
	public List<ExpTabDetBean> findBgtxx(Map map){
		return this.sqlMapClientTemplate.queryForList("findBgtxx",map);
	}
	/**
	 * 查询图片信息
	 */
	public List<String> getPhotosByType(Map map) {
		return this.sqlMapClientTemplate.queryForList("get_photos",map);
	}
	/**
	 * 获取曝光台

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findBgtListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取曝光台
	 */
	public List<Lighthouse> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Lighthouse> list =sqlMapClientTemplate.queryForList("findBgtList",paraMap,s,l);
		return list;
	}
}
