
package com.jshx.module.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name = "Form_InCategory")
public class FormInCategory extends BaseModel implements java.io.Serializable
{
    //private Integer sysID;

    private String tableID;

    private String categoryNum;

//    @GenericGenerator(name = "generator", strategy="native")
//    @Id
//    @GeneratedValue(generator = "generator")
//    @Column(name = "SysID", nullable = false)
//    public Integer getSysID()
//    {
//        return sysID;
//    }
//
//    public void setSysID(Integer sysID)
//    {
//        this.sysID = sysID;
//    }

    @Column(name = "TableID")
    public String getTableID()
    {
        return tableID;
    }

    public void setTableID(String tableID)
    {
        this.tableID = tableID;
    }

    @Column(name = "CategoryNum")
    public String getCategoryNum()
    {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum)
    {
        this.categoryNum = categoryNum;
    }
}
