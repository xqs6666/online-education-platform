package com.xian.mapper;

import com.xian.model.ChapterVideos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface ChapterVideosMapper  {

    ChapterVideos getChapterVideoByChapterId(Integer chapterId);

    void save(ChapterVideos chapterVideos);

    void updateById(ChapterVideos chapterVideos);

    void deleteById(Integer chapterId);
}
