package com.blog.consumer;

import com.alibaba.fastjson.JSON;
import com.blog.entity.Article;
import com.blog.mapper.ElasticsearchMapper;
import com.blog.model.dto.ArticleSearchDTO;
import com.blog.model.dto.MaxwellDataDTO;
import com.blog.util.BeanCopyUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.blog.constant.RabbitMQConstant.MAXWELL_QUEUE;

@Component
@RabbitListener(queues = MAXWELL_QUEUE)
public class MaxWellConsumer {

    @Autowired
    private ElasticsearchMapper elasticsearchMapper;

    @RabbitHandler
    public void process(byte[] data) {
        MaxwellDataDTO maxwellDataDTO = JSON.parseObject(new String(data), MaxwellDataDTO.class);
        Article article = JSON.parseObject(JSON.toJSONString(maxwellDataDTO.getData()), Article.class);
        switch (maxwellDataDTO.getType()) {
            case "insert":
            case "update":
                elasticsearchMapper.save(BeanCopyUtil.copyObject(article, ArticleSearchDTO.class));
                break;
            case "delete":
                elasticsearchMapper.deleteById(article.getId());
                break;
            default:
                break;
        }
    }
}