package com.blog.strategy;


import com.blog.model.dto.UserInfoDTO;

public interface SocialLoginStrategy {

    UserInfoDTO login(String data);

}
