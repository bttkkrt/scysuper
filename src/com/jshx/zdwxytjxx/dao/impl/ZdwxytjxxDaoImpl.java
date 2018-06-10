package com.jshx.zdwxytjxx.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxytjxx.dao.ZdwxytjxxDao;
import com.jshx.zdwxytjxx.entity.Zdwxytj;
import com.jshx.zdwxytjxx.entity.Zdwxytjxx;

@Component("zdwxytjxxDao")
public class ZdwxytjxxDaoImpl extends BaseDaoImpl implements ZdwxytjxxDao
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
		return this.findPageByHqlId("findZdwxytjxxByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zdwxytjxx> findZdwxytjxx(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxytjxxByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxytjxx getById(String id)
	{
		return (Zdwxytjxx)this.getObjectById(Zdwxytjxx.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxytjxx zdwxytjxx)
	{
		zdwxytjxx.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxytjxx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxytjxx zdwxytjxx)
	{
		this.saveOrUpdateObject(zdwxytjxx);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxytjxx.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxytjxx zdwxytjxx = (Zdwxytjxx)this.getObjectById(Zdwxytjxx.class, id);
		zdwxytjxx.setDelFlag(1);
		this.saveObject(zdwxytjxx);
	}

	@Override
	public Zdwxytj getZdwxytjByMap(Map map) {
		// TODO Auto-generated method stub
		Zdwxytj zdwxytj = new Zdwxytj();
		try {
			zdwxytj = (Zdwxytj) sqlMapClientTemplate.getSqlMapClient().queryForObject("getZdwxytjByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zdwxytj;
	}
}
