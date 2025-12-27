package com.blog.service;

import com.blog.entity.Comment;
import com.blog.model.dto.CommentAdminDTO;
import com.blog.model.dto.CommentDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.ReplyDTO;
import com.blog.model.vo.CommentVO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.ReviewVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    PageResultDTO<CommentDTO> listComments(CommentVO commentVO);

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();

    PageResultDTO<CommentAdminDTO> listCommentsAdmin(ConditionVO conditionVO);

    void updateCommentsReview(ReviewVO reviewVO);

}
