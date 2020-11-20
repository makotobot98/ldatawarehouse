#！/bin/bash
source /etc/profile
if [ -n "$1" ]
then
do_date=$1
else
do_date=`date -d "-1 day" +%F`
fi
sql="
insert overwrite table dim.dim_trade_shops_org
partition(dt='$do_date')
select t1.shopid, t1.shopname, t2.id as cityid, t2.orgname as cityName, t3.id as region_id, t3.orgname as region_name
from (select shopId, shopName, areaId
		from ods.ods_trade_shops
		where dt='$do_date') t1
	left join
	(select id, parentId, orgname, orglevel
		from ods.ods_trade_shop_admin_org
		where orglevel=2 and dt='$do_date') t2
	on t1.areaid = t2.id
	left join
	(select id, parentId, orgname, orglevel
		from ods.ods_trade_shop_admin_org
		where orglevel=1 and dt='$do_date') t3
	on t2.parentid = t3.id;
"
hive -e "$sql"