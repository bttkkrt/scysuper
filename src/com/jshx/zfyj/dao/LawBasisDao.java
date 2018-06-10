package com.jshx.zfyj.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zfyj.entity.LawBasis;


public interface LawBasisDao extends BaseDao
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
	public List<LawBasis> findLawBasis(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LawBasis getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LawBasis model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LawBasis model);

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
	 * 获取执法依据列表
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public List<LawBasis>  getLawBasisListByUserAndType(Map map,int start,int limit);
	
	
	/**
	 * 获取执法依据列表条数
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public int  getLawBasisListSizeByUserAndType(Map map );
}
