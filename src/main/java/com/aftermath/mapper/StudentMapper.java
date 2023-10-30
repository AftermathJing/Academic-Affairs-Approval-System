package com.aftermath.mapper;

import com.aftermath.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();

    Student selectById(@Param("id") String id);

    void deleteById(@Param("id") String id);

    Student selectByIdPassword(@Param("id") String id, @Param("password") String password);
}
