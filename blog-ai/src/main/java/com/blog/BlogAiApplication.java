package com.blog;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


 @SpringBootApplication
 @EnableDubbo
 @EnableDiscoveryClient
 @MapperScan("com.blog.mapper")
public class BlogAiApplication {
    public static void main(String[] args) {
         SpringApplication.run(BlogAiApplication.class, args);

//        ZhipuAiChatModel model = ZhipuAiChatModel.builder()
//                .apiKey("")
//                .baseUrl("https://open.bigmodel.cn/")
//                .model("glm-4.5-flash")
//                .temperature(0.7)
//                .connectTimeout(Duration.ofSeconds(10))
//                .writeTimeout(Duration.ofSeconds(10))
//                .readTimeout(Duration.ofSeconds(10))
//                .callTimeout(Duration.ofSeconds(90))
//                .maxRetries(3)
//                .build();
//
//        String result = model.generate("用一句话解释什么是 Kafka");
//        System.out.println(result);

    }
     @Bean
     public RestTemplate restTemplate() {
         return new RestTemplate();
     }
}