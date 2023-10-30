package com.aftermath.mapper;

import com.aftermath.pojo.Instructor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstructorMapper {
    List<Instructor> selectAll();

    Instructor selectById(@Param("id") String id);

    void deleteById(@Param("id") String id);

    List<Instructor> selectSupervisorInstructor(@Param("courseName") String courseName);

    List<Instructor> selectByName(@Param("instructorName") String instructorName);

    List<Instructor> selectTeacherInstructor(@Param("courseName") String courseName);

    Instructor selectByIdPassword(@Param("id") String id, @Param("password") String password);

    void modifyNotes(@Param("id") String id, @Param("authority") String authority);

    String queryNotes(@Param("id") String id);
}
