package com.blog.service;

import com.blog.entity.UniqueView;
import com.blog.model.dto.UniqueViewDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

}
