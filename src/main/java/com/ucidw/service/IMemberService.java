package com.ucidw.service;


import com.ucidw.common.ServerResponse;

import java.util.Map;

public interface IMemberService {

    /**
     * 统计最后7天增长会员
     * @return
     */
    ServerResponse<Map<String, Object>> countMemberGrowth();

    /**
     * 统计7天活跃会员
     * @return
     */
    ServerResponse<Map<String, Object>> countMemberActive();


    /**
     * 统计7天留存会员
     * @return
     */
    ServerResponse<Map<String, Object>> countMemberRetention();
}
