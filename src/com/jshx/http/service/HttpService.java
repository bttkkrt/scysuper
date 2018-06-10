package com.jshx.http.service;

import java.util.List;
import java.util.Map;

import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.factorypicture.entity.Factorypicture;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.KeyBean;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.PhotoBean;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.xkzsb.entity.JshxXkzsb;
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;

public interface HttpService {
	public List<MyUserBean> getUserBeanByMap(Map map);//查询用户信息
	
	public KeyBean getVersionNumberByForm(Map map);//根据手机型号获取版本信息

	public DataBean getPhotoListByType(Map map);//根据图片类型查询图片的名称 luting 2015-10-21
	
	public List<DataBean> getRoleByUserId(Map map);//根据用户id查询该用户拥有的角色列表 lj 2013-07-28
	
	/**
	 * 查询List<Map<String,Object>>(通用)
	 * 费谦
	 * 2015-10-12
	 */
	public List<Map<String,Object>> findListDataByMap(Map<String, Object> paraMap);
	
	/**
	 * update(通用)
	 * 费谦
	 * 2014-11-6
	 */
	public int updateByMap(Map<String, Object> paraMap);
	
	public int getListCountbyMap(Map map);//获取信息列表总数 lj 2015-10-15

	public List<Map> getListByMap(Map map);//获取信息列表 lj 2015-10-15

	public Map getMapByMap(Map map);//获取信息详情对象 lj 2015-10-15

	public void updateMapByMap(Map map);//更新信息对象 lj 2015-10-15
	
	public boolean judgeRoleCode(String userId,String roleCode);//判断用户userId是否具有角色roleCode
	

	public List<Map<String,String>> findcheckName(Map<String, String> paraMap);//找出检查项
	
	
	/**
	 * 中队队员和中队队长查看监管网格下的企业信息
	 * 费谦
	 * 2016-1-25
	 */
	public Map<String, Object> addParamByRole(Map<String, Object> paraMap,String userId,String deptCode);
	
	public List<SecProFeeAcc> getSecProFeeAccListByMap(Map map);
	
	
	
	
	public void savePhotoInfo(PhotoBean bean);//保存图片信息

	public List<DataBean> getTownList(Map map);//获取所在镇列表 lj 2013-07-23
	public List<DataBean> getCountyList(Map map);//获取所在县列表 lj 2015-01-16
	public List<DataBean> getWSList(Map map);//获取执法文书列表 lj 2013-07-23
	public List<CommonTroubleBean> getCommonTroubleList(Map map);//获取一般隐患信息列表 lj 2013-07-23
	public List<CommonTroubleBean> getAllCommonTrouble(Map map);//一般安全隐患部门检查完整数据查询  李海棠  2014-04-03
	public int getAllCommonTroubleCount(Map map);//一般安全隐患部门检查完整数据条数查询  李海棠  2014-04-03
	public int getCommonTroubleListCount(Map map);//获取一般隐患信息列表总条数 lj 2013-07-23
	public List<DataBean> getDeptList(Map map);//获取填报部门信息列表 lj 2013-07-23
	public List<CommonTroubleBean> getMajorTroubleList(Map map);//获取重大隐患信息列表 lj 2013-07-23
	public List<CommonTroubleBean> getAllMajorTrouble(Map map);//重大安全隐患部门检查完整数据查询  李海棠  2014-04-03
	public int getAllMajorTroubleCount(Map map);//重大安全隐患部门检查完整数据条数查询  李海棠  2014-04-03
	public int getMajorTroubleListCount(Map map);//获取重大隐患信息列表总条数 lj 2013-07-23
	
	public DataBean getDeptCodeByUserId(Map map);//根据用户id查询该用户的deptid lj 2013-07-28
	public List<String> getAllDeptIdsByDeptCode(Map map);//根据部门code 查询关联的部门id列表 lj 2013-07-28
	
	public List<Gpdb> getSuperviseHandlingListByMap(Map map);//获取挂牌督办列表
	public int getTotalNumberOfSupreviseHandlingByMap(Map map);//获取挂牌督办总条数

	public List<JshxZcyhsb> getZcyhListByMap(Map map);//获取自查隐患列表 2013-09-25 陆婷
	public int getZcyhListSizeByMap(Map map);//获取自查隐患列表总条数 2013-09-25 陆婷
	
	public List<JshxZazPxb> getZazpxListByMap(Map map);//获取主要负责人、安全管理员安全培训和职业卫生培训情况列表 2013-09-25 陆婷
	public int getZazpxListSizeByMap(Map map);//获取主要负责人、安全管理员安全培训和职业卫生培训情况列表总条数 2013-09-25 陆婷
	
	public List<JshxTzzyPxb> getTzzypxListByMap(Map map);//获取特种作业人员培训情况列表 2013-09-25 陆婷
	public int getTzzypxListSizeByMap(Map map);//获取特种作业人员培训情况列表总条数 2013-09-25 陆婷
	
	public List<JshxXrcPxb> getXrcpxListByMap(Map map);//获取新入厂员工的培训情况列表 2013-09-25 陆婷
	public int getXrcpxListSizeByMap(Map map);//获取新入厂员工的培训情况列表总条数 2013-09-25 陆婷
	
	public List<JshxZzxtPxb> getZzxtpxListByMap(Map map);//获取员工再培训及转岗、下岗、脱岗培训情况列表 2013-09-25 陆婷
	public int getZzxtpxListSizeByMap(Map map);//获取员工再培训及转岗、下岗、脱岗培训情况列表总条数 2013-09-25 陆婷
	
	public List<JshxXkzsb> getXzxkListByMap(Map map);//获取行政许可信息列表 2013-09-25 陆婷
	public int getXzxkListSizeByMap(Map map);//获取行政许可信息列表总条数 2013-09-25 陆婷
	
	public List<Factorypicture> getCqtpListByMap(Map map);//获取厂区图片列表 2013-09-25 陆婷
	public int getCqtpListSizeByMap(Map map);//获取厂区图片列表总条数 2013-09-25 陆婷
	
	public List<JshxZzbw> getZzbwListByMap(Map map);//获取关键装置部位列表 2013-09-25 陆婷
	public int getZzbwListSizeByMap(Map map);//获取关键装置部位列表总条数 2013-09-25 陆婷
	
	public List<Zjjtzsb> getZjjTzsbListByMap(Map map);//获取质监局特种设备列表 2014-1-2  陆婷
	public int getZjjTzsbListSizeByMap(Map map);//获取质监局特种设备列表总条数 2014-1-2  陆婷
	
	public List<Zjjtzsbzyry> getZjjTzsbzyryListByMap(Map map);//获取质监局特种设备作业人员列表 2014-7-4  陆婷
	
	

	public List<Map<String,String>> getInspectTaskList(Map map);//获取安全检查任务列表 hanxc 2014-11-13
		public int getInspectTaskListCountByMap(Map map);//获取安全检查任务数 hanxc 2014-11-13
		

		public List<Map<String,String>> GetInspectItemList(Map map);//获取安全检查项列表 hanxc 2014-11-13
		public int GetInspectItemListCountByMap(Map map);//获取安全检查项数 hanxc 2014-11-13

		public List<Map<String, String>> getNewsphotoList(Map map);//获取新闻图片列表 GY-UPDATE 2015-01-07

		public List<Map<String, String>> getZcyhNumByMap(Map map);

		public List<Map<String, String>> getInspectTaskNumByMonth(Map map);

		public List<Map<String, String>> getInspectTaskNumByYear(Map map);
}
