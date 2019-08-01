package com.taotao.utils;

import java.io.Serializable;
import java.util.List;

/**
 * easyUIDataGrid对象返回值
 */
public class EasyUIResult implements Serializable {

	private Long total;
	
	private List<?> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
