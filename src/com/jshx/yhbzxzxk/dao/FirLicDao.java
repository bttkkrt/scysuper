package com.jshx.yhbzxzxk.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yhbzxzxk.entity.FirLic;


public interface FirLicDao extends BaseDao
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
	public List findFirLic(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FirLic getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(FirLic model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(FirLic model);

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
	 * 获取烟花爆竹经营许可证
	 */
	public int findAllInfos(Map<String, Object> paraMap);
	
	/**
	 * 获取烟花爆竹经营许可证列表分页
	 */
	public List<FirLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize);
}
