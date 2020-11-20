#！/bin/bash
source /etc/profile
if [ -n "$1" ]
then
do_date=$1
else
do_date=`date -d "-1 day" +%F`
fi
sql="
insert overwrite table dim.dim_trade_product_cat
partition(dt='$do_date')
select
	t1.catid, -- catagory level 1 id
	t1.catname, -- catagory level name
	t2.catid, -- catagory level 2 id
	t2.catname, -- catagory level 2 name
	t3.catid, -- catagory level 3 id
	t3.catname -- catagory level 3 name
	from -- product level catagory 3 information
		(select catid, catname, parentid
		from ods.ods_trade_product_category
		where level=3 and dt='$do_date') t3
	left join
	-- product level catagory 2 information
		(select catid, catname, parentid
		from ods.ods_trade_product_category
		where level=2 and dt='$do_date') t2
	on t3.parentid = t2.catid
	left join
	-- product level catagory 1 information
		(select catid, catname, parentid
		from ods.ods_trade_product_category
		where level=1 and dt='$do_date') t1
	on t2.parentid = t1.catid;
"
hive -e "$sql"