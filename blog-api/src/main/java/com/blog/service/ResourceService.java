package com.blog.service;

import com.blog.entity.Resource;
import com.blog.model.dto.LabelOptionDTO;
import com.blog.model.dto.ResourceDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();

}
