package com.blog.service;

import com.blog.entity.Talk;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.TalkAdminDTO;
import com.blog.model.dto.TalkDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.TalkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface TalkService extends IService<Talk> {

    PageResultDTO<TalkDTO> listTalks();

    TalkDTO getTalkById(Integer talkId);

    void saveOrUpdateTalk(TalkVO talkVO);

    void deleteTalks(List<Integer> talkIdList);

    PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO);

    TalkAdminDTO getBackTalkById(Integer talkId);

}
