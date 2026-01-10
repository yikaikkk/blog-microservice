package com.blog.controller;

import com.blog.annotation.OptLog;
import com.blog.model.vo.ResultVO;
import com.blog.service.AiChatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "AI助手模块")
public class AiAssistantController {

    @DubboReference(version = "1.0.0" ,timeout = 10000)
    private AiChatService aiChatService;


    @ApiOperation(value = "查询AI助手 - GET方式")
    @GetMapping("/admin/ai/chat")
    public ResultVO<String> chatPost(@RequestParam String model, @RequestParam String prompt) {
        return ResultVO.ok(aiChatService.chat(model, prompt));
    }

//     curl -X GET "http://localhost:8090/api/admin/ai/chat?model=glm-4&prompt=你好，请用一句话介绍自己" \
// -H "Content-Type: application/json"

    
}
