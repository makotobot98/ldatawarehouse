package com.ucidw.service.impl;


import com.ucidw.common.ServerResponse;
import com.ucidw.mapper.TradeMapper;
import com.ucidw.service.ITradeService;
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
    public ServerResponse<Map<String, Object>> countTradeData() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> cityNames = new ArrayList<String>();
        List<String> growDataList = new ArrayList<>();
        List<TradeOrderVo> list = mapper.tradeData();
        for (TradeOrderVo me : list) {
            if (me.getCityname().equals("")) continue;
            cityNames.add(me.getCityname());
            growDataList.add(me.getTotalcount());
        }
        map.put("cityNames",cityNames);
        map.put("seriesData",growDataList);
        return ServerResponse.createBySuccess(map);
    }

    /*@Override
    public ServerResponse<List<TradeOrderVo>> countTradeData() {
        return ServerResponse.createBySuccess(mapper.tradeData());
    }*/
}
