package com.ucidw.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ucidw.common.Const;
import com.ucidw.common.ServerResponse;
import com.ucidw.mapper.MemberMapper;
import com.ucidw.mapper.UserMapper;
import com.ucidw.model.User;
import com.ucidw.service.IMemberService;
import com.ucidw.service.IUserService;
import com.ucidw.util.MD5Util;
import com.ucidw.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("iMemberService")
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper mapper;

    public ServerResponse<Map<String, Object>> countMemberGrowth() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> dateList = new ArrayList<String>();
        List<String> growDataList = new ArrayList<String>();
        List<MemberVo> list = mapper.selectMembers();
        for (MemberVo me : list) {
            dateList.add(me.getDt());
            growDataList.add(me.getCnt());
        }
        map.put("xData",dateList);
        map.put("seriesData",growDataList);
        return  ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse<Map<String, Object>> countMemberActive() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> dateList = new ArrayList<String>();
        List<Integer> activeDataList = new ArrayList<Integer>();
        List<MemberVo> list = mapper.selectActiveMembers();
        for (MemberVo me : list) {
            dateList.add(me.getDt());
            activeDataList.add(me.getDayCount());
        }
        map.put("xData",dateList);
        map.put("seriesData",activeDataList);
        return  ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse<Map<String, Object>> countMemberRetention() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> dateList = new ArrayList<String>();
        List<String> growDataList = new ArrayList<String>();
        List<String> newUserCount = new ArrayList<String>();
        List<MemberVo> list = mapper.selectRetentiveMembers();
        for (MemberVo me : list) {
            dateList.add(me.getDt());
            growDataList.add(me.getRetentionCount());
            newUserCount.add(me.getNewMidCount());
        }
        map.put("xData",dateList);
        map.put("seriesData",growDataList);
        map.put("newUserCount",newUserCount);
        return  ServerResponse.createBySuccess(map);
    }
}
