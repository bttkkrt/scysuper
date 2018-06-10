package com.jshx.module.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

@Entity
@Table(name = "PORTAL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Portal extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String title;

	private String url;
	
	private Integer isPublic;
	
	private List<PortalRight> rightList;

	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "URL", length = 2000)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the rightList
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="portal", targetEntity=PortalRight.class)
	public List<PortalRight> getRightList() {
		return rightList;
	}

	/**
	 * @param rightList the rightList to set
	 */
	public void setRightList(List<PortalRight> rightList) {
		this.rightList = rightList;
	}

	/**
	 * @return the isPublic
	 */
	@Column(name="IS_PUBLIC", length=1)
	public Integer getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
}
