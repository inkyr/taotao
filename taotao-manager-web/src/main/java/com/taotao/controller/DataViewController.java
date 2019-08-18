package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DataViewController {

    @Autowired
    private DataViewService dataViewService;

    @RequestMapping("/showDataView")
    public String dataView(){
        //return "forward:test.html";

        return "data-view";
    }

    @RequestMapping("/findDataByYear")
    @ResponseBody
    public String showDataByYear(String year){
        Map<String, Integer[]> map = dataViewService.findDataByYear(Long.valueOf(year));
        String str = JsonUtils.objectToJson(map);
        System.out.println(str);
        return str;
    }
}
