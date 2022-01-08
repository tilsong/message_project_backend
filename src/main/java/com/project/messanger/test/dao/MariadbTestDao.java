package com.project.messanger.test.dao;

import com.project.messanger.test.Model.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MariadbTestDao {

    List<TestUser> getAllUsers();
}
