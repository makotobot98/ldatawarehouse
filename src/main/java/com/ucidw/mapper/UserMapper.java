package com.ucidw.mapper;

import com.ucidw.common.ServerResponse;
import com.ucidw.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkUsername(String username);

    int checkEmail(String email);

    List<User> userList();

    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);


}