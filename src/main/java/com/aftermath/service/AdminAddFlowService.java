package com.aftermath.service;

import com.aftermath.mapper.CourseMapper;
import com.aftermath.mapper.FlowMapper;
import com.aftermath.mapper.InstructorMapper;
import com.aftermath.pojo.Course;
import com.aftermath.pojo.Flow;
import com.aftermath.pojo.Instructor;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdminAddFlowService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public boolean existFlow(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);

        Flow flow = flowMapper.selectByCourseId(courseId);

        sqlSession.close();

        return flow != null;
    }

    public boolean existCourse(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Course course = courseMapper.selectByName(courseName);

        sqlSession.close();

        return course != null;
    }

    public void addFlow(String courseId, String supervisorInstructor, String teacherInstructor) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);

        flowMapper.addFlow(courseId, supervisorInstructor, teacherInstructor);

        sqlSession.commit();

        sqlSession.close();
    }

    public Course queryCourseByName(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);

        Course course = courseMapper.selectByName(courseName);

        sqlSession.close();

        return course;
    }

    public List<Instructor> querySupervisorInstructor(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        List<Instructor> instructors = instructorMapper.selectSupervisorInstructor(courseName);

        sqlSession.close();

        return instructors;
    }

    public List<Instructor> queryTeacherInstructor(String courseName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        List<Instructor> instructors = instructorMapper.selectTeacherInstructor(courseName);

        sqlSession.close();

        return instructors;
    }
}
