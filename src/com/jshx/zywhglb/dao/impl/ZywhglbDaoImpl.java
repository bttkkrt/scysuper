package com.jshx.zywhglb.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywhglb.dao.ZywhglbDao;
import com.jshx.zywhglb.entity.Zywhglb;

@Component("zywhglbDao")
public class ZywhglbDaoImpl extends BaseDaoImpl implements ZywhglbDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZywhglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zywhglb> findZywhglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywhglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywhglb getById(String id)
	{
		return (Zywhglb)this.getObjectById(Zywhglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywhglb zywhglb)
	{
		zywhglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zywhglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywhglb zywhglb)
	{
		this.saveOrUpdateObject(zywhglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywhglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywhglb zywhglb = (Zywhglb)this.getObjectById(Zywhglb.class, id);
		zywhglb.setDelFlag(1);
		this.saveObject(zywhglb);
	}

	@Override
	public List<Zywhglb> getFcListByMap(Map map) {
		// TODO Auto-generated method stub
		List<Zywhglb> list = new ArrayList<Zywhglb>();
		try {
			list =  sqlMapClientTemplate.getSqlMapClient().queryForList("getFcListByMap", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
