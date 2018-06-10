package com.jshx.module.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.jshx.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name = "Form_TableField")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Field")
public class FormField extends BaseModel implements java.io.Serializable {

	//private Integer fieldId;

	private FormTable table;

	private String fieldName;// 字段名称

	private String fieldType;// 字段类型

	private Integer fieldLength;// 字段长度

	private Integer decimalLength;// 小数位长度

	private String fieldDisplayName;// 字段显示名

	private Integer dispInGrid;// 在表格中显示？

	private Integer isForeignKey;// 是否外键

	private Integer sortSQ;// 序号

	private String fieldDefaultValue;// 默认值

	private Integer allowNull;// 允许NULL

	private Integer isQueryCondition;// 是否查询条件

	private Integer mustFill;// 是否必填项

	private String fieldDisplayType;// 字段表现形式：Textbox/Radio。。。

	private String dataSource;// 数据源（代码或者取值SQL）

	private String dataSourceType;// 数据源类型

	private String dataSource_CodeName;// 代码名称/字段名称

	private String validCheckFormula;// 计算公式/有效性计算公式

	private String sortDirection;// 排序方向

	private Integer isSortField;// 是否排序字段

	private Integer uniqueField;// 是否唯一字段

	private Integer fieldIndex;// 是否标题字段

	private Integer controlWidth;// 控件宽度

	private String defaultValue;// 默认sql取值

	private String valueUrl;// 取值地址

	private Integer gridWidth;// 表格宽度

	private Integer gridMultiRows;// 是否允许折行
	
	private Integer dispInDetail;  //查看详细中是否显示
	
	private String columnWidth;  //列表页面显示的宽度，可为数字或百分比
	
	@Column(name = "allowNull")
	@XmlElement(name = "AllowNull")
	public Integer getAllowNull() {
		return allowNull;
	}

	public void setAllowNull(Integer allowNull) {
		this.allowNull = allowNull;
	}



	@Column(name = "controlWidth")
	@XmlElement(name = "ControlWidth")
	public Integer getControlWidth() {
		return controlWidth;
	}

	public void setControlWidth(Integer controlWidth) {
		this.controlWidth = controlWidth;
	}

	@Column(name = "dataSource")
	@XmlElement(name = "DataSource")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Column(name = "dataSource_CodeName")
	@XmlElement(name = "DataSource_CodeName")
	public String getDataSource_CodeName() {
		return dataSource_CodeName;
	}

	public void setDataSource_CodeName(String dataSource_CodeName) {
		this.dataSource_CodeName = dataSource_CodeName;
	}

	@Column(name = "dataSourceType")
	@XmlElement(name = "DataSourceType")
	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	@Column(name = "decimalLength")
	@XmlElement(name = "DecimalLength")
	public Integer getDecimalLength() {
		return decimalLength;
	}

	public void setDecimalLength(Integer decimalLength) {
		this.decimalLength = decimalLength;
	}

	@Column(name = "defaultValue")
	@XmlElement(name = "DefaultValue")
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "dispInGrid")
	@XmlElement(name = "DisplayInGrid")
	public Integer getDispInGrid() {
		return dispInGrid;
	}

	public void setDispInGrid(Integer dispInGrid) {
		this.dispInGrid = dispInGrid;
	}

	@Column(name = "fieldDefaultValue")
	@XmlElement(name = "FieldDefaultValue")
	public String getFieldDefaultValue() {
		return fieldDefaultValue;
	}

	public void setFieldDefaultValue(String fieldDefaultValue) {
		this.fieldDefaultValue = fieldDefaultValue;
	}

	@Column(name = "fieldDisplayName")
	@XmlElement(name = "FieldDisplayName")
	public String getFieldDisplayName() {
		return fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		this.fieldDisplayName = fieldDisplayName;
	}

	@Column(name = "fieldDisplayType")
	@XmlElement(name = "FieldDisplayType")
	public String getFieldDisplayType() {
		return fieldDisplayType;
	}

	public void setFieldDisplayType(String fieldDisplayType) {
		this.fieldDisplayType = fieldDisplayType;
	}

	

	@Column(name = "fieldIndex")
	@XmlElement(name = "FieldIndex")
	public Integer getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(Integer fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	@Column(name = "fieldLength")
	@XmlElement(name = "FieldLength")
	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	@Column(name = "fieldName")
	@XmlElement(name = "FieldName")
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName==null?null:fieldName.toUpperCase();
	}

	@Column(name = "fieldType")
	@XmlElement(name = "FieldType")
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "gridMultiRows")
	@XmlElement(name = "GridMultiRows")
	public Integer getGridMultiRows() {
		return gridMultiRows;
	}

	public void setGridMultiRows(Integer gridMultiRows) {
		this.gridMultiRows = gridMultiRows;
	}

	@Column(name = "gridWidth")
	@XmlElement(name = "GridWidth")
	public Integer getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(Integer gridWidth) {
		this.gridWidth = gridWidth;
	}

	@Column(name = "isForeignKey")
	@XmlElement(name = "IsForeignKey")
	public Integer getIsForeignKey() {
		return isForeignKey;
	}

	public void setIsForeignKey(Integer isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	@Column(name = "isQueryCondition")
	@XmlElement(name = "IsQueryCondition")
	public Integer getIsQueryCondition() {
		return isQueryCondition;
	}

	public void setIsQueryCondition(Integer isQueryCondition) {
		this.isQueryCondition = isQueryCondition;
	}

	@Column(name = "isSortField")
	@XmlElement(name = "IsSortField")
	public Integer getIsSortField() {
		return isSortField;
	}

	public void setIsSortField(Integer isSortField) {
		this.isSortField = isSortField;
	}

	@Column(name = "mustFill")
	@XmlElement(name = "MustFill")
	public Integer getMustFill() {
		return mustFill;
	}

	public void setMustFill(Integer mustFill) {
		this.mustFill = mustFill;
	}

	@Column(name = "sortDirection")
	@XmlElement(name = "SortDirection")
	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	@Column(name = "sortSQ")
	@XmlElement(name = "SortSQ")
	public Integer getSortSQ() {
		return sortSQ;
	}

	public void setSortSQ(Integer sortSQ) {
		this.sortSQ = sortSQ;
	}

	@Column(name = "uniqueField")
	@XmlElement(name = "UniqueField")
	public Integer getUniqueField() {
		return uniqueField;
	}

	public void setUniqueField(Integer uniqueField) {
		this.uniqueField = uniqueField;
	}

	@Column(name = "validCheckFormula")
	@XmlElement(name = "ValidCheckFormula")
	public String getValidCheckFormula() {
		return validCheckFormula;
	}

	public void setValidCheckFormula(String validCheckFormula) {
		this.validCheckFormula = validCheckFormula;
	}

	@Column(name = "valueUrl")
	@XmlElement(name = "ValueUrl")
	public String getValueUrl() {
		return valueUrl;
	}

	public void setValueUrl(String valueUrl) {
		this.valueUrl = valueUrl;
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity=FormTable.class)
	@JoinColumn(name = "tableId", nullable = false)
	@XmlTransient
	public FormTable getTable() {
		return table;
	}

	public void setTable(FormTable table) {
		this.table = table;
	}
	@Column(name = "dispInDetail")
	@XmlElement(name = "DisplayInDetail")
	public Integer getDispInDetail() {
		return dispInDetail;
	}

	public void setDispInDetail(Integer dispInDetail) {
		this.dispInDetail = dispInDetail;
	}

	@Column(name = "COLUMN_WIDTH", length = 30, nullable = true)
	public String getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}
}
