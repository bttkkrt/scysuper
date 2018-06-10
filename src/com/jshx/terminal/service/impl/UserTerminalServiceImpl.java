package com.jshx.terminal.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.terminal.dao.UserTerminalDao;
import com.jshx.terminal.entity.UserTerminal;
import com.jshx.terminal.service.UserTerminalService;

@Service("userTerminalService")
public class UserTerminalServiceImpl extends BaseServiceImpl implements UserTerminalService
{
	/**
	 * Dao类
	 */
	@Resource
	private UserTerminalDao userTerminalDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return userTerminalDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public UserTerminal getById(String id)
	{
		return userTerminalDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(UserTerminal userTerminal)
	{
		userTerminalDao.save(userTerminal);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(UserTerminal userTerminal)
	{
		userTerminalDao.update(userTerminal);
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
		List objects=userTerminalDao.findUserTerminal(paraMap);
		
		userTerminalDao.removeAll(objects);
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
				    userTerminalDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Department> getDeptInfo(Map map) {
		// TODO Auto-generated method stub
		return userTerminalDao.getDeptInfo(map);
	}

	@Override
	public List<User> getUsersByDeptCode(String code) {
		// TODO Auto-generated method stub
		return userTerminalDao.getUsersByDeptCode(code);
	}
}
