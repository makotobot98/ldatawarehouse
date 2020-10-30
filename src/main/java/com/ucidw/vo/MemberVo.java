package com.ucidw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
    private String dt;              //'日期',
    private int dayCount;           //'本日活跃会员数量',
    private int weekCount;         // '本周活跃会员数量',
    private int monthCount;        // '本月活跃会员数量',
    private String cnt;            //新增会员数
    private String addDate;         // '新增日期',
    private int retentionDay;      //'截止当前日期留存天数',
    private int retentionCount;    //  '留存数',
    private int newMidCount;       // '当日会员新增数',
    private String retentionRatio;  // '留存率',
}
