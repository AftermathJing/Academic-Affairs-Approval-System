package com.aftermath.service;

import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Course;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentCourseService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Course> queryCourse(String studentId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        List<Course> courses = courseMapper.selectAllChoose(studentId);

        sqlSession.close();

        return courses;
    }
}
