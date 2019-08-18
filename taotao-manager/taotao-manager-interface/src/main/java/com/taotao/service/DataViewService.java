package com.taotao.service;

import java.util.Map;

public interface DataViewService {

    Map<String, Integer[]> findDataByYear(Long year);
}
