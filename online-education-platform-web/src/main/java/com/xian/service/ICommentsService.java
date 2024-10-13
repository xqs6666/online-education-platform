package com.xian.service;

import com.xian.model.Comments;
import com.xian.model.dto.CommentDTO;
import com.xian.model.dto.CommentWithUserDTO;
import com.xian.model.vo.CommentsDataVo;
import com.xian.model.vo.CommentsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface ICommentsService  {

    List<CommentWithUserDTO> getComments(Integer courseId);

    void addComment(Integer parentId,CommentDTO commentDTO);

    List<CommentsDataVo> getCommentsList();

}
