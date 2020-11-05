package com.ucidw.controller;

import com.ucidw.common.ServerResponse;
import com.ucidw.service.IAdService;
import com.ucidw.service.ITradeService;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.TradeOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dwh/trade")
public class TradeController {

    @Autowired
    private ITradeService iTradeService;

    /**
     * 一级商品和二级商品   订单总数    购买商品总数    支付金额总数
     */
    @RequestMapping(value = "trade.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Map<String, Object>> countTradeData() {
        ServerResponse<Map<String, Object>> response = iTradeService.countTradeData();
        return response;
    }
}
