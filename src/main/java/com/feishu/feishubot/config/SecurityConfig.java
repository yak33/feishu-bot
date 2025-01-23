package com.feishu.feishubot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Spring Security配置类
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 14:13
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF 保护（适用于无状态的 REST API）
                .csrf(csrf -> csrf.disable())
                // 配置请求授权规则
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/open-api/**").permitAll() // 放行 /open-api 开头的接口
                        .anyRequest().authenticated() // 其他所有请求需要认证
                )
                // 启用 HTTP Basic 认证（新方式）
                .httpBasic(withDefaults()); // 使用 withDefaults() 方法

        return http.build();
    }
}
