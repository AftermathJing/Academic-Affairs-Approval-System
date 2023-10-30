package com.aftermath.service;

import com.aftermath.mapper.ApprovalMapper;
import com.aftermath.pojo.Approval;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdminSelectService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Approval> queryStatusTrue() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectStatusTrue();

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryStatusFalse() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectStatusFalse();

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryStatusNull() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectStatusNull();

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryByCourseName(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectByCourseName(courseName);

        sqlSession.close();

        return approvals;
    }

    public List<Approval> queryByStudentName(String studentName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectByStudentName(studentName);

        sqlSession.close();

        return approvals;
    }
}
