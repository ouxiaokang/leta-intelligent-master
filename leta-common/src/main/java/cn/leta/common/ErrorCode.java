package cn.leta.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by xiegengcai on 2018/6/26.
 *
 * @author Xie Gengcai
 */
@Data
@AllArgsConstructor
public class ErrorCode {
    private int code;
    private String message;

    public static ErrorCode build(int code, String message) {
        return new ErrorCode(code, message);
    }
}
