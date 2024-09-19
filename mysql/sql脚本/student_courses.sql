create table student_courses
(
    registration_id int auto_increment comment '注册ID'
        primary key,
    student_id      int                                 null comment '学生ID',
    course_id       int                                 null comment '课程ID',
    registered_at   timestamp default CURRENT_TIMESTAMP null comment '注册日期',
    progress        int       default 0                 null comment '学习进度',
    constraint student_courses_ibfk_1
        foreign key (student_id) references users (user_id),
    constraint student_courses_ibfk_2
        foreign key (course_id) references courses (course_id)
);

create index idx_student_courses_course_id
    on student_courses (course_id);

create index idx_student_courses_student_id
    on student_courses (student_id);

