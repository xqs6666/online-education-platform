create table chapter_videos
(
    video_id    int auto_increment comment '视频ID'
        primary key,
    chapter_id  int                                 null comment '章节ID',
    video_url   varchar(255)                        not null comment '视频链接',
    uploaded_at timestamp default CURRENT_TIMESTAMP null comment '上传日期',
    constraint chapter_videos_ibfk_1
        foreign key (chapter_id) references chapters (chapter_id)
);

create index idx_chapter_videos_chapter_id
    on chapter_videos (chapter_id);

