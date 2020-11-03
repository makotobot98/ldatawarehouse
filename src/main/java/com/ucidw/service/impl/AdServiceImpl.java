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
        List<String> exposureList = new ArrayList<String>();
        //点击量
        List<String> cntList = new ArrayList<String>();
        //购买量
        List<String> buyList = new ArrayList<String>();


        List<AdVo> list = mapper.countAdData();
        for (AdVo me : list) {
            hourList.add(me.getHour());
            exposureList.add(me.getExposure());
            cntList.add(me.getCnt());
            buyList.add(me.getBuyCount());
        }
        return  ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse<Map<String, Object>> countAdAction() {
        Map<String,Object> map = new HashMap<String, Object>(); //json map object
        //exposure
        List<String> exposureList = new ArrayList<>();
        //click
        List<String> clickList = new ArrayList<>();
        //click and purchase
        List<String> purchaseList = new ArrayList<>();
        List<AdVo> ls = mapper.countAdAction();

        for (AdVo record : ls) {
            //record: (dt = 2020-07-21, hr = 1, cnt(for click) = 9800, exposure(for exposure) = 20000, buyCount(for purchase) = 2000)
            clickList.add(record.getCnt());
            exposureList.add(record.getExposure());
            purchaseList.add(record.getBuyCount());
        }

        map.put("exposureCountData", exposureList);
        map.put("clickCountData", clickList);
        map.put("purchaseCountData", purchaseList);

        return ServerResponse.createBySuccess(map);
    }
}
