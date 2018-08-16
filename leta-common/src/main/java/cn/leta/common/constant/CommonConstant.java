package cn.leta.common.constant;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2017-11-30.
 * @author Xie Gengcai
 */
public interface CommonConstant {

    enum JsonpParamName {
        jsonp, callback;
    }
    /**
     * token 前缀（name_）枚举
     */
    enum Token {
        STAFF       // 员工登录TOKEN
        , WECHAT    // 微信服务接口TOKEN
    }

    enum Modules {
        GATEWAY_SERVER
        , CONFIG_SERVER
        , GROUP_SERVER
        , UPMS_SERVER
        , USER_SERVER
        , WECHAT_SERVER
        , TICKET_SERVER
        , MC_SERVER
    }

    String LETA_TOKEN = "LETA_TOKEN";
    /**
     * 默认每页展示数
     */
    int PAGE_SIZE = 20;
    //excel2003扩展名
    String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 阿里大鱼
     */
    String ALIYUN_SMS = "aliyun_sms";

    /**
     * 路由信息Redis保存的key
     */
    String ROUTE_KEY = "_ROUTE_KEY";

    char C_SPACE = ' ';
    char C_TAB = '	';
    char C_DOT = '.';
    char C_SLASH = '/';
    char C_BACKSLASH = '\\';
    char C_CR = '\r';
    char C_LF = '\n';
    char C_UNDERLINE = '_';
    char C_DASHED = '-';
    char C_COMMA = ',';
    char C_COLON = ':';

    String SPACE = String.valueOf(C_SPACE);
    String TAB = String.valueOf(C_TAB);
    String DOT = String.valueOf(C_DOT);
    String DOUBLE_DOT = "..";
    String SLASH = String.valueOf(C_SLASH);
    String BACKSLASH = String.valueOf(C_BACKSLASH);
    String CR = String.valueOf(C_CR);
    String LF = String.valueOf(C_LF);
    String CRLF = "\r\n";
    String UNDERLINE = String.valueOf(C_UNDERLINE);
    String DASHED = String.valueOf(C_DASHED);
    String COMMA = String.valueOf(C_COMMA);
    String COLON = String.valueOf(C_COLON);
}
