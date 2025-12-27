package com.blog.service;

import com.blog.entity.Tag;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.TagAdminDTO;
import com.blog.model.dto.TagDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagService extends IService<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    PageResultDTO<TagAdminDTO> listTagsAdmin(ConditionVO conditionVO);

    List<TagAdminDTO> listTagsAdminBySearch(ConditionVO conditionVO);

    void saveOrUpdateTag(TagVO tagVO);

    void deleteTag(List<Integer> tagIds);

}
