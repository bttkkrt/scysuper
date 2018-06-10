package com.jshx.swgl.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.swgl.entity.ReceiveInformation;

public interface ReceiveInformationService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReceiveInformation getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReceiveInformation model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReceiveInformation model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);
	
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
}
