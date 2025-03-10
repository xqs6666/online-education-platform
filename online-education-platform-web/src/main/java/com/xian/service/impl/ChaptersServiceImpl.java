package com.xian.service.impl;

import com.xian.mapper.ChapterVideosMapper;
import com.xian.mapper.CoursesMapper;
import com.xian.model.ChapterVideos;
import com.xian.model.Chapters;
import com.xian.mapper.ChaptersMapper;
import com.xian.model.Courses;
import com.xian.model.dto.ChaptersDTO;
import com.xian.model.dto.UpdateChaptersDTO;
import com.xian.model.vo.ChapterVo;
import com.xian.model.vo.CourseChapterVo;
import com.xian.model.vo.VideosVo;
import com.xian.service.IChaptersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xian.sftp.SftpService;
import com.xian.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Service
@Slf4j
public class ChaptersServiceImpl  implements IChaptersService {
    @Autowired
    private CoursesMapper coursesMapper;

    @Autowired
    private ChaptersMapper chaptersMapper;

    @Autowired
    private ChapterVideosMapper chapterVideosMapper;

    @Autowired
    private SftpService sftpService;



    // 注入 Nginx 的视频访问 URL 前缀
    @Value("${nginx.video-url-prefix}")
    private String videoUrlPrefix;

    @Override
    @Transactional
    public CourseChapterVo getChapters(String courseId) {

        List<Chapters> chaptersList=chaptersMapper.getChapters(courseId);
        if (chaptersList.isEmpty()){
            throw new RuntimeException("该课程没有章节");
        }

        CourseChapterVo chapterVoList=new CourseChapterVo();

        List<ChapterVo> ChapterVoList=new ArrayList<>();
        for (Chapters chapters : chaptersList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapters,chapterVo);

            ChapterVideos chapterVideo =chapterVideosMapper.getChapterVideoByChapterId(chapters.getChapterId());
            if (chapterVideo==null){
                chapterVo.setPlayUrl("");
            }
            chapterVo.setPlayUrl(chapterVideo.getVideoUrl());
            chapterVo.setVideoId(chapterVideo.getVideoCode());
            ChapterVoList.add(chapterVo);
        }
        chapterVoList.setChapterVoList(ChapterVoList);
        Courses courses= coursesMapper.getById(Long.valueOf(courseId));
        chapterVoList.setCourses(courses);
        return chapterVoList;
    }

    @Override
    @Transactional
    public void save(ChaptersDTO chaptersDTO) {
        if (chaptersDTO.getPlayUrl()==null){
            throw new RuntimeException("请上传视频");
        }

        try {
            Chapters chapters = new Chapters();
            chapters.setCourseId(chaptersDTO.getCourseId());
            chapters.setTitle(chaptersDTO.getTitle());
            chapters.setDescription(chaptersDTO.getDescription());
            chapters.setSortOrder(chaptersDTO.getSortOrder());
            chaptersMapper.save(chapters);

            log.info("chapters:{}",chapters);
            ChapterVideos chapterVideos = new ChapterVideos();
            chapterVideos.setChapterId(chapters.getChapterId());
            chapterVideos.setVideoUrl(chaptersDTO.getPlayUrl());
            String playUrl = chaptersDTO.getPlayUrl();
            // 分割字符串
            String[] parts = playUrl.split("/");
            String videoIdWithExtension = parts[parts.length - 1]; // 获取最后一部分
            String videoId = videoIdWithExtension.split("\\.")[0]; // 去掉文件扩展名
            chapterVideos.setVideoCode(videoId);

            chapterVideosMapper.save(chapterVideos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传视频失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateChapter(UpdateChaptersDTO updateChaptersDTO) {
        Chapters chapters = new Chapters();
        BeanUtils.copyProperties(updateChaptersDTO,chapters);
        chaptersMapper.updateById(chapters);
        ChapterVideos chapterVideos = new ChapterVideos();
        chapterVideos.setChapterId(updateChaptersDTO.getChapterId());
        chapterVideos.setVideoUrl(updateChaptersDTO.getVideoUrl());
        String videoUrl = updateChaptersDTO.getVideoUrl();
        String[] parts = videoUrl.split("/");
        String videoIdWithExtension = parts[parts.length - 1]; // 获取最后一部分
        String videoId = videoIdWithExtension.split("\\.")[0];
        chapterVideos.setVideoCode(videoId);
        chapterVideosMapper.updateById(chapterVideos);
    }

    @Override
    @Transactional
    public void removeById(Integer chapterId) {
        // 删除章节视频
        ChapterVideos chapterVideo = chapterVideosMapper.getChapterVideoByChapterId(chapterId);
        // 定义要删除的远程文件路径
        String remoteFilePath =chapterVideo.getVideoCode() + ".mp4";  // 视频文件是 .mp4 后缀
        // 使用 SftpService 删除文件
        try {
            sftpService.deleteFile(remoteFilePath);
            chapterVideosMapper.deleteById(chapterId);
            chaptersMapper.removeById(chapterId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
