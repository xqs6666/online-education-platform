package com.xian.service.impl;

import com.xian.cotext.UserContext;
import com.xian.model.Users;
import com.xian.mapper.UsersMapper;
import com.xian.model.vo.LoginVo;
import com.xian.model.vo.RegisterVo;
import com.xian.properties.JwtProperties;
import com.xian.service.IUsersService;
import com.xian.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private static final String REGISTER_QUEUE = "registerQueue";

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private final static String USER_TOKEN = "user:";

    @Override
    public void updateById(Users users) {
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");

        if (users.getUserId()!=null){
            if (users.getUserId()!=userId){
                throw new RuntimeException("非法操作");
            }
        }
        Users newUser = new Users();
        BeanUtils.copyProperties(users,newUser);
        newUser.setUserId(userId);
        usersMapper.updateById(newUser);
    }

    @Override
    public List<Users> list() {
        return usersMapper.list();
    }

    @Override
    public Users getById() {
        Map<String, Object> userMap = UserContext.getUser();
        if (userMap==null){
            throw new RuntimeException("非法操作");
        }
        Integer userId = (Integer) userMap.get("userId");
        Users user = usersMapper.getById(userId);

        return user;
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
        // 验证用户名密码
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
        // 生成token
        HashMap<String, Object> info = new HashMap<>();
        info.put("userId", users.getUserId());
        String token = JwtUtil.generateToken(jwtProperties.getSecret(), jwtProperties.getExpiration(), info);

        // 3. redis存储信息 1天过期
        // redis存储信息 1天过期
        // 清除之前的信息
        info.clear();
        info.put("userId", users.getUserId());
        info.put("username", users.getUsername());
        info.put("userType",users.getUserType());
        redisTemplate.opsForValue().set(USER_TOKEN+users.getUserId(),info,1, TimeUnit.DAYS);

        return token;
    }

    //用户提交注册表单验证后，系统将注册信息推送到消息队列中，后台服务从队列中取出信息并异步发送欢迎邮件。
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

        // 4. 发送注册成功信息
        redisTemplate.opsForList().leftPush(REGISTER_QUEUE, user); // 将用户信息推送到队列中
    }

    @Override
    public void logout() {
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");
        Boolean aBoolean = redisTemplate.delete(USER_TOKEN + userId);
        if (!aBoolean) {
            throw new RuntimeException("退出失败");
        }
    }
}
