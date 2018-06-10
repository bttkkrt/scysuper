/**
 * Class Name : CardoneinfoAction
 * Class Description：远程视频
 * Writer：陆婷
 * CreateTime：2013-12-10
 */
package com.jshx.carDoneInfo.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.carDoneInfo.entity.CarEquipment;
import com.jshx.carDoneInfo.entity.CarEquipmentState;
import com.jshx.carDoneInfo.entity.Tree;
import com.jshx.carDoneInfo.service.CardoneinfoService;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.utils.SysPropertiesUtil;

public class CardoneinfoAction extends BaseAction {
	/**
	 * 业务类
	 */
	@Autowired
	private CardoneinfoService cardoneinfoService;
	@Autowired
	private CompanyService companyService;

	/**
	 * 用于接收字符串
	 */
	private String str;

	/**
	 * 视频列表
	 */
	private List<CarEquipment> equipmentList = new ArrayList<CarEquipment>();

	/**
	 * 旧密码
	 */
	private String oldpass;

	/**
	 * 新密码
	 */
	private String newpass;

	/**
	 * 视频
	 */
	private CarEquipment carEquipment = new CarEquipment();

	/**
	 * Function Name: getDevList Function Description：获取远程视频列表 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public String getDevList() {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
		map.put("month", sdf.format(new Date()));
		CarEquipmentState a = cardoneinfoService.getEquipmentState(map);
		if (a != null) {
			oldpass = a.getPassword();
		} else {
			CarEquipmentState b = cardoneinfoService.getEquipmentState(null);
			if (b == null) {
				b = new CarEquipmentState();
				b.setCreateTime(new Date());
				b.setCreateUserID(this.getLoginUserId());
				b.setMonth(sdf.format(new Date()));
				b.setPassword("xcqajj@111111");
				cardoneinfoService.saveEquipmentState(b);
				oldpass = b.getPassword();
			} else {
				oldpass = b.getPassword();
				newpass = "xcqajj@" + sdf1.format(new Date());
			}
		}
		return SUCCESS;
	}

	/**
	 * Function Name: savePass Function Description：保存新密码 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public void savePass() throws IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			CarEquipmentState b = new CarEquipmentState();
			b.setCreateTime(new Date());
			b.setCreateUserID(this.getLoginUserId());
			b.setMonth(sdf.format(new Date()));
			b.setPassword(newpass);
			cardoneinfoService.saveEquipmentState(b);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
	}

	/**
	 * Function Name: viewVideo Function Description：查看远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public String viewVideo() {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		map.put("month", sdf.format(new Date()));
		CarEquipmentState a = cardoneinfoService.getEquipmentState(map);
		oldpass = a.getPassword();
		return SUCCESS;
	}

	/**
	 * 储存摄像头设备
	 */
	public void saveDevList() throws IOException {
		try {
			cardoneinfoService.deleteEquip(null);
			JSONArray array = JSONArray.fromObject(str);
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = (JSONObject) array.get(i);
				CarEquipment carEquipment = new CarEquipment();
				carEquipment.setCreateUserID(this.getLoginUserId());
				carEquipment.setDelFlag(0);
				carEquipment.setDeptId(this.getLoginUserDepartmentId());
				carEquipment.setPuid(json.getString("channel"));
				carEquipment.setDetailName(json.getString("name"));
				cardoneinfoService.saveEquipment(carEquipment);
			}
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
	}

	/**
	 * Function Name: carEquipmentList Function Description：获取远程视频列表 Writer：陆婷
	 * CreateTime：2013-12-10 修改：根据企业视频名称获取视频列表 by 陆婷 2013-12-18
	 */
	@SuppressWarnings("rawtypes")
	public String carEquipmentList() {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		map.put("month", sdf.format(new Date()));
		CarEquipmentState a = cardoneinfoService.getEquipmentState(map);
		oldpass = a.getPassword();
		return SUCCESS;
	}

	/**
	 * 获取视频列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carEquipmentLists() throws IOException {
		Map map = new HashMap();
		if (carEquipment != null && carEquipment.getDetailName() != null
				&& !"".equals(carEquipment.getDetailName())) {
			String detailNames =  new String(carEquipment.getDetailName().getBytes("ISO-8859-1"), "UTF-8");
			map.put("detailName", "%" + detailNames.trim() + "%");
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if (deptCode.startsWith("002009")) {
			CompanyBackUp company = companyService.getCompanyByLoginId(this
					.getLoginUser().getLoginId());
			if (company != null && company.getCompanyname() != null
					&& !"".equals(company.getCompanyname())) {
				map.put("companyid", company.getCompanyId());
				equipmentList = cardoneinfoService.getEquipmentListForShowname(map);
			}
		} else {
			equipmentList = cardoneinfoService.getEquipmentListForShowname(map);
		}
		
		Tree tree = new Tree();
		tree.setId("0");
		tree.setText("企业");
		tree.setState("opened");
		List<Tree> trees = new ArrayList<Tree>();
		tree.setChildren(trees);

		Tree tree2 = new Tree();
		tree2.setId("1");
		tree2.setText("危化企业");
		trees.add(tree2);
		List<Tree> trees2 = new ArrayList<Tree>();
		tree2.setChildren(trees2);
		
		Tree tree3 = new Tree();
		tree3.setId("1");
		tree3.setText("涉粉企业");
		trees.add(tree3);
		List<Tree> trees3 = new ArrayList<Tree>();
		tree3.setChildren(trees3);
		
		int i = 3;
		for(CarEquipment carEquipment : equipmentList){
			String type = carEquipment.getQylx();
			String companyName = carEquipment.getCompanyName();
			String localName = carEquipment.getLocalName();
			String channel = carEquipment.getPuid();
			if(StringUtils.equals(type, "0")){
				boolean repeat = false;
				if(tree2.getChildren() != null){
					for(Tree tree4 : tree2.getChildren()){
						if(StringUtils.equals(companyName, tree4.getText())){
							Tree tree5 = new Tree();
							tree5.setId(channel);
							tree5.setText(localName);
							tree4.getChildren().add(tree5);
							repeat = true;
							break;
						}
					}
				}
				
				if(!repeat){
					Tree tree4 = new Tree();
					tree4.setId(i+"");
					tree4.setText(companyName);
					tree2.getChildren().add(tree4);
					List<Tree> trees4 = new ArrayList<Tree>();
					tree4.setChildren(trees4);
					
					Tree tree5 = new Tree();
					tree5.setId(channel);
					tree5.setText(localName);
					tree4.getChildren().add(tree5);
				}
			}else{
				boolean repeat = false;
				for(Tree tree4 : tree3.getChildren()){
					if(StringUtils.equals(companyName, tree4.getText())){
						Tree tree5 = new Tree();
						tree5.setId(channel);
						tree5.setText(localName);
						tree4.getChildren().add(tree5);
						repeat = true;
						break;
					}
				}
				if(!repeat){
					Tree tree4 = new Tree();
					tree4.setId(i+"");
					tree4.setText(companyName);
					tree3.getChildren().add(tree4);
					List<Tree> trees4 = new ArrayList<Tree>();
					tree4.setChildren(trees4);
					
					Tree tree5 = new Tree();
					tree5.setId(channel);
					tree5.setText(localName);
					tree4.getChildren().add(tree5);
				}
			}
		}
		JSONArray json = JSONArray.fromObject(tree);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Charset", "utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().println(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function Name: carEquipmentVvideo Function Description：查询远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	public String carEquipmentVvideo() {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		map.put("month", sdf.format(new Date()));
		CarEquipmentState a = cardoneinfoService.getEquipmentState(map);
		oldpass = a.getPassword();
		return SUCCESS;
	}

	private String guid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<CarEquipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<CarEquipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public CarEquipment getCarEquipment() {
		return carEquipment;
	}

	public void setCarEquipment(CarEquipment carEquipment) {
		this.carEquipment = carEquipment;
	}
}
