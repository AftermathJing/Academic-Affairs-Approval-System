package com.aftermath.service;

import com.aftermath.mapper.ApprovalMapper;
import com.aftermath.pojo.Approval;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class InstructorApprovalService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Approval> queryAllApproval(String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ApprovalMapper approvalMapper = sqlSession.getMapper(ApprovalMapper.class);

        List<Approval> approvals = approvalMapper.selectApprovalByInstructorId(instructorId);

        sqlSession.close();

        return approvals;
    }

}
