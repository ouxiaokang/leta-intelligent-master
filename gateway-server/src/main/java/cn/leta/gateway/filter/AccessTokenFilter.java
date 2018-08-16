package cn.leta.gateway.filter;

import cn.leta.common.Errors;
import cn.leta.common.bean.config.FilterIgnorePropertiesConfig;
import cn.leta.common.constant.CommonConstant;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.ObjectMapperHelper;
import cn.leta.wechat.model.dto.AccessTokenDTO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * 登录拦截器,未登录的用户直接返回未登录数据。在RateLimitPreFilter 之前执行，不然又空指针问题
 * Created by xiegengcai on 2018/6/27.
 *
 * @author Xie Gengcai
 */
@Slf4j
@Component
public class AccessTokenFilter extends ZuulFilter {
    private static final String PARAM_TOKEN = "TOKEN";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapperHelper objectMapperHelper;

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.RIBBON_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = getCurrentContext();
        String requestURI = (String) ctx.get("requestURI");
        if ("/*".equals(requestURI)) {
            return false;
        }
        return ctx.get("serviceId") != null && !filterIgnorePropertiesConfig.isIgnoreUrl(requestURI);
    }

    @Override
    public Object run() {
        RequestContext ctx = getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        HttpServletRequest req = ctx.getRequest();
        String token = req.getParameter(PARAM_TOKEN); // 拼参数方式
        token =  token == null ? req.getHeader(PARAM_TOKEN) : token;//header方式
        if (StringUtils.isEmpty(token)) {
            throw new BizException(Errors.GLOBAL.UNAUTHORIZED);
        }
        log.info("token:" + token);
        // 微信公众号token
        if (token.startsWith(CommonConstant.Token.WECHAT.name())) {
            String cacheVal = stringRedisTemplate.opsForValue().get(String.join(CommonConstant.COLON, CommonConstant.Modules.GATEWAY_SERVER.name(), CommonConstant.LETA_TOKEN, token));
            if (StringUtils.isEmpty(cacheVal)) {
                // throw token Expires exception (BizException)
                throw new BizException(Errors.GLOBAL.TOKEN_EXPIRED);
            }
            AccessTokenDTO accessTokenDTO = objectMapperHelper.readValue(cacheVal, AccessTokenDTO.class);
            ctx.addZuulRequestHeader("groupCode", accessTokenDTO.getGroupCode());
            ctx.addZuulRequestHeader("appId", accessTokenDTO.getAppId());
        }
        // 员工登录token
        if (token.startsWith(CommonConstant.Token.STAFF.name())) {
            Map<String, Object> staffToken = (Map<String, Object>) redisTemplate.opsForValue().get(String.join(CommonConstant.COLON, CommonConstant.Modules.GROUP_SERVER.name(), CommonConstant.LETA_TOKEN, token));
            ctx.addZuulRequestHeader("groupCode", staffToken.get("groupCode").toString());
            ctx.addZuulRequestHeader("staffId", staffToken.get("staffId").toString());
            ctx.addZuulRequestHeader("staffName", staffToken.get("staffName").toString());
            ctx.addZuulRequestHeader("phone", staffToken.get("phone").toString());
        }
        return null;
    }

}
