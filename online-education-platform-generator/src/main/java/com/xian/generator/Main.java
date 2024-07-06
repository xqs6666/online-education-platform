package com.xian.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class Main {
    public static void main(String[] args) {
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:mysql://60.204.156.130:3306/online_education_platform", "root", "genius398")
                .globalConfig(builder -> {
                    builder.author("鲜青松") // 设置作者
                            .enableSwagger() // 启用 Swagger
                            .outputDir("D:\\code\\online-education-platform\\generator"); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xian") // 设置父包名
                            .entity("model") // 设置实体类包名
                            .mapper("mapper") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .controller("controller") // 设置 Controller 包名
                            .xml("mappers"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("users", "student_courses", "student_assignments", "forums",
                                    "courses", "comments", "chapters", "chapter_videos", "assignments") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle() // 启用 REST 风格
                            .enableHyphenStyle() // 启用驼峰命名风格
                            .build(); // 设置 Controller 生成策略
                })
                .execute(); // 执行生成
    }
}
