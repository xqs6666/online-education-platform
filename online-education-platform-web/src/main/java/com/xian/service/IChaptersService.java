package com.xian.service;

import com.xian.model.Chapters;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.dto.ChaptersDTO;
import com.xian.model.vo.ChapterVo;
import com.xian.model.vo.CourseChapterVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface IChaptersService  {

    CourseChapterVo getChapters(String courseId);

    void save(ChaptersDTO chaptersDTO, MultipartFile file);
}
