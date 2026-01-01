package com.blog.service.impl;

import com.blog.service.AiChatService;

import com.blog.strategy.AiModelStrategy;
import org.springframework.stereotype.Service;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;



@Service
@DubboService(version = "1.0.0")
public class AiChatServiceImpl implements AiChatService {
    @Autowired
    private AiModelStrategy aiModelStrategy;

    @Override
    public String chat(String model, String prompt) {
        return aiModelStrategy.chat(model, prompt);
    }
}
