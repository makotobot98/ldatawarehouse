insert overwrite table dim.dim_trade_payment
partition(dt='$do_date')
select id, payName
from ods.ods_trade_payments
where dt='$do_date';