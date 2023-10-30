package com.aftermath.mapper;

import com.aftermath.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    List<Admin> selectAll();

    Admin selectById(@Param("id") String id);

    void deleteById(@Param("id") String id);

    Admin selectByIdPassword(@Param("id") String id, @Param("password") String password);
}
