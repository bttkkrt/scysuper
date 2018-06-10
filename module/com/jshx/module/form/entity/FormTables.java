package com.jshx.module.form.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "Tables")
public class FormTables {
	
	private List<FormTable> tables;

	public FormTables() {
		tables = new ArrayList<FormTable>();
	}

	@XmlElement(name = "Table")
	public List<FormTable> getTables() {
		if(tables==null)
			tables = new ArrayList<FormTable>();
		return tables;
	}

	public void setTables(List<FormTable> tables) {
		this.tables = tables;
	}
	
	public void add(FormTable table){
		tables.add(table);
	}
}