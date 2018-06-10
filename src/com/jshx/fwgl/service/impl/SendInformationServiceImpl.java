package com.jshx.fwgl.service.impl;

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
import com.jshx.fwgl.dao.SendInformationDao;
import com.jshx.fwgl.entity.SendInformation;
import com.jshx.fwgl.service.SendInformationService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.entity.NoticeCallback;

@Service("sendInformationService")
public class SendInformationServiceImpl extends BaseServiceImpl implements SendInformationService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("sendInformationDao")
	private SendInformationDao sendInformationDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return sendInformationDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SendInformation getById(String id)
	{
		return sendInformationDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SendInformation sendInformation)
	{
		sendInformationDao.save(sendInformation);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SendInformation sendInformation)
	{
		sendInformationDao.update(sendInformation);
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
		List objects=sendInformationDao.findSendInformation(paraMap);
		
		sendInformationDao.removeAll(objects);
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
				    sendInformationDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map)
	{
		return sendInformationDao.getAllUsersByMap(map);
	}
	
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map)
	{
		return sendInformationDao.getAllDepartByMap(map);
	}
	
	/**
	 * 查询已阅读人员
	 */
	public List<NoticeCallback> getUserReadedids(String id)
	{
		return sendInformationDao.getUserReadedids(id);
	}
	
	/**
	 * 保存阅读记录
	 */
	@Transactional
	public void saveNoticeBack(NoticeCallback noticeCallback)
	{
		sendInformationDao.saveNoticeBack(noticeCallback);
	}
	
	/**
	 * 更新阅读记录
	 */
	@Transactional
	public void updateNoticeBack(NoticeCallback noticeCallback)
	{
		sendInformationDao.updateNoticeBack(noticeCallback);
	}
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geReadedUsersIds(String id,String userId)
	{
		return sendInformationDao.geReadedUsersIds(id, userId);
	}
	
	/**
	 * 获取当前人员阅读记录
	 */
	public List<NoticeCallback> geBackById(Map map)
	{
		return sendInformationDao.geBackById(map);
	}
	
	/**
	 * 删除已有阅读记录
	 */
	@Transactional
	public void deleteNoticeBackByMap(Map map)
	{
		sendInformationDao.deleteNoticeBackByMap(map);
	}
	
	
	//获取安监局领导角色的Id和name
	public List<Map<String,Object>> findAJJldListByMap(Map<String, Object> paraMap){//获取安监局领导角色的Id和name
		return sendInformationDao.findAJJldListByMap(paraMap);
	}
}
