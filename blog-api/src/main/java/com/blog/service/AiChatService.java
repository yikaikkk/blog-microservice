package com.blog.service;

import reactor.core.publisher.Flux;

public interface AiChatService {
    /**
     * 调用模型
     * @param model 模型名称
     * @param prompt 提示词
     * @return 模型回复
     */
    String chat(String model, String prompt);


    Flux<String> chatStream(String model, String prompt);

    
}
