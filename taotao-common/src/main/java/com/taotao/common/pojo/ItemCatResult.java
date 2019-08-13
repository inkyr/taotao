package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

public class ItemCatResult implements Serializable {

    private List<ItemCat> data;

    public List<ItemCat> getData() {
        return data;
    }

    public void setData(List<ItemCat> data) {
        this.data = data;
    }
}
