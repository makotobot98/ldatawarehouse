package com.ucidw.controller;

import com.github.pagehelper.PageInfo;
import com.ucidw.common.Const;
import com.ucidw.common.ServerResponse;
import com.ucidw.model.User;
import com.ucidw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dwh/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> add(User user) {
        return iUserService.add(user);
    }


    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        ServerResponse<PageInfo> users = iUserService.getUsers(pageNum, pageSize);
        return users;

    }
    //删除用户

    @RequestMapping("deleteUser.do")
    @ResponseBody
    public ServerResponse<User> delete(int id) {
        return iUserService.deleteByPrimary(id);
    }
}
