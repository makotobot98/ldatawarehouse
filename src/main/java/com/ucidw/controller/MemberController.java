package com.ucidw.controller;

import com.ucidw.common.Const;
import com.ucidw.common.ServerResponse;
import com.ucidw.model.User;
import com.ucidw.service.IMemberService;
import com.ucidw.service.IUserService;
import com.ucidw.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;


    /**
     * 1.本周新增会员数
     * @return
     */
    @RequestMapping(value = "memberGrowth.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Map<String,Object>> countGrowMember() {
        ServerResponse<Map<String,Object>> response = iMemberService.countMemeberGrowth();
        return response;
    }

    /**
     * 2.本周活跃会员数
     */
    @RequestMapping(value = "memberActive.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Map<String,Object>> countActiveMember() {
        ServerResponse<Map<String,Object>> response = iMemberService.countMemeberActive();
        return response;
    }
}
