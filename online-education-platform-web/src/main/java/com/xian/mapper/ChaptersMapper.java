package com.xian.mapper;

import com.xian.model.Chapters;
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
public interface ChaptersMapper  {

    List<Chapters> getChapters(String courseId);

    void save(Chapters chapters);
}
