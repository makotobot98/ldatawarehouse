package com.ucidw.service.impl;


import com.ucidw.common.ServerResponse;
import com.ucidw.mapper.AdMapper;
import com.ucidw.mapper.MemberMapper;
import com.ucidw.service.IAdService;
import com.ucidw.service.IMemberService;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("iAdService")
public class AdServiceImpl implements IAdService {

    @Autowired
    private AdMapper mapper;

    @Override
    public ServerResponse<List<AdVo>> countAdData() {
        //小时
        List<String> hourList = new ArrayList<String>();
        //曝光量
        List<Long> exposureList = new ArrayList<Long>();
        //点击量
        List<Long> cntList = new ArrayList<Long>();
        //购买量
        List<Long> buyList = new ArrayList<Long>();


        List<AdVo> list = mapper.countAdData();
        for (AdVo me : list) {
            hourList.add(me.getHour());
            exposureList.add(me.getExposure());
            cntList.add(me.getCnt());
            buyList.add(me.getBuyCount());
        }
        return  ServerResponse.createBySuccess(list);
    }
}
