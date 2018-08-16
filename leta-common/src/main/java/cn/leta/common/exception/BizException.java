package cn.leta.common.exception;

import cn.leta.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by xiegengcai on 2018/6/22.
 *
 * @author Xie Gengcai
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BizException extends RuntimeException {

    private int code;
    private String message;
    private Throwable cause;

    public BizException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
