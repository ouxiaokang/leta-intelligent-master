package cn.leta.common;

/**
 * 错误码定义
 * Created by xiegengcai on 2018/6/26.
 *
 * @author Xie Gengcai
 */
public interface Errors {

    /**
     * 全局错误定义。10000~19999
     */
    interface GLOBAL {
        ErrorCode SUCCESS = ErrorCode.build(0, "操作成功");
        ErrorCode FAIL = ErrorCode.build(10000, "操作失败，请重试或联系客服");
        ErrorCode NOT_SUPPORTED_SERVICE = ErrorCode.build(10001, "未支持的服务");
        ErrorCode NOT_SUPPORTED_METHOD = ErrorCode.build(10002, "不支持的请求方法");
        ErrorCode UNAUTHORIZED = ErrorCode.build(10003, "未授权请求，请先申请TOKEN");
        ErrorCode FORBIDDEN = ErrorCode.build(10004, "禁止访问，请联系管理员开放权限");
        ErrorCode GATEWAY_EXCEPTION = ErrorCode.build(10005, "网关异常");
        ErrorCode TIME_OUT = ErrorCode.build(10006, "服务超时");
        ErrorCode PARAM_IS_INVALID = ErrorCode.build(10005, "参数无效");
        ErrorCode MISSING_PARAMS = ErrorCode.build(10006, "您输入的参数不完整");
        ErrorCode PARAM_TYPE_BIND_ERROR = ErrorCode.build(10007, "参数类型不匹配");
        ErrorCode MESSAGE_NOTREADABLE_ERROR = ErrorCode.build(10008, "无法解析请求内容");
        ErrorCode JSON_PARSE_ERROR = ErrorCode.build(10009, "JSON数据格式错误");
        ErrorCode DATA_EXISTED_ERROR = ErrorCode.build(10010, "[%s=%s]的数据已存在");
        ErrorCode DATA_NOT_EXISTED = ErrorCode.build(10011, "数据不存在");
        ErrorCode UNSUPPORT_CHANGE_STATUS = ErrorCode.build(10012, "当前状态下不支持更新操作");
        ErrorCode SIGN_ERROR = ErrorCode.build(10013, "签名错误，请查阅文档");
        ErrorCode TOKEN_EXPIRED = ErrorCode.build(10014, "无效TOKEN或已过期");
    }

    /**
     * 经销商模块错误定义。20000~29999
     */
    interface Group {
        ErrorCode REDIS_NULL = ErrorCode.build(20000,"Redis模板获取异常");
        ErrorCode REDIS_TRANSFORMATION_ERROR =ErrorCode.build(20001,"Redis转换数据异常");
    }

    /**
     * 用户模块错误定义。30000~39999
     */
    interface User {
        ErrorCode PASSWORD_INCORRECT = ErrorCode.build(30000, "密码错误");
        ErrorCode VERIFY_CODE_INCORRECT = ErrorCode.build(30001, "验证码错误");
        ErrorCode FREQUENTLY_SEND_VERIFY_CODE = ErrorCode.build(30002, "获取验证码过于频繁");
    }

    /**
     * 微信模块错误码。40000-49999
     */
    interface Wechat{

        ErrorCode WX_ERROR = ErrorCode.build(40000,"访问微信开放平台错误：%s");
        ErrorCode APP_SECRET_ERROR=ErrorCode.build(40001,"AppSecret错误或accessToken无效");
        ErrorCode ACCESS_TOKEN_ERROR=ErrorCode.build(40002,"不合法的凭证类型");
        ErrorCode OPENID_ERROR = ErrorCode.build(40003,"OpenId错误");
        ErrorCode APPID_ERROR = ErrorCode.build(40013,"不合法的 AppID");
        ErrorCode SIGNATURE_ERROR = ErrorCode.build(40014,"消息签名错误");
        ErrorCode PARSE_XML_MSG_ERROR = ErrorCode.build(40015,"解析XML消息错误");
        ErrorCode WHITE_LIST_ERROR =ErrorCode.build(40164,"该公众号未配置白名单");
        ErrorCode CREATE_MEUN_ERROR =ErrorCode.build(40165,"公众号自定义菜单创建异常,错误码：[%s=%s]");
    }
    /**
     * 工单模块错误定义。50000-59999
     */
    interface Ticket {
        ErrorCode TICKET_CATEGORY_NOT_MATCH = ErrorCode.build(50000,"工单类型不匹配");
        ErrorCode CAll_LOG_CATEGORY_NOT_MATCH = ErrorCode.build(50001,"话单类型不匹配");
        ErrorCode UNSUPPORT_CHANGE_CONTACT = ErrorCode.build(50002, "当前状态下不支持变更联系方式");
        ErrorCode DUE_TIME_IS_NULL = ErrorCode.build(50003, "预约时间不能为空");
        ErrorCode STATUS_ERROR = ErrorCode.build(50004, "工单状态不正确");
        ErrorCode CANNOT_OPERATE_OTHERS_TICKET = ErrorCode.build(50005, "不能接受指派他人的工单");
    }

    interface Order {
        ErrorCode ORDER_ITEMS_IS_EMPTY = ErrorCode.build(60000, "订单项为空");
    }
}
