package com.jshx.vedioname.dao;

import java.util.List;
import java.util.Map;

import com.jshx.company.entity.Company;
import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.vedioname.entity.VedioName;

public interface VedioNameDao extends BaseDao {
	/**
	 * 	分页查询
	 * 
	 * 	@param page
	 *            分页信息
	 * 	@param paraMap
	 *            查询条件信息
	 * 	@return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 	查询所有记录
	 * 
	 * 	@param page
	 *            分页信息
	 * 	@param paraMap
	 *            查询条件信息
	 * 	@return 分页信息
	 */
	public List findVedioName(Map<String, Object> paraMap);

	/**
	 * 	根据主键ID查询信息
	 * 
	 *	@param id
	 *            主键ID
	 * 	@return 主键ID对应的信息
	 */
	public VedioName getById(String id);

	/**
	 * 	保存信息
	 * 
	 * 	@param model
	 *            信息
	 */
	public void save(VedioName model);

	/**
	 * 	修改信息
	 * 
	 * 	@param model
	 *            信息
	 */
	public void update(VedioName model);

	/**
	 * 	物理删除信息
	 * 
	 * 	@param ids
	 *            主键ID
	 */
	public void delete(String id);

	/**
	 * 	逻辑删除信息
	 * 
	 * 	@param ids
	 *            主键ID
	 */
	public void deleteWithFlag(String id);

	/**
	 * 	查询设备列表中的企业数量
	 */
	public int getCompanyCount(Map<String, Object> condition);
	
	/**
	 * 	分页查询设备列表中的企业
	 */
	public List<Company> getCompanyByPage(Map<String, Object> condition);

}
