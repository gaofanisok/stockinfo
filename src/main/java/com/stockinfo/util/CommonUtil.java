package com.stockinfo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2019/12/12 0012 14 54
 * @Description: 工具类
 */
public  class CommonUtil {
    /**
     *<pre>
     * Description  : 返回消息的值转换，返回消息统一用这两个方法  <br/>
     * ChangeLog    : 1. 创建 (2019/12/12 0012 14:54 [gaofan]);
     * @author gaofan
     * @date 2019/12/12 0012 14:54
     * @param code
     * @param msg
     * @return java.lang.String
     *</pre>          
    */
    public static String    toReturnJsonMsg(int code, String msg, String data) {
        return JSONObject.fromObject(new ApiJsonResult(msg, code, data)).toString();
    }
    /**
     * Description : 返回消息的值转换，返回消息统一用这两个方法  <br/>
     * ChangeLog : 1. 创建 (2019/12/12 0012 14:54 [gaofan]);
     * @author gaofan
     * @param code
     * @param msg
     * @return java.lang.String
     */
    public static String toReturnJsonMsg(int code, String msg) {
        return JSONObject.fromObject(new ApiJsonResult(msg, code)).toString();
    }
    /**
     *<pre>
     * Description  : 返回json数据给前端  <br/>
     * ChangeLog    : 1. 创建 (2019/12/13 0013 9:56 [gaofan]);
     * @author gaofan
     * @date 2019/12/13 0013 9:56
     * @param errorCode
     * @param msg
     * @param data
     * @return net.sf.json.JSONObject
     *</pre>
    */
    public static JSONObject RejsonArray(int errorCode,String msg,Object data){
        JSONObject js=new JSONObject();
        js.put("msg",msg);
        js.put("data",data);
        js.put("errorCode",errorCode);
        return js;
    }
    public static String RejsonArray(int errorCode,String msg){
        JSONObject js=new JSONObject();
        js.put("msg",msg);
        js.put("data",null);
        js.put("errorCode",errorCode);
        return js.toString();
    }
    /**
     * Description : 把map中值为null的专为 ""  <br/>
     * ChangeLog : 1. 创建 (2019/12/12 10:04 [gaofan]);
     * @param map
     * @return void
     */
    public static void nullToEmptyInMap(Map map) {

        if (map != null) {
            Iterator entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String) entry.getKey();
                if (entry.getValue() == null) {
                    map.put(key, "");
                }
            }
        }

    }

    /**
     * <pre>
     * Description : 根据map返回jsonstring数据,不移除数据
     * ChangeLog : 1. 创建 (2019.12.12 14:16 gaofan);
     * @param map
     * @return java.lang.String
     * </pre>
     */
    public static String getJsonstringByMap2(Map<String, Object> map) {

        String data = "";
        if (map != null) {
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
            data = JSON.toJSONString(map, SerializerFeature.WRITE_MAP_NULL_FEATURES,
                    SerializerFeature.WriteDateUseDateFormat);
        }
        return data;
    }

    /**
     * <pre>
     * Description : 转json
     * ChangeLog : 1. 创建 (2019.12.12 18:00 gaofan);
     * @param obj
     * @return java.lang.String
     * </pre>
     */
    public static String toJSON(Object obj) {
        SerializeConfig mapping = new SerializeConfig();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));

        String text = JSON.toJSONString(obj, mapping);

        return text;
    }
}
