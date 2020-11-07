package com.ucidw.mapper;

import com.ucidw.model.User;
import com.ucidw.vo.AdVo;
import com.ucidw.vo.MemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdMapper {


    List<AdVo> countAdData();
    List<AdVo> countAdAction();
}