package com.ldw.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Charsets;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.interceptor.Interceptor;
import org.junit.Test;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
public class LogTimeInterceptor implements Interceptor {
  @Override
  public void initialize() {}

  /**
   * overrideing interceptor to extract the event timestamp and add to the header in format of "yyyy-MM-dd"
   */
  @Override
  public Event intercept(Event event) {
    String eventBody = new String(event.getBody(), Charsets.UTF_8);
    // obtain event header
    Map<String, String> headersMap = event.getHeaders();
    String[] bodyArr = eventBody.split("\\s+");
    try {
      String jsonStr = bodyArr[6];
      String timestampStr = "";
      JSONObject jsonObject = JSON.parseObject(jsonStr);
      //parse the time from the log based on the log type: start log or event log
      if (headersMap.getOrDefault("logtype", "").equals("start")){
        // start log timestamp
        timestampStr = jsonObject.getJSONObject("app_active").getString("time");
      } else if (headersMap.getOrDefault("logtype", "").equals("event")) {
        // event log timestamp
        JSONArray jsonArray = jsonObject.getJSONArray("event");
        if (jsonArray.size() > 0){
        timestampStr = jsonArray.getJSONObject(0).getString("time");
        }
      }
      // convert the time stamp into "yyyy-MM-dd" and add to the header
      long timestamp = Long.parseLong(timestampStr);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      Instant instant = Instant.ofEpochMilli(timestamp);
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
      String date = formatter.format(localDateTime);
      // add to header
      headersMap.put("logtime", date);
      event.setHeaders(headersMap);
    } catch (Exception e){
      headersMap.put("logtime", "Unknown");
      event.setHeaders(headersMap);
    }
    return event;
  }

  /**
   * intercept for a batch of events, simply reuse the intercept() for a single event
   */
  @Override
  public List<Event> intercept(List<Event> events) {
    List<Event> lstEvent = new ArrayList<>();
    for (Event event: events){
      Event outEvent = intercept(event);
      if (outEvent != null) {
        lstEvent.add(outEvent);
      }
    }
    return lstEvent;
  }

  @Override
  public void close() {}

  //provide a builder class
  public static class Builder implements Interceptor.Builder {
    @Override
    public Interceptor build() {
    return new LogTypeInterceptor();
    }
    @Override
    public void configure(Context context) {
    }
  }

  /** 
   * some straight forward unit test
   */
  @Test
  public void startLogIntercept(){
    String str = "2020-08-02 18:19:32.959 [main] INFO
      com.ecommerce.AppStart - {\"app_active\":
      {\"name\":\"app_active\",\"json\":
      {\"entry\":\"1\",\"action\":\"0\",\"error_code\":\"0\"},\"time\":159634284
      0284},\"attr\":{\"area\":\"DaQing
      \",\"uid\":\"2F10092A2\",\"app_v\":\"1.1.15\",\"event_type\":\"common\",\"
      device_id\":\"1FB872-
      9A1002\",\"os_type\":\"2.8\",\"channel\":\"TB\",\"language\":\"chinese\",\
      "brand\":\"iphone-8\"}}";
    Map<String, String> map = new HashMap<>();
    Event event = new SimpleEvent();
    map.put("logtype", "start");
    event.setHeaders(map);
    event.setBody(str.getBytes(Charsets.UTF_8));
    LogTypeInterceptor customerInterceptor = new LogTypeInterceptor();
    Event outEvent = customerInterceptor.intercept(event);
    // verifying result
    Map<String, String> headersMap = outEvent.getHeaders();
    System.out.println(JSON.toJSONString(headersMap));
  }

  @Test
  public void eventJunit(){
    String str = "2020-08-02 18:20:11.877 [main] INFO
      com.ecommerce.AppEvent - {\"event\":
      [{\"name\":\"goods_detail_loading\",\"json\":
      {\"entry\":\"1\",\"goodsid\":\"0\",\"loading_time\":\"93\",\"action\":\"3\
      ",\"staytime\":\"56\",\"showtype\":\"2\"},\"time\":1596343881690},
      {\"name\":\"loading\",\"json\":
      {\"loading_time\":\"15\",\"action\":\"3\",\"loading_type\":\"3\",\"type\":
      \"1\"},\"time\":1596356988428},{\"name\":\"notification\",\"json\":
      {\"action\":\"1\",\"type\":\"2\"},\"time\":1596374167278},
      {\"name\":\"favorites\",\"json\":
      {\"course_id\":1,\"id\":0,\"userid\":0},\"time\":1596350933962}],\"attr\":
      {\"area\":\"ChangZhi
      \",\"uid\":\"2F10092A4\",\"app_v\":\"1.1.14\",\"event_type\":\"common\",\"
      device_id\":\"1FB872-
      9A1004\",\"os_type\":\"0.5.0\",\"channel\":\"QL\",\"language\":\"chinese\"
      ,\"brand\":\"xiaomi-0\"}}";
    Map<String, String> map = new HashMap<>();
    // new Event
    Event event = new SimpleEvent();
    map.put("logtype", "event");
    event.setHeaders(map);
    event.setBody(str.getBytes(Charsets.UTF_8));
    // invoke interceptor
    LogTypeInterceptor customerInterceptor = new LogTypeInterceptor();
    Event outEvent = customerInterceptor.intercept(event);
    // result
    Map<String, String> headersMap = outEvent.getHeaders();
    System.out.println(JSON.toJSONString(headersMap));
  }
}
