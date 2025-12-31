package com.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "langchain.zhipu")
public class ZhipuConfig implements AbstractAiConfig {
    // 配置名称，例如 "zhipu"
    private String name;
    // 基础URL，例如 "https://api.zhipu.cn"
    private String baseUrl;
    // 模型名称，例如 "zhipu"
    private String model;
    // API密钥
    private String apiKey;
}
