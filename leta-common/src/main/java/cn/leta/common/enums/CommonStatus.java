package cn.leta.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 * Created by xiegengcai on 2018/6/29.
 *
 * @author Xie Gengcai
 */
@Getter
@AllArgsConstructor
public enum CommonStatus {
    /**
     * 无效
     */
    INVALID(0, "无效")
    /**
     * 有效
     */
    , VALID(1, "有效")
    ;
    private int code;
    private String name;
}
