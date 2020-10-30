package com.ucidw.service;


import com.ucidw.common.ServerResponse;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.TradeOrderVo;

import java.util.List;

public interface ITradeService {

    /**
     * 统计一级商品和二级商品   订单总数    购买商品总数    支付金额总数
     * @return
     */
    ServerResponse<List<TradeOrderVo>> countTradeData();
}
