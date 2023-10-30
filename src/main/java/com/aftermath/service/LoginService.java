package com.aftermath.service;

import com.aftermath.mapper.AdminMapper;
import com.aftermath.mapper.InstructorMapper;
import com.aftermath.mapper.StudentMapper;
import com.aftermath.pojo.Admin;
import com.aftermath.pojo.Instructor;
import com.aftermath.pojo.Student;
import com.aftermath.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class LoginService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public Student selectStudent(String username, String password){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.selectByIdPassword(username, password);

        sqlSession.close();

        return student;
    }

    public Instructor selectInstructor(String username, String password){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        Instructor instructor = instructorMapper.selectByIdPassword(username, password);

        sqlSession.close();

        return instructor;
    }

    public Admin selectAdmin(String username, String password){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        Admin admin = adminMapper.selectByIdPassword(username, password);

        sqlSession.close();

        return admin;
    }
}
