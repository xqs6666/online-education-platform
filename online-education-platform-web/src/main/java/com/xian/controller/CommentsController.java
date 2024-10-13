package com.xian.controller;

import com.xian.model.Comments;
import com.xian.model.dto.CommentDTO;
import com.xian.model.dto.CommentWithUserDTO;
import com.xian.model.vo.CommentsDataVo;
import com.xian.model.vo.CommentsVo;
import com.xian.service.ICommentsService;
import com.xian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@RestController
@RequestMapping("/online/comments")
public class CommentsController {
    @Autowired
    private ICommentsService commentsService;

    //根据课程id获取评论列表及回复
    @GetMapping("/getComments")
    public Result<List<CommentWithUserDTO>> getComments(Integer courseId){
        List<CommentWithUserDTO> commentsList=commentsService.getComments(courseId);
        return Result.success(commentsList);
    }

    //添加评论
    @PostMapping("/addComment/{parentId}")
    public Result addComment(@PathVariable(required = false) Integer parentId,@RequestBody CommentDTO commentDTO){
        commentsService.addComment(parentId,commentDTO);
        return Result.success();
    }

    //获取评论列表
    @GetMapping("/getCommentsList")
    public Result<List<CommentsDataVo>> getCommentsList(){
        List<CommentsDataVo> commentsList=commentsService.getCommentsList();
        return Result.success(commentsList);
    }

//    //删除评论
//    @DeleteMapping("/deleteComment/{commentId}")
//    public Result deleteComment(@PathVariable Integer commentId){
//        commentsService.deleteComment(commentId);
//        return Result.success();
//    }

}
