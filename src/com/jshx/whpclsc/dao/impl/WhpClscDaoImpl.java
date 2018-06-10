package com.jshx.whpclsc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.module.admin.entity.User;
import com.jshx.whpclsc.dao.WhpClscDao;
import com.jshx.whpclsc.entity.WhpClsc;

@Component("whpClscDao")
public class WhpClscDaoImpl extends BaseDaoImpl implements WhpClscDao
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
		return this.findPageByHqlId("findWhpClscByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<WhpClsc> findWhpClsc(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhpClscByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public WhpClsc getById(String id)
	{
		return (WhpClsc)this.getObjectById(WhpClsc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(WhpClsc whpClsc)
	{
		whpClsc.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(whpClsc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(WhpClsc whpClsc)
	{
		this.saveOrUpdateObject(whpClsc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(WhpClsc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		WhpClsc whpClsc = (WhpClsc)this.getObjectById(WhpClsc.class, id);
		whpClsc.setDelFlag(1);
		this.saveObject(whpClsc);
	}

	@Override
	public List<User> getUserListByMap(Map map) {
		// TODO Auto-generated method stub
			return sqlMapClientTemplate.queryForList("getWhpUserListByMap",map);
	}
}
