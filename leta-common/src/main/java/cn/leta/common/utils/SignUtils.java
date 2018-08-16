package cn.leta.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 参数摘要算法。
 * <ol>
 * <li>参数按key字符默认排序</li>
 * <li>{@link #sign(Map)} 如有noSignParamNames，排除掉</li>
 * <li>将上一步得到所有参数按key+value拼接起来</li>
 * <li>将上一步的结果字符串首尾加盐 secret {@link #sign(Map, List)}</li>
 * </ol>
 * Created by xiegengcai on 2018/5/31.
 *
 * @author Xie Gengcai
 */
public class SignUtils {
    /**
     * @param params 需要签名的参数名
     * @param params 参数列表
     * @return
     */
    public static String sign(Map<String, String> params) {
        return sign(params, null);
    }

    /**
     * 对paramValues进行签名，其中noSignParamNames这些参数不参与签名
     *
     * @param params
     * @param noSignParamNames
     * @return
     */
    public static String sign(Map<String, String> params,
                              List<String> noSignParamNames) {
        StringBuilder buffer = new StringBuilder();
        List<String> paramNames = new ArrayList<>(params.size());
        paramNames.addAll(params.keySet());
        if (noSignParamNames != null && noSignParamNames.size() > 0) {
            noSignParamNames.forEach(paramNames::remove);
        }
        Collections.sort(paramNames);

        for (String paramName : paramNames) {
            buffer.append("&").append(paramName).append("=").append(params.get(paramName));
        }
        buffer.deleteCharAt(0);
        return MessageDigestUtil.digest(MessageDigestUtil.Algorithm.SHA_1.getName(), buffer.toString());
    }

    /**
     * 简单签名方法。将参数列表进行字典序默认排序，然后拼接为source签名
     * @param list
     * @return
     */
    public static String sign(List<String> list) {
        Collections.sort(list);
        StringBuilder buffer = new StringBuilder();
        list.forEach(str -> buffer.append(str));
        return MessageDigestUtil.digest(MessageDigestUtil.Algorithm.SHA_1.getName(), buffer.toString());
    }

}
