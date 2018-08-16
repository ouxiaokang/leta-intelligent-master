package cn.leta.common.utils;

import cn.leta.common.annotation.SensitiveInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 日志敏感信息脱敏工具
 * Created by xiegengcai on 2018/6/29.
 *
 * @author Xie Gengcai
 */
public final class SensitiveInfoUtil {
    private SensitiveInfoUtil(){}

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, getValueFilter());
    }

    private static String desensitizePhoneOrIdCard(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
    }

    private static String desensitizeBankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "****"));
    }

    private static String desensitizePasswordOrSecret(String source) {
        if (StringUtils.isBlank(source)) {
            return "";
        }
        return "***********";
    }

    private static final ValueFilter getValueFilter() {
        return (obj, key, value) -> {//obj-对象  key-字段名  value-字段值
            try {
                Field field = obj.getClass().getDeclaredField(key);
                SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                if (null != annotation && value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.isNotBlank(strVal)) {
                        switch (annotation.type()) {
                            case SALT:
                                return desensitizePasswordOrSecret(strVal);
                            case PASSWORD:
                                return desensitizePasswordOrSecret(strVal);
                            case SECRET_KEY:
                                return desensitizePasswordOrSecret(strVal);
                            case ID_CARD:
                                return desensitizePhoneOrIdCard(strVal);
                            case BANK_CARD:
                                return desensitizeBankCard(strVal);
                            default:
                                break;
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                //找不到的field对功能没有影响,空处理
            }
            return value;
        };
    }

}
