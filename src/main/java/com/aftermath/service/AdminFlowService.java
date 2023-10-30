package com.aftermath.service;

import com.aftermath.mapper.FlowMapper;
import com.aftermath.pojo.Flow;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdminFlowService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Flow> queryFlow() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);

        List<Flow> flows = flowMapper.selectAll();

        sqlSession.close();

        return flows;
    }

    public void deleteFlow(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);

        flowMapper.deleteByCourseId(courseId);

        sqlSession.commit();

        sqlSession.close();

    }

    public boolean existFlow(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);

        Flow flow = flowMapper.selectByCourseId(courseId);

        sqlSession.close();

        return flow != null;
    }
}
