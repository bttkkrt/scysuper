package com.jshx.core.json;

import java.util.List;
/**
 * json分页对象
 * 
 * @author Chenjian
 *
 */
public class JsonPagination {

	private int total;

	private List<?> results;

	public JsonPagination(int totalCount, List<?> results){
		this.total=totalCount;
		this.results=results;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}
}
