package com.jshx.module.infomation.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.http.bean.Information;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;


public interface ContentInformationsDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findContentInformations(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ContentInformations getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ContentInformations model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ContentInformations model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	
	/**
	 * 激活信息
	 */
	public void activeInfo(String id);
	
	/**
	 * 设置信息过期
	 */
	public void expireInfo(String id);
	
	/**
	 * 设置信息使用
	 */
	public void inexpireInfo(String id);
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map);
	
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map);
	
	/**
	 * 查询已阅读人员
	 * 2013-07-18
	 */
	public List<NoticeCallback> getUserReadedids(String id);
	
	/**
	 * 保存阅读记录
	 * 2013-07-18
	 */
	public void saveNoticeBack(NoticeCallback noticeCallback);
	
	/**
	 * 更新阅读记录
	 * 2013-07-18
	 */
	public void updateNoticeBack(NoticeCallback noticeCallback);
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geReadedUsersIds(String id,String userId);
	
	/**
	 * 获取公告阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map);
	
	/**
	 * 删除已有阅读记录
	 */
	public void deleteNoticeBackByMap(Map map);
	
	/**
	 * 获取公告
	 */
	public List<Information> findAllInfos(Map<String, Object> paraMap);
	
	/**
	 * 获取公告列表分页
	 */
	public List<Information> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize);
	
	public List<Map> getUserIds (Map map);
	/**
	 * 查询阅读期限内未读的公告
	 */
	public List<Information> findUnread(Map map);
	/**
	 * 查询阅读期限内某公告未读的人
	 */
	public List<NoticeCallback> findUnreadUser(Map map);
	
	public List<String> findAllCandidates(Map<String, Object> paraMap);
	
	public User getMaxDeptCodeUser(Map<String, Object> map);
	
	public List<CodeValue> getHyflList(Map map);
	
	public List<User> getDeptListByHyfl(Map map);
	public List<Dept> getDepartByMap(Map<String,Object> map);
	public List<User> findUsersByMap(Map<String ,Object> map);
}
