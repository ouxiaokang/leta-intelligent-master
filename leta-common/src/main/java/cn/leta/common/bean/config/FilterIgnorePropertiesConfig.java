package cn.leta.common.bean.config;

import cn.leta.common.lang.SimpleCache;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiegengcai on 2018/6/29.
 *
 * @author Xie Gengcai
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class FilterIgnorePropertiesConfig {
    @Value("${server.context-path:}")
    private String contextPath;
    private List<String> urls = new ArrayList<>();

    private List<String> clients = new ArrayList<>();
    private static final SimpleCache<String, Boolean> IGNORE_URL_CACHE = new SimpleCache<>();
    private static final SimpleCache<String, Boolean> IGNORE_CLIENT_CACHE = new SimpleCache<>();
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 是否是忽略的URI。配置不含contextPath
     * @param url
     * @return
     */
    public boolean isIgnoreUrl(String url) {
        if (IGNORE_URL_CACHE.containsKey(url)) {
            return true;
        }
        for (String path : this.urls) {
            if (antPathMatcher.match(contextPath + path, url)) {
                IGNORE_URL_CACHE.put(url, Boolean.TRUE);
                return true;
            }
        }
        return false;
    }

    public boolean isIgnoreClient(String client) {
        if (IGNORE_CLIENT_CACHE.containsKey(client)) {
            return true;
        }

        for (String tmp : this.clients) {
            if (tmp.equals(client)) {
                IGNORE_CLIENT_CACHE.put(client, Boolean.TRUE);
                return true;
            }
        }

        return false;
    }

}
