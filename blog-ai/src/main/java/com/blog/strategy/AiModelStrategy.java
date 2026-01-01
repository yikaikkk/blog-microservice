package com.blog.strategy;

import com.blog.config.AbstractAiConfig;
import com.blog.config.ZhipuConfig;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;




@Component
@Slf4j
public class AiModelStrategy {

     @Autowired
     private AbstractAiConfig[] abstractAiConfig;

    private Map<String, ChatModel> modelMap = new HashMap<>();

     @PostConstruct
     public void AiModelStrategyInit() {
         log.info("初始化模型策略");
         log.info("模型数量:{}", abstractAiConfig.length);

         for (AbstractAiConfig config : abstractAiConfig) {
            if(config.getName().equals("zhipu")){
                log.debug("初始化zhipu模型:{}", config.getModel());
                modelMap.put(config.getModel(), (ChatModel) ZhipuInit((ZhipuConfig) config));
            }
         }
         for (String model : modelMap.keySet()) {
           String res=chat(model, "用一句话解释什么是 Kafka");
           log.info("模型:{} 回复:{}", model, res);
         }
         
     }

    public ZhipuAiChatModel ZhipuInit(ZhipuConfig zhipuConfig) {
        log.info("初始化zhipu模型:{}", zhipuConfig.getModel());
        log.info("zhipu模型配置:{}", zhipuConfig);
        
        return ZhipuAiChatModel.builder()
                .baseUrl(zhipuConfig.getBaseUrl())
                .apiKey(zhipuConfig.getApiKey())
                .model(zhipuConfig.getModel())
                .temperature(0.7)
                .connectTimeout(Duration.ofSeconds(10))
                .writeTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(10))
                .callTimeout(Duration.ofSeconds(90))
                .maxRetries(3)
                .build();
    }
    
    /**
     * 调用模型
     * @param model 模型名称
     * @param prompt 提示词
     * @return 模型回复
     */
    public String chat(String model, String prompt) {
        return modelMap.get(model).chat(prompt);
    }
}
