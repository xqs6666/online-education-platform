create table chapters
(
    chapter_id  int auto_increment comment '章节ID'
        primary key,
    course_id   int                                 null comment '课程ID',
    title       varchar(255)                        not null comment '章节标题',
    description text                                null comment '章节描述',
    created_at  timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint chapters_ibfk_1
        foreign key (course_id) references courses (course_id)
);

create index idx_chapters_course_id
    on chapters (course_id);

