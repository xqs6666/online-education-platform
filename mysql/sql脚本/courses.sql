create table courses
(
    course_id   int auto_increment comment '课程ID'
        primary key,
    title       varchar(255)                             not null comment '课程标题',
    description text                                     null comment '课程描述',
    category_id int                                      not null comment '课程分类id',
    price       decimal(10, 2) default 0.00              null comment '课程价格',
    teacher_id  int                                      not null comment '教师ID',
    created_at  timestamp      default CURRENT_TIMESTAMP null comment '创建日期',
    updated_at  timestamp      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    image       varchar(255)                             null comment '图片',
    constraint courses_ibfk_1
        foreign key (teacher_id) references users (user_id)
);

create index idx_courses_teacher_id
    on courses (teacher_id);

INSERT INTO online_education_platform.courses (course_id, title, description, category_id, price, teacher_id, created_at, updated_at, image) VALUES (1, 'JavaSE基础课程', 'java学习很牛逼，nice', 1, 18.88, 4, '2024-08-08 18:18:52', '2024-10-14 14:52:47', 'https://img2.baidu.com/it/u=2611421759,1559911602&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=500');
INSERT INTO online_education_platform.courses (course_id, title, description, category_id, price, teacher_id, created_at, updated_at, image) VALUES (2, 'python基础课程', 'python学习很牛逼，nice', 1, 20.88, 4, '2024-08-08 18:19:20', '2024-10-14 14:53:08', 'https://img2.baidu.com/it/u=2113867332,315376026&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=312');
INSERT INTO online_education_platform.courses (course_id, title, description, category_id, price, teacher_id, created_at, updated_at, image) VALUES (3, 'PS', 'PS学习很牛逼，nice', 2, 800.00, 4, '2024-09-03 07:04:39', '2024-09-03 07:04:39', 'https://img2.baidu.com/it/u=3574863973,3090991588&fm=253&fmt=auto&app=120&f=JPEG?w=805&h=800');
