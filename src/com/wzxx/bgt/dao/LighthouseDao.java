package com.wzxx.bgt.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.bgt.entity.Lighthouse;
import com.wzxx.bgtxx.entity.ExpTabDet;
import com.wzxx.bgtxx.entity.ExpTabDetBean;


public interface LighthouseDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLighthouse(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Lighthouse getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Lighthouse model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Lighthouse model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	/**
	 * 根据titleId查询曝光台详情
	 */
	public List<ExpTabDetBean> findBgtxx(Map map);
	/**
	 * 查询图片信息
	 */
	public List<String> getPhotosByType(Map map);
	/**
	 * 获取曝光台
	 */
	public int findAllInfos(Map<String, Object> paraMap);
	
	/**
	 * 获取曝光台列表分页
	 */
	public List<Lighthouse> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize);
}
