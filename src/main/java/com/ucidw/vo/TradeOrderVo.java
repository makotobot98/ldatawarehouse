package com.ucidw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrderVo {
    private String dt;                      // 日期
    private String areatype;                // 区域范围：区域类型（全国、大区、城市）
    private String regionname;              // 区域名称
    private String cityname;                // 城市名称
    private String categorytype;            // 商品分类类型（一级、二级）
    private String category1;               // 商品一级分类名称
    private String category2;             // 商品二级分类名称
    private long totalcount;                // 订单数量
    private long totalProductnum;          // 商品数量
    private double totalmoney;              // 支付金额
}
