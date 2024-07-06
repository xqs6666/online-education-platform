package com.xian.service.impl;

import com.xian.model.Users;
import com.xian.mapper.UsersMapper;
import com.xian.properties.JwtProperties;
import com.xian.service.IUsersService;
import com.xian.util.JwtUtil;
import com.xian.vo.LoginVo;
import com.xian.vo.RegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Service
public class UsersServiceImpl  implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void updateById(Users users) {
        usersMapper.updateById(users);
    }

    @Override
    public List<Users> list() {
        return usersMapper.list();
    }

    @Override
    public Users getById(Integer id) {
        return usersMapper.getById(id);
    }

    @Override
    public void save(Users users) {
        usersMapper.save(users);
    }

    @Override
    public void removeById(Integer id) {
        usersMapper.removeById(id);
    }

    @Override
    public String login(LoginVo loginVo) {
        // 1. 验证用户名密码
        if (loginVo.getUsername()==null || loginVo.getPassword()==null) {
            throw new RuntimeException("用户名或密码不能为空");
        }
        Users users=usersMapper.queryUserByUsername(loginVo.getUsername());
        if (users==null) {
            throw new RuntimeException("用户名错误");
        }
        if (!users.getPassword().equals(loginVo.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        // 2. 生成token
        HashMap<String, Object> info = new HashMap<>();
        info.put("username", users.getUsername());
        info.put("id", users.getUserId());
        info.put("userType",users.getUserType());
        String token = JwtUtil.generateToken(jwtProperties.getSecret(), jwtProperties.getExpiration(), info);
        // 3. 返回token
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        if (registerVo.getUsername()==null || registerVo.getPassword() == null||registerVo.getEmail()==null||registerVo.getUserType()==null) {
            throw new RuntimeException("不能为空");
        }
        // 1. 验证用户名(唯一性)
        Users users = usersMapper.queryUserByUsername(registerVo.getUsername());
        if (users!=null) {
            throw new RuntimeException("用户名已存在");
        }
        // 2. 验证邮箱(唯一性)
        users = usersMapper.queryUserByEmail(registerVo.getEmail());
        if (users!=null) {
            throw new RuntimeException("邮箱已存在");
        }

        // 3. 保存用户
        Users user = new Users();
        BeanUtils.copyProperties(registerVo, user);

        usersMapper.save(user);
    }
}
