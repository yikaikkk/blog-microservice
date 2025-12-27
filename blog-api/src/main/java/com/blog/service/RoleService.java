package com.blog.service;

import com.blog.entity.Role;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.RoleDTO;
import com.blog.model.dto.UserRoleDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.RoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<UserRoleDTO> listUserRoles();

    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);

    void saveOrUpdateRole(RoleVO roleVO);

    void deleteRoles(List<Integer> ids);

}
