package cn.leta.common.vo;

import cn.leta.common.ErrorCode;
import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Rest服务通用响应
 * Created by xiegengcai on 2018/6/22.
 *
 * @author Xie Gengcai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetaRespone<T> implements Serializable {

    private static final long serialVersionUID = 1038606910817857161L;
    private int code;
    private String message;
    private T data;

    private static final LetaRespone<Void> SUCCESS = new LetaRespone<>(Errors.GLOBAL.SUCCESS);

    public LetaRespone (ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public  static LetaRespone<Void> success() {
        return SUCCESS;
    }

    public static <T> LetaRespone<T> success(T data) {
        LetaRespone<T> letaRespone = new LetaRespone<>();
        letaRespone.setCode(Errors.GLOBAL.SUCCESS.getCode());
        letaRespone.setMessage(Errors.GLOBAL.SUCCESS.getMessage());
        letaRespone.setData(data);
        return letaRespone;
    }

    public static LetaRespone<Void> failure(ErrorCode errorCode) {
        LetaRespone<Void> letaRespone = new LetaRespone<>();
        letaRespone.setCode(errorCode.getCode());
        letaRespone.setMessage(errorCode.getMessage());
        return letaRespone;
    }

    public static LetaRespone<Void> exception(BizException e) {
        LetaRespone<Void> letaRespone = new LetaRespone<>();
        letaRespone.setCode(e.getCode());
        letaRespone.setMessage(e.getMessage());
        return letaRespone;
    }

    public static LetaRespone<Void> exception(ErrorCode errorCode, String message) {
        LetaRespone<Void> letaRespone = new LetaRespone<>();
        letaRespone.setCode(errorCode.getCode());
        letaRespone.setMessage(String.join("#", errorCode.getMessage(), message));
        return letaRespone;
    }

    public static LetaRespone<Void> exception(ErrorCode errorCode, Exception e, HttpStatus httpStatus) {
        LetaRespone<Void> letaRespone = new LetaRespone<>();
        letaRespone.setCode(errorCode.getCode());
        letaRespone.setMessage(String.join("#", errorCode.getMessage(), "httpStatus=", String.valueOf(httpStatus.value()), e.getMessage()));
        return letaRespone;
    }

}
