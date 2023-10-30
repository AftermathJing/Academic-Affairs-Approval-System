package com.aftermath.service;

import com.aftermath.mapper.InstructorMapper;
import com.aftermath.pojo.Instructor;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdminAuthorityService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Instructor> selectInstructorsByName(String instructorName){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        List<Instructor> instructors = instructorMapper.selectByName(instructorName);

        sqlSession.close();

        return instructors;
    }

    public void modifyAuthority(String instructorId, String authority){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        instructorMapper.modifyNotes(instructorId, authority);

        sqlSession.commit();

        sqlSession.close();
    }

    public boolean modifySuccess(String instructorId, String authority){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        String targetAuthority = instructorMapper.queryNotes(instructorId);

        sqlSession.close();

        return targetAuthority.equals(authority);
    }
}
