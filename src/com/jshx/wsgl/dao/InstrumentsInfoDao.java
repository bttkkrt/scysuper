package com.jshx.wsgl.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.entity.LockUser;


public interface InstrumentsInfoDao extends BaseDao
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
	public List<InstrumentsInfo> findInstrumentsInfo(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InstrumentsInfo getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InstrumentsInfo model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InstrumentsInfo model);

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
	 * 根据文书类型及案件编号删除已有文书，保证每个案件的每一类文书均唯一
	 * @author luting
	 * 2015-10-25
	 */
	public void deleteInstrumentsInfoByMap(Map map);
	
	/**
	 * 获取文书列表
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public List<InstrumentsInfo> findInstrumentsInfos(Map<String, Object> paraMap);
	
	/**
	 * 获取文书列表
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public List<InstrumentsInfo> findInstrumentsInfoss(Map<String, Object> paraMap);
	
	/**
	 * 获取文书列表
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public List<InstrumentsInfo>  getInstrumentsInfoListByUserAndType(Map map,int start,int limit);
	
	
	/**
	 * 获取文书列表条数
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public int  getInstrumentsInfoListSizeByUserAndType(Map map );
	
	/**
	 * 获取文书编号最大值
	  * @param map
	 * @return
	 * @author luting 2015-11-25
	 */
	public int getMaxAjhNumByMap(Map map);
	
	/**
	 * 将所有案件相关文书置为不可修改状态
	 * @author luting 2015-12-24
	 */
	public void updateAllWsInfoByMap(Map map);
	
	/**
	 * 查询所有部门
	 */
	public List<Dept> getAllDepartByMap(Map map);
	
	/**
	 * 查询所有人员
	 */
	public List<User> getAllUsersByMap(Map map);
	
	/**
	 * 删除已锁定人员
	 */
	public void deleteLockUser(Map map);
	
	/**
	 * 新增锁定人员
	 */
	public void saveLockUser(LockUser lockUser);
	
	/**
	 * 查询询问笔录被询问人
	 */
	public List<String> queryXwblUser(Map map);
}
