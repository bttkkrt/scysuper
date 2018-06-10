/**
 * Class Name : CardoneinfoService
 * Class Description：远程视频
 * Writer：陆婷
 * CreateTime：2013-12-10
 */
package com.jshx.carDoneInfo.service;

import java.util.List;
import java.util.Map;

import com.jshx.carDoneInfo.entity.CarEquipment;
import com.jshx.carDoneInfo.entity.CarEquipmentState;
import com.jshx.core.base.service.BaseService;

public interface CardoneinfoService extends BaseService
{
	/**
	 * Function Name: getEquipmentList
	 * Function Description：获取远程视频列表
	 * Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public List<CarEquipment> getEquipmentList(Map map);
	
	/**
	 * Function Name: saveEquipment
	 * Function Description：保存远程视频
	 * Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public void saveEquipment(CarEquipment carEquipment);
	
	/**
	 * Function Name: deleteEquip
	 * Function Description：删除远程视频
	 * Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public void deleteEquip(Map map);
	
	/**
	 * Function Name: saveEquipmentState
	 * Function Description：保存密码修改状态
	 * Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public void saveEquipmentState(CarEquipmentState carEquipmentState);
	
	/**
	 * Function Name: CarEquipmentState
	 * Function Description：获取原始密码
	 * Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public CarEquipmentState getEquipmentState(Map map);
	
	public List<CarEquipment> getEquipmentLists(Map map);
	
	public int getEquipmentListSize(Map map);

	/**
	 * 获取视频
	 * @param map
	 * @return
	 */
	public List<CarEquipment> getEquipmentListForShowname(Map map);
}
