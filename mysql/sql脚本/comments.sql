create table comments
(
    comment_id int auto_increment comment '评论ID'
        primary key,
    user_id    int                                 null comment '用户ID',
    content    text                                not null comment '评论内容',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    course_id  int                                 not null comment '课程id',
    parent_id  int       default 0                 null comment '父评论ID',
    constraint comments_ibfk_2
        foreign key (user_id) references users (user_id)
);

create index idx_comments_user_id
    on comments (user_id);

INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (1, 4, '这一篇教程很不错', '2024-09-24 06:34:57', 1, 0);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (2, 1, '一般般吧', '2024-09-25 06:47:40', 1, 1);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (3, 6, '垃圾', '2024-09-25 06:49:05', 1, 1);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (4, 4, '很详细', '2024-09-25 07:00:37', 2, 0);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (5, 1, '老师很好！', '2024-09-25 07:04:03', 2, 0);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (6, 5, '你才是垃圾', '2024-09-25 07:21:27', 1, 3);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (12, 4, '好看 爱看', '2024-09-25 09:55:40', 1, 0);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (13, 4, '我不爱看', '2024-09-25 09:55:48', 1, 12);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (14, 4, '我也不爱看', '2024-09-25 09:55:57', 1, 13);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (15, 4, '一级', '2024-09-25 10:02:26', 2, 0);
INSERT INTO online_education_platform.comments (comment_id, user_id, content, created_at, course_id, parent_id) VALUES (16, 4, '二级', '2024-09-25 10:02:53', 2, 15);
