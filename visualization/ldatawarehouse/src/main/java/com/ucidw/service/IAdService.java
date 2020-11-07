package com.ucidw.service;


import com.ucidw.common.ServerResponse;
import com.ucidw.vo.AdVo;

import java.util.List;
import java.util.Map;

public interface IAdService {

    /**
     * 统计某日的曝光数总数、点击总数、购买总数
     * @return
     */
    ServerResponse<List<AdVo>> countAdData();
    ServerResponse<Map<String, Object>> countAdAction();
}
