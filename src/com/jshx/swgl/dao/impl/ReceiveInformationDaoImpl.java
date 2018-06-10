package com.jshx.swgl.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.swgl.entity.ReceiveInformation;
import com.jshx.swgl.dao.ReceiveInformationDao;

@Component("receiveInformationDao")
public class ReceiveInformationDaoImpl extends BaseDaoImpl implements ReceiveInformationDao
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
		return this.findPageByHqlId("findReceiveInformationByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findReceiveInformation(Map<String, Object> paraMap){
		return this.findListByHqlId("findReceiveInformationByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReceiveInformation getById(String id)
	{
		return (ReceiveInformation)this.getObjectById(ReceiveInformation.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReceiveInformation receiveInformation)
	{
		receiveInformation.setId(null);
		this.saveOrUpdateObject(receiveInformation);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReceiveInformation receiveInformation)
	{
		this.saveOrUpdateObject(receiveInformation);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ReceiveInformation.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ReceiveInformation receiveInformation = (ReceiveInformation)this.getObjectById(ReceiveInformation.class, id);
		receiveInformation.setDelFlag(1);
		this.saveObject(receiveInformation);
	}
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map)
	{
		return sqlMapClientTemplate.queryForList("getAllUsers", map);
	}
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map)
	{
		return this.sqlMapClientTemplate.queryForList("getAllDepart", map);
	}
	
	/**
	 * 查询已阅读人员
	 */
	public List<NoticeCallback> getUserReadedids(String id)
	{
		 Map map=new HashMap();
		 map.put("id", id);
		 return findListByHqlId("querynoticeback", map);
	}
	
	/**
	 * 保存阅读记录
	 */
	public void saveNoticeBack(NoticeCallback noticeCallback)
	{
		noticeCallback.setId(null);
		saveOrUpdateObject(noticeCallback);
	}
	
	/**
	 * 更新阅读记录
	 */
	public void updateNoticeBack(NoticeCallback noticeCallback)
	{
		saveOrUpdateObject(noticeCallback);
	}
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geReadedUsersIds(String id,String userId)
	{
		Map map=new HashMap();
		map.put("id", id);
		map.put("userid", userId);
		return findListByHqlId("queryreadedusersids2",map);
	}
	
	/**
	 * 获取公告阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map)
	{
	    return findListByHqlId("querynoticeback2", map);
	}
	
	/**
	 * 删除已有阅读记录
	 */
	public void deleteNoticeBackByMap(Map map)
	{
		sqlMapClientTemplate.delete("deleteNoticeBackByMap2",map);
	}
}
