create table forums
(
    forum_id   int auto_increment comment '讨论主题ID'
        primary key,
    user_id    int                                 null comment '用户ID',
    title      varchar(255)                        not null comment '讨论标题',
    content    text                                not null comment '讨论内容',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    constraint forums_ibfk_2
        foreign key (user_id) references users (user_id)
);

create index idx_forums_user_id
    on forums (user_id);

