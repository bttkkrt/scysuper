package com.jshx.qyjbxx.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.dao.EntBaseInfoDao;
import com.jshx.qyjbxx.entity.EntBaseInfo;

@Component("entBaseInfoDao")
public class EntBaseInfoDaoImpl extends BaseDaoImpl implements EntBaseInfoDao
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
		return this.findPageByHqlId("findEntBaseInfoByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<EntBaseInfo> findEntBaseInfo(Map<String, Object> paraMap){
		return this.findListByHqlId("findEntBaseInfoByMapss", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EntBaseInfo getById(String id)
	{
		return (EntBaseInfo)this.getObjectById(EntBaseInfo.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EntBaseInfo entBaseInfo)
	{
		entBaseInfo.setId(null);
		this.saveOrUpdateObject(entBaseInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EntBaseInfo entBaseInfo)
	{
		this.saveOrUpdateObject(entBaseInfo);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EntBaseInfo.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EntBaseInfo entBaseInfo = (EntBaseInfo)this.getObjectById(EntBaseInfo.class, id);
		entBaseInfo.setDelFlag(1);
		this.saveObject(entBaseInfo);
	}

	@Override
	public EntBaseInfo findEntBaseInfoByMap(Map map) {
		// TODO Auto-generated method stub
		EntBaseInfo EntBaseInfo = new EntBaseInfo();
		List<EntBaseInfo> list = this.findListByHqlId("findEntBaseInfoByMaps", map);
		if(list.size() != 0)
		{
			EntBaseInfo = list.get(0);
		}
		return EntBaseInfo;
	}

	@Override
	public List<EntBaseInfo> getEntBaseInfoListByUserAndType(Map map,
			int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getEntBaseInfoListByUserAndType",map,s,l);
	}

	@Override
	public int getEntBaseInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getEntBaseInfoListSizeByUserAndType",map);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(user);
	}

}
