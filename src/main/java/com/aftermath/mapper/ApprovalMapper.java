package com.aftermath.mapper;

import com.aftermath.pojo.Approval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalMapper {

    List<Approval> selectAll();
    List<Approval> selectStatusFalse();
    List<Approval> selectStatusNull();
    List<Approval> selectStatusTrue();
    List<Approval> selectByCourseName(@Param("courseName") String courseName);
    List<Approval> selectByStudentName(@Param("studentName") String courseName);
    List<Approval> selectByCourseNameStudentId(@Param("courseName") String courseName, @Param("studentId") String studentId);
    List<Approval> selectByCourseNameInstructorId(@Param("courseName") String courseName, @Param("instructorId") String instructorId);
    List<Approval> selectByStudentNameInstructorId(@Param("studentName") String studentName, @Param("instructorId") String instructorId);
    List<Approval> selectAllApproval(@Param("studentId") String studentId);
    List<Approval> selectAllApproving(@Param("studentId") String studentId);
    List<Approval> selectAllReject(@Param("studentId") String studentId);
    List<Approval> selectApprovingByInstructorId(@Param("instructorId") String instructorId);
    List<Approval> selectApprovalByInstructorId(@Param("instructorId") String instructorId);
    List<Approval> selectAgreeByInstructorId(@Param("instructorId") String instructorId);
    List<Approval> selectRejectByInstructorId(@Param("instructorId") String instructorId);
}
