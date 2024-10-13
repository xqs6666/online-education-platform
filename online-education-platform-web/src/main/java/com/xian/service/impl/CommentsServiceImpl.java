package com.xian.service.impl;

import com.xian.cotext.UserContext;
import com.xian.mapper.CoursesMapper;
import com.xian.mapper.UsersMapper;
import com.xian.model.Comments;
import com.xian.mapper.CommentsMapper;
import com.xian.model.Courses;
import com.xian.model.Users;
import com.xian.model.dto.CommentDTO;
import com.xian.model.dto.CommentWithUserDTO;
import com.xian.model.vo.CommentsDataVo;
import com.xian.model.vo.CommentsVo;
import com.xian.service.ICommentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Service
public class CommentsServiceImpl  implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CoursesMapper coursesMapper;

    /**
     * 根据课程ID获取所有评论，并将其转换为CommentWithUserDTO列表
     *
     * @param courseId 课程ID
     * @return 返回包含评论及其子评论的CommentWithUserDTO列表
     */
    @Override
    public List<CommentWithUserDTO> getComments(Integer courseId) {
        // 根据课程ID查询所有评论
        List<Comments> commentsList = commentsMapper.getCommentsByCourseId(courseId);

        // 初始化CommentWithUserDTO列表
        List<CommentWithUserDTO> commentWithUserDTOS = new ArrayList<>();

        // 遍历所有评论
        for (Comments comments : commentsList) {
            // 检查是否为顶级评论
            if (comments.getParentId() == 0) {
                // 将评论转换为CommentWithUserDTO
                CommentWithUserDTO parentComment = convertToDTO(comments);
                // 获取子评论
                List<CommentWithUserDTO> childrenComments = getChildrenComments(comments.getCommentId(), commentsList);
                // 设置子评论
                parentComment.setChildren(childrenComments);
                // 添加顶级评论到列表
                commentWithUserDTOS.add(parentComment);
            }
        }
        // 返回CommentWithUserDTO列表
        return commentWithUserDTOS;
    }

    /**
     * 获取指定评论的所有子评论
     *
     * @param commentId 当前评论ID
     * @param commentsList 所有评论列表
     * @return 返回子评论的CommentWithUserDTO列表
     */
    private List<CommentWithUserDTO> getChildrenComments(Integer commentId, List<Comments> commentsList) {
        // 初始化子评论列表
        List<CommentWithUserDTO> childrenComments = new ArrayList<>();

        // 遍历所有评论
        for (Comments comments : commentsList) {
            // 检查是否为当前评论的子评论
            if (comments.getParentId().equals(commentId)) {
                // 将子评论转换为CommentWithUserDTO
                CommentWithUserDTO commentWithUserDTO = convertToDTO(comments);
                // 获取子评论的子评论
                List<CommentWithUserDTO> children = getChildrenComments(commentWithUserDTO.getCommentId(), commentsList);
                // 设置子评论
                commentWithUserDTO.setChildren(children);
                // 添加子评论到列表
                childrenComments.add(commentWithUserDTO);
            }
        }
        // 返回子评论列表
        return childrenComments;
    }

    /**
     * 将Comment实体转换为CommentWithUserDTO对象
     * 此方法的目的是将评论数据及其关联的用户信息封装到一个DTO对象中，以便于前端展示
     *
     * @param comments The Comments entity, representing a comment entity object
     * @return Returns a CommentWithUserDTO object filled with comment and user information
     */
    private CommentWithUserDTO convertToDTO(Comments comments) {
        // 初始化DTO对象
        CommentWithUserDTO commentWithUserDTO = new CommentWithUserDTO();
        // 复制Comments对象的属性到CommentWithUserDTO
        BeanUtils.copyProperties(comments, commentWithUserDTO);
        // 通过用户ID获取用户信息
        Users users = usersMapper.getById(comments.getUserId());
        // 设置用户名
        commentWithUserDTO.setUsername(users.getUsername());
        // 设置用户头像
        commentWithUserDTO.setAvatar(users.getAvatar());
        // 返回填充好的DTO对象
        return commentWithUserDTO;
    }


    @Override
    public void addComment(Integer parentId, CommentDTO commentDTO) {
        // 参数校验
        if (commentDTO == null) {
            throw new RuntimeException("参数错误");
        }
        if (commentDTO.getContent() == null || commentDTO.getContent().trim().isEmpty()) {
            throw new RuntimeException("评论内容不能为空");
        }
        if (commentDTO.getCourseId() == null) {
            throw new RuntimeException("课程ID不能为空");
        }

        // 获取当前用户ID
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");

        // 构建 Comments 对象
        Comments comments = new Comments();
        comments.setContent(commentDTO.getContent());
        comments.setUserId(userId);
        comments.setCourseId(commentDTO.getCourseId());

        // 如果 parentId 不为 null 或 0，说明是回复评论，否则是一级评论
        if (parentId != null && parentId > 0) {
            comments.setParentId(parentId);
        } else {
            comments.setParentId(0); // 设置为 0 表示一级评论
        }

        // 插入评论
        commentsMapper.insert(comments);
    }

    @Override
    public List<CommentsDataVo> getCommentsList() {
        List<CommentsDataVo> commentsDataVoList=new ArrayList<>();
        List<Comments> commentsList=commentsMapper.list();
        for (Comments comments : commentsList){
            CommentsDataVo commentsVo = new CommentsDataVo();
            BeanUtils.copyProperties(comments,commentsVo);
            Users users = usersMapper.getById(comments.getUserId());
            commentsVo.setUsername(users.getUsername());
            commentsVo.setAvatar(users.getAvatar());
            Courses courses = coursesMapper.getById(Long.valueOf(comments.getCourseId()));
            commentsVo.setCourseName(courses.getTitle());
            commentsDataVoList.add(commentsVo);
        }
        return commentsDataVoList;
    }

}
