package com.xian.controller;

import com.xian.model.Users;
import com.xian.service.IUsersService;
import com.xian.util.Result;
import com.xian.vo.LoginVo;
import com.xian.vo.RegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@RestController
@RequestMapping("/users")
@Api(tags = "UsersController")
public class UsersController {
    @Autowired
    private IUsersService iUsersService;



    //登录
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(@RequestBody LoginVo loginVo) {
        String token=iUsersService.login(loginVo);
        return Result.success(token);
    }

    //注册
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result register(@RequestBody RegisterVo registerVo) {
        iUsersService.register(registerVo);
        return Result.success();
    }

    //登出
    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public Result logout() {
        iUsersService.logout();
        return Result.success();
    }



    //获取所有用户信息
    @GetMapping("/getUsers")
    @ApiOperation(value = "获取所有用户信息")
    public Result<List<Users>> getUsers() {
        return Result.success(iUsersService.list());
    }

    //获取单个用户信息
    @GetMapping("/getUser")
    @ApiOperation(value = "获取单个用户信息")
    public Result<Users> getUser(Integer id) {
        return Result.success(iUsersService.getById(id));
    }

    //新增用户信息
    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户信息")
    public Result addUser(@RequestBody Users users) {
        iUsersService.save(users);
        return Result.success();
    }

    //根据id删除用户信息
     @PostMapping("/deleteUserById")
     @ApiOperation(value = "根据id删除用户信息")
     public Result deleteUserById(Integer id) {
     iUsersService.removeById(id);
     return Result.success();
     }


    //修改用户信息
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    public Result updateUser(@RequestBody Users users) {
        iUsersService.updateById(users);
        return Result.success();
    }




}
