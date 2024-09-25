package com.xian.mapper;

import com.xian.model.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface CommentsMapper  {

    List<Comments> getCommentsByCourseId(Integer courseId);

    void insert(Comments comments);
}
