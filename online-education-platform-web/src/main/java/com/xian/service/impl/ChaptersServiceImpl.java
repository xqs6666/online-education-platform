package com.xian.service.impl;

import com.xian.mapper.ChapterVideosMapper;
import com.xian.model.ChapterVideos;
import com.xian.model.Chapters;
import com.xian.mapper.ChaptersMapper;
import com.xian.model.dto.ChaptersDTO;
import com.xian.model.vo.ChapterVo;
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
    public List<ChapterVo> getChapters(String courseId) {

        List<Chapters> chaptersList=chaptersMapper.getChapters(courseId);
        if (chaptersList.isEmpty()){
            throw new RuntimeException("该课程没有章节");
        }
        List<ChapterVo> chapterVoList= new ArrayList<>();
        for (Chapters chapters : chaptersList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapters,chapterVo);

            ChapterVideos chapterVideo =chapterVideosMapper.getChapterVideoByChapterId(chapters.getChapterId());
            chapterVo.setPlayUrl(chapterVideo.getVideoUrl());
            chapterVo.setVideoId(chapterVideo.getVideoCode());
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    @Override
    public void save(ChaptersDTO chaptersDTO, MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            // 生成唯一ID
            String uuid = UUID.randomUUID().toString();
            // 获取文件扩展名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 使用 SftpService 上传文件
            sftpService.uploadFile(file, uuid, extension);


            Chapters chapters = new Chapters();
            chapters.setCourseId(chaptersDTO.getCourseId());
            chapters.setTitle(chaptersDTO.getTitle());
            chapters.setDescription(chaptersDTO.getDescription());
            chaptersMapper.save(chapters);

            log.info("chapters:{}",chapters);
            ChapterVideos chapterVideos = new ChapterVideos();
            chapterVideos.setChapterId(chapters.getChapterId());
            chapterVideos.setVideoUrl(videoUrlPrefix + uuid + extension);
            chapterVideos.setVideoCode(uuid);
            chapterVideosMapper.save(chapterVideos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传视频失败：" + e.getMessage());
        }
    }
}
