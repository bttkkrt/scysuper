package com.jshx.module.infomation.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.http.bean.Information;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.dao.ContentInformationsDao;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentInformationsService;

@Service("contentInformationsService")
public class ContentInformationsServiceImpl extends BaseServiceImpl implements ContentInformationsService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("contentInformationsDao")
	private ContentInformationsDao contentInformationsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return contentInformationsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ContentInformations getById(String id)
	{
		return contentInformationsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void save(ContentInformations contentInformations)
	{
		contentInformationsDao.save(contentInformations);
	}

	/**
	 * 修改信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void update(ContentInformations contentInformations)
	{
		contentInformationsDao.update(contentInformations);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=contentInformationsDao.findContentInformations(paraMap);
		
		contentInformationsDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    contentInformationsDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 禁用信息
	 */
	@Transactional
	public void inactiveInfo(String id){
		contentInformationsDao.deleteWithFlag(id);
	}
	
	/**
	 * 激活被禁用的信息
	 */
	@Transactional
	public void activeInfo(String id){
		contentInformationsDao.activeInfo(id);
	}
	
	/**
	 * 设置信息过期
	 */
	@Transactional
	public void expireInfo(String id){
		contentInformationsDao.expireInfo(id);
	}
	
	/**
	 * 设置信息使用
	 */
	@Transactional
	public void inexpireInfo(String id){
		contentInformationsDao.inexpireInfo(id);
	}
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map)
	{
		return contentInformationsDao.getAllUsersByMap(map);
	}
	
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map)
	{
		return contentInformationsDao.getAllDepartByMap(map);
	}
	
	/**
	 * 查询已阅读人员
	 */
	public List<NoticeCallback> getUserReadedids(String id)
	{
		return contentInformationsDao.getUserReadedids(id);
	}
	
	/**
	 * 保存阅读记录
	 */
	@Transactional
	public void saveNoticeBack(NoticeCallback noticeCallback)
	{
		contentInformationsDao.saveNoticeBack(noticeCallback);
	}
	
	/**
	 * 更新阅读记录
	 */
	@Transactional
	public void updateNoticeBack(NoticeCallback noticeCallback)
	{
		contentInformationsDao.updateNoticeBack(noticeCallback);
	}
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geReadedUsersIds(String id,String userId)
	{
		return contentInformationsDao.geReadedUsersIds(id, userId);
	}
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map)
	{
		return contentInformationsDao.geBackById(map);
	}
	
	/**
	 * 删除已有阅读记录
	 */
	@Transactional
	public void deleteNoticeBackByMap(Map map)
	{
		contentInformationsDao.deleteNoticeBackByMap(map);
	}
	
	/**
	 * 获取未读公告
	 */
	public List<Information> findAllInfos(Map<String, Object> paraMap)
  	{
    	return contentInformationsDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Information> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return contentInformationsDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
	public List<Map> getUserIds (Map map){
		return contentInformationsDao.getUserIds(map);
	}
	/**
	 * 查询阅读期限内未读的公告
	 */
	public List<Information> findUnread(Map map){
		return contentInformationsDao.findUnread(map);
	}
	/**
	 * 查询阅读期限内某公告未读的人
	 */
	public List<NoticeCallback> findUnreadUser(Map map){
		return contentInformationsDao.findUnreadUser(map);
	}
	@Override
	public List<String> findAllCandidates(Map<String, Object> paraMap) {
		return  contentInformationsDao.findAllCandidates(paraMap);
	}
	
	@Override
	public User getMaxDeptCodeUser(Map<String,Object> map){
		return  contentInformationsDao.getMaxDeptCodeUser(map);
	}
	
	@Override
	public List<User> getDeptListByHyfl(Map map) {
		// TODO Auto-generated method stub
		return contentInformationsDao.getDeptListByHyfl(map);
	}

	@Override
	public List<CodeValue> getHyflList(Map map) {
		// TODO Auto-generated method stub
		return contentInformationsDao.getHyflList(map);
	}
	@Override
	public List<Dept> getDepartByMap(Map<String,Object> map){
		return contentInformationsDao.getDepartByMap(map);
	}
	@Override
	public List<User> findUsersByMap(Map<String ,Object> map)
	{
		return contentInformationsDao.findUsersByMap(map);
	}
}
