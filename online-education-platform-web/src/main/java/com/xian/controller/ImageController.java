package com.xian.controller;

import com.xian.model.vo.ImagesVo;  // 确保你有适当的 VO 类来处理图像数据
import com.xian.sftp.SftpService;
import com.xian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/online/images")
public class ImageController {

    @Autowired
    private SftpService sftpService;

    // 注入 Nginx 的视频访问 URL 前缀
    @Value("${nginx.video-url-prefix}")
    private String videoUrlPrefix;

    // 上传图片接口
    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("File is empty");
        }

        try {
            // 生成唯一ID
            String uuid = UUID.randomUUID().toString();
            // 获取文件扩展名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 校验文件类型
            if (!isImageFile(extension)) {
                return Result.error("File type is not supported. Please upload an image file.");
            }

            // 使用 SftpService 上传文件
            sftpService.uploadFile(file, uuid, extension);

            // 返回图片的唯一ID和访问地址
            ImagesVo imagesVo = new ImagesVo();
            imagesVo.setImageId(uuid);
            imagesVo.setImageUrl(videoUrlPrefix + uuid + extension);
            return Result.success(imagesVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to upload image: " + e.getMessage());
        }
    }

    // 通过图片ID获取图片访问地址
    @GetMapping("/view/{id}")
    public Result viewImage(@PathVariable String id) {
        String fileExtension = ".jpg"; // 根据你的需求设置默认扩展名
        ImagesVo imagesVo = new ImagesVo();
        imagesVo.setImageId(id);
        imagesVo.setImageUrl(videoUrlPrefix + id + fileExtension);
        return Result.success(imagesVo);
    }

    // 删除图片接口
    @DeleteMapping("/delete/{id}")
    public Result deleteImage(@PathVariable String id) {
        try {
            // 定义要删除的远程文件路径
            String remoteFilePath =  id + ".jpg";

            // 使用 SftpService 删除文件
            sftpService.deleteFile(remoteFilePath);

            return Result.success("Image deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to delete image: " + e.getMessage());
        }
    }

    // 校验文件类型
    private boolean isImageFile(String extension) {
        return extension.equalsIgnoreCase(".jpg") ||
               extension.equalsIgnoreCase(".jpeg") ||
               extension.equalsIgnoreCase(".png") ||
               extension.equalsIgnoreCase(".gif");
    }
}
