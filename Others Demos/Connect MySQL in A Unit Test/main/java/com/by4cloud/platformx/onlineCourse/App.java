package com.by4cloud.platformx.onlineCourse;

import com.by4cloud.platformx.common.feign.annotation.EnablePlatformxFeignClients;
import com.by4cloud.platformx.common.security.annotation.EnablePlatformxResourceServer;
import com.by4cloud.platformx.common.swagger.annotation.EnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author platformx archetype
 * <p>
 * 项目启动类
 */
@EnableOpenApi("online_course")
@EnablePlatformxFeignClients
@EnableDiscoveryClient
@EnablePlatformxResourceServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
