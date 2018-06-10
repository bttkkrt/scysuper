package com.jshx.fwgl.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.fwgl.entity.SendInformation;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;


public interface SendInformationDao extends BaseDao
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
	public List findSendInformation(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SendInformation getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SendInformation model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SendInformation model);

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
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map);
	
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map);
	
	/**
	 * 查询已阅读人员
	 */
	public List<NoticeCallback> getUserReadedids(String id);
	
	/**
	 * 保存阅读记录
	 */
	public void saveNoticeBack(NoticeCallback noticeCallback);
	
	/**
	 * 更新阅读记录
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
	
	public List<Map<String,Object>> findAJJldListByMap(Map<String, Object> paraMap);//获取安监局领导角色的Id和name
}
