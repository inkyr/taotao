package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemDesc implements Serializable{

    private Long itemId;

    private Date created;

    private Date updated;

    private String desc;


	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "TbItemDesc{" +
				"itemId=" + itemId +
				", created=" + created +
				", updated=" + updated +
				", desc='" + desc + '\'' +
				'}';
	}
}