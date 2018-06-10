package com.jshx.vedioname.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.vedioname.entity.VedioName;
import com.jshx.vedioname.service.VedioNameService;

@SuppressWarnings("serial")
public class VedioNameAction extends BaseAction {

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private VedioName vedioName = new VedioName();

	/**
	 * 业务类
	 */
	@Autowired
	private VedioNameService vedioNameService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;

	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != vedioName) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != vedioName.getVedioname())
					&& (0 < vedioName.getVedioname().trim().length())) {
				paraMap.put("vedioname", "%" + vedioName.getVedioname().trim()
						+ "%");
			}

			if ((null != vedioName.getShowname())
					&& (0 < vedioName.getShowname().trim().length())) {
				paraMap.put("showname", "%" + vedioName.getShowname().trim()
						+ "%");
			}
			if ((null != vedioName.getCompanyname())
					&& (0 < vedioName.getCompanyname().trim().length())) {
				paraMap.put("companyname", "%"
						+ vedioName.getCompanyname().trim() + "%");
			}
			if ((null != vedioName.getQylx())
					&& (0 < vedioName.getQylx().trim().length())) {
				paraMap.put("qylx", vedioName.getQylx().trim());
			}

		}

		pagination = vedioNameService.findByPage(pagination, paraMap);

		convObjectToJson(pagination, null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception {
		if ((null != vedioName) && (null != vedioName.getId()))
			vedioName = vedioNameService.getById(vedioName.getId());

		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception {
		view();
		return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception {
		if ("add".equalsIgnoreCase(this.flag)) {
			vedioName.setDeptId(this.getLoginUserDepartmentId());
			vedioName.setDelFlag(0);
			vedioNameService.save(vedioName);
		} else {
			vedioNameService.update(vedioName);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception {
		try {
			vedioNameService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public VedioName getVedioName() {
		return this.vedioName;
	}

	public void setVedioName(VedioName vedioName) {
		this.vedioName = vedioName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
