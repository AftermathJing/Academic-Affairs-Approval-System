package com.aftermath.service;

import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Course;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class InstructorCourseService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Course> queryCourse(String instructorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        List<Course> courses = courseMapper.selectByInstructorId(instructorId);

        sqlSession.close();

        return courses;
    }
}
