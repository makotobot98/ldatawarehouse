package com.ucidw.service.impl;


import com.ucidw.common.ServerResponse;
import com.ucidw.mapper.TradeMapper;
import com.ucidw.service.IAdService;
import com.ucidw.service.ITradeService;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.TradeOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("iTradeService")
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private TradeMapper mapper;

    @Override
    public ServerResponse<List<TradeOrderVo>> countTradeData() {
        return ServerResponse.createBySuccess(mapper.tradeData());
    }
}
