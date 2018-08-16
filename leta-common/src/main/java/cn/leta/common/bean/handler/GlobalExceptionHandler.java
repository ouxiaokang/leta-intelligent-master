package cn.leta.common.bean.handler;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.vo.LetaRespone;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.TimeoutException;

/**
 * Created by xiegengcai on 2018/6/27.
 *
 * @author Xie Gengcai
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public LetaRespone<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("未支持的服务{}", e.getRequestURL());
        return LetaRespone.exception(Errors.GLOBAL.NOT_SUPPORTED_SERVICE, e.getRequestURL());
    }

    /**
     * 处理方法参数不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public LetaRespone<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型不匹配，parameterName={}, parameterValue={}，不是{}类型", e.getName(), e.getValue(), e.getRequiredType().getCanonicalName());
        return LetaRespone.failure(Errors.GLOBAL.PARAM_TYPE_BIND_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public LetaRespone<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = String.format("Request method '%s' not supported, supported method%s", e.getMethod(), e.getSupportedHttpMethods());
        log.error(message);
        return LetaRespone.exception(Errors.GLOBAL.NOT_SUPPORTED_METHOD, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public LetaRespone<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        if (e.getCause() instanceof JsonParseException) {
            return handleJsonParseException((JsonParseException) e.getCause());
        }
        log.error("HttpMessageNotReadableException:{}", e);
        return LetaRespone.failure(Errors.GLOBAL.MESSAGE_NOTREADABLE_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public LetaRespone<Void> handleJsonParseException(JsonParseException e) {
        log.error(Errors.GLOBAL.JSON_PARSE_ERROR.getMessage()+"(Caused by"+e.getClass().getName()+"):" + e.getMessage());
        return LetaRespone.failure(Errors.GLOBAL.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public LetaRespone<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindException(new BindException(e.getBindingResult()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public LetaRespone<Void> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(constraintViolation -> message.append("#").append(constraintViolation.getPropertyPath()).append(constraintViolation.getMessage()));
        log.error("参数校验失败，{}", message);
        return LetaRespone.exception(Errors.GLOBAL.PARAM_IS_INVALID, message.toString());
    }

    @ExceptionHandler(BindException.class)
    public LetaRespone<Void> handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> message.append("#").append(fieldError.getObjectName()).append(".").append(fieldError.getField()).append(fieldError.getDefaultMessage()));
        log.error("参数绑定错误， {}", message);
        return LetaRespone.exception(Errors.GLOBAL.PARAM_TYPE_BIND_ERROR, message.toString());
    }

    @ExceptionHandler(TimeoutException.class)
    public LetaRespone<Void> handleTimeoutException(TimeoutException e) {
        log.error("服务超时，{}", e);
        return LetaRespone.failure(Errors.GLOBAL.TIME_OUT);
    }

    @ExceptionHandler(Exception.class)
    public LetaRespone<Void> handleException(Exception e) {
        log.error("未知异常，{}", e);
        return LetaRespone.failure(Errors.GLOBAL.FAIL);
    }

    @ExceptionHandler(BizException.class)
    public LetaRespone<Void> handleBizException(BizException e) {
        log.error("业务异常, code={}, message={}", e.getCode(), e.getMessage());
        return LetaRespone.exception(e);
    }
}
