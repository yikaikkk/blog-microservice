package com.blog.strategy;

import com.blog.config.AbstractAiConfig;
import com.blog.config.ZhipuConfig;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


    






@Component
@Slf4j
public class AiModelStrategy {

     @Autowired
     private AbstractAiConfig[] abstractAiConfig;

    private Map<String, ChatLanguageModel> modelMap = new HashMap<>();

    private Map<String, StreamingChatLanguageModel> streamingModelMap = new HashMap<>();

     @PostConstruct
     public void AiModelStrategyInit() {
         log.info("初始化模型策略");
         log.info("模型数量:{}", abstractAiConfig.length);

         for (AbstractAiConfig config : abstractAiConfig) {
            if(config.getName().equals("zhipu")){
                log.info("初始化zhipu模型:{}", config.getModel());
                modelMap.put(config.getModel(), ZhipuInit((ZhipuConfig) config));
            }
         }

         for (AbstractAiConfig config : abstractAiConfig) {
            if(config.getName().equals("zhipu")){
                log.info("初始化zhipu流式模型:{}", config.getModel());
                streamingModelMap.put(config.getModel(), streamingChatLanguageModelInit((ZhipuConfig) config));
            }
         }

//        for(String model : modelMap.keySet()){
//
//            log.info("初始化zhipu模型:{}", modelMap.get(model));
//         }
//
//
//         for(String model : streamingModelMap.keySet()){
//
//            log.info("初始化zhipu流式模型:{}", streamingModelMap.get(model));
//         }


         


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

    public StreamingChatLanguageModel streamingChatLanguageModelInit(ZhipuConfig zhipuConfig) {
        log.info("初始化zhipu流式模型:{}", zhipuConfig.getModel());
        log.info("zhipu模型配置:{}", zhipuConfig);
        
        return ZhipuAiStreamingChatModel.builder()
                .baseUrl(zhipuConfig.getBaseUrl())
                .apiKey(zhipuConfig.getApiKey())
                .model(zhipuConfig.getModel())
                .temperature(0.7)
                .connectTimeout(Duration.ofSeconds(10))
                .writeTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(10))
                .callTimeout(Duration.ofSeconds(90))
                .build();
    }
    
    
    /**
     * 调用模型
     * @param model 模型名称
     * @param prompt 提示词
     * @return 模型回复
     */
    public String chat(String model, String prompt) {
        log.info("调用模型:{} 提示词:{}", model, prompt);   
        return modelMap.get(model).generate(prompt);
    }

    /**
     * 调用模型流式回复
     * @param model 模型名称
     * @param prompt 提示词
     * @return 模型回复流
     */
    public Flux<String> chatStream(String model, String prompt) {

    StreamingChatLanguageModel chatModel = streamingModelMap.get(model);

    return Flux.create(sink -> {
        chatModel.generate(prompt, new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                sink.next(token);   // 推送给 Flux
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                sink.complete();
            }

            @Override
            public void onError(Throwable error) {
                sink.error(error);
            }
        });

    }, FluxSink.OverflowStrategy.BUFFER);
}
}
