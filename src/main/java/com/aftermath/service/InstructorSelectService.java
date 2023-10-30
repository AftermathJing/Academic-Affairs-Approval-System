package com.aftermath.service;

import com.aftermath.mapper.ApprovalMapper;
import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Approval;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class InstructorSelectService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Approval> queryStatusTrue(String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectAgreeByInstructorId(instructorId);

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryStatusFalse(String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectRejectByInstructorId(instructorId);

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryByCourseName(String courseName, String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectByCourseNameInstructorId(courseName, instructorId);

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryByStudentName(String studentName, String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectByStudentNameInstructorId(studentName, instructorId);

        sqlSession.close();

        return approvals;
    }
}
