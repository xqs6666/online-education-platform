package com.xian.controller;

import com.xian.model.vo.VideosVo;

import com.xian.sftp.SftpService;
import com.xian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/online/videos")
public class VideoController {

    @Autowired
    private SftpService sftpService;

    // 注入 Nginx 的视频访问 URL 前缀
    @Value("${nginx.video-url-prefix}")
    private String videoUrlPrefix;

    // 上传视频接口
    @PostMapping("/upload")
    public Result uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("File is empty");
        }

        try {
            // 生成唯一ID
            String uuid = UUID.randomUUID().toString();
            // 获取文件扩展名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 使用 SftpService 上传文件
            sftpService.uploadFile(file, uuid, extension);

            // 返回视频的唯一ID和播放地址
            VideosVo videosVo = new VideosVo();
            videosVo.setVideoId(uuid);
            videosVo.setPlayUrl(videoUrlPrefix + uuid + extension);
            return Result.success(videosVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to upload video: " + e.getMessage());
        }
    }

    // 通过视频ID获取视频播放地址
    @GetMapping("/play/{id}")
    public Result playVideo(@PathVariable String id) {
        String fileExtension = ".mp4";  // 你可以根据实际情况处理扩展名
        VideosVo videosVo = new VideosVo();
        videosVo.setPlayUrl(videoUrlPrefix + id + fileExtension);
        return Result.success(videosVo);
    }

    // 删除视频接口
    @DeleteMapping("/delete/{id}")
    public Result deleteVideo(@PathVariable String id) {
        try {
            // 定义要删除的远程文件路径
            String remoteFilePath = id + ".mp4";  // 视频文件是 .mp4 后缀

            // 使用 SftpService 删除文件
            sftpService.deleteFile(remoteFilePath);

            return Result.success("Video deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to delete video: " + e.getMessage());
        }
    }
}
