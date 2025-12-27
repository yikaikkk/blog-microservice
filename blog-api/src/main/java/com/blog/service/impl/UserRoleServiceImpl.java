package com.blog.service.impl;

import com.blog.entity.UserRole;
import com.blog.mapper.UserRoleMapper;
import com.blog.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
