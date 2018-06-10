package com.jshx.gpqyyhzg.dao.impl;

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
import com.jshx.gpqyyhzg.dao.GpqyyhzgDao;
import com.jshx.gpqyyhzg.entity.Gpqyyhzg;

@Component("gpqyyhzgDao")
public class GpqyyhzgDaoImpl extends BaseDaoImpl implements GpqyyhzgDao
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
		return this.findPageByHqlId("findGpqyyhzgByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGpqyyhzg(Map<String, Object> paraMap){
		return this.findListByHqlId("findGpqyyhzgByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gpqyyhzg getById(String id)
	{
		return (Gpqyyhzg)this.getObjectById(Gpqyyhzg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gpqyyhzg gpqyyhzg)
	{
		gpqyyhzg.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(gpqyyhzg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gpqyyhzg gpqyyhzg)
	{
		this.saveOrUpdateObject(gpqyyhzg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gpqyyhzg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gpqyyhzg gpqyyhzg = (Gpqyyhzg)this.getObjectById(Gpqyyhzg.class, id);
		gpqyyhzg.setDelFlag(1);
		this.saveObject(gpqyyhzg);
	}

	@Override
	public Gpqyyhzg getGpdbByMap(Map map) {
		// TODO Auto-generated method stub
		Gpqyyhzg Gpqyyhzg = new Gpqyyhzg();
		try {
			Gpqyyhzg =  (Gpqyyhzg) sqlMapClientTemplate.getSqlMapClient().queryForObject("getGpdbDataByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Gpqyyhzg;
	}

	@Override
	public Gpqyyhzg findGpqyyhzgAllByMap(Map map) {
		// TODO Auto-generated method stub
		Gpqyyhzg Gpqyyhzg = new Gpqyyhzg();
		try {
			Gpqyyhzg =  (Gpqyyhzg) sqlMapClientTemplate.getSqlMapClient().queryForObject("findGpqyyhzgAllByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Gpqyyhzg;
	}

	@Override
	public List<Gpqyyhzg> findGpqyyhzgAllListByMap(Map map) {
		// TODO Auto-generated method stub
		List<Gpqyyhzg> list = new ArrayList<Gpqyyhzg>();
		try {
			list =  sqlMapClientTemplate.getSqlMapClient().queryForList("findGpqyyhzgAllListByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
