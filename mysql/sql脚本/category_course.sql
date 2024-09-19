create table category_course
(
    category_course_id int auto_increment comment '分类课程主键'
        primary key,
    category_id        int not null,
    course_id          int not null comment '课程id'
)
    comment '课程分类表' charset = utf8mb4;

INSERT INTO online_education_platform.category_course (category_course_id, category_id, course_id) VALUES (1, 1, 1);
INSERT INTO online_education_platform.category_course (category_course_id, category_id, course_id) VALUES (2, 1, 2);
