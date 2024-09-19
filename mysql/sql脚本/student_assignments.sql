create table student_assignments
(
    submission_id  int auto_increment comment '提交ID'
        primary key,
    student_id     int                                 null comment '学生ID',
    assignment_id  int                                 null comment '作业ID',
    submission_url varchar(255)                        null comment '提交链接',
    submitted_at   timestamp default CURRENT_TIMESTAMP null comment '提交日期',
    grade          float                               null comment '评分',
    feedback       text                                null comment '评语',
    constraint student_assignments_ibfk_1
        foreign key (student_id) references users (user_id),
    constraint student_assignments_ibfk_2
        foreign key (assignment_id) references assignments (assignment_id)
);

create index idx_student_assignments_assignment_id
    on student_assignments (assignment_id);

create index idx_student_assignments_student_id
    on student_assignments (student_id);

