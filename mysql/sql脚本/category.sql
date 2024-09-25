create table category
(
    category_id   int auto_increment comment '课程分类主键名称'
        primary key,
    category_name varchar(30) not null comment '课程名称'
)
    comment '课程分类表';

INSERT INTO online_education_platform.category (category_id, category_name) VALUES (1, 'IT');
INSERT INTO online_education_platform.category (category_id, category_name) VALUES (2, '媒体');
