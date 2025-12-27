package com.blog.service;

import com.blog.entity.Menu;
import com.blog.model.dto.LabelOptionDTO;
import com.blog.model.dto.MenuDTO;
import com.blog.model.dto.UserMenuDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.IsHiddenVO;
import com.blog.model.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenus(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void updateMenuIsHidden(IsHiddenVO isHiddenVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();

}
