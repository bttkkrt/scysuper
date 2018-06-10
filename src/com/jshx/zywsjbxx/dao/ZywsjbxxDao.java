package com.jshx.zywsjbxx.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsjbxx.entity.Zybwhqytj;
import com.jshx.zywsjbxx.entity.Zywsglry;
import com.jshx.zywsjbxx.entity.Zywsjbxx;

/**
 * @author 高强
 *  createtime 13年11月20
 *  desc 职业卫生基本信息service层
 *
 */
public interface ZywsjbxxDao extends BaseDao
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
	public List findZywsjbxx(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsjbxx getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsjbxx model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsjbxx model);

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

	public List<Zywsglry> getZywsglrysById(String id);

	public void saveZywsglry(Zywsglry zywsglry);

	public Zywsjbxx getByCompanyId(String id);

	public Zywsglry getZywsglryById(String id);

	public void updateZywsglry(Zywsglry zywsglryOld);

	public void deleteZywsglryDel(String ids);
	
	public List<Zybwhqytj> getZywhqytjListByMap(Map map);
	
	public Zybwhqytj getZywhqytjByMap(Map map);
}
