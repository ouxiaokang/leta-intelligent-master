package cn.leta.common.annotation;

import cn.leta.common.Errors;
import cn.leta.common.bean.config.FilterIgnorePropertiesConfig;
import cn.leta.common.vo.LetaRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * 统一格式响应封装
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/21.
 *
 * @author Xie Gengcai
 */
@RestControllerAdvice
public class LetaResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;


    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if (isExclude(request)) {
            return body;
        }
        if (body instanceof LetaRespone) {
            return body;
        }
        // 40x错误转换统一响应格式
        if (body instanceof Map) {
            Map<String, ?> httpError = (Map<String, ?>) body;
            Integer status = (Integer) httpError.get("status");
            if (status != null) {
                body = getErrorRespone(status);
            } else {
                body = LetaRespone.success(body);
            }
        } else {
            // 正常响应格式封装
            body = LetaRespone.success(body);
        }
        // 统一响应Content-Type
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        return body;
    }

    /**
     * 细化HTTP错误响应
     * @param status
     * @return
     */
    protected LetaRespone<Void> getErrorRespone(int status) {
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        switch (httpStatus) {
            case NOT_FOUND:
                return LetaRespone.exception(Errors.GLOBAL.NOT_SUPPORTED_SERVICE, HttpStatus.NOT_FOUND.getReasonPhrase());
            case UNAUTHORIZED:
                return LetaRespone.exception(Errors.GLOBAL.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
            case FORBIDDEN:
                return LetaRespone.exception(Errors.GLOBAL.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
            default:
                return LetaRespone.exception(Errors.GLOBAL.FAIL, "httpStatus="+status);
        }
    }
    /**
     * 排除请求。包括actuator监控请求及 {@see FilterIgnorePropertiesConfig} 中匹配的
     * @param request
     * @return
     */
    private boolean isExclude(ServerHttpRequest request) {
        String uri = request.getURI().getPath();

        if (filterIgnorePropertiesConfig.isIgnoreUrl(uri)) {
            return true;
        }
        return false;
    }
}