package cn.leta.gateway;

import cn.leta.common.utils.ObjectMapperHelper;
import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2017/10/18.
 * @author Xie Gengcai
 */
@EnableSwaggerButler
@EnableZuulProxy
@SpringCloudApplication
@ComponentScan(basePackages = {"cn.leta.common", "cn.leta.gateway"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ObjectMapperHelper objectMapperHelper(){
        return new ObjectMapperHelper();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod(HttpMethod.OPTIONS);// 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.GET);// 允许Get的请求方法
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }
}
