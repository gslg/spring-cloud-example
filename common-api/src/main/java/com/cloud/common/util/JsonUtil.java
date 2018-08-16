package com.cloud.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Array;
import java.util.*;

public class JsonUtil {
    private JsonUtil() {
    }

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.DisableCircularReferenceDetect    //关闭循环引用的时候出现 $ref
    };

    /**
     * 序列化为和JSON-LIB兼容的字符串
     *
     * @param object
     * @return
     */
    public static String toCompatibleJSONString(Object object) {
        return JSON.toJSONString(object, features);
    }


    public static Map<String, Object> toMap(String jsonString) {
        return toObject(jsonString, HashMap.class);
    }

    /**
     * 将对象转换成JSON字符串
     *
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        return toCompatibleJSONString(obj);
    }

    public static String toJSONString(Object obj, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(obj, dateFormat, features);
    }

    /**
     * 将JSON字符串转换为对象(非数组型JSON)
     *
     * @param jsonString
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonString, Class<T> cla) {
        return (T) JSON.parseObject(jsonString, cla, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }

    public static JSONObject JSONObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    public static JSONObject JSONObject(Object jsonObj) {
        return JSON.parseObject(toJSONString(jsonObj));
    }

    public static JSONArray JSONArray(Object jsonObj) {
        return JSON.parseArray(toJSONString(jsonObj));
    }

    public static JSONArray JSONArray(String jsonString) {
        return JSON.parseArray(jsonString);
    }

    /**
     * 转json数组为list
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        if (text == null) {
            return null;
        }

        List<T> list;
        DefaultJSONParser parser = new DefaultJSONParser(text);
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken();
            list = null;
        } else {
            list = new ArrayList<T>();
            parser.parseArray(clazz, list);
        }
        parser.close();
        return list;
    }

    /**
     * 将JSON字符串转换为对象(数组型JSON)
     *
     * @param jsonString
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(String jsonString, Class<T> cla) {
        List<T> list = parseArray(jsonString, cla);
        if (list == null) return null;
        int size = list.size();
        int i = 0;
        T[] result = (T[]) Array.newInstance(cla, size);
        for (Iterator<T> itr = list.iterator();
             itr.hasNext(); ) {
            result[i++] = itr.next();
        }
        return result;
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray() || obj instanceof Collection || obj instanceof JSONArray;
    }
}
