package com.xian.mapper;

import com.xian.model.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface UsersMapper {

    void updateById(Users users);

    List<Users> list();

    Users getById(Integer id);

    void save(Users users);

    void removeById(Integer id);

    Users queryUserByUsername(String username);

    Users queryUserByEmail(String email);
}
