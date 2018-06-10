package com.jshx.module.admin.service.impl;

import java.util.Comparator;

import com.jshx.module.admin.entity.CodeValue;

class SortBySQ implements Comparator {
	public int compare(Object obj1, Object obj2) {
		CodeValue code1 = (CodeValue) obj1;
		CodeValue code2 = (CodeValue) obj2;
		//if (Integer.parseInt(code1.getItemValue()) > Integer.parseInt(code2.getItemValue()))
		if(code1.getSortSQ() > code2.getSortSQ())
			return 1;
		else
			return 0;
	}
}
