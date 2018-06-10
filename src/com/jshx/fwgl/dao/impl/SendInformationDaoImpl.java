package com.jshx.fwgl.dao.impl;

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
import com.jshx.fwgl.entity.SendInformation;
import com.jshx.fwgl.dao.SendInformationDao;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;

@Component("sendInformationDao")
public class SendInformationDaoImpl extends BaseDaoImpl implements SendInformationDao
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
		return this.findPageByHqlId("findSendInformationByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSendInformation(Map<String, Object> paraMap){
		return this.findListByHqlId("findSendInformationByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SendInformation getById(String id)
	{
		return (SendInformation)this.getObjectById(SendInformation.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SendInformation sendInformation)
	{
		sendInformation.setId(null);
		this.saveOrUpdateObject(sendInformation);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SendInformation sendInformation)
	{
		this.saveOrUpdateObject(sendInformation);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SendInformation.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SendInformation sendInformation = (SendInformation)this.getObjectById(SendInformation.class, id);
		sendInformation.setDelFlag(1);
		this.saveObject(sendInformation);
	}
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map)
	{
		return sqlMapClientTemplate.queryForList("getAllUsers3", map);
	}
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map)
	{
		return this.sqlMapClientTemplate.queryForList("getAllDepart3", map);
	}
	
	/**
	 * 查询已阅读人员
	 */
	public List<NoticeCallback> getUserReadedids(String id)
	{
		 Map map=new HashMap();
		 map.put("id", id);
		 return findListByHqlId("querynoticeback3", map);
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
		return findListByHqlId("queryreadedusersids3",map);
	}
	
	/**
	 * 获取公告阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map)
	{
	    return findListByHqlId("querynoticeback3", map);
	}
	
	/**
	 * 删除已有阅读记录
	 */
	public void deleteNoticeBackByMap(Map map)
	{
		sqlMapClientTemplate.delete("deleteNoticeBackByMap3",map);
	}
	
		
	public List<Map<String,Object>> findAJJldListByMap(Map<String, Object> paraMap){//获取安监局领导角色的Id和name
		String sqlId=(String) paraMap.get("sqlId");
		return this.sqlMapClientTemplate.queryForList(sqlId,paraMap);
		
	}
}
