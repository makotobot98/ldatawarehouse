package com.ucidw.service.impl;


import com.ucidw.common.ServerResponse;
import com.ucidw.mapper.TradeMapper;
import com.ucidw.service.IAdService;
import com.ucidw.service.ITradeService;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.MemberVo;
import com.ucidw.vo.TradeOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("iTradeService")
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private TradeMapper mapper;

    @Override
    public ServerResponse<List<TradeOrderVo>> countTradeData() {
        String date = "";
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> cityNames = new ArrayList<String>();
        List<String> growDataList = new ArrayList<String>();
        List<TradeOrderVo> list = mapper.tradeData();
        for (TradeOrderVo me : list) {
            if (me.getAreatype() != "City") continue;
            date = me.getDt();
            cityNames.add(me.getCityname());
            growDataList.add(me.getTotalcount());
        }
        map.put("date", date);
        map.put("cityNames",cityNames);
        map.put("seriesData",growDataList);
        return ServerResponse.createBySuccess(mapper.tradeData());
    }
}
