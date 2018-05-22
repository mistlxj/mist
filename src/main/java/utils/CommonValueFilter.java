package utils;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Member;
import java.util.Date;

/**
 * Created by lixiaojian10 on 2018/5/22.
 */
public class CommonValueFilter implements ValueFilter {
    public static final CommonValueFilter ME = new CommonValueFilter();

    @Override
    public Object process(Object object, String name, Object value) {
        if (value instanceof StackTraceElement) {
            return value.toString();
        } else if (value instanceof Date) {
            return TimeUtil.formatAllTime((Date) value);
        } else if (value instanceof Member) {
            return value.toString();
        }
        return value;
    }
}
