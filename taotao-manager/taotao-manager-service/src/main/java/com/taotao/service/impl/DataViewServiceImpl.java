package com.taotao.service.impl;

import com.taotao.common.pojo.DataViewResult;
import com.taotao.mapper.OrderItemMapper;
import com.taotao.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataViewServiceImpl implements DataViewService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Map<String, Integer[]> findDataByYear(Long year) {
        List<DataViewResult> dataByYear = orderItemMapper.getDataByYear(year);
        Integer[] categories = new Integer[12];
        Integer[] data = new Integer[12];
        int count = 0;
        for (DataViewResult dataViewResult : dataByYear) {
            categories[count] = dataViewResult.getMonth();
            data[count] = dataViewResult.getTotalMoney();
            count++;
        }
        Map<String, Integer[]> map = new HashMap();
        map.put("categories", categories);
        map.put("data", data);
        return map;
    }
}
