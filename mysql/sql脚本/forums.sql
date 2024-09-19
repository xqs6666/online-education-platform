create table forums
(
    forum_id   int auto_increment comment '讨论主题ID'
        primary key,
    course_id  int                                 null comment '课程ID',
    user_id    int                                 null comment '用户ID',
    title      varchar(255)                        not null comment '讨论标题',
    content    text                                not null comment '讨论内容',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    constraint forums_ibfk_1
        foreign key (course_id) references courses (course_id),
    constraint forums_ibfk_2
        foreign key (user_id) references users (user_id)
);

create index idx_forums_course_id
    on forums (course_id);

create index idx_forums_user_id
    on forums (user_id);

