/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/**  
 * @author   Huairu.Li
 * @version 创建时间：2011-1-24 下午04:13:57  
 * 一维代码实体类
 */
@Entity
@Table(name = "FORM_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormCategory extends BaseModel{

	private static final long serialVersionUID = 1L;

    private String categoryNum;

    private String categoryName;

    private Integer sortSQ;

    private FormCategory parentCategory;

    private List<FormCategory> childCategory;
    
	private Integer hasChild; 

    @Column(name = "CATEGORY_NUM", length = 20, nullable = false)
    public String getCategoryNum()
    {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum)
    {
        this.categoryNum = categoryNum;
    }

    @Column(name = "CATEGORY_NAME", length = 20, nullable = false)
    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    @Column(name = "SORT_SQ", length = 3, nullable = false)
    public Integer getSortSQ()
    {
        return sortSQ;
    }

    public void setSortSQ(Integer sortSQ)
    {
        this.sortSQ = sortSQ;
    }

	@Transient
    public FormCategory getParentCategory()
    {
        return parentCategory;
    }

    public void setParentCategory(FormCategory parentCategory)
    {
        this.parentCategory = parentCategory;
    }

    @Transient
    public List<FormCategory> getChildCategory()
    {
        return childCategory;
    }

    public void setChildCategory(List<FormCategory> childCategory)
    {
        this.childCategory = childCategory;
    }

    @Transient
	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
}
