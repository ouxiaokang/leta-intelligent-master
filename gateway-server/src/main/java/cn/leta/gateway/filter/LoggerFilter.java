package cn.leta.gateway.filter;

import cn.leta.common.bean.config.FilterIgnorePropertiesConfig;
import cn.leta.common.lang.SimpleCache;
import cn.leta.gateway.service.LogSendService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 网关日志拦截器
 * Created by xiegengcai on 2018/6/28.
 *
 * @author Xie Gengcai
 */
//@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;


    @Autowired
    private LogSendService logSendService;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    /**
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (ctx.get("serviceId") == null) {
            return false;
        }
        return !filterIgnorePropertiesConfig.isIgnoreUrl(request.getRequestURI());
    }

    @Override
    public Object run() {
        logSendService.send(RequestContext.getCurrentContext());
        return null;
    }
}
