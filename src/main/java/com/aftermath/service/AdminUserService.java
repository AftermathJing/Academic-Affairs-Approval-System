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

import java.util.List;

public class AdminUserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Student> selectStudents(){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.selectAll();

        sqlSession.close();

        return students;
    }

    public List<Instructor> selectInstructors(){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        List<Instructor> instructors = instructorMapper.selectAll();

        sqlSession.close();

        return instructors;
    }

    public List<Admin> selectAdmins(){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        List<Admin> admins = adminMapper.selectAll();

        sqlSession.close();

        return admins;
    }

    public void deleteAdmin(String adminId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        adminMapper.deleteById(adminId);

        sqlSession.commit();

        sqlSession.close();

    }

    public void deleteInstructor(String instructorId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        instructorMapper.deleteById(instructorId);

        sqlSession.commit();

        sqlSession.close();

    }

    public void deleteStudent(String studentId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        studentMapper.deleteById(studentId);

        sqlSession.commit();

        sqlSession.close();

    }

    public boolean existStudent(String studentId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.selectById(studentId);

        sqlSession.close();

        return student != null;
    }

    public boolean existInstructor(String instructorId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);

        Instructor instructor = instructorMapper.selectById(instructorId);

        sqlSession.close();

        return instructor != null;
    }

    public boolean existAdmin(String adminId){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        Admin admin = adminMapper.selectById(adminId);

        sqlSession.close();

        return admin != null;
    }
}
