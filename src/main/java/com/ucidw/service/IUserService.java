package com.ucidw.service;


import com.github.pagehelper.PageInfo;
import com.ucidw.common.ServerResponse;
import com.ucidw.model.User;

import javax.servlet.http.HttpSession;

public interface IUserService {


    ServerResponse<User> login(String username, String password);

    ServerResponse<String> add(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse<PageInfo> getUsers(int pageNum, int pageSize);

    ServerResponse<User> deleteByPrimary(int id);
}
