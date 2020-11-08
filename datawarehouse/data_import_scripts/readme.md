# Sample parsed Start Log JSON

```json
{
	"app_active": {
		"name": "app_active",
		"json": {
		"entry": "3",
		"action": "0",
		"error_code": "0"
	},
	"time": 1593553936325
	},
	"attr": {
		"area": "xx",
		"uid": "2F10092A192",
		"app_v": "1.1.12",
		"event_type": "common",
		"device_id": "1FB872-9A100192",
		"os_type": "0.7.0",
		"channel": "MA",
   		"language": "english",
		"brand": "Huawei-4"
	}
}
```

# Sample parsed Event Log JSON

```json
{
   "event":[
      {
         "name":"goods_detail_loading",
         "json":{
            "entry":"2",
            "goodsid":"0",
            "loading_time":"71",
            "action":"3",
            "staytime":"119",
            "showtype":"5"
         },
         "time":1594804466872
      },
      {
         "name":"notification",
         "json":{
            "action":"3",
            "type":"4"
         },
         "time":1594775458428
      },
      {
         "name":"ad",
         "json":{
            "duration":"19",
            "ad_action":"0",
            "shop_id":"46",
            "event_type":"ad",
            "ad_type":"2",
            "show_style":"1",
            "product_id":"9022",
            "place":"placeindex_right",
            "sort":"4"
         },
         "time":1594779518872
      },
      {
         "name":"favorites",
         "json":{
            "course_id":2,
            "id":0,
            "userid":0
         },
         "time":1594812897271
      }
   ],
   "attr":{
      "area":"QingYuan",
      "uid":"2F10092A77",
      "app_v":"1.1.7",
      "event_type":"common",
      "device_id":"1FB872-9A10077",
      "os_type":"0.8.4",
      "channel":"PQ",
      "language":"chinese",
      "brand":"iphone-2"
   }
}
```

