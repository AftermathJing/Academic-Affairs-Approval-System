package com.aftermath.service;

import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Course;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentChooseService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Course> queryAllNoChoose(String studentId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        List<Course> courses = courseMapper.selectAllNoChoose(studentId);

        sqlSession.close();

        return courses;
    }

    public void insertChoose(String studentId, String courseId, String notes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.insertChoose(studentId, courseId, notes);

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
