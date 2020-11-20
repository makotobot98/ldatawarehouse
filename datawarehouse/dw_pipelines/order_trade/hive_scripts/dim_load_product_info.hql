insert overwrite table dim.dim_trade_product_info
	select productId,
	productName,
	shopId,
	price,
	isSale,
	status,	
	categoryId,
	createTime,
	modifyTime,
	case when modifyTime is not null
		then substr(modifyTime,0,10)
		else substr(createTime,0,10)
	end as start_dt,
	'9999-12-31' as end_dt
from ods.ods_trade_product_info
where dt='$do_date'
union all
select dim.productId,
	dim.productName,
	dim.shopId,
	dim.price,
	dim.isSale,
	dim.status,
	dim.categoryId,
	dim.createTime,
	dim.modifyTime,
	dim.start_dt,
	case when dim.end_dt >= '9999-12-31' and ods.productId is not null
		then '$do_date'
		else dim.end_dt
	end as end_dt
from dim.dim_trade_product_info dim left join
	(select * from ods.ods_trade_product_info where dt='$do_date' ) ods on dim.productId = ods.productId;