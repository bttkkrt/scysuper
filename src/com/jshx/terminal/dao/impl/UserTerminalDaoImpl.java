package com.jshx.terminal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.terminal.dao.UserTerminalDao;
import com.jshx.terminal.entity.BaseBean;
import com.jshx.terminal.entity.UserTerminal;

@Component("userTerminalDao")
public class UserTerminalDaoImpl extends BaseDaoImpl implements UserTerminalDao
{   
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{   
		return this.findPageByHqlId("findUserTerminalByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findUserTerminal(Map<String, Object> paraMap){
		return this.findListByHqlId("findUserTerminalByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public UserTerminal getById(String id)
	{
		return (UserTerminal)this.getObjectById(UserTerminal.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(UserTerminal userTerminal)
	{
		userTerminal.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(userTerminal);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(UserTerminal userTerminal)
	{
		this.saveOrUpdateObject(userTerminal);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(UserTerminal.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		UserTerminal userTerminal = (UserTerminal)this.getObjectById(UserTerminal.class, id);
		userTerminal.setDelFlag(1);
		this.saveObject(userTerminal);
	}

	@Override
	public List<Department> getDeptInfo(Map map) {
		// TODO Auto-generated method stub
		return this.findListByHql("from Department d where d.delFlag = 0");
	}

	@Override
	public List<User> getUsersByDeptCode(String code) {
		// TODO Auto-generated method stub
		code = "%"+code+"%";
		Map map = new HashMap();
		map.put("code",code);
		return this.findListByHqlId("findUsersByDeptCode", map);
	}
}
