package com.xian.controller;

import com.xian.model.dto.ChaptersDTO;
import com.xian.model.dto.UpdateChaptersDTO;
import com.xian.model.vo.CourseChapterVo;
import com.xian.service.IChaptersService;
import com.xian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@RestController
@RequestMapping("/online/chapters")
public class ChaptersController {
    @Autowired
    private IChaptersService chaptersService;

    //根据课程id查询章节列表
    @GetMapping("/getChapterVideo/{courseId}")
    public Result<CourseChapterVo> getChapters(@PathVariable String courseId){
        CourseChapterVo chaptersList=chaptersService.getChapters(courseId);
        return Result.success(chaptersList);
    }

    //添加章节
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody ChaptersDTO chaptersDTO){
        chaptersService.save(chaptersDTO);
        return Result.success();
    }

    //修改章节
    @PutMapping("/updateChapter")
    public Result updateChapter(@RequestBody UpdateChaptersDTO updateChaptersDTO){
        chaptersService.updateChapter(updateChaptersDTO);
        return Result.success();
    }

    //删除章节
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Result deleteChapter(@PathVariable Integer chapterId){
        chaptersService.removeById(chapterId);
        return Result.success();
    }


}
