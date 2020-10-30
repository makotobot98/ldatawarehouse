package com.ucidw.mapper;

import com.ucidw.vo.MemberVo;

import java.util.List;

public interface MemberMapper {
    List<MemberVo> selectMembers();

    List<MemberVo> selectActiveMembers();
}
