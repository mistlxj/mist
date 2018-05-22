package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

import static com.alibaba.fastjson.serializer.SerializerFeature.UseISO8601DateFormat;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;

/**
 * Created by lixiaojian10 on 2018/5/22.
 */
public class JSONUtil {
    private static class TestModel {
        private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public static void main(String ags[]) {
        TestModel model = new TestModel();
        model.setDate(new Date());
        System.out.println(TimeUtil.formatAllTime(model.getDate()));
        String str = JSONUtil.toString(model);
        System.out.println(str);
        model = JSON.parseObject(str, TestModel.class);
        System.out.println(TimeUtil.formatAllTime(model.getDate()));
    }

    /**
     * 转化JSON格式
     *
     * @param text
     * @return
     */
    public static JSON parse(String text) {
        try {
            Object obj = JSON.parse(text);
            if (obj instanceof JSON) {
                return (JSON) obj;
            } else {
                return parseJSON(text);
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static String json(Object o) {
        try {
            return JSON.toJSONString(o, CommonValueFilter.ME);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String toString(Object o) {
        return toString(o, UseISO8601DateFormat);
    }

    public static String toStringNative(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static String toStringC(Object o) {
        return toString(o, UseISO8601DateFormat, WriteClassName);
    }

    public static String toString(Object o, SerializerFeature... features) {
        try {
            return JSON.toJSONString(o, CommonValueFilter.ME, features);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static JSONObject parseJSON(String text) {
        try {
            return JSON.parseObject(text);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static JSONArray parseJSONArr(String text) {
        try {
            return JSON.parseArray(text);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static String toStringWithNull(Object o) {
        return toString(o, WriteMapNullValue, UseISO8601DateFormat);
    }

    private JSONUtil() {

    }
}
