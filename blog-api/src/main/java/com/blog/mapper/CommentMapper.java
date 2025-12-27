package com.blog.mapper;

import com.blog.entity.Comment;
import com.blog.model.dto.CommentAdminDTO;
import com.blog.model.dto.CommentCountDTO;
import com.blog.model.dto.CommentDTO;
import com.blog.model.dto.ReplyDTO;
import com.blog.model.vo.CommentVO;
import com.blog.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> listComments(@Param("current") Long current, @Param("size") Long size, @Param("commentVO") CommentVO commentVO);

    List<ReplyDTO> listReplies(@Param("commentIds") List<Integer> commentIdList);

    List<CommentDTO> listTopSixComments();

    Integer countComments(@Param("conditionVO") ConditionVO conditionVO);

    List<CommentAdminDTO> listCommentsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    List<CommentCountDTO> listCommentCountByTypeAndTopicIds(@Param("type") Integer type, @Param("topicIds") List<Integer> topicIds);

    CommentCountDTO listCommentCountByTypeAndTopicId(@Param("type") Integer type, @Param("topicId") Integer topicId);

}
