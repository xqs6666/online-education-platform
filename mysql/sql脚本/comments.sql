create table comments
(
    comment_id int auto_increment comment '评论ID'
        primary key,
    forum_id   int                                 null comment '讨论主题ID',
    user_id    int                                 null comment '用户ID',
    content    text                                not null comment '评论内容',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建日期',
    constraint comments_ibfk_1
        foreign key (forum_id) references forums (forum_id),
    constraint comments_ibfk_2
        foreign key (user_id) references users (user_id)
);

create index idx_comments_forum_id
    on comments (forum_id);

create index idx_comments_user_id
    on comments (user_id);

