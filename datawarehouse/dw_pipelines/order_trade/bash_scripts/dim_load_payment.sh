#！/bin/bash
source /etc/profile
if [ -n "$1" ]
then
do_date=$1
else
do_date=`date -d "-1 day" +%F`
fi
sql="
insert overwrite table dim.dim_trade_payment
partition(dt='$do_date')
select id, payName
from ods.ods_trade_payments
where dt='$do_date';
"
hive -e "$sql"