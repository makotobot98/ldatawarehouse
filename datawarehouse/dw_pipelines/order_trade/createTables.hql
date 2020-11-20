-- creating DataBases;
CREATE DATABASE ODS;
CREATE DATABASE DIM;
CREATE DATABASE DWD;
CREATE DATABASE DWS;
CREATE DATABASE ADS;
-- ods tables
DROP TABLE IF EXISTS `ods.ods_trade_orders`;
CREATE EXTERNAL TABLE `ods.ods_trade_orders`(
    `orderid` int,
    `orderno` string,
    `userid` bigint,
    `status` tinyint,
    `productmoney` decimal(10,0),
    `totalmoney` decimal(10,0),
    `paymethod` tinyint,
    `ispay` tinyint,
    `areaid` int,
    `tradesrc` tinyint,
    `tradetype` int,
    `isrefund` tinyint,
    `dataflag` tinyint,
    `createtime` string,
    `paytime` string,
    `modifiedtime` string
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/orders/';

DROP TABLE IF EXISTS `ods.ods_trade_order_product`;
CREATE EXTERNAL TABLE `ods.ods_trade_order_product`(
    `id` string,
    `orderid` decimal(10,2),
    `productid` string,
    `productnum` string,
    `productprice` string,
    `money` string,
    `extra` string,
    `createtime` string
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/order_product/';

DROP TABLE IF EXISTS `ods.ods_trade_product_info`;
CREATE EXTERNAL TABLE `ods.ods_trade_product_info`(
    `productid` bigint,
    `productname` string,
    `price` decimal(10,0),
    `issale` tinyint,
    `status` tinyint,
    `categoryid` string,
    `createtime` string,
    `modifytime` string
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/product_info/';

DROP TABLE IF EXISTS `ods.ods_trade_product_category`;
CREATE EXTERNAL TABLE `ods.ods_trade_product_category`(
    `catid` int,
    `parentid` int,
    `catname` string,
    `isshow` tinyint,
    `sortnum` int,
    `isdel` tinyint,
    `createtime` string,
    `level` tinyint
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/product_category';
DROP TABLE IF EXISTS `ods.ods_trade_shops`;
CREATE EXTERNAL TABLE `ods.ods_trade_shops`(
    `shopid` int,
    `userid` int,
    `areaid` int,
    `shopname` string,
    `shoplevel` tinyint,
    `status` tinyint,
    `createtime` string,
    `modifytime` string
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/shops';

DROP TABLE IF EXISTS `ods.ods_trade_shop_admin_org`;
CREATE EXTERNAL TABLE `ods.ods_trade_shop_admin_org`(
    `id` int,
    `parentid` int,
    `orgname` string,
    `orglevel` tinyint,
    `isdelete` tinyint,
    `createtime` string,
    `updatetime` string,
    `isshow` tinyint,
     `orgType` tinyint
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/shop_org/';

DROP TABLE IF EXISTS `ods.ods_trade_payments`;
CREATE EXTERNAL TABLE `ods.ods_trade_payments`(
    `id` string,
    `paymethod` string,
    `payname` string,
    `description` string,
    `payorder` int,
    `online` tinyint
)
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ','
location '/user/data/trade.db/payments/';

-- create dim table
DROP TABLE IF EXISTS dim.dim_trade_product_cat;
create table if not exists dim.dim_trade_product_cat(
    firstId int, -- level 1 catogory id
    firstName string, -- level 1 item name
    secondId int, -- level 2 catogory id
    secondName string, -- level 2 item name
    thirdId int, -- level 2 catogory id
    thirdName string -- level 3 item name
)
partitioned by (dt string)
STORED AS PARQUET;

drop table if exists dim.dim_trade_shops_org;
create table dim.dim_trade_shops_org(
    shopid int,
    shopName string,
    cityId int,
    cityName string ,
    regionId int ,
    regionName string
)
partitioned by (dt string)
STORED AS PARQUET;

drop table if exists dim.dim_trade_payment;
create table if not exists dim.dim_trade_payment(
    paymentId string, -- payment method id
    paymentName string -- payment method name
)
partitioned by (dt string)
STORED AS PARQUET;

drop table if exists dim.dim_trade_product_info;
create table dim.dim_trade_product_info(
    `productId` bigint,
    `productName` string,
    `shopId` string,
    `price` decimal,
    `isSale` tinyint,
    `status` tinyint,
    `categoryId` string,
    `createTime` string,
    `modifyTime` string,
    `start_dt` string,
    `end_dt` string
) 
STORED AS PARQUET;

-- dwd tables
-- order fact table, zipper table
DROP TABLE IF EXISTS dwd.dwd_trade_orders;
create table dwd.dwd_trade_orders(
    `orderId` int,
    `orderNo` string,
    `userId` bigint,
    `status` tinyint,
    `productMoney` decimal,
    `totalMoney` decimal,
    `payMethod` tinyint,
    `isPay` tinyint,
    `areaId` int,
    `tradeSrc` tinyint,
    `tradeType` int,
    `isRefund` tinyint,
    `dataFlag` tinyint,
    `createTime` string,
    `payTime` string,
    `modifiedTime` string,
    `start_date` string,
    `end_date` string
) 
partitioned by (dt string)
STORED AS PARQUET;

-- dws table
DROP TABLE IF EXISTS dws.dws_trade_orders;
create table if not exists dws.dws_trade_orders(
    orderid string, -- order id
    cat_3rd_id string, -- item level 3 catagory id
    shopid string, -- shop id
    paymethod tinyint, -- payment method
    productsnum bigint, -- item count
    paymoney double, -- cost
    paytime string -- payment time
)
partitioned by (dt string)
STORED AS PARQUET;

-- fat order table
DROP TABLE IF EXISTS dws.dws_trade_orders_w;
create table if not exists dws.dws_trade_orders_w(
    orderid string, 
    cat_3rd_id string, 
    thirdname string, 
    secondname string, 
    firstname string, 
    shopid string, 
    shopname string, 
    regionname string, 
    cityname string, 
    paymethod tinyint, 
    productsnum bigint, 
    paymoney double, 
    paytime string 
)
partitioned by (dt string)
STORED AS PARQUET;

-- ads tables
DROP TABLE IF EXISTS ads.ads_trade_order_analysis;
create table if not exists ads.ads_trade_order_analysis(
    areatype string, -- reaa type:（country, state,city）
    regionname string, -- area name
    cityname string, -- city name
    categorytype string, -- item catagory type(level 1, level 2）
    category1 string, -- item level 1 catagory name
    category2 string, -- item level 2 catagory name
    totalcount bigint, -- order count
    total_productnum bigint, -- item count
    totalmoney double -- payment
)
partitioned by (dt string)
row format delimited fields terminated by ',';