package com.xian.service;

import com.xian.model.Users;
import com.xian.model.vo.LoginVo;
import com.xian.model.vo.RegisterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface IUsersService  {

    void updateById(Users users);


    List<Users> list();

    Users getById();

    void save(Users users);

    void removeById(Integer id);

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    void logout();
}
