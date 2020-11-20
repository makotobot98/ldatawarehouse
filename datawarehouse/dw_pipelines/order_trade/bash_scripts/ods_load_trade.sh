#!/bin/bash
source /etc/profile
if [ -n "$1" ] ;then
do_date=$1
else
do_date=`date -d "-1 day" +%F`
fi
# create directory
hdfs dfs -mkdir -p /user/data/trade.db/product_category/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/shops/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/shop_org/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/payments/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/orders/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/order_product/dt=$do_date
hdfs dfs -mkdir -p /user/data/trade.db/product_info/dt=$do_date
# load data with dataX
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/product_category.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/shops.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/shop_org.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/payments.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/orders.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/order_product.json
python $DATAX_HOME/bin/datax.py -p "-Ddo_date=$do_date"
/data/ldw/json/product_info.json
# load ods layer data
sql="
alter table ods.ods_trade_orders add partition(dt='$do_date');
alter table ods.ods_trade_order_product add partition(dt='$do_date');
alter table ods.ods_trade_product_info add partition(dt='$do_date');
alter table ods.ods_trade_product_category add partition(dt='$do_date');
alter table ods.ods_trade_shops add partition(dt='$do_date');
alter table ods.ods_trade_shop_admin_org add partition(dt='$do_date');
alter table ods.ods_trade_payments add partition(dt='$do_date');
"
hive -e "$sql"