package cn.leta.gateway.service;

import cn.leta.common.entity.GatewayLog;
import cn.leta.common.utils.ExceptionUtil;
import cn.leta.common.utils.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.io.ByteStreams;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 日志发送服务
 * Created by xiegengcai on 2018/6/28.
 *
 * @author Xie Gengcai
 */
public interface LogSendService {
    /**
     * 往消息通道发消息
     *
     * @param requestContext requestContext
     */
    void send(RequestContext requestContext);

    default GatewayLog build(RequestContext ctx) {
        HttpServletRequest request = ctx.getRequest();
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        GatewayLog gateWayLog = new GatewayLog();
        gateWayLog.setRemoteAddr(HttpUtil.getClientIP(request));
        gateWayLog.setRequestUri(requestUri);
        gateWayLog.setMethod(method);
        gateWayLog.setUserAgent(request.getHeader("user-agent"));
        if (HttpMethod.POST.name().equals(request.getMethod()) || HttpMethod.PUT.name().equalsIgnoreCase(request.getMethod())) {
            try {
                gateWayLog.setParams(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {

            }
        } else {
            gateWayLog.setParams(JSON.toJSONString(request.getParameterMap()));
        }
        Long startTime = (Long) ctx.get("startTime");

        gateWayLog.setServiceId(ctx.get("serviceId").toString());
        try {
            if (ctx.getThrowable() != null ) {
                gateWayLog.setException(ExceptionUtil.getCauseMessage(ctx.getThrowable()));
            } else {
                gateWayLog.setResponseData(StreamUtils.copyToString(ctx.getResponseDataStream(), StandardCharsets.UTF_8));
            }
        } catch (IOException e) {

        }
        gateWayLog.setTime(System.currentTimeMillis() - startTime);
        return gateWayLog;

    }

}
