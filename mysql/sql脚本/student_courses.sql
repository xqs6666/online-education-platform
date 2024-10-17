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

INSERT INTO online_education_platform.student_courses (registration_id, student_id, course_id, registered_at, progress) VALUES (1, 1, 1, '2024-10-09 12:21:17', 0);
INSERT INTO online_education_platform.student_courses (registration_id, student_id, course_id, registered_at, progress) VALUES (3, 1, 2, '2024-10-09 12:35:16', 0);
INSERT INTO online_education_platform.student_courses (registration_id, student_id, course_id, registered_at, progress) VALUES (4, 1, 3, '2024-10-09 12:35:35', 0);
INSERT INTO online_education_platform.student_courses (registration_id, student_id, course_id, registered_at, progress) VALUES (5, 2, 1, '2024-10-09 12:37:24', 0);
INSERT INTO online_education_platform.student_courses (registration_id, student_id, course_id, registered_at, progress) VALUES (6, 2, 2, '2024-10-09 12:53:28', 0);
