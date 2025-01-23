package com.feishu.feishubot.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * 服务启动监控类
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 14:00
 */
@Slf4j
@Configuration(proxyBeanMethods = false) // 用于优化运行时性能，避免创建代理方法。
public class StartEventListener {

    @Order
    @EventListener(WebServerInitializedEvent.class)
    public void onApplicationEvent(WebServerInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        int localPort = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("---[{}]-启动完成，当前使用的端口:[{}]，环境变量:[{}]---", appName, localPort, profile);
    }
}
