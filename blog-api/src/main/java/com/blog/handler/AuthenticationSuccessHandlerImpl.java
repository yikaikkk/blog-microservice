package com.blog.handler;

import com.alibaba.fastjson.JSON;
import com.blog.constant.CommonConstant;
import com.blog.entity.UserAuth;
import com.blog.mapper.UserAuthMapper;
import com.blog.model.dto.UserDetailsDTO;
import com.blog.model.dto.UserInfoDTO;
import com.blog.model.vo.ResultVO;
import com.blog.service.TokenService;
import com.blog.util.BeanCopyUtil;
import com.blog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO userLoginDTO = BeanCopyUtil.copyObject(UserUtil.getUserDetailsDTO(), UserInfoDTO.class);
        if (Objects.nonNull(authentication)) {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
            String token = tokenService.createToken(userDetailsDTO);
            userLoginDTO.setToken(token);
        }
        response.setContentType(CommonConstant.APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(ResultVO.ok(userLoginDTO)));
        updateUserInfo();
    }

    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtil.getUserDetailsDTO().getId())
                .ipAddress(UserUtil.getUserDetailsDTO().getIpAddress())
                .ipSource(UserUtil.getUserDetailsDTO().getIpSource())
                .lastLoginTime(UserUtil.getUserDetailsDTO().getLastLoginTime())
                .build();
        userAuthMapper.updateById(userAuth);
    }
}
