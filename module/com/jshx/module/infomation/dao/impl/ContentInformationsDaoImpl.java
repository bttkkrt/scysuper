package com.jshx.module.infomation.dao.impl;

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
import com.jshx.http.bean.Information;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.dao.ContentInformationsDao;

@Component("contentInformationsDao")
public class ContentInformationsDaoImpl extends BaseDaoImpl implements ContentInformationsDao
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
		return this.findPageByHqlId("findContentInformationsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findContentInformations(Map<String, Object> paraMap){
		return this.findListByHqlId("findContentInformationsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ContentInformations getById(String id)
	{
		return (ContentInformations)this.getObjectById(ContentInformations.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ContentInformations contentInformations)
	{
		contentInformations.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(contentInformations);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ContentInformations contentInformations)
	{
		this.saveOrUpdateObject(contentInformations);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ContentInformations.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ContentInformations contentInformations = (ContentInformations)this.getObjectById(ContentInformations.class, id);
		contentInformations.setDelFlag("1");
		this.saveObject(contentInformations);
	}
	/**
	 * 激活信息
	 */
	public void activeInfo(String id){
		ContentInformations contentInformations = (ContentInformations)this.getObjectById(ContentInformations.class, id);
		contentInformations.setDelFlag("0");
		this.saveObject(contentInformations);
	}
	
	/**
	 * 设置信息过期
	 */
	public void expireInfo(String id){
		ContentInformations contentInformations = (ContentInformations)this.getObjectById(ContentInformations.class, id);
		contentInformations.setExpireFlag("1");
		this.saveObject(contentInformations);
	}
	
	/**
	 * 设置信息使用
	 */
	public void inexpireInfo(String id){
		ContentInformations contentInformations = (ContentInformations)this.getObjectById(ContentInformations.class, id);
		contentInformations.setExpireFlag("0");
		this.saveObject(contentInformations);
	}
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map)
	{
		return sqlMapClientTemplate.queryForList("getAllUsersByMap", map);
	}
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map)
	{
		return this.sqlMapClientTemplate.queryForList("getAllDepartByMap", map);
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
	 * author：陆婷
	 * 2013-07-18
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
		return findListByHqlId("queryreadedusersids",map);
	}
	
	/**
	 * 获取公告阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map)
	{
	    return findListByHqlId("querynoticeback", map);
	}
	
	/**
	 * 删除已有阅读记录
	 */
	public void deleteNoticeBackByMap(Map map)
	{
		sqlMapClientTemplate.delete("deleteNoticeBackByMap",map);
	}
	/**
	 * 获取未读公告

	 */
	public List<Information> findAllInfos(Map<String, Object> paraMap)
  	{
    	List<Information> list =sqlMapClientTemplate.queryForList("findContentInformationsList",paraMap);
   
    	return list;
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Information> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		List<Information> list =sqlMapClientTemplate.queryForList("findContentInformationsList",paraMap,totalPageNum,pageSize);
		return list;
	}
	public List<Map> getUserIds (Map map){
		String sqlID = (String) map.get("sqlID");
		return this.sqlMapClientTemplate.queryForList(sqlID,map); 
	}
	/**
	 * 查询阅读期限内未读的公告
	 */
	public List<Information> findUnread(Map map){
		List<Information> list =sqlMapClientTemplate.queryForList("findUnreadList",map);
    	return list;
	}
	/**
	 * 查询阅读期限内某公告未读的人
	 */
	public List<NoticeCallback> findUnreadUser(Map map){
		List<NoticeCallback> list=sqlMapClientTemplate.queryForList("findUnreadUserList",map);
		return list;
	}
	@Override
	public List<String> findAllCandidates(Map<String, Object> paraMap) {
		return     sqlMapClientTemplate.queryForList("findAllCandidates",paraMap);
	}
	
	@Override
	public User getMaxDeptCodeUser(Map<String,Object> map){
		return  (User) sqlMapClientTemplate.queryForObject("getMaxDeptCodeUser",map);
	}
	
	public List<User> findUsersByMap(Map<String ,Object> map)
	{
		return sqlMapClientTemplate.queryForList("getAllUsersByMap1", map);
	}
	
	@Override
	public List<User> getDeptListByHyfl(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getDeptListByHyfl", map);
	}

	@Override
	public List<CodeValue> getHyflList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getHyflList", map);
	}
	@Override
	public List<Dept> getDepartByMap(Map<String,Object> map){
		return  (List<Dept>) sqlMapClientTemplate.queryForList("getDepartByMap",map);
	}
}
