create table users
(
    user_id     int auto_increment comment '用户ID'
        primary key,
    username    varchar(50)                         not null comment '用户名',
    email       varchar(100)                        not null comment '邮箱',
    password    varchar(255)                        not null comment '密码',
    user_type   enum ('student', 'teacher')         not null comment '用户类型',
    created_at  timestamp default CURRENT_TIMESTAMP null comment '注册日期',
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    user_tag    varchar(10)                         null comment '用户标签',
    description varchar(255)                        null comment '用户描述',
    avatar      varchar(255)                        null comment '用户头像',
    constraint email
        unique (email)
);

INSERT INTO online_education_platform.users (user_id, username, email, password, user_type, created_at, updated_at, user_tag, description, avatar) VALUES (1, 'xqs', 'xqs@qq.com', '123456', 'student', '2024-07-05 18:59:02', '2024-07-06 09:27:15', null, null, null);
INSERT INTO online_education_platform.users (user_id, username, email, password, user_type, created_at, updated_at, user_tag, description, avatar) VALUES (2, 'yyx', 'yyx@qq.com', '123456', 'student', '2024-07-05 18:59:02', '2024-07-06 10:08:36', null, null, null);
INSERT INTO online_education_platform.users (user_id, username, email, password, user_type, created_at, updated_at, user_tag, description, avatar) VALUES (4, 'lz', 'lz@qq.com', '123456', 'teacher', '2024-07-06 10:09:27', '2024-08-07 07:52:43', 'java', '作为一名Java老师，我拥有扎实的编程基础和丰富的教学经验，致力于用简单易懂的方式帮助学生掌握Java编程技能，激发他们的学习兴趣，培养实际解决问题的能力。', 'https://img0.baidu.com/it/u=531804218,2147883837&fm=253&fmt=auto&app=120&f=PNG?w=575&h=500');
INSERT INTO online_education_platform.users (user_id, username, email, password, user_type, created_at, updated_at, user_tag, description, avatar) VALUES (5, 'tsq', 'tsq@qq.com', '123456', 'teacher', '2024-07-06 13:14:04', '2024-07-06 13:14:04', null, null, null);
INSERT INTO online_education_platform.users (user_id, username, email, password, user_type, created_at, updated_at, user_tag, description, avatar) VALUES (6, 'rs', 'rs@qq.com', '123456', 'teacher', '2024-07-06 13:19:18', '2024-07-06 13:19:18', null, null, null);
