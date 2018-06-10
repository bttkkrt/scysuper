package com.jshx.module.form.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.jshx.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name = "Form_TableInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Table")
public class FormTable extends BaseModel implements java.io.Serializable {

	//private Integer tableId;

	private String tableName; // 显示名称

	private String physicalName;// 存储表名

	private String projectName; // 项目名称

	private String generatePath;// 产生路径

	private String fileprexName;// 生成文件前缀

	private Integer sortSQ; // 排序

	private Integer pageType; // 页面类型 个性化0 通用1

	private Integer displayInMenu; // 在菜单显示

	private String srcPackage;

	private List<FormField> fieldList;
	
	private String tableWidth;  //编辑或详情页面表单的宽度
	
    private String labelWidth;  //编辑或详情页面属性标签的宽度
	
	private String textWidth;   //编辑或详情页面属性值或输入框的宽度
	
	private String phyNameInRule;// 按驼峰式规则显示的表名

	public FormTable() {
	}

	public FormTable(String tableId) {
		super.setId(tableId);
	}

	@Column(name = "displayInMenu")
	@XmlElement(name = "DisplayInMenu")
	public Integer getDisplayInMenu() {
		return displayInMenu;
	}

	public void setDisplayInMenu(Integer displayInMenu) {
		this.displayInMenu = displayInMenu;
	}

	@Column(name = "fileprexName")
	@XmlElement(name = "FilePrexName")
	public String getFileprexName() {
		return fileprexName;
	}

	public void setFileprexName(String fileprexName) {
		this.fileprexName = fileprexName;
	}

	@Column(name = "generatePath")
	@XmlElement(name = "GeneratePath")
	public String getGeneratePath() {
		return generatePath;
	}

	public void setGeneratePath(String generatePath) {
		this.generatePath = generatePath;
	}

	

	@Column(name = "pageType")
	@XmlElement(name = "PageType")
	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	@Column(name = "physicalName")
	@XmlElement(name = "PhysicalName")
	public String getPhysicalName() {
		return physicalName;
	}

	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName==null?null:physicalName.toUpperCase();
	}

	@Column(name = "projectName")
	@XmlElement(name = "ProjectName")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "sortSQ")
	@XmlElement(name = "SortSQ")
	public Integer getSortSQ() {
		return sortSQ;
	}

	public void setSortSQ(Integer sortSQ) {
		this.sortSQ = sortSQ;
	}

	@Column(name = "tableName")
	@XmlElement(name = "TableName")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@OneToMany(mappedBy = "table", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, targetEntity = FormField.class)
	@XmlElement(name = "Field")
	public List<FormField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FormField> fieldList) {
		this.fieldList = fieldList;
	}

	@Column(name = "SRC_PACKAGE")
	@XmlElement(name = "SRC_PACKAGE")
	public String getSrcPackage() {
		return srcPackage;
	}

	public void setSrcPackage(String srcPackage) {
		this.srcPackage = srcPackage;
	}
	
	public void add(FormField field){
		fieldList.add(field);
	}

	@Transient
	public String getPhyNameInRule() {
		String[] pNameStrings = this.getPhysicalName().split("_");
		String tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
		}
		return tablePhyName;
	}

	public void setPhyNameInRule(String phyNameInRule) {

		this.phyNameInRule = phyNameInRule;
	}

	@Column(name="TABLE_WIDTH", length = 5, nullable = true)
	public String getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	@Column(name="LABEL_WIDTH", length = 5, nullable = true)
	public String getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(String labelWidth) {
		this.labelWidth = labelWidth;
	}

	@Column(name="TEXT_WIDTH", length = 5, nullable = true)
	public String getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(String textWidth) {
		this.textWidth = textWidth;
	}
}
