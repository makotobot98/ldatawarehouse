package com.ucidw.controller;

import com.ucidw.common.ServerResponse;
import com.ucidw.service.IAdService;
import com.ucidw.service.IMemberService;
import com.ucidw.vo.AdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private IAdService iAdService;


    /**
     * 1.某日的曝光数总数、点击总数、购买总数
     * @return
     */
    @RequestMapping(value = "adCount.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<AdVo>> countGrowMember() {
        ServerResponse<List<AdVo>> response = iAdService.countAdData();
        return response;
    }
}
