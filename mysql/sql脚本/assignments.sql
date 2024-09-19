create table assignments
(
    assignment_id int auto_increment comment '作业ID'
        primary key,
    course_id     int  null comment '课程ID',
    description   text not null comment '作业描述',
    due_date      date null comment '截止日期',
    constraint assignments_ibfk_1
        foreign key (course_id) references courses (course_id)
);

create index idx_assignments_course_id
    on assignments (course_id);

