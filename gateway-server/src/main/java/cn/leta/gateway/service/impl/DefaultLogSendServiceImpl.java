package cn.leta.gateway.service.impl;

import cn.leta.gateway.service.LogSendService;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 缺省日志发送服务实现
 * Created by xiegengcai on 2018/6/28.
 *
 * @author Xie Gengcai
 */
@Component
@Slf4j
public class DefaultLogSendServiceImpl implements LogSendService {

    @Override
    public void send(RequestContext requestContext) {
        log.info(JSON.toJSONString(build(requestContext)));
    }

}
