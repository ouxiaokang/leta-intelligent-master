package cn.leta.upms;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableSwagger2Doc
@EnableTransactionManagement
@SpringCloudApplication
@ComponentScan(basePackages = {"cn.leta.common", "cn.leta.upms.common", "cn.leta.upms.service", "cn.leta.upms.controller"})
public class LetaUpmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LetaUpmsApplication.class, args);
    }
}