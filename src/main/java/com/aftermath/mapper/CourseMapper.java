package com.aftermath.mapper;

import com.aftermath.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    List<Course> selectAll();

    Course selectById(@Param("courseId") String courseId);

    Course selectByName(@Param("courseName") String courseName);

    List<Course> selectByStudentId(@Param("studentId") String studentId);

    List<Course> selectByInstructorId(@Param("instructorId") String instructorId);

    List<Course> selectAllChoose(@Param("studentId") String studentId);

    List<Course> selectAllNoChoose(@Param("studentId") String studentId);

    List<Course> selectAllReject(@Param("studentId") String studentId);

    Boolean selectStatus(@Param("studentId") String studentId, @Param("courseId") String courseId);

    Integer existChoose(@Param("studentId") String studentId, @Param("courseId") String courseId);

    void insertChoose(@Param("studentId") String studentId, @Param("courseId") String courseId, @Param("notes") String notes);

    void updateChooseTrue(@Param("studentId") String studentId, @Param("courseId") String courseId);

    void updateChooseFalse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    void cancelChoose(@Param("studentId") String studentId, @Param("courseId") String courseId);

    void deleteById(@Param("courseId") String courseId);

    void insertCourse(@Param("courseId") String courseId,
                      @Param("courseName") String courseName,
                      @Param("credits") Double credits,
                      @Param("beginYear") Short beginYear,
                      @Param("semester") String semester,
                      @Param("notes") String notes);
}
