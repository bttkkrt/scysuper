package com.jshx.zywsjbxx.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
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
public interface ZywsjbxxService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

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
	public String save(Zywsjbxx model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsjbxx model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);
	/**
	 * desc 查询职业卫生管理人员
	 * @param id 职业卫生信息id
	 * writer 高强
	 * createtime 11月21
	 */
	public List<Zywsglry> getZywsglrysById(String id);
	/**
	 * desc 保存职业卫生管理人员
	 * @param zywsglry 职业卫生管理人员信息
	 * writer 高强
	 * createtime 11月21
	 */
	public void saveZywsglry(Zywsglry zywsglry);
	/**
	 * desc 查询职业卫生信息
	 * @param id  企业id
	 * writer 高强
	 * createtime 11月21
	 */
	public Zywsjbxx getByCompanyId(String id);
	/**
	 * desc 查询职业卫生管理人员信息
	 * @param id  职业卫生管理人员id
	 * writer 高强
	 * createtime 11月21
	 */
	public Zywsglry getZywsglryById(String id);
	/**
	 * desc 更新职业卫生信息
	 * @param zywsglryOld 职业卫生管理人员信息
	 * writer 高强
	 * createtime 11月21
	 */
	public void updateZywsglry(Zywsglry zywsglryOld);
	/**
	 * desc 删除管理人信息
	 * @param  id 管理人信息id
	 * writer 高强
	 * createtime 11月21日
	 */
	public void deleteZywsglryDel(String ids);
	
	public List<Zybwhqytj> getZywhqytjListByMap(Map map);
	
	public Zybwhqytj getZywhqytjByMap(Map map);

}
