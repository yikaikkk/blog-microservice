package com.blog.service;

import com.blog.entity.FriendLink;
import com.blog.model.dto.FriendLinkAdminDTO;
import com.blog.model.dto.FriendLinkDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.FriendLinkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> listFriendLinks();

    PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO);

    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
