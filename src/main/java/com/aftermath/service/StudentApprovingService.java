package com.aftermath.service;

import com.aftermath.mapper.ApprovalMapper;
import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Approval;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentApprovingService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Approval> queryAllApproval(String studentId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectAllApproval(studentId);

        sqlSession.close();

        return approvals;
    }

    public void cancelChoose(String studentId, String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.cancelChoose(studentId, courseId);

        sqlSession.commit();

        sqlSession.close();
    }

    public boolean existChoose(String studentId, String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Integer integer = courseMapper.existChoose(studentId, courseId);

        sqlSession.close();

        return integer != 0;
    }
}
