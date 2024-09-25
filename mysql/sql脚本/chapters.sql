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

INSERT INTO online_education_platform.chapters (chapter_id, course_id, title, description, created_at, updated_at) VALUES (2, 1, '第一节', '认识jre与jvm', '2024-09-18 11:20:18', '2024-09-18 11:20:18');
INSERT INTO online_education_platform.chapters (chapter_id, course_id, title, description, created_at, updated_at) VALUES (3, 1, '第二节', '认识常量与变量', '2024-09-18 11:29:43', '2024-09-18 11:29:43');
