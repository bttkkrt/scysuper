package com.jshx.whysjbqk.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whysjbqk.entity.Whysjbqk;
import com.jshx.whysjbqk.entity.Whysjbqkglb;


public interface WhysjbqkDao extends BaseDao
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
	public List findWhysjbqk(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whysjbqk getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whysjbqk model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whysjbqk model);

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
	 * 保存危害因素关联信息 2013-11-21 李军
	 * @param model 信息
	 */
	public void saveGlb(Whysjbqkglb model);

	/**
	 * 查询 危害因素关联信息 列表 2013-11-21
	 */
	public List<Whysjbqkglb> getWhysjbqkglbList(Map map);
	/**
	 * 根据id删除危害因素关联信息 李军 2013-11-21
	 */
	public void delWhysjbqkglb(String id);
	/**
	 * 根据企业id获取危害因素id 李军 2013-11-21
	 */
	public String getWhysjbqkIdByQyid(String id);

}
