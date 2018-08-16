package cn.leta.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 系统日志
 * Created by xiegengcai on 2018/6/28.
 *
 * @author Xie Gengcai
 */
@Data
public class GatewayLog implements Serializable {

    /**
     * 请求来源
     */
    private String appKey;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 操作IP地址
     */
    private String remoteAddr;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * 执行时间
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 响应数据
     */
    private String responseData;

    /**
     * 服务ID
     */
    private String serviceId;
}
