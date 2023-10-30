CREATE DATABASE ApprovalSystem;
use ApprovalSystem;

CREATE TABLE `Instructor`
(
    `ID`       char(8) COMMENT '职工号',
    `Name`     varchar(20) COMMENT '姓名',
    `Password` varchar(15) COMMENT '密码',
    `Notes`    varchar(50) COMMENT '备注',
    PRIMARY KEY (`ID`)
) COMMENT '职工表';

CREATE TABLE `Student`
(
    `ID`       char(8) COMMENT '学号',
    `Name`     varchar(20) COMMENT '姓名',
    `Password` varchar(15) COMMENT '密码',
    `Grade`    numeric(1, 0) COMMENT '年级',
    PRIMARY KEY (`ID`)
) COMMENT '学生表';

CREATE TABLE `Course`
(
    `ID`         char(15) COMMENT '课程号',
    `Name`       varchar(30) UNIQUE COMMENT '课程名',
    `Credits`    numeric(3, 1) CHECK ( Credits > 0 ) COMMENT '学分',
    `Semester`   varchar(6) CHECK ( Semester in
                                    ('Spring', 'Fall')) COMMENT '学期',
    `Begin_year` numeric(4, 0) CHECK
        ( Begin_year > 2000 and Begin_year < 2100 ) COMMENT '起始年',
    `Notes`      varchar(50) COMMENT '备注',
    PRIMARY KEY (`ID`)
) COMMENT '课程';

CREATE TABLE `Choose`
(
    `Student_ID` char(8) COMMENT '学号',
    `Course_ID`  char(15) COMMENT '课程号',
    `Status`     boolean DEFAULT NULL COMMENT '申请是否成功',
    `Notes`      varchar(50) COMMENT '备注',
    PRIMARY KEY (`Student_ID`, `Course_ID`),
    FOREIGN KEY (`Student_ID`) references Student (`ID`) on delete cascade,
    FOREIGN KEY (`Course_ID`) references Course (`ID`) on delete cascade
) COMMENT '选课';

CREATE TABLE `Teach`
(
    `Instructor_ID` char(8) COMMENT '职工号',
    `Course_ID`     char(15) COMMENT '课程号',
    PRIMARY KEY (`Instructor_ID`, `Course_ID`),
    FOREIGN KEY (`Instructor_ID`) references Instructor (`ID`) on delete cascade,
    FOREIGN KEY (`Course_ID`) references Course (`ID`) on delete cascade
) COMMENT '教课';

CREATE TABLE `Admin`
(
    `ID`       char(8) COMMENT '职工号',
    `Name`     varchar(20) COMMENT '姓名',
    `Password` varchar(15) COMMENT '密码',
    PRIMARY KEY (`ID`)
) COMMENT '管理员表';

CREATE TABLE `Flow`
(
    `Course_ID`             char(15) COMMENT '课程号',
    `Supervisor_Instructor` varchar(100) COMMENT '主管教师',
    `Instructor`            varchar(100) COMMENT '授课教师',
    PRIMARY KEY (`Course_ID`),
    FOREIGN KEY (`Course_ID`) references Course (`ID`) on delete cascade
) COMMENT '审批流表';
