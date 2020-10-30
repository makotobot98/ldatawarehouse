package com.ucidw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdVo {
    private String dt;  //日期
    private String hour; //小时
    private double clickRate; //点击率
    private double buyRate; //点击购买率
    private String place;   //广告位置 首页=1，左侧=2，右侧=3，列表页=4
    private int productId;  //商品id
    private long cnt;       //点击数
    private int rank;       //排名
    private long exposure;  //曝光数
    private long buyCount;  //购买数

}