package com.aftermath.mapper;

import com.aftermath.pojo.Flow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowMapper {

    List<Flow> selectAll();

    Flow selectByCourseId(@Param("courseId") String courseId);

    List<Flow> selectByCourseName(@Param("courseName") String courseName);

    void addFlow(@Param("courseId") String courseId,
                 @Param("supervisorInstructor") String supervisorInstructor,
                 @Param("teacherInstructor") String teacherInstructor);

    void deleteByCourseId(@Param("courseId") String courseId);
}
