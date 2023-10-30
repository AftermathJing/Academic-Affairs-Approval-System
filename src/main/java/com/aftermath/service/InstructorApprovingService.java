package com.aftermath.service;

import com.aftermath.mapper.ApprovalMapper;
import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Approval;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class InstructorApprovingService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Approval> queryAllApproval(String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectApprovingByInstructorId(instructorId);

        sqlSession.close();

        return approvals;
    }

    public void reject(String studentId, String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.updateChooseFalse(studentId, courseId);

        sqlSession.commit();

        sqlSession.close();
    }

    public void pass(String studentId, String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.updateChooseTrue(studentId, courseId);

        sqlSession.commit();

        sqlSession.close();
    }

    public Boolean queryStatus(String studentId, String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Boolean aBoolean = courseMapper.selectStatus(studentId, courseId);

        sqlSession.close();

        return aBoolean;
    }

}
