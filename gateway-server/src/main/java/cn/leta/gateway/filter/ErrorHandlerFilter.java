package cn.leta.gateway.filter;

import cn.leta.common.exception.BizException;
import cn.leta.common.utils.ObjectMapperHelper;
import cn.leta.common.vo.LetaRespone;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


/**
 * 网关统一异常处理
 * Created by xiegengcai on 2018/6/28.
 *
 * @author Xie Gengcai
 */
@Slf4j
@Component
public class ErrorHandlerFilter extends ZuulFilter {

    @Autowired
    private ObjectMapperHelper objectMapperHelper;
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;// SendErrorFilter 之前
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Throwable e = ctx.getThrowable();
            // 处理BizException
            if (e.getCause() != null && e.getCause() instanceof BizException) {
                BizException bizException = (BizException) e.getCause();
                LetaRespone<Void> letaRespone = LetaRespone.exception(bizException);
                ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                ctx.setResponseBody(objectMapperHelper.writeValueAsString(letaRespone));
                // 删除该异常信息,不然在下一个过滤器中还会被执行处理
                ctx.remove("throwable");
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }

        return null;
    }

}
