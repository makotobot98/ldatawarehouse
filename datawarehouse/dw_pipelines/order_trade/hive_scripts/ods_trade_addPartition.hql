alter table ods.ods_trade_orders add partition(dt='$do_date');
alter table ods.ods_trade_order_product add partition(dt='$do_date');
alter table ods.ods_trade_product_info add partition(dt='$do_date');
alter table ods.ods_trade_product_category add partition(dt='$do_date');
alter table ods.ods_trade_shops add partition(dt='$do_date');
alter table ods.ods_trade_shop_admin_org add partition(dt='$do_date');
alter table ods.ods_trade_payments add partition(dt='$do_date');