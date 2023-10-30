package com.aftermath.service;

import com.aftermath.mapper.CourseMapper;
import com.aftermath.pojo.Course;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdminCourseService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Course> queryCourse() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        List<Course> courses = courseMapper.selectAll();

        sqlSession.close();

        return courses;
    }

    public boolean existSameIdCourse(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Course course = courseMapper.selectById(courseId);

        sqlSession.close();

        return course != null;
    }

    public boolean existSameNameCourse(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Course course = courseMapper.selectByName(courseName);

        sqlSession.close();

        return course != null;
    }

    public void insertCourse(String courseId, String courseName, Double credits, Short beginYear, String semester, String notes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.insertCourse(courseId, courseName, credits, beginYear, semester, notes);

        sqlSession.commit();

        sqlSession.close();

    }

    public boolean insertSuccess(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Course course = courseMapper.selectById(courseId);

        sqlSession.close();

        return course != null;
    }

    public void deleteCourse(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        courseMapper.deleteById(courseId);

        sqlSession.commit();

        sqlSession.close();
    }
}
