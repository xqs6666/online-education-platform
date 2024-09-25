create table chapter_videos
(
    video_id    int auto_increment comment '视频ID'
        primary key,
    chapter_id  int                                 null comment '章节ID',
    video_url   varchar(255)                        not null comment '视频链接',
    uploaded_at timestamp default CURRENT_TIMESTAMP null comment '上传日期',
    video_code  varchar(255)                        not null,
    constraint chapter_videos_ibfk_1
        foreign key (chapter_id) references chapters (chapter_id)
);

create index idx_chapter_videos_chapter_id
    on chapter_videos (chapter_id);

INSERT INTO online_education_platform.chapter_videos (video_id, chapter_id, video_url, uploaded_at, video_code) VALUES (1, 2, 'http://47.98.224.148/videos/f2082b14-14dd-4806-adcd-49a71f18d417.mp4', '2024-09-18 11:20:18', 'f2082b14-14dd-4806-adcd-49a71f18d417');
INSERT INTO online_education_platform.chapter_videos (video_id, chapter_id, video_url, uploaded_at, video_code) VALUES (2, 3, 'http://47.98.224.148/videos/ea3085bf-85c7-422c-a8c1-0569d4ce97e7.mp4', '2024-09-18 11:29:43', 'ea3085bf-85c7-422c-a8c1-0569d4ce97e7');
